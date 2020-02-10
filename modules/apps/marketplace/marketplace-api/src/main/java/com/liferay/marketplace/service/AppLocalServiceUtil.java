/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.marketplace.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for App. This utility wraps
 * <code>com.liferay.marketplace.service.impl.AppLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ryan Park
 * @see AppLocalService
 * @generated
 */
public class AppLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.marketplace.service.impl.AppLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the app to the database. Also notifies the appropriate model listeners.
	 *
	 * @param app the app
	 * @return the app that was added
	 */
	public static com.liferay.marketplace.model.App addApp(
		com.liferay.marketplace.model.App app) {

		return getService().addApp(app);
	}

	public static void clearInstalledAppsCache() {
		getService().clearInstalledAppsCache();
	}

	/**
	 * Creates a new app with the primary key. Does not add the app to the database.
	 *
	 * @param appId the primary key for the new app
	 * @return the new app
	 */
	public static com.liferay.marketplace.model.App createApp(long appId) {
		return getService().createApp(appId);
	}

	/**
	 * Deletes the app from the database. Also notifies the appropriate model listeners.
	 *
	 * @param app the app
	 * @return the app that was removed
	 */
	public static com.liferay.marketplace.model.App deleteApp(
		com.liferay.marketplace.model.App app) {

		return getService().deleteApp(app);
	}

	/**
	 * Deletes the app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param appId the primary key of the app
	 * @return the app that was removed
	 * @throws PortalException if a app with the primary key could not be found
	 */
	public static com.liferay.marketplace.model.App deleteApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteApp(appId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.marketplace.model.impl.AppModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.marketplace.model.impl.AppModelImpl</code>.
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

	public static com.liferay.marketplace.model.App fetchApp(long appId) {
		return getService().fetchApp(appId);
	}

	/**
	 * Returns the app with the matching UUID and company.
	 *
	 * @param uuid the app's UUID
	 * @param companyId the primary key of the company
	 * @return the matching app, or <code>null</code> if a matching app could not be found
	 */
	public static com.liferay.marketplace.model.App fetchAppByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchAppByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.marketplace.model.App fetchRemoteApp(
		long remoteAppId) {

		return getService().fetchRemoteApp(remoteAppId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the app with the primary key.
	 *
	 * @param appId the primary key of the app
	 * @return the app
	 * @throws PortalException if a app with the primary key could not be found
	 */
	public static com.liferay.marketplace.model.App getApp(long appId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getApp(appId);
	}

	/**
	 * Returns the app with the matching UUID and company.
	 *
	 * @param uuid the app's UUID
	 * @param companyId the primary key of the company
	 * @return the matching app
	 * @throws PortalException if a matching app could not be found
	 */
	public static com.liferay.marketplace.model.App getAppByUuidAndCompanyId(
			String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAppByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.marketplace.model.impl.AppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of apps
	 * @param end the upper bound of the range of apps (not inclusive)
	 * @return the range of apps
	 */
	public static java.util.List<com.liferay.marketplace.model.App> getApps(
		int start, int end) {

		return getService().getApps(start, end);
	}

	public static java.util.List<com.liferay.marketplace.model.App> getApps(
		String category) {

		return getService().getApps(category);
	}

	/**
	 * Returns the number of apps.
	 *
	 * @return the number of apps
	 */
	public static int getAppsCount() {
		return getService().getAppsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.marketplace.model.App>
		getInstalledApps() {

		return getService().getInstalledApps();
	}

	public static java.util.List<com.liferay.marketplace.model.App>
		getInstalledApps(String category) {

		return getService().getInstalledApps(category);
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

	public static java.util.Map<String, String> getPrepackagedApps() {
		return getService().getPrepackagedApps();
	}

	public static void installApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().installApp(remoteAppId);
	}

	public static void uninstallApp(long remoteAppId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().uninstallApp(remoteAppId);
	}

	/**
	 * Updates the app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param app the app
	 * @return the app that was updated
	 */
	public static com.liferay.marketplace.model.App updateApp(
		com.liferay.marketplace.model.App app) {

		return getService().updateApp(app);
	}

	public static com.liferay.marketplace.model.App updateApp(
			long userId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateApp(userId, file);
	}

	public static com.liferay.marketplace.model.App updateApp(
			long userId, long remoteAppId, String title, String description,
			String category, String iconURL, String version, boolean required,
			java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateApp(
			userId, remoteAppId, title, description, category, iconURL, version,
			required, file);
	}

	public static AppLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AppLocalService, AppLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AppLocalService.class);

		ServiceTracker<AppLocalService, AppLocalService> serviceTracker =
			new ServiceTracker<AppLocalService, AppLocalService>(
				bundle.getBundleContext(), AppLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}