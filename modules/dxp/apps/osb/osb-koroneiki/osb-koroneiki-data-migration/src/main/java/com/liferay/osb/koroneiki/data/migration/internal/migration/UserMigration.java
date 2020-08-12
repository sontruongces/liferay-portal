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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = UserMigration.class)
public class UserMigration {

	@Activate
	public void activate() {
		_customerMemberContactRole =
			_contactRoleLocalService.getMemberContactRole(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
					ACCOUNT_CUSTOMER.toString());
		_workerMemberContactRole =
			_contactRoleLocalService.getMemberContactRole(
				com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.
					ACCOUNT_WORKER.toString());
	}

	public void migrate(long userId) throws Exception {
		try (Connection connection = DataAccess.getConnection()) {
			_migrateAccountCustomers(connection, userId);
		}

		try (Connection connection = DataAccess.getConnection()) {
			_migrateAccountWorkers(connection, userId);
		}

		try (Connection connection = DataAccess.getConnection()) {
			_migrateCorpEntries(connection, userId);
		}

		try (Connection connection = DataAccess.getConnection()) {
			_migrateCorpProjects(connection, userId);
		}
	}

	private Account _getAccount(String corpProjectUuid) throws PortalException {
		List<ExternalLink> externalLinks =
			_externalLinkLocalService.getExternalLinks(
				_classNameLocalService.getClassNameId(Account.class),
				ExternalLinkDomain.WEB, ExternalLinkEntityName.WEB_CORP_PROJECT,
				corpProjectUuid, 0, 1);

		if (externalLinks.isEmpty()) {
			return null;
		}

		ExternalLink externalLink = externalLinks.get(0);

		return _accountLocalService.getAccount(externalLink.getClassPK());
	}

