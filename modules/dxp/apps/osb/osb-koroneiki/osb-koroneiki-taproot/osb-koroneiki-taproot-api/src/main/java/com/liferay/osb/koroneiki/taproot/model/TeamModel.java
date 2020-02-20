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

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Team service. Represents a row in the &quot;Koroneiki_Team&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.osb.koroneiki.taproot.model.impl.TeamModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.osb.koroneiki.taproot.model.impl.TeamImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Team
 * @generated
 */
@ProviderType
public interface TeamModel
	extends BaseModel<Team>, MVCCModel, ShardedModel, StagedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a team model instance should use the {@link Team} interface instead.
	 */

	/**
	 * Returns the primary key of this team.
	 *
	 * @return the primary key of this team
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this team.
	 *
	 * @param primaryKey the primary key of this team
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this team.
	 *
	 * @return the mvcc version of this team
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this team.
	 *
	 * @param mvccVersion the mvcc version of this team
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this team.
	 *
	 * @return the uuid of this team
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this team.
	 *
	 * @param uuid the uuid of this team
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the team ID of this team.
	 *
	 * @return the team ID of this team
	 */
	public long getTeamId();

	/**
	 * Sets the team ID of this team.
	 *
	 * @param teamId the team ID of this team
	 */
	public void setTeamId(long teamId);

	/**
	 * Returns the company ID of this team.
	 *
	 * @return the company ID of this team
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this team.
	 *
	 * @param companyId the company ID of this team
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this team.
	 *
	 * @return the user ID of this team
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this team.
	 *
	 * @param userId the user ID of this team
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this team.
	 *
	 * @return the user uuid of this team
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this team.
	 *
	 * @param userUuid the user uuid of this team
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this team.
	 *
	 * @return the create date of this team
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this team.
	 *
	 * @param createDate the create date of this team
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this team.
	 *
	 * @return the modified date of this team
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this team.
	 *
	 * @param modifiedDate the modified date of this team
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the team key of this team.
	 *
	 * @return the team key of this team
	 */
	@AutoEscape
	public String getTeamKey();

	/**
	 * Sets the team key of this team.
	 *
	 * @param teamKey the team key of this team
	 */
	public void setTeamKey(String teamKey);

	/**
	 * Returns the account ID of this team.
	 *
	 * @return the account ID of this team
	 */
	public long getAccountId();

	/**
	 * Sets the account ID of this team.
	 *
	 * @param accountId the account ID of this team
	 */
	public void setAccountId(long accountId);

	/**
	 * Returns the name of this team.
	 *
	 * @return the name of this team
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this team.
	 *
	 * @param name the name of this team
	 */
	public void setName(String name);

	/**
	 * Returns the system of this team.
	 *
	 * @return the system of this team
	 */
	public boolean getSystem();

	/**
	 * Returns <code>true</code> if this team is system.
	 *
	 * @return <code>true</code> if this team is system; <code>false</code> otherwise
	 */
	public boolean isSystem();

	/**
	 * Sets whether this team is system.
	 *
	 * @param system the system of this team
	 */
	public void setSystem(boolean system);

}