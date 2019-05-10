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

package com.liferay.portlet.asset.model.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetCategorySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the AssetCategory service. Represents a row in the &quot;AssetCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>AssetCategoryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetCategoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class AssetCategoryModelImpl
	extends BaseModelImpl<AssetCategory> implements AssetCategoryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset category model instance should use the <code>AssetCategory</code> interface instead.
	 */
	public static final String TABLE_NAME = "AssetCategory";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"categoryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"parentCategoryId", Types.BIGINT},
		{"leftCategoryId", Types.BIGINT}, {"rightCategoryId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"title", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"vocabularyId", Types.BIGINT},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("categoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentCategoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("leftCategoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("rightCategoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("vocabularyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AssetCategory (uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,categoryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentCategoryId LONG,leftCategoryId LONG,rightCategoryId LONG,name VARCHAR(75) null,title STRING null,description STRING null,vocabularyId LONG,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table AssetCategory";

	public static final String ORDER_BY_JPQL =
		" ORDER BY assetCategory.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AssetCategory.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.asset.kernel.model.AssetCategory"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.asset.kernel.model.AssetCategory"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.asset.kernel.model.AssetCategory"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long NAME_COLUMN_BITMASK = 8L;

	public static final long PARENTCATEGORYID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long VOCABULARYID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static AssetCategory toModel(AssetCategorySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		AssetCategory model = new AssetCategoryImpl();

		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCategoryId(soapModel.getCategoryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentCategoryId(soapModel.getParentCategoryId());
		model.setLeftCategoryId(soapModel.getLeftCategoryId());
		model.setRightCategoryId(soapModel.getRightCategoryId());
		model.setName(soapModel.getName());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setVocabularyId(soapModel.getVocabularyId());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AssetCategory> toModels(AssetCategorySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<AssetCategory> models = new ArrayList<AssetCategory>(
			soapModels.length);

		for (AssetCategorySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final String MAPPING_TABLE_ASSETENTRIES_ASSETCATEGORIES_NAME =
		"AssetEntries_AssetCategories";

	public static final Object[][]
		MAPPING_TABLE_ASSETENTRIES_ASSETCATEGORIES_COLUMNS = {
			{"companyId", Types.BIGINT}, {"categoryId", Types.BIGINT},
			{"entryId", Types.BIGINT}
		};

	public static final String
		MAPPING_TABLE_ASSETENTRIES_ASSETCATEGORIES_SQL_CREATE =
			"create table AssetEntries_AssetCategories (companyId LONG not null,categoryId LONG not null,entryId LONG not null,primary key (categoryId, entryId))";

	public static final boolean
		FINDER_CACHE_ENABLED_ASSETENTRIES_ASSETCATEGORIES =
			GetterUtil.getBoolean(
				com.liferay.portal.util.PropsUtil.get(
					"value.object.finder.cache.enabled.AssetEntries_AssetCategories"),
				true);

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.asset.kernel.model.AssetCategory"));

	public AssetCategoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _categoryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCategoryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _categoryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetCategory.class;
	}

	@Override
	public String getModelClassName() {
		return AssetCategory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AssetCategory, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AssetCategory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetCategory, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AssetCategory)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AssetCategory, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AssetCategory, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AssetCategory)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AssetCategory, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AssetCategory, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<AssetCategory, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AssetCategory, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AssetCategory, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<AssetCategory, Object>>();
		Map<String, BiConsumer<AssetCategory, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<AssetCategory, ?>>();

		attributeGetterFunctions.put("uuid", AssetCategory::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<AssetCategory, String>)AssetCategory::setUuid);
		attributeGetterFunctions.put(
			"externalReferenceCode", AssetCategory::getExternalReferenceCode);
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			(BiConsumer<AssetCategory, String>)
				AssetCategory::setExternalReferenceCode);
		attributeGetterFunctions.put(
			"categoryId", AssetCategory::getCategoryId);
		attributeSetterBiConsumers.put(
			"categoryId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setCategoryId);
		attributeGetterFunctions.put("groupId", AssetCategory::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setGroupId);
		attributeGetterFunctions.put("companyId", AssetCategory::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setCompanyId);
		attributeGetterFunctions.put("userId", AssetCategory::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setUserId);
		attributeGetterFunctions.put("userName", AssetCategory::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<AssetCategory, String>)AssetCategory::setUserName);
		attributeGetterFunctions.put(
			"createDate", AssetCategory::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<AssetCategory, Date>)AssetCategory::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", AssetCategory::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<AssetCategory, Date>)AssetCategory::setModifiedDate);
		attributeGetterFunctions.put(
			"parentCategoryId", AssetCategory::getParentCategoryId);
		attributeSetterBiConsumers.put(
			"parentCategoryId",
			(BiConsumer<AssetCategory, Long>)
				AssetCategory::setParentCategoryId);
		attributeGetterFunctions.put(
			"leftCategoryId", AssetCategory::getLeftCategoryId);
		attributeSetterBiConsumers.put(
			"leftCategoryId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setLeftCategoryId);
		attributeGetterFunctions.put(
			"rightCategoryId", AssetCategory::getRightCategoryId);
		attributeSetterBiConsumers.put(
			"rightCategoryId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setRightCategoryId);
		attributeGetterFunctions.put("name", AssetCategory::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<AssetCategory, String>)AssetCategory::setName);
		attributeGetterFunctions.put("title", AssetCategory::getTitle);
		attributeSetterBiConsumers.put(
			"title",
			(BiConsumer<AssetCategory, String>)AssetCategory::setTitle);
		attributeGetterFunctions.put(
			"description", AssetCategory::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<AssetCategory, String>)AssetCategory::setDescription);
		attributeGetterFunctions.put(
			"vocabularyId", AssetCategory::getVocabularyId);
		attributeSetterBiConsumers.put(
			"vocabularyId",
			(BiConsumer<AssetCategory, Long>)AssetCategory::setVocabularyId);
		attributeGetterFunctions.put(
			"lastPublishDate", AssetCategory::getLastPublishDate);
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			(BiConsumer<AssetCategory, Date>)AssetCategory::setLastPublishDate);

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
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
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
	public long getParentCategoryId() {
		return _parentCategoryId;
	}

	@Override
	public void setParentCategoryId(long parentCategoryId) {
		_columnBitmask |= PARENTCATEGORYID_COLUMN_BITMASK;

		if (!_setOriginalParentCategoryId) {
			_setOriginalParentCategoryId = true;

			_originalParentCategoryId = _parentCategoryId;
		}

		_parentCategoryId = parentCategoryId;
	}

	public long getOriginalParentCategoryId() {
		return _originalParentCategoryId;
	}

	@JSON
	@Override
	public long getLeftCategoryId() {
		return _leftCategoryId;
	}

	@Override
	public void setLeftCategoryId(long leftCategoryId) {
		_leftCategoryId = leftCategoryId;
	}

	@JSON
	@Override
	public long getRightCategoryId() {
		return _rightCategoryId;
	}

	@Override
	public void setRightCategoryId(long rightCategoryId) {
		_rightCategoryId = rightCategoryId;
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
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getTitle(), languageId, useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(
				LocalizationUtil.updateLocalization(
					getTitle(), "Title", title, languageId, defaultLanguageId));
		}
		else {
			setTitle(
				LocalizationUtil.removeLocalization(
					getTitle(), "Title", languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getSiteDefault());
	}

	@Override
	public void setTitleMap(
		Map<Locale, String> titleMap, Locale defaultLocale) {

		if (titleMap == null) {
			return;
		}

		setTitle(
			LocalizationUtil.updateLocalization(
				titleMap, getTitle(), "Title",
				LocaleUtil.toLanguageId(defaultLocale)));
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
	public long getVocabularyId() {
		return _vocabularyId;
	}

	@Override
	public void setVocabularyId(long vocabularyId) {
		_columnBitmask |= VOCABULARYID_COLUMN_BITMASK;

		if (!_setOriginalVocabularyId) {
			_setOriginalVocabularyId = true;

			_originalVocabularyId = _vocabularyId;
		}

		_vocabularyId = vocabularyId;
	}

	public long getOriginalVocabularyId() {
		return _originalVocabularyId;
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

	public long getNestedSetsTreeNodeLeft() {
		return _leftCategoryId;
	}

	public long getNestedSetsTreeNodeRight() {
		return _rightCategoryId;
	}

	public long getNestedSetsTreeNodeScopeId() {
		return _groupId;
	}

	public void setNestedSetsTreeNodeLeft(long nestedSetsTreeNodeLeft) {
		_leftCategoryId = nestedSetsTreeNodeLeft;
	}

	public void setNestedSetsTreeNodeRight(long nestedSetsTreeNodeRight) {
		_rightCategoryId = nestedSetsTreeNodeRight;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AssetCategory.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AssetCategory.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

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
		String xml = getTitle();

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
			AssetCategory.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getSiteDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}

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
	public AssetCategory toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AssetCategory)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AssetCategoryImpl assetCategoryImpl = new AssetCategoryImpl();

		assetCategoryImpl.setUuid(getUuid());
		assetCategoryImpl.setExternalReferenceCode(getExternalReferenceCode());
		assetCategoryImpl.setCategoryId(getCategoryId());
		assetCategoryImpl.setGroupId(getGroupId());
		assetCategoryImpl.setCompanyId(getCompanyId());
		assetCategoryImpl.setUserId(getUserId());
		assetCategoryImpl.setUserName(getUserName());
		assetCategoryImpl.setCreateDate(getCreateDate());
		assetCategoryImpl.setModifiedDate(getModifiedDate());
		assetCategoryImpl.setParentCategoryId(getParentCategoryId());
		assetCategoryImpl.setLeftCategoryId(getLeftCategoryId());
		assetCategoryImpl.setRightCategoryId(getRightCategoryId());
		assetCategoryImpl.setName(getName());
		assetCategoryImpl.setTitle(getTitle());
		assetCategoryImpl.setDescription(getDescription());
		assetCategoryImpl.setVocabularyId(getVocabularyId());
		assetCategoryImpl.setLastPublishDate(getLastPublishDate());

		assetCategoryImpl.resetOriginalValues();

		return assetCategoryImpl;
	}

	@Override
	public int compareTo(AssetCategory assetCategory) {
		int value = 0;

		value = getName().compareTo(assetCategory.getName());

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

		if (!(obj instanceof AssetCategory)) {
			return false;
		}

		AssetCategory assetCategory = (AssetCategory)obj;

		long primaryKey = assetCategory.getPrimaryKey();

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
		AssetCategoryModelImpl assetCategoryModelImpl = this;

		assetCategoryModelImpl._originalUuid = assetCategoryModelImpl._uuid;

		assetCategoryModelImpl._originalExternalReferenceCode =
			assetCategoryModelImpl._externalReferenceCode;

		assetCategoryModelImpl._originalGroupId =
			assetCategoryModelImpl._groupId;

		assetCategoryModelImpl._setOriginalGroupId = false;

		assetCategoryModelImpl._originalCompanyId =
			assetCategoryModelImpl._companyId;

		assetCategoryModelImpl._setOriginalCompanyId = false;

		assetCategoryModelImpl._setModifiedDate = false;

		assetCategoryModelImpl._originalParentCategoryId =
			assetCategoryModelImpl._parentCategoryId;

		assetCategoryModelImpl._setOriginalParentCategoryId = false;

		assetCategoryModelImpl._originalName = assetCategoryModelImpl._name;

		assetCategoryModelImpl._originalVocabularyId =
			assetCategoryModelImpl._vocabularyId;

		assetCategoryModelImpl._setOriginalVocabularyId = false;

		assetCategoryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AssetCategory> toCacheModel() {
		AssetCategoryCacheModel assetCategoryCacheModel =
			new AssetCategoryCacheModel();

		assetCategoryCacheModel.uuid = getUuid();

		String uuid = assetCategoryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetCategoryCacheModel.uuid = null;
		}

		assetCategoryCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			assetCategoryCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			assetCategoryCacheModel.externalReferenceCode = null;
		}

		assetCategoryCacheModel.categoryId = getCategoryId();

		assetCategoryCacheModel.groupId = getGroupId();

		assetCategoryCacheModel.companyId = getCompanyId();

		assetCategoryCacheModel.userId = getUserId();

		assetCategoryCacheModel.userName = getUserName();

		String userName = assetCategoryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetCategoryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetCategoryCacheModel.createDate = createDate.getTime();
		}
		else {
			assetCategoryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetCategoryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			assetCategoryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetCategoryCacheModel.parentCategoryId = getParentCategoryId();

		assetCategoryCacheModel.leftCategoryId = getLeftCategoryId();

		assetCategoryCacheModel.rightCategoryId = getRightCategoryId();

		assetCategoryCacheModel.name = getName();

		String name = assetCategoryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			assetCategoryCacheModel.name = null;
		}

		assetCategoryCacheModel.title = getTitle();

		String title = assetCategoryCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			assetCategoryCacheModel.title = null;
		}

		assetCategoryCacheModel.description = getDescription();

		String description = assetCategoryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			assetCategoryCacheModel.description = null;
		}

		assetCategoryCacheModel.vocabularyId = getVocabularyId();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			assetCategoryCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			assetCategoryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return assetCategoryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AssetCategory, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AssetCategory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetCategory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((AssetCategory)this));
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
		Map<String, Function<AssetCategory, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AssetCategory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetCategory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((AssetCategory)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		AssetCategory.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		AssetCategory.class, ModelWrapper.class
	};

	private String _uuid;
	private String _originalUuid;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _categoryId;
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
	private long _parentCategoryId;
	private long _originalParentCategoryId;
	private boolean _setOriginalParentCategoryId;
	private long _leftCategoryId;
	private long _rightCategoryId;
	private String _name;
	private String _originalName;
	private String _title;
	private String _titleCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private long _vocabularyId;
	private long _originalVocabularyId;
	private boolean _setOriginalVocabularyId;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private AssetCategory _escapedModel;

}