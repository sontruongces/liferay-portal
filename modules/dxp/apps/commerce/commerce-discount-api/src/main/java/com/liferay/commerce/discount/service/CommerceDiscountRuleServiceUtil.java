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

package com.liferay.commerce.discount.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceDiscountRule. This utility wraps
 * {@link com.liferay.commerce.discount.service.impl.CommerceDiscountRuleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceDiscountRuleService
 * @see com.liferay.commerce.discount.service.base.CommerceDiscountRuleServiceBaseImpl
 * @see com.liferay.commerce.discount.service.impl.CommerceDiscountRuleServiceImpl
 * @generated
 */
@ProviderType
public class CommerceDiscountRuleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.discount.service.impl.CommerceDiscountRuleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.discount.model.CommerceDiscountRule addCommerceDiscountRule(
		long commerceDiscountId, String type, String typeSettings,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceDiscountRule(commerceDiscountId, type,
			typeSettings, serviceContext);
	}

	public static void deleteCommerceDiscountRule(long commerceDiscountRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceDiscountRule(commerceDiscountRuleId);
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountRule getCommerceDiscountRule(
		long commerceDiscountRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceDiscountRule(commerceDiscountRuleId);
	}

	public static java.util.List<com.liferay.commerce.discount.model.CommerceDiscountRule> getCommerceDiscountRules(
		long commerceDiscountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.discount.model.CommerceDiscountRule> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getCommerceDiscountRules(commerceDiscountId, start, end,
			orderByComparator);
	}

	public static int getCommerceDiscountRulesCount(long commerceDiscountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceDiscountRulesCount(commerceDiscountId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.discount.model.CommerceDiscountRule updateCommerceDiscountRule(
		long commerceDiscountRuleId, String type, String typeSettings)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceDiscountRule(commerceDiscountRuleId, type,
			typeSettings);
	}

	public static CommerceDiscountRuleService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceDiscountRuleService, CommerceDiscountRuleService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceDiscountRuleService.class);

		ServiceTracker<CommerceDiscountRuleService, CommerceDiscountRuleService> serviceTracker =
			new ServiceTracker<CommerceDiscountRuleService, CommerceDiscountRuleService>(bundle.getBundleContext(),
				CommerceDiscountRuleService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}