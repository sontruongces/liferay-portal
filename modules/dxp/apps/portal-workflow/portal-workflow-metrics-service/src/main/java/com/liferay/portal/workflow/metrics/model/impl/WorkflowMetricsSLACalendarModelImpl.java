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

package com.liferay.portal.workflow.metrics.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLACalendar;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLACalendarModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the WorkflowMetricsSLACalendar service. Represents a row in the &quot;WorkflowMetricsSLACalendar&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link WorkflowMetricsSLACalendarModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WorkflowMetricsSLACalendarImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowMetricsSLACalendarImpl
 * @see WorkflowMetricsSLACalendar
 * @see WorkflowMetricsSLACalendarModel
 * @generated
 */
@ProviderType
public class WorkflowMetricsSLACalendarModelImpl extends BaseModelImpl<WorkflowMetricsSLACalendar>
	implements WorkflowMetricsSLACalendarModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a workflow metrics sla calendar model instance should use the {@link WorkflowMetricsSLACalendar} interface instead.
	 */
	public static final String TABLE_NAME = "WorkflowMetricsSLACalendar";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "uuid_", Types.VARCHAR },
			{ "workflowMetricsSLACalendarId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("workflowMetricsSLACalendarId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table WorkflowMetricsSLACalendar (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,workflowMetricsSLACalendarId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table WorkflowMetricsSLACalendar";
	public static final String ORDER_BY_JPQL = " ORDER BY workflowMetricsSLACalendar.workflowMetricsSLACalendarId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY WorkflowMetricsSLACalendar.workflowMetricsSLACalendarId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.metrics.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLACalendar"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.metrics.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLACalendar"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.workflow.metrics.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLACalendar"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long WORKFLOWMETRICSSLACALENDARID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.workflow.metrics.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLACalendar"));

	public WorkflowMetricsSLACalendarModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _workflowMetricsSLACalendarId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setWorkflowMetricsSLACalendarId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _workflowMetricsSLACalendarId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return WorkflowMetricsSLACalendar.class;
	}

	@Override
	public String getModelClassName() {
		return WorkflowMetricsSLACalendar.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<WorkflowMetricsSLACalendar, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<WorkflowMetricsSLACalendar, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<WorkflowMetricsSLACalendar, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(attributeName,
				attributeGetterFunction.apply((WorkflowMetricsSLACalendar)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<WorkflowMetricsSLACalendar, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<WorkflowMetricsSLACalendar, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((WorkflowMetricsSLACalendar)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<WorkflowMetricsSLACalendar, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<WorkflowMetricsSLACalendar, Object>> getAttributeSetterBiConsumers() {
		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<WorkflowMetricsSLACalendar, Object>> _attributeGetterFunctions;
	private static final Map<String, BiConsumer<WorkflowMetricsSLACalendar, Object>> _attributeSetterBiConsumers;

	static {
		Map<String, Function<WorkflowMetricsSLACalendar, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<WorkflowMetricsSLACalendar, Object>>();
		Map<String, BiConsumer<WorkflowMetricsSLACalendar, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<WorkflowMetricsSLACalendar, ?>>();


		attributeGetterFunctions.put("mvccVersion", WorkflowMetricsSLACalendar::getMvccVersion);
		attributeSetterBiConsumers.put("mvccVersion", (BiConsumer<WorkflowMetricsSLACalendar, Long>)WorkflowMetricsSLACalendar::setMvccVersion);
		attributeGetterFunctions.put("uuid", WorkflowMetricsSLACalendar::getUuid);
		attributeSetterBiConsumers.put("uuid", (BiConsumer<WorkflowMetricsSLACalendar, String>)WorkflowMetricsSLACalendar::setUuid);
		attributeGetterFunctions.put("workflowMetricsSLACalendarId", WorkflowMetricsSLACalendar::getWorkflowMetricsSLACalendarId);
		attributeSetterBiConsumers.put("workflowMetricsSLACalendarId", (BiConsumer<WorkflowMetricsSLACalendar, Long>)WorkflowMetricsSLACalendar::setWorkflowMetricsSLACalendarId);
		attributeGetterFunctions.put("groupId", WorkflowMetricsSLACalendar::getGroupId);
		attributeSetterBiConsumers.put("groupId", (BiConsumer<WorkflowMetricsSLACalendar, Long>)WorkflowMetricsSLACalendar::setGroupId);
		attributeGetterFunctions.put("companyId", WorkflowMetricsSLACalendar::getCompanyId);
		attributeSetterBiConsumers.put("companyId", (BiConsumer<WorkflowMetricsSLACalendar, Long>)WorkflowMetricsSLACalendar::setCompanyId);
		attributeGetterFunctions.put("userId", WorkflowMetricsSLACalendar::getUserId);
		attributeSetterBiConsumers.put("userId", (BiConsumer<WorkflowMetricsSLACalendar, Long>)WorkflowMetricsSLACalendar::setUserId);
		attributeGetterFunctions.put("userName", WorkflowMetricsSLACalendar::getUserName);
		attributeSetterBiConsumers.put("userName", (BiConsumer<WorkflowMetricsSLACalendar, String>)WorkflowMetricsSLACalendar::setUserName);
		attributeGetterFunctions.put("createDate", WorkflowMetricsSLACalendar::getCreateDate);
		attributeSetterBiConsumers.put("createDate", (BiConsumer<WorkflowMetricsSLACalendar, Date>)WorkflowMetricsSLACalendar::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", WorkflowMetricsSLACalendar::getModifiedDate);
		attributeSetterBiConsumers.put("modifiedDate", (BiConsumer<WorkflowMetricsSLACalendar, Date>)WorkflowMetricsSLACalendar::setModifiedDate);


		_attributeGetterFunctions = Collections.unmodifiableMap(attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap((Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getWorkflowMetricsSLACalendarId() {
		return _workflowMetricsSLACalendarId;
	}

	@Override
	public void setWorkflowMetricsSLACalendarId(
		long workflowMetricsSLACalendarId) {
		_workflowMetricsSLACalendarId = workflowMetricsSLACalendarId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				WorkflowMetricsSLACalendar.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			WorkflowMetricsSLACalendar.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public WorkflowMetricsSLACalendar toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (WorkflowMetricsSLACalendar)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		WorkflowMetricsSLACalendarImpl workflowMetricsSLACalendarImpl = new WorkflowMetricsSLACalendarImpl();

		workflowMetricsSLACalendarImpl.setMvccVersion(getMvccVersion());
		workflowMetricsSLACalendarImpl.setUuid(getUuid());
		workflowMetricsSLACalendarImpl.setWorkflowMetricsSLACalendarId(getWorkflowMetricsSLACalendarId());
		workflowMetricsSLACalendarImpl.setGroupId(getGroupId());
		workflowMetricsSLACalendarImpl.setCompanyId(getCompanyId());
		workflowMetricsSLACalendarImpl.setUserId(getUserId());
		workflowMetricsSLACalendarImpl.setUserName(getUserName());
		workflowMetricsSLACalendarImpl.setCreateDate(getCreateDate());
		workflowMetricsSLACalendarImpl.setModifiedDate(getModifiedDate());

		workflowMetricsSLACalendarImpl.resetOriginalValues();

		return workflowMetricsSLACalendarImpl;
	}

	@Override
	public int compareTo(WorkflowMetricsSLACalendar workflowMetricsSLACalendar) {
		long primaryKey = workflowMetricsSLACalendar.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowMetricsSLACalendar)) {
			return false;
		}

		WorkflowMetricsSLACalendar workflowMetricsSLACalendar = (WorkflowMetricsSLACalendar)obj;

		long primaryKey = workflowMetricsSLACalendar.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		WorkflowMetricsSLACalendarModelImpl workflowMetricsSLACalendarModelImpl = this;

		workflowMetricsSLACalendarModelImpl._originalUuid = workflowMetricsSLACalendarModelImpl._uuid;

		workflowMetricsSLACalendarModelImpl._originalGroupId = workflowMetricsSLACalendarModelImpl._groupId;

		workflowMetricsSLACalendarModelImpl._setOriginalGroupId = false;

		workflowMetricsSLACalendarModelImpl._originalCompanyId = workflowMetricsSLACalendarModelImpl._companyId;

		workflowMetricsSLACalendarModelImpl._setOriginalCompanyId = false;

		workflowMetricsSLACalendarModelImpl._setModifiedDate = false;

		workflowMetricsSLACalendarModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<WorkflowMetricsSLACalendar> toCacheModel() {
		WorkflowMetricsSLACalendarCacheModel workflowMetricsSLACalendarCacheModel =
			new WorkflowMetricsSLACalendarCacheModel();

		workflowMetricsSLACalendarCacheModel.mvccVersion = getMvccVersion();

		workflowMetricsSLACalendarCacheModel.uuid = getUuid();

		String uuid = workflowMetricsSLACalendarCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			workflowMetricsSLACalendarCacheModel.uuid = null;
		}

		workflowMetricsSLACalendarCacheModel.workflowMetricsSLACalendarId = getWorkflowMetricsSLACalendarId();

		workflowMetricsSLACalendarCacheModel.groupId = getGroupId();

		workflowMetricsSLACalendarCacheModel.companyId = getCompanyId();

		workflowMetricsSLACalendarCacheModel.userId = getUserId();

		workflowMetricsSLACalendarCacheModel.userName = getUserName();

		String userName = workflowMetricsSLACalendarCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			workflowMetricsSLACalendarCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			workflowMetricsSLACalendarCacheModel.createDate = createDate.getTime();
		}
		else {
			workflowMetricsSLACalendarCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			workflowMetricsSLACalendarCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			workflowMetricsSLACalendarCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		return workflowMetricsSLACalendarCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<WorkflowMetricsSLACalendar, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((4 * attributeGetterFunctions.size()) +
				2);

		sb.append("{");

		for (Map.Entry<String, Function<WorkflowMetricsSLACalendar, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<WorkflowMetricsSLACalendar, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply(
					(WorkflowMetricsSLACalendar)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<WorkflowMetricsSLACalendar, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((5 * attributeGetterFunctions.size()) +
				4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<WorkflowMetricsSLACalendar, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<WorkflowMetricsSLACalendar, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply(
					(WorkflowMetricsSLACalendar)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = WorkflowMetricsSLACalendar.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			WorkflowMetricsSLACalendar.class, ModelWrapper.class
		};
	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _workflowMetricsSLACalendarId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _columnBitmask;
	private WorkflowMetricsSLACalendar _escapedModel;
}