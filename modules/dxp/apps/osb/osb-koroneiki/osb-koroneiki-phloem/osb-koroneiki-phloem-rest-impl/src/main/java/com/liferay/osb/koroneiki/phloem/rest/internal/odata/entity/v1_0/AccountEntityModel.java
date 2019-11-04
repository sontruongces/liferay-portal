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
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.IntegerEntityField;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amos Fong
 */
public class AccountEntityModel implements EntityModel {

	public static final String NAME = "Account";

	public AccountEntityModel() {
		_entityFieldsMap = Stream.of(
			new EntityField(
				"code", EntityField.Type.STRING,
				locale -> Field.getSortableFieldName("code_String"),
				locale -> "code", String::valueOf),
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
					"entitlements", locale -> "entitlements")),
			new CollectionEntityField(
				new StringEntityField(
					"externalLinkDomains", locale -> "externalLinkDomains")),
			new CollectionEntityField(
				new StringEntityField(
					"externalLinkEntityIds",
					locale -> "externalLinkEntityIds")),
			new CollectionEntityField(
				new StringEntityField(
					"externalLinkEntityNames",
					locale -> "externalLinkEntityNames")),
			new StringEntityField("name", locale -> "name"),
			new IntegerEntityField("status", locale -> "status"),
			new StringEntityField(
				"parentAccountKey", locale -> "parentAccountKey"),
			new CollectionEntityField(
				new StringEntityField(
					"productKeys", locale -> "productEntryKeys"))
		).collect(
			Collectors.toMap(EntityField::getName, Function.identity())
		);
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