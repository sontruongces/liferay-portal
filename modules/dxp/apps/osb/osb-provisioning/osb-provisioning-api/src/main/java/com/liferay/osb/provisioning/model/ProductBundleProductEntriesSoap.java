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

import com.liferay.osb.provisioning.service.persistence.ProductBundleProductEntriesPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleProductEntriesSoap implements Serializable {

	public static ProductBundleProductEntriesSoap toSoapModel(
		ProductBundleProductEntries model) {

		ProductBundleProductEntriesSoap soapModel =
			new ProductBundleProductEntriesSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setProductBundleId(model.getProductBundleId());
		soapModel.setProductEntryId(model.getProductEntryId());

		return soapModel;
	}

	public static ProductBundleProductEntriesSoap[] toSoapModels(
		ProductBundleProductEntries[] models) {

		ProductBundleProductEntriesSoap[] soapModels =
			new ProductBundleProductEntriesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProductBundleProductEntriesSoap[][] toSoapModels(
		ProductBundleProductEntries[][] models) {

		ProductBundleProductEntriesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProductBundleProductEntriesSoap
				[models.length][models[0].length];
		}
		else {
			soapModels = new ProductBundleProductEntriesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProductBundleProductEntriesSoap[] toSoapModels(
		List<ProductBundleProductEntries> models) {

		List<ProductBundleProductEntriesSoap> soapModels =
			new ArrayList<ProductBundleProductEntriesSoap>(models.size());

		for (ProductBundleProductEntries model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ProductBundleProductEntriesSoap[soapModels.size()]);
	}

	public ProductBundleProductEntriesSoap() {
	}

	public ProductBundleProductEntriesPK getPrimaryKey() {
		return new ProductBundleProductEntriesPK(
			_productBundleId, _productEntryId);
	}

	public void setPrimaryKey(ProductBundleProductEntriesPK pk) {
		setProductBundleId(pk.productBundleId);
		setProductEntryId(pk.productEntryId);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getProductBundleId() {
		return _productBundleId;
	}

	public void setProductBundleId(long productBundleId) {
		_productBundleId = productBundleId;
	}

	public long getProductEntryId() {
		return _productEntryId;
	}

	public void setProductEntryId(long productEntryId) {
		_productEntryId = productEntryId;
	}

	private long _mvccVersion;
	private long _productBundleId;
	private long _productEntryId;

}