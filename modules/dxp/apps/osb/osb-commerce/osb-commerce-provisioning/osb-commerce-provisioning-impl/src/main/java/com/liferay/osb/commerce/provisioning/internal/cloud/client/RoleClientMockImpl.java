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
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;

/**
 * @author Ivica Cardic
 */
public class RoleClientMockImpl implements RoleClient {

	public RoleClientMockImpl(
		CompanyLocalService companyLocalService,
		RoleLocalService roleLocalService) {

		_companyLocalService = companyLocalService;
		_roleLocalService = roleLocalService;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void postUserRole(
		String roleName, long userId, String virtualHostname) {

		try {
			Company company = _companyLocalService.getCompanyByVirtualHost(
				virtualHostname);

			Role role = _roleLocalService.getRole(
				company.getCompanyId(), roleName);

			_roleLocalService.addUserRole(userId, role.getRoleId());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	private final CompanyLocalService _companyLocalService;
	private final RoleLocalService _roleLocalService;

}