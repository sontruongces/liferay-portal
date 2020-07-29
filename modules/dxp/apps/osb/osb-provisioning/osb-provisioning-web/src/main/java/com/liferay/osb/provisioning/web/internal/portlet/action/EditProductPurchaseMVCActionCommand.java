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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/edit_product_purchase"
	},
	service = MVCActionCommand.class
)
public class EditProductPurchaseMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateProductPurchase(actionRequest);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (HttpException httpException) {
			SessionErrors.add(
				actionRequest, httpException.getClass(), httpException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateProductPurchase(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String productPurchaseKey = ParamUtil.getString(
			actionRequest, "productPurchaseKey");

		boolean perpetual = ParamUtil.getBoolean(actionRequest, "perpetual");
		int quantity = ParamUtil.getInteger(actionRequest, "purchased");
		int sizing = ParamUtil.getInteger(actionRequest, "instanceSize");
		String status = ParamUtil.getString(actionRequest, "status");

		Map<String, String> properties = new HashMap<>();

		ProductPurchase curProductPurchase =
			_productPurchaseWebService.getProductPurchase(productPurchaseKey);

		if (curProductPurchase != null) {
			Map<String, String> curProperties =
				curProductPurchase.getProperties();

			if (curProperties != null) {
				for (Map.Entry<String, String> entry :
						curProperties.entrySet()) {

					properties.put(entry.getKey(), entry.getValue());
				}
			}
		}

		properties.put("sizing", String.valueOf(sizing));

		ProductPurchase productPurchase = new ProductPurchase();

		productPurchase.setQuantity(quantity);
		productPurchase.setProperties(properties);
		productPurchase.setStatus(ProductPurchase.Status.create(status));

		if (perpetual) {
			productPurchase.setPerpetual(perpetual);
		}
		else {
			int startDateMonth = ParamUtil.getInteger(
				actionRequest, "startDateMonth");
			int startDateDay = ParamUtil.getInteger(
				actionRequest, "startDateDay");
			int startDateYear = ParamUtil.getInteger(
				actionRequest, "startDateYear");

			Date startDate = _portal.getDate(
				startDateMonth, startDateDay, startDateYear,
				themeDisplay.getTimeZone(), null);

			int endDateMonth = ParamUtil.getInteger(
				actionRequest, "endDateMonth");
			int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
			int endDateYear = ParamUtil.getInteger(
				actionRequest, "endDateYear");

			Date endDate = _portal.getDate(
				endDateMonth, endDateDay, endDateYear,
				themeDisplay.getTimeZone(), null);

			int gracePeriodEndDateMonth = ParamUtil.getInteger(
				actionRequest, "gracePeriodEndDateMonth");
			int gracePeriodEndDateDay = ParamUtil.getInteger(
				actionRequest, "gracePeriodEndDateDay");
			int gracePeriodEndDateYear = ParamUtil.getInteger(
				actionRequest, "gracePeriodEndDateYear");

			Date originalEndDate = _portal.getDate(
				gracePeriodEndDateMonth, gracePeriodEndDateDay,
				gracePeriodEndDateYear, themeDisplay.getTimeZone(), null);

			productPurchase.setStartDate(startDate);
			productPurchase.setEndDate(endDate);
			productPurchase.setOriginalEndDate(originalEndDate);
		}

		_productPurchaseWebService.updateProductPurchase(
			user.getFullName(), StringPool.BLANK, productPurchaseKey,
			productPurchase);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductPurchaseMVCActionCommand.class);

	@Reference
	private Portal _portal;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

}