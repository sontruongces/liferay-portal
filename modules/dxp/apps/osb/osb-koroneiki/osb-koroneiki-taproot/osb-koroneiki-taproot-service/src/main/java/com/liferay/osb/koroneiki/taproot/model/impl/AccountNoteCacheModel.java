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

package com.liferay.osb.koroneiki.taproot.model.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
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
 * The cache model class for representing AccountNote in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AccountNoteCacheModel
	implements CacheModel<AccountNote>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AccountNoteCacheModel)) {
			return false;
		}

		AccountNoteCacheModel accountNoteCacheModel =
			(AccountNoteCacheModel)object;

		if ((accountNoteId == accountNoteCacheModel.accountNoteId) &&
			(mvccVersion == accountNoteCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, accountNoteId);

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
		StringBundler sb = new StringBundler(37);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", accountNoteId=");
		sb.append(accountNoteId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", creatorOktaId=");
		sb.append(creatorOktaId);
		sb.append(", creatorName=");
		sb.append(creatorName);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", modifierOktaId=");
		sb.append(modifierOktaId);
		sb.append(", modifierName=");
		sb.append(modifierName);
		sb.append(", accountNoteKey=");
		sb.append(accountNoteKey);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", content=");
		sb.append(content);
		sb.append(", format=");
		sb.append(format);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AccountNote toEntityModel() {
		AccountNoteImpl accountNoteImpl = new AccountNoteImpl();

		accountNoteImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			accountNoteImpl.setUuid("");
		}
		else {
			accountNoteImpl.setUuid(uuid);
		}

		accountNoteImpl.setAccountNoteId(accountNoteId);
		accountNoteImpl.setCompanyId(companyId);
		accountNoteImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			accountNoteImpl.setCreateDate(null);
		}
		else {
			accountNoteImpl.setCreateDate(new Date(createDate));
		}

		if (creatorOktaId == null) {
			accountNoteImpl.setCreatorOktaId("");
		}
		else {
			accountNoteImpl.setCreatorOktaId(creatorOktaId);
		}

		if (creatorName == null) {
			accountNoteImpl.setCreatorName("");
		}
		else {
			accountNoteImpl.setCreatorName(creatorName);
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountNoteImpl.setModifiedDate(null);
		}
		else {
			accountNoteImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (modifierOktaId == null) {
			accountNoteImpl.setModifierOktaId("");
		}
		else {
			accountNoteImpl.setModifierOktaId(modifierOktaId);
		}

		if (modifierName == null) {
			accountNoteImpl.setModifierName("");
		}
		else {
			accountNoteImpl.setModifierName(modifierName);
		}

		if (accountNoteKey == null) {
			accountNoteImpl.setAccountNoteKey("");
		}
		else {
			accountNoteImpl.setAccountNoteKey(accountNoteKey);
		}

		accountNoteImpl.setAccountId(accountId);

		if (type == null) {
			accountNoteImpl.setType("");
		}
		else {
			accountNoteImpl.setType(type);
		}

		accountNoteImpl.setPriority(priority);

		if (content == null) {
			accountNoteImpl.setContent("");
		}
		else {
			accountNoteImpl.setContent(content);
		}

		if (format == null) {
			accountNoteImpl.setFormat("");
		}
		else {
			accountNoteImpl.setFormat(format);
		}

		if (status == null) {
			accountNoteImpl.setStatus("");
		}
		else {
			accountNoteImpl.setStatus(status);
		}

		accountNoteImpl.resetOriginalValues();

		return accountNoteImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		accountNoteId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		creatorOktaId = objectInput.readUTF();
		creatorName = objectInput.readUTF();
		modifiedDate = objectInput.readLong();
		modifierOktaId = objectInput.readUTF();
		modifierName = objectInput.readUTF();
		accountNoteKey = objectInput.readUTF();

		accountId = objectInput.readLong();
		type = objectInput.readUTF();

		priority = objectInput.readInt();
		content = objectInput.readUTF();
		format = objectInput.readUTF();
		status = objectInput.readUTF();
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

		objectOutput.writeLong(accountNoteId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (creatorOktaId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(creatorOktaId);
		}

		if (creatorName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(creatorName);
		}

		objectOutput.writeLong(modifiedDate);

		if (modifierOktaId == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifierOktaId);
		}

		if (modifierName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(modifierName);
		}

		if (accountNoteKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountNoteKey);
		}

		objectOutput.writeLong(accountId);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(priority);

		if (content == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(content);
		}

		if (format == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(format);
		}

		if (status == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(status);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long accountNoteId;
	public long companyId;
	public long userId;
	public long createDate;
	public String creatorOktaId;
	public String creatorName;
	public long modifiedDate;
	public String modifierOktaId;
	public String modifierName;
	public String accountNoteKey;
	public long accountId;
	public String type;
	public int priority;
	public String content;
	public String format;
	public String status;

}