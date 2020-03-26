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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewAccountDisplayContext {

	public ViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService,
			ExternalLinkWebService externalLinkWebService,
			NoteWebService noteWebService,
			ProductPurchaseViewWebService productPurchaseViewWebService)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountReader = accountReader;
		_contactRoleWebService = contactRoleWebService;
		_contactWebService = contactWebService;
		_externalLinkWebService = externalLinkWebService;
		_noteWebService = noteWebService;
		_productPurchaseViewWebService = productPurchaseViewWebService;

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);

		_accountDisplay = new AccountDisplay(
			httpServletRequest, _accountReader, _account);
	}

	public void addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = _renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			_httpServletRequest,
			LanguageUtil.get(_httpServletRequest, "accounts"),
			accountsPortletURL.toString());

		List<Account> ancestorAccounts = _accountReader.getAncestorAccounts(
			_account);

		for (Account account : ancestorAccounts) {
			PortletURL portletURL = _renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("accountKey", account.getKey());

			PortalUtil.addPortletBreadcrumbEntry(
				_httpServletRequest, account.getName(), portletURL.toString());
		}
	}

	public AccountDisplay getAccountDisplay() {
		return _accountDisplay;
	}

	public List<ContactRole> getContactRoles(String type) throws Exception {
		return _contactRoleWebService.search(
			"type eq '" + type + "'", 1, 1000, "name");
	}

	public SearchContainer getContactsSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(_renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _renderResponse.createRenderURL(),
			Collections.emptyList(), "no-contacts-were-found");

		StringBundler sb = new StringBundler();

		sb.append("customerAccountKeys/any(s:s eq '");
		sb.append(_account.getKey());
		sb.append("')");

		String[] contactRoleKeys = ParamUtil.getStringValues(
			_renderRequest, "contactRoleKeys");

		if (!ArrayUtil.isEmpty(contactRoleKeys)) {
			sb.append(" and accountKeysContactRoleKeys/any(s:");

			for (int i = 0; i < contactRoleKeys.length; i++) {
				if (i > 0) {
					sb.append(" or ");
				}

				sb.append("s eq '");
				sb.append(_account.getKey() + "_" + contactRoleKeys[i]);
				sb.append("'");
			}

			sb.append(")");
		}

		List<Contact> contacts = _contactWebService.search(
			keywords, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						_contactRoleWebService.getAccountCustomerContactRoles(
							_account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						_httpServletRequest, contact, contactRoles);
				}));

		int count = (int)_contactWebService.searchCount(
			keywords, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public String getDeleteNoteURL(String noteKey) {
		PortletURL deleteNoteURL = _renderResponse.createActionURL();

		deleteNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		deleteNoteURL.setParameter(Constants.CMD, Constants.DELETE);
		deleteNoteURL.setParameter("noteKey", noteKey);

		return deleteNoteURL.toString();
	}

	public List<ExternalLinkDisplay> getExternalLinkDisplays()
		throws Exception {

		return TransformUtil.transform(
			_externalLinkWebService.getExternalLinks(
				_account.getKey(), 1, 1000),
			externalLink -> new ExternalLinkDisplay(
				_httpServletRequest, externalLink));
	}

	public Map<String, Object> getPanelData() throws Exception {
		Map<String, Object> data = new HashMap<>();

		PortletURL addNoteURL = _renderResponse.createActionURL();

		addNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		addNoteURL.setParameter("accountKey", _account.getKey());

		data.put("addNoteURL", addNoteURL.toString());

		data.put(
			"notes",
			TransformUtil.transform(
				_noteWebService.getNotes(
					_account.getKey(), StringPool.BLANK, StringPool.BLANK, 1,
					1000),
				note -> new NoteDisplay(_httpServletRequest, note)));

		return data;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = _renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_account");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(_renderRequest, "tabs1"));
		portletURL.setParameter("accountKey", _account.getKey());

		return portletURL;
	}

	public SearchContainer getProductPurchaseViewSearchContainer(String state)
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			_renderRequest, _renderResponse.createRenderURL(),
			Collections.emptyList(), "no-subscriptions-were-found");

		String search = ParamUtil.getString(_renderRequest, "search");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				_account.getKey(), search, state, searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart());

		searchContainer.setResults(
			TransformUtil.transform(
				productPurchaseViews,
				productPurchaseView -> new ProductSubscriptionDisplay(
					_httpServletRequest, _account, productPurchaseView)));

		int count =
			(int)_productPurchaseViewWebService.getProductPurchaseViewsCount(
				_account.getKey(), search, state);

		searchContainer.setTotal(count);

		return searchContainer;
	}

	public String getUpdateNoteURL(String noteKey) {
		PortletURL updateNoteURL = _renderResponse.createActionURL();

		updateNoteURL.setParameter(ActionRequest.ACTION_NAME, "/edit_note");
		updateNoteURL.setParameter("noteKey", noteKey);

		return updateNoteURL.toString();
	}

	private final Account _account;
	private final AccountDisplay _accountDisplay;
	private final AccountReader _accountReader;
	private final ContactRoleWebService _contactRoleWebService;
	private final ContactWebService _contactWebService;
	private final ExternalLinkWebService _externalLinkWebService;
	private final HttpServletRequest _httpServletRequest;
	private final NoteWebService _noteWebService;
	private final ProductPurchaseViewWebService _productPurchaseViewWebService;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}