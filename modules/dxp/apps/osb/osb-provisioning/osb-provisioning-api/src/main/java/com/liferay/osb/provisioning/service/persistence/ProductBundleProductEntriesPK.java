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

package com.liferay.osb.provisioning.service.persistence;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleProductEntriesPK
	implements Comparable<ProductBundleProductEntriesPK>, Serializable {

	public long productBundleId;
	public long productEntryId;

	public ProductBundleProductEntriesPK() {
	}

	public ProductBundleProductEntriesPK(
		long productBundleId, long productEntryId) {

		this.productBundleId = productBundleId;
		this.productEntryId = productEntryId;
	}

	public long getProductBundleId() {
		return productBundleId;
	}

	public void setProductBundleId(long productBundleId) {
		this.productBundleId = productBundleId;
	}

	public long getProductEntryId() {
		return productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		this.productEntryId = productEntryId;
	}

	@Override
	public int compareTo(ProductBundleProductEntriesPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (productBundleId < pk.productBundleId) {
			value = -1;
		}
		else if (productBundleId > pk.productBundleId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (productEntryId < pk.productEntryId) {
			value = -1;
		}
		else if (productEntryId > pk.productEntryId) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(object instanceof ProductBundleProductEntriesPK)) {
			return false;
		}

		ProductBundleProductEntriesPK pk =
			(ProductBundleProductEntriesPK)object;

		if ((productBundleId == pk.productBundleId) &&
			(productEntryId == pk.productEntryId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, productBundleId);
		hashCode = HashUtil.hash(hashCode, productEntryId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("productBundleId=");

		sb.append(productBundleId);
		sb.append(", productEntryId=");

		sb.append(productEntryId);

		sb.append("}");

		return sb.toString();
	}

}