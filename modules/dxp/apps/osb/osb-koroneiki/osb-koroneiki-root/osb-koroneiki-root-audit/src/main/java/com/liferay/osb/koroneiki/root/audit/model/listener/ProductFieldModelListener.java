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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ProductFieldModelListener
	extends BaseAuditModelListener<ProductField> {

	@Override
	public void onAfterCreate(ProductField productField)
		throws ModelListenerException {

		try {
			long accountId = _getAccountId(productField);

			ServiceContext serviceContext = getServiceContext(
				productField.getClassNameId(), productField.getClassPK());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class), accountId,
				productField.getClassNameId(), productField.getClassPK(),
				AuditEntry.Action.ADD.toString(), productField.getName(),
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				productField.getValue(), getDescription(productField),
				serviceContext);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeRemove(ProductField productField)
		throws ModelListenerException {

		try {
			long accountId = _getAccountId(productField);

			ServiceContext serviceContext = getServiceContext(
				productField.getClassNameId(), productField.getClassPK());

			auditEntryLocalService.addAuditEntry(
				getUserId(),
				classNameLocalService.getClassNameId(Account.class), accountId,
				productField.getClassNameId(), productField.getClassPK(),
				AuditEntry.Action.DELETE.toString(), productField.getName(),
				StringPool.BLANK, productField.getValue(), StringPool.BLANK,
				StringPool.BLANK, getDescription(productField), serviceContext);
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	public void onBeforeUpdate(ProductField productField)
		throws ModelListenerException {

		try {
			ProductField oldProductField =
				_productFieldLocalService.getProductField(
					productField.getProductFieldId());

			String oldValue = oldProductField.getValue();

			if (!Objects.equals(oldValue, productField.getValue())) {
				long accountId = _getAccountId(productField);

				ServiceContext serviceContext = getServiceContext(
					productField.getClassNameId(), productField.getClassPK());

				auditEntryLocalService.addAuditEntry(
					getUserId(),
					classNameLocalService.getClassNameId(Account.class),
					accountId, productField.getClassNameId(),
					productField.getClassPK(),
					AuditEntry.Action.UPDATE.toString(), productField.getName(),
					StringPool.BLANK, oldValue, StringPool.BLANK,
					productField.getValue(), getDescription(productField),
					serviceContext);
			}
		}
		catch (PortalException portalException) {
			throw new ModelListenerException(portalException);
		}
	}

	@Override
	protected String getDescription(ProductField productField)
		throws PortalException {

		ProductEntry productEntry = null;

		if (productField.getClassNameId() ==
				classNameLocalService.getClassNameId(
					ProductConsumption.class)) {

			ProductConsumption productConsumption =
				_productConsumptionLocalService.getProductConsumption(
					productField.getClassPK());

			productEntry = productConsumption.getProductEntry();
		}
		else {
			ProductPurchase productPurchase =
				_productPurchaseLocalService.getProductPurchase(
					productField.getClassPK());

			productEntry = productPurchase.getProductEntry();
		}

		return productEntry.getName();
	}

	private long _getAccountId(ProductField productField)
		throws PortalException {

		if (productField.getClassNameId() ==
				classNameLocalService.getClassNameId(
					ProductConsumption.class)) {

			ProductConsumption productConsumption =
				_productConsumptionLocalService.getProductConsumption(
					productField.getClassPK());

			return productConsumption.getAccountId();
		}

		ProductPurchase productPurchase =
			_productPurchaseLocalService.getProductPurchase(
				productField.getClassPK());

		return productPurchase.getAccountId();
	}

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}