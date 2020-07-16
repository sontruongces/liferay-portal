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

package com.liferay.osb.koroneiki.phytohormone.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Entitlement. This utility wraps
 * <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementLocalService
 * @generated
 */
public class EntitlementLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the entitlement to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlement the entitlement
	 * @return the entitlement that was added
	 */
	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		addEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement) {

		return getService().addEntitlement(entitlement);
	}

	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
			addEntitlement(
				long userId, long entitlementDefinitionId, long classNameId,
				long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addEntitlement(
			userId, entitlementDefinitionId, classNameId, classPK);
	}

	/**
	 * Creates a new entitlement with the primary key. Does not add the entitlement to the database.
	 *
	 * @param entitlementId the primary key for the new entitlement
	 * @return the new entitlement
	 */
	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		createEntitlement(long entitlementId) {

		return getService().createEntitlement(entitlementId);
	}

	/**
	 * Deletes the entitlement from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlement the entitlement
	 * @return the entitlement that was removed
	 */
	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		deleteEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement) {

		return getService().deleteEntitlement(entitlement);
	}

	/**
	 * Deletes the entitlement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement that was removed
	 * @throws PortalException if a entitlement with the primary key could not be found
	 */
	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
			deleteEntitlement(long entitlementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteEntitlement(entitlementId);
	}

	public static void deleteEntitlements(long classNameId, long classPK) {
		getService().deleteEntitlements(classNameId, classPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		fetchEntitlement(long entitlementId) {

		return getService().fetchEntitlement(entitlementId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the entitlement with the primary key.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement
	 * @throws PortalException if a entitlement with the primary key could not be found
	 */
	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
			getEntitlement(long entitlementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEntitlement(entitlementId);
	}

	/**
	 * Returns a range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of entitlements
	 */
	public static java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements(int start, int end) {

		return getService().getEntitlements(start, end);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements(
				long classNameId, long classPK, int start, int end) {

		return getService().getEntitlements(classNameId, classPK, start, end);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements(
				String className, long classPK, int start, int end) {

		return getService().getEntitlements(className, classPK, start, end);
	}

	/**
	 * Returns the number of entitlements.
	 *
	 * @return the number of entitlements
	 */
	public static int getEntitlementsCount() {
		return getService().getEntitlementsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the entitlement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect EntitlementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param entitlement the entitlement
	 * @return the entitlement that was updated
	 */
	public static com.liferay.osb.koroneiki.phytohormone.model.Entitlement
		updateEntitlement(
			com.liferay.osb.koroneiki.phytohormone.model.Entitlement
				entitlement) {

		return getService().updateEntitlement(entitlement);
	}

	public static EntitlementLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<EntitlementLocalService, EntitlementLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EntitlementLocalService.class);

		ServiceTracker<EntitlementLocalService, EntitlementLocalService>
			serviceTracker =
				new ServiceTracker
					<EntitlementLocalService, EntitlementLocalService>(
						bundle.getBundleContext(),
						EntitlementLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}