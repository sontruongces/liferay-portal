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

package com.liferay.commerce.product.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CPDefinitionOptionValueRel in entity cache.
 *
 * @author Marco Leo
 * @see CPDefinitionOptionValueRel
 * @generated
 */
@ProviderType
public class CPDefinitionOptionValueRelCacheModel implements CacheModel<CPDefinitionOptionValueRel>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPDefinitionOptionValueRelCacheModel)) {
			return false;
		}

		CPDefinitionOptionValueRelCacheModel cpDefinitionOptionValueRelCacheModel =
			(CPDefinitionOptionValueRelCacheModel)obj;

		if (CPDefinitionOptionValueRelId == cpDefinitionOptionValueRelCacheModel.CPDefinitionOptionValueRelId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, CPDefinitionOptionValueRelId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", CPDefinitionOptionValueRelId=");
		sb.append(CPDefinitionOptionValueRelId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", title=");
		sb.append(title);
		sb.append(", CPDefinitionOptionRelId=");
		sb.append(CPDefinitionOptionRelId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CPDefinitionOptionValueRel toEntityModel() {
		CPDefinitionOptionValueRelImpl cpDefinitionOptionValueRelImpl = new CPDefinitionOptionValueRelImpl();

		if (uuid == null) {
			cpDefinitionOptionValueRelImpl.setUuid(StringPool.BLANK);
		}
		else {
			cpDefinitionOptionValueRelImpl.setUuid(uuid);
		}

		cpDefinitionOptionValueRelImpl.setCPDefinitionOptionValueRelId(CPDefinitionOptionValueRelId);
		cpDefinitionOptionValueRelImpl.setGroupId(groupId);
		cpDefinitionOptionValueRelImpl.setCompanyId(companyId);
		cpDefinitionOptionValueRelImpl.setUserId(userId);

		if (userName == null) {
			cpDefinitionOptionValueRelImpl.setUserName(StringPool.BLANK);
		}
		else {
			cpDefinitionOptionValueRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			cpDefinitionOptionValueRelImpl.setCreateDate(null);
		}
		else {
			cpDefinitionOptionValueRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			cpDefinitionOptionValueRelImpl.setModifiedDate(null);
		}
		else {
			cpDefinitionOptionValueRelImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		if (title == null) {
			cpDefinitionOptionValueRelImpl.setTitle(StringPool.BLANK);
		}
		else {
			cpDefinitionOptionValueRelImpl.setTitle(title);
		}

		cpDefinitionOptionValueRelImpl.setCPDefinitionOptionRelId(CPDefinitionOptionRelId);

		if (name == null) {
			cpDefinitionOptionValueRelImpl.setName(StringPool.BLANK);
		}
		else {
			cpDefinitionOptionValueRelImpl.setName(name);
		}

		cpDefinitionOptionValueRelImpl.setPriority(priority);

		cpDefinitionOptionValueRelImpl.resetOriginalValues();

		return cpDefinitionOptionValueRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		CPDefinitionOptionValueRelId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		title = objectInput.readUTF();

		CPDefinitionOptionRelId = objectInput.readLong();
		name = objectInput.readUTF();

		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(CPDefinitionOptionValueRelId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeLong(CPDefinitionOptionRelId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(priority);
	}

	public String uuid;
	public long CPDefinitionOptionValueRelId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String title;
	public long CPDefinitionOptionRelId;
	public String name;
	public int priority;
}