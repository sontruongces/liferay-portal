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
import com.liferay.osb.provisioning.message.parser.MessageParser;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=dossiera.provisioning.create",
	service = DossieraCreateMessageSubscriber.class
)
public class DossieraCreateMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(
				(String)message.getPayload());

			_dossieraCreateMessageParser.parse(
				"dossiera.provisioning.create", jsonObject);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DossieraCreateMessageSubscriber.class);

	@Reference(target = "(type=dossiera.create)")
	private MessageParser _dossieraCreateMessageParser;

	@Reference
	private JSONFactory _jsonFactory;

}