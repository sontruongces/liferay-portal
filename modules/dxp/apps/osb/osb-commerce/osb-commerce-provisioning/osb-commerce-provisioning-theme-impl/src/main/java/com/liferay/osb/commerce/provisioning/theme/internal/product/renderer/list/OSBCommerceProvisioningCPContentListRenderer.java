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

package com.liferay.osb.commerce.provisioning.theme.internal.product.renderer.list;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.content.render.list.CPContentListRenderer;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gianmarco Brunialti Masera
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.content.list.renderer.key=" + OSBCommerceProvisioningCPContentListRenderer.KEY,
		"commerce.product.content.list.renderer.order=1000",
		"commerce.product.content.list.renderer.portlet.name=" + CPPortletKeys.CP_PUBLISHER_WEB
	},
	service = CPContentListRenderer.class
)
public class OSBCommerceProvisioningCPContentListRenderer
	implements CPContentListRenderer {

	public static final String KEY = "osb-commerce-provisioning-list";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "osb-commerce-provisioning");
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletRequest.setAttribute(
			"osb-commerce-provisioning:CPEntriesMap",
			_getCPEntriesMap(httpServletRequest));

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/product_publisher/render/view.jsp");
	}

	private Map<String, Object> _getCPEntriesMap(HttpServletRequest request)
		throws PortalException {

		CPDataSourceResult cpDataSourceResult =
			(CPDataSourceResult)request.getAttribute(
				CPWebKeys.CP_DATA_SOURCE_RESULT);

		List<Map<String, Object>> cpEntries = new ArrayList<>();

		for (CPCatalogEntry cpCatalogEntry :
				cpDataSourceResult.getCPCatalogEntries()) {

			cpEntries.add(_getCPEntryMap(cpCatalogEntry, request));
		}

		return new HashMap<String, Object>() {
			{
				put("cpEntries", cpEntries);
			}
		};
	}

	private Map<String, Object> _getCPEntryMap(
			CPCatalogEntry cpCatalogEntry, HttpServletRequest request)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<String, Object> cpEntryMap = new HashMap<>();

		cpEntryMap.put("description", cpCatalogEntry.getShortDescription());

		cpEntryMap.put(
			"detailURL",
			_cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay));

		cpEntryMap.put("name", cpCatalogEntry.getName());
		cpEntryMap.put("productId", cpCatalogEntry.getCPDefinitionId());
		cpEntryMap.put(
			"productImageURL", cpCatalogEntry.getDefaultImageFileUrl());

		List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

		CPSku cpSku = null;

		if (cpSkus.size() == 1) {
			cpSku = cpSkus.get(0);
		}

		if (cpSku != null) {
			cpEntryMap.put("sku", cpSku.getSku());
			cpEntryMap.put("skuId", cpSku.getCPInstanceId());
		}

		cpEntryMap.put(
			"spritemap",
			themeDisplay.getPathThemeImages() + "/lexicon/icons.svg");

		return cpEntryMap;
	}

	@Reference
	private CPContentHelper _cpContentHelper;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.osb.commerce.provisioning.theme.impl)"
	)
	private ServletContext _servletContext;

}