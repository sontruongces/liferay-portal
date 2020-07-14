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

import com.liferay.osb.provisioning.exception.ProductBundleNameException;
import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.osb.provisioning.service.base.ProductBundleLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = "model.class.name=com.liferay.osb.provisioning.model.ProductBundle",
	service = AopService.class
)
public class ProductBundleLocalServiceImpl
	extends ProductBundleLocalServiceBaseImpl {

	public ProductBundle addProductBundle(long userId, String name)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, name);

		long productBundleId = counterLocalService.increment();

		ProductBundle productBundle = productBundlePersistence.create(
			productBundleId);

		productBundle.setCompanyId(user.getCompanyId());
		productBundle.setUserId(userId);
		productBundle.setName(name);

		return productBundlePersistence.update(productBundle);
	}

	protected void validate(long productBundleId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new ProductBundleNameException();
		}

		ProductBundle productBundle = productBundlePersistence.fetchByName(
			name);

		if ((productBundle != null) &&
			(productBundle.getProductBundleId() != productBundleId)) {

			throw new ProductBundleNameException.MustNotBeDuplicate(name);
		}
	}

}