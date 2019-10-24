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

package com.liferay.osb.koroneiki.phytohormone.service.impl;

import com.liferay.osb.koroneiki.phytohormone.exception.EntitlementDefinitionNameException;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.base.EntitlementDefinitionLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition",
	service = AopService.class
)
public class EntitlementDefinitionLocalServiceImpl
	extends EntitlementDefinitionLocalServiceBaseImpl {

	public EntitlementDefinition addEntitlementDefinition(
			long userId, long classNameId, String name, String description,
			String definition, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(classNameId, name);

		long entitlementDefinitionId = counterLocalService.increment();

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.create(entitlementDefinitionId);

		entitlementDefinition.setCompanyId(user.getUserId());
		entitlementDefinition.setUserId(userId);
		entitlementDefinition.setClassNameId(classNameId);
		entitlementDefinition.setName(name);
		entitlementDefinition.setDescription(description);
		entitlementDefinition.setDefinition(definition);
		entitlementDefinition.setStatus(status);

		return entitlementDefinitionPersistence.update(entitlementDefinition);
	}

	public List<EntitlementDefinition> getEntitlementDefinitions(
		long classNameId, int status) {

		return entitlementDefinitionPersistence.findByC_S(classNameId, status);
	}

	public EntitlementDefinition updateEntitlementDefinition(
			long entitlementDefinitionId, String description, String definition,
			int status)
		throws PortalException {

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.findByPrimaryKey(
				entitlementDefinitionId);

		entitlementDefinition.setDescription(description);
		entitlementDefinition.setDefinition(definition);
		entitlementDefinition.setStatus(status);

		return entitlementDefinitionPersistence.update(entitlementDefinition);
	}

	protected void validate(long classNameId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new EntitlementDefinitionNameException();
		}

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.fetchByC_N(classNameId, name);

		if (entitlementDefinition != null) {
			throw new EntitlementDefinitionNameException.MustNotBeDuplicate(
				classNameId, name);
		}
	}

}