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

package com.liferay.osb.provisioning.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.constants.ProductConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * @author Kyle Bischof
 */
public class AccountUtil {

	public static String getHighestSLA(Account account) {
		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return StringPool.BLANK;
		}

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			Product product = productPurchase.getProduct();

			String productName = product.getName();

			if (ArrayUtil.contains(
					ProductConstants.SUBSCRIPTION_NAMES, productName)) {

				return StringUtil.replace(
					productName, " Subscription", StringPool.BLANK);
			}
		}

		return StringPool.BLANK;
	}

	public static String getPartner(Account account) {
		Team[] teams = account.getAssignedTeams();

		if (!ArrayUtil.isEmpty(teams)) {
			return teams[0].getName();
		}

		return StringPool.BLANK;
	}

	public static String getSupportEndDate(Account account) {
		if (ArrayUtil.isEmpty(account.getProductPurchases())) {
			return StringPool.BLANK;
		}

		Date supportEndDate = null;

		for (ProductPurchase productPurchase : account.getProductPurchases()) {
			if (productPurchase.getPerpetual()) {
				return "Perpetual";
			}

			if ((supportEndDate == null) ||
				supportEndDate.before(productPurchase.getEndDate())) {

				supportEndDate = productPurchase.getEndDate();
			}
		}

		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

		return dateFormat.format(supportEndDate);
	}

}