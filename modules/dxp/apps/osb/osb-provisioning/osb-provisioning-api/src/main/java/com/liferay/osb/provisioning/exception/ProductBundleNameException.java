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

package com.liferay.osb.provisioning.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Yuanyuan Huang
 */
public class ProductBundleNameException extends PortalException {

	public ProductBundleNameException() {
	}

	public ProductBundleNameException(String msg) {
		super(msg);
	}

	public ProductBundleNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ProductBundleNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends ProductBundleNameException {

		public MustNotBeDuplicate(String name) {
			super(
				String.format(
					"A product bundle with name %s is already in use", name));
		}

	}

}