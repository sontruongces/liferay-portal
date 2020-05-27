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

package com.liferay.osb.commerce.provisioning;

import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Ivica Cardic
 */
public enum OSBCommercePortalInstanceStatus {

	ACTIVE(WorkflowConstants.STATUS_APPROVED), CANCELLED(1),
	IN_PROGRESS(WorkflowConstants.STATUS_INCOMPLETE);

	public int getStatus() {
		return _status;
	}

	private OSBCommercePortalInstanceStatus(int status) {
		_status = status;
	}

	private final int _status;

}