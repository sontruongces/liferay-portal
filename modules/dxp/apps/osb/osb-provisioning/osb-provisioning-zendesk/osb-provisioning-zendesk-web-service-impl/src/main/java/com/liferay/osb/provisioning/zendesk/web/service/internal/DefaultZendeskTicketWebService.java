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
			"comment", JSONUtil.put("html_body", zendeskTicket.getDescription())
		).put(
			"organization_id", zendeskTicket.getZendeskOrganizationId()
		).put(
			"requester_id", zendeskTicket.getRequesterId()
		).put(
			"subject", zendeskTicket.getSubject()
		);

		JSONArray customFieldsJSONArray = JSONFactoryUtil.createJSONArray();

		Map<Long, String> customFields = zendeskTicket.getCustomFields();

		for (Map.Entry<Long, String> customField : customFields.entrySet()) {
			customFieldsJSONArray.put(
				JSONUtil.put(
					"id", customField.getKey()
				).put(
					"value", customField.getValue()
				));
		}

		ticketJSONObject.put("custom_fields", customFieldsJSONArray);

		if (zendeskTicket.getGroupId() > 0) {
			ticketJSONObject.put("group_id", zendeskTicket.getGroupId());
		}

		JSONObject jsonObject = JSONUtil.put("ticket", ticketJSONObject);

		zendeskBaseWebService.post(endpoint, jsonObject.toString());
	}

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

}