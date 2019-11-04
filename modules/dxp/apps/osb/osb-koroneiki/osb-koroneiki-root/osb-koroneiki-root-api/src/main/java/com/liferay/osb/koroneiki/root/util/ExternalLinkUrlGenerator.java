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

package com.liferay.osb.koroneiki.root.util;

import com.liferay.osb.koroneiki.root.constants.RootPortletKeys;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Kyle Bischof
 */
public class ExternalLinkUrlGenerator {

	public static String generate(
		long companyId, String domain, String entityName, String entityId) {

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				companyId, companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
				PortletKeys.PREFS_PLID_SHARED,
				RootPortletKeys.EXTERNAL_LINKS_ADMIN);

		String url = portletPreferences.getValue(
			domain + StringPool.UNDERLINE + entityName, StringPool.BLANK);

		return StringUtil.replace(url, "[$ENTITY_ID$]", entityId);
	}

}