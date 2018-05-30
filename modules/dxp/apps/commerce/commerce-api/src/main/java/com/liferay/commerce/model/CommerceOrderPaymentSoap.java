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

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Alessio Antonio Rendina
 * @generated
 */
@ProviderType
public class CommerceOrderPaymentSoap implements Serializable {
	public static CommerceOrderPaymentSoap toSoapModel(
		CommerceOrderPayment model) {
		CommerceOrderPaymentSoap soapModel = new CommerceOrderPaymentSoap();

		soapModel.setCommerceOrderPaymentId(model.getCommerceOrderPaymentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCommerceOrderId(model.getCommerceOrderId());
		soapModel.setCommercePaymentMethodId(model.getCommercePaymentMethodId());
		soapModel.setStatus(model.getStatus());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static CommerceOrderPaymentSoap[] toSoapModels(
		CommerceOrderPayment[] models) {
		CommerceOrderPaymentSoap[] soapModels = new CommerceOrderPaymentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderPaymentSoap[][] toSoapModels(
		CommerceOrderPayment[][] models) {
		CommerceOrderPaymentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CommerceOrderPaymentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CommerceOrderPaymentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CommerceOrderPaymentSoap[] toSoapModels(
		List<CommerceOrderPayment> models) {
		List<CommerceOrderPaymentSoap> soapModels = new ArrayList<CommerceOrderPaymentSoap>(models.size());

		for (CommerceOrderPayment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CommerceOrderPaymentSoap[soapModels.size()]);
	}

	public CommerceOrderPaymentSoap() {
	}

	public long getPrimaryKey() {
		return _commerceOrderPaymentId;
	}

	public void setPrimaryKey(long pk) {
		setCommerceOrderPaymentId(pk);
	}

	public long getCommerceOrderPaymentId() {
		return _commerceOrderPaymentId;
	}

	public void setCommerceOrderPaymentId(long commerceOrderPaymentId) {
		_commerceOrderPaymentId = commerceOrderPaymentId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCommerceOrderId() {
		return _commerceOrderId;
	}

	public void setCommerceOrderId(long commerceOrderId) {
		_commerceOrderId = commerceOrderId;
	}

	public long getCommercePaymentMethodId() {
		return _commercePaymentMethodId;
	}

	public void setCommercePaymentMethodId(long commercePaymentMethodId) {
		_commercePaymentMethodId = commercePaymentMethodId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _commerceOrderPaymentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _commerceOrderId;
	private long _commercePaymentMethodId;
	private int _status;
	private String _content;
}