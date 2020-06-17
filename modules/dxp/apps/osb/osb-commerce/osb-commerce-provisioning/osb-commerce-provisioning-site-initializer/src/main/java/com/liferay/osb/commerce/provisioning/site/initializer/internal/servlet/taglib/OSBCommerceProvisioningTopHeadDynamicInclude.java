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

package com.liferay.osb.commerce.provisioning.site.initializer.internal.servlet.taglib;

import com.liferay.layout.admin.constants.LayoutAdminPortletKeys;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.Theme;
import com.liferay.portal.kernel.service.ThemeLocalService;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;
import com.liferay.portal.kernel.servlet.PortalWebResourcesUtil;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Gianmarco Brunialti Masera
 */
@Component(immediate = true, service = DynamicInclude.class)
public class OSBCommerceProvisioningTopHeadDynamicInclude
	implements DynamicInclude {

	@Override
	public void include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String key)
		throws IOException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Group scopeGroup = themeDisplay.getScopeGroup();

		LayoutSet publicLayoutSet = scopeGroup.getPublicLayoutSet();
		LayoutSet privateLayoutSet = scopeGroup.getPrivateLayoutSet();

		if (!Objects.equals(publicLayoutSet.getThemeId(), _THEME_ID) &&
			!Objects.equals(privateLayoutSet.getThemeId(), _THEME_ID)) {

			return;
		}

		Layout layout = themeDisplay.getLayout();

		if (!layout.isTypeControlPanel()) {
			return;
		}

		if (!Objects.equals(
				themeDisplay.getPpid(), LayoutAdminPortletKeys.GROUP_PAGES)) {

			return;
		}

		PrintWriter printWriter = httpServletResponse.getWriter();

		printWriter.write("<link data-senna-track=\"permanent\" href=\"");

		StringBundler sb = new StringBundler(4);

		sb.append(themeDisplay.getCDNBaseURL());
		sb.append(_portal.getPathProxy());

		Theme theme = _themeLocalService.getTheme(
			themeDisplay.getCompanyId(), _THEME_ID);

		sb.append(theme.getContextPath());

		sb.append("/css/fragments_editor.css");

		long themeLastModified = PortalWebResourcesUtil.getLastModified(
			PortalWebResourceConstants.RESOURCE_TYPE_THEME_CONTRIBUTOR);

		String staticResourceURL = _portal.getStaticResourceURL(
			httpServletRequest, sb.toString(), themeLastModified);

		printWriter.write(staticResourceURL);

		printWriter.write("\" rel=\"stylesheet\" type = \"text/css\" />\n");
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			"/html/common/themes/top_head.jsp#post");
	}

	private static final String _THEME_ID =
		"osbcommerceprovisioningtheme_WAR_osbcommerceprovisioningtheme";

	@Reference
	private Portal _portal;

	@Reference
	private ThemeLocalService _themeLocalService;

}