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

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductPurchaseService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseService
 * @generated
 */
public class ProductPurchaseServiceWrapper
	implements ProductPurchaseService, ServiceWrapper<ProductPurchaseService> {

	public ProductPurchaseServiceWrapper(
		ProductPurchaseService productPurchaseService) {

		_productPurchaseService = productPurchaseService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductPurchaseServiceUtil} to access the product purchase remote service. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				long accountId, long productEntryId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.addProductPurchase(
			accountId, productEntryId, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				String accountKey, String productEntryKey,
				java.util.Date startDate, java.util.Date endDate,
				java.util.Date originalEndDate, int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.addProductPurchase(
			accountKey, productEntryKey, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.deleteProductPurchase(productPurchaseId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.deleteProductPurchase(
			productPurchaseKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getAccountProductPurchases(long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchases(
			accountId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getAccountProductPurchases(String accountKey, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchases(
			accountKey, start, end);
	}

	@Override
	public int getAccountProductPurchasesCount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchasesCount(
			accountId);
	}

	@Override
	public int getAccountProductPurchasesCount(String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchasesCount(
			accountKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getContactProductPurchases(long contactId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getContactProductPurchases(
			contactId, start, end);
	}

	@Override
	public int getContactProductPurchasesCount(long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getContactProductPurchasesCount(
			contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productPurchaseService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchase(productPurchaseId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchase(productPurchaseKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getProductPurchases(
				String domain, String entityName, String entityId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchases(
			domain, entityName, entityId, start, end);
	}

	@Override
	public int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchasesCount(
			domain, entityName, entityId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			updateProductPurchase(
				long productPurchaseId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.updateProductPurchase(
			productPurchaseId, startDate, endDate, originalEndDate, quantity,
			status, productFields);
	}

	@Override
	public ProductPurchaseService getWrappedService() {
		return _productPurchaseService;
	}

	@Override
	public void setWrappedService(
		ProductPurchaseService productPurchaseService) {

		_productPurchaseService = productPurchaseService;
	}

	private ProductPurchaseService _productPurchaseService;

}