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

package com.liferay.osb.koroneiki.phytohormone.internal.permission;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.permission.EntitlementDefinitionPermission;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	service = {EntitlementDefinitionPermission.class, ModelPermission.class}
)
public class EntitlementDefinitionPermissionImpl
	implements EntitlementDefinitionPermission, ModelPermission {

	public static final String RESOURCE_NAME_ENTITLEMENT_DEFINITIONS =
		"com.liferay.osb.koroneiki.phytohormone.entitlement.definitions";

	@Override
	public void check(
			PermissionChecker permissionChecker,
			EntitlementDefinition entitlementDefinition, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entitlementDefinition, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, EntitlementDefinition.class.getName(),
				entitlementDefinition.getEntitlementDefinitionId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long entitlementDefinitionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entitlementDefinitionId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, EntitlementDefinition.class.getName(),
				entitlementDefinitionId, actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME_ENTITLEMENT_DEFINITIONS, 0,
				actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			EntitlementDefinition entitlementDefinition, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				entitlementDefinition.getCompanyId(),
				EntitlementDefinition.class.getName(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, EntitlementDefinition.class.getName(),
			entitlementDefinition.getEntitlementDefinitionId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long entitlementDefinitionId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long[] entitlementDefinitionIds, String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(entitlementDefinitionIds)) {
			return false;
		}

		for (long entitlementDefinitionId : entitlementDefinitionIds) {
			if (!contains(
					permissionChecker, entitlementDefinitionId, actionId)) {

				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(
			0, RESOURCE_NAME_ENTITLEMENT_DEFINITIONS,
			RESOURCE_NAME_ENTITLEMENT_DEFINITIONS, actionId);
	}

	@Override
	public String getClassName() {
		return EntitlementDefinition.class.getName();
	}

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

}