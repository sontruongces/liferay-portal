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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class ProductPurchaseViewUtil {

	public static ProductPurchaseView toProductPurchaseView(
			ProductEntry productEntry,
			List<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				trunkProductConsumptions,
			List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
				trunkProductPurchases)
		throws Exception {

		return new ProductPurchaseView() {
			{
				product = ProductUtil.toProduct(productEntry);
				productConsumptions = TransformUtil.transformToArray(
					trunkProductConsumptions,
					ProductConsumptionUtil::toProductConsumption,
					ProductConsumption.class);
				productPurchases = TransformUtil.transformToArray(
					trunkProductPurchases,
					ProductPurchaseUtil::toProductPurchase,
					ProductPurchase.class);
			}
		};
	}

}