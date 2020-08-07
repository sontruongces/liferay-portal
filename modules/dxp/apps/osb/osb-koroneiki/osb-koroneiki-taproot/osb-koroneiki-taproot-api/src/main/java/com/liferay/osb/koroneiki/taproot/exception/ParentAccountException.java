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

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Yuanyuan Huang
 * @author Kyle Bischof
 */
public class ParentAccountException extends PortalException {

	public ParentAccountException() {
	}

	public ParentAccountException(String msg) {
		super(msg);
	}

	public ParentAccountException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ParentAccountException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeChild extends ParentAccountException {

		public MustNotBeChild(String accountId) {
			super(
				String.format(
					"The parent account must not be a child of %s", accountId));
		}

	}

	public static class MustNotBeIdentical extends ParentAccountException {

		public MustNotBeIdentical(String accountId) {
			super(
				String.format(
					"The parent account must be different than %s", accountId));
		}

	}

}