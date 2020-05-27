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

package com.liferay.osb.commerce.provisioning.internal.cloud.client.dto;

/**
 * @author Ivica Cardic
 */
public class PortalInstance {

	public String getVirtualHostname() {
		return _virtualHostname;
	}

	public String getWebId() {
		return _webId;
	}

	public void setVirtualHostname(String virtualHostname) {
		_virtualHostname = virtualHostname;
	}

	public void setWebId(String webId) {
		_webId = webId;
	}

	private String _virtualHostname;
	private String _webId;

}