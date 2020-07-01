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

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.ContactAccountRoleServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ContactAccountRole"
	},
	service = AopService.class
)
public class ContactAccountRoleServiceImpl
	extends ContactAccountRoleServiceBaseImpl {

	public ContactAccountRole addContactAccountRole(
			long contactId, long accountId, long contactRoleId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId,
			TaprootActionKeys.ASSIGN_CONTACT);

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId,
			TaprootActionKeys.ASSIGN_CONTACT);

		return contactAccountRoleLocalService.addContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	public ContactAccountRole deleteContactAccountRole(
			long contactId, long accountId, long contactRoleId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId,
			TaprootActionKeys.ASSIGN_CONTACT);

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId,
			TaprootActionKeys.ASSIGN_CONTACT);

		return contactAccountRoleLocalService.deleteContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	public void deleteContactAccountRoles(
			long contactId, long accountId, String contactRoleType)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId,
			TaprootActionKeys.ASSIGN_CONTACT);

		List<ContactAccountRole> contactAccountRoles =
			contactAccountRolePersistence.findByCI_AI(contactId, accountId);

		for (ContactAccountRole contactAccountRole : contactAccountRoles) {
			ContactRole contactRole = contactAccountRole.getContactRole();

			if (!contactRoleType.equals(contactRole.getType())) {
				continue;
			}

			_contactRolePermission.check(
				getPermissionChecker(), contactRole,
				TaprootActionKeys.ASSIGN_CONTACT);

			contactAccountRoleLocalService.deleteContactAccountRole(
				contactId, accountId, contactRole.getContactRoleId());
		}

		_contactTeamRoleLocalService.deleteAccountTeamContact(
			accountId, contactId);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactRolePermission _contactRolePermission;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

}