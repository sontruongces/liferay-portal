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

package com.liferay.commerce.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceShipment. This utility wraps
 * {@link com.liferay.commerce.service.impl.CommerceShipmentServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShipmentService
 * @see com.liferay.commerce.service.base.CommerceShipmentServiceBaseImpl
 * @see com.liferay.commerce.service.impl.CommerceShipmentServiceImpl
 * @generated
 */
@ProviderType
public class CommerceShipmentServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.service.impl.CommerceShipmentServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.model.CommerceShipment addCommerceShipment(
		long shipmentUserId, long commerceAddressId,
		long commerceShippingMethodId, long commerceWarehouseId,
		String carrier, String trackingNumber, int expectedDuration,
		int status, int shippingDateMonth, int shippingDateDay,
		int shippingDateYear, int shippingDateHour, int shippingDateMinute,
		int expectedDateMonth, int expectedDateDay, int expectedDateYear,
		int expectedDateHour, int expectedDateMinute,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceShipment(shipmentUserId, commerceAddressId,
			commerceShippingMethodId, commerceWarehouseId, carrier,
			trackingNumber, expectedDuration, status, shippingDateMonth,
			shippingDateDay, shippingDateYear, shippingDateHour,
			shippingDateMinute, expectedDateMonth, expectedDateDay,
			expectedDateYear, expectedDateHour, expectedDateMinute,
			serviceContext);
	}

	public static void deleteCommerceShipment(long commerceShipmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceShipment(commerceShipmentId);
	}

	public static com.liferay.commerce.model.CommerceShipment getCommerceShipment(
		long commerceShipmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceShipment(commerceShipmentId);
	}

	public static java.util.List<com.liferay.commerce.model.CommerceShipment> getCommerceShipments(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceShipment> orderByComparator) {
		return getService()
				   .getCommerceShipments(groupId, start, end, orderByComparator);
	}

	public static int getCommerceShipmentsCount(long groupId) {
		return getService().getCommerceShipmentsCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.model.CommerceShipment updateCommerceShipment(
		long commerceShipmentId, long shipmentUserId, long commerceAddressId,
		long commerceShippingMethodId, String carrier, String trackingNumber,
		int expectedDuration, int status, int shippingDateMonth,
		int shippingDateDay, int shippingDateYear, int shippingDateHour,
		int shippingDateMinute, int expectedDateMonth, int expectedDateDay,
		int expectedDateYear, int expectedDateHour, int expectedDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceShipment(commerceShipmentId, shipmentUserId,
			commerceAddressId, commerceShippingMethodId, carrier,
			trackingNumber, expectedDuration, status, shippingDateMonth,
			shippingDateDay, shippingDateYear, shippingDateHour,
			shippingDateMinute, expectedDateMonth, expectedDateDay,
			expectedDateYear, expectedDateHour, expectedDateMinute);
	}

	public static CommerceShipmentService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceShipmentService, CommerceShipmentService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceShipmentService.class);

		ServiceTracker<CommerceShipmentService, CommerceShipmentService> serviceTracker =
			new ServiceTracker<CommerceShipmentService, CommerceShipmentService>(bundle.getBundleContext(),
				CommerceShipmentService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}