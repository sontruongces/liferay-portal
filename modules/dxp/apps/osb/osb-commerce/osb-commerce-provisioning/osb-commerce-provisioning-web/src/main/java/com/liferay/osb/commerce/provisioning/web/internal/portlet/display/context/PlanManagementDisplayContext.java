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

package com.liferay.osb.commerce.provisioning.web.internal.portlet.display.context;

import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.osb.commerce.provisioning.constants.OSBCommercePortalInstanceConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Gianmarco Brunialti Masera
 */
public class PlanManagementDisplayContext {

	public PlanManagementDisplayContext(
		long commerceAccountId,
		CommerceOrderLocalService commerceOrderLocalService,
		CommerceSubscriptionEntryLocalService
			commerceSubscriptionEntryLocalService,
		Portal portal, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		_commerceAccountId = commerceAccountId;
		_commerceOrderLocalService = commerceOrderLocalService;
		_commerceSubscriptionEntryLocalService =
			commerceSubscriptionEntryLocalService;
		_portal = portal;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;
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

	public Map<String, Object> getPlanManagementData(ThemeDisplay themeDisplay)
		throws PortalException {

		return new HashMap<String, Object>() {
			{
				put("activePlan", _getActivePlanData());
				put("planFeatures", _getPlanFeaturesData());
				put("spritemap", _getSpritemap(themeDisplay));
			}
		};
	}

	private Map<String, Object> _getActivePlanData() throws PortalException {
		Map<String, Object> activePlanData = new HashMap<>();

		CommerceSubscriptionEntry commerceSubscriptionEntry =
			getCommerceSubscriptionEntry();

		if (commerceSubscriptionEntry != null) {
			activePlanData.put(
				"cancelPlanURL",
				_getCancelSubscriptionURL(commerceSubscriptionEntry));
			activePlanData.put(
				"endDate", commerceSubscriptionEntry.getLastIterationDate());
			activePlanData.put(
				"startDate", commerceSubscriptionEntry.getStartDate());
		}

		CPInstance cpInstance;

		try {
			cpInstance = _commerceOrderItem.fetchCPInstance();
		}
		catch (Exception exception) {
			cpInstance = null;
		}

		if (cpInstance != null) {
			CPDefinition cpDefinition = cpInstance.getCPDefinition();

			activePlanData.put("planName", cpDefinition.getName());

			activePlanData.put("price", cpInstance.getPrice());
		}

		return activePlanData;
	}

	private String _getCancelSubscriptionURL(
		CommerceSubscriptionEntry commerceSubscriptionEntry) {

		PortletURL portletURL = _renderResponse.createActionURL();

		portletURL.setParameter(ActionRequest.ACTION_NAME, "editPlan");
		portletURL.setParameter(Constants.CMD, Constants.DELETE);
		portletURL.setParameter(
			"commerceSubscriptionEntryId",
			String.valueOf(
				commerceSubscriptionEntry.getCommerceSubscriptionEntryId()));

		String redirect = _portal.getCurrentURL(_renderRequest);

		portletURL.setParameter("redirect", redirect);

		return portletURL.toString();
	}

	private Map<String, Object> _getPlanFeaturesData() {
		return new HashMap<String, Object>() {
			{
				put("activeFeatures", getFeatureNames(true));
				put("inactiveFeatures", getFeatureNames(false));
			}
		};
	}

	private String _getSpritemap(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/lexicon/icons.svg";
	}

	private final long _commerceAccountId;
	private CommerceOrderItem _commerceOrderItem;
	private final CommerceOrderLocalService _commerceOrderLocalService;
	private final CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;
	private final Portal _portal;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}