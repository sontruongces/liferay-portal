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

package com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.CollectionEntityField;
import com.liferay.portal.odata.entity.DateTimeEntityField;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IntegerEntityField;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewEntityModel implements EntityModel {

	public static final String NAME = "ProductPurchaseView";

	public ProductPurchaseViewEntityModel(List<String> productFieldNames) {
		_entityFieldsMap = Stream.of(
			new StringEntityField("accountKey", locale -> "accountKey"),
			new CollectionEntityField(
				new StringEntityField(
					"contactOktaIdContactRoleKeys",
					locale -> "contactOktaIdContactRoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"contactOktaIds", locale -> "contactOktaIds")),
			new CollectionEntityField(
				new StringEntityField(
					"contactUuidContactRoleKeys",
					locale -> "contactUuidContactRoleKeys")),
			new CollectionEntityField(
				new StringEntityField(
					"contactUuids", locale -> "contactUuids")),
			new CollectionEntityField(
				new StringEntityField(
					"customerContactOktaIds",
					locale -> "customerContactOktaIds")),
			new CollectionEntityField(
				new StringEntityField(
					"customerContactUuids", locale -> "customerContactUuids")),
			new StringEntityField("name", locale -> "name"),
			new StringEntityField("perpetual", locale -> "perpetual"),
			new StringEntityField("productKey", locale -> "productKey"),
			new IntegerEntityField(
				"provisionedCount",
				locale -> Field.getSortableFieldName("provisionedCount")),
			new IntegerEntityField(
				"purchasedCount",
				locale -> Field.getSortableFieldName("purchasedCount")),
			new StringEntityField("state", locale -> "state"),
			new StringEntityField("status", locale -> "status"),
			new DateTimeEntityField(
				"supportLifeEndDate",
				locale -> Field.getSortableFieldName("supportLifeEndDate"),
				locale -> "supportLifeEndDate"),
			new DateTimeEntityField(
				"supportLifeStartDate",
				locale -> Field.getSortableFieldName("supportLifeStartDate"),
				locale -> "supportLifeStartDate"),
			new CollectionEntityField(
				new StringEntityField(
					"workerContactOktaIds", locale -> "workerContactOktaIds")),
			new CollectionEntityField(
				new StringEntityField(
					"workerContactUuids", locale -> "workerContactUuids"))
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);

		for (String productFieldName : productFieldNames) {
			String name = "property_" + productFieldName;

			_entityFieldsMap.put(
				name,
				new StringEntityField(
					name,
					locale -> Field.getSortableFieldName(name + "_String")));
		}
	}

	@Override
	public Map<String, EntityField> getEntityFieldsMap() {
		return _entityFieldsMap;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private final Map<String, EntityField> _entityFieldsMap;

}