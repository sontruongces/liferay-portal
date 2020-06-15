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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.LayoutSetModel;
import com.liferay.portal.kernel.model.LayoutSetSoap;
import com.liferay.portal.kernel.model.LayoutSetVersion;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

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
 * The base model implementation for the LayoutSet service. Represents a row in the &quot;LayoutSet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LayoutSetModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutSetImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetImpl
 * @generated
 */
@JSON(strict = true)
public class LayoutSetModelImpl
	extends BaseModelImpl<LayoutSet> implements LayoutSetModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout set model instance should use the <code>LayoutSet</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutSet";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"headId", Types.BIGINT},
		{"head", Types.BOOLEAN}, {"layoutSetId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"privateLayout", Types.BOOLEAN}, {"logoId", Types.BIGINT},
		{"themeId", Types.VARCHAR}, {"colorSchemeId", Types.VARCHAR},
		{"css", Types.CLOB}, {"pageCount", Types.INTEGER},
		{"settings_", Types.CLOB}, {"layoutSetPrototypeUuid", Types.VARCHAR},
		{"layoutSetPrototypeLinkEnabled", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("headId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("head", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("layoutSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("privateLayout", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("logoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("themeId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("colorSchemeId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("css", Types.CLOB);
		TABLE_COLUMNS_MAP.put("pageCount", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("settings_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("layoutSetPrototypeUuid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutSetPrototypeLinkEnabled", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutSet (mvccVersion LONG default 0 not null,headId LONG,head BOOLEAN,layoutSetId LONG not null primary key,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,privateLayout BOOLEAN,logoId LONG,themeId VARCHAR(75) null,colorSchemeId VARCHAR(75) null,css TEXT null,pageCount INTEGER,settings_ TEXT null,layoutSetPrototypeUuid VARCHAR(75) null,layoutSetPrototypeLinkEnabled BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table LayoutSet";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutSet.layoutSetId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutSet.layoutSetId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.LayoutSet"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.LayoutSet"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.LayoutSet"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long HEAD_COLUMN_BITMASK = 4L;

	public static final long HEADID_COLUMN_BITMASK = 8L;

	public static final long LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK = 16L;

	public static final long LOGOID_COLUMN_BITMASK = 32L;

	public static final long PRIVATELAYOUT_COLUMN_BITMASK = 64L;

	public static final long LAYOUTSETID_COLUMN_BITMASK = 128L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutSet toModel(LayoutSetSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutSet model = new LayoutSetImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setHeadId(soapModel.getHeadId());
		model.setLayoutSetId(soapModel.getLayoutSetId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setPrivateLayout(soapModel.isPrivateLayout());
		model.setLogoId(soapModel.getLogoId());
		model.setThemeId(soapModel.getThemeId());
		model.setColorSchemeId(soapModel.getColorSchemeId());
		model.setCss(soapModel.getCss());
		model.setPageCount(soapModel.getPageCount());
		model.setSettings(soapModel.getSettings());
		model.setLayoutSetPrototypeUuid(soapModel.getLayoutSetPrototypeUuid());
		model.setLayoutSetPrototypeLinkEnabled(
			soapModel.isLayoutSetPrototypeLinkEnabled());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutSet> toModels(LayoutSetSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<LayoutSet> models = new ArrayList<LayoutSet>(soapModels.length);

		for (LayoutSetSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.LayoutSet"));

	public LayoutSetModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutSetId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutSetId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutSetId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutSet.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutSet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutSet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSet, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((LayoutSet)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutSet, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutSet, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutSet)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutSet, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutSet, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutSet>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutSet.class.getClassLoader(), LayoutSet.class,
			ModelWrapper.class);

		try {
			Constructor<LayoutSet> constructor =
				(Constructor<LayoutSet>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LayoutSet, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutSet, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<LayoutSet, Object>>();
		Map<String, BiConsumer<LayoutSet, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LayoutSet, ?>>();

		attributeGetterFunctions.put("mvccVersion", LayoutSet::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<LayoutSet, Long>)LayoutSet::setMvccVersion);
		attributeGetterFunctions.put("headId", LayoutSet::getHeadId);
		attributeSetterBiConsumers.put(
			"headId", (BiConsumer<LayoutSet, Long>)LayoutSet::setHeadId);
		attributeGetterFunctions.put("layoutSetId", LayoutSet::getLayoutSetId);
		attributeSetterBiConsumers.put(
			"layoutSetId",
			(BiConsumer<LayoutSet, Long>)LayoutSet::setLayoutSetId);
		attributeGetterFunctions.put("groupId", LayoutSet::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId", (BiConsumer<LayoutSet, Long>)LayoutSet::setGroupId);
		attributeGetterFunctions.put("companyId", LayoutSet::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<LayoutSet, Long>)LayoutSet::setCompanyId);
		attributeGetterFunctions.put("createDate", LayoutSet::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<LayoutSet, Date>)LayoutSet::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", LayoutSet::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<LayoutSet, Date>)LayoutSet::setModifiedDate);
		attributeGetterFunctions.put(
			"privateLayout", LayoutSet::getPrivateLayout);
		attributeSetterBiConsumers.put(
			"privateLayout",
			(BiConsumer<LayoutSet, Boolean>)LayoutSet::setPrivateLayout);
		attributeGetterFunctions.put("logoId", LayoutSet::getLogoId);
		attributeSetterBiConsumers.put(
			"logoId", (BiConsumer<LayoutSet, Long>)LayoutSet::setLogoId);
		attributeGetterFunctions.put("themeId", LayoutSet::getThemeId);
		attributeSetterBiConsumers.put(
			"themeId", (BiConsumer<LayoutSet, String>)LayoutSet::setThemeId);
		attributeGetterFunctions.put(
			"colorSchemeId", LayoutSet::getColorSchemeId);
		attributeSetterBiConsumers.put(
			"colorSchemeId",
			(BiConsumer<LayoutSet, String>)LayoutSet::setColorSchemeId);
		attributeGetterFunctions.put("css", LayoutSet::getCss);
		attributeSetterBiConsumers.put(
			"css", (BiConsumer<LayoutSet, String>)LayoutSet::setCss);
		attributeGetterFunctions.put("pageCount", LayoutSet::getPageCount);
		attributeSetterBiConsumers.put(
			"pageCount",
			(BiConsumer<LayoutSet, Integer>)LayoutSet::setPageCount);
		attributeGetterFunctions.put("settings", LayoutSet::getSettings);
		attributeSetterBiConsumers.put(
			"settings", (BiConsumer<LayoutSet, String>)LayoutSet::setSettings);
		attributeGetterFunctions.put(
			"layoutSetPrototypeUuid", LayoutSet::getLayoutSetPrototypeUuid);
		attributeSetterBiConsumers.put(
			"layoutSetPrototypeUuid",
			(BiConsumer<LayoutSet, String>)
				LayoutSet::setLayoutSetPrototypeUuid);
		attributeGetterFunctions.put(
			"layoutSetPrototypeLinkEnabled",
			LayoutSet::getLayoutSetPrototypeLinkEnabled);
		attributeSetterBiConsumers.put(
			"layoutSetPrototypeLinkEnabled",
			(BiConsumer<LayoutSet, Boolean>)
				LayoutSet::setLayoutSetPrototypeLinkEnabled);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	public boolean getHead() {
		return _head;
	}

	@Override
	public boolean isHead() {
		return _head;
	}

	public boolean getOriginalHead() {
		return _originalHead;
	}

	public void setHead(boolean head) {
		_columnBitmask |= HEAD_COLUMN_BITMASK;

		if (!_setOriginalHead) {
			_setOriginalHead = true;

			_originalHead = _head;
		}

		_head = head;
	}

	@Override
	public void populateVersionModel(LayoutSetVersion layoutSetVersion) {
		layoutSetVersion.setGroupId(getGroupId());
		layoutSetVersion.setCompanyId(getCompanyId());
		layoutSetVersion.setCreateDate(getCreateDate());
		layoutSetVersion.setModifiedDate(getModifiedDate());
		layoutSetVersion.setPrivateLayout(getPrivateLayout());
		layoutSetVersion.setLogoId(getLogoId());
		layoutSetVersion.setThemeId(getThemeId());
		layoutSetVersion.setColorSchemeId(getColorSchemeId());
		layoutSetVersion.setCss(getCss());
		layoutSetVersion.setPageCount(getPageCount());
		layoutSetVersion.setSettings(getSettings());
		layoutSetVersion.setLayoutSetPrototypeUuid(getLayoutSetPrototypeUuid());
		layoutSetVersion.setLayoutSetPrototypeLinkEnabled(
			getLayoutSetPrototypeLinkEnabled());
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
	public long getHeadId() {
		return _headId;
	}

	@Override
	public void setHeadId(long headId) {
		_columnBitmask |= HEADID_COLUMN_BITMASK;

		if (!_setOriginalHeadId) {
			_setOriginalHeadId = true;

			_originalHeadId = _headId;
		}

		if (headId >= 0) {
			setHead(false);
		}
		else {
			setHead(true);
		}

		_headId = headId;
	}

	public long getOriginalHeadId() {
		return _originalHeadId;
	}

	@JSON
	@Override
	public long getLayoutSetId() {
		return _layoutSetId;
	}

	@Override
	public void setLayoutSetId(long layoutSetId) {
		_layoutSetId = layoutSetId;
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
	public boolean getPrivateLayout() {
		return _privateLayout;
	}

	@JSON
	@Override
	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		_columnBitmask |= PRIVATELAYOUT_COLUMN_BITMASK;

		if (!_setOriginalPrivateLayout) {
			_setOriginalPrivateLayout = true;

			_originalPrivateLayout = _privateLayout;
		}

		_privateLayout = privateLayout;
	}

	public boolean getOriginalPrivateLayout() {
		return _originalPrivateLayout;
	}

	@JSON
	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		_columnBitmask |= LOGOID_COLUMN_BITMASK;

		if (!_setOriginalLogoId) {
			_setOriginalLogoId = true;

			_originalLogoId = _logoId;
		}

		_logoId = logoId;
	}

	public long getOriginalLogoId() {
		return _originalLogoId;
	}

	@JSON
	@Override
	public String getThemeId() {
		if (_themeId == null) {
			return "";
		}
		else {
			return _themeId;
		}
	}

	@Override
	public void setThemeId(String themeId) {
		_themeId = themeId;
	}

	@JSON
	@Override
	public String getColorSchemeId() {
		if (_colorSchemeId == null) {
			return "";
		}
		else {
			return _colorSchemeId;
		}
	}

	@Override
	public void setColorSchemeId(String colorSchemeId) {
		_colorSchemeId = colorSchemeId;
	}

	@JSON
	@Override
	public String getCss() {
		if (_css == null) {
			return "";
		}
		else {
			return _css;
		}
	}

	@Override
	public void setCss(String css) {
		_css = css;
	}

	@JSON
	@Override
	public int getPageCount() {
		return _pageCount;
	}

	@Override
	public void setPageCount(int pageCount) {
		_pageCount = pageCount;
	}

	@JSON
	@Override
	public String getSettings() {
		if (_settings == null) {
			return "";
		}
		else {
			return _settings;
		}
	}

	@Override
	public void setSettings(String settings) {
		_settings = settings;
	}

	@JSON
	@Override
	public String getLayoutSetPrototypeUuid() {
		if (_layoutSetPrototypeUuid == null) {
			return "";
		}
		else {
			return _layoutSetPrototypeUuid;
		}
	}

	@Override
	public void setLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		_columnBitmask |= LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK;

		if (_originalLayoutSetPrototypeUuid == null) {
			_originalLayoutSetPrototypeUuid = _layoutSetPrototypeUuid;
		}

		_layoutSetPrototypeUuid = layoutSetPrototypeUuid;
	}

	public String getOriginalLayoutSetPrototypeUuid() {
		return GetterUtil.getString(_originalLayoutSetPrototypeUuid);
	}

	@JSON
	@Override
	public boolean getLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	@JSON
	@Override
	public boolean isLayoutSetPrototypeLinkEnabled() {
		return _layoutSetPrototypeLinkEnabled;
	}

	@Override
	public void setLayoutSetPrototypeLinkEnabled(
		boolean layoutSetPrototypeLinkEnabled) {

		_layoutSetPrototypeLinkEnabled = layoutSetPrototypeLinkEnabled;
	}

	public String getCompanyFallbackVirtualHostname() {
		return null;
	}

	public void setCompanyFallbackVirtualHostname(
		String companyFallbackVirtualHostname) {
	}

	public java.util.TreeMap getVirtualHostnames() {
		return null;
	}

	public void setVirtualHostnames(java.util.TreeMap virtualHostnames) {
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutSet.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutSet toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutSet>
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
		LayoutSetImpl layoutSetImpl = new LayoutSetImpl();

		layoutSetImpl.setMvccVersion(getMvccVersion());
		layoutSetImpl.setHeadId(getHeadId());
		layoutSetImpl.setLayoutSetId(getLayoutSetId());
		layoutSetImpl.setGroupId(getGroupId());
		layoutSetImpl.setCompanyId(getCompanyId());
		layoutSetImpl.setCreateDate(getCreateDate());
		layoutSetImpl.setModifiedDate(getModifiedDate());
		layoutSetImpl.setPrivateLayout(isPrivateLayout());
		layoutSetImpl.setLogoId(getLogoId());
		layoutSetImpl.setThemeId(getThemeId());
		layoutSetImpl.setColorSchemeId(getColorSchemeId());
		layoutSetImpl.setCss(getCss());
		layoutSetImpl.setPageCount(getPageCount());
		layoutSetImpl.setSettings(getSettings());
		layoutSetImpl.setLayoutSetPrototypeUuid(getLayoutSetPrototypeUuid());
		layoutSetImpl.setLayoutSetPrototypeLinkEnabled(
			isLayoutSetPrototypeLinkEnabled());

		layoutSetImpl.resetOriginalValues();

		return layoutSetImpl;
	}

	@Override
	public int compareTo(LayoutSet layoutSet) {
		long primaryKey = layoutSet.getPrimaryKey();

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

		if (!(object instanceof LayoutSet)) {
			return false;
		}

		LayoutSet layoutSet = (LayoutSet)object;

		long primaryKey = layoutSet.getPrimaryKey();

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
		LayoutSetModelImpl layoutSetModelImpl = this;

		layoutSetModelImpl._originalHeadId = layoutSetModelImpl._headId;

		layoutSetModelImpl._setOriginalHeadId = false;

		layoutSetModelImpl._originalHead = layoutSetModelImpl._head;

		layoutSetModelImpl._setOriginalHead = false;

		layoutSetModelImpl._originalGroupId = layoutSetModelImpl._groupId;

		layoutSetModelImpl._setOriginalGroupId = false;

		layoutSetModelImpl._originalCompanyId = layoutSetModelImpl._companyId;

		layoutSetModelImpl._setOriginalCompanyId = false;

		layoutSetModelImpl._setModifiedDate = false;

		layoutSetModelImpl._originalPrivateLayout =
			layoutSetModelImpl._privateLayout;

		layoutSetModelImpl._setOriginalPrivateLayout = false;

		layoutSetModelImpl._originalLogoId = layoutSetModelImpl._logoId;

		layoutSetModelImpl._setOriginalLogoId = false;

		layoutSetModelImpl._originalLayoutSetPrototypeUuid =
			layoutSetModelImpl._layoutSetPrototypeUuid;

		setCompanyFallbackVirtualHostname(null);

		setVirtualHostnames(null);

		layoutSetModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutSet> toCacheModel() {
		LayoutSetCacheModel layoutSetCacheModel = new LayoutSetCacheModel();

		layoutSetCacheModel.mvccVersion = getMvccVersion();

		layoutSetCacheModel.headId = getHeadId();

		layoutSetCacheModel.head = isHead();

		layoutSetCacheModel.layoutSetId = getLayoutSetId();

		layoutSetCacheModel.groupId = getGroupId();

		layoutSetCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutSetCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutSetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutSetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutSetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutSetCacheModel.privateLayout = isPrivateLayout();

		layoutSetCacheModel.logoId = getLogoId();

		layoutSetCacheModel.themeId = getThemeId();

		String themeId = layoutSetCacheModel.themeId;

		if ((themeId != null) && (themeId.length() == 0)) {
			layoutSetCacheModel.themeId = null;
		}

		layoutSetCacheModel.colorSchemeId = getColorSchemeId();

		String colorSchemeId = layoutSetCacheModel.colorSchemeId;

		if ((colorSchemeId != null) && (colorSchemeId.length() == 0)) {
			layoutSetCacheModel.colorSchemeId = null;
		}

		layoutSetCacheModel.css = getCss();

		String css = layoutSetCacheModel.css;

		if ((css != null) && (css.length() == 0)) {
			layoutSetCacheModel.css = null;
		}

		layoutSetCacheModel.pageCount = getPageCount();

		layoutSetCacheModel.settings = getSettings();

		String settings = layoutSetCacheModel.settings;

		if ((settings != null) && (settings.length() == 0)) {
			layoutSetCacheModel.settings = null;
		}

		layoutSetCacheModel.layoutSetPrototypeUuid =
			getLayoutSetPrototypeUuid();

		String layoutSetPrototypeUuid =
			layoutSetCacheModel.layoutSetPrototypeUuid;

		if ((layoutSetPrototypeUuid != null) &&
			(layoutSetPrototypeUuid.length() == 0)) {

			layoutSetCacheModel.layoutSetPrototypeUuid = null;
		}

		layoutSetCacheModel.layoutSetPrototypeLinkEnabled =
			isLayoutSetPrototypeLinkEnabled();

		layoutSetCacheModel._companyFallbackVirtualHostname =
			getCompanyFallbackVirtualHostname();

		layoutSetCacheModel._virtualHostnames = getVirtualHostnames();

		return layoutSetCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutSet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LayoutSet)this));
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
		Map<String, Function<LayoutSet, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutSet, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutSet, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LayoutSet)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, LayoutSet>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _headId;
	private long _originalHeadId;
	private boolean _setOriginalHeadId;
	private boolean _head;
	private boolean _originalHead;
	private boolean _setOriginalHead;
	private long _layoutSetId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private boolean _privateLayout;
	private boolean _originalPrivateLayout;
	private boolean _setOriginalPrivateLayout;
	private long _logoId;
	private long _originalLogoId;
	private boolean _setOriginalLogoId;
	private String _themeId;
	private String _colorSchemeId;
	private String _css;
	private int _pageCount;
	private String _settings;
	private String _layoutSetPrototypeUuid;
	private String _originalLayoutSetPrototypeUuid;
	private boolean _layoutSetPrototypeLinkEnabled;
	private long _columnBitmask;
	private LayoutSet _escapedModel;

}