	private void _migrateAccountCustomers(Connection connection, long userId)
		throws Exception {

		StringBundler sb = new StringBundler(11);

		sb.append("select CUSTOMER_User.uuid_, CUSTOMER_User.firstName, ");
		sb.append("CUSTOMER_User.middleName, CUSTOMER_User.lastName, ");
		sb.append("CUSTOMER_User.emailAddress, CUSTOMER_User.languageId, ");
		sb.append("CUSTOMER_User.emailAddressVerified, ");
		sb.append("OSB_AccountEntry.corpProjectUuid, ");
		sb.append("OSB_AccountCustomer.role from OSB_AccountCustomer inner ");
		sb.append("join OSB_AccountEntry on OSB_AccountEntry.accountEntryId ");
		sb.append("= OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("CUSTOMER_User on CUSTOMER_User.userId = ");
		sb.append("OSB_AccountCustomer.userId where OSB_AccountEntry.status ");
		sb.append("!= 500");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String contactUuid = resultSet.getString(1);

				Contact contact = _contactLocalService.fetchContactByUuid(
					contactUuid);

				if (contact == null) {
					String contactFirstName = resultSet.getString(2);
					String contactMiddleName = resultSet.getString(3);
					String contactLastName = resultSet.getString(4);
					String contactEmailAddress = resultSet.getString(5);
					String contactLanguageId = resultSet.getString(6);
					boolean emailAddressVerified = resultSet.getBoolean(7);

					contact = _contactLocalService.addContact(
						contactUuid, userId, StringPool.BLANK, contactFirstName,
						contactMiddleName, contactLastName, contactEmailAddress,
						contactLanguageId, emailAddressVerified);
				}

				String corpProjectUuid = resultSet.getString(8);

				Account account = _getAccount(corpProjectUuid);

				if (account == null) {
					_log.error(
						"Unable to find account with corpProjectUuid " +
							corpProjectUuid);

					continue;
				}

				int role = resultSet.getInt(9);

				Long contactRoleId =
					_roleMigration.getAccountCustomerContactRoleId(role);

				if (contactRoleId == null) {
					_log.error("Unable to find contact role from role " + role);

					_contactAccountRoleLocalService.addContactAccountRole(
						contact.getContactId(), account.getAccountId(),
						_customerMemberContactRole.getContactRoleId());

					continue;
				}

				_contactAccountRoleLocalService.addContactAccountRole(
					contact.getContactId(), account.getAccountId(),
					contactRoleId);
			}
		}
	}

	private void _migrateAccountWorkers(Connection connection, long userId)
		throws Exception {

		StringBundler sb = new StringBundler(10);

		sb.append("select CUSTOMER_User.uuid_, CUSTOMER_User.firstName, ");
		sb.append("CUSTOMER_User.middleName, CUSTOMER_User.lastName, ");
		sb.append("CUSTOMER_User.emailAddress, CUSTOMER_User.languageId, ");
		sb.append("CUSTOMER_User.emailAddressVerified, ");
		sb.append("OSB_AccountEntry.corpProjectUuid, OSB_AccountWorker.role ");
		sb.append("from OSB_AccountWorker inner join OSB_AccountEntry on ");
		sb.append("OSB_AccountEntry.accountEntryId = ");
		sb.append("OSB_AccountWorker.accountEntryId inner join CUSTOMER_User ");
		sb.append("on CUSTOMER_User.userId = OSB_AccountWorker.userId where ");
		sb.append("OSB_AccountEntry.status != 500");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String contactUuid = resultSet.getString(1);

				Contact contact = _contactLocalService.fetchContactByUuid(
					contactUuid);

				if (contact == null) {
					String contactFirstName = resultSet.getString(2);
					String contactMiddleName = resultSet.getString(3);
					String contactLastName = resultSet.getString(4);
					String contactEmailAddress = resultSet.getString(5);
					String contactLanguageId = resultSet.getString(6);
					boolean emailAddressVerified = resultSet.getBoolean(7);

					contact = _contactLocalService.addContact(
						contactUuid, userId, StringPool.BLANK, contactFirstName,
						contactMiddleName, contactLastName, contactEmailAddress,
						contactLanguageId, emailAddressVerified);
				}

				String corpProjectUuid = resultSet.getString(8);

				Account account = _getAccount(corpProjectUuid);

				if (account == null) {
					_log.error(
						"Unable to find account with corpProjectUuid " +
							corpProjectUuid);

					continue;
				}

				int role = resultSet.getInt(9);

				Long contactRoleId =
					_roleMigration.getAccountWorkerContactRoleId(role);

				if (contactRoleId == null) {
					_log.error("Unable to find contact role from role " + role);

					_contactAccountRoleLocalService.addContactAccountRole(
						contact.getContactId(), account.getAccountId(),
						_workerMemberContactRole.getContactRoleId());

					continue;
				}

				_contactAccountRoleLocalService.addContactAccountRole(
					contact.getContactId(), account.getAccountId(),
					contactRoleId);
			}
		}
	}

	private void _migrateContactAccountRoles(
			Connection connection, long webUserId, long webOrganizationId,
			long contactId, long accountId)
		throws Exception {

		boolean assignedContactRole = false;

		StringBundler sb = new StringBundler(6);

		sb.append("select roleId from WEB_UserGroupRole inner join WEB_Group ");
		sb.append("on WEB_Group.groupId = WEB_UserGroupRole.groupId where ");
		sb.append("userId = ");
		sb.append(webUserId);
		sb.append(" and WEB_Group.classPK = ");
		sb.append(webOrganizationId);

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long roleId = resultSet.getLong("roleId");

				Long contactRoleId = _roleMigration.getPortalContactRoleId(
					roleId);

				if (contactRoleId == null) {
					_log.error("Unable to find contact role " + roleId);

					continue;
				}

				ContactRole contactRole =
					_contactRoleLocalService.fetchContactRole(contactRoleId);

				if (contactRole == null) {
					_log.error("Unable to find contact role " + roleId);

					continue;
				}

				_contactAccountRoleLocalService.addContactAccountRole(
					contactId, accountId, contactRole.getContactRoleId());

				assignedContactRole = true;
			}
		}

		if (!assignedContactRole) {
			_contactAccountRoleLocalService.addContactAccountRole(
				contactId, accountId,
				_customerMemberContactRole.getContactRoleId());
		}
	}

	private void _migrateCorpEntries(Connection connection, long userId)
		throws Exception {

		StringBundler sb = new StringBundler(8);

		sb.append("select WEB_User.userId, OSB_CorpEntry.organizationId, ");
		sb.append("WEB_User.uuid_, WEB_User.firstName, WEB_User.middleName, ");
		sb.append("WEB_User.lastName, WEB_User.emailAddress, ");
		sb.append("WEB_User.languageId, CUSTOMER_User.emailAddressVerified, ");
		sb.append("OSB_CorpEntry.corpEntryId from WEB_User inner join ");
		sb.append("WEB_Users_Orgs on WEB_Users_Orgs.userId = WEB_User.userId ");
		sb.append("inner join OSB_CorpEntry on OSB_CorpEntry.organizationId ");
		sb.append("= WEB_Users_Orgs.organizationId");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				long webUserId = resultSet.getLong(1);
				long webOrganizationId = resultSet.getLong(2);
				String contactUuid = resultSet.getString(3);
				long accountId = resultSet.getLong(10);

				Contact contact = _contactLocalService.fetchContactByUuid(
					contactUuid);

				if (contact == null) {
					String contactFirstName = resultSet.getString(4);
					String contactMiddleName = resultSet.getString(5);
					String contactLastName = resultSet.getString(6);
					String contactEmailAddress = resultSet.getString(7);
					String contactLanguageId = resultSet.getString(8);
					boolean emailAddressVerified = resultSet.getBoolean(9);

					contact = _contactLocalService.addContact(
						contactUuid, userId, StringPool.BLANK, contactFirstName,
						contactMiddleName, contactLastName, contactEmailAddress,
						contactLanguageId, emailAddressVerified);
				}

				_migrateContactAccountRoles(
					connection, webUserId, webOrganizationId,
					contact.getContactId(), accountId);
			}
		}
	}

	private void _migrateCorpProjects(Connection connection, long userId)
		throws Exception {

		StringBundler sb = new StringBundler(11);

		sb.append("select WEB_User.userId, OSB_CorpProject.organizationId, ");
		sb.append("WEB_User.uuid_, WEB_User.firstName, WEB_User.middleName, ");
		sb.append("WEB_User.lastName, WEB_User.emailAddress, ");
		sb.append("WEB_User.languageId, CUSTOMER_User.emailAddressVerified, ");
		sb.append("OSB_CorpProject.uuid_ from WEB_User inner join ");
		sb.append("WEB_Users_Orgs on WEB_Users_Orgs.userId = WEB_User.userId ");
		sb.append("inner join OSB_CorpProject on ");
		sb.append("OSB_CorpProject.organizationId = ");
		sb.append("WEB_Users_Orgs.organizationId inner join OSB_AccountEntry ");
		sb.append("on OSB_AccountEntry.corpProjectUuid = ");
		sb.append("OSB_CorpProject.uuid_ where OSB_AccountEntry.status != 500");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String contactUuid = resultSet.getString(3);
				String corpProjectUuid = resultSet.getString(10);

				Contact contact = _contactLocalService.fetchContactByUuid(
					contactUuid);

				if (contact == null) {
					String contactFirstName = resultSet.getString(4);
					String contactMiddleName = resultSet.getString(5);
					String contactLastName = resultSet.getString(6);
					String contactEmailAddress = resultSet.getString(7);
					String contactLanguageId = resultSet.getString(8);
					boolean emailAddressVerified = resultSet.getBoolean(9);

					contact = _contactLocalService.addContact(
						contactUuid, userId, StringPool.BLANK, contactFirstName,
						contactMiddleName, contactLastName, contactEmailAddress,
						contactLanguageId, emailAddressVerified);
				}

				Account account = _getAccount(corpProjectUuid);

				if (account == null) {
					_log.error(
						"Unable to find account with corpProjectUuid " +
							corpProjectUuid);

					continue;
				}

				long webUserId = resultSet.getLong(1);
				long webOrganizationId = resultSet.getLong(2);

				_migrateContactAccountRoles(
					connection, webUserId, webOrganizationId,
					contact.getContactId(), account.getAccountId());
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UserMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactAccountRoleLocalService _contactAccountRoleLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	private ContactRole _customerMemberContactRole;

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private RoleMigration _roleMigration;

	@Reference
	private UserLocalService _userLocalService;

	private ContactRole _workerMemberContactRole;

}