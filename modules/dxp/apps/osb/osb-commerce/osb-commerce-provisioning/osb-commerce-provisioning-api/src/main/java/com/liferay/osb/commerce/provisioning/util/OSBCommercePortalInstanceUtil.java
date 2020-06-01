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

package com.liferay.osb.commerce.provisioning.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

/**
 * @author Ivica Cardic
 */
public class OSBCommercePortalInstanceUtil {

	public static String getPortalInstanceURL(
		String portalInstanceVirtualHostname) {

		StringBundler sb = new StringBundler(5);

		boolean devEnvironment = _isMockEnvironment(
			portalInstanceVirtualHostname);

		if (devEnvironment) {
			sb.append("http://");
		}
		else {
			sb.append("https://");
		}

		sb.append(portalInstanceVirtualHostname);
		sb.append(StringPool.COLON);

		if (devEnvironment) {
			sb.append("8080");
		}
		else {
			sb.append("443");
		}

		sb.append("/group/osb-commerce");

		return sb.toString();
	}

	private static boolean _isMockEnvironment(
		String portalInstanceVirtualHostname) {

		return portalInstanceVirtualHostname.endsWith(".test");
	}

}