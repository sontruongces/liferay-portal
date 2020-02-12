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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
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
public class ViewAccountDisplayContext {

	public ViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest,
			AccountWebService accountWebService)
		throws Exception {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
		_httpServletRequest = httpServletRequest;
		_accountWebService = accountWebService;

		_account = (Account)renderRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);
		_themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_scanAccount(_account);

		_addPortletBreadcrumbEntries();
	}

	public Account getAccount() {
		return _account;
	}

	public String getPrimaryCountry() {
		PostalAddress[] postalAddresses = _account.getPostalAddresses();

		if (postalAddresses != null) {
			for (PostalAddress postalAddress : postalAddresses) {
				if ((postalAddress.getPrimary() != null) &&
					postalAddress.getPrimary()) {

					return postalAddress.getAddressCountry();
				}
			}
		}

		return StringPool.BLANK;
	}

	public boolean isEWSA() throws Exception {
		return _ewsa;
	}

	private void _addPortletBreadcrumbEntries() {
		PortletURL accountsPortletURL = _renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			_httpServletRequest, _themeDisplay.translate("accounts"),
			accountsPortletURL.toString());

		for (Account account : _accountsHeirarchy) {
			PortletURL portletURL = _renderResponse.createRenderURL();

			portletURL.setParameter(
				"mvcRenderCommandName", "/accounts/view_account");
			portletURL.setParameter("accountKey", account.getKey());

			PortalUtil.addPortletBreadcrumbEntry(
				_httpServletRequest, account.getName(), portletURL.toString());
		}
	}

	private void _scanAccount(Account account) throws Exception {
		_accountsHeirarchy.add(0, account);

		ProductPurchase[] productPurchases = account.getProductPurchases();

		if ((productPurchases != null) && !_ewsa) {
			for (ProductPurchase productPurchase : productPurchases) {
				Product product = productPurchase.getProduct();

				String name = product.getName();

				if (name.equals(ProductConstants.NAME_DXP_EWSA) ||
					name.equals(ProductConstants.NAME_PORTAL_EWSA)) {

					_ewsa = true;
				}
			}
		}

		if (Validator.isNotNull(account.getParentAccountKey())) {
			Account parentAccount = _accountWebService.fetchAccount(
				account.getParentAccountKey());

			if (parentAccount != null) {
				_scanAccount(parentAccount);
			}
		}
	}

	private final Account _account;
	private final List<Account> _accountsHeirarchy = new ArrayList<>();
	private final AccountWebService _accountWebService;
	private boolean _ewsa;
	private final HttpServletRequest _httpServletRequest;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;
	private final ThemeDisplay _themeDisplay;

}