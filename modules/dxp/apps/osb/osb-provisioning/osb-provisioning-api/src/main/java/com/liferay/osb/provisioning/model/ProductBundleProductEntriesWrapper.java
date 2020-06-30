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

package com.liferay.osb.provisioning.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ProductBundleProductEntries}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductEntries
 * @generated
 */
public class ProductBundleProductEntriesWrapper
	extends BaseModelWrapper<ProductBundleProductEntries>
	implements ModelWrapper<ProductBundleProductEntries>,
			   ProductBundleProductEntries {

	public ProductBundleProductEntriesWrapper(
		ProductBundleProductEntries productBundleProductEntries) {

		super(productBundleProductEntries);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("productBundleId", getProductBundleId());
		attributes.put("productEntryId", getProductEntryId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long productBundleId = (Long)attributes.get("productBundleId");

		if (productBundleId != null) {
			setProductBundleId(productBundleId);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}
	}

	/**
	 * Returns the mvcc version of this product bundle product entries.
	 *
	 * @return the mvcc version of this product bundle product entries
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this product bundle product entries.
	 *
	 * @return the primary key of this product bundle product entries
	 */
	@Override
	public com.liferay.osb.provisioning.service.persistence.
		ProductBundleProductEntriesPK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the product bundle ID of this product bundle product entries.
	 *
	 * @return the product bundle ID of this product bundle product entries
	 */
	@Override
	public long getProductBundleId() {
		return model.getProductBundleId();
	}

	/**
	 * Returns the product entry ID of this product bundle product entries.
	 *
	 * @return the product entry ID of this product bundle product entries
	 */
	@Override
	public long getProductEntryId() {
		return model.getProductEntryId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the mvcc version of this product bundle product entries.
	 *
	 * @param mvccVersion the mvcc version of this product bundle product entries
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this product bundle product entries.
	 *
	 * @param primaryKey the primary key of this product bundle product entries
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.osb.provisioning.service.persistence.
			ProductBundleProductEntriesPK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the product bundle ID of this product bundle product entries.
	 *
	 * @param productBundleId the product bundle ID of this product bundle product entries
	 */
	@Override
	public void setProductBundleId(long productBundleId) {
		model.setProductBundleId(productBundleId);
	}

	/**
	 * Sets the product entry ID of this product bundle product entries.
	 *
	 * @param productEntryId the product entry ID of this product bundle product entries
	 */
	@Override
	public void setProductEntryId(long productEntryId) {
		model.setProductEntryId(productEntryId);
	}

	@Override
	protected ProductBundleProductEntriesWrapper wrap(
		ProductBundleProductEntries productBundleProductEntries) {

		return new ProductBundleProductEntriesWrapper(
			productBundleProductEntries);
	}

}