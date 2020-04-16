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

package com.liferay.osb.provisioning.koroneiki.web.service.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class HttpException extends PortalException {

	public HttpException() {
	}

	public HttpException(String message) {
		super(message);
	}

	public HttpException(String message, int statusCode) {
		super(message);

		_statusCode = statusCode;
	}

	public HttpException(String message, Throwable cause, int statusCode) {
		super(message, cause);

		_statusCode = statusCode;
	}

	public HttpException(Throwable cause, int statusCode) {
		super(cause);

		_statusCode = statusCode;
	}

	public int getStatusCode() {
		return _statusCode;
	}

	private int _statusCode;

}