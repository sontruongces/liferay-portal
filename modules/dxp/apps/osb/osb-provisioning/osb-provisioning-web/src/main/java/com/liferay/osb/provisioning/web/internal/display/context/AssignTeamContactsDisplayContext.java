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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.web.internal.dao.search.AssignTeamContactRowChecker;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class AssignTeamContactsDisplayContext extends ViewTeamDisplayContext {

	public AssignTeamContactsDisplayContext() {
	}

	public String getClearResultsURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_team_contacts");
		portletURL.setParameter("teamKey", team.getKey());

		return portletURL.toString();
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-contacts-were-found");

		StringBundler sb = new StringBundler(3);

		sb.append("customerAccountKeys/any(s:s eq '");
		sb.append(account.getKey());
		sb.append("')");

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		if (!ArrayUtil.isEmpty(contactRoleKeys)) {
			sb.append(" and accountKeysContactRoleKeys/any(s:");

			for (int i = 0; i < contactRoleKeys.length; i++) {
				if (i > 0) {
					sb.append(" or ");
				}

				sb.append("s eq '");
				sb.append(account.getKey());
				sb.append("_");
				sb.append(contactRoleKeys[i]);
				sb.append("'");
			}

			sb.append(")");
		}

		List<Contact> contacts = contactWebService.search(
			keywords, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountCustomerContactRoles(
							account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						httpServletRequest, 0, contact, contactRoles);
				}));

		searchContainer.setRowChecker(_getRowChecker());

		int count = (int)contactWebService.searchCount(keywords, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private RowChecker _getRowChecker() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("teamKeys/any(s:s eq '");
		sb.append(team.getKey());
		sb.append("')");

		List<Contact> contacts = contactWebService.search(
			StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		return new AssignTeamContactRowChecker(
			renderResponse, ListUtil.toList(contacts, Contact::getKey));
	}

}