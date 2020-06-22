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

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalServiceUtil;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.FullNameGenerator;
import com.liferay.portal.kernel.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.List;
import java.util.Locale;

/**
 * @author Kyle Bischof
 */
public class ContactImpl extends ContactBaseImpl {

	public ContactImpl() {
	}

	public List<ContactRole> getContactRoles(
		long accountId, String contactRoleType) {

		return ContactRoleLocalServiceUtil.getContactAccountContactRoles(
			accountId, getContactId(), new String[] {contactRoleType},
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<Entitlement> getEntitlements() {
		return EntitlementLocalServiceUtil.getEntitlements(
			Contact.class.getName(), getContactId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			Contact.class.getName(), getContactId(), QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public String getFullName() {
		FullNameGenerator fullNameGenerator =
			FullNameGeneratorFactory.getInstance();

		Locale locale = LocaleUtil.fromLanguageId(getLanguageId());

		return fullNameGenerator.getLocalizedFullName(
			getFirstName(), getMiddleName(), getLastName(), locale, 0, 0);
	}

	public List<Team> getTeams() throws PortalException {
		return TeamLocalServiceUtil.getContactTeams(
			getContactId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

}