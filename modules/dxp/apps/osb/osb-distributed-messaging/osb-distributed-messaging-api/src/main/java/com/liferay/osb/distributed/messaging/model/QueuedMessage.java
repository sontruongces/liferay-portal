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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the QueuedMessage service. Represents a row in the &quot;DM_QueuedMessage&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see QueuedMessageModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.distributed.messaging.model.impl.QueuedMessageImpl"
)
@ProviderType
public interface QueuedMessage extends PersistedModel, QueuedMessageModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.distributed.messaging.model.impl.QueuedMessageImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<QueuedMessage, Long>
		QUEUED_MESSAGE_ID_ACCESSOR = new Accessor<QueuedMessage, Long>() {

			@Override
			public Long get(QueuedMessage queuedMessage) {
				return queuedMessage.getQueuedMessageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<QueuedMessage> getTypeClass() {
				return QueuedMessage.class;
			}

		};

	public com.liferay.osb.distributed.messaging.Message getMessage()
		throws Exception;

}