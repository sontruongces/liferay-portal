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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountContactRoles;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.AccountUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactAccountContactRolesResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/contact-account-contact-roles.properties",
	scope = ServiceScope.PROTOTYPE,
	service = ContactAccountContactRolesResource.class
)
public class ContactAccountContactRolesResourceImpl
	extends BaseContactAccountContactRolesResourceImpl {

	@Override
	public Page<ContactAccountContactRoles>
			getContactAccountContactRolesByOktaIdPage(
				String oktaId, Pagination pagination)
		throws Exception {

		Contact contact = _contactLocalService.getContactByOktaId(oktaId);

		return _getContactAccountContactRolesPage(contact, pagination);
	}

	@Override
	public Page<ContactAccountContactRoles>
			getContactAccountContactRolesByUuidPage(
				String contactUuid, Pagination pagination)
		throws Exception {

		Contact contact = _contactLocalService.getContactByUuid(contactUuid);

		return _getContactAccountContactRolesPage(contact, pagination);
	}

	private Page<ContactAccountContactRoles> _getContactAccountContactRolesPage(
			Contact contact, Pagination pagination)
		throws Exception {

		List<ContactAccountContactRoles> contactAccountContactRolesList =
			new ArrayList<>();

		List<Account> accounts = transform(
			_accountService.getContactAccounts(
				contact.getContactId(), pagination.getStartPosition(),
				pagination.getEndPosition()),
			account -> AccountUtil.toAccount(account));

		for (Account account : accounts) {
			ContactAccountContactRoles contactAccountContactRoles =
				new ContactAccountContactRoles();

			Page<ContactRole> contactRolesPage =
				_contactRoleResource.
					getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
						account.getKey(), contact.getEmailAddress(),
						Pagination.of(1, 1000));

			Collection<ContactRole> contactRoles = contactRolesPage.getItems();

			contactAccountContactRoles.setAccount(account);
			contactAccountContactRoles.setContactRoles(
				contactRoles.toArray(new ContactRole[0]));

			contactAccountContactRolesList.add(contactAccountContactRoles);
		}

		return Page.of(
			contactAccountContactRolesList, pagination,
			_accountService.getContactAccountsCount(contact.getContactId()));
	}

	@Reference
	private AccountService _accountService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleResource _contactRoleResource;

}