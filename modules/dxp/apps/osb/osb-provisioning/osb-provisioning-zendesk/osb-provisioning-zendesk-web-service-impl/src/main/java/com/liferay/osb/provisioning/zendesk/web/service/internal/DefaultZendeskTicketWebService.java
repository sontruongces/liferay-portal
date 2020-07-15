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
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ZendeskTicketWebService.class)
public class DefaultZendeskTicketWebService implements ZendeskTicketWebService {

	public void createZendeskTicket(ZendeskTicket zendeskTicket)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + ZendeskRESTEndpoints.TICKETS;

		JSONObject ticketJSONObject = JSONUtil.put(
			"subject", zendeskTicket.getSubject());

		if (zendeskTicket.getGroupId() > 0) {
			ticketJSONObject.put("group_id", zendeskTicket.getGroupId());
		}

		ticketJSONObject.put(
			"organization_id", zendeskTicket.getZendeskOrganizationId());
		ticketJSONObject.put("requester_id", zendeskTicket.getRequesterId());

		JSONObject commentJSONObject = JSONUtil.put(
			"html_body", zendeskTicket.getDescription());

		ticketJSONObject.put("comment", commentJSONObject);

		JSONArray customFieldsJSONArray = JSONFactoryUtil.createJSONArray();

		Map<Long, String> customFields = zendeskTicket.getCustomFields();

		for (Map.Entry<Long, String> customField : customFields.entrySet()) {
			JSONObject fieldJSONObject = JSONUtil.put(
				"id", customField.getKey());

			fieldJSONObject.put("value", customField.getValue());

			customFieldsJSONArray.put(fieldJSONObject);
		}

		ticketJSONObject.put("custom_fields", customFieldsJSONArray);

		JSONObject jsonObject = JSONUtil.put("ticket", ticketJSONObject);

		zendeskBaseWebService.post(endpoint, jsonObject.toString());
	}

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

}