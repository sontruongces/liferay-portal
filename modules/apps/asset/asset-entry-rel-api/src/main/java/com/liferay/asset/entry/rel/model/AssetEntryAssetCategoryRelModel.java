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

package com.liferay.asset.entry.rel.model;

import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the AssetEntryAssetCategoryRel service. Represents a row in the &quot;AssetEntryAssetCategoryRel&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.asset.entry.rel.model.impl.AssetEntryAssetCategoryRelImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryAssetCategoryRel
 * @generated
 */
@ProviderType
public interface AssetEntryAssetCategoryRelModel
	extends BaseModel<AssetEntryAssetCategoryRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a asset entry asset category rel model instance should use the {@link AssetEntryAssetCategoryRel} interface instead.
	 */

	/**
	 * Returns the primary key of this asset entry asset category rel.
	 *
	 * @return the primary key of this asset entry asset category rel
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this asset entry asset category rel.
	 *
	 * @param primaryKey the primary key of this asset entry asset category rel
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the asset entry asset category rel ID of this asset entry asset category rel.
	 *
	 * @return the asset entry asset category rel ID of this asset entry asset category rel
	 */
	public long getAssetEntryAssetCategoryRelId();

	/**
	 * Sets the asset entry asset category rel ID of this asset entry asset category rel.
	 *
	 * @param assetEntryAssetCategoryRelId the asset entry asset category rel ID of this asset entry asset category rel
	 */
	public void setAssetEntryAssetCategoryRelId(
		long assetEntryAssetCategoryRelId);

	/**
	 * Returns the asset entry ID of this asset entry asset category rel.
	 *
	 * @return the asset entry ID of this asset entry asset category rel
	 */
	public long getAssetEntryId();

	/**
	 * Sets the asset entry ID of this asset entry asset category rel.
	 *
	 * @param assetEntryId the asset entry ID of this asset entry asset category rel
	 */
	public void setAssetEntryId(long assetEntryId);

	/**
	 * Returns the asset category ID of this asset entry asset category rel.
	 *
	 * @return the asset category ID of this asset entry asset category rel
	 */
	public long getAssetCategoryId();

	/**
	 * Sets the asset category ID of this asset entry asset category rel.
	 *
	 * @param assetCategoryId the asset category ID of this asset entry asset category rel
	 */
	public void setAssetCategoryId(long assetCategoryId);

	/**
	 * Returns the priority of this asset entry asset category rel.
	 *
	 * @return the priority of this asset entry asset category rel
	 */
	public int getPriority();

	/**
	 * Sets the priority of this asset entry asset category rel.
	 *
	 * @param priority the priority of this asset entry asset category rel
	 */
	public void setPriority(int priority);

}