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

package com.liferay.osb.koroneiki.scion.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.model.ServiceProducerModel;
import com.liferay.osb.koroneiki.scion.model.ServiceProducerSoap;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ServiceProducer service. Represents a row in the &quot;Koroneiki_ServiceProducer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>ServiceProducerModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ServiceProducerImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerImpl
 * @generated
 */
@JSON(strict = true)
public class ServiceProducerModelImpl
	extends BaseModelImpl<ServiceProducer> implements ServiceProducerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a service producer model instance should use the <code>ServiceProducer</code> interface instead.
	 */
	public static final String TABLE_NAME = "Koroneiki_ServiceProducer";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"serviceProducerId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"authorizationUserId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceProducerId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("authorizationUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Koroneiki_ServiceProducer (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,serviceProducerId LONG not null primary key,companyId LONG,userId LONG,authorizationUserId LONG,name VARCHAR(75) null,description STRING null)";

	public static final String TABLE_SQL_DROP =
		"drop table Koroneiki_ServiceProducer";

	public static final String ORDER_BY_JPQL =
		" ORDER BY serviceProducer.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Koroneiki_ServiceProducer.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long AUTHORIZATIONUSERID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ServiceProducer toModel(ServiceProducerSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ServiceProducer model = new ServiceProducerImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setServiceProducerId(soapModel.getServiceProducerId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setAuthorizationUserId(soapModel.getAuthorizationUserId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ServiceProducer> toModels(
		ServiceProducerSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ServiceProducer> models = new ArrayList<ServiceProducer>(
			soapModels.length);

		for (ServiceProducerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public ServiceProducerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _serviceProducerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setServiceProducerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _serviceProducerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceProducer.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceProducer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ServiceProducer, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ServiceProducer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ServiceProducer, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ServiceProducer)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ServiceProducer, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ServiceProducer, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ServiceProducer)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ServiceProducer, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ServiceProducer, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ServiceProducer>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ServiceProducer.class.getClassLoader(), ServiceProducer.class,
			ModelWrapper.class);

		try {
			Constructor<ServiceProducer> constructor =
				(Constructor<ServiceProducer>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ServiceProducer, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ServiceProducer, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ServiceProducer, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<ServiceProducer, Object>>();
		Map<String, BiConsumer<ServiceProducer, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ServiceProducer, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ServiceProducer::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ServiceProducer, Long>)ServiceProducer::setMvccVersion);
		attributeGetterFunctions.put("uuid", ServiceProducer::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<ServiceProducer, String>)ServiceProducer::setUuid);
		attributeGetterFunctions.put(
			"serviceProducerId", ServiceProducer::getServiceProducerId);
		attributeSetterBiConsumers.put(
			"serviceProducerId",
			(BiConsumer<ServiceProducer, Long>)
				ServiceProducer::setServiceProducerId);
		attributeGetterFunctions.put(
			"companyId", ServiceProducer::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ServiceProducer, Long>)ServiceProducer::setCompanyId);
		attributeGetterFunctions.put("userId", ServiceProducer::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<ServiceProducer, Long>)ServiceProducer::setUserId);
		attributeGetterFunctions.put(
			"authorizationUserId", ServiceProducer::getAuthorizationUserId);
		attributeSetterBiConsumers.put(
			"authorizationUserId",
			(BiConsumer<ServiceProducer, Long>)
				ServiceProducer::setAuthorizationUserId);
		attributeGetterFunctions.put("name", ServiceProducer::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<ServiceProducer, String>)ServiceProducer::setName);
		attributeGetterFunctions.put(
			"description", ServiceProducer::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<ServiceProducer, String>)
				ServiceProducer::setDescription);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
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
	public long getServiceProducerId() {
		return _serviceProducerId;
	}

	@Override
	public void setServiceProducerId(long serviceProducerId) {
		_serviceProducerId = serviceProducerId;
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
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public long getAuthorizationUserId() {
		return _authorizationUserId;
	}

	@Override
	public void setAuthorizationUserId(long authorizationUserId) {
		_columnBitmask |= AUTHORIZATIONUSERID_COLUMN_BITMASK;

		if (!_setOriginalAuthorizationUserId) {
			_setOriginalAuthorizationUserId = true;

			_originalAuthorizationUserId = _authorizationUserId;
		}

		_authorizationUserId = authorizationUserId;
	}

	@Override
	public String getAuthorizationUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(
				getAuthorizationUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setAuthorizationUserUuid(String authorizationUserUuid) {
	}

	public long getOriginalAuthorizationUserId() {
		return _originalAuthorizationUserId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ServiceProducer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ServiceProducer toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ServiceProducer>
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
		ServiceProducerImpl serviceProducerImpl = new ServiceProducerImpl();

		serviceProducerImpl.setMvccVersion(getMvccVersion());
		serviceProducerImpl.setUuid(getUuid());
		serviceProducerImpl.setServiceProducerId(getServiceProducerId());
		serviceProducerImpl.setCompanyId(getCompanyId());
		serviceProducerImpl.setUserId(getUserId());
		serviceProducerImpl.setAuthorizationUserId(getAuthorizationUserId());
		serviceProducerImpl.setName(getName());
		serviceProducerImpl.setDescription(getDescription());

		serviceProducerImpl.resetOriginalValues();

		return serviceProducerImpl;
	}

	@Override
	public int compareTo(ServiceProducer serviceProducer) {
		int value = 0;

		value = getName().compareTo(serviceProducer.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ServiceProducer)) {
			return false;
		}

		ServiceProducer serviceProducer = (ServiceProducer)object;

		long primaryKey = serviceProducer.getPrimaryKey();

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
		ServiceProducerModelImpl serviceProducerModelImpl = this;

		serviceProducerModelImpl._originalUuid = serviceProducerModelImpl._uuid;

		serviceProducerModelImpl._originalCompanyId =
			serviceProducerModelImpl._companyId;

		serviceProducerModelImpl._setOriginalCompanyId = false;

		serviceProducerModelImpl._originalAuthorizationUserId =
			serviceProducerModelImpl._authorizationUserId;

		serviceProducerModelImpl._setOriginalAuthorizationUserId = false;

		serviceProducerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ServiceProducer> toCacheModel() {
		ServiceProducerCacheModel serviceProducerCacheModel =
			new ServiceProducerCacheModel();

		serviceProducerCacheModel.mvccVersion = getMvccVersion();

		serviceProducerCacheModel.uuid = getUuid();

		String uuid = serviceProducerCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			serviceProducerCacheModel.uuid = null;
		}

		serviceProducerCacheModel.serviceProducerId = getServiceProducerId();

		serviceProducerCacheModel.companyId = getCompanyId();

		serviceProducerCacheModel.userId = getUserId();

		serviceProducerCacheModel.authorizationUserId =
			getAuthorizationUserId();

		serviceProducerCacheModel.name = getName();

		String name = serviceProducerCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			serviceProducerCacheModel.name = null;
		}

		serviceProducerCacheModel.description = getDescription();

		String description = serviceProducerCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			serviceProducerCacheModel.description = null;
		}

		return serviceProducerCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ServiceProducer, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ServiceProducer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ServiceProducer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ServiceProducer)this));
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
		Map<String, Function<ServiceProducer, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ServiceProducer, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ServiceProducer, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ServiceProducer)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ServiceProducer>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _serviceProducerId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _authorizationUserId;
	private long _originalAuthorizationUserId;
	private boolean _setOriginalAuthorizationUserId;
	private String _name;
	private String _description;
	private long _columnBitmask;
	private ServiceProducer _escapedModel;

}