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

package com.liferay.polls.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.polls.model.PollsChoice;
import com.liferay.polls.model.PollsChoiceModel;
import com.liferay.polls.model.PollsChoiceSoap;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the PollsChoice service. Represents a row in the &quot;PollsChoice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>PollsChoiceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PollsChoiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoiceImpl
 * @generated
 */
@JSON(strict = true)
public class PollsChoiceModelImpl
	extends BaseModelImpl<PollsChoice> implements PollsChoiceModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a polls choice model instance should use the <code>PollsChoice</code> interface instead.
	 */
	public static final String TABLE_NAME = "PollsChoice";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"choiceId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"questionId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("choiceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("questionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PollsChoice (uuid_ VARCHAR(75) null,choiceId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,questionId LONG,name VARCHAR(75) null,description STRING null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table PollsChoice";

	public static final String ORDER_BY_JPQL =
		" ORDER BY pollsChoice.questionId ASC, pollsChoice.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PollsChoice.questionId ASC, PollsChoice.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long QUESTIONID_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

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
	public static PollsChoice toModel(PollsChoiceSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		PollsChoice model = new PollsChoiceImpl();

		model.setUuid(soapModel.getUuid());
		model.setChoiceId(soapModel.getChoiceId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setQuestionId(soapModel.getQuestionId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<PollsChoice> toModels(PollsChoiceSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<PollsChoice> models = new ArrayList<PollsChoice>(
			soapModels.length);

		for (PollsChoiceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public PollsChoiceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _choiceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChoiceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _choiceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PollsChoice.class;
	}

	@Override
	public String getModelClassName() {
		return PollsChoice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PollsChoice, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PollsChoice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PollsChoice, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PollsChoice)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PollsChoice, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PollsChoice, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PollsChoice)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PollsChoice, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PollsChoice, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PollsChoice>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PollsChoice.class.getClassLoader(), PollsChoice.class,
			ModelWrapper.class);

		try {
			Constructor<PollsChoice> constructor =
				(Constructor<PollsChoice>)proxyClass.getConstructor(
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

	private static final Map<String, Function<PollsChoice, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<PollsChoice, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<PollsChoice, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<PollsChoice, Object>>();
		Map<String, BiConsumer<PollsChoice, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<PollsChoice, ?>>();

		attributeGetterFunctions.put("uuid", PollsChoice::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<PollsChoice, String>)PollsChoice::setUuid);
		attributeGetterFunctions.put("choiceId", PollsChoice::getChoiceId);
		attributeSetterBiConsumers.put(
			"choiceId",
			(BiConsumer<PollsChoice, Long>)PollsChoice::setChoiceId);
		attributeGetterFunctions.put("groupId", PollsChoice::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<PollsChoice, Long>)PollsChoice::setGroupId);
		attributeGetterFunctions.put("companyId", PollsChoice::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<PollsChoice, Long>)PollsChoice::setCompanyId);
		attributeGetterFunctions.put("userId", PollsChoice::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<PollsChoice, Long>)PollsChoice::setUserId);
		attributeGetterFunctions.put("userName", PollsChoice::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<PollsChoice, String>)PollsChoice::setUserName);
		attributeGetterFunctions.put("createDate", PollsChoice::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<PollsChoice, Date>)PollsChoice::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", PollsChoice::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<PollsChoice, Date>)PollsChoice::setModifiedDate);
		attributeGetterFunctions.put("questionId", PollsChoice::getQuestionId);
		attributeSetterBiConsumers.put(
			"questionId",
			(BiConsumer<PollsChoice, Long>)PollsChoice::setQuestionId);
		attributeGetterFunctions.put("name", PollsChoice::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<PollsChoice, String>)PollsChoice::setName);
		attributeGetterFunctions.put(
			"description", PollsChoice::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<PollsChoice, String>)PollsChoice::setDescription);
		attributeGetterFunctions.put(
			"lastPublishDate", PollsChoice::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<PollsChoice, Date>)PollsChoice::setLastPublishDate);

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
	public long getChoiceId() {
		return _choiceId;
	}

	@Override
	public void setChoiceId(long choiceId) {
		_choiceId = choiceId;
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
	public long getQuestionId() {
		return _questionId;
	}

	@Override
	public void setQuestionId(long questionId) {
		_columnBitmask = -1L;

		if (!_setOriginalQuestionId) {
			_setOriginalQuestionId = true;

			_originalQuestionId = _questionId;
		}

		_questionId = questionId;
	}

	public long getOriginalQuestionId() {
		return _originalQuestionId;
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

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
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
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
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

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(PollsChoice.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PollsChoice.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getDescription();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			PollsChoice.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public PollsChoice toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PollsChoice>
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
		PollsChoiceImpl pollsChoiceImpl = new PollsChoiceImpl();

		pollsChoiceImpl.setUuid(getUuid());
		pollsChoiceImpl.setChoiceId(getChoiceId());
		pollsChoiceImpl.setGroupId(getGroupId());
		pollsChoiceImpl.setCompanyId(getCompanyId());
		pollsChoiceImpl.setUserId(getUserId());
		pollsChoiceImpl.setUserName(getUserName());
		pollsChoiceImpl.setCreateDate(getCreateDate());
		pollsChoiceImpl.setModifiedDate(getModifiedDate());
		pollsChoiceImpl.setQuestionId(getQuestionId());
		pollsChoiceImpl.setName(getName());
		pollsChoiceImpl.setDescription(getDescription());
		pollsChoiceImpl.setLastPublishDate(getLastPublishDate());

		pollsChoiceImpl.resetOriginalValues();

		return pollsChoiceImpl;
	}

	@Override
	public int compareTo(PollsChoice pollsChoice) {
		int value = 0;

		if (getQuestionId() < pollsChoice.getQuestionId()) {
			value = -1;
		}
		else if (getQuestionId() > pollsChoice.getQuestionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getName().compareTo(pollsChoice.getName());

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

		if (!(obj instanceof PollsChoice)) {
			return false;
		}

		PollsChoice pollsChoice = (PollsChoice)obj;

		long primaryKey = pollsChoice.getPrimaryKey();

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
		PollsChoiceModelImpl pollsChoiceModelImpl = this;

		pollsChoiceModelImpl._originalUuid = pollsChoiceModelImpl._uuid;

		pollsChoiceModelImpl._originalGroupId = pollsChoiceModelImpl._groupId;

		pollsChoiceModelImpl._setOriginalGroupId = false;

		pollsChoiceModelImpl._originalCompanyId =
			pollsChoiceModelImpl._companyId;

		pollsChoiceModelImpl._setOriginalCompanyId = false;

		pollsChoiceModelImpl._setModifiedDate = false;

		pollsChoiceModelImpl._originalQuestionId =
			pollsChoiceModelImpl._questionId;

		pollsChoiceModelImpl._setOriginalQuestionId = false;

		pollsChoiceModelImpl._originalName = pollsChoiceModelImpl._name;

		pollsChoiceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PollsChoice> toCacheModel() {
		PollsChoiceCacheModel pollsChoiceCacheModel =
			new PollsChoiceCacheModel();

		pollsChoiceCacheModel.uuid = getUuid();

		String uuid = pollsChoiceCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			pollsChoiceCacheModel.uuid = null;
		}

		pollsChoiceCacheModel.choiceId = getChoiceId();

		pollsChoiceCacheModel.groupId = getGroupId();

		pollsChoiceCacheModel.companyId = getCompanyId();

		pollsChoiceCacheModel.userId = getUserId();

		pollsChoiceCacheModel.userName = getUserName();

		String userName = pollsChoiceCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			pollsChoiceCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			pollsChoiceCacheModel.createDate = createDate.getTime();
		}
		else {
			pollsChoiceCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			pollsChoiceCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			pollsChoiceCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		pollsChoiceCacheModel.questionId = getQuestionId();

		pollsChoiceCacheModel.name = getName();

		String name = pollsChoiceCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			pollsChoiceCacheModel.name = null;
		}

		pollsChoiceCacheModel.description = getDescription();

		String description = pollsChoiceCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			pollsChoiceCacheModel.description = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			pollsChoiceCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			pollsChoiceCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return pollsChoiceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PollsChoice, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PollsChoice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PollsChoice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((PollsChoice)this));
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
		Map<String, Function<PollsChoice, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PollsChoice, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PollsChoice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((PollsChoice)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, PollsChoice>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _choiceId;
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
	private long _questionId;
	private long _originalQuestionId;
	private boolean _setOriginalQuestionId;
	private String _name;
	private String _originalName;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private PollsChoice _escapedModel;

}