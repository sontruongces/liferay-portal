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

package com.liferay.osb.koroneiki.trunk.web.internal.portlet;

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.trunk.constants.TrunkPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=koroneiki-products-admin-portlet",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.render-weight=0",
		"javax.portlet.display-name=Products",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + TrunkPortletKeys.PRODUCTS_ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class ProductsAdminPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("externalLinkDomains")) {
				serveExternalLinkDomains(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("externalLinkEntityNames")) {
				serveExternalLinkEntityNames(resourceRequest, resourceResponse);
			}
		}
		catch (Exception exception) {
			throw new PortletException(exception);
		}
	}

	protected void serveExternalLinkDomains(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String domain = ParamUtil.getString(resourceRequest, "domain");

		if (Validator.isNotNull(domain)) {
			if (domain.length() == 1) {
				domain += StringPool.PERCENT;
			}
			else {
				domain = StringPool.PERCENT + domain + StringPool.PERCENT;
			}

			List<String> domains = _externalLinkLocalService.search(domain);

			JSONArray domainsArray = JSONFactoryUtil.createJSONArray();

			for (String curDomain : domains) {
				domainsArray.put(curDomain);
			}

			writeJSON(resourceRequest, resourceResponse, domainsArray);
		}
	}

	protected void serveExternalLinkEntityNames(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String entityName = ParamUtil.getString(resourceRequest, "entityName");

		if (Validator.isNotNull(entityName)) {
			if (entityName.length() == 1) {
				entityName += StringPool.PERCENT;
			}
			else {
				entityName =
					StringPool.PERCENT + entityName + StringPool.PERCENT;
			}

			String domain = ParamUtil.getString(resourceRequest, "domain");

			List<String> entityNames = _externalLinkLocalService.search(
				domain, entityName);

			JSONArray entityNamesArray = JSONFactoryUtil.createJSONArray();

			for (String curEntityName : entityNames) {
				entityNamesArray.put(curEntityName);
			}

			writeJSON(resourceRequest, resourceResponse, entityNamesArray);
		}
	}

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

}