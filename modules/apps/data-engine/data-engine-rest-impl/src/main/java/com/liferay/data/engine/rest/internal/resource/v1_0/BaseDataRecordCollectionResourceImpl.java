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

package com.liferay.data.engine.rest.internal.resource.v1_0;

import com.liferay.data.engine.rest.dto.v1_0.DataRecordCollection;
import com.liferay.data.engine.rest.dto.v1_0.DataRecordCollectionPermission;
import com.liferay.data.engine.rest.resource.v1_0.DataRecordCollectionResource;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.VulcanBatchEngineTaskItemDelegate;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.ActionUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
@Path("/v1.0")
public abstract class BaseDataRecordCollectionResourceImpl
	implements DataRecordCollectionResource, EntityModelResource,
			   VulcanBatchEngineTaskItemDelegate<DataRecordCollection> {

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/data-engine/v1.0/data-definitions/{dataDefinitionId}/data-record-collections'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "dataDefinitionId"),
			@Parameter(in = ParameterIn.QUERY, name = "keywords"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/data-definitions/{dataDefinitionId}/data-record-collections")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public Page<DataRecordCollection>
			getDataDefinitionDataRecordCollectionsPage(
				@NotNull @Parameter(hidden = true)
				@PathParam("dataDefinitionId") Long dataDefinitionId,
				@Parameter(hidden = true) @QueryParam("keywords") String
					keywords,
				@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/data-engine/v1.0/data-definitions/{dataDefinitionId}/data-record-collections' -d $'{"dataDefinitionId": ___, "dataRecordCollectionKey": ___, "description": ___, "id": ___, "name": ___, "siteId": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {@Parameter(in = ParameterIn.PATH, name = "dataDefinitionId")}
	)
	@Path("/data-definitions/{dataDefinitionId}/data-record-collections")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public DataRecordCollection postDataDefinitionDataRecordCollection(
			@NotNull @Parameter(hidden = true) @PathParam("dataDefinitionId")
				Long dataDefinitionId,
			DataRecordCollection dataRecordCollection)
		throws Exception {

		return new DataRecordCollection();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/data-engine/v1.0/data-definitions/{dataDefinitionId}/data-record-collections/batch'  -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes("application/json")
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "dataDefinitionId"),
			@Parameter(in = ParameterIn.QUERY, name = "callbackURL")
		}
	)
	@Path("/data-definitions/{dataDefinitionId}/data-record-collections/batch")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public Response postDataDefinitionDataRecordCollectionBatch(
			@NotNull @Parameter(hidden = true) @PathParam("dataDefinitionId")
				Long dataDefinitionId,
			@Parameter(hidden = true) @QueryParam("callbackURL") String
				callbackURL,
			Object object)
		throws Exception {

		vulcanBatchEngineImportTaskResource.setContextAcceptLanguage(
			contextAcceptLanguage);
		vulcanBatchEngineImportTaskResource.setContextCompany(contextCompany);
		vulcanBatchEngineImportTaskResource.setContextHttpServletRequest(
			contextHttpServletRequest);
		vulcanBatchEngineImportTaskResource.setContextUriInfo(contextUriInfo);
		vulcanBatchEngineImportTaskResource.setContextUser(contextUser);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.entity(
			vulcanBatchEngineImportTaskResource.postImportTask(
				DataRecordCollection.class.getName(), callbackURL, null, object)
		).build();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/data-engine/v1.0/data-record-collections/{dataRecordCollectionId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@DELETE
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "dataRecordCollectionId")
		}
	)
	@Path("/data-record-collections/{dataRecordCollectionId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public void deleteDataRecordCollection(
			@NotNull @Parameter(hidden = true)
			@PathParam("dataRecordCollectionId") Long dataRecordCollectionId)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'DELETE' 'http://localhost:8080/o/data-engine/v1.0/data-record-collections/batch'  -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes("application/json")
	@DELETE
	@Parameters(
		value = {@Parameter(in = ParameterIn.QUERY, name = "callbackURL")}
	)
	@Path("/data-record-collections/batch")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public Response deleteDataRecordCollectionBatch(
			@Parameter(hidden = true) @QueryParam("callbackURL") String
				callbackURL,
			Object object)
		throws Exception {

		vulcanBatchEngineImportTaskResource.setContextAcceptLanguage(
			contextAcceptLanguage);
		vulcanBatchEngineImportTaskResource.setContextCompany(contextCompany);
		vulcanBatchEngineImportTaskResource.setContextHttpServletRequest(
			contextHttpServletRequest);
		vulcanBatchEngineImportTaskResource.setContextUriInfo(contextUriInfo);
		vulcanBatchEngineImportTaskResource.setContextUser(contextUser);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.entity(
			vulcanBatchEngineImportTaskResource.deleteImportTask(
				DataRecordCollection.class.getName(), callbackURL, object)
		).build();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/data-engine/v1.0/data-record-collections/{dataRecordCollectionId}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "dataRecordCollectionId")
		}
	)
	@Path("/data-record-collections/{dataRecordCollectionId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public DataRecordCollection getDataRecordCollection(
			@NotNull @Parameter(hidden = true)
			@PathParam("dataRecordCollectionId") Long dataRecordCollectionId)
		throws Exception {

		return new DataRecordCollection();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/data-engine/v1.0/data-record-collections/{dataRecordCollectionId}' -d $'{"dataDefinitionId": ___, "dataRecordCollectionKey": ___, "description": ___, "id": ___, "name": ___, "siteId": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@PUT
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "dataRecordCollectionId")
		}
	)
	@Path("/data-record-collections/{dataRecordCollectionId}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public DataRecordCollection putDataRecordCollection(
			@NotNull @Parameter(hidden = true)
			@PathParam("dataRecordCollectionId") Long dataRecordCollectionId,
			DataRecordCollection dataRecordCollection)
		throws Exception {

		return new DataRecordCollection();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'PUT' 'http://localhost:8080/o/data-engine/v1.0/data-record-collections/batch'  -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes("application/json")
	@PUT
	@Parameters(
		value = {@Parameter(in = ParameterIn.QUERY, name = "callbackURL")}
	)
	@Path("/data-record-collections/batch")
	@Produces("application/json")
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public Response putDataRecordCollectionBatch(
			@Parameter(hidden = true) @QueryParam("callbackURL") String
				callbackURL,
			Object object)
		throws Exception {

		vulcanBatchEngineImportTaskResource.setContextAcceptLanguage(
			contextAcceptLanguage);
		vulcanBatchEngineImportTaskResource.setContextCompany(contextCompany);
		vulcanBatchEngineImportTaskResource.setContextHttpServletRequest(
			contextHttpServletRequest);
		vulcanBatchEngineImportTaskResource.setContextUriInfo(contextUriInfo);
		vulcanBatchEngineImportTaskResource.setContextUser(contextUser);

		Response.ResponseBuilder responseBuilder = Response.accepted();

		return responseBuilder.entity(
			vulcanBatchEngineImportTaskResource.putImportTask(
				DataRecordCollection.class.getName(), callbackURL, object)
		).build();
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/data-engine/v1.0/data-record-collections/{dataRecordCollectionId}/data-record-collection-permissions' -d $'{"addDataRecord": ___, "addDataRecordCollection": ___, "definePermissions": ___, "delete": ___, "deleteDataRecord": ___, "exportDataRecord": ___, "roleNames": ___, "update": ___, "updateDataRecord": ___, "view": ___, "viewDataRecord": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "dataRecordCollectionId"),
			@Parameter(in = ParameterIn.QUERY, name = "operation")
		}
	)
	@Path(
		"/data-record-collections/{dataRecordCollectionId}/data-record-collection-permissions"
	)
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public void postDataRecordCollectionDataRecordCollectionPermission(
			@NotNull @Parameter(hidden = true)
			@PathParam("dataRecordCollectionId") Long dataRecordCollectionId,
			@NotNull @Parameter(hidden = true) @QueryParam("operation") String
				operation,
			DataRecordCollectionPermission dataRecordCollectionPermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'POST' 'http://localhost:8080/o/data-engine/v1.0/sites/{siteId}/data-record-collection-permissions' -d $'{"addDataRecord": ___, "addDataRecordCollection": ___, "definePermissions": ___, "delete": ___, "deleteDataRecord": ___, "exportDataRecord": ___, "roleNames": ___, "update": ___, "updateDataRecord": ___, "view": ___, "viewDataRecord": ___}' --header 'Content-Type: application/json' -u 'test@liferay.com:test'
	 */
	@Override
	@Consumes({"application/json", "application/xml"})
	@POST
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "siteId"),
			@Parameter(in = ParameterIn.QUERY, name = "operation")
		}
	)
	@Path("/sites/{siteId}/data-record-collection-permissions")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public void postSiteDataRecordCollectionPermission(
			@NotNull @Parameter(hidden = true) @PathParam("siteId") Long siteId,
			@NotNull @Parameter(hidden = true) @QueryParam("operation") String
				operation,
			DataRecordCollectionPermission dataRecordCollectionPermission)
		throws Exception {
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/data-engine/v1.0/sites/{siteId}/data-record-collections'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "siteId"),
			@Parameter(in = ParameterIn.QUERY, name = "keywords"),
			@Parameter(in = ParameterIn.QUERY, name = "page"),
			@Parameter(in = ParameterIn.QUERY, name = "pageSize")
		}
	)
	@Path("/sites/{siteId}/data-record-collections")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public Page<DataRecordCollection> getSiteDataRecordCollectionsPage(
			@NotNull @Parameter(hidden = true) @PathParam("siteId") Long siteId,
			@Parameter(hidden = true) @QueryParam("keywords") String keywords,
			@Context Pagination pagination)
		throws Exception {

		return Page.of(Collections.emptyList());
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -X 'GET' 'http://localhost:8080/o/data-engine/v1.0/sites/{siteId}/data-record-collections/{dataRecordCollectionKey}'  -u 'test@liferay.com:test'
	 */
	@Override
	@GET
	@Parameters(
		value = {
			@Parameter(in = ParameterIn.PATH, name = "siteId"),
			@Parameter(in = ParameterIn.PATH, name = "dataRecordCollectionKey")
		}
	)
	@Path("/sites/{siteId}/data-record-collections/{dataRecordCollectionKey}")
	@Produces({"application/json", "application/xml"})
	@Tags(value = {@Tag(name = "DataRecordCollection")})
	public DataRecordCollection getSiteDataRecordCollection(
			@NotNull @Parameter(hidden = true) @PathParam("siteId") Long siteId,
			@NotNull @Parameter(hidden = true)
			@PathParam("dataRecordCollectionKey") String
				dataRecordCollectionKey)
		throws Exception {

		return new DataRecordCollection();
	}

	@Override
	@SuppressWarnings("PMD.UnusedLocalVariable")
	public void create(
			java.util.Collection<DataRecordCollection> dataRecordCollections,
			Map<String, Serializable> parameters)
		throws Exception {

		for (DataRecordCollection dataRecordCollection :
				dataRecordCollections) {

			postDataDefinitionDataRecordCollection(
				Long.valueOf((String)parameters.get("dataDefinitionId")),
				dataRecordCollection);
		}
	}

	@Override
	public void delete(
			java.util.Collection<DataRecordCollection> dataRecordCollections,
			Map<String, Serializable> parameters)
		throws Exception {

		for (DataRecordCollection dataRecordCollection :
				dataRecordCollections) {

			deleteDataRecordCollection(dataRecordCollection.getId());
		}
	}

	@Override
	public EntityModel getEntityModel(Map<String, List<String>> multivaluedMap)
		throws Exception {

		return getEntityModel(
			new MultivaluedHashMap<String, Object>(multivaluedMap));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return null;
	}

	@Override
	public Page<DataRecordCollection> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		return getSiteDataRecordCollectionsPage(
			(Long)parameters.get("siteId"), (String)parameters.get("keywords"),
			pagination);
	}

	@Override
	public void setLanguageId(String languageId) {
		this.contextAcceptLanguage = new AcceptLanguage() {

			@Override
			public List<Locale> getLocales() {
				return null;
			}

			@Override
			public String getPreferredLanguageId() {
				return languageId;
			}

			@Override
			public Locale getPreferredLocale() {
				return LocaleUtil.fromLanguageId(languageId);
			}

		};
	}

	@Override
	public void update(
			java.util.Collection<DataRecordCollection> dataRecordCollections,
			Map<String, Serializable> parameters)
		throws Exception {

		for (DataRecordCollection dataRecordCollection :
				dataRecordCollections) {

			putDataRecordCollection(
				dataRecordCollection.getId() != null ?
				dataRecordCollection.getId() :
				(Long)parameters.get("dataRecordCollectionId"),
				dataRecordCollection);
		}
	}

	public void setContextAcceptLanguage(AcceptLanguage contextAcceptLanguage) {
		this.contextAcceptLanguage = contextAcceptLanguage;
	}

	public void setContextCompany(
		com.liferay.portal.kernel.model.Company contextCompany) {

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

	public void setContextUser(
		com.liferay.portal.kernel.model.User contextUser) {

		this.contextUser = contextUser;
	}

	protected Map<String, String> addAction(
		String actionName, GroupedModel groupedModel, String methodName) {

		return ActionUtil.addAction(
			actionName, getClass(), groupedModel, methodName,
			contextScopeChecker, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, Long id, String methodName, Long ownerId,
		String permissionName, Long siteId) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			ownerId, permissionName, siteId, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, Long id, String methodName,
		ModelResourcePermission modelResourcePermission) {

		return ActionUtil.addAction(
			actionName, getClass(), id, methodName, contextScopeChecker,
			modelResourcePermission, contextUriInfo);
	}

	protected Map<String, String> addAction(
		String actionName, String methodName, String permissionName,
		Long siteId) {

		return addAction(
			actionName, siteId, methodName, null, permissionName, siteId);
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
	protected com.liferay.portal.kernel.model.Company contextCompany;
	protected HttpServletRequest contextHttpServletRequest;
	protected HttpServletResponse contextHttpServletResponse;
	protected Object contextScopeChecker;
	protected UriInfo contextUriInfo;
	protected com.liferay.portal.kernel.model.User contextUser;
	protected GroupLocalService groupLocalService;
	protected ResourceActionLocalService resourceActionLocalService;
	protected ResourcePermissionLocalService resourcePermissionLocalService;
	protected RoleLocalService roleLocalService;
	protected VulcanBatchEngineImportTaskResource
		vulcanBatchEngineImportTaskResource;

}