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

package com.liferay.osb.koroneiki.trunk.model.impl.view;

import com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView;
import com.liferay.petra.string.StringPool;

import java.io.Serializable;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewImpl implements ProductPurchaseView {

	public ProductPurchaseViewImpl() {
	}

	public long getAccountId() {
		return _accountId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public Class<?> getModelClass() {
		return ProductPurchaseView.class;
	}

	public String getModelClassName() {
		return ProductPurchaseView.class.getName();
	}

	public Serializable getPrimaryKeyObj() {
		return String.valueOf(_accountId) + StringPool.UNDERLINE +
			String.valueOf(_productEntryId);
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	private long _accountId;
	private long _companyId;
	private long _productEntryId;

}