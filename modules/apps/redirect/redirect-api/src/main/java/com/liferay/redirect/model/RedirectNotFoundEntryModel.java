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

package com.liferay.redirect.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the RedirectNotFoundEntry service. Represents a row in the &quot;RedirectNotFoundEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.redirect.model.impl.RedirectNotFoundEntryModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.redirect.model.impl.RedirectNotFoundEntryImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RedirectNotFoundEntry
 * @generated
 */
@ProviderType
public interface RedirectNotFoundEntryModel
	extends BaseModel<RedirectNotFoundEntry>, GroupedModel, MVCCModel,
			ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a redirect not found entry model instance should use the {@link RedirectNotFoundEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this redirect not found entry.
	 *
	 * @return the primary key of this redirect not found entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this redirect not found entry.
	 *
	 * @param primaryKey the primary key of this redirect not found entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this redirect not found entry.
	 *
	 * @return the mvcc version of this redirect not found entry
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this redirect not found entry.
	 *
	 * @param mvccVersion the mvcc version of this redirect not found entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the redirect not found entry ID of this redirect not found entry.
	 *
	 * @return the redirect not found entry ID of this redirect not found entry
	 */
	public long getRedirectNotFoundEntryId();

	/**
	 * Sets the redirect not found entry ID of this redirect not found entry.
	 *
	 * @param redirectNotFoundEntryId the redirect not found entry ID of this redirect not found entry
	 */
	public void setRedirectNotFoundEntryId(long redirectNotFoundEntryId);

	/**
	 * Returns the group ID of this redirect not found entry.
	 *
	 * @return the group ID of this redirect not found entry
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this redirect not found entry.
	 *
	 * @param groupId the group ID of this redirect not found entry
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this redirect not found entry.
	 *
	 * @return the company ID of this redirect not found entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this redirect not found entry.
	 *
	 * @param companyId the company ID of this redirect not found entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this redirect not found entry.
	 *
	 * @return the user ID of this redirect not found entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this redirect not found entry.
	 *
	 * @param userId the user ID of this redirect not found entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this redirect not found entry.
	 *
	 * @return the user uuid of this redirect not found entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this redirect not found entry.
	 *
	 * @param userUuid the user uuid of this redirect not found entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this redirect not found entry.
	 *
	 * @return the user name of this redirect not found entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this redirect not found entry.
	 *
	 * @param userName the user name of this redirect not found entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this redirect not found entry.
	 *
	 * @return the create date of this redirect not found entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this redirect not found entry.
	 *
	 * @param createDate the create date of this redirect not found entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this redirect not found entry.
	 *
	 * @return the modified date of this redirect not found entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this redirect not found entry.
	 *
	 * @param modifiedDate the modified date of this redirect not found entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the ignored of this redirect not found entry.
	 *
	 * @return the ignored of this redirect not found entry
	 */
	public boolean getIgnored();

	/**
	 * Returns <code>true</code> if this redirect not found entry is ignored.
	 *
	 * @return <code>true</code> if this redirect not found entry is ignored; <code>false</code> otherwise
	 */
	public boolean isIgnored();

	/**
	 * Sets whether this redirect not found entry is ignored.
	 *
	 * @param ignored the ignored of this redirect not found entry
	 */
	public void setIgnored(boolean ignored);

	/**
	 * Returns the url of this redirect not found entry.
	 *
	 * @return the url of this redirect not found entry
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this redirect not found entry.
	 *
	 * @param url the url of this redirect not found entry
	 */
	public void setUrl(String url);

	@Override
	public RedirectNotFoundEntry cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}