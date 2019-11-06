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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.EntitlementDefinitionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionService;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/entitlement-definition.properties",
	scope = ServiceScope.PROTOTYPE,
	service = EntitlementDefinitionResource.class
)
public class EntitlementDefinitionResourceImpl
	extends BaseEntitlementDefinitionResourceImpl {

	@Override
	public void deleteEntitlementDefinition(String entitlementDefinitionKey)
		throws Exception {

		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			entitlementDefinition =
				_entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionKey);

		_entitlementDefinitionService.deleteEntitlementDefinition(
			entitlementDefinition.getEntitlementDefinitionId());
	}

	@Override
	public Page<EntitlementDefinition> getAccountEntitlementDefinitionsPage(
			String search, Pagination pagination)
		throws Exception {

		long classNameId = _classNameLocalService.getClassNameId(Account.class);

		search = GetterUtil.getString(search);
		search = StringUtil.quote(search, StringPool.PERCENT);

		return Page.of(
			transform(
				_entitlementDefinitionLocalService.search(
					classNameId, search, pagination.getStartPosition(),
					pagination.getEndPosition()),
				entitlementDefinition ->
					EntitlementDefinitionUtil.toEntitlementDefinition(
						entitlementDefinition)),
			pagination,
			_entitlementDefinitionLocalService.searchCount(
				classNameId, search));
	}

	@Override
	public Page<EntitlementDefinition> getContactEntitlementDefinitionsPage(
			String search, Pagination pagination)
		throws Exception {

		long classNameId = _classNameLocalService.getClassNameId(Contact.class);

		search = GetterUtil.getString(search);
		search = StringUtil.quote(search, StringPool.PERCENT);

		return Page.of(
			transform(
				_entitlementDefinitionLocalService.search(
					classNameId, search, pagination.getStartPosition(),
					pagination.getEndPosition()),
				entitlementDefinition ->
					EntitlementDefinitionUtil.toEntitlementDefinition(
						entitlementDefinition)),
			pagination,
			_entitlementDefinitionLocalService.searchCount(
				classNameId, search));
	}

	@Override
	public EntitlementDefinition getEntitlementDefinition(
			String entitlementDefinitionKey)
		throws Exception {

		return EntitlementDefinitionUtil.toEntitlementDefinition(
			_entitlementDefinitionLocalService.getEntitlementDefinition(
				entitlementDefinitionKey));
	}

	@Override
	public EntitlementDefinition postAccountEntitlementDefinition(
			EntitlementDefinition entitlementDefinition)
		throws Exception {

		int status = WorkflowConstants.STATUS_APPROVED;

		EntitlementDefinition.Status entitlementDefinitionStatus =
			entitlementDefinition.getStatus();

		if (entitlementDefinitionStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				entitlementDefinitionStatus.toString());
		}

		return EntitlementDefinitionUtil.toEntitlementDefinition(
			_entitlementDefinitionService.addEntitlementDefinition(
				_classNameLocalService.getClassNameId(Account.class),
				entitlementDefinition.getName(),
				entitlementDefinition.getDescription(),
				entitlementDefinition.getDefinition(), status));
	}

	@Override
	public EntitlementDefinition postContactEntitlementDefinition(
			EntitlementDefinition entitlementDefinition)
		throws Exception {

		int status = WorkflowConstants.STATUS_APPROVED;

		EntitlementDefinition.Status entitlementDefinitionStatus =
			entitlementDefinition.getStatus();

		if (entitlementDefinitionStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				entitlementDefinitionStatus.toString());
		}

		return EntitlementDefinitionUtil.toEntitlementDefinition(
			_entitlementDefinitionService.addEntitlementDefinition(
				_classNameLocalService.getClassNameId(Contact.class),
				entitlementDefinition.getName(),
				entitlementDefinition.getDescription(),
				entitlementDefinition.getDefinition(), status));
	}

	@Override
	public void postEntitlementDefinitionSynchronize(
			String entitlementDefinitionKey)
		throws Exception {

		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			entitlementDefinition =
				_entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionKey);

		_entitlementDefinitionService.synchronizeEntitlementDefinition(
			entitlementDefinition.getEntitlementDefinitionId());
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

	@Reference
	private EntitlementDefinitionService _entitlementDefinitionService;

}