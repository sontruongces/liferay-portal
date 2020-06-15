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

import com.liferay.headless.osb.commerce.client.dto.v1_0.UserAccount;
import com.liferay.headless.osb.commerce.client.resource.v1_0.UserAccountResource;
import com.liferay.portal.util.PropsValues;

/**
 * @author Ivica Cardic
 */
public class UserAccountClientMockImpl implements UserAccountClient {

	public UserAccountClientMockImpl() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public UserAccount postUserAccount(
		UserAccount userAccount, String virtualHostname) {

		UserAccountResource.Builder builder = UserAccountResource.builder();

		UserAccountResource userAccountResource = builder.endpoint(
			virtualHostname, 8080, "http"
		).authentication(
			PropsValues.DEFAULT_ADMIN_EMAIL_ADDRESS_PREFIX + "@" +
				virtualHostname,
			PropsValues.DEFAULT_ADMIN_PASSWORD
		).build();

		try {
			userAccount = userAccountResource.postUserAccount(userAccount);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		return userAccount;
	}

}