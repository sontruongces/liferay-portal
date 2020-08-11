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
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class AccountSearchDisplayContext {

	public AccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest, AccountReader accountReader,
		AccountWebService accountWebService) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountReader = accountReader;
		_accountWebService = accountWebService;

		_currentURLObj = PortletURLUtil.getCurrent(
			_renderRequest, _renderResponse);
	}

	public SearchContainer getSearchContainer() throws Exception {
		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _currentURLObj, Collections.emptyList(),
			"no-accounts-were-found");

		String[] keywords = StringUtil.split(
			ParamUtil.getString(_renderRequest, "keywords"), StringPool.SPACE);

		StringBundler sb = new StringBundler();

		if (!ArrayUtil.isEmpty(keywords)) {
			for (int i = 0; i < keywords.length; i++) {
				String keyword = keywords[i];

				sb.append("(contains(code, '");
				sb.append(keyword);
				sb.append("') or contains(name, '");
				sb.append(keyword);
				sb.append("')");

				if (i < (keywords.length - 1)) {
					sb.append(" or ");
				}
			}
		}

		String sort = StringPool.BLANK;

		if (ArrayUtil.isEmpty(keywords)) {
			sort = "name";
		}

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), sort);

		searchContainer.setResults(
			TransformUtil.transform(
				accounts,
				account -> new AccountDisplay(
					_renderRequest, _renderResponse, _accountReader, account)));

		int count = (int)_accountWebService.searchCount(
			StringPool.BLANK, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private final AccountReader _accountReader;
	private final AccountWebService _accountWebService;
	private final PortletURL _currentURLObj;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}