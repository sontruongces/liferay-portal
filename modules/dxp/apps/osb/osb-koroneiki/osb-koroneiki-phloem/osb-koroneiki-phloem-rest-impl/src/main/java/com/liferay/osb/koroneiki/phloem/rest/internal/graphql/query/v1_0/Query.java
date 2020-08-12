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

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.query.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AuditEntryResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactAccountViewResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class Query {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setAuditEntryResourceComponentServiceObjects(
		ComponentServiceObjects<AuditEntryResource>
			auditEntryResourceComponentServiceObjects) {

		_auditEntryResourceComponentServiceObjects =
			auditEntryResourceComponentServiceObjects;
	}

	public static void setContactResourceComponentServiceObjects(
		ComponentServiceObjects<ContactResource>
			contactResourceComponentServiceObjects) {

		_contactResourceComponentServiceObjects =
			contactResourceComponentServiceObjects;
	}

	public static void setContactAccountViewResourceComponentServiceObjects(
		ComponentServiceObjects<ContactAccountViewResource>
			contactAccountViewResourceComponentServiceObjects) {

		_contactAccountViewResourceComponentServiceObjects =
			contactAccountViewResourceComponentServiceObjects;
	}

	public static void setContactRoleResourceComponentServiceObjects(
		ComponentServiceObjects<ContactRoleResource>
			contactRoleResourceComponentServiceObjects) {

		_contactRoleResourceComponentServiceObjects =
			contactRoleResourceComponentServiceObjects;
	}

	public static void setEntitlementDefinitionResourceComponentServiceObjects(
		ComponentServiceObjects<EntitlementDefinitionResource>
			entitlementDefinitionResourceComponentServiceObjects) {

		_entitlementDefinitionResourceComponentServiceObjects =
			entitlementDefinitionResourceComponentServiceObjects;
	}

	public static void setExternalLinkResourceComponentServiceObjects(
		ComponentServiceObjects<ExternalLinkResource>
			externalLinkResourceComponentServiceObjects) {

		_externalLinkResourceComponentServiceObjects =
			externalLinkResourceComponentServiceObjects;
	}

	public static void setNoteResourceComponentServiceObjects(
		ComponentServiceObjects<NoteResource>
			noteResourceComponentServiceObjects) {

		_noteResourceComponentServiceObjects =
			noteResourceComponentServiceObjects;
	}

	public static void setPostalAddressResourceComponentServiceObjects(
		ComponentServiceObjects<PostalAddressResource>
			postalAddressResourceComponentServiceObjects) {

		_postalAddressResourceComponentServiceObjects =
			postalAddressResourceComponentServiceObjects;
	}

	public static void setProductResourceComponentServiceObjects(
		ComponentServiceObjects<ProductResource>
			productResourceComponentServiceObjects) {

		_productResourceComponentServiceObjects =
			productResourceComponentServiceObjects;
	}

	public static void setProductConsumptionResourceComponentServiceObjects(
		ComponentServiceObjects<ProductConsumptionResource>
			productConsumptionResourceComponentServiceObjects) {

		_productConsumptionResourceComponentServiceObjects =
			productConsumptionResourceComponentServiceObjects;
	}

	public static void setProductPurchaseResourceComponentServiceObjects(
		ComponentServiceObjects<ProductPurchaseResource>
			productPurchaseResourceComponentServiceObjects) {

		_productPurchaseResourceComponentServiceObjects =
			productPurchaseResourceComponentServiceObjects;
	}

	public static void setProductPurchaseViewResourceComponentServiceObjects(
		ComponentServiceObjects<ProductPurchaseViewResource>
			productPurchaseViewResourceComponentServiceObjects) {

		_productPurchaseViewResourceComponentServiceObjects =
			productPurchaseViewResourceComponentServiceObjects;
	}

	public static void setTeamResourceComponentServiceObjects(
		ComponentServiceObjects<TeamResource>
			teamResourceComponentServiceObjects) {

		_teamResourceComponentServiceObjects =
			teamResourceComponentServiceObjects;
	}

	public static void setTeamRoleResourceComponentServiceObjects(
		ComponentServiceObjects<TeamRoleResource>
			teamRoleResourceComponentServiceObjects) {

		_teamRoleResourceComponentServiceObjects =
			teamRoleResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accounts(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage accounts(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getAccountsPage(
					search,
					_filterBiFunction.apply(accountResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(accountResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage accountByExternalLinkDomainEntityNameEntity(
			@GraphQLName("domain") String domain,
			@GraphQLName("entityName") String entityName,
			@GraphQLName("entityId") String entityId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.
					getAccountByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {account(accountKey: ___){assignedTeams, code, contactEmailAddress, contacts, customerContacts, dateCreated, dateModified, description, entitlements, externalLinks, faxNumber, internal, key, logoId, name, parentAccountKey, phoneNumber, postalAddresses, productPurchases, profileEmailAddress, region, status, tier, website, workerContacts}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Account account(@GraphQLName("accountKey") String accountKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.getAccount(accountKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountChildAccounts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage accountChildAccounts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getAccountChildAccountsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOktaAccounts(oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage contactByOktaAccounts(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getContactByOktaAccountsPage(
					oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidAccounts(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage contactByUuidContactUuidAccounts(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getContactByUuidContactUuidAccountsPage(
					contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyAssignedAccounts(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage teamTeamKeyAssignedAccounts(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getTeamTeamKeyAssignedAccountsPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyAuditEntries(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntryPage accountAccountKeyAuditEntries(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getAccountAccountKeyAuditEntriesPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {auditEntry(auditEntryKey: ___){action, agentName, agentUID, auditSetId, dateCreated, description, field, key, newValue, oldValue, summary}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntry auditEntry(
			@GraphQLName("auditEntryKey") String auditEntryKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> auditEntryResource.getAuditEntry(
				auditEntryKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRoleContactRoleKeyAuditEntries(contactRoleKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntryPage contactRoleContactRoleKeyAuditEntries(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getContactRoleContactRoleKeyAuditEntriesPage(
					contactRoleKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOktaAuditEntries(oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntryPage contactByOktaAuditEntries(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getContactByOktaAuditEntriesPage(
					oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidAuditEntries(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntryPage contactByUuidContactUuidAuditEntries(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getContactByUuidContactUuidAuditEntriesPage(
					contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRoleTeamRoleKeyAuditEntries(page: ___, pageSize: ___, teamRoleKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntryPage teamRoleTeamRoleKeyAuditEntries(
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getTeamRoleTeamRoleKeyAuditEntriesPage(
					teamRoleKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyAuditEntries(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AuditEntryPage teamTeamKeyAuditEntries(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_auditEntryResourceComponentServiceObjects,
			this::_populateResourceContext,
			auditEntryResource -> new AuditEntryPage(
				auditEntryResource.getTeamTeamKeyAuditEntriesPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContacts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactPage accountAccountKeyContacts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getAccountAccountKeyContactsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContacts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactPage accountAccountKeyCustomerContacts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getAccountAccountKeyCustomerContactsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContacts(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactPage accountAccountKeyWorkerContacts(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getAccountAccountKeyWorkerContactsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contacts(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactPage contacts(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getContactsPage(
					search,
					_filterBiFunction.apply(contactResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(contactResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByEmailAddresEmailAddress(emailAddress: ___){contactRoles, dateCreated, dateModified, emailAddress, emailAddressVerified, entitlements, externalLinks, firstName, key, languageId, lastName, middleName, oktaId, teams, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Contact contactByEmailAddresEmailAddress(
			@GraphQLName("emailAddress") String emailAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.getContactByEmailAddresEmailAddress(
					emailAddress));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOkta(oktaId: ___){contactRoles, dateCreated, dateModified, emailAddress, emailAddressVerified, entitlements, externalLinks, firstName, key, languageId, lastName, middleName, oktaId, teams, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Contact contactByOkta(@GraphQLName("oktaId") String oktaId)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.getContactByOkta(oktaId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuid(contactUuid: ___){contactRoles, dateCreated, dateModified, emailAddress, emailAddressVerified, entitlements, externalLinks, firstName, key, languageId, lastName, middleName, oktaId, teams, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Contact contactByUuidContactUuid(
			@GraphQLName("contactUuid") String contactUuid)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.getContactByUuidContactUuid(
				contactUuid));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContacts(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactPage teamTeamKeyContacts(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> new ContactPage(
				contactResource.getTeamTeamKeyContactsPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOktaContactAccountViews(oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactAccountViewPage contactByOktaContactAccountViews(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactAccountViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactAccountViewResource -> new ContactAccountViewPage(
				contactAccountViewResource.
					getContactByOktaContactAccountViewsPage(
						oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidContactAccountViews(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactAccountViewPage contactByUuidContactUuidContactAccountViews(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactAccountViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactAccountViewResource -> new ContactAccountViewPage(
				contactAccountViewResource.
					getContactByUuidContactUuidContactAccountViewsPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContactByEmailAddresContactEmailAddressRoles(accountKey: ___, contactEmailAddress: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage
			accountAccountKeyContactByEmailAddresContactEmailAddressRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactEmailAddress") String contactEmailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
						accountKey, contactEmailAddress,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContactByOktaRoles(accountKey: ___, oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage accountAccountKeyContactByOktaRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.getAccountAccountKeyContactByOktaRolesPage(
					accountKey, oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyContactByUuidContactUuidRoles(accountKey: ___, contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage accountAccountKeyContactByUuidContactUuidRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyContactByUuidContactUuidRolesPage(
						accountKey, contactUuid,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContactByEmailAddresContactEmailAddressRoles(accountKey: ___, contactEmailAddress: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage
			accountAccountKeyCustomerContactByEmailAddresContactEmailAddressRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactEmailAddress") String contactEmailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
						accountKey, contactEmailAddress,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContactByOktaRoles(accountKey: ___, oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage accountAccountKeyCustomerContactByOktaRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyCustomerContactByOktaRolesPage(
						accountKey, oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyCustomerContactByUuidContactUuidRoles(accountKey: ___, contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage
			accountAccountKeyCustomerContactByUuidContactUuidRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
						accountKey, contactUuid,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContactByEmailAddresContactEmailAddressRoles(accountKey: ___, contactEmailAddress: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage
			accountAccountKeyWorkerContactByEmailAddresContactEmailAddressRoles(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("contactEmailAddress") String contactEmailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
						accountKey, contactEmailAddress,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContactByOktaRoles(accountKey: ___, oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage accountAccountKeyWorkerContactByOktaRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyWorkerContactByOktaRolesPage(
						accountKey, oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyWorkerContactByUuidContactUuidRoles(accountKey: ___, contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage accountAccountKeyWorkerContactByUuidContactUuidRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
						accountKey, contactUuid,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRoles(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage contactRoles(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.getContactRolesPage(
					search,
					_filterBiFunction.apply(contactRoleResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(contactRoleResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRole(contactRoleKey: ___){dateCreated, dateModified, description, key, name, system, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRole contactRole(
			@GraphQLName("contactRoleKey") String contactRoleKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.getContactRole(
				contactRoleKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactRoleContactRoleTypeContactRoleName(contactRoleName: ___, contactRoleType: ___){dateCreated, dateModified, description, key, name, system, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRole contactRoleContactRoleTypeContactRoleName(
			@GraphQLName("contactRoleType") String contactRoleType,
			@GraphQLName("contactRoleName") String contactRoleName)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource ->
				contactRoleResource.
					getContactRoleContactRoleTypeContactRoleName(
						contactRoleType, contactRoleName));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContactByEmailAddressRoles(emailAddress: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage teamTeamKeyContactByEmailAddressRoles(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("emailAddress") String emailAddress,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getTeamTeamKeyContactByEmailAddressRolesPage(
						teamKey, emailAddress, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContactByOktaRoles(oktaId: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage teamTeamKeyContactByOktaRoles(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.getTeamTeamKeyContactByOktaRolesPage(
					teamKey, oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyContactByUuidContactUuidRoles(contactUuid: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ContactRolePage teamTeamKeyContactByUuidContactUuidRoles(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> new ContactRolePage(
				contactRoleResource.
					getTeamTeamKeyContactByUuidContactUuidRolesPage(
						teamKey, contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountEntitlementDefinitions(page: ___, pageSize: ___, search: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EntitlementDefinitionPage accountEntitlementDefinitions(
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource -> new EntitlementDefinitionPage(
				entitlementDefinitionResource.
					getAccountEntitlementDefinitionsPage(
						search, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactEntitlementDefinitions(page: ___, pageSize: ___, search: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EntitlementDefinitionPage contactEntitlementDefinitions(
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource -> new EntitlementDefinitionPage(
				entitlementDefinitionResource.
					getContactEntitlementDefinitionsPage(
						search, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {entitlementDefinition(entitlementDefinitionKey: ___){dateCreated, dateModified, definition, description, externalLinks, key, name, status}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EntitlementDefinition entitlementDefinition(
			@GraphQLName("entitlementDefinitionKey") String
				entitlementDefinitionKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.getEntitlementDefinition(
					entitlementDefinitionKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyExternalLinks(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage accountAccountKeyExternalLinks(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getAccountAccountKeyExternalLinksPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOktaExternalLinks(oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage contactByOktaExternalLinks(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getContactByOktaExternalLinksPage(
					oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidExternalLinks(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage contactByUuidContactUuidExternalLinks(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getContactByUuidContactUuidExternalLinksPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {externalLink(externalLinkKey: ___){dateCreated, domain, entityId, entityName, key, url}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLink externalLink(
			@GraphQLName("externalLinkKey") String externalLinkKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.getExternalLink(
				externalLinkKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumptionProductConsumptionKeyExternalLinks(page: ___, pageSize: ___, productConsumptionKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage
			productConsumptionProductConsumptionKeyExternalLinks(
				@GraphQLName("productConsumptionKey") String
					productConsumptionKey,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getProductConsumptionProductConsumptionKeyExternalLinksPage(
						productConsumptionKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchaseProductPurchaseKeyExternalLinks(page: ___, pageSize: ___, productPurchaseKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage productPurchaseProductPurchaseKeyExternalLinks(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.
					getProductPurchaseProductPurchaseKeyExternalLinksPage(
						productPurchaseKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productProductKeyExternalLinks(page: ___, pageSize: ___, productKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage productProductKeyExternalLinks(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getProductProductKeyExternalLinksPage(
					productKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamTeamKeyExternalLinks(page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ExternalLinkPage teamTeamKeyExternalLinks(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> new ExternalLinkPage(
				externalLinkResource.getTeamTeamKeyExternalLinksPage(
					teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyNotes(accountKey: ___, page: ___, pageSize: ___, status: ___, type: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public NotePage accountAccountKeyNotes(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("status") String status,
			@GraphQLName("type") String type,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> new NotePage(
				noteResource.getAccountAccountKeyNotesPage(
					accountKey, status, type, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {note(noteKey: ___){content, creatorName, creatorUID, dateCreated, dateModified, format, key, modifierName, modifierUID, priority, status, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Note note(@GraphQLName("noteKey") String noteKey) throws Exception {
		return _applyComponentServiceObjects(
			_noteResourceComponentServiceObjects,
			this::_populateResourceContext,
			noteResource -> noteResource.getNote(noteKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyPostalAddresses(accountKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public PostalAddressPage accountAccountKeyPostalAddresses(
			@GraphQLName("accountKey") String accountKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> new PostalAddressPage(
				postalAddressResource.getAccountAccountKeyPostalAddressesPage(
					accountKey)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {postalAddress(postalAddressId: ___){addressCountry, addressLocality, addressRegion, addressType, id, mailing, postalCode, primary, streetAddressLine1, streetAddressLine2, streetAddressLine3}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public PostalAddress postalAddress(
			@GraphQLName("postalAddressId") Long postalAddressId)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> postalAddressResource.getPostalAddress(
				postalAddressId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {products(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPage products(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> new ProductPage(
				productResource.getProductsPage(
					search,
					_filterBiFunction.apply(productResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(productResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPage productByExternalLinkDomainEntityNameEntity(
			@GraphQLName("domain") String domain,
			@GraphQLName("entityName") String entityName,
			@GraphQLName("entityId") String entityId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> new ProductPage(
				productResource.
					getProductByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productByNameProductName(productName: ___){dateCreated, dateModified, externalLinks, key, name, properties}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Product productByNameProductName(
			@GraphQLName("productName") String productName)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.getProductByNameProductName(
				productName));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {product(productKey: ___){dateCreated, dateModified, externalLinks, key, name, properties}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Product product(@GraphQLName("productKey") String productKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.getProduct(productKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductConsumptions(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductConsumptionPage accountAccountKeyProductConsumptions(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getAccountAccountKeyProductConsumptionsPage(
						accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOktaProductConsumptions(oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductConsumptionPage contactByOktaProductConsumptions(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getContactByOktaProductConsumptionsPage(
						oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidProductConsumptions(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductConsumptionPage contactByUuidContactUuidProductConsumptions(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getContactByUuidContactUuidProductConsumptionsPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumptions(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductConsumptionPage productConsumptions(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.getProductConsumptionsPage(
					search,
					_filterBiFunction.apply(
						productConsumptionResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						productConsumptionResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumptionByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductConsumptionPage
			productConsumptionByExternalLinkDomainEntityNameEntity(
				@GraphQLName("domain") String domain,
				@GraphQLName("entityName") String entityName,
				@GraphQLName("entityId") String entityId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource -> new ProductConsumptionPage(
				productConsumptionResource.
					getProductConsumptionByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productConsumption(productConsumptionKey: ___){accountKey, dateCreated, endDate, externalLinks, key, productKey, productPurchaseKey, properties, startDate}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductConsumption productConsumption(
			@GraphQLName("productConsumptionKey") String productConsumptionKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.getProductConsumption(
					productConsumptionKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductPurchases(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchasePage accountAccountKeyProductPurchases(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.
					getAccountAccountKeyProductPurchasesPage(
						accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByOktaProductPurchases(oktaId: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchasePage contactByOktaProductPurchases(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.getContactByOktaProductPurchasesPage(
					oktaId, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {contactByUuidContactUuidProductPurchases(contactUuid: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchasePage contactByUuidContactUuidProductPurchases(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.
					getContactByUuidContactUuidProductPurchasesPage(
						contactUuid, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchases(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchasePage productPurchases(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.getProductPurchasesPage(
					search,
					_filterBiFunction.apply(
						productPurchaseResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						productPurchaseResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchaseByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchasePage
			productPurchaseByExternalLinkDomainEntityNameEntity(
				@GraphQLName("domain") String domain,
				@GraphQLName("entityName") String entityName,
				@GraphQLName("entityId") String entityId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource -> new ProductPurchasePage(
				productPurchaseResource.
					getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
						domain, entityName, entityId,
						Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchase(productPurchaseKey: ___){accountKey, dateCreated, endDate, externalLinks, key, originalEndDate, perpetual, product, productKey, properties, quantity, startDate, status}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchase productPurchase(
			@GraphQLName("productPurchaseKey") String productPurchaseKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.getProductPurchase(productPurchaseKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyProductProductKeyProductPurchaseView(accountKey: ___, productKey: ___){product, productConsumptions, productPurchases}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchaseView
			accountAccountKeyProductProductKeyProductPurchaseView(
				@GraphQLName("accountKey") String accountKey,
				@GraphQLName("productKey") String productKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseViewResource ->
				productPurchaseViewResource.
					getAccountAccountKeyProductProductKeyProductPurchaseView(
						accountKey, productKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {productPurchaseViews(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ProductPurchaseViewPage productPurchaseViews(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseViewResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseViewResource -> new ProductPurchaseViewPage(
				productPurchaseViewResource.getProductPurchaseViewsPage(
					search,
					_filterBiFunction.apply(
						productPurchaseViewResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(
						productPurchaseViewResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyAssignedTeams(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamPage accountAccountKeyAssignedTeams(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getAccountAccountKeyAssignedTeamsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyTeams(accountKey: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamPage accountAccountKeyTeams(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getAccountAccountKeyTeamsPage(
					accountKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teams(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamPage teams(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getTeamsPage(
					search, _filterBiFunction.apply(teamResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(teamResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamByExternalLinkDomainEntityNameEntity(domain: ___, entityId: ___, entityName: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamPage teamByExternalLinkDomainEntityNameEntity(
			@GraphQLName("domain") String domain,
			@GraphQLName("entityName") String entityName,
			@GraphQLName("entityId") String entityId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> new TeamPage(
				teamResource.getTeamByExternalLinkDomainEntityNameEntityPage(
					domain, entityName, entityId,
					Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {team(teamKey: ___){account, accountKey, contacts, dateCreated, dateModified, externalLinks, key, name, system, teamRoles}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Team team(@GraphQLName("teamKey") String teamKey) throws Exception {
		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.getTeam(teamKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accountAccountKeyAssignedTeamTeamKeyRoles(accountKey: ___, page: ___, pageSize: ___, teamKey: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamRolePage accountAccountKeyAssignedTeamTeamKeyRoles(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> new TeamRolePage(
				teamRoleResource.
					getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
						accountKey, teamKey, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRoles(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamRolePage teamRoles(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> new TeamRolePage(
				teamRoleResource.getTeamRolesPage(
					search,
					_filterBiFunction.apply(teamRoleResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(teamRoleResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRole(teamRoleKey: ___){dateCreated, dateModified, description, key, name, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamRole teamRole(@GraphQLName("teamRoleKey") String teamRoleKey)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.getTeamRole(teamRoleKey));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {teamRoleTeamRoleTypeTeamRoleName(teamRoleName: ___, teamRoleType: ___){dateCreated, dateModified, description, key, name, type}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TeamRole teamRoleTeamRoleTypeTeamRoleName(
			@GraphQLName("teamRoleType") String teamRoleType,
			@GraphQLName("teamRoleName") String teamRoleName)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource ->
				teamRoleResource.getTeamRoleTeamRoleTypeTeamRoleName(
					teamRoleType, teamRoleName));
	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyWorkerContactByOktaRolesPageTypeExtension {

		public GetAccountAccountKeyWorkerContactByOktaRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage accountKeyWorkerContactByOktaRoles(
				@GraphQLName("oktaId") String oktaId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyWorkerContactByOktaRolesPage(
							_account.getKey(), oktaId,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyCustomerContactsPageTypeExtension {

		public GetAccountAccountKeyCustomerContactsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactPage accountKeyCustomerContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getAccountAccountKeyCustomerContactsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByOktaAuditEntriesPageTypeExtension {

		public GetContactByOktaAuditEntriesPageTypeExtension(Contact contact) {
			_contact = contact;
		}

		@GraphQLField
		public AuditEntryPage byOktaAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getContactByOktaAuditEntriesPage(
						_contact.getOktaId(), Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Product.class)
	public class GetProductProductKeyExternalLinksPageTypeExtension {

		public GetProductProductKeyExternalLinksPageTypeExtension(
			Product product) {

			_product = product;
		}

		@GraphQLField
		public ExternalLinkPage productKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getProductProductKeyExternalLinksPage(
						_product.getKey(), Pagination.of(page, pageSize))));
		}

		private Product _product;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyProductPurchasesPageTypeExtension {

		public GetAccountAccountKeyProductPurchasesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ProductPurchasePage accountKeyProductPurchases(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseResource -> new ProductPurchasePage(
					productPurchaseResource.
						getAccountAccountKeyProductPurchasesPage(
							_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyAssignedTeamTeamKeyRolesPageTypeExtension {

		public GetAccountAccountKeyAssignedTeamTeamKeyRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public TeamRolePage accountKeyAssignedTeamTeamKeyRoles(
				@GraphQLName("teamKey") String teamKey,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_teamRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				teamRoleResource -> new TeamRolePage(
					teamRoleResource.
						getAccountAccountKeyAssignedTeamTeamKeyRolesPage(
							_account.getKey(), teamKey,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyAssignedTeamsPageTypeExtension {

		public GetAccountAccountKeyAssignedTeamsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public TeamPage accountKeyAssignedTeams(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_teamResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				teamResource -> new TeamPage(
					teamResource.getAccountAccountKeyAssignedTeamsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyNotesPageTypeExtension {

		public GetAccountAccountKeyNotesPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField
		public NotePage accountKeyNotes(
				@GraphQLName("status") String status,
				@GraphQLName("type") String type,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_noteResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				noteResource -> new NotePage(
					noteResource.getAccountAccountKeyNotesPage(
						_account.getKey(), status, type,
						Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageTypeExtension {

		public GetAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage
				accountKeyContactByEmailAddresContactEmailAddressRoles(
					@GraphQLName("contactEmailAddress") String
						contactEmailAddress,
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
							_account.getKey(), contactEmailAddress,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyPostalAddressesPageTypeExtension {

		public GetAccountAccountKeyPostalAddressesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public PostalAddressPage accountKeyPostalAddresses() throws Exception {
			return _applyComponentServiceObjects(
				_postalAddressResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				postalAddressResource -> new PostalAddressPage(
					postalAddressResource.
						getAccountAccountKeyPostalAddressesPage(
							_account.getKey())));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyWorkerContactsPageTypeExtension {

		public GetAccountAccountKeyWorkerContactsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactPage accountKeyWorkerContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getAccountAccountKeyWorkerContactsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyTeamsPageTypeExtension {

		public GetAccountAccountKeyTeamsPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField
		public TeamPage accountKeyTeams(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_teamResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				teamResource -> new TeamPage(
					teamResource.getAccountAccountKeyTeamsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyExternalLinksPageTypeExtension {

		public GetTeamTeamKeyExternalLinksPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField
		public ExternalLinkPage teamKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getTeamTeamKeyExternalLinksPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByOktaAccountsPageTypeExtension {

		public GetContactByOktaAccountsPageTypeExtension(Contact contact) {
			_contact = contact;
		}

		@GraphQLField
		public AccountPage byOktaAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getContactByOktaAccountsPage(
						_contact.getOktaId(), Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidAccountsPageTypeExtension {

		public GetContactByUuidContactUuidAccountsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public AccountPage byUuidContactUuidAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getContactByUuidContactUuidAccountsPage(
						_contact.getUuid(), Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByOktaProductConsumptionsPageTypeExtension {

		public GetContactByOktaProductConsumptionsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ProductConsumptionPage byOktaProductConsumptions(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productConsumptionResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productConsumptionResource -> new ProductConsumptionPage(
					productConsumptionResource.
						getContactByOktaProductConsumptionsPage(
							_contact.getOktaId(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageTypeExtension {

		public GetAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage
				accountKeyWorkerContactByEmailAddresContactEmailAddressRoles(
					@GraphQLName("contactEmailAddress") String
						contactEmailAddress,
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyWorkerContactByEmailAddresContactEmailAddressRolesPage(
							_account.getKey(), contactEmailAddress,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyCustomerContactByOktaRolesPageTypeExtension {

		public GetAccountAccountKeyCustomerContactByOktaRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage accountKeyCustomerContactByOktaRoles(
				@GraphQLName("oktaId") String oktaId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyCustomerContactByOktaRolesPage(
							_account.getKey(), oktaId,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyWorkerContactByUuidContactUuidRolesPageTypeExtension {

		public GetAccountAccountKeyWorkerContactByUuidContactUuidRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage accountKeyWorkerContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyWorkerContactByUuidContactUuidRolesPage(
							_account.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyExternalLinksPageTypeExtension {

		public GetAccountAccountKeyExternalLinksPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ExternalLinkPage accountKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getAccountAccountKeyExternalLinksPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByOktaContactAccountViewsPageTypeExtension {

		public GetContactByOktaContactAccountViewsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ContactAccountViewPage byOktaContactAccountViews(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactAccountViewResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactAccountViewResource -> new ContactAccountViewPage(
					contactAccountViewResource.
						getContactByOktaContactAccountViewsPage(
							_contact.getOktaId(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyAuditEntriesPageTypeExtension {

		public GetAccountAccountKeyAuditEntriesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public AuditEntryPage accountKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getAccountAccountKeyAuditEntriesPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyProductConsumptionsPageTypeExtension {

		public GetAccountAccountKeyProductConsumptionsPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ProductConsumptionPage accountKeyProductConsumptions(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productConsumptionResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productConsumptionResource -> new ProductConsumptionPage(
					productConsumptionResource.
						getAccountAccountKeyProductConsumptionsPage(
							_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyAssignedAccountsPageTypeExtension {

		public GetTeamTeamKeyAssignedAccountsPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField
		public AccountPage teamKeyAssignedAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getTeamTeamKeyAssignedAccountsPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyContactsPageTypeExtension {

		public GetAccountAccountKeyContactsPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField
		public ContactPage accountKeyContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getAccountAccountKeyContactsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountChildAccountsPageTypeExtension {

		public GetAccountChildAccountsPageTypeExtension(Account account) {
			_account = account;
		}

		@GraphQLField
		public AccountPage childAccounts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> new AccountPage(
					accountResource.getAccountChildAccountsPage(
						_account.getKey(), Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Contact.class)
	public class
		GetContactByUuidContactUuidContactAccountViewsPageTypeExtension {

		public GetContactByUuidContactUuidContactAccountViewsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ContactAccountViewPage byUuidContactUuidContactAccountViews(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactAccountViewResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactAccountViewResource -> new ContactAccountViewPage(
					contactAccountViewResource.
						getContactByUuidContactUuidContactAccountViewsPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyContactByUuidContactUuidRolesPageTypeExtension {

		public GetAccountAccountKeyContactByUuidContactUuidRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage accountKeyContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyContactByUuidContactUuidRolesPage(
							_account.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyProductProductKeyProductPurchaseViewTypeExtension {

		public GetAccountAccountKeyProductProductKeyProductPurchaseViewTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ProductPurchaseView
				accountKeyProductProductKeyProductPurchaseView(
					@GraphQLName("productKey") String productKey)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseViewResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseViewResource ->
					productPurchaseViewResource.
						getAccountAccountKeyProductProductKeyProductPurchaseView(
							_account.getKey(), productKey));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByOktaExternalLinksPageTypeExtension {

		public GetContactByOktaExternalLinksPageTypeExtension(Contact contact) {
			_contact = contact;
		}

		@GraphQLField
		public ExternalLinkPage byOktaExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.getContactByOktaExternalLinksPage(
						_contact.getOktaId(), Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(TeamRole.class)
	public class GetTeamRoleTeamRoleKeyAuditEntriesPageTypeExtension {

		public GetTeamRoleTeamRoleKeyAuditEntriesPageTypeExtension(
			TeamRole teamRole) {

			_teamRole = teamRole;
		}

		@GraphQLField
		public AuditEntryPage teamRoleKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getTeamRoleTeamRoleKeyAuditEntriesPage(
						_teamRole.getKey(), Pagination.of(page, pageSize))));
		}

		private TeamRole _teamRole;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyContactByUuidContactUuidRolesPageTypeExtension {

		public GetTeamTeamKeyContactByUuidContactUuidRolesPageTypeExtension(
			Team team) {

			_team = team;
		}

		@GraphQLField
		public ContactRolePage teamKeyContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getTeamTeamKeyContactByUuidContactUuidRolesPage(
							_team.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(ProductConsumption.class)
	public class
		GetProductConsumptionProductConsumptionKeyExternalLinksPageTypeExtension {

		public GetProductConsumptionProductConsumptionKeyExternalLinksPageTypeExtension(
			ProductConsumption productConsumption) {

			_productConsumption = productConsumption;
		}

		@GraphQLField
		public ExternalLinkPage productConsumptionKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getProductConsumptionProductConsumptionKeyExternalLinksPage(
							_productConsumption.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ProductConsumption _productConsumption;

	}

	@GraphQLTypeExtension(Contact.class)
	public class
		GetContactByUuidContactUuidProductConsumptionsPageTypeExtension {

		public GetContactByUuidContactUuidProductConsumptionsPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ProductConsumptionPage byUuidContactUuidProductConsumptions(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productConsumptionResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productConsumptionResource -> new ProductConsumptionPage(
					productConsumptionResource.
						getContactByUuidContactUuidProductConsumptionsPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class GetAccountAccountKeyContactByOktaRolesPageTypeExtension {

		public GetAccountAccountKeyContactByOktaRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage accountKeyContactByOktaRoles(
				@GraphQLName("oktaId") String oktaId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyContactByOktaRolesPage(
							_account.getKey(), oktaId,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyAuditEntriesPageTypeExtension {

		public GetTeamTeamKeyAuditEntriesPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField
		public AuditEntryPage teamKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.getTeamTeamKeyAuditEntriesPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidExternalLinksPageTypeExtension {

		public GetContactByUuidContactUuidExternalLinksPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ExternalLinkPage byUuidContactUuidExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getContactByUuidContactUuidExternalLinksPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyContactsPageTypeExtension {

		public GetTeamTeamKeyContactsPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField
		public ContactPage teamKeyContacts(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactResource -> new ContactPage(
					contactResource.getTeamTeamKeyContactsPage(
						_team.getKey(), Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyContactByOktaRolesPageTypeExtension {

		public GetTeamTeamKeyContactByOktaRolesPageTypeExtension(Team team) {
			_team = team;
		}

		@GraphQLField
		public ContactRolePage teamKeyContactByOktaRoles(
				@GraphQLName("oktaId") String oktaId,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.getTeamTeamKeyContactByOktaRolesPage(
						_team.getKey(), oktaId,
						Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(ContactRole.class)
	public class GetContactRoleContactRoleKeyAuditEntriesPageTypeExtension {

		public GetContactRoleContactRoleKeyAuditEntriesPageTypeExtension(
			ContactRole contactRole) {

			_contactRole = contactRole;
		}

		@GraphQLField
		public AuditEntryPage contactRoleKeyAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.
						getContactRoleContactRoleKeyAuditEntriesPage(
							_contactRole.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ContactRole _contactRole;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidAuditEntriesPageTypeExtension {

		public GetContactByUuidContactUuidAuditEntriesPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public AuditEntryPage byUuidContactUuidAuditEntries(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_auditEntryResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				auditEntryResource -> new AuditEntryPage(
					auditEntryResource.
						getContactByUuidContactUuidAuditEntriesPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageTypeExtension {

		public GetAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage
				accountKeyCustomerContactByEmailAddresContactEmailAddressRoles(
					@GraphQLName("contactEmailAddress") String
						contactEmailAddress,
					@GraphQLName("pageSize") int pageSize,
					@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyCustomerContactByEmailAddresContactEmailAddressRolesPage(
							_account.getKey(), contactEmailAddress,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLTypeExtension(ProductPurchase.class)
	public class
		GetProductPurchaseProductPurchaseKeyExternalLinksPageTypeExtension {

		public GetProductPurchaseProductPurchaseKeyExternalLinksPageTypeExtension(
			ProductPurchase productPurchase) {

			_productPurchase = productPurchase;
		}

		@GraphQLField
		public ExternalLinkPage productPurchaseKeyExternalLinks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_externalLinkResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				externalLinkResource -> new ExternalLinkPage(
					externalLinkResource.
						getProductPurchaseProductPurchaseKeyExternalLinksPage(
							_productPurchase.getKey(),
							Pagination.of(page, pageSize))));
		}

		private ProductPurchase _productPurchase;

	}

	@GraphQLTypeExtension(Team.class)
	public class GetTeamTeamKeyContactByEmailAddressRolesPageTypeExtension {

		public GetTeamTeamKeyContactByEmailAddressRolesPageTypeExtension(
			Team team) {

			_team = team;
		}

		@GraphQLField
		public ContactRolePage teamKeyContactByEmailAddressRoles(
				@GraphQLName("emailAddress") String emailAddress,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getTeamTeamKeyContactByEmailAddressRolesPage(
							_team.getKey(), emailAddress,
							Pagination.of(page, pageSize))));
		}

		private Team _team;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByOktaProductPurchasesPageTypeExtension {

		public GetContactByOktaProductPurchasesPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ProductPurchasePage byOktaProductPurchases(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseResource -> new ProductPurchasePage(
					productPurchaseResource.
						getContactByOktaProductPurchasesPage(
							_contact.getOktaId(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Contact.class)
	public class GetContactByUuidContactUuidProductPurchasesPageTypeExtension {

		public GetContactByUuidContactUuidProductPurchasesPageTypeExtension(
			Contact contact) {

			_contact = contact;
		}

		@GraphQLField
		public ProductPurchasePage byUuidContactUuidProductPurchases(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_productPurchaseResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				productPurchaseResource -> new ProductPurchasePage(
					productPurchaseResource.
						getContactByUuidContactUuidProductPurchasesPage(
							_contact.getUuid(),
							Pagination.of(page, pageSize))));
		}

		private Contact _contact;

	}

	@GraphQLTypeExtension(Account.class)
	public class
		GetAccountAccountKeyCustomerContactByUuidContactUuidRolesPageTypeExtension {

		public GetAccountAccountKeyCustomerContactByUuidContactUuidRolesPageTypeExtension(
			Account account) {

			_account = account;
		}

		@GraphQLField
		public ContactRolePage accountKeyCustomerContactByUuidContactUuidRoles(
				@GraphQLName("contactUuid") String contactUuid,
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_contactRoleResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				contactRoleResource -> new ContactRolePage(
					contactRoleResource.
						getAccountAccountKeyCustomerContactByUuidContactUuidRolesPage(
							_account.getKey(), contactUuid,
							Pagination.of(page, pageSize))));
		}

		private Account _account;

	}

	@GraphQLName("AccountPage")
	public class AccountPage {

		public AccountPage(Page accountPage) {
			items = accountPage.getItems();
			page = accountPage.getPage();
			pageSize = accountPage.getPageSize();
			totalCount = accountPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Account> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("AuditEntryPage")
	public class AuditEntryPage {

		public AuditEntryPage(Page auditEntryPage) {
			items = auditEntryPage.getItems();
			page = auditEntryPage.getPage();
			pageSize = auditEntryPage.getPageSize();
			totalCount = auditEntryPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<AuditEntry> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ContactPage")
	public class ContactPage {

		public ContactPage(Page contactPage) {
			items = contactPage.getItems();
			page = contactPage.getPage();
			pageSize = contactPage.getPageSize();
			totalCount = contactPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Contact> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ContactAccountViewPage")
	public class ContactAccountViewPage {

		public ContactAccountViewPage(Page contactAccountViewPage) {
			items = contactAccountViewPage.getItems();
			page = contactAccountViewPage.getPage();
			pageSize = contactAccountViewPage.getPageSize();
			totalCount = contactAccountViewPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<ContactAccountView> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ContactRolePage")
	public class ContactRolePage {

		public ContactRolePage(Page contactRolePage) {
			items = contactRolePage.getItems();
			page = contactRolePage.getPage();
			pageSize = contactRolePage.getPageSize();
			totalCount = contactRolePage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<ContactRole> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("EntitlementDefinitionPage")
	public class EntitlementDefinitionPage {

		public EntitlementDefinitionPage(Page entitlementDefinitionPage) {
			items = entitlementDefinitionPage.getItems();
			page = entitlementDefinitionPage.getPage();
			pageSize = entitlementDefinitionPage.getPageSize();
			totalCount = entitlementDefinitionPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<EntitlementDefinition> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ExternalLinkPage")
	public class ExternalLinkPage {

		public ExternalLinkPage(Page externalLinkPage) {
			items = externalLinkPage.getItems();
			page = externalLinkPage.getPage();
			pageSize = externalLinkPage.getPageSize();
			totalCount = externalLinkPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<ExternalLink> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("NotePage")
	public class NotePage {

		public NotePage(Page notePage) {
			items = notePage.getItems();
			page = notePage.getPage();
			pageSize = notePage.getPageSize();
			totalCount = notePage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Note> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("PostalAddressPage")
	public class PostalAddressPage {

		public PostalAddressPage(Page postalAddressPage) {
			items = postalAddressPage.getItems();
			page = postalAddressPage.getPage();
			pageSize = postalAddressPage.getPageSize();
			totalCount = postalAddressPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<PostalAddress> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductPage")
	public class ProductPage {

		public ProductPage(Page productPage) {
			items = productPage.getItems();
			page = productPage.getPage();
			pageSize = productPage.getPageSize();
			totalCount = productPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Product> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductConsumptionPage")
	public class ProductConsumptionPage {

		public ProductConsumptionPage(Page productConsumptionPage) {
			items = productConsumptionPage.getItems();
			page = productConsumptionPage.getPage();
			pageSize = productConsumptionPage.getPageSize();
			totalCount = productConsumptionPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<ProductConsumption> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductPurchasePage")
	public class ProductPurchasePage {

		public ProductPurchasePage(Page productPurchasePage) {
			items = productPurchasePage.getItems();
			page = productPurchasePage.getPage();
			pageSize = productPurchasePage.getPageSize();
			totalCount = productPurchasePage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<ProductPurchase> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ProductPurchaseViewPage")
	public class ProductPurchaseViewPage {

		public ProductPurchaseViewPage(Page productPurchaseViewPage) {
			items = productPurchaseViewPage.getItems();
			page = productPurchaseViewPage.getPage();
			pageSize = productPurchaseViewPage.getPageSize();
			totalCount = productPurchaseViewPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<ProductPurchaseView> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TeamPage")
	public class TeamPage {

		public TeamPage(Page teamPage) {
			items = teamPage.getItems();
			page = teamPage.getPage();
			pageSize = teamPage.getPageSize();
			totalCount = teamPage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<Team> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TeamRolePage")
	public class TeamRolePage {

		public TeamRolePage(Page teamRolePage) {
			items = teamRolePage.getItems();
			page = teamRolePage.getPage();
			pageSize = teamRolePage.getPageSize();
			totalCount = teamRolePage.getTotalCount();
		}

		@GraphQLField
		protected java.util.Collection<TeamRole> items;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextAcceptLanguage(_acceptLanguage);
		accountResource.setContextCompany(_company);
		accountResource.setContextHttpServletRequest(_httpServletRequest);
		accountResource.setContextHttpServletResponse(_httpServletResponse);
		accountResource.setContextUriInfo(_uriInfo);
		accountResource.setContextUser(_user);
	}

	private void _populateResourceContext(AuditEntryResource auditEntryResource)
		throws Exception {

		auditEntryResource.setContextAcceptLanguage(_acceptLanguage);
		auditEntryResource.setContextCompany(_company);
		auditEntryResource.setContextHttpServletRequest(_httpServletRequest);
		auditEntryResource.setContextHttpServletResponse(_httpServletResponse);
		auditEntryResource.setContextUriInfo(_uriInfo);
		auditEntryResource.setContextUser(_user);
	}

	private void _populateResourceContext(ContactResource contactResource)
		throws Exception {

		contactResource.setContextAcceptLanguage(_acceptLanguage);
		contactResource.setContextCompany(_company);
		contactResource.setContextHttpServletRequest(_httpServletRequest);
		contactResource.setContextHttpServletResponse(_httpServletResponse);
		contactResource.setContextUriInfo(_uriInfo);
		contactResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ContactAccountViewResource contactAccountViewResource)
		throws Exception {

		contactAccountViewResource.setContextAcceptLanguage(_acceptLanguage);
		contactAccountViewResource.setContextCompany(_company);
		contactAccountViewResource.setContextHttpServletRequest(
			_httpServletRequest);
		contactAccountViewResource.setContextHttpServletResponse(
			_httpServletResponse);
		contactAccountViewResource.setContextUriInfo(_uriInfo);
		contactAccountViewResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ContactRoleResource contactRoleResource)
		throws Exception {

		contactRoleResource.setContextAcceptLanguage(_acceptLanguage);
		contactRoleResource.setContextCompany(_company);
		contactRoleResource.setContextHttpServletRequest(_httpServletRequest);
		contactRoleResource.setContextHttpServletResponse(_httpServletResponse);
		contactRoleResource.setContextUriInfo(_uriInfo);
		contactRoleResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			EntitlementDefinitionResource entitlementDefinitionResource)
		throws Exception {

		entitlementDefinitionResource.setContextAcceptLanguage(_acceptLanguage);
		entitlementDefinitionResource.setContextCompany(_company);
		entitlementDefinitionResource.setContextHttpServletRequest(
			_httpServletRequest);
		entitlementDefinitionResource.setContextHttpServletResponse(
			_httpServletResponse);
		entitlementDefinitionResource.setContextUriInfo(_uriInfo);
		entitlementDefinitionResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ExternalLinkResource externalLinkResource)
		throws Exception {

		externalLinkResource.setContextAcceptLanguage(_acceptLanguage);
		externalLinkResource.setContextCompany(_company);
		externalLinkResource.setContextHttpServletRequest(_httpServletRequest);
		externalLinkResource.setContextHttpServletResponse(
			_httpServletResponse);
		externalLinkResource.setContextUriInfo(_uriInfo);
		externalLinkResource.setContextUser(_user);
	}

	private void _populateResourceContext(NoteResource noteResource)
		throws Exception {

		noteResource.setContextAcceptLanguage(_acceptLanguage);
		noteResource.setContextCompany(_company);
		noteResource.setContextHttpServletRequest(_httpServletRequest);
		noteResource.setContextHttpServletResponse(_httpServletResponse);
		noteResource.setContextUriInfo(_uriInfo);
		noteResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			PostalAddressResource postalAddressResource)
		throws Exception {

		postalAddressResource.setContextAcceptLanguage(_acceptLanguage);
		postalAddressResource.setContextCompany(_company);
		postalAddressResource.setContextHttpServletRequest(_httpServletRequest);
		postalAddressResource.setContextHttpServletResponse(
			_httpServletResponse);
		postalAddressResource.setContextUriInfo(_uriInfo);
		postalAddressResource.setContextUser(_user);
	}

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextAcceptLanguage(_acceptLanguage);
		productResource.setContextCompany(_company);
		productResource.setContextHttpServletRequest(_httpServletRequest);
		productResource.setContextHttpServletResponse(_httpServletResponse);
		productResource.setContextUriInfo(_uriInfo);
		productResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ProductConsumptionResource productConsumptionResource)
		throws Exception {

		productConsumptionResource.setContextAcceptLanguage(_acceptLanguage);
		productConsumptionResource.setContextCompany(_company);
		productConsumptionResource.setContextHttpServletRequest(
			_httpServletRequest);
		productConsumptionResource.setContextHttpServletResponse(
			_httpServletResponse);
		productConsumptionResource.setContextUriInfo(_uriInfo);
		productConsumptionResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ProductPurchaseResource productPurchaseResource)
		throws Exception {

		productPurchaseResource.setContextAcceptLanguage(_acceptLanguage);
		productPurchaseResource.setContextCompany(_company);
		productPurchaseResource.setContextHttpServletRequest(
			_httpServletRequest);
		productPurchaseResource.setContextHttpServletResponse(
			_httpServletResponse);
		productPurchaseResource.setContextUriInfo(_uriInfo);
		productPurchaseResource.setContextUser(_user);
	}

	private void _populateResourceContext(
			ProductPurchaseViewResource productPurchaseViewResource)
		throws Exception {

		productPurchaseViewResource.setContextAcceptLanguage(_acceptLanguage);
		productPurchaseViewResource.setContextCompany(_company);
		productPurchaseViewResource.setContextHttpServletRequest(
			_httpServletRequest);
		productPurchaseViewResource.setContextHttpServletResponse(
			_httpServletResponse);
		productPurchaseViewResource.setContextUriInfo(_uriInfo);
		productPurchaseViewResource.setContextUser(_user);
	}

	private void _populateResourceContext(TeamResource teamResource)
		throws Exception {

		teamResource.setContextAcceptLanguage(_acceptLanguage);
		teamResource.setContextCompany(_company);
		teamResource.setContextHttpServletRequest(_httpServletRequest);
		teamResource.setContextHttpServletResponse(_httpServletResponse);
		teamResource.setContextUriInfo(_uriInfo);
		teamResource.setContextUser(_user);
	}

	private void _populateResourceContext(TeamRoleResource teamRoleResource)
		throws Exception {

		teamRoleResource.setContextAcceptLanguage(_acceptLanguage);
		teamRoleResource.setContextCompany(_company);
		teamRoleResource.setContextHttpServletRequest(_httpServletRequest);
		teamRoleResource.setContextHttpServletResponse(_httpServletResponse);
		teamRoleResource.setContextUriInfo(_uriInfo);
		teamRoleResource.setContextUser(_user);
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<AuditEntryResource>
		_auditEntryResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactAccountViewResource>
		_contactAccountViewResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;
	private static ComponentServiceObjects<EntitlementDefinitionResource>
		_entitlementDefinitionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ExternalLinkResource>
		_externalLinkResourceComponentServiceObjects;
	private static ComponentServiceObjects<NoteResource>
		_noteResourceComponentServiceObjects;
	private static ComponentServiceObjects<PostalAddressResource>
		_postalAddressResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductConsumptionResource>
		_productConsumptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductPurchaseResource>
		_productPurchaseResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductPurchaseViewResource>
		_productPurchaseViewResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private Company _company;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;
	private User _user;

}