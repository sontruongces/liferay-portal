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

package com.liferay.osb.provisioning.message.connector;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Kyle Bischof
 */
public interface WebServiceConnector {

	public JSONObject getContactByEmailAddress(String emailAddress)
		throws Exception;

	public JSONObject getContactRoles(String name) throws Exception;

	public JSONObject getProductByExternalLink(
			String domain, String entityName, String entityId)
		throws Exception;

	public JSONObject postAccount(String json) throws Exception;

	public JSONObject postContactRole(String json) throws Exception;

	public JSONObject postProduct(String json) throws Exception;

}