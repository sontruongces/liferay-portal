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

import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TeamAccountRole in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TeamAccountRoleCacheModel
	implements CacheModel<TeamAccountRole>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof TeamAccountRoleCacheModel)) {
			return false;
		}

		TeamAccountRoleCacheModel teamAccountRoleCacheModel =
			(TeamAccountRoleCacheModel)object;

		if (teamAccountRolePK.equals(
				teamAccountRoleCacheModel.teamAccountRolePK) &&
			(mvccVersion == teamAccountRoleCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, teamAccountRolePK);

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
		StringBundler sb = new StringBundler(9);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", teamId=");
		sb.append(teamId);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", teamRoleId=");
		sb.append(teamRoleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TeamAccountRole toEntityModel() {
		TeamAccountRoleImpl teamAccountRoleImpl = new TeamAccountRoleImpl();

		teamAccountRoleImpl.setMvccVersion(mvccVersion);
		teamAccountRoleImpl.setTeamId(teamId);
		teamAccountRoleImpl.setAccountId(accountId);
		teamAccountRoleImpl.setTeamRoleId(teamRoleId);

		teamAccountRoleImpl.resetOriginalValues();

		return teamAccountRoleImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		teamId = objectInput.readLong();

		accountId = objectInput.readLong();

		teamRoleId = objectInput.readLong();

		teamAccountRolePK = new TeamAccountRolePK(
			teamId, accountId, teamRoleId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(teamId);

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(teamRoleId);
	}

	public long mvccVersion;
	public long teamId;
	public long accountId;
	public long teamRoleId;
	public transient TeamAccountRolePK teamAccountRolePK;

}