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

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProductBundle in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleCacheModel
	implements CacheModel<ProductBundle>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ProductBundleCacheModel)) {
			return false;
		}

		ProductBundleCacheModel productBundleCacheModel =
			(ProductBundleCacheModel)object;

		if ((productBundleId == productBundleCacheModel.productBundleId) &&
			(mvccVersion == productBundleCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, productBundleId);

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
		StringBundler sb = new StringBundler(17);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", productBundleId=");
		sb.append(productBundleId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ProductBundle toEntityModel() {
		ProductBundleImpl productBundleImpl = new ProductBundleImpl();

		productBundleImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			productBundleImpl.setUuid("");
		}
		else {
			productBundleImpl.setUuid(uuid);
		}

		productBundleImpl.setProductBundleId(productBundleId);
		productBundleImpl.setCompanyId(companyId);
		productBundleImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			productBundleImpl.setCreateDate(null);
		}
		else {
			productBundleImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			productBundleImpl.setModifiedDate(null);
		}
		else {
			productBundleImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			productBundleImpl.setName("");
		}
		else {
			productBundleImpl.setName(name);
		}

		productBundleImpl.resetOriginalValues();

		return productBundleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		productBundleId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(productBundleId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long productBundleId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public String name;

}