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

package com.liferay.object.rest.internal.vulcan.openapi.contributor;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.rest.internal.helper.ObjectHelper;
import com.liferay.object.rest.internal.vulcan.openapi.contributor.util.OpenAPIContributorUtil;
import com.liferay.object.rest.openapi.v1_0.ObjectEntryOpenAPIResource;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.object.system.JaxRsApplicationDescriptor;
import com.liferay.object.system.SystemObjectDefinitionMetadata;
import com.liferay.object.system.SystemObjectDefinitionMetadataRegistry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.vulcan.openapi.contributor.OpenAPIContributor;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.net.URI;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luis Miguel Barcos
 */
@Component(service = OpenAPIContributor.class)
public class RelatedObjectEntryOpenAPIContributor
	extends BaseOpenAPIContributor {

	@Activate
	public void activate() {
		init(_dtoConverterRegistry, _systemObjectDefinitionMetadataRegistry);
	}

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private SystemObjectDefinitionMetadataRegistry
		_systemObjectDefinitionMetadataRegistry;

	@Override
	public void contribute(OpenAPI openAPI, UriInfo uriInfo) throws Exception {
		if (!GetterUtil.getBoolean(PropsUtil.get("feature.flag.LPS-153324")) ||
			(uriInfo == null)) {

			return;
		}

		Map<ObjectDefinition, SystemObjectDefinitionMetadata>
			systemObjectDefinitionMetadataMap = new HashMap<>();

		for (ObjectDefinition systemObjectDefinition :
				_objectDefinitionLocalService.getSystemObjectDefinitions()) {

			SystemObjectDefinitionMetadata systemObjectDefinitionMetadata =
				_systemObjectDefinitionMetadataRegistry.
					getSystemObjectDefinitionMetadata(
						systemObjectDefinition.getName());

			if (systemObjectDefinitionMetadata == null) {
				continue;
			}

			URI uri = uriInfo.getBaseUri();

			String path = uri.getPath();

			if (path.contains(
					_getSystemObjectRESTBasePath(
						systemObjectDefinitionMetadata))) {

				systemObjectDefinitionMetadataMap.put(
					systemObjectDefinition, systemObjectDefinitionMetadata);
			}
		}

		for (Map.Entry<ObjectDefinition, SystemObjectDefinitionMetadata> entry :
				systemObjectDefinitionMetadataMap.entrySet()) {

			ObjectDefinition systemObjectDefinition = entry.getKey();

			for (ObjectRelationship systemObjectRelationship :
					_objectRelationshipLocalService.getObjectRelationships(
						systemObjectDefinition.getObjectDefinitionId())) {

				_contribute(
					openAPI, systemObjectDefinition, entry.getValue(),
					systemObjectRelationship, uriInfo);
			}
		}
	}

	private void _contribute(
			OpenAPI openAPI, ObjectDefinition systemObjectDefinition,
			SystemObjectDefinitionMetadata systemObjectDefinitionMetadata,
			ObjectRelationship systemObjectRelationship, UriInfo uriInfo)
		throws Exception {

		ObjectDefinition relatedObjectDefinition = _getRelatedObjectDefinition(
			systemObjectDefinition, systemObjectRelationship);

		OpenAPI relatedOpenAPI = OpenAPIContributorUtil.getObjectEntryOpenAPI(
			relatedObjectDefinition, _objectEntryOpenAPIResource);

		String relatedSchemaName = _objectHelper.getSchemaName(
			relatedObjectDefinition);

		OpenAPIContributorUtil.copySchemas(
			relatedSchemaName, relatedOpenAPI,
			relatedObjectDefinition.isSystem(), openAPI);

		String schemaName = _objectHelper.getSchemaName(systemObjectDefinition);

		String name = StringBundler.concat(
			StringPool.SLASH, _getJaxRsVersion(uriInfo), StringPool.SLASH,
			_getSystemObjectBasePath(systemObjectDefinitionMetadata),
			StringPool.SLASH, _getIdParameterTemplate(schemaName),
			StringPool.SLASH, systemObjectRelationship.getName());

		Paths paths = openAPI.getPaths();

		paths.addPathItem(
			name,
			new PathItem() {
				{
					get(
						_getGetOperation(
							systemObjectRelationship, relatedSchemaName,
							schemaName));
				}
			});
		paths.addPathItem(
			StringBundler.concat(
				name, StringPool.SLASH,
				_getIdParameterTemplate(
					relatedObjectDefinition.getShortName())),
			new PathItem() {
				{
					delete(
						_getDeleteOperation(
							systemObjectRelationship, relatedSchemaName,
							schemaName));
					put(
						_getPutOperation(
							systemObjectRelationship, relatedSchemaName,
							schemaName));
				}
			});
	}

	private Content _getContent(String schemaName) {
		Content content = new Content();

		MediaType mediaType = new MediaType();

		if (schemaName != null) {
			Schema schema = new Schema();

			schema.set$ref(schemaName);

			mediaType.setSchema(schema);
		}

		content.addMediaType("application/json", mediaType);
		content.addMediaType("application/xml", mediaType);

		return content;
	}

	private Operation _getDeleteOperation(
		ObjectRelationship objectRelationship, String relatedSchemaName,
		String schemaName) {

		return new Operation() {
			{
				operationId(
					_getOperationId(
						"delete", objectRelationship.getName(), schemaName));
				parameters(
					Arrays.asList(
						new Parameter() {
							{
								in("path");
								name(_getIdParameterName(schemaName));
								required(true);
							}
						},
						new Parameter() {
							{
								in("path");
								name(_getIdParameterName(relatedSchemaName));
								required(true);
							}
						}));
				responses(
					new ApiResponses() {
						{
							setDefault(
								new ApiResponse() {
									{
										setContent(_getContent(null));
									}
								});
						}
					});
				tags(Collections.singletonList(schemaName));
			}
		};
	}

	private Operation _getGetOperation(
		ObjectRelationship objectRelationship, String relatedSchemaName,
		String schemaName) {

		String parameterName = _getIdParameterName(schemaName);

		return new Operation() {
			{
				operationId(
					_getOperationId(
						"get", objectRelationship.getName(), schemaName));
				parameters(
					Collections.singletonList(
						new Parameter() {
							{
								in("path");
								name(parameterName);
								required(true);
							}
						}));
				responses(
					new ApiResponses() {
						{
							setDefault(
								new ApiResponse() {
									{
										setContent(
											_getContent(
												OpenAPIContributorUtil.
													getPageSchemaName(
														relatedSchemaName)));
									}
								});
						}
					});
				tags(Collections.singletonList(schemaName));
			}
		};
	}

	private String _getIdParameterName(String name) {
		return StringUtil.lowerCaseFirstLetter(name) + "Id";
	}

	private String _getIdParameterTemplate(String name) {
		return StringPool.OPEN_CURLY_BRACE + _getIdParameterName(name) +
			StringPool.CLOSE_CURLY_BRACE;
	}

	private String _getJaxRsVersion(UriInfo uriInfo) {
		return StringUtil.extractFirst(uriInfo.getPath(), StringPool.SLASH);
	}

	private String _getOperationId(
		String method, String objectRelationshipName,
		String systemObjectDefinitionName) {

		String sufix = "";

		if (StringUtil.equals(method, "get")) {
			sufix = "Page";
		}

		return StringBundler.concat(
			method, systemObjectDefinitionName,
			StringUtil.upperCaseFirstLetter(objectRelationshipName), sufix);
	}

	private Operation _getPutOperation(
		ObjectRelationship objectRelationship, String relatedSchemaName,
		String schemaName) {

		return new Operation() {
			{
				operationId(
					_getOperationId(
						"put", objectRelationship.getName(), schemaName));
				parameters(
					Arrays.asList(
						new Parameter() {
							{
								in("path");
								name(_getIdParameterName(schemaName));
								required(true);
							}
						},
						new Parameter() {
							{
								in("path");
								name(_getIdParameterName(relatedSchemaName));
								required(true);
							}
						}));
				responses(
					new ApiResponses() {
						{
							setDefault(
								new ApiResponse() {
									{
										setContent(
											_getContent(relatedSchemaName));
									}
								});
						}
					});
				tags(Collections.singletonList(schemaName));
			}
		};
	}

	private ObjectDefinition _getRelatedObjectDefinition(
			ObjectDefinition systemObjectDefinition,
			ObjectRelationship objectRelationship)
		throws Exception {

		long objectDefinitionId1 = objectRelationship.getObjectDefinitionId1();

		if (objectDefinitionId1 !=
				systemObjectDefinition.getObjectDefinitionId()) {

			return _objectDefinitionLocalService.getObjectDefinition(
				objectRelationship.getObjectDefinitionId1());
		}

		return _objectDefinitionLocalService.getObjectDefinition(
			objectRelationship.getObjectDefinitionId2());
	}

	private String _getSystemObjectBasePath(
		SystemObjectDefinitionMetadata systemObjectDefinitionMetadata) {

		JaxRsApplicationDescriptor jaxRsApplicationDescriptor =
			systemObjectDefinitionMetadata.getJaxRsApplicationDescriptor();

		return jaxRsApplicationDescriptor.getPath();
	}

	private String _getSystemObjectRESTBasePath(
		SystemObjectDefinitionMetadata systemObjectDefinitionMetadata) {

		JaxRsApplicationDescriptor jaxRsApplicationDescriptor =
			systemObjectDefinitionMetadata.getJaxRsApplicationDescriptor();

		return jaxRsApplicationDescriptor.getApplicationPath();
	}

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryOpenAPIResource _objectEntryOpenAPIResource;

	@Reference
	private ObjectHelper _objectHelper;

	@Reference
	private ObjectRelationshipLocalService _objectRelationshipLocalService;

	@Reference
	private SystemObjectDefinitionMetadataRegistry
		_systemObjectDefinitionMetadataRegistry;

}