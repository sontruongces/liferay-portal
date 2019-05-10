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
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.ResourceBlock;
import com.liferay.portal.kernel.model.ResourceBlockModel;
import com.liferay.portal.kernel.model.ResourceBlockSoap;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the ResourceBlock service. Represents a row in the &quot;ResourceBlock&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ResourceBlockModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ResourceBlockImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockImpl
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@JSON(strict = true)
@ProviderType
public class ResourceBlockModelImpl
	extends BaseModelImpl<ResourceBlock> implements ResourceBlockModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a resource block model instance should use the <code>ResourceBlock</code> interface instead.
	 */
	public static final String TABLE_NAME = "ResourceBlock";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"resourceBlockId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"permissionsHash", Types.VARCHAR},
		{"referenceCount", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("resourceBlockId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("permissionsHash", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("referenceCount", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ResourceBlock (mvccVersion LONG default 0 not null,resourceBlockId LONG not null primary key,companyId LONG,groupId LONG,name VARCHAR(75) null,permissionsHash VARCHAR(75) null,referenceCount LONG)";

	public static final String TABLE_SQL_DROP = "drop table ResourceBlock";

	public static final String ORDER_BY_JPQL =
		" ORDER BY resourceBlock.resourceBlockId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ResourceBlock.resourceBlockId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.ResourceBlock"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.ResourceBlock"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.ResourceBlock"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long NAME_COLUMN_BITMASK = 4L;

	public static final long PERMISSIONSHASH_COLUMN_BITMASK = 8L;

	public static final long RESOURCEBLOCKID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ResourceBlock toModel(ResourceBlockSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ResourceBlock model = new ResourceBlockImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setResourceBlockId(soapModel.getResourceBlockId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setGroupId(soapModel.getGroupId());
		model.setName(soapModel.getName());
		model.setPermissionsHash(soapModel.getPermissionsHash());
		model.setReferenceCount(soapModel.getReferenceCount());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ResourceBlock> toModels(ResourceBlockSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ResourceBlock> models = new ArrayList<ResourceBlock>(
			soapModels.length);

		for (ResourceBlockSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.ResourceBlock"));

	public ResourceBlockModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _resourceBlockId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setResourceBlockId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _resourceBlockId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ResourceBlock.class;
	}

	@Override
	public String getModelClassName() {
		return ResourceBlock.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ResourceBlock, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ResourceBlock, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ResourceBlock, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ResourceBlock)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ResourceBlock, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ResourceBlock, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ResourceBlock)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ResourceBlock, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ResourceBlock, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ResourceBlock, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ResourceBlock, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ResourceBlock, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ResourceBlock, Object>>();
		Map<String, BiConsumer<ResourceBlock, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ResourceBlock, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", ResourceBlock::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<ResourceBlock, Long>)ResourceBlock::setMvccVersion);
		attributeGetterFunctions.put(
			"resourceBlockId", ResourceBlock::getResourceBlockId);
		attributeSetterBiConsumers.put(
			"resourceBlockId",
			(BiConsumer<ResourceBlock, Long>)ResourceBlock::setResourceBlockId);
		attributeGetterFunctions.put("companyId", ResourceBlock::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<ResourceBlock, Long>)ResourceBlock::setCompanyId);
		attributeGetterFunctions.put("groupId", ResourceBlock::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<ResourceBlock, Long>)ResourceBlock::setGroupId);
		attributeGetterFunctions.put("name", ResourceBlock::getName);
		attributeSetterBiConsumers.put(
			"name", (BiConsumer<ResourceBlock, String>)ResourceBlock::setName);
		attributeGetterFunctions.put(
			"permissionsHash", ResourceBlock::getPermissionsHash);
		attributeSetterBiConsumers.put(
			"permissionsHash",
			(BiConsumer<ResourceBlock, String>)
				ResourceBlock::setPermissionsHash);
		attributeGetterFunctions.put(
			"referenceCount", ResourceBlock::getReferenceCount);
		attributeSetterBiConsumers.put(
			"referenceCount",
			(BiConsumer<ResourceBlock, Long>)ResourceBlock::setReferenceCount);

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
	public long getResourceBlockId() {
		return _resourceBlockId;
	}

	@Override
	public void setResourceBlockId(long resourceBlockId) {
		_resourceBlockId = resourceBlockId;
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
		_columnBitmask |= NAME_COLUMN_BITMASK;

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
	public String getPermissionsHash() {
		if (_permissionsHash == null) {
			return "";
		}
		else {
			return _permissionsHash;
		}
	}

	@Override
	public void setPermissionsHash(String permissionsHash) {
		_columnBitmask |= PERMISSIONSHASH_COLUMN_BITMASK;

		if (_originalPermissionsHash == null) {
			_originalPermissionsHash = _permissionsHash;
		}

		_permissionsHash = permissionsHash;
	}

	public String getOriginalPermissionsHash() {
		return GetterUtil.getString(_originalPermissionsHash);
	}

	@JSON
	@Override
	public long getReferenceCount() {
		return _referenceCount;
	}

	@Override
	public void setReferenceCount(long referenceCount) {
		_referenceCount = referenceCount;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ResourceBlock.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ResourceBlock toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ResourceBlock)ProxyUtil.newProxyInstance(
				_classLoader, _escapedModelInterfaces,
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ResourceBlockImpl resourceBlockImpl = new ResourceBlockImpl();

		resourceBlockImpl.setMvccVersion(getMvccVersion());
		resourceBlockImpl.setResourceBlockId(getResourceBlockId());
		resourceBlockImpl.setCompanyId(getCompanyId());
		resourceBlockImpl.setGroupId(getGroupId());
		resourceBlockImpl.setName(getName());
		resourceBlockImpl.setPermissionsHash(getPermissionsHash());
		resourceBlockImpl.setReferenceCount(getReferenceCount());

		resourceBlockImpl.resetOriginalValues();

		return resourceBlockImpl;
	}

	@Override
	public int compareTo(ResourceBlock resourceBlock) {
		long primaryKey = resourceBlock.getPrimaryKey();

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

		if (!(obj instanceof ResourceBlock)) {
			return false;
		}

		ResourceBlock resourceBlock = (ResourceBlock)obj;

		long primaryKey = resourceBlock.getPrimaryKey();

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
		ResourceBlockModelImpl resourceBlockModelImpl = this;

		resourceBlockModelImpl._originalCompanyId =
			resourceBlockModelImpl._companyId;

		resourceBlockModelImpl._setOriginalCompanyId = false;

		resourceBlockModelImpl._originalGroupId =
			resourceBlockModelImpl._groupId;

		resourceBlockModelImpl._setOriginalGroupId = false;

		resourceBlockModelImpl._originalName = resourceBlockModelImpl._name;

		resourceBlockModelImpl._originalPermissionsHash =
			resourceBlockModelImpl._permissionsHash;

		resourceBlockModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ResourceBlock> toCacheModel() {
		ResourceBlockCacheModel resourceBlockCacheModel =
			new ResourceBlockCacheModel();

		resourceBlockCacheModel.mvccVersion = getMvccVersion();

		resourceBlockCacheModel.resourceBlockId = getResourceBlockId();

		resourceBlockCacheModel.companyId = getCompanyId();

		resourceBlockCacheModel.groupId = getGroupId();

		resourceBlockCacheModel.name = getName();

		String name = resourceBlockCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			resourceBlockCacheModel.name = null;
		}

		resourceBlockCacheModel.permissionsHash = getPermissionsHash();

		String permissionsHash = resourceBlockCacheModel.permissionsHash;

		if ((permissionsHash != null) && (permissionsHash.length() == 0)) {
			resourceBlockCacheModel.permissionsHash = null;
		}

		resourceBlockCacheModel.referenceCount = getReferenceCount();

		return resourceBlockCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ResourceBlock, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ResourceBlock, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ResourceBlock, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ResourceBlock)this));
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
		Map<String, Function<ResourceBlock, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ResourceBlock, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ResourceBlock, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ResourceBlock)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader =
		ResourceBlock.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
		ResourceBlock.class, ModelWrapper.class
	};

	private long _mvccVersion;
	private long _resourceBlockId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private String _name;
	private String _originalName;
	private String _permissionsHash;
	private String _originalPermissionsHash;
	private long _referenceCount;
	private long _columnBitmask;
	private ResourceBlock _escapedModel;

}