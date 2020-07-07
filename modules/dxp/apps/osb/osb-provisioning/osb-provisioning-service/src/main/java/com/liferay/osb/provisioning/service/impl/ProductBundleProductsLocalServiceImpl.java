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

package com.liferay.osb.provisioning.service.impl;

import com.liferay.osb.provisioning.exception.RequiredProductException;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductWebService;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.service.ProductBundleLocalService;
import com.liferay.osb.provisioning.service.base.ProductBundleProductsLocalServiceBaseImpl;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Yuanyuan Huang
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.model.ProductBundleProducts",
	service = AopService.class
)
public class ProductBundleProductsLocalServiceImpl
	extends ProductBundleProductsLocalServiceBaseImpl {

	public ProductBundleProducts addProductBundleProducts(
			long productBundleId, String productKey)
		throws Exception {

		validate(productBundleId, productKey);

		ProductBundleProductsPK productBundleProductsPK =
			new ProductBundleProductsPK(productBundleId, productKey);

		ProductBundleProducts productBundleProducts =
			productBundleProductsPersistence.fetchByPrimaryKey(
				productBundleProductsPK);

		if (productBundleProducts == null) {
			productBundleProducts = productBundleProductsPersistence.create(
				productBundleProductsPK);

			productBundleProducts = productBundleProductsPersistence.update(
				productBundleProducts);
		}

		return productBundleProducts;
	}

	public int getProductBundleProductsCount(String productKey) {
		return productBundleProductsPersistence.countByProductKey(productKey);
	}

	protected void validate(long productBundleId, String productKey)
		throws Exception {

		if (Validator.isNull(productKey)) {
			throw new RequiredProductException();
		}

		_productBundleLocalService.getProductBundle(productBundleId);

		_productWebService.getProduct(productKey);
	}

	@Reference
	private ProductBundleLocalService _productBundleLocalService;

	@Reference
	private ProductWebService _productWebService;

}