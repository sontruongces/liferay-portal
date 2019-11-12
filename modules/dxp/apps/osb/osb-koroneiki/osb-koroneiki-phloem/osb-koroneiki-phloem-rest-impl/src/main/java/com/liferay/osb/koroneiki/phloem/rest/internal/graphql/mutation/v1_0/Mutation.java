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

package com.liferay.osb.koroneiki.phloem.rest.internal.graphql.mutation.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumptionPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchasePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.AccountResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ContactRoleResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.EntitlementDefinitionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductConsumptionResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

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
public class Mutation {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setContactResourceComponentServiceObjects(
		ComponentServiceObjects<ContactResource>
			contactResourceComponentServiceObjects) {

		_contactResourceComponentServiceObjects =
			contactResourceComponentServiceObjects;
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

	@GraphQLField
	public Account createAccount(@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccount(account));
	}

	@GraphQLField
	public boolean deleteAccount(@GraphQLName("accountKey") String accountKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccount(accountKey));

		return true;
	}

	@GraphQLField
	public Account updateAccount(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccount(accountKey, account));
	}

	@GraphQLField
	public boolean deleteAccountAccountPermission(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("accountPermission") AccountPermission
				accountPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountAccountPermission(
				accountKey, accountPermission));

		return true;
	}

	@GraphQLField
	public boolean updateAccountAccountPermission(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("accountPermission") AccountPermission
				accountPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountAccountPermission(
				accountKey, accountPermission));

		return true;
	}

	@GraphQLField
	public boolean deleteAccountAssignedTeamTeamKeyRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamRoleKeys") String[] teamRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountAssignedTeamTeamKeyRole(
					accountKey, teamKey, teamRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean updateAccountAssignedTeamTeamKeyRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamRoleKeys") String[] teamRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.putAccountAssignedTeamTeamKeyRole(
					accountKey, teamKey, teamRoleKeys));

		return true;
	}

	@GraphQLField
	public Account createAccountChildAccount(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccountChildAccount(
				accountKey, account));
	}

	@GraphQLField
	public boolean deleteAccountContactByEmailAddres(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddresses") String[]
				contactEmailAddresses)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountContactByEmailAddres(
					accountKey, contactEmailAddresses));

		return true;
	}

	@GraphQLField
	public boolean updateAccountContactByEmailAddres(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddresses") String[]
				contactEmailAddresses)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountContactByEmailAddres(
				accountKey, contactEmailAddresses));

		return true;
	}

	@GraphQLField
	public boolean deleteAccountContactByEmailAddresContactEmailAddressRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddress") String contactEmailAddress,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.
					deleteAccountContactByEmailAddresContactEmailAddressRole(
						accountKey, contactEmailAddress, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean updateAccountContactByEmailAddresContactEmailAddressRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactEmailAddress") String contactEmailAddress,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.
					putAccountContactByEmailAddresContactEmailAddressRole(
						accountKey, contactEmailAddress, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean deleteAccountContactByOkta(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaIds") String[] oktaIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountContactByOkta(
				accountKey, oktaIds));

		return true;
	}

	@GraphQLField
	public boolean updateAccountContactByOkta(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaIds") String[] oktaIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountContactByOkta(
				accountKey, oktaIds));

		return true;
	}

	@GraphQLField
	public boolean deleteAccountContactByOktaRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountContactByOktaRole(
				accountKey, oktaId, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean updateAccountContactByOktaRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountContactByOktaRole(
				accountKey, oktaId, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean deleteAccountContactByUuid(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountContactByUuid(
				accountKey, contactUuids));

		return true;
	}

	@GraphQLField
	public boolean updateAccountContactByUuid(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountContactByUuid(
				accountKey, contactUuids));

		return true;
	}

	@GraphQLField
	public boolean deleteAccountContactByUuidContactUuidRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.deleteAccountContactByUuidContactUuidRole(
					accountKey, contactUuid, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean updateAccountContactByUuidContactUuidRole(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource ->
				accountResource.putAccountContactByUuidContactUuidRole(
					accountKey, contactUuid, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public Contact createContact(@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.postContact(contact));
	}

	@GraphQLField
	public boolean deleteContactByEmailAddresEmailAddress(
			@GraphQLName("emailAddress") String emailAddress)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.deleteContactByEmailAddresEmailAddress(
					emailAddress));

		return true;
	}

	@GraphQLField
	public Contact updateContactByEmailAddresEmailAddress(
			@GraphQLName("emailAddress") String emailAddress,
			@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.putContactByEmailAddresEmailAddress(
					emailAddress, contact));
	}

	@GraphQLField
	public boolean deleteContactByOkta(@GraphQLName("oktaId") String oktaId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.deleteContactByOkta(oktaId));

		return true;
	}

	@GraphQLField
	public Contact updateContactByOkta(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.putContactByOkta(
				oktaId, contact));
	}

	@GraphQLField
	public boolean deleteContactByOktaContactPermission(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.deleteContactByOktaContactPermission(
					oktaId, contactPermission));

		return true;
	}

	@GraphQLField
	public boolean updateContactByOktaContactPermission(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.putContactByOktaContactPermission(
					oktaId, contactPermission));

		return true;
	}

	@GraphQLField
	public boolean deleteContactByUuidContactUuid(
			@GraphQLName("contactUuid") String contactUuid)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.deleteContactByUuidContactUuid(
				contactUuid));

		return true;
	}

	@GraphQLField
	public Contact updateContactByUuidContactUuid(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contact") Contact contact)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource -> contactResource.putContactByUuidContactUuid(
				contactUuid, contact));
	}

	@GraphQLField
	public boolean deleteContactByUuidContactUuidContactPermission(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.deleteContactByUuidContactUuidContactPermission(
					contactUuid, contactPermission));

		return true;
	}

	@GraphQLField
	public boolean updateContactByUuidContactUuidContactPermission(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.putContactByUuidContactUuidContactPermission(
					contactUuid, contactPermission));

		return true;
	}

	@GraphQLField
	public ContactRole createContactRole(
			@GraphQLName("contactRole") ContactRole contactRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.postContactRole(
				contactRole));
	}

	@GraphQLField
	public boolean deleteContactRole(
			@GraphQLName("contactRoleKey") String contactRoleKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.deleteContactRole(
				contactRoleKey));

		return true;
	}

	@GraphQLField
	public ContactRole updateContactRole(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("contactRole") ContactRole contactRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource -> contactRoleResource.putContactRole(
				contactRoleKey, contactRole));
	}

	@GraphQLField
	public boolean deleteContactRoleContactRolePermission(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("contactRolePermission") ContactRolePermission
				contactRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource ->
				contactRoleResource.deleteContactRoleContactRolePermission(
					contactRoleKey, contactRolePermission));

		return true;
	}

	@GraphQLField
	public boolean updateContactRoleContactRolePermission(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("contactRolePermission") ContactRolePermission
				contactRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource ->
				contactRoleResource.putContactRoleContactRolePermission(
					contactRoleKey, contactRolePermission));

		return true;
	}

	@GraphQLField
	public EntitlementDefinition createAccountEntitlementDefinition(
			@GraphQLName("entitlementDefinition") EntitlementDefinition
				entitlementDefinition)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.postAccountEntitlementDefinition(
					entitlementDefinition));
	}

	@GraphQLField
	public EntitlementDefinition createContactEntitlementDefinition(
			@GraphQLName("entitlementDefinition") EntitlementDefinition
				entitlementDefinition)
		throws Exception {

		return _applyComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.postContactEntitlementDefinition(
					entitlementDefinition));
	}

	@GraphQLField
	public boolean deleteEntitlementDefinition(
			@GraphQLName("entitlementDefinitionKey") String
				entitlementDefinitionKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.deleteEntitlementDefinition(
					entitlementDefinitionKey));

		return true;
	}

	@GraphQLField
	public boolean createEntitlementDefinitionSynchronize(
			@GraphQLName("entitlementDefinitionKey") String
				entitlementDefinitionKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_entitlementDefinitionResourceComponentServiceObjects,
			this::_populateResourceContext,
			entitlementDefinitionResource ->
				entitlementDefinitionResource.
					postEntitlementDefinitionSynchronize(
						entitlementDefinitionKey));

		return true;
	}

	@GraphQLField
	public ExternalLink createAccountAccountKeyExternalLink(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postAccountAccountKeyExternalLink(
					accountKey, externalLink));
	}

	@GraphQLField
	public ExternalLink createContactByOktaExternalLink(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postContactByOktaExternalLink(
					oktaId, externalLink));
	}

	@GraphQLField
	public ExternalLink createContactByUuidContactUuidExternalLink(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postContactByUuidContactUuidExternalLink(
					contactUuid, externalLink));
	}

	@GraphQLField
	public boolean deleteExternalLink(
			@GraphQLName("externalLinkKey") String externalLinkKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource -> externalLinkResource.deleteExternalLink(
				externalLinkKey));

		return true;
	}

	@GraphQLField
	public ExternalLink
			createProductConsumptionProductConsumptionKeyExternalLink(
				@GraphQLName("productConsumptionKey") String
					productConsumptionKey,
				@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.
					postProductConsumptionProductConsumptionKeyExternalLink(
						productConsumptionKey, externalLink));
	}

	@GraphQLField
	public ExternalLink createProductPurchaseProductPurchaseKeyExternalLink(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.
					postProductPurchaseProductPurchaseKeyExternalLink(
						productPurchaseKey, externalLink));
	}

	@GraphQLField
	public ExternalLink createProductProductKeyExternalLink(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postProductProductKeyExternalLink(
					productKey, externalLink));
	}

	@GraphQLField
	public ExternalLink createTeamTeamKeyExternalLink(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("externalLink") ExternalLink externalLink)
		throws Exception {

		return _applyComponentServiceObjects(
			_externalLinkResourceComponentServiceObjects,
			this::_populateResourceContext,
			externalLinkResource ->
				externalLinkResource.postTeamTeamKeyExternalLink(
					teamKey, externalLink));
	}

	@GraphQLField
	public PostalAddress createAccountAccountKeyPostalAddress(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("postalAddress") PostalAddress postalAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource ->
				postalAddressResource.postAccountAccountKeyPostalAddress(
					accountKey, postalAddress));
	}

	@GraphQLField
	public boolean deletePostalAddress(
			@GraphQLName("postalAddressId") Long postalAddressId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> postalAddressResource.deletePostalAddress(
				postalAddressId));

		return true;
	}

	@GraphQLField
	public PostalAddress updatePostalAddress(
			@GraphQLName("postalAddressId") Long postalAddressId,
			@GraphQLName("postalAddress") PostalAddress postalAddress)
		throws Exception {

		return _applyComponentServiceObjects(
			_postalAddressResourceComponentServiceObjects,
			this::_populateResourceContext,
			postalAddressResource -> postalAddressResource.putPostalAddress(
				postalAddressId, postalAddress));
	}

	@GraphQLField
	public Product createProduct(@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.postProduct(product));
	}

	@GraphQLField
	public boolean deleteProduct(@GraphQLName("productKey") String productKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProduct(productKey));

		return true;
	}

	@GraphQLField
	public Product updateProduct(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.putProduct(productKey, product));
	}

	@GraphQLField
	public boolean deleteProductProductPermission(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("productPermission") ProductPermission
				productPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.deleteProductProductPermission(
				productKey, productPermission));

		return true;
	}

	@GraphQLField
	public boolean updateProductProductPermission(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("productPermission") ProductPermission
				productPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.putProductProductPermission(
				productKey, productPermission));

		return true;
	}

	@GraphQLField
	public ProductConsumption createAccountAccountKeyProductConsumption(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("productConsumption") ProductConsumption
				productConsumption)
		throws Exception {

		return _applyComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					postAccountAccountKeyProductConsumption(
						accountKey, productConsumption));
	}

	@GraphQLField
	public boolean deleteProductConsumption(
			@GraphQLName("productConsumptionKey") String productConsumptionKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.deleteProductConsumption(
					productConsumptionKey));

		return true;
	}

	@GraphQLField
	public boolean deleteProductConsumptionProductConsumptionPermission(
			@GraphQLName("productConsumptionKey") String productConsumptionKey,
			@GraphQLName("productConsumptionPermission")
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					deleteProductConsumptionProductConsumptionPermission(
						productConsumptionKey, productConsumptionPermission));

		return true;
	}

	@GraphQLField
	public boolean updateProductConsumptionProductConsumptionPermission(
			@GraphQLName("productConsumptionKey") String productConsumptionKey,
			@GraphQLName("productConsumptionPermission")
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					putProductConsumptionProductConsumptionPermission(
						productConsumptionKey, productConsumptionPermission));

		return true;
	}

	@GraphQLField
	public ProductPurchase createAccountAccountKeyProductPurchase(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.postAccountAccountKeyProductPurchase(
					accountKey, productPurchase));
	}

	@GraphQLField
	public boolean deleteProductPurchase(
			@GraphQLName("productPurchaseKey") String productPurchaseKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.deleteProductPurchase(
					productPurchaseKey));

		return true;
	}

	@GraphQLField
	public ProductPurchase updateProductPurchase(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("productPurchase") ProductPurchase productPurchase)
		throws Exception {

		return _applyComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.putProductPurchase(
					productPurchaseKey, productPurchase));
	}

	@GraphQLField
	public boolean deleteProductPurchaseProductPurchasePermission(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("productPurchasePermission") ProductPurchasePermission
				productPurchasePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.
					deleteProductPurchaseProductPurchasePermission(
						productPurchaseKey, productPurchasePermission));

		return true;
	}

	@GraphQLField
	public boolean updateProductPurchaseProductPurchasePermission(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("productPurchasePermission") ProductPurchasePermission
				productPurchasePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.
					putProductPurchaseProductPurchasePermission(
						productPurchaseKey, productPurchasePermission));

		return true;
	}

	@GraphQLField
	public Team createAccountAccountKeyTeam(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.postAccountAccountKeyTeam(
				accountKey, team));
	}

	@GraphQLField
	public boolean deleteTeam(@GraphQLName("teamKey") String teamKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeam(teamKey));

		return true;
	}

	@GraphQLField
	public Team updateTeam(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeam(teamKey, team));
	}

	@GraphQLField
	public boolean deleteTeamContactByOkta(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("oktaIds") String[] oktaIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByOkta(
				teamKey, oktaIds));

		return true;
	}

	@GraphQLField
	public boolean updateTeamContactByOkta(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("oktaIds") String[] oktaIds)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByOkta(
				teamKey, oktaIds));

		return true;
	}

	@GraphQLField
	public boolean deleteTeamContactByOktaRole(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByOktaRole(
				teamKey, oktaId, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean updateTeamContactByOktaRole(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByOktaRole(
				teamKey, oktaId, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean deleteTeamContactByUuid(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByUuid(
				teamKey, contactUuids));

		return true;
	}

	@GraphQLField
	public boolean updateTeamContactByUuid(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuids") String[] contactUuids)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByUuid(
				teamKey, contactUuids));

		return true;
	}

	@GraphQLField
	public boolean deleteTeamContactByUuidContactUuidRole(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamContactByUuidContactUuidRole(
				teamKey, contactUuid, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean updateTeamContactByUuidContactUuidRole(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("contactRoleKeys") String[] contactRoleKeys)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamContactByUuidContactUuidRole(
				teamKey, contactUuid, contactRoleKeys));

		return true;
	}

	@GraphQLField
	public boolean deleteTeamTeamPermission(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamPermission") TeamPermission teamPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.deleteTeamTeamPermission(
				teamKey, teamPermission));

		return true;
	}

	@GraphQLField
	public boolean updateTeamTeamPermission(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("teamPermission") TeamPermission teamPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeamTeamPermission(
				teamKey, teamPermission));

		return true;
	}

	@GraphQLField
	public TeamRole createTeamRole(@GraphQLName("teamRole") TeamRole teamRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.postTeamRole(teamRole));
	}

	@GraphQLField
	public boolean deleteTeamRole(
			@GraphQLName("teamRoleKey") String teamRoleKey)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.deleteTeamRole(teamRoleKey));

		return true;
	}

	@GraphQLField
	public TeamRole updateTeamRole(
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("teamRole") TeamRole teamRole)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.putTeamRole(
				teamRoleKey, teamRole));
	}

	@GraphQLField
	public boolean deleteTeamRoleTeamRolePermission(
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("teamRolePermission") TeamRolePermission
				teamRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource ->
				teamRoleResource.deleteTeamRoleTeamRolePermission(
					teamRoleKey, teamRolePermission));

		return true;
	}

	@GraphQLField
	public boolean updateTeamRoleTeamRolePermission(
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("teamRolePermission") TeamRolePermission
				teamRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.putTeamRoleTeamRolePermission(
				teamRoleKey, teamRolePermission));

		return true;
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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
	private static ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;
	private static ComponentServiceObjects<EntitlementDefinitionResource>
		_entitlementDefinitionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ExternalLinkResource>
		_externalLinkResourceComponentServiceObjects;
	private static ComponentServiceObjects<PostalAddressResource>
		_postalAddressResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductResource>
		_productResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductConsumptionResource>
		_productConsumptionResourceComponentServiceObjects;
	private static ComponentServiceObjects<ProductPurchaseResource>
		_productPurchaseResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamResource>
		_teamResourceComponentServiceObjects;
	private static ComponentServiceObjects<TeamRoleResource>
		_teamRoleResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private Company _company;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private UriInfo _uriInfo;
	private User _user;

}