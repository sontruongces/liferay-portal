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

package com.liferay.osb.commerce.provisioning.web.internal.display.context;

import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.osb.commerce.provisioning.constants.OSBCommercePortalInstanceConstants;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class PlanSettingsDisplayContext {

	public PlanSettingsDisplayContext(
		long commerceAccountId,
		CommerceOrderLocalService commerceOrderLocalService,
		CommerceSubscriptionEntryLocalService
			commerceSubscriptionEntryLocalService) {

		_commerceAccountId = commerceAccountId;
		_commerceOrderLocalService = commerceOrderLocalService;
		_commerceSubscriptionEntryLocalService =
			commerceSubscriptionEntryLocalService;
	}

	public CommerceOrderItem getCommerceOrderItem() {
		if (_commerceOrderItem == null) {
			List<CommerceOrder> commerceOrders =
				_commerceOrderLocalService.getCommerceOrdersByCommerceAccountId(
					_commerceAccountId, 0,
					_commerceOrderLocalService.
						getCommerceOrdersCountByCommerceAccountId(
							_commerceAccountId),
					OrderByComparatorFactoryUtil.create(
						"CommerceOrder", "paymentStatus", true, "createDate",
						false));

			if (commerceOrders.isEmpty()) {
				return null;
			}

			CommerceOrder commerceOrder = commerceOrders.get(0);

			if (commerceOrder.getPaymentStatus() !=
					CommerceOrderPaymentConstants.STATUS_COMPLETED) {

				return null;
			}

			List<CommerceOrderItem> commerceOrderItems =
				commerceOrder.getCommerceOrderItems();

			_commerceOrderItem = commerceOrderItems.get(0);
		}

		return _commerceOrderItem;
	}

	public CommerceSubscriptionEntry getCommerceSubscriptionEntry() {
		CommerceOrderItem commerceOrderItem = getCommerceOrderItem();

		if (commerceOrderItem == null) {
			return null;
		}

		return _commerceSubscriptionEntryLocalService.
			fetchCommerceSubscriptionEntryByCommerceOrderItemId(
				commerceOrderItem.getCommerceOrderItemId());
	}

	public List<String> getFeatureNames(boolean active) {
		CommerceSubscriptionEntry commerceSubscriptionEntry =
			getCommerceSubscriptionEntry();

		if (commerceSubscriptionEntry == null) {
			return Collections.emptyList();
		}

		List<String> featureNames = new ArrayList<>();

		UnicodeProperties unicodeProperties =
			commerceSubscriptionEntry.getSubscriptionTypeSettingsProperties();

		for (String featureName :
				OSBCommercePortalInstanceConstants.PORTAL_INSTANCE_FEATURES) {

			if ((unicodeProperties.get(featureName) != null) && active) {
				featureNames.add(featureName);
			}
			else if ((unicodeProperties.get(featureName) == null) && !active) {
				featureNames.add(featureName);
			}
		}

		return featureNames;
	}

	private final long _commerceAccountId;
	private CommerceOrderItem _commerceOrderItem;
	private final CommerceOrderLocalService _commerceOrderLocalService;
	private final CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

}