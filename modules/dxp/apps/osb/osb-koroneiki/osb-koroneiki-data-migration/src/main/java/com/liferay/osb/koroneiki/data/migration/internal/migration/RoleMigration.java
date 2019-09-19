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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = RoleMigration.class)
public class RoleMigration {

	public void migrate(long userId) throws Exception {
		User user = _userLocalService.getUser(userId);

		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			12324522, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			33118240, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			33118252, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			33118264, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			90852750, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			90852751, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			106868290, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			112936638, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			112936646, false, false, false);
		_resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, ContactRole.class.getName(),
			112936656, false, false, false);
	}

	@Reference
	private ResourceLocalService _resourceLocalService;

	@Reference
	private UserLocalService _userLocalService;

}