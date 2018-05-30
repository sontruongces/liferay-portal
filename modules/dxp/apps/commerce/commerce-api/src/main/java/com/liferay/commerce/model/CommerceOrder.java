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

package com.liferay.commerce.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceOrder service. Represents a row in the &quot;CommerceOrder&quot; database table, with each column mapped to a property of this class.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderModel
 * @see com.liferay.commerce.model.impl.CommerceOrderImpl
 * @see com.liferay.commerce.model.impl.CommerceOrderModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.model.impl.CommerceOrderImpl")
@ProviderType
public interface CommerceOrder extends CommerceOrderModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.model.impl.CommerceOrderImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceOrder, Long> COMMERCE_ORDER_ID_ACCESSOR =
		new Accessor<CommerceOrder, Long>() {
			@Override
			public Long get(CommerceOrder commerceOrder) {
				return commerceOrder.getCommerceOrderId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CommerceOrder> getTypeClass() {
				return CommerceOrder.class;
			}
		};

	public CommerceAddress getBillingAddress()
		throws com.liferay.portal.kernel.exception.PortalException;

	public String getClassName()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getClassPK()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.commerce.currency.model.CommerceCurrency getCommerceCurrency()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<CommerceOrderItem> getCommerceOrderItems();

	public CommercePaymentMethod getCommercePaymentMethod()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommerceShippingMethod getCommerceShippingMethod()
		throws com.liferay.portal.kernel.exception.PortalException;

	public long getCustomerId();

	public String getCustomerName()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.kernel.model.Organization getOrderOrganization()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.portal.kernel.model.User getOrderUser()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommerceAddress getShippingAddress()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.commerce.currency.model.CommerceMoney getShippingMoney()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.commerce.currency.model.CommerceMoney getSubTotalMoney()
		throws com.liferay.portal.kernel.exception.PortalException;

	public com.liferay.commerce.currency.model.CommerceMoney getTotalMoney()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isB2B()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isEmpty();

	public boolean isGuestOrder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isOpen();
}