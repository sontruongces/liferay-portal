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

import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalServiceUtil;
import com.liferay.osb.koroneiki.taproot.web.internal.search.TeamSearch;
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
public class TeamsDisplayContext {

	public TeamsDisplayContext(
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

		if (_teamSearch != null) {
			portletURL.setParameter(
				_teamSearch.getCurParam(),
				String.valueOf(_teamSearch.getCur()));
			portletURL.setParameter(
				_teamSearch.getDeltaParam(),
				String.valueOf(_teamSearch.getDelta()));
		}

		return portletURL;
	}

	public SearchContainer getSearchContainer() throws Exception {
		if (_teamSearch != null) {
			return _teamSearch;
		}

		TeamSearch teamSearch = new TeamSearch(_renderRequest, getPortletURL());

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(_httpServletRequest, "keywords");

		Sort sort = SortFactoryUtil.getSort(
			Team.class, Sort.STRING_TYPE, getOrderByCol(), getOrderByType());

		Hits hits = TeamLocalServiceUtil.search(
			themeDisplay.getCompanyId(), keywords, teamSearch.getStart(),
			teamSearch.getEnd(), sort);

		List<Team> results = new ArrayList<>();

		for (Document document : hits.getDocs()) {
			long teamId = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			results.add(TeamLocalServiceUtil.getTeam(teamId));
		}

		teamSearch.setResults(results);
		teamSearch.setTotal(hits.getLength());

		_teamSearch = teamSearch;

		return _teamSearch;
	}

	private final HttpServletRequest _httpServletRequest;
	private String _keywords;
	private String _orderByCol;
	private String _orderByType;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private TeamSearch _teamSearch;

}