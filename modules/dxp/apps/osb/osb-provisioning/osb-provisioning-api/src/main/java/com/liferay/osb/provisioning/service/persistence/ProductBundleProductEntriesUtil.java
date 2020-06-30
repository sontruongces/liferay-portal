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

package com.liferay.osb.provisioning.service.persistence;

import com.liferay.osb.provisioning.model.ProductBundleProductEntries;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the product bundle product entries service. This utility wraps <code>com.liferay.osb.provisioning.service.persistence.impl.ProductBundleProductEntriesPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductEntriesPersistence
 * @generated
 */
public class ProductBundleProductEntriesUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		ProductBundleProductEntries productBundleProductEntries) {

		getPersistence().clearCache(productBundleProductEntries);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ProductBundleProductEntries>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductBundleProductEntries> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductBundleProductEntries> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductBundleProductEntries> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductBundleProductEntries> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductBundleProductEntries update(
		ProductBundleProductEntries productBundleProductEntries) {

		return getPersistence().update(productBundleProductEntries);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductBundleProductEntries update(
		ProductBundleProductEntries productBundleProductEntries,
		ServiceContext serviceContext) {

		return getPersistence().update(
			productBundleProductEntries, serviceContext);
	}

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or throws a <code>NoSuchProductBundleProductEntriesException</code> if it could not be found.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a matching product bundle product entries could not be found
	 */
	public static ProductBundleProductEntries findByProductBundleId(
			long productBundleId)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductEntriesException {

		return getPersistence().findByProductBundleId(productBundleId);
	}

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle product entries, or <code>null</code> if a matching product bundle product entries could not be found
	 */
	public static ProductBundleProductEntries fetchByProductBundleId(
		long productBundleId) {

		return getPersistence().fetchByProductBundleId(productBundleId);
	}

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle product entries, or <code>null</code> if a matching product bundle product entries could not be found
	 */
	public static ProductBundleProductEntries fetchByProductBundleId(
		long productBundleId, boolean useFinderCache) {

		return getPersistence().fetchByProductBundleId(
			productBundleId, useFinderCache);
	}

	/**
	 * Removes the product bundle product entries where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the product bundle product entries that was removed
	 */
	public static ProductBundleProductEntries removeByProductBundleId(
			long productBundleId)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductEntriesException {

		return getPersistence().removeByProductBundleId(productBundleId);
	}

	/**
	 * Returns the number of product bundle product entrieses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle product entrieses
	 */
	public static int countByProductBundleId(long productBundleId) {
		return getPersistence().countByProductBundleId(productBundleId);
	}

	/**
	 * Caches the product bundle product entries in the entity cache if it is enabled.
	 *
	 * @param productBundleProductEntries the product bundle product entries
	 */
	public static void cacheResult(
		ProductBundleProductEntries productBundleProductEntries) {

		getPersistence().cacheResult(productBundleProductEntries);
	}

	/**
	 * Caches the product bundle product entrieses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductEntrieses the product bundle product entrieses
	 */
	public static void cacheResult(
		List<ProductBundleProductEntries> productBundleProductEntrieses) {

		getPersistence().cacheResult(productBundleProductEntrieses);
	}

	/**
	 * Creates a new product bundle product entries with the primary key. Does not add the product bundle product entries to the database.
	 *
	 * @param productBundleProductEntriesPK the primary key for the new product bundle product entries
	 * @return the new product bundle product entries
	 */
	public static ProductBundleProductEntries create(
		ProductBundleProductEntriesPK productBundleProductEntriesPK) {

		return getPersistence().create(productBundleProductEntriesPK);
	}

	/**
	 * Removes the product bundle product entries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries that was removed
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	public static ProductBundleProductEntries remove(
			ProductBundleProductEntriesPK productBundleProductEntriesPK)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductEntriesException {

		return getPersistence().remove(productBundleProductEntriesPK);
	}

	public static ProductBundleProductEntries updateImpl(
		ProductBundleProductEntries productBundleProductEntries) {

		return getPersistence().updateImpl(productBundleProductEntries);
	}

	/**
	 * Returns the product bundle product entries with the primary key or throws a <code>NoSuchProductBundleProductEntriesException</code> if it could not be found.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	public static ProductBundleProductEntries findByPrimaryKey(
			ProductBundleProductEntriesPK productBundleProductEntriesPK)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleProductEntriesException {

		return getPersistence().findByPrimaryKey(productBundleProductEntriesPK);
	}

	/**
	 * Returns the product bundle product entries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries, or <code>null</code> if a product bundle product entries with the primary key could not be found
	 */
	public static ProductBundleProductEntries fetchByPrimaryKey(
		ProductBundleProductEntriesPK productBundleProductEntriesPK) {

		return getPersistence().fetchByPrimaryKey(
			productBundleProductEntriesPK);
	}

	/**
	 * Returns all the product bundle product entrieses.
	 *
	 * @return the product bundle product entrieses
	 */
	public static List<ProductBundleProductEntries> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product bundle product entrieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductEntriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle product entrieses
	 * @param end the upper bound of the range of product bundle product entrieses (not inclusive)
	 * @return the range of product bundle product entrieses
	 */
	public static List<ProductBundleProductEntries> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product bundle product entrieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductEntriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle product entrieses
	 * @param end the upper bound of the range of product bundle product entrieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product bundle product entrieses
	 */
	public static List<ProductBundleProductEntries> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProductEntries> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product bundle product entrieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductEntriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle product entrieses
	 * @param end the upper bound of the range of product bundle product entrieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product bundle product entrieses
	 */
	public static List<ProductBundleProductEntries> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProductEntries> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product bundle product entrieses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product bundle product entrieses.
	 *
	 * @return the number of product bundle product entrieses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ProductBundleProductEntriesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ProductBundleProductEntriesPersistence,
		 ProductBundleProductEntriesPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ProductBundleProductEntriesPersistence.class);

		ServiceTracker
			<ProductBundleProductEntriesPersistence,
			 ProductBundleProductEntriesPersistence> serviceTracker =
				new ServiceTracker
					<ProductBundleProductEntriesPersistence,
					 ProductBundleProductEntriesPersistence>(
						 bundle.getBundleContext(),
						 ProductBundleProductEntriesPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}