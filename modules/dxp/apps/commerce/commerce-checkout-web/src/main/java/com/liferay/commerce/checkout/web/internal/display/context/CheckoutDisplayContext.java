/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.checkout.web.internal.display.context;

import com.liferay.commerce.checkout.web.util.CommerceCheckoutStep;
import com.liferay.commerce.checkout.web.util.CommerceCheckoutStepServicesTracker;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.model.CommerceCart;
import com.liferay.commerce.util.CommerceCartHelper;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Marco Leo
 */
public class CheckoutDisplayContext {

	public CheckoutDisplayContext(
			CommerceCartHelper commerceCartHelper,
			CommerceCheckoutStepServicesTracker
				commerceCheckoutStepServicesTracker,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		_commerceCartHelper = commerceCartHelper;
		_commerceCheckoutStepServicesTracker =
			commerceCheckoutStepServicesTracker;

		_httpServletRequest = httpServletRequest;
		_httpServletResponse = httpServletResponse;

		_commerceCart = _commerceCartHelper.getCurrentCommerceCart(
			httpServletRequest, httpServletResponse,
			CommerceConstants.COMMERCE_CART_TYPE_CART);

		String checkoutStepName = httpServletRequest.getParameter(
			"checkoutStepName");

		_currentCheckoutStep =
			_commerceCheckoutStepServicesTracker.getCommerceCheckoutStep(
				checkoutStepName);
	}

	public List<CommerceCheckoutStep> getCommerceCheckoutSteps() {
		return _commerceCheckoutStepServicesTracker.getCommerceCheckoutSteps();
	}

	public String getCurrentCheckoutStepName() {
		return _commerceCart.getName();
	}

	public PortletURL getNextCheckoutStepPortletURL() {
		return null;
	}

	public PortletURL getPreviusCheckoutStepPortletURL() {
		return null;
	}

	public boolean isCurrentCommerceCheckoutStep(
		CommerceCheckoutStep commerceCheckoutStep) {

		return false;
	}

	public void renderCurrentCheckoutStep() throws Exception {
		_currentCheckoutStep.render(_httpServletRequest, _httpServletResponse);
	}

	private final CommerceCart _commerceCart;
	private final CommerceCartHelper _commerceCartHelper;
	private final CommerceCheckoutStepServicesTracker
		_commerceCheckoutStepServicesTracker;
	private final CommerceCheckoutStep _currentCheckoutStep;
	private final HttpServletRequest _httpServletRequest;
	private final HttpServletResponse _httpServletResponse;

}