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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.annotation.versioning.ProviderType;

/**
 * To access this resource, run:
 *
 *     curl -u your@email.com:yourpassword -D - http://localhost:8080/o/koroneiki-rest/v1.0
 *
 * @author Amos Fong
 * @generated
 */
@Generated("")
@ProviderType
public interface ExternalLinkResource {

	public Page<ExternalLink> getAccountAccountKeyExternalLinksPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public ExternalLink postAccountAccountKeyExternalLink(
			String agentName, String agentUID, String accountKey,
			ExternalLink externalLink)
		throws Exception;

	public Page<ExternalLink> getContactByOktaExternalLinksPage(
			String oktaId, Pagination pagination)
		throws Exception;

	public ExternalLink postContactByOktaExternalLink(
			String agentName, String agentUID, String oktaId,
			ExternalLink externalLink)
		throws Exception;

	public Page<ExternalLink> getContactByUuidContactUuidExternalLinksPage(
			String contactUuid, Pagination pagination)
		throws Exception;

	public ExternalLink postContactByUuidContactUuidExternalLink(
			String agentName, String agentUID, String contactUuid,
			ExternalLink externalLink)
		throws Exception;

	public void deleteExternalLink(
			String agentName, String agentUID, String externalLinkKey)
		throws Exception;

	public ExternalLink getExternalLink(String externalLinkKey)
		throws Exception;

	public ExternalLink putExternalLink(
			String agentName, String agentUID, String externalLinkKey,
			ExternalLink externalLink)
		throws Exception;

	public Page<ExternalLink>
			getProductConsumptionProductConsumptionKeyExternalLinksPage(
				String productConsumptionKey, Pagination pagination)
		throws Exception;

	public ExternalLink postProductConsumptionProductConsumptionKeyExternalLink(
			String agentName, String agentUID, String productConsumptionKey,
			ExternalLink externalLink)
		throws Exception;

	public Page<ExternalLink>
			getProductPurchaseProductPurchaseKeyExternalLinksPage(
				String productPurchaseKey, Pagination pagination)
		throws Exception;

	public ExternalLink postProductPurchaseProductPurchaseKeyExternalLink(
			String agentName, String agentUID, String productPurchaseKey,
			ExternalLink externalLink)
		throws Exception;

	public Page<ExternalLink> getProductProductKeyExternalLinksPage(
			String productKey, Pagination pagination)
		throws Exception;

	public ExternalLink postProductProductKeyExternalLink(
			String agentName, String agentUID, String productKey,
			ExternalLink externalLink)
		throws Exception;

	public Page<ExternalLink> getTeamTeamKeyExternalLinksPage(
			String teamKey, Pagination pagination)
		throws Exception;

	public ExternalLink postTeamTeamKeyExternalLink(
			String agentName, String agentUID, String teamKey,
			ExternalLink externalLink)
		throws Exception;

	public default void setContextAcceptLanguage(
		AcceptLanguage contextAcceptLanguage) {
	}

	public void setContextCompany(Company contextCompany);

	public default void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {
	}

	public default void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {
	}

	public default void setContextUriInfo(UriInfo contextUriInfo) {
	}

	public void setContextUser(User contextUser);

}