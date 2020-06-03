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

package com.liferay.osb.provisioning.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ExternalLinkWebService {

	public ExternalLink addAccountExternalLink(
			String agentName, String agentUID, String accountKey,
			ExternalLink externalLink)
		throws Exception;

	public ExternalLink addProductExternalLink(
			String agentName, String agentUID, String productKey,
			ExternalLink externalLink)
		throws Exception;

	public void deleteExternalLink(
			String agentName, String agentUID, String externalLinkKey)
		throws Exception;

	public List<ExternalLink> getExternalLinks(
			String accountKey, int page, int pageSize)
		throws Exception;

	public ExternalLink updateExternalLink(
			String agentName, String agentUID, String externalLinkKey,
			ExternalLink externalLink)
		throws Exception;

}