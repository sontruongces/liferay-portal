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
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.Collections;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewSearchDisplayContext {

	public ProductPurchaseViewSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		ProductPurchaseViewWebService productPurchaseViewWebService) {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;

		_productPurchaseViewWebService = productPurchaseViewWebService;
	}

	public SearchContainer getSearchContainer(String accountKey)
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-subscriptions-were-found");

		List<ProductPurchaseView> productPurchaseViews =
			_productPurchaseViewWebService.getProductPurchaseViews(
				accountKey, searchContainer.getCur(),
				searchContainer.getEnd() - searchContainer.getStart());

		searchContainer.setResults(productPurchaseViews);
		searchContainer.setTotal(productPurchaseViews.size());

		return searchContainer;
	}

	protected final RenderRequest renderRequest;
	protected final RenderResponse renderResponse;

	private final ProductPurchaseViewWebService _productPurchaseViewWebService;

}