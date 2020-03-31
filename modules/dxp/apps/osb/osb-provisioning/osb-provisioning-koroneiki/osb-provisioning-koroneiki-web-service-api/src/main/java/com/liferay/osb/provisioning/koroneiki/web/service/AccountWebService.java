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

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface AccountWebService {

	public void assignContactRoles(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public Account fetchAccount(String accountKey) throws Exception;

	public Account getAccount(String accountKey) throws Exception;

	public Account postAccount(
			String agentName, String agentUID, Account account)
		throws Exception;

	public Account putAccount(
			String agentName, String agentUID, String accountKey,
			Account account)
		throws Exception;

	public List<Account> search(
			String filterString, int page, int pageSize, String sortString)
		throws Exception;

	public long searchCount(String filterString) throws Exception;

	public void unassignContactRoles(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress, String[] contactRoleKeys)
		throws Exception;

	public void unassignCustomerContact(
			String agentName, String agentUID, String accountKey,
			String contactEmailAddress)
		throws Exception;

}