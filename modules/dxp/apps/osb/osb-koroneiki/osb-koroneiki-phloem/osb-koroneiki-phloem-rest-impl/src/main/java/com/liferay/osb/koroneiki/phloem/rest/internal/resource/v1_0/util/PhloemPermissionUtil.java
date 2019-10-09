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
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.service.permission.ModelPermissionsFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PhloemPermissionUtil.class)
public class PhloemPermissionUtil {

	public void persistModelPermission(
			String operation, long companyId, long userId, String resourceName,
			long resourcePrimKey, String[] roleNames, List<String> actionIds)
		throws Exception {

		if (StringUtil.equalsIgnoreCase("save", operation)) {
			_updateModelPermission(
				companyId, userId, resourceName, resourcePrimKey, roleNames,
				actionIds);
		}
		else {
			_deleteModelPermission(
				companyId, resourceName, resourcePrimKey, roleNames, actionIds);
		}
	}

	private void _deleteModelPermission(
			long companyId, String resourceName, long resourcePrimKey,
			String[] roleNames, List<String> actionIds)
		throws Exception {

		for (Role role : _getRoles(companyId, roleNames)) {
			for (String actionId : actionIds) {
				_resourcePermissionLocalService.removeResourcePermission(
					companyId, resourceName, ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(resourcePrimKey), role.getRoleId(),
					actionId);
			}
		}
	}

	private List<Role> _getRoles(long companyId, String[] roleNames)
		throws PortalException {

		List<String> invalidRoleNames = new ArrayList<>();
		List<Role> roles = new ArrayList<>();

		for (String roleName : roleNames) {
			try {
				Role role = _roleLocalService.getRole(companyId, roleName);

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

	private void _updateModelPermission(
			long companyId, long userId, String resourceName,
			long resourcePrimKey, String[] roleNames, List<String> actionIds)
		throws Exception {

		ModelPermissions modelPermissions =
			ModelPermissionsFactory.createWithDefaultPermissions(resourceName);

		for (String roleName : roleNames) {
			modelPermissions.addRolePermissions(
				roleName, ArrayUtil.toStringArray(actionIds));
		}

		_resourcePermissionLocalService.addModelResourcePermissions(
			companyId, 0, userId, resourceName, String.valueOf(resourcePrimKey),
			modelPermissions);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PhloemPermissionUtil.class);

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}