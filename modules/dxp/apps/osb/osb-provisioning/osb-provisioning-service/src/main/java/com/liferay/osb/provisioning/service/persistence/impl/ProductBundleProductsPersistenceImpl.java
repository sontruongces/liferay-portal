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

import com.liferay.osb.provisioning.exception.NoSuchProductBundleProductsException;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.model.impl.ProductBundleProductsImpl;
import com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPersistence;
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
 * The persistence implementation for the product bundle products service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ProductBundleProductsPersistence.class)
public class ProductBundleProductsPersistenceImpl
	extends BasePersistenceImpl<ProductBundleProducts>
	implements ProductBundleProductsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProductBundleProductsUtil</code> to access the product bundle products persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProductBundleProductsImpl.class.getName();

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
	 * Returns the product bundle products where productBundleId = &#63; or throws a <code>NoSuchProductBundleProductsException</code> if it could not be found.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts findByProductBundleId(long productBundleId)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = fetchByProductBundleId(
			productBundleId);

		if (productBundleProducts == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("productBundleId=");
			sb.append(productBundleId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProductBundleProductsException(sb.toString());
		}

		return productBundleProducts;
	}

	/**
	 * Returns the product bundle products where productBundleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts fetchByProductBundleId(long productBundleId) {
		return fetchByProductBundleId(productBundleId, true);
	}

	/**
	 * Returns the product bundle products where productBundleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productBundleId the product bundle ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts fetchByProductBundleId(
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

		if (result instanceof ProductBundleProducts) {
			ProductBundleProducts productBundleProducts =
				(ProductBundleProducts)result;

			if (productBundleId != productBundleProducts.getProductBundleId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productBundleId);

				List<ProductBundleProducts> list = query.list();

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
								"ProductBundleProductsPersistenceImpl.fetchByProductBundleId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ProductBundleProducts productBundleProducts = list.get(0);

					result = productBundleProducts;

					cacheResult(productBundleProducts);
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
			return (ProductBundleProducts)result;
		}
	}

	/**
	 * Removes the product bundle products where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the product bundle products that was removed
	 */
	@Override
	public ProductBundleProducts removeByProductBundleId(long productBundleId)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = findByProductBundleId(
			productBundleId);

		return remove(productBundleProducts);
	}

	/**
	 * Returns the number of product bundle productses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle productses
	 */
	@Override
	public int countByProductBundleId(long productBundleId) {
		FinderPath finderPath = _finderPathCountByProductBundleId;

		Object[] finderArgs = new Object[] {productBundleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTBUNDLEPRODUCTS_WHERE);

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
			"productBundleProducts.id.productBundleId = ?";

	public ProductBundleProductsPersistenceImpl() {
		setModelClass(ProductBundleProducts.class);

		setModelImplClass(ProductBundleProductsImpl.class);
		setModelPKClass(ProductBundleProductsPK.class);
	}

	/**
	 * Caches the product bundle products in the entity cache if it is enabled.
	 *
	 * @param productBundleProducts the product bundle products
	 */
	@Override
	public void cacheResult(ProductBundleProducts productBundleProducts) {
		entityCache.putResult(
			entityCacheEnabled, ProductBundleProductsImpl.class,
			productBundleProducts.getPrimaryKey(), productBundleProducts);

		finderCache.putResult(
			_finderPathFetchByProductBundleId,
			new Object[] {productBundleProducts.getProductBundleId()},
			productBundleProducts);

		productBundleProducts.resetOriginalValues();
	}

	/**
	 * Caches the product bundle productses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductses the product bundle productses
	 */
	@Override
	public void cacheResult(
		List<ProductBundleProducts> productBundleProductses) {

		for (ProductBundleProducts productBundleProducts :
				productBundleProductses) {

			if (entityCache.getResult(
					entityCacheEnabled, ProductBundleProductsImpl.class,
					productBundleProducts.getPrimaryKey()) == null) {

				cacheResult(productBundleProducts);
			}
			else {
				productBundleProducts.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all product bundle productses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProductBundleProductsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the product bundle products.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProductBundleProducts productBundleProducts) {
		entityCache.removeResult(
			entityCacheEnabled, ProductBundleProductsImpl.class,
			productBundleProducts.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ProductBundleProductsModelImpl)productBundleProducts, true);
	}

	@Override
	public void clearCache(
		List<ProductBundleProducts> productBundleProductses) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProductBundleProducts productBundleProducts :
				productBundleProductses) {

			entityCache.removeResult(
				entityCacheEnabled, ProductBundleProductsImpl.class,
				productBundleProducts.getPrimaryKey());

			clearUniqueFindersCache(
				(ProductBundleProductsModelImpl)productBundleProducts, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ProductBundleProductsImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProductBundleProductsModelImpl productBundleProductsModelImpl) {

		Object[] args = new Object[] {
			productBundleProductsModelImpl.getProductBundleId()
		};

		finderCache.putResult(
			_finderPathCountByProductBundleId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByProductBundleId, args,
			productBundleProductsModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProductBundleProductsModelImpl productBundleProductsModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				productBundleProductsModelImpl.getProductBundleId()
			};

			finderCache.removeResult(_finderPathCountByProductBundleId, args);
			finderCache.removeResult(_finderPathFetchByProductBundleId, args);
		}

		if ((productBundleProductsModelImpl.getColumnBitmask() &
			 _finderPathFetchByProductBundleId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				productBundleProductsModelImpl.getOriginalProductBundleId()
			};

			finderCache.removeResult(_finderPathCountByProductBundleId, args);
			finderCache.removeResult(_finderPathFetchByProductBundleId, args);
		}
	}

	/**
	 * Creates a new product bundle products with the primary key. Does not add the product bundle products to the database.
	 *
	 * @param productBundleProductsPK the primary key for the new product bundle products
	 * @return the new product bundle products
	 */
	@Override
	public ProductBundleProducts create(
		ProductBundleProductsPK productBundleProductsPK) {

		ProductBundleProducts productBundleProducts =
			new ProductBundleProductsImpl();

		productBundleProducts.setNew(true);
		productBundleProducts.setPrimaryKey(productBundleProductsPK);

		return productBundleProducts;
	}

	/**
	 * Removes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts remove(
			ProductBundleProductsPK productBundleProductsPK)
		throws NoSuchProductBundleProductsException {

		return remove((Serializable)productBundleProductsPK);
	}

	/**
	 * Removes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts remove(Serializable primaryKey)
		throws NoSuchProductBundleProductsException {

		Session session = null;

		try {
			session = openSession();

			ProductBundleProducts productBundleProducts =
				(ProductBundleProducts)session.get(
					ProductBundleProductsImpl.class, primaryKey);

			if (productBundleProducts == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductBundleProductsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(productBundleProducts);
		}
		catch (NoSuchProductBundleProductsException noSuchEntityException) {
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
	protected ProductBundleProducts removeImpl(
		ProductBundleProducts productBundleProducts) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(productBundleProducts)) {
				productBundleProducts = (ProductBundleProducts)session.get(
					ProductBundleProductsImpl.class,
					productBundleProducts.getPrimaryKeyObj());
			}

			if (productBundleProducts != null) {
				session.delete(productBundleProducts);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (productBundleProducts != null) {
			clearCache(productBundleProducts);
		}

		return productBundleProducts;
	}

	@Override
	public ProductBundleProducts updateImpl(
		ProductBundleProducts productBundleProducts) {

		boolean isNew = productBundleProducts.isNew();

		if (!(productBundleProducts instanceof
				ProductBundleProductsModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(productBundleProducts.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					productBundleProducts);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in productBundleProducts proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProductBundleProducts implementation " +
					productBundleProducts.getClass());
		}

		ProductBundleProductsModelImpl productBundleProductsModelImpl =
			(ProductBundleProductsModelImpl)productBundleProducts;

		Session session = null;

		try {
			session = openSession();

			if (productBundleProducts.isNew()) {
				session.save(productBundleProducts);

				productBundleProducts.setNew(false);
			}
			else {
				productBundleProducts = (ProductBundleProducts)session.merge(
					productBundleProducts);
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
			entityCacheEnabled, ProductBundleProductsImpl.class,
			productBundleProducts.getPrimaryKey(), productBundleProducts,
			false);

		clearUniqueFindersCache(productBundleProductsModelImpl, false);
		cacheUniqueFindersCache(productBundleProductsModelImpl);

		productBundleProducts.resetOriginalValues();

		return productBundleProducts;
	}

	/**
	 * Returns the product bundle products with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = fetchByPrimaryKey(
			primaryKey);

		if (productBundleProducts == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductBundleProductsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return productBundleProducts;
	}

	/**
	 * Returns the product bundle products with the primary key or throws a <code>NoSuchProductBundleProductsException</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts findByPrimaryKey(
			ProductBundleProductsPK productBundleProductsPK)
		throws NoSuchProductBundleProductsException {

		return findByPrimaryKey((Serializable)productBundleProductsPK);
	}

	/**
	 * Returns the product bundle products with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products, or <code>null</code> if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts fetchByPrimaryKey(
		ProductBundleProductsPK productBundleProductsPK) {

		return fetchByPrimaryKey((Serializable)productBundleProductsPK);
	}

	/**
	 * Returns all the product bundle productses.
	 *
	 * @return the product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator,
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

		List<ProductBundleProducts> list = null;

		if (useFinderCache) {
			list = (List<ProductBundleProducts>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PRODUCTBUNDLEPRODUCTS;

				sql = sql.concat(ProductBundleProductsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProductBundleProducts>)QueryUtil.list(
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
	 * Removes all the product bundle productses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProductBundleProducts productBundleProducts : findAll()) {
			remove(productBundleProducts);
		}
	}

	/**
	 * Returns the number of product bundle productses.
	 *
	 * @return the number of product bundle productses
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
					_SQL_COUNT_PRODUCTBUNDLEPRODUCTS);

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
		return "productBundleProductsPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PRODUCTBUNDLEPRODUCTS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProductBundleProductsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the product bundle products persistence.
	 */
	@Activate
	public void activate() {
		ProductBundleProductsModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		ProductBundleProductsModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByProductBundleId", new String[] {Long.class.getName()},
			ProductBundleProductsModelImpl.PRODUCTBUNDLEID_COLUMN_BITMASK);

		_finderPathCountByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductBundleId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(ProductBundleProductsImpl.class.getName());
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
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.model.ProductBundleProducts"),
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

	private static final String _SQL_SELECT_PRODUCTBUNDLEPRODUCTS =
		"SELECT productBundleProducts FROM ProductBundleProducts productBundleProducts";

	private static final String _SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE =
		"SELECT productBundleProducts FROM ProductBundleProducts productBundleProducts WHERE ";

	private static final String _SQL_COUNT_PRODUCTBUNDLEPRODUCTS =
		"SELECT COUNT(productBundleProducts) FROM ProductBundleProducts productBundleProducts";

	private static final String _SQL_COUNT_PRODUCTBUNDLEPRODUCTS_WHERE =
		"SELECT COUNT(productBundleProducts) FROM ProductBundleProducts productBundleProducts WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"productBundleProducts.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProductBundleProducts exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProductBundleProducts exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProductBundleProductsPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"productBundleId", "productKey"});

	static {
		try {
			Class.forName(ProvisioningPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}