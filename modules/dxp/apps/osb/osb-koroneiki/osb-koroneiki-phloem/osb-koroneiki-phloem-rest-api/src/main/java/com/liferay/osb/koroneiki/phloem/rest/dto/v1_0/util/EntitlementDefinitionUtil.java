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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.EntitlementDefinition;

/**
 * @author Amos Fong
 */
public class EntitlementDefinitionUtil {

	public static EntitlementDefinition toEntitlementDefinition(
			com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				entitlementDefinition)
		throws Exception {

		return new EntitlementDefinition() {
			{
				dateCreated = entitlementDefinition.getCreateDate();
				dateModified = entitlementDefinition.getModifiedDate();
				definition = entitlementDefinition.getDefinition();
				description = entitlementDefinition.getDescription();
				key = entitlementDefinition.getEntitlementDefinitionKey();
				name = entitlementDefinition.getName();
				status = Status.create(entitlementDefinition.getStatusLabel());
			}
		};
	}

}