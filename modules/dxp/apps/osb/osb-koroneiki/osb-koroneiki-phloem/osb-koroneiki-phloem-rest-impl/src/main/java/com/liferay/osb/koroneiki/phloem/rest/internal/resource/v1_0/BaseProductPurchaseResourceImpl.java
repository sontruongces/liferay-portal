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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.Company;
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

import javax.validation.constraints.NotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public abstract class BaseProductPurchaseResourceImpl
	implements ProductPurchaseResource {

	@Override
	@GET
	@Operation(description = "Retrieves the account's product purchases.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountId"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/accounts/{accountId}/product-purchases")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public Page<ProductPurchase> getAccountProductPurchasesPage(
			@NotNull @Parameter(hidden = true) @PathParam("accountId") Long
				accountId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "accountId"),
			@Parameter(in = ParameterIn.QUERY, name = "productId")
		}
	)
	@Path("/accounts/{accountId}/product-purchases")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public ProductPurchase postAccountProductPurchase(
			@NotNull @Parameter(hidden = true) @PathParam("accountId") Long
				accountId,
			@NotNull @Parameter(hidden = true) @QueryParam("productId") Long
				productId,
			ProductPurchase productPurchase)
		throws Exception {

		return new ProductPurchase();
	}

	@Override
	@DELETE
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "productPurchaseId")}
	)
	@Path("/product-purchases/{productPurchaseId}")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public void deleteProductPurchase(
			@NotNull @Parameter(hidden = true) @PathParam("productPurchaseId")
				Long productPurchaseId)
		throws Exception {
	}

	@Override
	@GET
	@Operation(description = "Retrieves the product purchase.")
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "productPurchaseId")}
	)
	@Path("/product-purchases/{productPurchaseId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public ProductPurchase getProductPurchase(
			@NotNull @Parameter(hidden = true) @PathParam("productPurchaseId")
				Long productPurchaseId)
		throws Exception {

		return new ProductPurchase();
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "productPurchaseId")}
	)
	@Path("/product-purchases/{productPurchaseId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public ProductPurchase putProductPurchase(
			@NotNull @Parameter(hidden = true) @PathParam("productPurchaseId")
				Long productPurchaseId,
			ProductPurchase productPurchase)
		throws Exception {

		return new ProductPurchase();
	}

	@Override
	@GET
	@Operation(description = "Retrieves the project's product purchases.")
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "projectId"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/projects/{projectId}/product-purchases")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public Page<ProductPurchase> getProjectProductPurchasesPage(
			@NotNull @Parameter(hidden = true) @PathParam("projectId") Long
				projectId,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "projectId"),
			@Parameter(in = ParameterIn.QUERY, name = "productId")
		}
	)
	@Path("/projects/{projectId}/product-purchases")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "ProductPurchase")})
	public ProductPurchase postProjectProductPurchase(
			@NotNull @Parameter(hidden = true) @PathParam("projectId") Long
				projectId,
			@NotNull @Parameter(hidden = true) @QueryParam("productId") Long
				productId,
			ProductPurchase productPurchase)
		throws Exception {

		return new ProductPurchase();
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(Company contextCompany) {
		this.contextCompany = contextCompany;
	}

	protected void preparePatch(
		ProductPurchase productPurchase,
		ProductPurchase existingProductPurchase) {
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

	@Context
	protected AcceptLanguage contextAcceptLanguage;

	@Context
	protected Company contextCompany;

	@Context
	protected UriInfo contextUriInfo;

}