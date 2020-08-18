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

package com.liferay.osb.distributed.messaging.rabbitmq.connector.consumer;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.Connection;
import com.liferay.osb.distributed.messaging.rabbitmq.connector.message.AttributeTranslator;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;
import com.liferay.osgi.util.StringPlus;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseConsumer implements Consumer {

	public Channel getChannel() {
		return channel;
	}

	public void handleCancel(String consumerTag) throws IOException {
		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Consumer cancelled for ", consumerTag, " on ", queue));
		}
	}

	public void handleCancelOk(String consumerTag) {
		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Consumer cancelled for ", consumerTag, " on ", queue));
		}
	}

	public void handleConsumeOk(String consumerTag) {
		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Consumer registered for ", consumerTag, " on ", queue));
		}
	}

	@Override
	public void handleDelivery(
		String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
		byte[] body) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"Received message ", envelope.getDeliveryTag(),
					" with routing key ", envelope.getRoutingKey()));
		}

		String payload = null;

		try {
			payload = new String(body, "UTF-8");
		}
		catch (UnsupportedEncodingException unsupportedEncodingException) {
			_log.error(
				unsupportedEncodingException, unsupportedEncodingException);

			basicAck(envelope);

			return;
		}

		try {
			messageRouter.route(
				envelope.getRoutingKey(),
				new Message(
					envelope.getRoutingKey(),
					AttributeTranslator.toMap(properties), payload));

			basicAck(envelope);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			basicReject(envelope);
		}
	}

	public void handleRecoverOk(String consumerTag) {
		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Recovered connection for ", consumerTag, " on ", queue));
		}
	}

	public void handleShutdownSignal(
		String consumerTag, ShutdownSignalException shutdownSignalException) {

		_log.error(
			StringBundler.concat(
				"Shutdown signal for ", consumerTag, " on ", queue),
			shutdownSignalException);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		exchange = GetterUtil.getString(properties.get("exchange"));
		queue = GetterUtil.getString(properties.get("queue"));
		prefetchCount = GetterUtil.getInteger(
			properties.get("prefetch.count"), 20);
		routingKeys = StringPlus.asList(properties.get("routing.key"));

		Connection connection = getConnection();

		channel = connection.createChannel(prefetchCount);

		channel.queueDeclare(queue, true, false, false, null);

		for (String routingKey : routingKeys) {
			channel.queueBind(queue, exchange, routingKey);
		}

		channel.basicConsume(queue, false, this);
	}

	protected void basicAck(Envelope envelope) {
		try {
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);
		}
	}

	protected void basicReject(Envelope envelope) {
		try {
			channel.basicReject(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);
		}
	}

	@Deactivate
	protected void deactivate() throws Exception {
		if (channel != null) {
			channel.close();
		}
	}

	protected abstract Connection getConnection();

	protected Channel channel;
	protected String exchange;

	@Reference
	protected MessageRouter messageRouter;

	protected int prefetchCount;
	protected String queue;
	protected List<String> routingKeys;

	private static final Log _log = LogFactoryUtil.getLog(BaseConsumer.class);

}