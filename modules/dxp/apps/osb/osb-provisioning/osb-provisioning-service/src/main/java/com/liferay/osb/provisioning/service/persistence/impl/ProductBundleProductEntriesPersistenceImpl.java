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

package com.liferay.osb.provisioning.service.persistence.impl;

import com.liferay.osb.provisioning.exception.NoSuchProductBundleProductEntriesException;
import com.liferay.osb.provisioning.model.ProductBundleProductEntries;
import com.liferay.osb.provisioning.model.impl.ProductBundleProductEntriesImpl;
import com.liferay.osb.provisioning.model.impl.ProductBundleProductEntriesModelImpl;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductEntriesPK;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductEntriesPersistence;
import com.liferay.osb.provisioning.service.persistence.impl.constants.ProvisioningPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the product bundle product entries service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ProductBundleProductEntriesPersistence.class)
public class ProductBundleProductEntriesPersistenceImpl
	extends BasePersistenceImpl<ProductBundleProductEntries>
	implements ProductBundleProductEntriesPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProductBundleProductEntriesUtil</code> to access the product bundle product entries persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProductBundleProductEntriesImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByProductBundleId;
	private FinderPath _finderPathCountByProductBundleId;

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or throws a <code>NoSuchProductBundleProductEntriesException</code> if it could not be found.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a matching product bundle product entries could not be found
	 */
	@Override
	public ProductBundleProductEntries findByProductBundleId(
			long productBundleId)
		throws NoSuchProductBundleProductEntriesException {

		ProductBundleProductEntries productBundleProductEntries =
			fetchByProductBundleId(productBundleId);

		if (productBundleProductEntries == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("productBundleId=");
			sb.append(productBundleId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProductBundleProductEntriesException(sb.toString());
		}

		return productBundleProductEntries;
	}

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle product entries, or <code>null</code> if a matching product bundle product entries could not be found
	 */
	@Override
	public ProductBundleProductEntries fetchByProductBundleId(
		long productBundleId) {

		return fetchByProductBundleId(productBundleId, true);
	}

	/**
	 * Returns the product bundle product entries where productBundleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle product entries, or <code>null</code> if a matching product bundle product entries could not be found
	 */
	@Override
	public ProductBundleProductEntries fetchByProductBundleId(
		long productBundleId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {productBundleId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByProductBundleId, finderArgs, this);
		}

		if (result instanceof ProductBundleProductEntries) {
			ProductBundleProductEntries productBundleProductEntries =
				(ProductBundleProductEntries)result;

			if (productBundleId !=
					productBundleProductEntries.getProductBundleId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTENTRIES_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productBundleId);

				List<ProductBundleProductEntries> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByProductBundleId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {productBundleId};
							}

							_log.warn(
								"ProductBundleProductEntriesPersistenceImpl.fetchByProductBundleId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProductBundleProductEntries productBundleProductEntries =
						list.get(0);

					result = productBundleProductEntries;

					cacheResult(productBundleProductEntries);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByProductBundleId, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (ProductBundleProductEntries)result;
		}
	}

	/**
	 * Removes the product bundle product entries where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the product bundle product entries that was removed
	 */
	@Override
	public ProductBundleProductEntries removeByProductBundleId(
			long productBundleId)
		throws NoSuchProductBundleProductEntriesException {

		ProductBundleProductEntries productBundleProductEntries =
			findByProductBundleId(productBundleId);

		return remove(productBundleProductEntries);
	}

	/**
	 * Returns the number of product bundle product entrieses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle product entrieses
	 */
	@Override
	public int countByProductBundleId(long productBundleId) {
		FinderPath finderPath = _finderPathCountByProductBundleId;

		Object[] finderArgs = new Object[] {productBundleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTBUNDLEPRODUCTENTRIES_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productBundleId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2 =
			"productBundleProductEntries.id.productBundleId = ?";

	public ProductBundleProductEntriesPersistenceImpl() {
		setModelClass(ProductBundleProductEntries.class);

		setModelImplClass(ProductBundleProductEntriesImpl.class);
		setModelPKClass(ProductBundleProductEntriesPK.class);
	}

	/**
	 * Caches the product bundle product entries in the entity cache if it is enabled.
	 *
	 * @param productBundleProductEntries the product bundle product entries
	 */
	@Override
	public void cacheResult(
		ProductBundleProductEntries productBundleProductEntries) {

		entityCache.putResult(
			entityCacheEnabled, ProductBundleProductEntriesImpl.class,
			productBundleProductEntries.getPrimaryKey(),
			productBundleProductEntries);

		finderCache.putResult(
			_finderPathFetchByProductBundleId,
			new Object[] {productBundleProductEntries.getProductBundleId()},
			productBundleProductEntries);

		productBundleProductEntries.resetOriginalValues();
	}

	/**
	 * Caches the product bundle product entrieses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductEntrieses the product bundle product entrieses
	 */
	@Override
	public void cacheResult(
		List<ProductBundleProductEntries> productBundleProductEntrieses) {

		for (ProductBundleProductEntries productBundleProductEntries :
				productBundleProductEntrieses) {

			if (entityCache.getResult(
					entityCacheEnabled, ProductBundleProductEntriesImpl.class,
					productBundleProductEntries.getPrimaryKey()) == null) {

				cacheResult(productBundleProductEntries);
			}
			else {
				productBundleProductEntries.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all product bundle product entrieses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProductBundleProductEntriesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the product bundle product entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		ProductBundleProductEntries productBundleProductEntries) {

		entityCache.removeResult(
			entityCacheEnabled, ProductBundleProductEntriesImpl.class,
			productBundleProductEntries.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ProductBundleProductEntriesModelImpl)productBundleProductEntries,
			true);
	}

	@Override
	public void clearCache(
		List<ProductBundleProductEntries> productBundleProductEntrieses) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProductBundleProductEntries productBundleProductEntries :
				productBundleProductEntrieses) {

			entityCache.removeResult(
				entityCacheEnabled, ProductBundleProductEntriesImpl.class,
				productBundleProductEntries.getPrimaryKey());

			clearUniqueFindersCache(
				(ProductBundleProductEntriesModelImpl)
					productBundleProductEntries,
				true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ProductBundleProductEntriesImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProductBundleProductEntriesModelImpl
			productBundleProductEntriesModelImpl) {

		Object[] args = new Object[] {
			productBundleProductEntriesModelImpl.getProductBundleId()
		};

		finderCache.putResult(
			_finderPathCountByProductBundleId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByProductBundleId, args,
			productBundleProductEntriesModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProductBundleProductEntriesModelImpl
			productBundleProductEntriesModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				productBundleProductEntriesModelImpl.getProductBundleId()
			};

			finderCache.removeResult(_finderPathCountByProductBundleId, args);
			finderCache.removeResult(_finderPathFetchByProductBundleId, args);
		}

		if ((productBundleProductEntriesModelImpl.getColumnBitmask() &
			 _finderPathFetchByProductBundleId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				productBundleProductEntriesModelImpl.
					getOriginalProductBundleId()
			};

			finderCache.removeResult(_finderPathCountByProductBundleId, args);
			finderCache.removeResult(_finderPathFetchByProductBundleId, args);
		}
	}

	/**
	 * Creates a new product bundle product entries with the primary key. Does not add the product bundle product entries to the database.
	 *
	 * @param productBundleProductEntriesPK the primary key for the new product bundle product entries
	 * @return the new product bundle product entries
	 */
	@Override
	public ProductBundleProductEntries create(
		ProductBundleProductEntriesPK productBundleProductEntriesPK) {

		ProductBundleProductEntries productBundleProductEntries =
			new ProductBundleProductEntriesImpl();

		productBundleProductEntries.setNew(true);
		productBundleProductEntries.setPrimaryKey(
			productBundleProductEntriesPK);

		return productBundleProductEntries;
	}

	/**
	 * Removes the product bundle product entries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries that was removed
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	@Override
	public ProductBundleProductEntries remove(
			ProductBundleProductEntriesPK productBundleProductEntriesPK)
		throws NoSuchProductBundleProductEntriesException {

		return remove((Serializable)productBundleProductEntriesPK);
	}

	/**
	 * Removes the product bundle product entries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the product bundle product entries
	 * @return the product bundle product entries that was removed
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	@Override
	public ProductBundleProductEntries remove(Serializable primaryKey)
		throws NoSuchProductBundleProductEntriesException {

		Session session = null;

		try {
			session = openSession();

			ProductBundleProductEntries productBundleProductEntries =
				(ProductBundleProductEntries)session.get(
					ProductBundleProductEntriesImpl.class, primaryKey);

			if (productBundleProductEntries == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductBundleProductEntriesException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(productBundleProductEntries);
		}
		catch (NoSuchProductBundleProductEntriesException
					noSuchEntityException) {

			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ProductBundleProductEntries removeImpl(
		ProductBundleProductEntries productBundleProductEntries) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(productBundleProductEntries)) {
				productBundleProductEntries =
					(ProductBundleProductEntries)session.get(
						ProductBundleProductEntriesImpl.class,
						productBundleProductEntries.getPrimaryKeyObj());
			}

			if (productBundleProductEntries != null) {
				session.delete(productBundleProductEntries);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (productBundleProductEntries != null) {
			clearCache(productBundleProductEntries);
		}

		return productBundleProductEntries;
	}

	@Override
	public ProductBundleProductEntries updateImpl(
		ProductBundleProductEntries productBundleProductEntries) {

		boolean isNew = productBundleProductEntries.isNew();

		if (!(productBundleProductEntries instanceof
				ProductBundleProductEntriesModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					productBundleProductEntries.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					productBundleProductEntries);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in productBundleProductEntries proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProductBundleProductEntries implementation " +
					productBundleProductEntries.getClass());
		}

		ProductBundleProductEntriesModelImpl
			productBundleProductEntriesModelImpl =
				(ProductBundleProductEntriesModelImpl)
					productBundleProductEntries;

		Session session = null;

		try {
			session = openSession();

			if (productBundleProductEntries.isNew()) {
				session.save(productBundleProductEntries);

				productBundleProductEntries.setNew(false);
			}
			else {
				productBundleProductEntries =
					(ProductBundleProductEntries)session.merge(
						productBundleProductEntries);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, ProductBundleProductEntriesImpl.class,
			productBundleProductEntries.getPrimaryKey(),
			productBundleProductEntries, false);

		clearUniqueFindersCache(productBundleProductEntriesModelImpl, false);
		cacheUniqueFindersCache(productBundleProductEntriesModelImpl);

		productBundleProductEntries.resetOriginalValues();

		return productBundleProductEntries;
	}

	/**
	 * Returns the product bundle product entries with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the product bundle product entries
	 * @return the product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	@Override
	public ProductBundleProductEntries findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductBundleProductEntriesException {

		ProductBundleProductEntries productBundleProductEntries =
			fetchByPrimaryKey(primaryKey);

		if (productBundleProductEntries == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductBundleProductEntriesException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return productBundleProductEntries;
	}

	/**
	 * Returns the product bundle product entries with the primary key or throws a <code>NoSuchProductBundleProductEntriesException</code> if it could not be found.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries
	 * @throws NoSuchProductBundleProductEntriesException if a product bundle product entries with the primary key could not be found
	 */
	@Override
	public ProductBundleProductEntries findByPrimaryKey(
			ProductBundleProductEntriesPK productBundleProductEntriesPK)
		throws NoSuchProductBundleProductEntriesException {

		return findByPrimaryKey((Serializable)productBundleProductEntriesPK);
	}

	/**
	 * Returns the product bundle product entries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductEntriesPK the primary key of the product bundle product entries
	 * @return the product bundle product entries, or <code>null</code> if a product bundle product entries with the primary key could not be found
	 */
	@Override
	public ProductBundleProductEntries fetchByPrimaryKey(
		ProductBundleProductEntriesPK productBundleProductEntriesPK) {

		return fetchByPrimaryKey((Serializable)productBundleProductEntriesPK);
	}

	/**
	 * Returns all the product bundle product entrieses.
	 *
	 * @return the product bundle product entrieses
	 */
	@Override
	public List<ProductBundleProductEntries> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<ProductBundleProductEntries> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<ProductBundleProductEntries> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProductEntries> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<ProductBundleProductEntries> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProductEntries> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<ProductBundleProductEntries> list = null;

		if (useFinderCache) {
			list = (List<ProductBundleProductEntries>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTENTRIES);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PRODUCTBUNDLEPRODUCTENTRIES;

				sql = sql.concat(
					ProductBundleProductEntriesModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProductBundleProductEntries>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the product bundle product entrieses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProductBundleProductEntries productBundleProductEntries :
				findAll()) {

			remove(productBundleProductEntries);
		}
	}

	/**
	 * Returns the number of product bundle product entrieses.
	 *
	 * @return the number of product bundle product entrieses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_PRODUCTBUNDLEPRODUCTENTRIES);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "productBundleProductEntriesPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PRODUCTBUNDLEPRODUCTENTRIES;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProductBundleProductEntriesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the product bundle product entries persistence.
	 */
	@Activate
	public void activate() {
		ProductBundleProductEntriesModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		ProductBundleProductEntriesModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductEntriesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductEntriesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductEntriesImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByProductBundleId", new String[] {Long.class.getName()},
			ProductBundleProductEntriesModelImpl.
				PRODUCTBUNDLEID_COLUMN_BITMASK);

		_finderPathCountByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductBundleId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(
			ProductBundleProductEntriesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.model.ProductBundleProductEntries"),
			true);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_PRODUCTBUNDLEPRODUCTENTRIES =
		"SELECT productBundleProductEntries FROM ProductBundleProductEntries productBundleProductEntries";

	private static final String _SQL_SELECT_PRODUCTBUNDLEPRODUCTENTRIES_WHERE =
		"SELECT productBundleProductEntries FROM ProductBundleProductEntries productBundleProductEntries WHERE ";

	private static final String _SQL_COUNT_PRODUCTBUNDLEPRODUCTENTRIES =
		"SELECT COUNT(productBundleProductEntries) FROM ProductBundleProductEntries productBundleProductEntries";

	private static final String _SQL_COUNT_PRODUCTBUNDLEPRODUCTENTRIES_WHERE =
		"SELECT COUNT(productBundleProductEntries) FROM ProductBundleProductEntries productBundleProductEntries WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"productBundleProductEntries.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProductBundleProductEntries exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProductBundleProductEntries exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProductBundleProductEntriesPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"productBundleId", "productEntryId"});

	static {
		try {
			Class.forName(ProvisioningPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}