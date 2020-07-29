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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yuanyuan Huang
 */
public class ProductPurchaseDisplayContext {

	public ProductPurchaseDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_httpServletRequest = httpServletRequest;

		_productPurchase = (ProductPurchase)_httpServletRequest.getAttribute(
			ProvisioningWebKeys.PRODUCT_PURCHASE);

		_account = (Account)_httpServletRequest.getAttribute(
			ProvisioningWebKeys.ACCOUNT);

		_productConsumptionsCount = ParamUtil.getInteger(
			_httpServletRequest, "provisionedCount");
	}

	public String getAccountName() {
		return _account.getName();
	}

	public ProductPurchaseDisplay getProductPurchaseDisplay() {
		return new ProductPurchaseDisplay(
			_httpServletRequest, _productPurchase, _productConsumptionsCount);
	}

	private final Account _account;
	private final HttpServletRequest _httpServletRequest;
	private final int _productConsumptionsCount;
	private final ProductPurchase _productPurchase;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}