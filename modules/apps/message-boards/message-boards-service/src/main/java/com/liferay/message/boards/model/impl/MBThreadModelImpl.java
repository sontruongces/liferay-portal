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

package com.liferay.message.boards.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.message.boards.model.MBThread;
import com.liferay.message.boards.model.MBThreadModel;
import com.liferay.message.boards.model.MBThreadSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the MBThread service. Represents a row in the &quot;MBThread&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>MBThreadModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MBThreadImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBThreadImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class MBThreadModelImpl
	extends BaseModelImpl<MBThread> implements MBThreadModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a message boards thread model instance should use the <code>MBThread</code> interface instead.
	 */
	public static final String TABLE_NAME = "MBThread";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"threadId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"categoryId", Types.BIGINT}, {"rootMessageId", Types.BIGINT},
		{"rootMessageUserId", Types.BIGINT}, {"title", Types.VARCHAR},
		{"messageCount", Types.INTEGER}, {"viewCount", Types.INTEGER},
		{"lastPostByUserId", Types.BIGINT}, {"lastPostDate", Types.TIMESTAMP},
		{"priority", Types.DOUBLE}, {"question", Types.BOOLEAN},
		{"lastPublishDate", Types.TIMESTAMP}, {"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT}, {"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("threadId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("categoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("rootMessageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("rootMessageUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("messageCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("viewCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("lastPostByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPostDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("question", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table MBThread (uuid_ VARCHAR(75) null,threadId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,categoryId LONG,rootMessageId LONG,rootMessageUserId LONG,title VARCHAR(75) null,messageCount INTEGER,viewCount INTEGER,lastPostByUserId LONG,lastPostDate DATE null,priority DOUBLE,question BOOLEAN,lastPublishDate DATE null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table MBThread";

	public static final String ORDER_BY_JPQL =
		" ORDER BY mbThread.priority DESC, mbThread.lastPostDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY MBThread.priority DESC, MBThread.lastPostDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.message.boards.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.message.boards.model.MBThread"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.message.boards.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.message.boards.model.MBThread"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.message.boards.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.message.boards.model.MBThread"),
		true);

	public static final long CATEGORYID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long LASTPOSTDATE_COLUMN_BITMASK = 8L;

	public static final long PRIORITY_COLUMN_BITMASK = 16L;

	public static final long ROOTMESSAGEID_COLUMN_BITMASK = 32L;

	public static final long STATUS_COLUMN_BITMASK = 64L;

	public static final long UUID_COLUMN_BITMASK = 128L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static MBThread toModel(MBThreadSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		MBThread model = new MBThreadImpl();

		model.setUuid(soapModel.getUuid());
		model.setThreadId(soapModel.getThreadId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCategoryId(soapModel.getCategoryId());
		model.setRootMessageId(soapModel.getRootMessageId());
		model.setRootMessageUserId(soapModel.getRootMessageUserId());
		model.setTitle(soapModel.getTitle());
		model.setMessageCount(soapModel.getMessageCount());
		model.setViewCount(soapModel.getViewCount());
		model.setLastPostByUserId(soapModel.getLastPostByUserId());
		model.setLastPostDate(soapModel.getLastPostDate());
		model.setPriority(soapModel.getPriority());
		model.setQuestion(soapModel.isQuestion());
		model.setLastPublishDate(soapModel.getLastPublishDate());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<MBThread> toModels(MBThreadSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<MBThread> models = new ArrayList<MBThread>(soapModels.length);

		for (MBThreadSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.message.boards.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.message.boards.model.MBThread"));

	public MBThreadModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _threadId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setThreadId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _threadId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return MBThread.class;
	}

	@Override
	public String getModelClassName() {
		return MBThread.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<MBThread, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<MBThread, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MBThread, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((MBThread)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<MBThread, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<MBThread, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(MBThread)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<MBThread, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<MBThread, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<MBThread, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<MBThread, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<MBThread, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<MBThread, Object>>();
		Map<String, BiConsumer<MBThread, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<MBThread, ?>>();

		attributeGetterFunctions.put("uuid", MBThread::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<MBThread, String>)MBThread::setUuid);
		attributeGetterFunctions.put("threadId", MBThread::getThreadId);
		attributeSetterBiConsumers.put(
			"threadId", (BiConsumer<MBThread, Long>)MBThread::setThreadId);
		attributeGetterFunctions.put("groupId", MBThread::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<MBThread, Long>)MBThread::setGroupId);
		attributeGetterFunctions.put("companyId", MBThread::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<MBThread, Long>)MBThread::setCompanyId);
		attributeGetterFunctions.put("userId", MBThread::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<MBThread, Long>)MBThread::setUserId);
		attributeGetterFunctions.put("userName", MBThread::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<MBThread, String>)MBThread::setUserName);
		attributeGetterFunctions.put("createDate", MBThread::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<MBThread, Date>)MBThread::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", MBThread::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<MBThread, Date>)MBThread::setModifiedDate);
		attributeGetterFunctions.put("categoryId", MBThread::getCategoryId);
		attributeSetterBiConsumers.put(
			"categoryId", (BiConsumer<MBThread, Long>)MBThread::setCategoryId);
		attributeGetterFunctions.put(
			"rootMessageId", MBThread::getRootMessageId);
		attributeSetterBiConsumers.put(
			"rootMessageId",
			(BiConsumer<MBThread, Long>)MBThread::setRootMessageId);
		attributeGetterFunctions.put(
			"rootMessageUserId", MBThread::getRootMessageUserId);
		attributeSetterBiConsumers.put(
			"rootMessageUserId",
			(BiConsumer<MBThread, Long>)MBThread::setRootMessageUserId);
		attributeGetterFunctions.put("title", MBThread::getTitle);
		attributeSetterBiConsumers.put(
			"title", (BiConsumer<MBThread, String>)MBThread::setTitle);
		attributeGetterFunctions.put("messageCount", MBThread::getMessageCount);
		attributeSetterBiConsumers.put(
			"messageCount",
			(BiConsumer<MBThread, Integer>)MBThread::setMessageCount);
		attributeGetterFunctions.put("viewCount", MBThread::getViewCount);
		attributeSetterBiConsumers.put(
			"viewCount", (BiConsumer<MBThread, Integer>)MBThread::setViewCount);
		attributeGetterFunctions.put(
			"lastPostByUserId", MBThread::getLastPostByUserId);
		attributeSetterBiConsumers.put(
			"lastPostByUserId",
			(BiConsumer<MBThread, Long>)MBThread::setLastPostByUserId);
		attributeGetterFunctions.put("lastPostDate", MBThread::getLastPostDate);
		attributeSetterBiConsumers.put(
			"lastPostDate",
			(BiConsumer<MBThread, Date>)MBThread::setLastPostDate);
		attributeGetterFunctions.put("priority", MBThread::getPriority);
		attributeSetterBiConsumers.put(
			"priority", (BiConsumer<MBThread, Double>)MBThread::setPriority);
		attributeGetterFunctions.put("question", MBThread::getQuestion);
		attributeSetterBiConsumers.put(
			"question", (BiConsumer<MBThread, Boolean>)MBThread::setQuestion);
		attributeGetterFunctions.put(
			"lastPublishDate", MBThread::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<MBThread, Date>)MBThread::setLastPublishDate);
		attributeGetterFunctions.put("status", MBThread::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<MBThread, Integer>)MBThread::setStatus);
		attributeGetterFunctions.put(
			"statusByUserId", MBThread::getStatusByUserId);
		attributeSetterBiConsumers.put(
			"statusByUserId",
			(BiConsumer<MBThread, Long>)MBThread::setStatusByUserId);
		attributeGetterFunctions.put(
			"statusByUserName", MBThread::getStatusByUserName);
		attributeSetterBiConsumers.put(
			"statusByUserName",
			(BiConsumer<MBThread, String>)MBThread::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", MBThread::getStatusDate);
		attributeSetterBiConsumers.put(
			"statusDate", (BiConsumer<MBThread, Date>)MBThread::setStatusDate);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
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

	@JSON
	@Override
	public long getThreadId() {
		return _threadId;
	}

	@Override
	public void setThreadId(long threadId) {
		_threadId = threadId;
	}

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
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

	@JSON
	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_columnBitmask |= CATEGORYID_COLUMN_BITMASK;

		if (!_setOriginalCategoryId) {
			_setOriginalCategoryId = true;

			_originalCategoryId = _categoryId;
		}

		_categoryId = categoryId;
	}

	public long getOriginalCategoryId() {
		return _originalCategoryId;
	}

	@JSON
	@Override
	public long getRootMessageId() {
		return _rootMessageId;
	}

	@Override
	public void setRootMessageId(long rootMessageId) {
		_columnBitmask |= ROOTMESSAGEID_COLUMN_BITMASK;

		if (!_setOriginalRootMessageId) {
			_setOriginalRootMessageId = true;

			_originalRootMessageId = _rootMessageId;
		}

		_rootMessageId = rootMessageId;
	}

	public long getOriginalRootMessageId() {
		return _originalRootMessageId;
	}

	@JSON
	@Override
	public long getRootMessageUserId() {
		return _rootMessageUserId;
	}

	@Override
	public void setRootMessageUserId(long rootMessageUserId) {
		_rootMessageUserId = rootMessageUserId;
	}

	@Override
	public String getRootMessageUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(
				getRootMessageUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setRootMessageUserUuid(String rootMessageUserUuid) {
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@JSON
	@Override
	public int getMessageCount() {
		return _messageCount;
	}

	@Override
	public void setMessageCount(int messageCount) {
		_messageCount = messageCount;
	}

	@JSON
	@Override
	public int getViewCount() {
		return _viewCount;
	}

	@Override
	public void setViewCount(int viewCount) {
		_viewCount = viewCount;
	}

	@JSON
	@Override
	public long getLastPostByUserId() {
		return _lastPostByUserId;
	}

	@Override
	public void setLastPostByUserId(long lastPostByUserId) {
		_lastPostByUserId = lastPostByUserId;
	}

	@Override
	public String getLastPostByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getLastPostByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setLastPostByUserUuid(String lastPostByUserUuid) {
	}

	@JSON
	@Override
	public Date getLastPostDate() {
		return _lastPostDate;
	}

	@Override
	public void setLastPostDate(Date lastPostDate) {
		_columnBitmask = -1L;

		if (_originalLastPostDate == null) {
			_originalLastPostDate = _lastPostDate;
		}

		_lastPostDate = lastPostDate;
	}

	public Date getOriginalLastPostDate() {
		return _originalLastPostDate;
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		_columnBitmask = -1L;

		if (!_setOriginalPriority) {
			_setOriginalPriority = true;

			_originalPriority = _priority;
		}

		_priority = priority;
	}

	public double getOriginalPriority() {
		return _originalPriority;
	}

	@JSON
	@Override
	public boolean getQuestion() {
		return _question;
	}

	@JSON
	@Override
	public boolean isQuestion() {
		return _question;
	}

	@Override
	public void setQuestion(boolean question) {
		_question = question;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@Override
	public long getContainerModelId() {
		return getThreadId();
	}

	@Override
	public void setContainerModelId(long containerModelId) {
		_threadId = containerModelId;
	}

	@Override
	public long getParentContainerModelId() {
		return getCategoryId();
	}

	@Override
	public void setParentContainerModelId(long parentContainerModelId) {
		_categoryId = parentContainerModelId;
	}

	@Override
	public String getContainerModelName() {
		return String.valueOf(getTitle());
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(MBThread.class.getName()));
	}

	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException {

		if (!isInTrash()) {
			return null;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry =
			com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.
				fetchEntry(getModelClassName(), getTrashEntryClassPK());

		if (trashEntry != null) {
			return trashEntry;
		}

		com.liferay.portal.kernel.trash.TrashHandler trashHandler =
			getTrashHandler();

		if (Validator.isNotNull(
				trashHandler.getContainerModelClassName(getPrimaryKey()))) {

			ContainerModel containerModel = null;

			try {
				containerModel = trashHandler.getParentContainerModel(this);
			}
			catch (NoSuchModelException nsme) {
				return null;
			}

			while (containerModel != null) {
				if (containerModel instanceof TrashedModel) {
					TrashedModel trashedModel = (TrashedModel)containerModel;

					return trashedModel.getTrashEntry();
				}

				trashHandler =
					com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil.
						getTrashHandler(
							trashHandler.getContainerModelClassName(
								containerModel.getContainerModelId()));

				if (trashHandler == null) {
					return null;
				}

				containerModel = trashHandler.getContainerModel(
					containerModel.getParentContainerModelId());
			}
		}

		return null;
	}

	@Override
	public long getTrashEntryClassPK() {
		return getPrimaryKey();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil.
			getTrashHandler(getModelClassName());
	}

	@Override
	public boolean isInTrash() {
		if (getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInTrashContainer() {
		com.liferay.portal.kernel.trash.TrashHandler trashHandler =
			getTrashHandler();

		if ((trashHandler == null) ||
			Validator.isNull(
				trashHandler.getContainerModelClassName(getPrimaryKey()))) {

			return false;
		}

		try {
			ContainerModel containerModel =
				trashHandler.getParentContainerModel(this);

			if (containerModel == null) {
				return false;
			}

			if (containerModel instanceof TrashedModel) {
				return ((TrashedModel)containerModel).isInTrash();
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isInTrashExplicitly() {
		if (!isInTrash()) {
			return false;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry =
			com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.
				fetchEntry(getModelClassName(), getTrashEntryClassPK());

		if (trashEntry != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isInTrashImplicitly() {
		if (!isInTrash()) {
			return false;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry =
			com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.
				fetchEntry(getModelClassName(), getTrashEntryClassPK());

		if (trashEntry != null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), MBThread.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public MBThread toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (MBThread)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MBThreadImpl mbThreadImpl = new MBThreadImpl();

		mbThreadImpl.setUuid(getUuid());
		mbThreadImpl.setThreadId(getThreadId());
		mbThreadImpl.setGroupId(getGroupId());
		mbThreadImpl.setCompanyId(getCompanyId());
		mbThreadImpl.setUserId(getUserId());
		mbThreadImpl.setUserName(getUserName());
		mbThreadImpl.setCreateDate(getCreateDate());
		mbThreadImpl.setModifiedDate(getModifiedDate());
		mbThreadImpl.setCategoryId(getCategoryId());
		mbThreadImpl.setRootMessageId(getRootMessageId());
		mbThreadImpl.setRootMessageUserId(getRootMessageUserId());
		mbThreadImpl.setTitle(getTitle());
		mbThreadImpl.setMessageCount(getMessageCount());
		mbThreadImpl.setViewCount(getViewCount());
		mbThreadImpl.setLastPostByUserId(getLastPostByUserId());
		mbThreadImpl.setLastPostDate(getLastPostDate());
		mbThreadImpl.setPriority(getPriority());
		mbThreadImpl.setQuestion(isQuestion());
		mbThreadImpl.setLastPublishDate(getLastPublishDate());
		mbThreadImpl.setStatus(getStatus());
		mbThreadImpl.setStatusByUserId(getStatusByUserId());
		mbThreadImpl.setStatusByUserName(getStatusByUserName());
		mbThreadImpl.setStatusDate(getStatusDate());

		mbThreadImpl.resetOriginalValues();

		return mbThreadImpl;
	}

	@Override
	public int compareTo(MBThread mbThread) {
		int value = 0;

		if (getPriority() < mbThread.getPriority()) {
			value = -1;
		}
		else if (getPriority() > mbThread.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		value = value * -1;

		if (value != 0) {
			return value;
		}

		value = DateUtil.compareTo(
			getLastPostDate(), mbThread.getLastPostDate());

		value = value * -1;

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

		if (!(obj instanceof MBThread)) {
			return false;
		}

		MBThread mbThread = (MBThread)obj;

		long primaryKey = mbThread.getPrimaryKey();

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
		MBThreadModelImpl mbThreadModelImpl = this;

		mbThreadModelImpl._originalUuid = mbThreadModelImpl._uuid;

		mbThreadModelImpl._originalGroupId = mbThreadModelImpl._groupId;

		mbThreadModelImpl._setOriginalGroupId = false;

		mbThreadModelImpl._originalCompanyId = mbThreadModelImpl._companyId;

		mbThreadModelImpl._setOriginalCompanyId = false;

		mbThreadModelImpl._setModifiedDate = false;

		mbThreadModelImpl._originalCategoryId = mbThreadModelImpl._categoryId;

		mbThreadModelImpl._setOriginalCategoryId = false;

		mbThreadModelImpl._originalRootMessageId =
			mbThreadModelImpl._rootMessageId;

		mbThreadModelImpl._setOriginalRootMessageId = false;

		mbThreadModelImpl._originalLastPostDate =
			mbThreadModelImpl._lastPostDate;

		mbThreadModelImpl._originalPriority = mbThreadModelImpl._priority;

		mbThreadModelImpl._setOriginalPriority = false;

		mbThreadModelImpl._originalStatus = mbThreadModelImpl._status;

		mbThreadModelImpl._setOriginalStatus = false;

		mbThreadModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<MBThread> toCacheModel() {
		MBThreadCacheModel mbThreadCacheModel = new MBThreadCacheModel();

		mbThreadCacheModel.uuid = getUuid();

		String uuid = mbThreadCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			mbThreadCacheModel.uuid = null;
		}

		mbThreadCacheModel.threadId = getThreadId();

		mbThreadCacheModel.groupId = getGroupId();

		mbThreadCacheModel.companyId = getCompanyId();

		mbThreadCacheModel.userId = getUserId();

		mbThreadCacheModel.userName = getUserName();

		String userName = mbThreadCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			mbThreadCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			mbThreadCacheModel.createDate = createDate.getTime();
		}
		else {
			mbThreadCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			mbThreadCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			mbThreadCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		mbThreadCacheModel.categoryId = getCategoryId();

		mbThreadCacheModel.rootMessageId = getRootMessageId();

		mbThreadCacheModel.rootMessageUserId = getRootMessageUserId();

		mbThreadCacheModel.title = getTitle();

		String title = mbThreadCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			mbThreadCacheModel.title = null;
		}

		mbThreadCacheModel.messageCount = getMessageCount();

		mbThreadCacheModel.viewCount = getViewCount();

		mbThreadCacheModel.lastPostByUserId = getLastPostByUserId();

		Date lastPostDate = getLastPostDate();

		if (lastPostDate != null) {
			mbThreadCacheModel.lastPostDate = lastPostDate.getTime();
		}
		else {
			mbThreadCacheModel.lastPostDate = Long.MIN_VALUE;
		}

		mbThreadCacheModel.priority = getPriority();

		mbThreadCacheModel.question = isQuestion();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			mbThreadCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			mbThreadCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		mbThreadCacheModel.status = getStatus();

		mbThreadCacheModel.statusByUserId = getStatusByUserId();

		mbThreadCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = mbThreadCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			mbThreadCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			mbThreadCacheModel.statusDate = statusDate.getTime();
		}
		else {
			mbThreadCacheModel.statusDate = Long.MIN_VALUE;
		}

		return mbThreadCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<MBThread, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<MBThread, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MBThread, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((MBThread)this));
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
		Map<String, Function<MBThread, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<MBThread, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<MBThread, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((MBThread)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		MBThread.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		MBThread.class, ModelWrapper.class
	};

	private String _uuid;
	private String _originalUuid;
	private long _threadId;
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
	private long _categoryId;
	private long _originalCategoryId;
	private boolean _setOriginalCategoryId;
	private long _rootMessageId;
	private long _originalRootMessageId;
	private boolean _setOriginalRootMessageId;
	private long _rootMessageUserId;
	private String _title;
	private int _messageCount;
	private int _viewCount;
	private long _lastPostByUserId;
	private Date _lastPostDate;
	private Date _originalLastPostDate;
	private double _priority;
	private double _originalPriority;
	private boolean _setOriginalPriority;
	private boolean _question;
	private Date _lastPublishDate;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _columnBitmask;
	private MBThread _escapedModel;

}