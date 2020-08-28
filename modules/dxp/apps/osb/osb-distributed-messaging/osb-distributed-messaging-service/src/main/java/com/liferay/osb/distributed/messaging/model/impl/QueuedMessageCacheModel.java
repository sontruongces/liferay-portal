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

package com.liferay.osb.distributed.messaging.model.impl;

import com.liferay.osb.distributed.messaging.model.QueuedMessage;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing QueuedMessage in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class QueuedMessageCacheModel
	implements CacheModel<QueuedMessage>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof QueuedMessageCacheModel)) {
			return false;
		}

		QueuedMessageCacheModel queuedMessageCacheModel =
			(QueuedMessageCacheModel)object;

		if ((queuedMessageId == queuedMessageCacheModel.queuedMessageId) &&
			(mvccVersion == queuedMessageCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, queuedMessageId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", queuedMessageId=");
		sb.append(queuedMessageId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", messageBrokerClassName=");
		sb.append(messageBrokerClassName);
		sb.append(", topic=");
		sb.append(topic);

		return sb.toString();
	}

	@Override
	public QueuedMessage toEntityModel() {
		QueuedMessageImpl queuedMessageImpl = new QueuedMessageImpl();

		queuedMessageImpl.setMvccVersion(mvccVersion);
		queuedMessageImpl.setQueuedMessageId(queuedMessageId);

		if (createDate == Long.MIN_VALUE) {
			queuedMessageImpl.setCreateDate(null);
		}
		else {
			queuedMessageImpl.setCreateDate(new Date(createDate));
		}

		if (messageBrokerClassName == null) {
			queuedMessageImpl.setMessageBrokerClassName("");
		}
		else {
			queuedMessageImpl.setMessageBrokerClassName(messageBrokerClassName);
		}

		if (topic == null) {
			queuedMessageImpl.setTopic("");
		}
		else {
			queuedMessageImpl.setTopic(topic);
		}

		queuedMessageImpl.resetOriginalValues();

		return queuedMessageImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		queuedMessageId = objectInput.readLong();
		createDate = objectInput.readLong();
		messageBrokerClassName = objectInput.readUTF();
		topic = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(queuedMessageId);
		objectOutput.writeLong(createDate);

		if (messageBrokerClassName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(messageBrokerClassName);
		}

		if (topic == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(topic);
		}
	}

	public long mvccVersion;
	public long queuedMessageId;
	public long createDate;
	public String messageBrokerClassName;
	public String topic;

}