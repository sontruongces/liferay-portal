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

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
public class ContactTeamRoleImpl extends ContactTeamRoleBaseImpl {

	public ContactTeamRoleImpl() {
	}

	public Contact getContact() throws PortalException {
		return ContactLocalServiceUtil.getContact(getContactId());
	}

	public ContactRole getContactRole() throws PortalException {
		return ContactRoleLocalServiceUtil.getContactRole(getContactRoleId());
	}

	public Team getTeam() throws PortalException {
		return TeamLocalServiceUtil.getTeam(getTeamId());
	}

}