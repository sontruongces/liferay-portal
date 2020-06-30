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

import com.liferay.osb.provisioning.exception.NoSuchProductBundleProductEntriesException;
import com.liferay.osb.provisioning.model.ProductBundleProductEntries;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product bundle product entries service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductEntriesUtil
 * @generated
 */
@ProviderType
public interface ProductBundleProductEntriesPersistence
	extends BasePersistence<ProductBundleProductEntries> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductBundleProductEntriesUtil} to access the product bundle product entries persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or throws a <code>NoSuchProductBundleProductEntriesException</code> if it could not be found.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a matching product bundle product entries could not be found
	 */
	public ProductBundleProductEntries findByProductBundleId(
			long productBundleId)
		throws NoSuchProductBundleProductEntriesException;

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle product entries, or <code>null</code> if a matching product bundle product entries could not be found
	 */
	public ProductBundleProductEntries fetchByProductBundleId(
		long productBundleId);

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle product entries, or <code>null</code> if a matching product bundle product entries could not be found
	 */
	public ProductBundleProductEntries fetchByProductBundleId(
		long productBundleId, boolean useFinderCache);

	/**
	 * Removes the product bundle product entries where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the product bundle product entries that was removed
	 */
	public ProductBundleProductEntries removeByProductBundleId(
			long productBundleId)
		throws NoSuchProductBundleProductEntriesException;

	/**
	 * Returns the number of product bundle product entrieses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle product entrieses
	 */
	public int countByProductBundleId(long productBundleId);

	/**
	 * Caches the product bundle product entries in the entity cache if it is enabled.
	 *
	 * @param productBundleProductEntries the product bundle product entries
	 */
	public void cacheResult(
		ProductBundleProductEntries productBundleProductEntries);

	/**
	 * Caches the product bundle product entrieses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductEntrieses the product bundle product entrieses
	 */
	public void cacheResult(
		java.util.List<ProductBundleProductEntries>
			productBundleProductEntrieses);

	/**
	 * Creates a new product bundle product entries with the primary key. Does not add the product bundle product entries to the database.
	 *
	 * @param productBundleProductEntriesPK the primary key for the new product bundle product entries
	 * @return the new product bundle product entries
	 */
	public ProductBundleProductEntries create(
		ProductBundleProductEntriesPK productBundleProductEntriesPK);

	/**
	 * Removes the product bundle product entries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries that was removed
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	public ProductBundleProductEntries remove(
			ProductBundleProductEntriesPK productBundleProductEntriesPK)
		throws NoSuchProductBundleProductEntriesException;

	public ProductBundleProductEntries updateImpl(
		ProductBundleProductEntries productBundleProductEntries);

	/**
	 * Returns the product bundle product entries with the primary key or throws a <code>NoSuchProductBundleProductEntriesException</code> if it could not be found.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	public ProductBundleProductEntries findByPrimaryKey(
			ProductBundleProductEntriesPK productBundleProductEntriesPK)
		throws NoSuchProductBundleProductEntriesException;

	/**
	 * Returns the product bundle product entries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries, or <code>null</code> if a product bundle product entries with the primary key could not be found
	 */
	public ProductBundleProductEntries fetchByPrimaryKey(
		ProductBundleProductEntriesPK productBundleProductEntriesPK);

	/**
	 * Returns all the product bundle product entrieses.
	 *
	 * @return the product bundle product entrieses
	 */
	public java.util.List<ProductBundleProductEntries> findAll();

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
	public java.util.List<ProductBundleProductEntries> findAll(
		int start, int end);

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
	public java.util.List<ProductBundleProductEntries> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ProductBundleProductEntries> orderByComparator);

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
	public java.util.List<ProductBundleProductEntries> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ProductBundleProductEntries> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product bundle product entrieses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product bundle product entrieses.
	 *
	 * @return the number of product bundle product entrieses
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}