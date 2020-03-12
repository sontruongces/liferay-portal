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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ExternalLinkDisplay {

	public ExternalLinkDisplay(
			HttpServletRequest httpServletRequest, ExternalLink externalLink)
		throws Exception {

		_httpServletRequest = httpServletRequest;
		_externalLink = externalLink;
	}

	public String getDomain() {
		return _externalLink.getDomain();
	}

	public String getEntityId() {
		return _externalLink.getEntityId();
	}

	public String getEntityName() {
		return _externalLink.getEntityName();
	}

	public ExternalLink getExternalLink() {
		return _externalLink;
	}

	public String getUrl() {
		return _externalLink.getUrl();
	}

	private final ExternalLink _externalLink;
	private final HttpServletRequest _httpServletRequest;

}