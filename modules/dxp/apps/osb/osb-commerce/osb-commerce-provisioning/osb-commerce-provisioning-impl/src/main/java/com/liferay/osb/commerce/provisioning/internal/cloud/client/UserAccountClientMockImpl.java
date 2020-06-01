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

import com.liferay.osb.commerce.provisioning.internal.cloud.client.dto.UserAccount;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Date;

/**
 * @author Ivica Cardic
 */
public class UserAccountClientMockImpl implements UserAccountClient {

	public UserAccountClientMockImpl(
		CompanyLocalService companyLocalService,
		UserLocalService userLocalService) {

		_companyLocalService = companyLocalService;
		_userLocalService = userLocalService;
	}

	@Override
	public void destroy() {
	}

	@Override
	public UserAccount postUserAccount(
		UserAccount userAccount, String virtualHostname) {

		try {
			Company company = _companyLocalService.getCompanyByVirtualHost(
				virtualHostname);

			User user = _userLocalService.addUser(
				0, company.getCompanyId(), true, null, null, true, null,
				userAccount.getEmailAddress(), 0, null, LocaleUtil.getDefault(),
				userAccount.getGivenName(), userAccount.getAdditionalName(),
				userAccount.getFamilyName(), 0, 0, true, 1, 1, 1970,
				userAccount.getJobTitle(), null, null, null, null, false,
				new ServiceContext());

			userAccount.setId(user.getUserId());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}

		return userAccount;
	}

	@Override
	public void updatePasswordManually(
		String password, long userId, String virtualHostname) {

		try {
			_userLocalService.updatePasswordManually(
				userId, password, true, false, new Date());
		}
		catch (PortalException portalException) {
			throw new RuntimeException(portalException);
		}
	}

	private final CompanyLocalService _companyLocalService;
	private final UserLocalService _userLocalService;

}