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

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the messageObject column in QueuedMessage.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessage
 * @generated
 */
public class QueuedMessageMessageObjectBlobModel {

	public QueuedMessageMessageObjectBlobModel() {
	}

	public QueuedMessageMessageObjectBlobModel(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public QueuedMessageMessageObjectBlobModel(
		long queuedMessageId, Blob messageObjectBlob) {

		_queuedMessageId = queuedMessageId;
		_messageObjectBlob = messageObjectBlob;
	}

	public long getQueuedMessageId() {
		return _queuedMessageId;
	}

	public void setQueuedMessageId(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public Blob getMessageObjectBlob() {
		return _messageObjectBlob;
	}

	public void setMessageObjectBlob(Blob messageObjectBlob) {
		_messageObjectBlob = messageObjectBlob;
	}

	private long _queuedMessageId;
	private Blob _messageObjectBlob;

}