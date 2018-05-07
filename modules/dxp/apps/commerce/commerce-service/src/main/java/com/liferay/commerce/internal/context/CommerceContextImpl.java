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

package com.liferay.commerce.internal.context;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.service.CommerceCurrencyService;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderHttpHelper;
import com.liferay.commerce.organization.util.CommerceOrganizationHelper;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.user.segment.util.CommerceUserSegmentHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.util.Portal;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class CommerceContextImpl implements CommerceContext {

	public CommerceContextImpl(
		HttpServletRequest httpServletRequest,
		CommerceCurrencyService commerceCurrencyService,
		CommerceOrderHttpHelper commerceOrderHttpHelper,
		CommerceOrganizationHelper commerceOrganizationHelper,
		CommercePriceListLocalService commercePriceListLocalService,
		CommerceUserSegmentHelper commerceUserSegmentHelper, Portal portal) {

		_httpServletRequest = httpServletRequest;
		_commerceCurrencyService = commerceCurrencyService;
		_commerceOrderHttpHelper = commerceOrderHttpHelper;
		_commerceOrganizationHelper = commerceOrganizationHelper;
		_commercePriceListLocalService = commercePriceListLocalService;
		_commerceUserSegmentHelper = commerceUserSegmentHelper;
		_portal = portal;
	}

	@Override
	public CommerceCurrency getCommerceCurrency() throws PortalException {
		if (_commerceCurrency != null) {
			return _commerceCurrency;
		}

		long groupId = _portal.getScopeGroupId(_httpServletRequest);

		_commerceCurrency =
			_commerceCurrencyService.fetchPrimaryCommerceCurrency(groupId);

		return _commerceCurrency;
	}

	@Override
	public CommerceOrder getCommerceOrder() throws PortalException {
		if (_commerceOrder != null) {
			return _commerceOrder;
		}

		_commerceOrder = _commerceOrderHttpHelper.getCurrentCommerceOrder(
			_httpServletRequest);

		return _commerceOrder;
	}

	@Override
	public Optional<CommercePriceList> getCommercePriceList()
		throws PortalException {

		long groupId = _portal.getScopeGroupId(_httpServletRequest);

		return _commercePriceListLocalService.getCommercePriceList(
			groupId, getCommerceUserSegmentEntryIds());
	}

	@Override
	public long[] getCommerceUserSegmentEntryIds() throws PortalException {
		return _commerceUserSegmentHelper.getCommerceUserSegmentIds(
			_httpServletRequest);
	}

	@Override
	public Organization getOrganization() throws PortalException {
		if (_organization != null) {
			return _organization;
		}

		_organization = _commerceOrganizationHelper.getCurrentOrganization(
			_httpServletRequest);

		return _organization;
	}

	private CommerceCurrency _commerceCurrency;
	private final CommerceCurrencyService _commerceCurrencyService;
	private CommerceOrder _commerceOrder;
	private final CommerceOrderHttpHelper _commerceOrderHttpHelper;
	private final CommerceOrganizationHelper _commerceOrganizationHelper;
	private final CommercePriceListLocalService _commercePriceListLocalService;
	private final CommerceUserSegmentHelper _commerceUserSegmentHelper;
	private final HttpServletRequest _httpServletRequest;
	private Organization _organization;
	private final Portal _portal;

}