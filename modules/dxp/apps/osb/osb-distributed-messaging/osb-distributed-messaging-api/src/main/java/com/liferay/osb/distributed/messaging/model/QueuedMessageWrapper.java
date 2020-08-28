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

package com.liferay.osb.distributed.messaging.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.sql.Blob;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link QueuedMessage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessage
 * @generated
 */
public class QueuedMessageWrapper
	extends BaseModelWrapper<QueuedMessage>
	implements ModelWrapper<QueuedMessage>, QueuedMessage {

	public QueuedMessageWrapper(QueuedMessage queuedMessage) {
		super(queuedMessage);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("queuedMessageId", getQueuedMessageId());
		attributes.put("createDate", getCreateDate());
		attributes.put("messageBrokerClassName", getMessageBrokerClassName());
		attributes.put("topic", getTopic());
		attributes.put("messageObject", getMessageObject());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long queuedMessageId = (Long)attributes.get("queuedMessageId");

		if (queuedMessageId != null) {
			setQueuedMessageId(queuedMessageId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String messageBrokerClassName = (String)attributes.get(
			"messageBrokerClassName");

		if (messageBrokerClassName != null) {
			setMessageBrokerClassName(messageBrokerClassName);
		}

		String topic = (String)attributes.get("topic");

		if (topic != null) {
			setTopic(topic);
		}

		Blob messageObject = (Blob)attributes.get("messageObject");

		if (messageObject != null) {
			setMessageObject(messageObject);
		}
	}

	/**
	 * Returns the create date of this queued message.
	 *
	 * @return the create date of this queued message
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public com.liferay.osb.distributed.messaging.Message getMessage()
		throws Exception {

		return model.getMessage();
	}

	/**
	 * Returns the message broker class name of this queued message.
	 *
	 * @return the message broker class name of this queued message
	 */
	@Override
	public String getMessageBrokerClassName() {
		return model.getMessageBrokerClassName();
	}

	/**
	 * Returns the message object of this queued message.
	 *
	 * @return the message object of this queued message
	 */
	@Override
	public Blob getMessageObject() {
		return model.getMessageObject();
	}

	/**
	 * Returns the mvcc version of this queued message.
	 *
	 * @return the mvcc version of this queued message
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this queued message.
	 *
	 * @return the primary key of this queued message
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the queued message ID of this queued message.
	 *
	 * @return the queued message ID of this queued message
	 */
	@Override
	public long getQueuedMessageId() {
		return model.getQueuedMessageId();
	}

	/**
	 * Returns the topic of this queued message.
	 *
	 * @return the topic of this queued message
	 */
	@Override
	public String getTopic() {
		return model.getTopic();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the create date of this queued message.
	 *
	 * @param createDate the create date of this queued message
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the message broker class name of this queued message.
	 *
	 * @param messageBrokerClassName the message broker class name of this queued message
	 */
	@Override
	public void setMessageBrokerClassName(String messageBrokerClassName) {
		model.setMessageBrokerClassName(messageBrokerClassName);
	}

	/**
	 * Sets the message object of this queued message.
	 *
	 * @param messageObject the message object of this queued message
	 */
	@Override
	public void setMessageObject(Blob messageObject) {
		model.setMessageObject(messageObject);
	}

	/**
	 * Sets the mvcc version of this queued message.
	 *
	 * @param mvccVersion the mvcc version of this queued message
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this queued message.
	 *
	 * @param primaryKey the primary key of this queued message
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the queued message ID of this queued message.
	 *
	 * @param queuedMessageId the queued message ID of this queued message
	 */
	@Override
	public void setQueuedMessageId(long queuedMessageId) {
		model.setQueuedMessageId(queuedMessageId);
	}

	/**
	 * Sets the topic of this queued message.
	 *
	 * @param topic the topic of this queued message
	 */
	@Override
	public void setTopic(String topic) {
		model.setTopic(topic);
	}

	@Override
	protected QueuedMessageWrapper wrap(QueuedMessage queuedMessage) {
		return new QueuedMessageWrapper(queuedMessage);
	}

}