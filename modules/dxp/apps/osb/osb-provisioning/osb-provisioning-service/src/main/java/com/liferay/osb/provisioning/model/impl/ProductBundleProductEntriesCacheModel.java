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

package com.liferay.osb.provisioning.model.impl;

import com.liferay.osb.provisioning.model.ProductBundleProductEntries;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductEntriesPK;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProductBundleProductEntries in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleProductEntriesCacheModel
	implements CacheModel<ProductBundleProductEntries>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductBundleProductEntriesCacheModel)) {
			return false;
		}

		ProductBundleProductEntriesCacheModel
			productBundleProductEntriesCacheModel =
				(ProductBundleProductEntriesCacheModel)object;

		if (productBundleProductEntriesPK.equals(
				productBundleProductEntriesCacheModel.
					productBundleProductEntriesPK) &&
			(mvccVersion ==
				productBundleProductEntriesCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productBundleProductEntriesPK);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", productBundleId=");
		sb.append(productBundleId);
		sb.append(", productEntryId=");
		sb.append(productEntryId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductBundleProductEntries toEntityModel() {
		ProductBundleProductEntriesImpl productBundleProductEntriesImpl =
			new ProductBundleProductEntriesImpl();

		productBundleProductEntriesImpl.setMvccVersion(mvccVersion);
		productBundleProductEntriesImpl.setProductBundleId(productBundleId);
		productBundleProductEntriesImpl.setProductEntryId(productEntryId);

		productBundleProductEntriesImpl.resetOriginalValues();

		return productBundleProductEntriesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		productBundleId = objectInput.readLong();

		productEntryId = objectInput.readLong();

		productBundleProductEntriesPK = new ProductBundleProductEntriesPK(
			productBundleId, productEntryId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(productBundleId);

		objectOutput.writeLong(productEntryId);
	}

	public long mvccVersion;
	public long productBundleId;
	public long productEntryId;
	public transient ProductBundleProductEntriesPK
		productBundleProductEntriesPK;

}