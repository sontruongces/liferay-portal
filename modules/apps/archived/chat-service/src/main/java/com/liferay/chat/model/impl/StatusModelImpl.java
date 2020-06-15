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

package com.liferay.chat.model.impl;

import com.liferay.chat.model.Status;
import com.liferay.chat.model.StatusModel;
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
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Status service. Represents a row in the &quot;Chat_Status&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>StatusModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link StatusImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StatusImpl
 * @generated
 */
public class StatusModelImpl
	extends BaseModelImpl<Status> implements StatusModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a status model instance should use the <code>Status</code> interface instead.
	 */
	public static final String TABLE_NAME = "Chat_Status";

	public static final Object[][] TABLE_COLUMNS = {
		{"statusId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"modifiedDate", Types.BIGINT}, {"online_", Types.BOOLEAN},
		{"awake", Types.BOOLEAN}, {"activePanelIds", Types.VARCHAR},
		{"message", Types.VARCHAR}, {"playSound", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("statusId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("online_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("awake", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("activePanelIds", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("message", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("playSound", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Chat_Status (statusId LONG not null primary key,userId LONG,modifiedDate LONG,online_ BOOLEAN,awake BOOLEAN,activePanelIds STRING null,message STRING null,playSound BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table Chat_Status";

	public static final String ORDER_BY_JPQL = " ORDER BY status.statusId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Chat_Status.statusId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 1L;

	public static final long ONLINE_COLUMN_BITMASK = 2L;

	public static final long USERID_COLUMN_BITMASK = 4L;

	public static final long STATUSID_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public StatusModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _statusId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStatusId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _statusId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Status.class;
	}

	@Override
	public String getModelClassName() {
		return Status.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Status, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Status, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Status, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Status)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Status, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Status, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Status)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Status, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Status, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Status>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Status.class.getClassLoader(), Status.class, ModelWrapper.class);

		try {
			Constructor<Status> constructor =
				(Constructor<Status>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new InternalError(reflectiveOperationException);
				}
			};
		}
		catch (NoSuchMethodException noSuchMethodException) {
			throw new InternalError(noSuchMethodException);
		}
	}

	private static final Map<String, Function<Status, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Status, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Status, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Status, Object>>();
		Map<String, BiConsumer<Status, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Status, ?>>();

		attributeGetterFunctions.put("statusId", Status::getStatusId);
		attributeSetterBiConsumers.put(
			"statusId", (BiConsumer<Status, Long>)Status::setStatusId);
		attributeGetterFunctions.put("userId", Status::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Status, Long>)Status::setUserId);
		attributeGetterFunctions.put("modifiedDate", Status::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Status, Long>)Status::setModifiedDate);
		attributeGetterFunctions.put("online", Status::getOnline);
		attributeSetterBiConsumers.put(
			"online", (BiConsumer<Status, Boolean>)Status::setOnline);
		attributeGetterFunctions.put("awake", Status::getAwake);
		attributeSetterBiConsumers.put(
			"awake", (BiConsumer<Status, Boolean>)Status::setAwake);
		attributeGetterFunctions.put(
			"activePanelIds", Status::getActivePanelIds);
		attributeSetterBiConsumers.put(
			"activePanelIds",
			(BiConsumer<Status, String>)Status::setActivePanelIds);
		attributeGetterFunctions.put("message", Status::getMessage);
		attributeSetterBiConsumers.put(
			"message", (BiConsumer<Status, String>)Status::setMessage);
		attributeGetterFunctions.put("playSound", Status::getPlaySound);
		attributeSetterBiConsumers.put(
			"playSound", (BiConsumer<Status, Boolean>)Status::setPlaySound);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(long statusId) {
		_statusId = statusId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@Override
	public long getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(long modifiedDate) {
		_columnBitmask |= MODIFIEDDATE_COLUMN_BITMASK;

		if (!_setOriginalModifiedDate) {
			_setOriginalModifiedDate = true;

			_originalModifiedDate = _modifiedDate;
		}

		_modifiedDate = modifiedDate;
	}

	public long getOriginalModifiedDate() {
		return _originalModifiedDate;
	}

	@Override
	public boolean getOnline() {
		return _online;
	}

	@Override
	public boolean isOnline() {
		return _online;
	}

	@Override
	public void setOnline(boolean online) {
		_columnBitmask |= ONLINE_COLUMN_BITMASK;

		if (!_setOriginalOnline) {
			_setOriginalOnline = true;

			_originalOnline = _online;
		}

		_online = online;
	}

	public boolean getOriginalOnline() {
		return _originalOnline;
	}

	@Override
	public boolean getAwake() {
		return _awake;
	}

	@Override
	public boolean isAwake() {
		return _awake;
	}

	@Override
	public void setAwake(boolean awake) {
		_awake = awake;
	}

	@Override
	public String getActivePanelIds() {
		if (_activePanelIds == null) {
			return "";
		}
		else {
			return _activePanelIds;
		}
	}

	@Override
	public void setActivePanelIds(String activePanelIds) {
		_activePanelIds = activePanelIds;
	}

	@Override
	public String getMessage() {
		if (_message == null) {
			return "";
		}
		else {
			return _message;
		}
	}

	@Override
	public void setMessage(String message) {
		_message = message;
	}

	@Override
	public boolean getPlaySound() {
		return _playSound;
	}

	@Override
	public boolean isPlaySound() {
		return _playSound;
	}

	@Override
	public void setPlaySound(boolean playSound) {
		_playSound = playSound;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Status.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Status toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Status>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		StatusImpl statusImpl = new StatusImpl();

		statusImpl.setStatusId(getStatusId());
		statusImpl.setUserId(getUserId());
		statusImpl.setModifiedDate(getModifiedDate());
		statusImpl.setOnline(isOnline());
		statusImpl.setAwake(isAwake());
		statusImpl.setActivePanelIds(getActivePanelIds());
		statusImpl.setMessage(getMessage());
		statusImpl.setPlaySound(isPlaySound());

		statusImpl.resetOriginalValues();

		return statusImpl;
	}

	@Override
	public int compareTo(Status status) {
		long primaryKey = status.getPrimaryKey();

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
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Status)) {
			return false;
		}

		Status status = (Status)object;

		long primaryKey = status.getPrimaryKey();

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
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		StatusModelImpl statusModelImpl = this;

		statusModelImpl._originalUserId = statusModelImpl._userId;

		statusModelImpl._setOriginalUserId = false;

		statusModelImpl._originalModifiedDate = statusModelImpl._modifiedDate;

		statusModelImpl._setOriginalModifiedDate = false;

		statusModelImpl._originalOnline = statusModelImpl._online;

		statusModelImpl._setOriginalOnline = false;

		statusModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Status> toCacheModel() {
		StatusCacheModel statusCacheModel = new StatusCacheModel();

		statusCacheModel.statusId = getStatusId();

		statusCacheModel.userId = getUserId();

		statusCacheModel.modifiedDate = getModifiedDate();

		statusCacheModel.online = isOnline();

		statusCacheModel.awake = isAwake();

		statusCacheModel.activePanelIds = getActivePanelIds();

		String activePanelIds = statusCacheModel.activePanelIds;

		if ((activePanelIds != null) && (activePanelIds.length() == 0)) {
			statusCacheModel.activePanelIds = null;
		}

		statusCacheModel.message = getMessage();

		String message = statusCacheModel.message;

		if ((message != null) && (message.length() == 0)) {
			statusCacheModel.message = null;
		}

		statusCacheModel.playSound = isPlaySound();

		return statusCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Status, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Status, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Status, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Status)this));
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
		Map<String, Function<Status, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Status, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Status, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Status)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Status>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _statusId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _modifiedDate;
	private long _originalModifiedDate;
	private boolean _setOriginalModifiedDate;
	private boolean _online;
	private boolean _originalOnline;
	private boolean _setOriginalOnline;
	private boolean _awake;
	private String _activePanelIds;
	private String _message;
	private boolean _playSound;
	private long _columnBitmask;
	private Status _escapedModel;

}