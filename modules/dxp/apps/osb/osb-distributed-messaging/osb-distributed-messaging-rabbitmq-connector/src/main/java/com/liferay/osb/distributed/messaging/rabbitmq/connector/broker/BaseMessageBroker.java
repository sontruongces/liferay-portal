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

package com.liferay.osb.distributed.messaging.rabbitmq.connector.broker;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.broker.MessageBroker;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.message.AttributeTranslator;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import com.rabbitmq.client.Channel;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Amos Fong
 */
public abstract class BaseMessageBroker implements MessageBroker {

	@Override
	public void publish(String topic, List<Message> messages)
		throws IOException {

		Channel channel = getChannel();

		for (Message message : messages) {
			try {
				_publish(channel, topic, message);
			}
			catch (IOException ioException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to publish. Resetting channel and retrying.",
						ioException);
				}

				closeChannel();

				channel = getChannel();

				_publish(channel, topic, message);
			}
		}
	}

	@Override
	public void publish(String topic, Message message) throws IOException {
		try {
			_publish(getChannel(), topic, message);
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to publish. Resetting channel and retrying.",
					ioException);
			}

			closeChannel();

			_publish(getChannel(), topic, message);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_exchange = GetterUtil.getString(properties.get("exchange"));
	}

	protected void closeChannel() {
		Connection connection = getConnection();

		Map<String, Channel> channelMap = _channelMapThreadLocal.get();

		Class<?> clazz = connection.getClass();

		Channel channel = channelMap.remove(clazz.getName());

		if (channel != null) {
			try {
				channel.close();
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
	}

	@Deactivate
	protected void deactivate() {
		_channelMapThreadLocal.remove();
	}

	protected Channel getChannel() throws IOException {
		Connection connection = getConnection();

		Map<String, Channel> channelMap = _channelMapThreadLocal.get();

		Class<?> clazz = connection.getClass();

		Channel channel = channelMap.get(clazz.getName());

		if ((channel == null) || !channel.isOpen()) {
			channel = connection.createChannel();

			channelMap.put(clazz.getName(), channel);
		}

		return channel;
	}

	protected abstract Connection getConnection();

	private void _publish(Channel channel, String topic, Message message)
		throws IOException {

		String payload = String.valueOf(message.getPayload());

		channel.basicPublish(
			_exchange, topic,
			AttributeTranslator.toProperties(message.getAttributes()),
			payload.getBytes());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageBroker.class);

	private static final ThreadLocal<Map<String, Channel>>
		_channelMapThreadLocal = new CentralizedThreadLocal<>(
			BaseMessageBroker.class + "._channelMap", () -> new HashMap<>(),
			false);

	private String _exchange;

}