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

package com.liferay.osb.koroneiki.root.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ExternalLinkFinder {

	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		findByDomain(String domain, int start, int end);

	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		findByD_EN(String domain, String entityName, int start, int end);

}