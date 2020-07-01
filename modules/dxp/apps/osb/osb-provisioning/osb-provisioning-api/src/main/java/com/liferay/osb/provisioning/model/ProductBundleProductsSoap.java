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

import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProductBundleProductsSoap implements Serializable {

	public static ProductBundleProductsSoap toSoapModel(
		ProductBundleProducts model) {

		ProductBundleProductsSoap soapModel = new ProductBundleProductsSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setProductBundleId(model.getProductBundleId());
		soapModel.setProductKey(model.getProductKey());

		return soapModel;
	}

	public static ProductBundleProductsSoap[] toSoapModels(
		ProductBundleProducts[] models) {

		ProductBundleProductsSoap[] soapModels =
			new ProductBundleProductsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProductBundleProductsSoap[][] toSoapModels(
		ProductBundleProducts[][] models) {

		ProductBundleProductsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ProductBundleProductsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProductBundleProductsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProductBundleProductsSoap[] toSoapModels(
		List<ProductBundleProducts> models) {

		List<ProductBundleProductsSoap> soapModels =
			new ArrayList<ProductBundleProductsSoap>(models.size());

		for (ProductBundleProducts model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ProductBundleProductsSoap[soapModels.size()]);
	}

	public ProductBundleProductsSoap() {
	}

	public ProductBundleProductsPK getPrimaryKey() {
		return new ProductBundleProductsPK(_productBundleId, _productKey);
	}

	public void setPrimaryKey(ProductBundleProductsPK pk) {
		setProductBundleId(pk.productBundleId);
		setProductKey(pk.productKey);
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

	public String getProductKey() {
		return _productKey;
	}

	public void setProductKey(String productKey) {
		_productKey = productKey;
	}

	private long _mvccVersion;
	private long _productBundleId;
	private String _productKey;

}