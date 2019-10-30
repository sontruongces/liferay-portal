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

package com.liferay.osb.koroneiki.phytohormone.web.internal.display.context;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class EntitlementDefinitionsDisplayContext
	extends BaseSearchDisplayContext {

	public EntitlementDefinitionsDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		super(renderRequest, renderResponse, httpServletRequest);
	}

	@Override
	protected SearchContainer createSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, getPortletURL(), Collections.emptyList(),
			"no-entitlement-definitions-were-found");

		long classNameId = 0;

		String tabs1 = ParamUtil.getString(renderRequest, "tabs1", "account");

		if (tabs1.equals("account")) {
			classNameId = PortalUtil.getClassNameId(Account.class);
		}
		else {
			classNameId = PortalUtil.getClassNameId(Contact.class);
		}

		String keywords = StringUtil.quote(getKeywords(), StringPool.PERCENT);

		List<EntitlementDefinition> entitlementDefinitions =
			EntitlementDefinitionLocalServiceUtil.search(
				classNameId, keywords, searchContainer.getStart(),
				searchContainer.getEnd());

		searchContainer.setResults(entitlementDefinitions);

		int total = EntitlementDefinitionLocalServiceUtil.searchCount(
			classNameId, keywords);

		searchContainer.setTotal(total);

		return searchContainer;
	}

}