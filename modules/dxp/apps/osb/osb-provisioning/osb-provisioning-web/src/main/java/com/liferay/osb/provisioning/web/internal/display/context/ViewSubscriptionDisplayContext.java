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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class ViewSubscriptionDisplayContext extends ViewAccountDisplayContext {

	public ViewSubscriptionDisplayContext() {
	}

	@Override
	public void addPortletBreadcrumbEntries() throws Exception {
		super.addPortletBreadcrumbEntries();

		PortletURL portletURL = getPortletURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, _productPurchaseViewDisplay.getName(),
			portletURL.toString());
	}

	@Override
	public void doInit() throws Exception {
		super.doInit();

		_productPurchaseView = (ProductPurchaseView)renderRequest.getAttribute(
			ProvisioningWebKeys.PRODUCT_PURCHASE_VIEW);

		_productPurchaseViewDisplay = new ProductPurchaseViewDisplay(
			httpServletRequest, account, _productPurchaseView);
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			"mvcRenderCommandName", "/accounts/view_subscription");
		portletURL.setParameter(
			"accountKey", _productPurchaseViewDisplay.getAccountKey());
		portletURL.setParameter(
			"productKey", _productPurchaseViewDisplay.getProductKey());

		return portletURL;
	}

	public ProductPurchaseViewDisplay getProductPurchaseViewDisplay() {
		return _productPurchaseViewDisplay;
	}

	private ProductPurchaseView _productPurchaseView;
	private ProductPurchaseViewDisplay _productPurchaseViewDisplay;

}