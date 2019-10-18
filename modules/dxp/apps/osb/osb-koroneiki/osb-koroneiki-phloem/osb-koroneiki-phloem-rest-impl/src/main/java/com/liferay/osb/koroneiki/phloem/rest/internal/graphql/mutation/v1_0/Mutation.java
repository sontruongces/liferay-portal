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
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import javax.annotation.Generated;

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
	public Account postAccount(@GraphQLName("account") Account account)
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
	public Account putAccount(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccount(accountKey, account));
	}

	@GraphQLField
	public boolean postAccountAccountPermission(
			@GraphQLName("accountKey") String accountKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("accountPermission") AccountPermission
				accountPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccountAccountPermission(
				accountKey, operation, accountPermission));

		return true;
	}

	@GraphQLField
	public Account postAccountChildAccount(
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
	public boolean putAccountContactByOkta(
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
	public boolean putAccountContactByOktaRole(
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
	public boolean putAccountContactByUuid(
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
	public boolean putAccountContactByUuidContactUuidRole(
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
	public boolean putAccountAssignedTeamTeamKeyRole(
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
	public Contact postContact(@GraphQLName("contact") Contact contact)
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
	public Contact putContactByEmailAddresEmailAddress(
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
	public Contact putContactByOkta(
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
	public boolean postContactByOktaContactPermission(
			@GraphQLName("oktaId") String oktaId,
			@GraphQLName("operation") String operation,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.postContactByOktaContactPermission(
					oktaId, operation, contactPermission));

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
	public Contact putContactByUuidContactUuid(
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
	public boolean postContactByUuidContactUuidContactPermission(
			@GraphQLName("contactUuid") String contactUuid,
			@GraphQLName("operation") String operation,
			@GraphQLName("contactPermission") ContactPermission
				contactPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactResource ->
				contactResource.postContactByUuidContactUuidContactPermission(
					contactUuid, operation, contactPermission));

		return true;
	}

	@GraphQLField
	public ContactRole postContactRole(
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
	public ContactRole putContactRole(
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
	public boolean postContactRoleContactRolePermission(
			@GraphQLName("contactRoleKey") String contactRoleKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("contactRolePermission") ContactRolePermission
				contactRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_contactRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			contactRoleResource ->
				contactRoleResource.postContactRoleContactRolePermission(
					contactRoleKey, operation, contactRolePermission));

		return true;
	}

	@GraphQLField
	public ExternalLink postAccountAccountKeyExternalLink(
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
	public ExternalLink postContactByOktaExternalLink(
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
	public ExternalLink postContactByUuidContactUuidExternalLink(
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
	public ExternalLink postProductConsumptionProductConsumptionKeyExternalLink(
			@GraphQLName("productConsumptionKey") String productConsumptionKey,
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
	public ExternalLink postProductPurchaseProductPurchaseKeyExternalLink(
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
	public ExternalLink postProductProductKeyExternalLink(
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
	public ExternalLink postTeamTeamKeyExternalLink(
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
	public PostalAddress postAccountAccountKeyPostalAddress(
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
	public PostalAddress putPostalAddress(
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
	public Product postProduct(@GraphQLName("product") Product product)
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
	public Product putProduct(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("product") Product product)
		throws Exception {

		return _applyComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.putProduct(productKey, product));
	}

	@GraphQLField
	public boolean postProductProductPermission(
			@GraphQLName("productKey") String productKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("productPermission") ProductPermission
				productPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productResourceComponentServiceObjects,
			this::_populateResourceContext,
			productResource -> productResource.postProductProductPermission(
				productKey, operation, productPermission));

		return true;
	}

	@GraphQLField
	public ProductConsumption postAccountAccountKeyProductConsumption(
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
	public boolean postProductConsumptionProductConsumptionPermission(
			@GraphQLName("productConsumptionKey") String productConsumptionKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("productConsumptionPermission")
				ProductConsumptionPermission productConsumptionPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productConsumptionResourceComponentServiceObjects,
			this::_populateResourceContext,
			productConsumptionResource ->
				productConsumptionResource.
					postProductConsumptionProductConsumptionPermission(
						productConsumptionKey, operation,
						productConsumptionPermission));

		return true;
	}

	@GraphQLField
	public ProductPurchase postAccountAccountKeyProductPurchase(
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
	public ProductPurchase putProductPurchase(
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
	public boolean postProductPurchaseProductPurchasePermission(
			@GraphQLName("productPurchaseKey") String productPurchaseKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("productPurchasePermission") ProductPurchasePermission
				productPurchasePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_productPurchaseResourceComponentServiceObjects,
			this::_populateResourceContext,
			productPurchaseResource ->
				productPurchaseResource.
					postProductPurchaseProductPurchasePermission(
						productPurchaseKey, operation,
						productPurchasePermission));

		return true;
	}

	@GraphQLField
	public Team postAccountAccountKeyTeam(
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
	public Team putTeam(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("team") Team team)
		throws Exception {

		return _applyComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.putTeam(teamKey, team));
	}

	@GraphQLField
	public boolean postTeamTeamPermission(
			@GraphQLName("teamKey") String teamKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("teamPermission") TeamPermission teamPermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamResource -> teamResource.postTeamTeamPermission(
				teamKey, operation, teamPermission));

		return true;
	}

	@GraphQLField
	public TeamRole postTeamRole(@GraphQLName("teamRole") TeamRole teamRole)
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
	public TeamRole putTeamRole(
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
	public boolean postTeamRoleTeamRolePermission(
			@GraphQLName("teamRoleKey") String teamRoleKey,
			@GraphQLName("operation") String operation,
			@GraphQLName("teamRolePermission") TeamRolePermission
				teamRolePermission)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_teamRoleResourceComponentServiceObjects,
			this::_populateResourceContext,
			teamRoleResource -> teamRoleResource.postTeamRoleTeamRolePermission(
				teamRoleKey, operation, teamRolePermission));

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
	}

	private void _populateResourceContext(ContactResource contactResource)
		throws Exception {

		contactResource.setContextAcceptLanguage(_acceptLanguage);
		contactResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ContactRoleResource contactRoleResource)
		throws Exception {

		contactRoleResource.setContextAcceptLanguage(_acceptLanguage);
		contactRoleResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ExternalLinkResource externalLinkResource)
		throws Exception {

		externalLinkResource.setContextAcceptLanguage(_acceptLanguage);
		externalLinkResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			PostalAddressResource postalAddressResource)
		throws Exception {

		postalAddressResource.setContextAcceptLanguage(_acceptLanguage);
		postalAddressResource.setContextCompany(_company);
	}

	private void _populateResourceContext(ProductResource productResource)
		throws Exception {

		productResource.setContextAcceptLanguage(_acceptLanguage);
		productResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ProductConsumptionResource productConsumptionResource)
		throws Exception {

		productConsumptionResource.setContextAcceptLanguage(_acceptLanguage);
		productConsumptionResource.setContextCompany(_company);
	}

	private void _populateResourceContext(
			ProductPurchaseResource productPurchaseResource)
		throws Exception {

		productPurchaseResource.setContextAcceptLanguage(_acceptLanguage);
		productPurchaseResource.setContextCompany(_company);
	}

	private void _populateResourceContext(TeamResource teamResource)
		throws Exception {

		teamResource.setContextAcceptLanguage(_acceptLanguage);
		teamResource.setContextCompany(_company);
	}

	private void _populateResourceContext(TeamRoleResource teamRoleResource)
		throws Exception {

		teamRoleResource.setContextAcceptLanguage(_acceptLanguage);
		teamRoleResource.setContextCompany(_company);
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactResource>
		_contactResourceComponentServiceObjects;
	private static ComponentServiceObjects<ContactRoleResource>
		_contactRoleResourceComponentServiceObjects;
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

}