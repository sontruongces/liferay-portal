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

package com.liferay.osb.provisioning.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Kyle Bischof
 */
public class ProvisioningWebConfigurationValues {

	public static final String GENERATE_LICENSE_URL = GetterUtil.getString(
		ProvisioningWebConfigurationUtil.get("generate.license.url"));

	public static final String LICENSE_MANAGER_URL = GetterUtil.getString(
		ProvisioningWebConfigurationUtil.get("license.manager.url"));

	public static String getGenerateLicenseURL(String accountKey) {
		return StringUtil.replace(
			GENERATE_LICENSE_URL, "[$ACCOUNT_KEY$]", accountKey);
	}

	public static String getLicenseManagerURL(
		String accountKey, String productKey) {

		String licenseManagerURL = StringUtil.replace(
			LICENSE_MANAGER_URL, "[$ACCOUNT_KEY$]", accountKey);

		return StringUtil.replace(
			licenseManagerURL, "[$PRODUCT_KEY$]", productKey);
	}

}