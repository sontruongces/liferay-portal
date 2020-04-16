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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.util.Collections;
import java.util.List;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.constraints.NotNull;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseProductPurchaseViewResourceImpl
	implements ProductPurchaseViewResource {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/product-purchase-views'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's products and related product consumptions and product purchases."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.QUERY, name = "productNames"),
			@Parameter(in = ParameterIn.QUERY, name = "state"),
			@Parameter(in = ParameterIn.QUERY, name = "search"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountKey}/product-purchase-views")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchaseView")})
	public Page<ProductPurchaseView>
			getAccountAccountKeyProductPurchaseViewsPage(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@Parameter(hidden = true) @QueryParam("productNames") String[]
					productNames,
				@Parameter(hidden = true) @QueryParam("state") String state,
				@Parameter(hidden = true) @QueryParam("search") String search,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/koroneiki-rest/v1.0/accounts/{accountKey}/product/{productKey}/product-purchase-view'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Operation(
		description = "Retrieves the account's product consumptions and product purchases based on product."
	)
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountKey"),
			@Parameter(in = ParameterIn.PATH, name = "productKey")
		}
	)
	@Path("/accounts/{accountKey}/product/{productKey}/product-purchase-view")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchaseView")})
	public ProductPurchaseView
			getAccountAccountKeyProductProductKeyProductPurchaseView(
				@NotNull @Parameter(hidden = true) @PathParam("accountKey")
					String accountKey,
				@NotNull @Parameter(hidden = true) @PathParam("productKey")
					String productKey)
		throws Exception {

		return new ProductPurchaseView();
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	public void setContextHttpServletRequest(
		HttpServletRequest contextHttpServletRequest) {

		this.contextHttpServletRequest = contextHttpServletRequest;
	}

	public void setContextHttpServletResponse(
		HttpServletResponse contextHttpServletResponse) {

		this.contextHttpServletResponse = contextHttpServletResponse;
	}

	public void setContextUriInfo(UriInfo contextUriInfo) {
		this.contextUriInfo = contextUriInfo;
	}

	public void setContextUser(User contextUser) {
		this.contextUser = contextUser;
	}

	protected void preparePatch(
		ProductPurchaseView productPurchaseView,
		ProductPurchaseView existingProductPurchaseView) {
	}

	protected <T, R> List<R> transform(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transform(collection, unsafeFunction);
	}

	protected <T, R> R[] transform(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction,
		Class<?> clazz) {

		return TransformUtil.transform(array, unsafeFunction, clazz);
	}

	protected <T, R> R[] transformToArray(
		java.util.Collection<T> collection,
		UnsafeFunction<T, R, Exception> unsafeFunction, Class<?> clazz) {

		return TransformUtil.transformToArray(
			collection, unsafeFunction, clazz);
	}

	protected <T, R> List<R> transformToList(
		T[] array, UnsafeFunction<T, R, Exception> unsafeFunction) {

		return TransformUtil.transformToList(array, unsafeFunction);
	}

	protected AcceptLanguage contextAcceptLanguage;
	protected Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected UriInfo contextUriInfo;
	protected User contextUser;

}