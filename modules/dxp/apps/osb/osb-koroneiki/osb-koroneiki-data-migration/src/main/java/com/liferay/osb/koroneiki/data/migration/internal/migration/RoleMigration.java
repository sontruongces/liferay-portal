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

import com.liferay.osb.koroneiki.taproot.constants.ContactRoleType;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = RoleMigration.class)
public class RoleMigration {

	public Map<Long, Long> migrate(long userId) throws Exception {
		Map<Long, Long> roleMap = new HashMap<>();

		roleMap.put(12324522L, _addContactRole(userId, "Admin"));
		roleMap.put(33118240L, _addContactRole(userId, "Buyer"));
		roleMap.put(33118252L, _addContactRole(userId, "Developer"));
		roleMap.put(33118264L, _addContactRole(userId, "LCS User"));
		roleMap.put(90852750L, _addContactRole(userId, "Sales Manager"));
		roleMap.put(90852751L, _addContactRole(userId, "Sales Representative"));
		roleMap.put(
			106868290L, _addContactRole(userId, "Analytics Cloud Owner"));
		roleMap.put(112936638L, _addContactRole(userId, "Partner Manager"));
		roleMap.put(112936646L, _addContactRole(userId, "Partner Member"));
		roleMap.put(112936656L, _addContactRole(userId, "Partner Watcher"));

		return roleMap;
	}

	private long _addContactRole(long userId, String name) throws Exception {
		ContactRole contactRole = _contactRoleLocalService.addContactRole(
			userId, name, StringPool.BLANK, ContactRoleType.ACCOUNT);

		return contactRole.getContactRoleId();
	}

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

}