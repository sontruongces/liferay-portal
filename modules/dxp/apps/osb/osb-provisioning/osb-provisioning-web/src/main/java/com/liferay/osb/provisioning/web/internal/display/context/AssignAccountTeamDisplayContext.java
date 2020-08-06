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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Kyle Bischof
 */
public class AssignAccountTeamDisplayContext extends ViewAccountDisplayContext {

	public AssignAccountTeamDisplayContext() {
	}

	public String getClearResultsURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/assign_account_team");

		return portletURL.toString();
	}

	public SearchContainer getSearchContainer(String teamRoleName)
		throws Exception {

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, currentURLObj, Collections.emptyList(),
			"no-teams-were-found");

		Map<String, Account> accountsMap = new HashMap<>();

		List<Account> partnerAccounts = accountWebService.search(
			StringPool.BLANK, "entitlements/any(k:contains(k,'Partner'))", 0,
			10000, null);

		StringBundler sb = new StringBundler((4 * partnerAccounts.size()) - 1);

		for (int i = 0; i < partnerAccounts.size(); i++) {
			Account account = partnerAccounts.get(i);

			accountsMap.put(account.getKey(), account);

			sb.append("(accountKey eq '");
			sb.append(account.getKey());
			sb.append("')");

			if (i < (partnerAccounts.size() - 1)) {
				sb.append(" or ");
			}
		}

		List<Team> teams = teamWebService.search(
			keywords, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "name");

		searchContainer.setResults(
			TransformUtil.transform(
				teams,
				team -> new TeamDisplay(
					renderRequest, renderResponse, team,
					accountsMap.get(team.getAccountKey()))));

		int count = (int)teamWebService.searchCount(keywords, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

}