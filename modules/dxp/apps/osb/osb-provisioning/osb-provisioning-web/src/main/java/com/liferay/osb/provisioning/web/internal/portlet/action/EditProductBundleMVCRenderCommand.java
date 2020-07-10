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
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.service.ProductBundleProductsLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
	service = MVCRenderCommand.class
)
public class EditProductBundleMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long productBundleId = ParamUtil.getLong(
				renderRequest, "productBundleId");

			if (productBundleId != 0) {
				renderRequest.setAttribute(
					ProvisioningWebKeys.PRODUCT_BUNDLE,
					_productBundleLocalService.getProductBundle(
						productBundleId));

				renderRequest.setAttribute(
					ProvisioningWebKeys.PRODUCT_BUNDLE_PRODUCTS,
					_productBundleProductsLocalService.
						getProductBundleAssignedProducts(productBundleId));
			}

			return "/product_bundles/edit_product_bundle.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			_log.error(exception, exception);

			return "/common/error.jsp";
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditProductBundleMVCRenderCommand.class);

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

}