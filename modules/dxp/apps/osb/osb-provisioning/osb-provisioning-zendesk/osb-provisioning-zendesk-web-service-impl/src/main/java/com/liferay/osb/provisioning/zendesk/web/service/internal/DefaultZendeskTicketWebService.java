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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

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

		JSONObject commentJSONObject = JSONUtil.put(
			"body", zendeskTicket.getDescription());

		ticketJSONObject.put("comment", commentJSONObject);

		JSONObject jsonObject = JSONUtil.put("ticket", ticketJSONObject);

		zendeskBaseWebService.post(endpoint, jsonObject.toString());
	}

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

}