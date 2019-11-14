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

package com.liferay.osb.koroneiki.taproot.constants;

/**
 * @author Kyle Bischof
 */
public class WorkflowConstants
	extends com.liferay.portal.kernel.workflow.WorkflowConstants {

	public static final String LABEL_APPROVED = "Approved";

	public static final String LABEL_CANCELLED = "Cancelled";

	public static final String LABEL_CLOSED = "Closed";

	public static final String LABEL_EXPIRED = "Expired";

	public static final String LABEL_INACTIVE = "Inactive";

	public static final String LABEL_PENDING = "Pending";

	public static final String LABEL_PENDING_VALIDATION = "Pending Validation";

	public static final String LABEL_REJECTED = "Rejected";

	public static final int STATUS_CANCELLED = 600;

	public static final int STATUS_CLOSED = 400;

	public static final int STATUS_PENDING_VALIDATION = 100;

	public static final int STATUS_REJECTED = 500;

	public static int getLabelStatus(String label) {
		if (label.equals(LABEL_APPROVED)) {
			return STATUS_APPROVED;
		}
		else if (label.equals(LABEL_CANCELLED)) {
			return STATUS_CANCELLED;
		}
		else if (label.equals(LABEL_CLOSED)) {
			return STATUS_CLOSED;
		}
		else if (label.equals(LABEL_EXPIRED)) {
			return STATUS_EXPIRED;
		}
		else if (label.equals(LABEL_INACTIVE)) {
			return STATUS_INACTIVE;
		}
		else if (label.equals(LABEL_PENDING)) {
			return STATUS_PENDING;
		}
		else if (label.equals(LABEL_PENDING_VALIDATION)) {
			return STATUS_PENDING_VALIDATION;
		}
		else if (label.equals(LABEL_REJECTED)) {
			return STATUS_REJECTED;
		}

		return com.liferay.portal.kernel.workflow.WorkflowConstants.
			getLabelStatus(label);
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_APPROVED) {
			return LABEL_APPROVED;
		}
		else if (status == STATUS_CANCELLED) {
			return LABEL_CANCELLED;
		}
		else if (status == STATUS_CLOSED) {
			return LABEL_CLOSED;
		}
		else if (status == STATUS_EXPIRED) {
			return LABEL_EXPIRED;
		}
		else if (status == STATUS_INACTIVE) {
			return LABEL_INACTIVE;
		}
		else if (status == STATUS_PENDING) {
			return LABEL_PENDING;
		}
		else if (status == STATUS_PENDING_VALIDATION) {
			return LABEL_PENDING_VALIDATION;
		}
		else if (status == STATUS_REJECTED) {
			return LABEL_REJECTED;
		}
		else {
			return com.liferay.portal.kernel.workflow.WorkflowConstants.
				getStatusLabel(status);
		}
	}

}