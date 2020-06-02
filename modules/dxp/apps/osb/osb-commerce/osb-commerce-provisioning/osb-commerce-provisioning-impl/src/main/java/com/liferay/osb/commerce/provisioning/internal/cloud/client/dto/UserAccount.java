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

package com.liferay.osb.commerce.provisioning.internal.cloud.client.dto;

import java.util.Date;

/**
 * @author Ivica Cardic
 */
public class UserAccount {

	public String getAdditionalName() {
		return additionalName;
	}

	public String getAlternateName() {
		return alternateName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public long getId() {
		return id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getName() {
		return name;
	}

	public void setAdditionalName(String additionalName) {
		this.additionalName = additionalName;
	}

	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String additionalName;
	protected String alternateName;
	protected Date birthDate;
	protected Date dateCreated;
	protected Date dateModified;
	protected String emailAddress;
	protected String familyName;
	protected String givenName;
	protected long id;
	protected String jobTitle;
	protected String name;

}