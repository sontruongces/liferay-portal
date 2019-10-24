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

package com.liferay.osb.koroneiki.phytohormone.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.phytohormone.service.http.EntitlementServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntitlementSoap implements Serializable {

	public static EntitlementSoap toSoapModel(Entitlement model) {
		EntitlementSoap soapModel = new EntitlementSoap();

		soapModel.setEntitlementId(model.getEntitlementId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEntitlementDefinitionId(
			model.getEntitlementDefinitionId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static EntitlementSoap[] toSoapModels(Entitlement[] models) {
		EntitlementSoap[] soapModels = new EntitlementSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntitlementSoap[][] toSoapModels(Entitlement[][] models) {
		EntitlementSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntitlementSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntitlementSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntitlementSoap[] toSoapModels(List<Entitlement> models) {
		List<EntitlementSoap> soapModels = new ArrayList<EntitlementSoap>(
			models.size());

		for (Entitlement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntitlementSoap[soapModels.size()]);
	}

	public EntitlementSoap() {
	}

	public long getPrimaryKey() {
		return _entitlementId;
	}

	public void setPrimaryKey(long pk) {
		setEntitlementId(pk);
	}

	public long getEntitlementId() {
		return _entitlementId;
	}

	public void setEntitlementId(long entitlementId) {
		_entitlementId = entitlementId;
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

	public long getEntitlementDefinitionId() {
		return _entitlementDefinitionId;
	}

	public void setEntitlementDefinitionId(long entitlementDefinitionId) {
		_entitlementDefinitionId = entitlementDefinitionId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _entitlementId;
	private long _companyId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _entitlementDefinitionId;
	private long _classNameId;
	private long _classPK;
	private String _name;

}