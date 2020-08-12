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

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.base.EntitlementLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.phytohormone.model.Entitlement",
	service = AopService.class
)
public class EntitlementLocalServiceImpl
	extends EntitlementLocalServiceBaseImpl {

	public Entitlement addEntitlement(
			long userId, long entitlementDefinitionId, long classNameId,
			long classPK)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		EntitlementDefinition entitlementDefinition =
			entitlementDefinitionPersistence.findByPrimaryKey(
				entitlementDefinitionId);

		long entitlementId = counterLocalService.increment();

		Entitlement entitlement = entitlementPersistence.create(entitlementId);

		entitlement.setCompanyId(user.getCompanyId());
		entitlement.setUserId(user.getUserId());
		entitlement.setEntitlementDefinitionId(entitlementDefinitionId);
		entitlement.setClassNameId(classNameId);
		entitlement.setClassPK(classPK);
		entitlement.setName(entitlementDefinition.getName());

		return entitlementPersistence.update(entitlement);
	}

	public void deleteEntitlements(long classNameId, long classPK) {
		entitlementPersistence.removeByC_C(classNameId, classPK);
	}

	public List<Entitlement> getEntitlements(
		long classNameId, long classPK, int start, int end) {

		return entitlementPersistence.findByC_C(
			classNameId, classPK, start, end);
	}

	public List<Entitlement> getEntitlements(
		String className, long classPK, int start, int end) {

		return entitlementPersistence.findByC_C(
			classNameLocalService.getClassNameId(className), classPK, start,
			end);
	}

}