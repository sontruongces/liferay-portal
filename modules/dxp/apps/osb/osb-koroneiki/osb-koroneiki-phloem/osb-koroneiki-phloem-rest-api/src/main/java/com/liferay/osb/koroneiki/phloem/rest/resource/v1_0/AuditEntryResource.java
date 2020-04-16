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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
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
public interface AuditEntryResource {

	public Page<AuditEntry> getAccountAccountKeyAuditEntriesPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public AuditEntry getAuditEntry(String auditEntryKey) throws Exception;

	public Page<AuditEntry> getContactRoleContactRoleKeyAuditEntriesPage(
			String contactRoleKey, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getContactByOktaAuditEntriesPage(
			String oktaId, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getContactByUuidContactUuidAuditEntriesPage(
			String contactUuid, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getTeamRoleTeamRoleKeyAuditEntriesPage(
			String teamRoleKey, Pagination pagination)
		throws Exception;

	public Page<AuditEntry> getTeamTeamKeyAuditEntriesPage(
			String teamKey, Pagination pagination)
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