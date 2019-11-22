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

	public Long getAccountCustomerContactRoleId(int role) {
		return _accountCustomerRoleMap.get(role);
	}

	public Long getAccountWorkerContactRoleId(int role) {
		return _accountWorkerRoleMap.get(role);
	}

	public Long getPortalContactRoleId(long roleId) {
		return _portalRoleMap.get(roleId);
	}

	public void migrate(long userId) throws Exception {
		_portalRoleMap.put(12324522L, _addContactRole(userId, "Admin"));
		_portalRoleMap.put(33118240L, _addContactRole(userId, "Buyer"));
		_portalRoleMap.put(33118252L, _addContactRole(userId, "Developer"));
		_portalRoleMap.put(33118264L, _addContactRole(userId, "LCS User"));
		_portalRoleMap.put(90852750L, _addContactRole(userId, "Sales Manager"));
		_portalRoleMap.put(
			90852751L, _addContactRole(userId, "Sales Representative"));
		_portalRoleMap.put(
			106868290L, _addContactRole(userId, "Analytics Cloud Owner"));
		_portalRoleMap.put(
			112936638L, _addContactRole(userId, "Partner Manager"));
		_portalRoleMap.put(
			112936646L, _addContactRole(userId, "Partner Member"));
		_portalRoleMap.put(
			112936656L, _addContactRole(userId, "Partner Watcher"));

		long supportContactContactRoleId = _addContactRole(
			userId, "Support Contact");

		_accountCustomerRoleMap.put(0, supportContactContactRoleId);
		_accountCustomerRoleMap.put(2, supportContactContactRoleId);

		long supportWatcherContactRoleId = _addContactRole(
			userId, "Support Watcher");

		_accountCustomerRoleMap.put(1, supportWatcherContactRoleId);
		_accountCustomerRoleMap.put(3, supportWatcherContactRoleId);
		_accountCustomerRoleMap.put(4, supportWatcherContactRoleId);

		_accountWorkerRoleMap.put(2, _addContactRole(userId, "Liferay Sales"));
		_accountWorkerRoleMap.put(
			4, _addContactRole(userId, "Liferay Advocacy Specialist"));
		_accountWorkerRoleMap.put(
			5, _addContactRole(userId, "Liferay Managed Services"));
		_accountWorkerRoleMap.put(
			6, _addContactRole(userId, "Liferay Customer Success"));
	}

	private long _addContactRole(long userId, String name) throws Exception {
		ContactRole contactRole = _contactRoleLocalService.addContactRole(
			userId, name, StringPool.BLANK, ContactRoleType.ACCOUNT);

		return contactRole.getContactRoleId();
	}

	private final Map<Integer, Long> _accountCustomerRoleMap = new HashMap<>();
	private final Map<Integer, Long> _accountWorkerRoleMap = new HashMap<>();

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	private final Map<Long, Long> _portalRoleMap = new HashMap<>();

}