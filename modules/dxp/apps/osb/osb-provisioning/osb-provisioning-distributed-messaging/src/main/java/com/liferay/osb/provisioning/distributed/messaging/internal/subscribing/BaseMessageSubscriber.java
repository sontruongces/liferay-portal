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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.provisioning.distributed.messaging.internal.configuration.ProvisioningDistributedMessagingConfigurationValues;
import com.liferay.osb.provisioning.zendesk.model.ZendeskTicket;
import com.liferay.osb.provisioning.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class BaseMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			JSONObject jsonObject = jsonFactory.createJSONObject(
				(String)message.getPayload());

			doParse(jsonObject);
		}
		catch (Exception exception) {
			try {
				createZendeskTicket(
					message.getDestinationName(), (String)message.getPayload(),
					exception);
			}
			catch (PortalException portalException) {
				_log.error(message);

				_log.error(portalException, portalException);
			}
		}
	}

	protected void createZendeskTicket(
			String routingKey, String message, Exception exception)
		throws PortalException {

		ZendeskTicket zendeskTicket = new ZendeskTicket();

		Map<Long, String> customFields = new HashMap<>();

		customFields.put(
			ProvisioningDistributedMessagingConfigurationValues.
				ZENDESK_CUSTOM_FIELD_PRODUCT_ID,
			"Provisioning Request");

		zendeskTicket.setCustomFields(customFields);

		StringBundler sb = new StringBundler(7);

		sb.append("An unexpected error occurred.<br />Routing Key: ");
		sb.append(routingKey);
		sb.append("<br />Message:<br /><pre>");
		sb.append(message);
		sb.append("</pre><br />Error:<br /><pre>");
		sb.append(StackTraceUtil.getStackTrace(exception));
		sb.append("</pre>");

		_log.error("Creating error Zendesk ticket: " + sb.toString());

		zendeskTicket.setDescription(sb.toString());

		zendeskTicket.setGroupId(
			ProvisioningDistributedMessagingConfigurationValues.
				PROVISIONING_ZENDESK_GROUP_ID);
		zendeskTicket.setRequesterId(
			ProvisioningDistributedMessagingConfigurationValues.
				PROVISIONING_ZENDESK_REQUESTER_ID);
		zendeskTicket.setSubject("Auto-Provisioning Error");
		zendeskTicket.setZendeskOrganizationId(
			ProvisioningDistributedMessagingConfigurationValues.
				PROVISIONING_ZENDESK_ORGANIZATION_ID);

		zendeskTicketWebService.createZendeskTicket(zendeskTicket);
	}

	protected abstract void doParse(JSONObject jsonObject) throws Exception;

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected ZendeskTicketWebService zendeskTicketWebService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}