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

package com.liferay.commerce.product.definitions.web.internal.frontend;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.definitions.web.internal.model.ProductOptionValue;
import com.liferay.commerce.product.definitions.web.internal.security.permission.resource.CommerceCatalogPermission;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.service.CPDefinitionOptionValueRelService;
import com.liferay.frontend.taglib.clay.data.set.ClayDataSetActionProvider;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = {
		"clay.data.provider.key=" + CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_OPTION_VALUES,
		"clay.data.provider.key=" + CommerceProductDataSetConstants.COMMERCE_DATA_SET_KEY_PRODUCT_OPTION_VALUES_STATIC
	},
	service = ClayDataSetActionProvider.class
)
public class CommerceProductOptionValueDataSetActionProvider
	implements ClayDataSetActionProvider {

	@Override
	public List<DropdownItem> getDropdownItems(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		ProductOptionValue productOptionValue = (ProductOptionValue)model;

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			_cpDefinitionOptionValueRelService.getCPDefinitionOptionValueRel(
				productOptionValue.getCPDefinitionOptionValueRelId());

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		return DropdownItemListBuilder.add(
			() -> CommerceCatalogPermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				cpDefinitionOptionRel.getCPDefinition(), ActionKeys.UPDATE),
			dropdownItem -> {
				dropdownItem.setHref(
					_getProductOptionValueEditURL(
						cpDefinitionOptionValueRel, httpServletRequest));
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "edit"));
				dropdownItem.setTarget("sidePanel");
			}
		).add(
			() -> CommerceCatalogPermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				cpDefinitionOptionRel.getCPDefinition(), ActionKeys.UPDATE),
			dropdownItem -> {
				dropdownItem.setHref(
					_getProductOptionValueDeleteURL(
						cpDefinitionOptionValueRel.
							getCPDefinitionOptionValueRelId(),
						httpServletRequest));
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "delete"));
			}
		).add(
			() -> CommerceCatalogPermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				cpDefinitionOptionRel.getCPDefinition(), ActionKeys.UPDATE),
			dropdownItem -> {
				dropdownItem.setHref(
					_getProductOptionValueUpdatePreselectedURL(
						cpDefinitionOptionValueRel.
							getCPDefinitionOptionValueRelId(),
						httpServletRequest));
				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "toggle-default"));
				dropdownItem.put("id", "updatePreselected");
			}
		).build();
	}

	private PortletURL _getProductOptionValueDeleteURL(
		long cpDefinitionOptionValueRelId,
		HttpServletRequest httpServletRequest) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			_portal.getOriginalServletRequest(httpServletRequest),
			CPPortletKeys.CP_DEFINITIONS, PortletRequest.ACTION_PHASE);

		String redirect = ParamUtil.getString(
			httpServletRequest, "currentUrl",
			_portal.getCurrentURL(httpServletRequest));

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editProductDefinitionOptionValueRel");
		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter(
			"cpDefinitionOptionValueRelId",
			String.valueOf(cpDefinitionOptionValueRelId));

		return portletURL;
	}

	private PortletURL _getProductOptionValueEditURL(
			CPDefinitionOptionValueRel cpDefinitionOptionValueRel,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		PortletURL portletURL = PortletProviderUtil.getPortletURL(
			httpServletRequest, CPDefinition.class.getName(),
			PortletProvider.Action.MANAGE);

		portletURL.setParameter(
			"mvcRenderCommandName", "editProductDefinitionOptionValueRel");

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		portletURL.setParameter(
			"cpDefinitionId",
			String.valueOf(cpDefinitionOptionRel.getCPDefinitionId()));
		portletURL.setParameter(
			"cpDefinitionOptionRelId",
			String.valueOf(cpDefinitionOptionRel.getCPDefinitionOptionRelId()));

		portletURL.setParameter(
			"cpDefinitionOptionValueRelId",
			String.valueOf(
				cpDefinitionOptionValueRel.getCPDefinitionOptionValueRelId()));

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException windowStateException) {
			_log.error(windowStateException, windowStateException);
		}

		return portletURL;
	}

	private PortletURL _getProductOptionValueUpdatePreselectedURL(
		long cpDefinitionOptionValueRelId,
		HttpServletRequest httpServletRequest) {

		PortletURL portletURL = _portal.getControlPanelPortletURL(
			_portal.getOriginalServletRequest(httpServletRequest),
			CPPortletKeys.CP_DEFINITIONS, PortletRequest.ACTION_PHASE);

		String redirect = ParamUtil.getString(
			httpServletRequest, "currentUrl",
			_portal.getCurrentURL(httpServletRequest));

		portletURL.setParameter(
			ActionRequest.ACTION_NAME, "editProductDefinitionOptionValueRel");
		portletURL.setParameter(Constants.CMD, "updatePreselected");
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter(
			"cpDefinitionOptionValueRelId",
			String.valueOf(cpDefinitionOptionValueRelId));

		return portletURL;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductOptionValueDataSetActionProvider.class);

	@Reference
	private CPDefinitionOptionValueRelService
		_cpDefinitionOptionValueRelService;

	@Reference
	private Portal _portal;

}