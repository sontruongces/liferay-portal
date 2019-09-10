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

package com.liferay.portal.reports.engine.console.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.reports.engine.console.model.Entry;
import com.liferay.portal.reports.engine.console.model.EntryModel;
import com.liferay.portal.reports.engine.console.model.EntrySoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

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

/**
 * The base model implementation for the Entry service. Represents a row in the &quot;Reports_Entry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>EntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link EntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryImpl
 * @generated
 */
@JSON(strict = true)
public class EntryModelImpl extends BaseModelImpl<Entry> implements EntryModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a entry model instance should use the <code>Entry</code> interface instead.
	 */
	public static final String TABLE_NAME = "Reports_Entry";

	public static final Object[][] TABLE_COLUMNS = {
		{"entryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"definitionId", Types.BIGINT},
		{"format", Types.VARCHAR}, {"scheduleRequest", Types.BOOLEAN},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP},
		{"repeating", Types.BOOLEAN}, {"recurrence", Types.VARCHAR},
		{"emailNotifications", Types.VARCHAR}, {"emailDelivery", Types.VARCHAR},
		{"portletId", Types.VARCHAR}, {"pageURL", Types.VARCHAR},
		{"reportParameters", Types.CLOB}, {"errorMessage", Types.VARCHAR},
		{"status", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("entryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("definitionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("format", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("scheduleRequest", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("repeating", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("recurrence", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailNotifications", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("emailDelivery", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("pageURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("reportParameters", Types.CLOB);
		TABLE_COLUMNS_MAP.put("errorMessage", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("status", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Reports_Entry (entryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,definitionId LONG,format VARCHAR(75) null,scheduleRequest BOOLEAN,startDate DATE null,endDate DATE null,repeating BOOLEAN,recurrence VARCHAR(75) null,emailNotifications VARCHAR(200) null,emailDelivery VARCHAR(200) null,portletId VARCHAR(75) null,pageURL STRING null,reportParameters TEXT null,errorMessage STRING null,status VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Reports_Entry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY entry.modifiedDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Reports_Entry.modifiedDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.reports.engine.console.model.Entry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.reports.engine.console.model.Entry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Entry toModel(EntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Entry model = new EntryImpl();

		model.setEntryId(soapModel.getEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDefinitionId(soapModel.getDefinitionId());
		model.setFormat(soapModel.getFormat());
		model.setScheduleRequest(soapModel.isScheduleRequest());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setRepeating(soapModel.isRepeating());
		model.setRecurrence(soapModel.getRecurrence());
		model.setEmailNotifications(soapModel.getEmailNotifications());
		model.setEmailDelivery(soapModel.getEmailDelivery());
		model.setPortletId(soapModel.getPortletId());
		model.setPageURL(soapModel.getPageURL());
		model.setReportParameters(soapModel.getReportParameters());
		model.setErrorMessage(soapModel.getErrorMessage());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Entry> toModels(EntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Entry> models = new ArrayList<Entry>(soapModels.length);

		for (EntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.reports.engine.console.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.reports.engine.console.model.Entry"));

	public EntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _entryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Entry.class;
	}

	@Override
	public String getModelClassName() {
		return Entry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Entry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Entry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Entry, Object> attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Entry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Entry, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Entry, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Entry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Entry, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Entry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Entry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Entry.class.getClassLoader(), Entry.class, ModelWrapper.class);

		try {
			Constructor<Entry> constructor =
				(Constructor<Entry>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<Entry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Entry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Entry, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Entry, Object>>();
		Map<String, BiConsumer<Entry, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Entry, ?>>();

		attributeGetterFunctions.put("entryId", Entry::getEntryId);
		attributeSetterBiConsumers.put(
			"entryId", (BiConsumer<Entry, Long>)Entry::setEntryId);
		attributeGetterFunctions.put("groupId", Entry::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<Entry, Long>)Entry::setGroupId);
		attributeGetterFunctions.put("companyId", Entry::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<Entry, Long>)Entry::setCompanyId);
		attributeGetterFunctions.put("userId", Entry::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Entry, Long>)Entry::setUserId);
		attributeGetterFunctions.put("userName", Entry::getUserName);
		attributeSetterBiConsumers.put(
			"userName", (BiConsumer<Entry, String>)Entry::setUserName);
		attributeGetterFunctions.put("createDate", Entry::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate", (BiConsumer<Entry, Date>)Entry::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Entry::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate", (BiConsumer<Entry, Date>)Entry::setModifiedDate);
		attributeGetterFunctions.put("definitionId", Entry::getDefinitionId);
		attributeSetterBiConsumers.put(
			"definitionId", (BiConsumer<Entry, Long>)Entry::setDefinitionId);
		attributeGetterFunctions.put("format", Entry::getFormat);
		attributeSetterBiConsumers.put(
			"format", (BiConsumer<Entry, String>)Entry::setFormat);
		attributeGetterFunctions.put(
			"scheduleRequest", Entry::getScheduleRequest);
		attributeSetterBiConsumers.put(
			"scheduleRequest",
			(BiConsumer<Entry, Boolean>)Entry::setScheduleRequest);
		attributeGetterFunctions.put("startDate", Entry::getStartDate);
		attributeSetterBiConsumers.put(
			"startDate", (BiConsumer<Entry, Date>)Entry::setStartDate);
		attributeGetterFunctions.put("endDate", Entry::getEndDate);
		attributeSetterBiConsumers.put(
			"endDate", (BiConsumer<Entry, Date>)Entry::setEndDate);
		attributeGetterFunctions.put("repeating", Entry::getRepeating);
		attributeSetterBiConsumers.put(
			"repeating", (BiConsumer<Entry, Boolean>)Entry::setRepeating);
		attributeGetterFunctions.put("recurrence", Entry::getRecurrence);
		attributeSetterBiConsumers.put(
			"recurrence", (BiConsumer<Entry, String>)Entry::setRecurrence);
		attributeGetterFunctions.put(
			"emailNotifications", Entry::getEmailNotifications);
		attributeSetterBiConsumers.put(
			"emailNotifications",
			(BiConsumer<Entry, String>)Entry::setEmailNotifications);
		attributeGetterFunctions.put("emailDelivery", Entry::getEmailDelivery);
		attributeSetterBiConsumers.put(
			"emailDelivery",
			(BiConsumer<Entry, String>)Entry::setEmailDelivery);
		attributeGetterFunctions.put("portletId", Entry::getPortletId);
		attributeSetterBiConsumers.put(
			"portletId", (BiConsumer<Entry, String>)Entry::setPortletId);
		attributeGetterFunctions.put("pageURL", Entry::getPageURL);
		attributeSetterBiConsumers.put(
			"pageURL", (BiConsumer<Entry, String>)Entry::setPageURL);
		attributeGetterFunctions.put(
			"reportParameters", Entry::getReportParameters);
		attributeSetterBiConsumers.put(
			"reportParameters",
			(BiConsumer<Entry, String>)Entry::setReportParameters);
		attributeGetterFunctions.put("errorMessage", Entry::getErrorMessage);
		attributeSetterBiConsumers.put(
			"errorMessage", (BiConsumer<Entry, String>)Entry::setErrorMessage);
		attributeGetterFunctions.put("status", Entry::getStatus);
		attributeSetterBiConsumers.put(
			"status", (BiConsumer<Entry, String>)Entry::setStatus);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getEntryId() {
		return _entryId;
	}

	@Override
	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
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
	public long getDefinitionId() {
		return _definitionId;
	}

	@Override
	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;
	}

	@JSON
	@Override
	public String getFormat() {
		if (_format == null) {
			return "";
		}
		else {
			return _format;
		}
	}

	@Override
	public void setFormat(String format) {
		_format = format;
	}

	@JSON
	@Override
	public boolean getScheduleRequest() {
		return _scheduleRequest;
	}

	@JSON
	@Override
	public boolean isScheduleRequest() {
		return _scheduleRequest;
	}

	@Override
	public void setScheduleRequest(boolean scheduleRequest) {
		_scheduleRequest = scheduleRequest;
	}

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@JSON
	@Override
	public boolean getRepeating() {
		return _repeating;
	}

	@JSON
	@Override
	public boolean isRepeating() {
		return _repeating;
	}

	@Override
	public void setRepeating(boolean repeating) {
		_repeating = repeating;
	}

	@JSON
	@Override
	public String getRecurrence() {
		if (_recurrence == null) {
			return "";
		}
		else {
			return _recurrence;
		}
	}

	@Override
	public void setRecurrence(String recurrence) {
		_recurrence = recurrence;
	}

	@JSON
	@Override
	public String getEmailNotifications() {
		if (_emailNotifications == null) {
			return "";
		}
		else {
			return _emailNotifications;
		}
	}

	@Override
	public void setEmailNotifications(String emailNotifications) {
		_emailNotifications = emailNotifications;
	}

	@JSON
	@Override
	public String getEmailDelivery() {
		if (_emailDelivery == null) {
			return "";
		}
		else {
			return _emailDelivery;
		}
	}

	@Override
	public void setEmailDelivery(String emailDelivery) {
		_emailDelivery = emailDelivery;
	}

	@JSON
	@Override
	public String getPortletId() {
		if (_portletId == null) {
			return "";
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	@JSON
	@Override
	public String getPageURL() {
		if (_pageURL == null) {
			return "";
		}
		else {
			return _pageURL;
		}
	}

	@Override
	public void setPageURL(String pageURL) {
		_pageURL = pageURL;
	}

	@JSON
	@Override
	public String getReportParameters() {
		if (_reportParameters == null) {
			return "";
		}
		else {
			return _reportParameters;
		}
	}

	@Override
	public void setReportParameters(String reportParameters) {
		_reportParameters = reportParameters;
	}

	@JSON
	@Override
	public String getErrorMessage() {
		if (_errorMessage == null) {
			return "";
		}
		else {
			return _errorMessage;
		}
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	@JSON
	@Override
	public String getStatus() {
		if (_status == null) {
			return "";
		}
		else {
			return _status;
		}
	}

	@Override
	public void setStatus(String status) {
		_status = status;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Entry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Entry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Entry>
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
		EntryImpl entryImpl = new EntryImpl();

		entryImpl.setEntryId(getEntryId());
		entryImpl.setGroupId(getGroupId());
		entryImpl.setCompanyId(getCompanyId());
		entryImpl.setUserId(getUserId());
		entryImpl.setUserName(getUserName());
		entryImpl.setCreateDate(getCreateDate());
		entryImpl.setModifiedDate(getModifiedDate());
		entryImpl.setDefinitionId(getDefinitionId());
		entryImpl.setFormat(getFormat());
		entryImpl.setScheduleRequest(isScheduleRequest());
		entryImpl.setStartDate(getStartDate());
		entryImpl.setEndDate(getEndDate());
		entryImpl.setRepeating(isRepeating());
		entryImpl.setRecurrence(getRecurrence());
		entryImpl.setEmailNotifications(getEmailNotifications());
		entryImpl.setEmailDelivery(getEmailDelivery());
		entryImpl.setPortletId(getPortletId());
		entryImpl.setPageURL(getPageURL());
		entryImpl.setReportParameters(getReportParameters());
		entryImpl.setErrorMessage(getErrorMessage());
		entryImpl.setStatus(getStatus());

		entryImpl.resetOriginalValues();

		return entryImpl;
	}

	@Override
	public int compareTo(Entry entry) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(), entry.getModifiedDate());

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

		if (!(obj instanceof Entry)) {
			return false;
		}

		Entry entry = (Entry)obj;

		long primaryKey = entry.getPrimaryKey();

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
		EntryModelImpl entryModelImpl = this;

		entryModelImpl._setModifiedDate = false;
	}

	@Override
	public CacheModel<Entry> toCacheModel() {
		EntryCacheModel entryCacheModel = new EntryCacheModel();

		entryCacheModel.entryId = getEntryId();

		entryCacheModel.groupId = getGroupId();

		entryCacheModel.companyId = getCompanyId();

		entryCacheModel.userId = getUserId();

		entryCacheModel.userName = getUserName();

		String userName = entryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			entryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			entryCacheModel.createDate = createDate.getTime();
		}
		else {
			entryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			entryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			entryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		entryCacheModel.definitionId = getDefinitionId();

		entryCacheModel.format = getFormat();

		String format = entryCacheModel.format;

		if ((format != null) && (format.length() == 0)) {
			entryCacheModel.format = null;
		}

		entryCacheModel.scheduleRequest = isScheduleRequest();

		Date startDate = getStartDate();

		if (startDate != null) {
			entryCacheModel.startDate = startDate.getTime();
		}
		else {
			entryCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			entryCacheModel.endDate = endDate.getTime();
		}
		else {
			entryCacheModel.endDate = Long.MIN_VALUE;
		}

		entryCacheModel.repeating = isRepeating();

		entryCacheModel.recurrence = getRecurrence();

		String recurrence = entryCacheModel.recurrence;

		if ((recurrence != null) && (recurrence.length() == 0)) {
			entryCacheModel.recurrence = null;
		}

		entryCacheModel.emailNotifications = getEmailNotifications();

		String emailNotifications = entryCacheModel.emailNotifications;

		if ((emailNotifications != null) &&
			(emailNotifications.length() == 0)) {

			entryCacheModel.emailNotifications = null;
		}

		entryCacheModel.emailDelivery = getEmailDelivery();

		String emailDelivery = entryCacheModel.emailDelivery;

		if ((emailDelivery != null) && (emailDelivery.length() == 0)) {
			entryCacheModel.emailDelivery = null;
		}

		entryCacheModel.portletId = getPortletId();

		String portletId = entryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			entryCacheModel.portletId = null;
		}

		entryCacheModel.pageURL = getPageURL();

		String pageURL = entryCacheModel.pageURL;

		if ((pageURL != null) && (pageURL.length() == 0)) {
			entryCacheModel.pageURL = null;
		}

		entryCacheModel.reportParameters = getReportParameters();

		String reportParameters = entryCacheModel.reportParameters;

		if ((reportParameters != null) && (reportParameters.length() == 0)) {
			entryCacheModel.reportParameters = null;
		}

		entryCacheModel.errorMessage = getErrorMessage();

		String errorMessage = entryCacheModel.errorMessage;

		if ((errorMessage != null) && (errorMessage.length() == 0)) {
			entryCacheModel.errorMessage = null;
		}

		entryCacheModel.status = getStatus();

		String status = entryCacheModel.status;

		if ((status != null) && (status.length() == 0)) {
			entryCacheModel.status = null;
		}

		return entryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Entry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Entry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Entry, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Entry)this));
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
		Map<String, Function<Entry, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Entry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Entry, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Entry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Entry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _entryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _definitionId;
	private String _format;
	private boolean _scheduleRequest;
	private Date _startDate;
	private Date _endDate;
	private boolean _repeating;
	private String _recurrence;
	private String _emailNotifications;
	private String _emailDelivery;
	private String _portletId;
	private String _pageURL;
	private String _reportParameters;
	private String _errorMessage;
	private String _status;
	private Entry _escapedModel;

}