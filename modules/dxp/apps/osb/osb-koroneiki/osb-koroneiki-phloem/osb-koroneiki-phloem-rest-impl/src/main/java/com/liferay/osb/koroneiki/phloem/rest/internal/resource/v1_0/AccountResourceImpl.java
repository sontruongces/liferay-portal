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
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.AccountUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.AccountEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.constants.ContactRoleType;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactService;
import com.liferay.osb.koroneiki.taproot.service.TeamAccountRoleService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl
	extends BaseAccountResourceImpl implements EntityModelResource {

	@Override
	public void deleteAccount(String accountKey) throws Exception {
		_accountService.deleteAccount(accountKey);
	}

	@Override
	public void deleteAccountAccountPermission(
			String accountKey, AccountPermission accountPermission)
		throws Exception {

		_updateAccountPermission(accountKey, "delete", accountPermission);
	}

	@Override
	public void deleteAccountAssignedTeamTeamKeyRole(
			String accountKey, String teamKey, String[] teamRoleKeys)
		throws Exception {

		for (String teamRoleKey : teamRoleKeys) {
			_teamAccountRoleService.deleteTeamAccountRole(
				teamKey, accountKey, teamRoleKey);
		}
	}

	@Override
	public void deleteAccountContactByOkta(String accountKey, String[] oktaIds)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(oktaIds.length);

		for (String oktaId : oktaIds) {
			contacts.add(
				_oktaContactIdentityProvider.getContactByProviderId(oktaId));
		}

		_deleteAccountContacts(contacts, accountKey);
	}

	@Override
	public void deleteAccountContactByOktaRole(
			String accountKey, String oktaId, String[] contactRoleKeys)
		throws Exception {

		_deleteAccountContactRole(
			_oktaContactIdentityProvider.getContactByProviderId(oktaId),
			accountKey, contactRoleKeys);
	}

	@Override
	public void deleteAccountContactByUuid(
			String accountKey, String[] contactUuids)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(contactUuids.length);

		for (String contactUuid : contactUuids) {
			contacts.add(
				_webContactIdentityProvider.getContactByProviderId(
					contactUuid));
		}

		_deleteAccountContacts(contacts, accountKey);
	}

	@Override
	public void deleteAccountContactByUuidContactUuidRole(
			String accountKey, String contactUuid, String[] contactRoleKeys)
		throws Exception {

		_deleteAccountContactRole(
			_webContactIdentityProvider.getContactByProviderId(contactUuid),
			accountKey, contactRoleKeys);
	}

	@Override
	public Account getAccount(String accountKey) throws Exception {
		return AccountUtil.toAccount(
			_accountService.getAccount(accountKey),
			contextAcceptLanguage.getPreferredLocale());
	}

	@Override
	public Page<Account> getAccountByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_accountService.getAccounts(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(
					account, contextAcceptLanguage.getPreferredLocale())),
			pagination,
			_accountService.getAccountsCount(domain, entityName, entityId));
	}

	@Override
	public Page<Account> getAccountChildAccountsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Account curAccount =
			_accountLocalService.getAccount(accountKey);

		return Page.of(
			transform(
				_accountService.getAccounts(
					curAccount.getAccountId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(
					account, contextAcceptLanguage.getPreferredLocale())),
			pagination,
			_accountService.getAccountsCount(curAccount.getAccountId()));
	}

	@Override
	public Page<Account> getAccountsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.Account.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> AccountUtil.toAccount(
				_accountLocalService.getAccount(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))),
				contextAcceptLanguage.getPreferredLocale()),
			sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Page<Account> getTeamTeamKeyAssignedAccountsPage(
			String teamKey, Pagination pagination)
		throws Exception {

		Team team = _teamLocalService.getTeam(teamKey);

		return Page.of(
			transform(
				_accountService.getTeamAccounts(
					team.getTeamId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				account -> AccountUtil.toAccount(
					account, contextAcceptLanguage.getPreferredLocale())),
			pagination, _accountService.getTeamAccountsCount(team.getTeamId()));
	}

	@Override
	public Account postAccount(Account account) throws Exception {
		String industry = StringPool.BLANK;

		Account.Industry accountIndustry = account.getIndustry();

		if (accountIndustry != null) {
			industry = accountIndustry.toString();
		}

		String tier = StringPool.BLANK;

		Account.Tier accountTier = account.getTier();

		if (accountTier != null) {
			tier = accountTier.toString();
		}

		int status = WorkflowConstants.STATUS_APPROVED;

		Account.Status accountStatus = account.getStatus();

		if (accountStatus != null) {
			status = WorkflowConstants.getLabelStatus(accountStatus.toString());
		}

		return AccountUtil.toAccount(
			_accountService.addAccount(
				0, account.getName(), account.getCode(),
				account.getDescription(), account.getNotes(),
				GetterUtil.getLong(account.getLogoId()),
				account.getContactEmailAddress(),
				account.getProfileEmailAddress(), account.getPhoneNumber(),
				account.getFaxNumber(), account.getWebsite(), industry, tier,
				account.getSoldBy(), account.getInternal(), status),
			contextAcceptLanguage.getPreferredLocale());
	}

	@Override
	public Account postAccountChildAccount(String accountKey, Account account)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Account parentAccount =
			_accountLocalService.getAccount(accountKey);

		String industry = StringPool.BLANK;

		Account.Industry accountIndustry = account.getIndustry();

		if (accountIndustry != null) {
			industry = accountIndustry.toString();
		}

		String tier = StringPool.BLANK;

		Account.Tier accountTier = account.getTier();

		if (accountTier != null) {
			tier = accountTier.toString();
		}

		int status = WorkflowConstants.STATUS_APPROVED;

		Account.Status accountStatus = account.getStatus();

		if (accountStatus != null) {
			status = WorkflowConstants.getLabelStatus(accountStatus.toString());
		}

		return AccountUtil.toAccount(
			_accountService.addAccount(
				parentAccount.getAccountId(), account.getName(),
				account.getCode(), account.getDescription(), account.getNotes(),
				0, account.getContactEmailAddress(),
				account.getProfileEmailAddress(), account.getPhoneNumber(),
				account.getFaxNumber(), account.getWebsite(), industry, tier,
				account.getSoldBy(), account.getInternal(), status),
			contextAcceptLanguage.getPreferredLocale());
	}

	@Override
	public Account putAccount(String accountKey, Account account)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Account curAccount =
			_accountLocalService.getAccount(accountKey);

		String code = GetterUtil.getString(
			account.getCode(), curAccount.getCode());
		String description = GetterUtil.getString(
			account.getDescription(), curAccount.getDescription());
		String notes = GetterUtil.getString(
			account.getNotes(), curAccount.getNotes());
		long logoId = GetterUtil.getLong(
			account.getLogoId(), curAccount.getLogoId());
		String contactEmailAddress = GetterUtil.getString(
			account.getContactEmailAddress(),
			curAccount.getContactEmailAddress());
		String profileEmailAddress = GetterUtil.getString(
			account.getProfileEmailAddress(),
			curAccount.getProfileEmailAddress());
		String phoneNumber = GetterUtil.getString(
			account.getPhoneNumber(), curAccount.getPhoneNumber());
		String faxNumber = GetterUtil.getString(
			account.getFaxNumber(), curAccount.getFaxNumber());
		String website = GetterUtil.getString(
			account.getWebsite(), curAccount.getWebsite());

		String industry = curAccount.getIndustry();

		Account.Industry accountIndustry = account.getIndustry();

		if (accountIndustry != null) {
			industry = accountIndustry.toString();
		}

		String tier = curAccount.getTier();

		Account.Tier accountTier = account.getTier();

		if (accountTier != null) {
			tier = accountTier.toString();
		}

		String soldBy = GetterUtil.getString(
			account.getSoldBy(), curAccount.getSoldBy());
		boolean internal = GetterUtil.getBoolean(
			account.getInternal(), curAccount.getInternal());

		int status = curAccount.getStatus();

		Account.Status accountStatus = account.getStatus();

		if (accountStatus != null) {
			status = WorkflowConstants.getLabelStatus(accountStatus.toString());
		}

		return AccountUtil.toAccount(
			_accountService.updateAccount(
				accountKey, curAccount.getParentAccountId(), account.getName(),
				code, description, notes, logoId, contactEmailAddress,
				profileEmailAddress, phoneNumber, faxNumber, website, industry,
				tier, soldBy, internal, status),
			contextAcceptLanguage.getPreferredLocale());
	}

	@Override
	public void putAccountAccountPermission(
			String accountKey, AccountPermission accountPermission)
		throws Exception {

		_updateAccountPermission(accountKey, "add", accountPermission);
	}

	@Override
	public void putAccountAssignedTeamTeamKeyRole(
			String accountKey, String teamKey, String[] teamRoleKeys)
		throws Exception {

		for (String teamRoleKey : teamRoleKeys) {
			_teamAccountRoleService.addTeamAccountRole(
				teamKey, accountKey, teamRoleKey);
		}
	}

	@Override
	public void putAccountContactByOkta(String accountKey, String[] oktaIds)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(oktaIds.length);

		for (String oktaId : oktaIds) {
			contacts.add(
				_oktaContactIdentityProvider.getContactByProviderId(oktaId));
		}

		_putAccountContacts(contacts, accountKey);
	}

	@Override
	public void putAccountContactByOktaRole(
			String accountKey, String oktaId, String[] contactRoleKeys)
		throws Exception {

		_putAccountContactRole(
			_oktaContactIdentityProvider.getContactByProviderId(oktaId),
			accountKey, contactRoleKeys);
	}

	@Override
	public void putAccountContactByUuid(
			String accountKey, String[] contactUuids)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(contactUuids.length);

		for (String contactUuid : contactUuids) {
			contacts.add(
				_webContactIdentityProvider.getContactByProviderId(
					contactUuid));
		}

		_putAccountContacts(contacts, accountKey);
	}

	@Override
	public void putAccountContactByUuidContactUuidRole(
			String accountKey, String contactUuid, String[] contactRoleKeys)
		throws Exception {

		_putAccountContactRole(
			_webContactIdentityProvider.getContactByProviderId(contactUuid),
			accountKey, contactRoleKeys);
	}

	private void _deleteAccountContactRole(
			Contact contact, String accountKey, String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		for (String contactRoleKey : contactRoleKeys) {
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactRoleKey);

			_contactAccountRoleService.deleteContactAccountRole(
				contact.getContactId(), account.getAccountId(),
				contactRole.getContactRoleId());
		}
	}

	private void _deleteAccountContacts(
			List<Contact> contacts, String accountKey)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		for (Contact contact : contacts) {
			_contactAccountRoleService.deleteContactAccountRoles(
				contact.getContactId(), account.getAccountId());
		}
	}

	private void _putAccountContactRole(
			Contact contact, String accountKey, String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		for (String contactRoleKey : contactRoleKeys) {
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactRoleKey);

			_contactAccountRoleService.addContactAccountRole(
				contact.getContactId(), account.getAccountId(),
				contactRole.getContactRoleId());
		}
	}

	private void _putAccountContacts(List<Contact> contacts, String accountKey)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		ContactRole contactRole = _contactRoleLocalService.getMemberContactRole(
			ContactRoleType.ACCOUNT);

		for (Contact contact : contacts) {
			_contactAccountRoleService.addContactAccountRole(
				contact.getContactId(), account.getAccountId(),
				contactRole.getContactRoleId());
		}
	}

	private void _updateAccountPermission(
			String accountKey, String operation,
			AccountPermission accountPermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Account account =
			_accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			PermissionThreadLocal.getPermissionChecker(), account,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(accountPermission.getAssignContact())) {
			actionIds.add(TaprootActionKeys.ASSIGN_CONTACT);
		}

		if (GetterUtil.getBoolean(accountPermission.getAssignTeam())) {
			actionIds.add(TaprootActionKeys.ASSIGN_TEAM);
		}

		if (GetterUtil.getBoolean(accountPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(accountPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(accountPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(accountPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.Account.class.getName(),
			account.getAccountId(), accountPermission.getRoleNames(),
			actionIds);
	}

	private static final EntityModel _entityModel = new AccountEntityModel();

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.AccountPermission
		_accountPermission;

	@Reference
	private AccountService _accountService;

	@Reference
	private ContactAccountRoleService _contactAccountRoleService;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactService _contactService;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _oktaContactIdentityProvider;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private TeamAccountRoleService _teamAccountRoleService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference(target = "(provider=web)")
	private ContactIdentityProvider _webContactIdentityProvider;

}