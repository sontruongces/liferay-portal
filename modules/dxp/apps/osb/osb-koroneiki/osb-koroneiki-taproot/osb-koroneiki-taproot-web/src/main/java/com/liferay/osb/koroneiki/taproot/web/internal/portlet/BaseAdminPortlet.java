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

package com.liferay.osb.koroneiki.taproot.web.internal.portlet;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseAdminPortlet extends MVCPortlet {

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
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void serveExternalLinkDomains(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String domain = ParamUtil.getString(resourceRequest, "domain");

		if (domain.length() == 1) {
			domain += StringPool.PERCENT;
		}
		else {
			domain = StringPool.PERCENT + domain + StringPool.PERCENT;
		}

		List<ExternalLink> externalLinks = externalLinkLocalService.search(
			domain);

		JSONArray externalLinksArray = JSONFactoryUtil.createJSONArray();

		for (ExternalLink externalLink : externalLinks) {
			if (Validator.isNotNull(domain)) {
				externalLinksArray.put(externalLink.getDomain());
			}
		}

		writeJSON(resourceRequest, resourceResponse, externalLinksArray);
	}

	protected void serveExternalLinkEntityNames(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String domain = ParamUtil.getString(resourceRequest, "domain");

		String entityName = ParamUtil.getString(resourceRequest, "entityName");

		if (entityName.length() == 1) {
			entityName += StringPool.PERCENT;
		}
		else {
			entityName = StringPool.PERCENT + entityName + StringPool.PERCENT;
		}

		List<ExternalLink> externalLinks = externalLinkLocalService.search(
			domain, entityName);

		JSONArray externalLinksArray = JSONFactoryUtil.createJSONArray();

		for (ExternalLink externalLink : externalLinks) {
			if (Validator.isNotNull(entityName)) {
				externalLinksArray.put(externalLink.getEntityName());
			}
		}

		writeJSON(resourceRequest, resourceResponse, externalLinksArray);
	}

	@Reference
	protected ExternalLinkLocalService externalLinkLocalService;

}