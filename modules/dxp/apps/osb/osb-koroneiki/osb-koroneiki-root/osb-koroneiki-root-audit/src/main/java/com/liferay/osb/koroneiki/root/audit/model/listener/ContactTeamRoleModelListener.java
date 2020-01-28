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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ContactTeamRoleModelListener
	extends BaseAuditModelListener<ContactTeamRole> {

	@Override
	public void onAfterCreate(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		try {
			Contact contact = contactTeamRole.getContact();
			ContactRole contactRole = contactTeamRole.getContactRole();
			Team team = contactTeamRole.getTeam();

			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactTeamRole.getContactRoleId(),
				AuditEntry.Action.ASSIGN.toString(), "Team", StringPool.BLANK,
				StringPool.BLANK, team.getName(),
				String.valueOf(team.getTeamId()), contactRole.getName(),
				serviceContext);

			serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				contactTeamRole.getTeamId());

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				contactTeamRole.getTeamId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				AuditEntry.Action.ASSIGN.toString(), "Contact Role",
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				String.valueOf(contactTeamRole.getContactRoleId()),
				contact.getFullName(), serviceContext);
		}
		catch (PortalException pe) {
			throw new ModelListenerException(pe);
		}
	}

	@Override
	public void onBeforeRemove(ContactTeamRole contactTeamRole)
		throws ModelListenerException {

		try {
			Contact contact = contactTeamRole.getContact();
			ContactRole contactRole = contactTeamRole.getContactRole();
			Team team = contactTeamRole.getTeam();

			ServiceContext serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				classNameLocalService.getClassNameId(ContactRole.class),
				contactTeamRole.getContactRoleId(),
				AuditEntry.Action.UNASSIGN.toString(), "Team", StringPool.BLANK,
				StringPool.BLANK, team.getName(),
				String.valueOf(team.getTeamId()), contactRole.getName(),
				serviceContext);

			serviceContext = getServiceContext(
				classNameLocalService.getClassNameId(Team.class),
				contactTeamRole.getTeamId());

			auditEntryLocalService.addAuditEntry(
				getUserId(), classNameLocalService.getClassNameId(Team.class),
				contactTeamRole.getTeamId(),
				classNameLocalService.getClassNameId(Contact.class),
				contactTeamRole.getContactId(),
				AuditEntry.Action.UNASSIGN.toString(), "Contact Role",
				StringPool.BLANK, StringPool.BLANK, contactRole.getName(),
				String.valueOf(contactTeamRole.getContactRoleId()),
				contact.getFullName(), serviceContext);
		}
		catch (PortalException pe) {
			throw new ModelListenerException(pe);
		}
	}

}