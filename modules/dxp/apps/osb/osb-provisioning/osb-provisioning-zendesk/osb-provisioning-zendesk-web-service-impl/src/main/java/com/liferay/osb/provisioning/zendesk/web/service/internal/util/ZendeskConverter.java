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

package com.liferay.osb.provisioning.zendesk.web.service.internal.util;

import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskConverter.class)
public class ZendeskConverter {

	public ZendeskTicket toZendeskTicket(JSONObject jsonObject) {
		ZendeskTicket zendeskTicket = new ZendeskTicket();

		zendeskTicket.setDescription(jsonObject.getString("description"));
		zendeskTicket.setRequesterId(jsonObject.getLong("requester_id"));
		zendeskTicket.setStatus(jsonObject.getString("status"));
		zendeskTicket.setSubject(jsonObject.getString("subject"));
		zendeskTicket.setZendeskOrganizationId(
			jsonObject.getLong("organization_id"));
		zendeskTicket.setZendeskTicketId(jsonObject.getLong("id"));

		return zendeskTicket;
	}

}