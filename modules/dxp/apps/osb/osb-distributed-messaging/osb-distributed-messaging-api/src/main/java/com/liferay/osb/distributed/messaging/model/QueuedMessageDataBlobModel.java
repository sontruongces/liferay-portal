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
 * The Blob model class for lazy loading the data column in QueuedMessage.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessage
 * @generated
 */
public class QueuedMessageDataBlobModel {

	public QueuedMessageDataBlobModel() {
	}

	public QueuedMessageDataBlobModel(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public QueuedMessageDataBlobModel(long queuedMessageId, Blob dataBlob) {
		_queuedMessageId = queuedMessageId;
		_dataBlob = dataBlob;
	}

	public long getQueuedMessageId() {
		return _queuedMessageId;
	}

	public void setQueuedMessageId(long queuedMessageId) {
		_queuedMessageId = queuedMessageId;
	}

	public Blob getDataBlob() {
		return _dataBlob;
	}

	public void setDataBlob(Blob dataBlob) {
		_dataBlob = dataBlob;
	}

	private long _queuedMessageId;
	private Blob _dataBlob;

}