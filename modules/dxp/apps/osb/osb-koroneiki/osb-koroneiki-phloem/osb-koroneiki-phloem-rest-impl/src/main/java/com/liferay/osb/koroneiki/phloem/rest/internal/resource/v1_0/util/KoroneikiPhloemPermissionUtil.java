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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util;

import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.service.permission.ModelPermissionsFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

/**
 * @author Kyle Bischof
 */
public class KoroneikiPhloemPermissionUtil {

	public static List<Role> getRoles(
			Company company, RoleLocalService roleLocalService,
			String[] roleNames)
		throws PortalException {

		List<String> invalidRoleNames = new ArrayList<>();
		List<Role> roles = new ArrayList<>();

		for (String roleName : roleNames) {
			try {
				Role role = roleLocalService.getRole(
					company.getCompanyId(), roleName);

				roles.add(role);
			}
			catch (NoSuchRoleException nsre) {
				if (_log.isDebugEnabled()) {
					_log.debug(roleName, nsre);
				}

				invalidRoleNames.add(roleName);
			}
		}

		if (!invalidRoleNames.isEmpty()) {
			throw new ValidationException(
				"Invalid roles: " + ArrayUtil.toStringArray(invalidRoleNames));
		}

		return roles;
	}

	public static void persistModelPermission(
			List<String> actionIds, Company company, long modelId,
			String operation, String resourceName,
			ResourcePermissionLocalService resourcePermissionLocalService,
			RoleLocalService roleLocalService, String[] roleNames, long groupId)
		throws Exception {

		if (StringUtil.equalsIgnoreCase("save", operation)) {
			ModelPermissions modelPermissions =
				ModelPermissionsFactory.createWithDefaultPermissions(
					resourceName);

			for (String roleName : roleNames) {
				modelPermissions.addRolePermissions(
					roleName, ArrayUtil.toStringArray(actionIds));
			}

			resourcePermissionLocalService.addModelResourcePermissions(
				company.getCompanyId(), groupId,
				PrincipalThreadLocal.getUserId(), resourceName,
				String.valueOf(modelId), modelPermissions);
		}
		else {
			for (Role role : getRoles(company, roleLocalService, roleNames)) {
				for (String actionId : actionIds) {
					resourcePermissionLocalService.removeResourcePermission(
						company.getCompanyId(), resourceName,
						ResourceConstants.SCOPE_INDIVIDUAL,
						String.valueOf(modelId), role.getRoleId(), actionId);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KoroneikiPhloemPermissionUtil.class);

}