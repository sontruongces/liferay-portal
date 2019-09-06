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

package com.liferay.osb.koroneiki.taproot.web.internal.display.context;

import com.liferay.osb.koroneiki.taproot.constants.ContactRoleType;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.web.internal.search.ContactRoleSearch;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ContactRolesDisplayContext {

	public ContactRolesDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
	}

	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = ParamUtil.getString(_httpServletRequest, "keywords");
		}

		return _keywords;
	}

	public String getOrderByCol() {
		if (Validator.isNull(_orderByCol)) {
			_orderByCol = ParamUtil.getString(
				_httpServletRequest, "orderByCol", "name");
		}

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNull(_orderByType)) {
			_orderByType = ParamUtil.getString(
				_httpServletRequest, "orderByType", "asc");
		}

		return _orderByType;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		if (Validator.isNotNull(getKeywords())) {
			portletURL.setParameter("keywords", getKeywords());
		}

		portletURL.setParameter("orderByCol", getOrderByCol());
		portletURL.setParameter("orderByType", getOrderByType());
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(_renderRequest, "tabs1"));

		if (_contactRoleSearch != null) {
			portletURL.setParameter(
				_contactRoleSearch.getCurParam(),
				String.valueOf(_contactRoleSearch.getCur()));
			portletURL.setParameter(
				_contactRoleSearch.getDeltaParam(),
				String.valueOf(_contactRoleSearch.getDelta()));
		}

		return portletURL;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (_contactRoleSearch != null) {
			return _contactRoleSearch;
		}

		ContactRoleSearch contactRoleSearch = new ContactRoleSearch(
			_renderRequest, getPortletURL());

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		int type = 0;

		String tabs1 = ParamUtil.getString(_renderRequest, "tabs1");

		if (tabs1.equals("team-contact-roles")) {
			type = ContactRoleType.TEAM;
		}
		else {
			type = ContactRoleType.ACCOUNT;
		}

		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		Sort sort = SortFactoryUtil.getSort(
			ContactRole.class, Sort.STRING_TYPE, getOrderByCol(),
			getOrderByType());

		Hits hits = ContactRoleLocalServiceUtil.search(
			themeDisplay.getCompanyId(), type, keywords,
			contactRoleSearch.getStart(), contactRoleSearch.getEnd(), sort);

		List<ContactRole> results = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			long contactRoleId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			results.add(
				ContactRoleLocalServiceUtil.getContactRole(contactRoleId));
		}

		contactRoleSearch.setResults(results);
		contactRoleSearch.setTotal(hits.getLength());

		_contactRoleSearch = contactRoleSearch;

		return _contactRoleSearch;
	}

	private ContactRoleSearch _contactRoleSearch;
	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}