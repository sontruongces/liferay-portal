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
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Kyle Bischof
 */
public class AccountSearchDisplayContext {

	public AccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		AccountWebService accountWebService) {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;

		_accountWebService = accountWebService;
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-accounts-were-found");

		String keywords = ParamUtil.getString(renderRequest, "keywords");

		String filterString = StringPool.BLANK;

		if (Validator.isNotNull(keywords)) {
			filterString = "name eq '" + keywords + "'";
		}

		List<Account> accounts = _accountWebService.search(
			filterString, searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), null);

		searchContainer.setResults(accounts);

		int count = (int)_accountWebService.searchCount(filterString);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	protected final RenderRequest renderRequest;
	protected final RenderResponse renderResponse;

	private final AccountWebService _accountWebService;

}