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

package com.liferay.portal.security.sso.openid.connect.persistence.model.impl;

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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.sso.openid.connect.persistence.model.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.persistence.model.OpenIdConnectSessionModel;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the OpenIdConnectSession service. Represents a row in the &quot;OpenIdConnectSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>OpenIdConnectSessionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OpenIdConnectSessionImpl}.
 * </p>
 *
 * @author Arthur Chan
 * @see OpenIdConnectSessionImpl
 * @generated
 */
public class OpenIdConnectSessionModelImpl
	extends BaseModelImpl<OpenIdConnectSession>
	implements OpenIdConnectSessionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a open ID connect session model instance should use the <code>OpenIdConnectSession</code> interface instead.
	 */
	public static final String TABLE_NAME = "OpenIdConnectSession";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"openIdConnectSessionId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"modifiedDate", Types.TIMESTAMP},
		{"userId", Types.BIGINT}, {"accessToken", Types.VARCHAR},
		{"configurationPid", Types.VARCHAR}, {"idToken", Types.VARCHAR},
		{"providerName", Types.VARCHAR}, {"refreshToken", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("openIdConnectSessionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("accessToken", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("configurationPid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("idToken", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("providerName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("refreshToken", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table OpenIdConnectSession (mvccVersion LONG default 0 not null,openIdConnectSessionId LONG not null primary key,companyId LONG,modifiedDate DATE null,userId LONG,accessToken VARCHAR(3000) null,configurationPid VARCHAR(256) null,idToken VARCHAR(3999) null,providerName VARCHAR(75) null,refreshToken VARCHAR(2000) null)";

	public static final String TABLE_SQL_DROP =
		"drop table OpenIdConnectSession";

	public static final String ORDER_BY_JPQL =
		" ORDER BY openIdConnectSession.openIdConnectSessionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY OpenIdConnectSession.openIdConnectSessionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long CONFIGURATIONPID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long USERID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long OPENIDCONNECTSESSIONID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public OpenIdConnectSessionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _openIdConnectSessionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOpenIdConnectSessionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _openIdConnectSessionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return OpenIdConnectSession.class;
	}

	@Override
	public String getModelClassName() {
		return OpenIdConnectSession.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<OpenIdConnectSession, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<OpenIdConnectSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OpenIdConnectSession, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((OpenIdConnectSession)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<OpenIdConnectSession, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<OpenIdConnectSession, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(OpenIdConnectSession)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<OpenIdConnectSession, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<OpenIdConnectSession, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, OpenIdConnectSession>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			OpenIdConnectSession.class.getClassLoader(),
			OpenIdConnectSession.class, ModelWrapper.class);

		try {
			Constructor<OpenIdConnectSession> constructor =
				(Constructor<OpenIdConnectSession>)proxyClass.getConstructor(
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

	private static final Map<String, Function<OpenIdConnectSession, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<OpenIdConnectSession, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<OpenIdConnectSession, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<OpenIdConnectSession, Object>>();
		Map<String, BiConsumer<OpenIdConnectSession, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<OpenIdConnectSession, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", OpenIdConnectSession::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<OpenIdConnectSession, Long>)
				OpenIdConnectSession::setMvccVersion);
		attributeGetterFunctions.put(
			"openIdConnectSessionId",
			OpenIdConnectSession::getOpenIdConnectSessionId);
		attributeSetterBiConsumers.put(
			"openIdConnectSessionId",
			(BiConsumer<OpenIdConnectSession, Long>)
				OpenIdConnectSession::setOpenIdConnectSessionId);
		attributeGetterFunctions.put(
			"companyId", OpenIdConnectSession::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<OpenIdConnectSession, Long>)
				OpenIdConnectSession::setCompanyId);
		attributeGetterFunctions.put(
			"modifiedDate", OpenIdConnectSession::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<OpenIdConnectSession, Date>)
				OpenIdConnectSession::setModifiedDate);
		attributeGetterFunctions.put("userId", OpenIdConnectSession::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<OpenIdConnectSession, Long>)
				OpenIdConnectSession::setUserId);
		attributeGetterFunctions.put(
			"accessToken", OpenIdConnectSession::getAccessToken);
		attributeSetterBiConsumers.put(
			"accessToken",
			(BiConsumer<OpenIdConnectSession, String>)
				OpenIdConnectSession::setAccessToken);
		attributeGetterFunctions.put(
			"configurationPid", OpenIdConnectSession::getConfigurationPid);
		attributeSetterBiConsumers.put(
			"configurationPid",
			(BiConsumer<OpenIdConnectSession, String>)
				OpenIdConnectSession::setConfigurationPid);
		attributeGetterFunctions.put(
			"idToken", OpenIdConnectSession::getIdToken);
		attributeSetterBiConsumers.put(
			"idToken",
			(BiConsumer<OpenIdConnectSession, String>)
				OpenIdConnectSession::setIdToken);
		attributeGetterFunctions.put(
			"providerName", OpenIdConnectSession::getProviderName);
		attributeSetterBiConsumers.put(
			"providerName",
			(BiConsumer<OpenIdConnectSession, String>)
				OpenIdConnectSession::setProviderName);
		attributeGetterFunctions.put(
			"refreshToken", OpenIdConnectSession::getRefreshToken);
		attributeSetterBiConsumers.put(
			"refreshToken",
			(BiConsumer<OpenIdConnectSession, String>)
				OpenIdConnectSession::setRefreshToken);

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
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_mvccVersion = mvccVersion;
	}

	@Override
	public long getOpenIdConnectSessionId() {
		return _openIdConnectSessionId;
	}

	@Override
	public void setOpenIdConnectSessionId(long openIdConnectSessionId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_openIdConnectSessionId = openIdConnectSessionId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
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

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
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

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalUserId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("userId"));
	}

	@Override
	public String getAccessToken() {
		if (_accessToken == null) {
			return "";
		}
		else {
			return _accessToken;
		}
	}

	@Override
	public void setAccessToken(String accessToken) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_accessToken = accessToken;
	}

	@Override
	public String getConfigurationPid() {
		if (_configurationPid == null) {
			return "";
		}
		else {
			return _configurationPid;
		}
	}

	@Override
	public void setConfigurationPid(String configurationPid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_configurationPid = configurationPid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalConfigurationPid() {
		return getColumnOriginalValue("configurationPid");
	}

	@Override
	public String getIdToken() {
		if (_idToken == null) {
			return "";
		}
		else {
			return _idToken;
		}
	}

	@Override
	public void setIdToken(String idToken) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_idToken = idToken;
	}

	@Override
	public String getProviderName() {
		if (_providerName == null) {
			return "";
		}
		else {
			return _providerName;
		}
	}

	@Override
	public void setProviderName(String providerName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_providerName = providerName;
	}

	@Override
	public String getRefreshToken() {
		if (_refreshToken == null) {
			return "";
		}
		else {
			return _refreshToken;
		}
	}

	@Override
	public void setRefreshToken(String refreshToken) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_refreshToken = refreshToken;
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), OpenIdConnectSession.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public OpenIdConnectSession toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, OpenIdConnectSession>
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
		OpenIdConnectSessionImpl openIdConnectSessionImpl =
			new OpenIdConnectSessionImpl();

		openIdConnectSessionImpl.setMvccVersion(getMvccVersion());
		openIdConnectSessionImpl.setOpenIdConnectSessionId(
			getOpenIdConnectSessionId());
		openIdConnectSessionImpl.setCompanyId(getCompanyId());
		openIdConnectSessionImpl.setModifiedDate(getModifiedDate());
		openIdConnectSessionImpl.setUserId(getUserId());
		openIdConnectSessionImpl.setAccessToken(getAccessToken());
		openIdConnectSessionImpl.setConfigurationPid(getConfigurationPid());
		openIdConnectSessionImpl.setIdToken(getIdToken());
		openIdConnectSessionImpl.setProviderName(getProviderName());
		openIdConnectSessionImpl.setRefreshToken(getRefreshToken());

		openIdConnectSessionImpl.resetOriginalValues();

		return openIdConnectSessionImpl;
	}

	@Override
	public OpenIdConnectSession cloneWithOriginalValues() {
		OpenIdConnectSessionImpl openIdConnectSessionImpl =
			new OpenIdConnectSessionImpl();

		openIdConnectSessionImpl.setMvccVersion(
			this.<Long>getColumnOriginalValue("mvccVersion"));
		openIdConnectSessionImpl.setOpenIdConnectSessionId(
			this.<Long>getColumnOriginalValue("openIdConnectSessionId"));
		openIdConnectSessionImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		openIdConnectSessionImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		openIdConnectSessionImpl.setUserId(
			this.<Long>getColumnOriginalValue("userId"));
		openIdConnectSessionImpl.setAccessToken(
			this.<String>getColumnOriginalValue("accessToken"));
		openIdConnectSessionImpl.setConfigurationPid(
			this.<String>getColumnOriginalValue("configurationPid"));
		openIdConnectSessionImpl.setIdToken(
			this.<String>getColumnOriginalValue("idToken"));
		openIdConnectSessionImpl.setProviderName(
			this.<String>getColumnOriginalValue("providerName"));
		openIdConnectSessionImpl.setRefreshToken(
			this.<String>getColumnOriginalValue("refreshToken"));

		return openIdConnectSessionImpl;
	}

	@Override
	public int compareTo(OpenIdConnectSession openIdConnectSession) {
		long primaryKey = openIdConnectSession.getPrimaryKey();

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

		if (!(object instanceof OpenIdConnectSession)) {
			return false;
		}

		OpenIdConnectSession openIdConnectSession =
			(OpenIdConnectSession)object;

		long primaryKey = openIdConnectSession.getPrimaryKey();

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

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<OpenIdConnectSession> toCacheModel() {
		OpenIdConnectSessionCacheModel openIdConnectSessionCacheModel =
			new OpenIdConnectSessionCacheModel();

		openIdConnectSessionCacheModel.mvccVersion = getMvccVersion();

		openIdConnectSessionCacheModel.openIdConnectSessionId =
			getOpenIdConnectSessionId();

		openIdConnectSessionCacheModel.companyId = getCompanyId();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			openIdConnectSessionCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			openIdConnectSessionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		openIdConnectSessionCacheModel.userId = getUserId();

		openIdConnectSessionCacheModel.accessToken = getAccessToken();

		String accessToken = openIdConnectSessionCacheModel.accessToken;

		if ((accessToken != null) && (accessToken.length() == 0)) {
			openIdConnectSessionCacheModel.accessToken = null;
		}

		openIdConnectSessionCacheModel.configurationPid = getConfigurationPid();

		String configurationPid =
			openIdConnectSessionCacheModel.configurationPid;

		if ((configurationPid != null) && (configurationPid.length() == 0)) {
			openIdConnectSessionCacheModel.configurationPid = null;
		}

		openIdConnectSessionCacheModel.idToken = getIdToken();

		String idToken = openIdConnectSessionCacheModel.idToken;

		if ((idToken != null) && (idToken.length() == 0)) {
			openIdConnectSessionCacheModel.idToken = null;
		}

		openIdConnectSessionCacheModel.providerName = getProviderName();

		String providerName = openIdConnectSessionCacheModel.providerName;

		if ((providerName != null) && (providerName.length() == 0)) {
			openIdConnectSessionCacheModel.providerName = null;
		}

		openIdConnectSessionCacheModel.refreshToken = getRefreshToken();

		String refreshToken = openIdConnectSessionCacheModel.refreshToken;

		if ((refreshToken != null) && (refreshToken.length() == 0)) {
			openIdConnectSessionCacheModel.refreshToken = null;
		}

		return openIdConnectSessionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<OpenIdConnectSession, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<OpenIdConnectSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OpenIdConnectSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply(
				(OpenIdConnectSession)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

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
		Map<String, Function<OpenIdConnectSession, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<OpenIdConnectSession, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<OpenIdConnectSession, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((OpenIdConnectSession)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, OpenIdConnectSession>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _openIdConnectSessionId;
	private long _companyId;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _userId;
	private String _accessToken;
	private String _configurationPid;
	private String _idToken;
	private String _providerName;
	private String _refreshToken;

	public <T> T getColumnValue(String columnName) {
		Function<OpenIdConnectSession, Object> function =
			_attributeGetterFunctions.get(columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((OpenIdConnectSession)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("mvccVersion", _mvccVersion);
		_columnOriginalValues.put(
			"openIdConnectSessionId", _openIdConnectSessionId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("accessToken", _accessToken);
		_columnOriginalValues.put("configurationPid", _configurationPid);
		_columnOriginalValues.put("idToken", _idToken);
		_columnOriginalValues.put("providerName", _providerName);
		_columnOriginalValues.put("refreshToken", _refreshToken);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("mvccVersion", 1L);

		columnBitmasks.put("openIdConnectSessionId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("modifiedDate", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("accessToken", 32L);

		columnBitmasks.put("configurationPid", 64L);

		columnBitmasks.put("idToken", 128L);

		columnBitmasks.put("providerName", 256L);

		columnBitmasks.put("refreshToken", 512L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private OpenIdConnectSession _escapedModel;

}