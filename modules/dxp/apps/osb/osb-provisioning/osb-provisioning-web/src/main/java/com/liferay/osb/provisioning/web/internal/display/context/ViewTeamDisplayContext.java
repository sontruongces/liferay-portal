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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class ViewTeamDisplayContext extends ViewAccountDisplayContext {

	public ViewTeamDisplayContext() {
	}

	@Override
	public void addPortletBreadcrumbEntries() throws Exception {
		super.addPortletBreadcrumbEntries();

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view_team");
		portletURL.setParameter("teamKey", _team.getKey());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, _team.getName(), portletURL.toString());
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_team = (Team)renderRequest.getAttribute(ProvisioningWebKeys.TEAM);

		_teamDisplay = new TeamDisplay(httpServletRequest, _team);
	}

	public SearchContainer getContactsSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-contacts-were-found");

		StringBundler sb = new StringBundler(3);

		sb.append("teamKeys/any(s:s eq '");
		sb.append(_team.getKey());
		sb.append("')");

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
						httpServletRequest, contact, contactRoles);
				}));

		int count = (int)contactWebService.searchCount(keywords, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view_team");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter("teamKey", _team.getKey());

		return portletURL;
	}

	public TeamDisplay getTeamDisplay() {
		return _teamDisplay;
	}

	private Team _team;
	private TeamDisplay _teamDisplay;

}