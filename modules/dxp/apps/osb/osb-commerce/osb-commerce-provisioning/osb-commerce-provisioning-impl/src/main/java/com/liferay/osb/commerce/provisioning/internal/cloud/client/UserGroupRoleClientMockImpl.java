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

package com.liferay.osb.commerce.provisioning.internal.cloud.client;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;

/**
 * @author Ivica Cardic
 */
public class UserGroupRoleClientMockImpl implements UserGroupRoleClient {

	public UserGroupRoleClientMockImpl(
		CompanyLocalService companyLocalService,
		GroupLocalService groupLocalService, RoleLocalService roleLocalService,
		UserGroupRoleLocalService userGroupRoleLocalService) {

		_companyLocalService = companyLocalService;
		_groupLocalService = groupLocalService;
		_roleLocalService = roleLocalService;
		_userGroupRoleLocalService = userGroupRoleLocalService;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void postUserGroupRole(
		String friendlyURL, String roleName, long userId,
		String virtualHostname) {

		try {
			Company company = _companyLocalService.getCompanyByVirtualHost(
				virtualHostname);

			Role role = _roleLocalService.getRole(
				company.getCompanyId(), roleName);

			Group group = _groupLocalService.fetchFriendlyURLGroup(
				company.getCompanyId(), friendlyURL);

			_userGroupRoleLocalService.addUserGroupRoles(
				userId, group.getGroupId(), new long[] {role.getRoleId()});
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	private final CompanyLocalService _companyLocalService;
	private final GroupLocalService _groupLocalService;
	private final RoleLocalService _roleLocalService;
	private final UserGroupRoleLocalService _userGroupRoleLocalService;

}