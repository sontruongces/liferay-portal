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

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.exception.ProductBundleNameException;
import com.liferay.osb.provisioning.exception.RequiredProductException;
import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.service.ProductBundleProductsLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PRODUCT_BUNDLES,
		"mvc.command.name=/product_bundles/edit_product_bundle"
	},
	service = MVCActionCommand.class
)
public class EditProductBundleMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteProductBundle(ActionRequest actionRequest)
		throws Exception {

		long productBundleId = ParamUtil.getLong(
			actionRequest, "productBundleId");

		_productBundleLocalService.deleteProductBundle(productBundleId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.DELETE)) {
				deleteProductBundle(actionRequest);
			}
			else {
				updateProductBundle(actionRequest, themeDisplay.getUser());
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof ProductBundleNameException ||
				exception instanceof RequiredProductException) {

				SessionErrors.add(
					actionRequest, exception.getClass(), exception);
			}
			else {
				_log.error(exception, exception);

				throw exception;
			}
		}
	}

	protected void updateProductBundle(ActionRequest actionRequest, User user)
		throws Exception {

		String[] productKeys = ParamUtil.getStringValues(
			actionRequest, "productKeys");

		if (ArrayUtil.isEmpty(productKeys)) {
			throw new RequiredProductException();
		}

		long productBundleId = ParamUtil.getLong(
			actionRequest, "productBundleId");

		String name = ParamUtil.getString(actionRequest, "name");

		if (productBundleId == 0) {
			ProductBundle productBundle =
				_productBundleLocalService.addProductBundle(
					user.getUserId(), name);

			productBundleId = productBundle.getProductBundleId();
		}
		else {
			_productBundleLocalService.updateProductBundle(
				productBundleId, name);
		}

		_updateProductBundleProducts(productBundleId, productKeys);
	}

	private void _updateProductBundleProducts(
			long productBundleId, String[] productKeys)
		throws Exception {

		List<ProductBundleProducts> oldProductBundleProducts =
			_productBundleProductsLocalService.getProductBundleProducts(
				productBundleId);

		for (ProductBundleProducts productBundleProduct :
				oldProductBundleProducts) {

			String productKey = productBundleProduct.getProductKey();

			if (!ArrayUtil.contains(productKeys, productKey)) {
				_productBundleProductsLocalService.deleteProductBundleProducts(
					productBundleId, productKey);
			}
		}

		for (String productKey : productKeys) {
			_productBundleProductsLocalService.addProductBundleProducts(
				productBundleId, productKey);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductBundleMVCActionCommand.class);

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

}