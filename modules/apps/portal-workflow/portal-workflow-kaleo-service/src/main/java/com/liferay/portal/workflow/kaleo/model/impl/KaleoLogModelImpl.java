/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.KaleoLogModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the KaleoLog service. Represents a row in the &quot;KaleoLog&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>KaleoLogModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoLogImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoLogImpl
 * @generated
 */
@ProviderType
public class KaleoLogModelImpl
	extends BaseModelImpl<KaleoLog> implements KaleoLogModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo log model instance should use the <code>KaleoLog</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoLog";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"kaleoLogId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"kaleoClassName", Types.VARCHAR}, {"kaleoClassPK", Types.BIGINT},
		{"kaleoDefinitionVersionId", Types.BIGINT},
		{"kaleoInstanceId", Types.BIGINT},
		{"kaleoInstanceTokenId", Types.BIGINT},
		{"kaleoTaskInstanceTokenId", Types.BIGINT},
		{"kaleoNodeName", Types.VARCHAR}, {"terminalKaleoNode", Types.BOOLEAN},
		{"kaleoActionId", Types.BIGINT}, {"kaleoActionName", Types.VARCHAR},
		{"kaleoActionDescription", Types.VARCHAR},
		{"previousKaleoNodeId", Types.BIGINT},
		{"previousKaleoNodeName", Types.VARCHAR},
		{"previousAssigneeClassName", Types.VARCHAR},
		{"previousAssigneeClassPK", Types.BIGINT},
		{"currentAssigneeClassName", Types.VARCHAR},
		{"currentAssigneeClassPK", Types.BIGINT}, {"type_", Types.VARCHAR},
		{"comment_", Types.CLOB}, {"startDate", Types.TIMESTAMP},
		{"endDate", Types.TIMESTAMP}, {"duration", Types.BIGINT},
		{"workflowContext", Types.CLOB}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoLogId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("kaleoClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoDefinitionVersionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoInstanceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoInstanceTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoTaskInstanceTokenId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoNodeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("terminalKaleoNode", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("kaleoActionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("kaleoActionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoActionDescription", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("previousKaleoNodeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("previousKaleoNodeName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("previousAssigneeClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("previousAssigneeClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("currentAssigneeClassName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("currentAssigneeClassPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("comment_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("duration", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("workflowContext", Types.CLOB);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoLog (mvccVersion LONG default 0 not null,kaleoLogId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,modifiedDate DATE null,kaleoClassName VARCHAR(200) null,kaleoClassPK LONG,kaleoDefinitionVersionId LONG,kaleoInstanceId LONG,kaleoInstanceTokenId LONG,kaleoTaskInstanceTokenId LONG,kaleoNodeName VARCHAR(200) null,terminalKaleoNode BOOLEAN,kaleoActionId LONG,kaleoActionName VARCHAR(200) null,kaleoActionDescription STRING null,previousKaleoNodeId LONG,previousKaleoNodeName VARCHAR(200) null,previousAssigneeClassName VARCHAR(200) null,previousAssigneeClassPK LONG,currentAssigneeClassName VARCHAR(200) null,currentAssigneeClassPK LONG,type_ VARCHAR(50) null,comment_ TEXT null,startDate DATE null,endDate DATE null,duration LONG,workflowContext TEXT null)";

	public static final String TABLE_SQL_DROP = "drop table KaleoLog";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoLog.kaleoLogId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoLog.kaleoLogId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoLog"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.model.KaleoLog"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.model.KaleoLog"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long KALEOCLASSNAME_COLUMN_BITMASK = 2L;

	public static final long KALEOCLASSPK_COLUMN_BITMASK = 4L;

	public static final long KALEODEFINITIONVERSIONID_COLUMN_BITMASK = 8L;

	public static final long KALEOINSTANCEID_COLUMN_BITMASK = 16L;

	public static final long KALEOINSTANCETOKENID_COLUMN_BITMASK = 32L;

	public static final long KALEOTASKINSTANCETOKENID_COLUMN_BITMASK = 64L;

	public static final long TYPE_COLUMN_BITMASK = 128L;

	public static final long KALEOLOGID_COLUMN_BITMASK = 256L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.workflow.kaleo.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.workflow.kaleo.model.KaleoLog"));

	public KaleoLogModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoLogId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoLogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoLogId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoLog.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoLog.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoLog, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((KaleoLog)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoLog, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoLog, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoLog)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoLog, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoLog, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<KaleoLog, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoLog, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoLog, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<KaleoLog, Object>>();
		Map<String, BiConsumer<KaleoLog, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<KaleoLog, ?>>();

		attributeGetterFunctions.put("mvccVersion", KaleoLog::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setMvccVersion);
		attributeGetterFunctions.put("kaleoLogId", KaleoLog::getKaleoLogId);
		attributeSetterBiConsumers.put(
			"kaleoLogId", (BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoLogId);
		attributeGetterFunctions.put("groupId", KaleoLog::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<KaleoLog, Long>)KaleoLog::setGroupId);
		attributeGetterFunctions.put("companyId", KaleoLog::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<KaleoLog, Long>)KaleoLog::setCompanyId);
		attributeGetterFunctions.put("userId", KaleoLog::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<KaleoLog, Long>)KaleoLog::setUserId);
		attributeGetterFunctions.put("userName", KaleoLog::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<KaleoLog, String>)KaleoLog::setUserName);
		attributeGetterFunctions.put("createDate", KaleoLog::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<KaleoLog, Date>)KaleoLog::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", KaleoLog::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<KaleoLog, Date>)KaleoLog::setModifiedDate);
		attributeGetterFunctions.put(
			"kaleoClassName", KaleoLog::getKaleoClassName);
		attributeSetterBiConsumers.put(
			"kaleoClassName",
			(BiConsumer<KaleoLog, String>)KaleoLog::setKaleoClassName);
		attributeGetterFunctions.put("kaleoClassPK", KaleoLog::getKaleoClassPK);
		attributeSetterBiConsumers.put(
			"kaleoClassPK",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoClassPK);
		attributeGetterFunctions.put(
			"kaleoDefinitionVersionId", KaleoLog::getKaleoDefinitionVersionId);
		attributeSetterBiConsumers.put(
			"kaleoDefinitionVersionId",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoDefinitionVersionId);
		attributeGetterFunctions.put(
			"kaleoInstanceId", KaleoLog::getKaleoInstanceId);
		attributeSetterBiConsumers.put(
			"kaleoInstanceId",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoInstanceId);
		attributeGetterFunctions.put(
			"kaleoInstanceTokenId", KaleoLog::getKaleoInstanceTokenId);
		attributeSetterBiConsumers.put(
			"kaleoInstanceTokenId",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoInstanceTokenId);
		attributeGetterFunctions.put(
			"kaleoTaskInstanceTokenId", KaleoLog::getKaleoTaskInstanceTokenId);
		attributeSetterBiConsumers.put(
			"kaleoTaskInstanceTokenId",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoTaskInstanceTokenId);
		attributeGetterFunctions.put(
			"kaleoNodeName", KaleoLog::getKaleoNodeName);
		attributeSetterBiConsumers.put(
			"kaleoNodeName",
			(BiConsumer<KaleoLog, String>)KaleoLog::setKaleoNodeName);
		attributeGetterFunctions.put(
			"terminalKaleoNode", KaleoLog::getTerminalKaleoNode);
		attributeSetterBiConsumers.put(
			"terminalKaleoNode",
			(BiConsumer<KaleoLog, Boolean>)KaleoLog::setTerminalKaleoNode);
		attributeGetterFunctions.put(
			"kaleoActionId", KaleoLog::getKaleoActionId);
		attributeSetterBiConsumers.put(
			"kaleoActionId",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setKaleoActionId);
		attributeGetterFunctions.put(
			"kaleoActionName", KaleoLog::getKaleoActionName);
		attributeSetterBiConsumers.put(
			"kaleoActionName",
			(BiConsumer<KaleoLog, String>)KaleoLog::setKaleoActionName);
		attributeGetterFunctions.put(
			"kaleoActionDescription", KaleoLog::getKaleoActionDescription);
		attributeSetterBiConsumers.put(
			"kaleoActionDescription",
			(BiConsumer<KaleoLog, String>)KaleoLog::setKaleoActionDescription);
		attributeGetterFunctions.put(
			"previousKaleoNodeId", KaleoLog::getPreviousKaleoNodeId);
		attributeSetterBiConsumers.put(
			"previousKaleoNodeId",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setPreviousKaleoNodeId);
		attributeGetterFunctions.put(
			"previousKaleoNodeName", KaleoLog::getPreviousKaleoNodeName);
		attributeSetterBiConsumers.put(
			"previousKaleoNodeName",
			(BiConsumer<KaleoLog, String>)KaleoLog::setPreviousKaleoNodeName);
		attributeGetterFunctions.put(
			"previousAssigneeClassName",
			KaleoLog::getPreviousAssigneeClassName);
		attributeSetterBiConsumers.put(
			"previousAssigneeClassName",
			(BiConsumer<KaleoLog, String>)
				KaleoLog::setPreviousAssigneeClassName);
		attributeGetterFunctions.put(
			"previousAssigneeClassPK", KaleoLog::getPreviousAssigneeClassPK);
		attributeSetterBiConsumers.put(
			"previousAssigneeClassPK",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setPreviousAssigneeClassPK);
		attributeGetterFunctions.put(
			"currentAssigneeClassName", KaleoLog::getCurrentAssigneeClassName);
		attributeSetterBiConsumers.put(
			"currentAssigneeClassName",
			(BiConsumer<KaleoLog, String>)
				KaleoLog::setCurrentAssigneeClassName);
		attributeGetterFunctions.put(
			"currentAssigneeClassPK", KaleoLog::getCurrentAssigneeClassPK);
		attributeSetterBiConsumers.put(
			"currentAssigneeClassPK",
			(BiConsumer<KaleoLog, Long>)KaleoLog::setCurrentAssigneeClassPK);
		attributeGetterFunctions.put("type", KaleoLog::getType);
		attributeSetterBiConsumers.put(
			"type", (BiConsumer<KaleoLog, String>)KaleoLog::setType);
		attributeGetterFunctions.put("comment", KaleoLog::getComment);
		attributeSetterBiConsumers.put(
			"comment", (BiConsumer<KaleoLog, String>)KaleoLog::setComment);
		attributeGetterFunctions.put("startDate", KaleoLog::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate", (BiConsumer<KaleoLog, Date>)KaleoLog::setStartDate);
		attributeGetterFunctions.put("endDate", KaleoLog::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate", (BiConsumer<KaleoLog, Date>)KaleoLog::setEndDate);
		attributeGetterFunctions.put("duration", KaleoLog::getDuration);
		attributeSetterBiConsumers.put(
			"duration", (BiConsumer<KaleoLog, Long>)KaleoLog::setDuration);
		attributeGetterFunctions.put(
			"workflowContext", KaleoLog::getWorkflowContext);
		attributeSetterBiConsumers.put(
			"workflowContext",
			(BiConsumer<KaleoLog, String>)KaleoLog::setWorkflowContext);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getKaleoLogId() {
		return _kaleoLogId;
	}

	@Override
	public void setKaleoLogId(long kaleoLogId) {
		_columnBitmask = -1L;

		_kaleoLogId = kaleoLogId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
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
	public String getKaleoClassName() {
		if (_kaleoClassName == null) {
			return "";
		}
		else {
			return _kaleoClassName;
		}
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		_columnBitmask |= KALEOCLASSNAME_COLUMN_BITMASK;

		if (_originalKaleoClassName == null) {
			_originalKaleoClassName = _kaleoClassName;
		}

		_kaleoClassName = kaleoClassName;
	}

	public String getOriginalKaleoClassName() {
		return GetterUtil.getString(_originalKaleoClassName);
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_columnBitmask |= KALEOCLASSPK_COLUMN_BITMASK;

		if (!_setOriginalKaleoClassPK) {
			_setOriginalKaleoClassPK = true;

			_originalKaleoClassPK = _kaleoClassPK;
		}

		_kaleoClassPK = kaleoClassPK;
	}

	public long getOriginalKaleoClassPK() {
		return _originalKaleoClassPK;
	}

	@Override
	public long getKaleoDefinitionVersionId() {
		return _kaleoDefinitionVersionId;
	}

	@Override
	public void setKaleoDefinitionVersionId(long kaleoDefinitionVersionId) {
		_columnBitmask |= KALEODEFINITIONVERSIONID_COLUMN_BITMASK;

		if (!_setOriginalKaleoDefinitionVersionId) {
			_setOriginalKaleoDefinitionVersionId = true;

			_originalKaleoDefinitionVersionId = _kaleoDefinitionVersionId;
		}

		_kaleoDefinitionVersionId = kaleoDefinitionVersionId;
	}

	public long getOriginalKaleoDefinitionVersionId() {
		return _originalKaleoDefinitionVersionId;
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_columnBitmask |= KALEOINSTANCEID_COLUMN_BITMASK;

		if (!_setOriginalKaleoInstanceId) {
			_setOriginalKaleoInstanceId = true;

			_originalKaleoInstanceId = _kaleoInstanceId;
		}

		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getOriginalKaleoInstanceId() {
		return _originalKaleoInstanceId;
	}

	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_columnBitmask |= KALEOINSTANCETOKENID_COLUMN_BITMASK;

		if (!_setOriginalKaleoInstanceTokenId) {
			_setOriginalKaleoInstanceTokenId = true;

			_originalKaleoInstanceTokenId = _kaleoInstanceTokenId;
		}

		_kaleoInstanceTokenId = kaleoInstanceTokenId;
	}

	public long getOriginalKaleoInstanceTokenId() {
		return _originalKaleoInstanceTokenId;
	}

	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_columnBitmask |= KALEOTASKINSTANCETOKENID_COLUMN_BITMASK;

		if (!_setOriginalKaleoTaskInstanceTokenId) {
			_setOriginalKaleoTaskInstanceTokenId = true;

			_originalKaleoTaskInstanceTokenId = _kaleoTaskInstanceTokenId;
		}

		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
	}

	public long getOriginalKaleoTaskInstanceTokenId() {
		return _originalKaleoTaskInstanceTokenId;
	}

	@Override
	public String getKaleoNodeName() {
		if (_kaleoNodeName == null) {
			return "";
		}
		else {
			return _kaleoNodeName;
		}
	}

	@Override
	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	@Override
	public boolean getTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	@Override
	public boolean isTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	@Override
	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_terminalKaleoNode = terminalKaleoNode;
	}

	@Override
	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	@Override
	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;
	}

	@Override
	public String getKaleoActionName() {
		if (_kaleoActionName == null) {
			return "";
		}
		else {
			return _kaleoActionName;
		}
	}

	@Override
	public void setKaleoActionName(String kaleoActionName) {
		_kaleoActionName = kaleoActionName;
	}

	@Override
	public String getKaleoActionDescription() {
		if (_kaleoActionDescription == null) {
			return "";
		}
		else {
			return _kaleoActionDescription;
		}
	}

	@Override
	public void setKaleoActionDescription(String kaleoActionDescription) {
		_kaleoActionDescription = kaleoActionDescription;
	}

	@Override
	public long getPreviousKaleoNodeId() {
		return _previousKaleoNodeId;
	}

	@Override
	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_previousKaleoNodeId = previousKaleoNodeId;
	}

	@Override
	public String getPreviousKaleoNodeName() {
		if (_previousKaleoNodeName == null) {
			return "";
		}
		else {
			return _previousKaleoNodeName;
		}
	}

	@Override
	public void setPreviousKaleoNodeName(String previousKaleoNodeName) {
		_previousKaleoNodeName = previousKaleoNodeName;
	}

	@Override
	public String getPreviousAssigneeClassName() {
		if (_previousAssigneeClassName == null) {
			return "";
		}
		else {
			return _previousAssigneeClassName;
		}
	}

	@Override
	public void setPreviousAssigneeClassName(String previousAssigneeClassName) {
		_previousAssigneeClassName = previousAssigneeClassName;
	}

	@Override
	public long getPreviousAssigneeClassPK() {
		return _previousAssigneeClassPK;
	}

	@Override
	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_previousAssigneeClassPK = previousAssigneeClassPK;
	}

	@Override
	public String getCurrentAssigneeClassName() {
		if (_currentAssigneeClassName == null) {
			return "";
		}
		else {
			return _currentAssigneeClassName;
		}
	}

	@Override
	public void setCurrentAssigneeClassName(String currentAssigneeClassName) {
		_currentAssigneeClassName = currentAssigneeClassName;
	}

	@Override
	public long getCurrentAssigneeClassPK() {
		return _currentAssigneeClassPK;
	}

	@Override
	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_currentAssigneeClassPK = currentAssigneeClassPK;
	}

	@Override
	public String getType() {
		if (_type == null) {
			return "";
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (_originalType == null) {
			_originalType = _type;
		}

		_type = type;
	}

	public String getOriginalType() {
		return GetterUtil.getString(_originalType);
	}

	@Override
	public String getComment() {
		if (_comment == null) {
			return "";
		}
		else {
			return _comment;
		}
	}

	@Override
	public void setComment(String comment) {
		_comment = comment;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@Override
	public long getDuration() {
		return _duration;
	}

	@Override
	public void setDuration(long duration) {
		_duration = duration;
	}

	@Override
	public String getWorkflowContext() {
		if (_workflowContext == null) {
			return "";
		}
		else {
			return _workflowContext;
		}
	}

	@Override
	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), KaleoLog.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoLog toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (KaleoLog)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoLogImpl kaleoLogImpl = new KaleoLogImpl();

		kaleoLogImpl.setMvccVersion(getMvccVersion());
		kaleoLogImpl.setKaleoLogId(getKaleoLogId());
		kaleoLogImpl.setGroupId(getGroupId());
		kaleoLogImpl.setCompanyId(getCompanyId());
		kaleoLogImpl.setUserId(getUserId());
		kaleoLogImpl.setUserName(getUserName());
		kaleoLogImpl.setCreateDate(getCreateDate());
		kaleoLogImpl.setModifiedDate(getModifiedDate());
		kaleoLogImpl.setKaleoClassName(getKaleoClassName());
		kaleoLogImpl.setKaleoClassPK(getKaleoClassPK());
		kaleoLogImpl.setKaleoDefinitionVersionId(getKaleoDefinitionVersionId());
		kaleoLogImpl.setKaleoInstanceId(getKaleoInstanceId());
		kaleoLogImpl.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		kaleoLogImpl.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		kaleoLogImpl.setKaleoNodeName(getKaleoNodeName());
		kaleoLogImpl.setTerminalKaleoNode(isTerminalKaleoNode());
		kaleoLogImpl.setKaleoActionId(getKaleoActionId());
		kaleoLogImpl.setKaleoActionName(getKaleoActionName());
		kaleoLogImpl.setKaleoActionDescription(getKaleoActionDescription());
		kaleoLogImpl.setPreviousKaleoNodeId(getPreviousKaleoNodeId());
		kaleoLogImpl.setPreviousKaleoNodeName(getPreviousKaleoNodeName());
		kaleoLogImpl.setPreviousAssigneeClassName(
			getPreviousAssigneeClassName());
		kaleoLogImpl.setPreviousAssigneeClassPK(getPreviousAssigneeClassPK());
		kaleoLogImpl.setCurrentAssigneeClassName(getCurrentAssigneeClassName());
		kaleoLogImpl.setCurrentAssigneeClassPK(getCurrentAssigneeClassPK());
		kaleoLogImpl.setType(getType());
		kaleoLogImpl.setComment(getComment());
		kaleoLogImpl.setStartDate(getStartDate());
		kaleoLogImpl.setEndDate(getEndDate());
		kaleoLogImpl.setDuration(getDuration());
		kaleoLogImpl.setWorkflowContext(getWorkflowContext());

		kaleoLogImpl.resetOriginalValues();

		return kaleoLogImpl;
	}

	@Override
	public int compareTo(KaleoLog kaleoLog) {
		int value = 0;

		if (getKaleoLogId() < kaleoLog.getKaleoLogId()) {
			value = -1;
		}
		else if (getKaleoLogId() > kaleoLog.getKaleoLogId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoLog)) {
			return false;
		}

		KaleoLog kaleoLog = (KaleoLog)obj;

		long primaryKey = kaleoLog.getPrimaryKey();

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
		KaleoLogModelImpl kaleoLogModelImpl = this;

		kaleoLogModelImpl._originalCompanyId = kaleoLogModelImpl._companyId;

		kaleoLogModelImpl._setOriginalCompanyId = false;

		kaleoLogModelImpl._setModifiedDate = false;

		kaleoLogModelImpl._originalKaleoClassName =
			kaleoLogModelImpl._kaleoClassName;

		kaleoLogModelImpl._originalKaleoClassPK =
			kaleoLogModelImpl._kaleoClassPK;

		kaleoLogModelImpl._setOriginalKaleoClassPK = false;

		kaleoLogModelImpl._originalKaleoDefinitionVersionId =
			kaleoLogModelImpl._kaleoDefinitionVersionId;

		kaleoLogModelImpl._setOriginalKaleoDefinitionVersionId = false;

		kaleoLogModelImpl._originalKaleoInstanceId =
			kaleoLogModelImpl._kaleoInstanceId;

		kaleoLogModelImpl._setOriginalKaleoInstanceId = false;

		kaleoLogModelImpl._originalKaleoInstanceTokenId =
			kaleoLogModelImpl._kaleoInstanceTokenId;

		kaleoLogModelImpl._setOriginalKaleoInstanceTokenId = false;

		kaleoLogModelImpl._originalKaleoTaskInstanceTokenId =
			kaleoLogModelImpl._kaleoTaskInstanceTokenId;

		kaleoLogModelImpl._setOriginalKaleoTaskInstanceTokenId = false;

		kaleoLogModelImpl._originalType = kaleoLogModelImpl._type;

		kaleoLogModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoLog> toCacheModel() {
		KaleoLogCacheModel kaleoLogCacheModel = new KaleoLogCacheModel();

		kaleoLogCacheModel.mvccVersion = getMvccVersion();

		kaleoLogCacheModel.kaleoLogId = getKaleoLogId();

		kaleoLogCacheModel.groupId = getGroupId();

		kaleoLogCacheModel.companyId = getCompanyId();

		kaleoLogCacheModel.userId = getUserId();

		kaleoLogCacheModel.userName = getUserName();

		String userName = kaleoLogCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoLogCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoLogCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoLogCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoLogCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoLogCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoLogCacheModel.kaleoClassName = getKaleoClassName();

		String kaleoClassName = kaleoLogCacheModel.kaleoClassName;

		if ((kaleoClassName != null) && (kaleoClassName.length() == 0)) {
			kaleoLogCacheModel.kaleoClassName = null;
		}

		kaleoLogCacheModel.kaleoClassPK = getKaleoClassPK();

		kaleoLogCacheModel.kaleoDefinitionVersionId =
			getKaleoDefinitionVersionId();

		kaleoLogCacheModel.kaleoInstanceId = getKaleoInstanceId();

		kaleoLogCacheModel.kaleoInstanceTokenId = getKaleoInstanceTokenId();

		kaleoLogCacheModel.kaleoTaskInstanceTokenId =
			getKaleoTaskInstanceTokenId();

		kaleoLogCacheModel.kaleoNodeName = getKaleoNodeName();

		String kaleoNodeName = kaleoLogCacheModel.kaleoNodeName;

		if ((kaleoNodeName != null) && (kaleoNodeName.length() == 0)) {
			kaleoLogCacheModel.kaleoNodeName = null;
		}

		kaleoLogCacheModel.terminalKaleoNode = isTerminalKaleoNode();

		kaleoLogCacheModel.kaleoActionId = getKaleoActionId();

		kaleoLogCacheModel.kaleoActionName = getKaleoActionName();

		String kaleoActionName = kaleoLogCacheModel.kaleoActionName;

		if ((kaleoActionName != null) && (kaleoActionName.length() == 0)) {
			kaleoLogCacheModel.kaleoActionName = null;
		}

		kaleoLogCacheModel.kaleoActionDescription = getKaleoActionDescription();

		String kaleoActionDescription =
			kaleoLogCacheModel.kaleoActionDescription;

		if ((kaleoActionDescription != null) &&
			(kaleoActionDescription.length() == 0)) {

			kaleoLogCacheModel.kaleoActionDescription = null;
		}

		kaleoLogCacheModel.previousKaleoNodeId = getPreviousKaleoNodeId();

		kaleoLogCacheModel.previousKaleoNodeName = getPreviousKaleoNodeName();

		String previousKaleoNodeName = kaleoLogCacheModel.previousKaleoNodeName;

		if ((previousKaleoNodeName != null) &&
			(previousKaleoNodeName.length() == 0)) {

			kaleoLogCacheModel.previousKaleoNodeName = null;
		}

		kaleoLogCacheModel.previousAssigneeClassName =
			getPreviousAssigneeClassName();

		String previousAssigneeClassName =
			kaleoLogCacheModel.previousAssigneeClassName;

		if ((previousAssigneeClassName != null) &&
			(previousAssigneeClassName.length() == 0)) {

			kaleoLogCacheModel.previousAssigneeClassName = null;
		}

		kaleoLogCacheModel.previousAssigneeClassPK =
			getPreviousAssigneeClassPK();

		kaleoLogCacheModel.currentAssigneeClassName =
			getCurrentAssigneeClassName();

		String currentAssigneeClassName =
			kaleoLogCacheModel.currentAssigneeClassName;

		if ((currentAssigneeClassName != null) &&
			(currentAssigneeClassName.length() == 0)) {

			kaleoLogCacheModel.currentAssigneeClassName = null;
		}

		kaleoLogCacheModel.currentAssigneeClassPK = getCurrentAssigneeClassPK();

		kaleoLogCacheModel.type = getType();

		String type = kaleoLogCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			kaleoLogCacheModel.type = null;
		}

		kaleoLogCacheModel.comment = getComment();

		String comment = kaleoLogCacheModel.comment;

		if ((comment != null) && (comment.length() == 0)) {
			kaleoLogCacheModel.comment = null;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			kaleoLogCacheModel.startDate = startDate.getTime();
		}
		else {
			kaleoLogCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			kaleoLogCacheModel.endDate = endDate.getTime();
		}
		else {
			kaleoLogCacheModel.endDate = Long.MIN_VALUE;
		}

		kaleoLogCacheModel.duration = getDuration();

		kaleoLogCacheModel.workflowContext = getWorkflowContext();

		String workflowContext = kaleoLogCacheModel.workflowContext;

		if ((workflowContext != null) && (workflowContext.length() == 0)) {
			kaleoLogCacheModel.workflowContext = null;
		}

		return kaleoLogCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoLog, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((KaleoLog)this));
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
		Map<String, Function<KaleoLog, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoLog, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoLog, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((KaleoLog)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		KaleoLog.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		KaleoLog.class, ModelWrapper.class
	};

	private long _mvccVersion;
	private long _kaleoLogId;
	private long _groupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _kaleoClassName;
	private String _originalKaleoClassName;
	private long _kaleoClassPK;
	private long _originalKaleoClassPK;
	private boolean _setOriginalKaleoClassPK;
	private long _kaleoDefinitionVersionId;
	private long _originalKaleoDefinitionVersionId;
	private boolean _setOriginalKaleoDefinitionVersionId;
	private long _kaleoInstanceId;
	private long _originalKaleoInstanceId;
	private boolean _setOriginalKaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _originalKaleoInstanceTokenId;
	private boolean _setOriginalKaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private long _originalKaleoTaskInstanceTokenId;
	private boolean _setOriginalKaleoTaskInstanceTokenId;
	private String _kaleoNodeName;
	private boolean _terminalKaleoNode;
	private long _kaleoActionId;
	private String _kaleoActionName;
	private String _kaleoActionDescription;
	private long _previousKaleoNodeId;
	private String _previousKaleoNodeName;
	private String _previousAssigneeClassName;
	private long _previousAssigneeClassPK;
	private String _currentAssigneeClassName;
	private long _currentAssigneeClassPK;
	private String _type;
	private String _originalType;
	private String _comment;
	private Date _startDate;
	private Date _endDate;
	private long _duration;
	private String _workflowContext;
	private long _columnBitmask;
	private KaleoLog _escapedModel;

}