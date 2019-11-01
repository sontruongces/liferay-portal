/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phytohormone.internal.messaging;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.StopWatch;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class SynchronizeEntitlementMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		int checkInterval = GetterUtil.getInteger(
			properties.get("check.interval"), 15);

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, checkInterval, TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Running account entitlement synchronization");
		}

		List<EntitlementDefinition> entitlementDefinitions =
			_entitlementDefinitionLocalService.getEntitlementDefinitions(
				Account.class.getName(), WorkflowConstants.STATUS_APPROVED);

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			_processEntitlementDefinition(
				entitlementDefinition.getCompanyId(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getClassNameId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Running contact entitlement synchronization");
		}

		entitlementDefinitions =
			_entitlementDefinitionLocalService.getEntitlementDefinitions(
				Contact.class.getName(), WorkflowConstants.STATUS_APPROVED);

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			_processEntitlementDefinition(
				entitlementDefinition.getCompanyId(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getClassNameId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}
	}

	private void _addEntitlements(
			long companyId, long entitlementDefinitionId, long classNameId,
			String definition)
		throws Exception {

		int count = 0;

		long defaultUserId = _userLocalService.getDefaultUserId(companyId);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataAccess.getConnection();

			String columnName = null;

			if (classNameId == _portal.getClassNameId(Account.class)) {
				columnName = "Koroneiki_Account.accountId";
			}
			else {
				columnName = "Koroneiki_Contact.contactId";
			}

			preparedStatement = connection.prepareStatement(
				_getAddSql(definition, columnName));

			preparedStatement.setLong(1, entitlementDefinitionId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long classPK = resultSet.getLong(columnName);

				_entitlementLocalService.addEntitlement(
					defaultUserId, entitlementDefinitionId, classNameId,
					classPK);

				count++;
			}
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug("Added " + count + " entitlements");
			}

			DataAccess.cleanUp(connection, preparedStatement, resultSet);
		}
	}

	private String _getAddSql(String definition, String columnName) {
		StringBundler sb = new StringBundler(6);

		sb.append(definition);

		if (StringUtil.containsIgnoreCase(
				definition, "where", StringPool.BLANK)) {

			sb.append(" and ");
		}
		else {
			sb.append(" where ");
		}

		sb.append(columnName);
		sb.append(" not in (select Koroneiki_Entitlement.classPK from ");
		sb.append("Koroneiki_Entitlement where ");
		sb.append("Koroneiki_Entitlement.entitlementDefinitionId = ?)");

		Timestamp now = CalendarUtil.getTimestamp(new Date());

		return StringUtil.replace(sb.toString(), "[$NOW$]", now.toString());
	}

	private String _getRemoveSql(String definition) {
		StringBundler sb = new StringBundler(6);

		sb.append("select Koroneiki_Entitlement.entitlementId from ");
		sb.append("Koroneiki_Entitlement where ");
		sb.append("(Koroneiki_Entitlement.entitlementDefinitionId = ?) and ");
		sb.append("(Koroneiki_Entitlement.classPK not in (");
		sb.append(definition);
		sb.append("))");

		Timestamp now = CalendarUtil.getTimestamp(new Date());

		return StringUtil.replace(sb.toString(), "[$NOW$]", now.toString());
	}

	private void _processEntitlementDefinition(
		long companyId, long entitlementDefinitionId, long classNameId,
		String name, String definition) {

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Adding entitlements for " + name);
			}

			_addEntitlements(
				companyId, entitlementDefinitionId, classNameId, definition);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Finished in " + stopWatch.getTime() + "ms");
		}

		stopWatch.reset();

		stopWatch.start();

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Removing entitlements for " + name);
			}

			_removeEntitlements(entitlementDefinitionId, definition);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Finished in " + stopWatch.getTime() + "ms");
		}
	}

	private void _removeEntitlements(
			long entitlementDefinitionId, String definition)
		throws Exception {

		int count = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DataAccess.getConnection();

			preparedStatement = connection.prepareStatement(
				_getRemoveSql(definition));

			preparedStatement.setLong(1, entitlementDefinitionId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				long entitlementId = resultSet.getLong(
					"Koroneiki_Entitlement.entitlementId");

				_entitlementLocalService.deleteEntitlement(entitlementId);

				count++;
			}
		}
		finally {
			if (_log.isDebugEnabled()) {
				_log.debug("Removed " + count + " entitlements");
			}

			DataAccess.cleanUp(connection, preparedStatement, resultSet);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizeEntitlementMessageListener.class);

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

	@Reference
	private EntitlementLocalService _entitlementLocalService;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED)
	private ModuleServiceLifecycle _moduleServiceLifecycle;

	@Reference
	private Portal _portal;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

	@Reference
	private UserLocalService _userLocalService;

}