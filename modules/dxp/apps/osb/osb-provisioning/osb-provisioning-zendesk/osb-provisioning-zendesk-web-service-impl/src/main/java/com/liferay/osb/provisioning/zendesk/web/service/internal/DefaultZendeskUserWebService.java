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

package com.liferay.osb.provisioning.zendesk.web.service.internal;

import com.liferay.osb.provisioning.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.provisioning.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.provisioning.zendesk.model.ZendeskUser;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.provisioning.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskUserWebService.class
)
public class DefaultZendeskUserWebService implements ZendeskUserWebService {

	public ZendeskUser getZendeskUserByEmailAddress(String emailAddress)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("query", emailAddress);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json", parameters);

		JSONArray usersJSONArray = responseJSONObject.getJSONArray("users");

		if (usersJSONArray.length() <= 0) {
			return null;
		}

		return zendeskConverter.toZendeskUser(usersJSONArray.getJSONObject(0));
	}

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}