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

package com.liferay.change.tracking.engine.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.change.tracking.engine.model.CTECollection;
import com.liferay.change.tracking.engine.service.CTECollectionLocalService;
import com.liferay.change.tracking.engine.service.persistence.CTECollectionPersistence;
import com.liferay.change.tracking.engine.service.persistence.CTEEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the cte collection local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.change.tracking.engine.service.impl.CTECollectionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.change.tracking.engine.service.impl.CTECollectionLocalServiceImpl
 * @see com.liferay.change.tracking.engine.service.CTECollectionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CTECollectionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CTECollectionLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.change.tracking.engine.service.CTECollectionLocalServiceUtil} to access the cte collection local service.
	 */

	/**
	 * Adds the cte collection to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cteCollection the cte collection
	 * @return the cte collection that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CTECollection addCTECollection(CTECollection cteCollection) {
		cteCollection.setNew(true);

		return cteCollectionPersistence.update(cteCollection);
	}

	/**
	 * Creates a new cte collection with the primary key. Does not add the cte collection to the database.
	 *
	 * @param cteCollectionId the primary key for the new cte collection
	 * @return the new cte collection
	 */
	@Override
	@Transactional(enabled = false)
	public CTECollection createCTECollection(long cteCollectionId) {
		return cteCollectionPersistence.create(cteCollectionId);
	}

	/**
	 * Deletes the cte collection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cteCollectionId the primary key of the cte collection
	 * @return the cte collection that was removed
	 * @throws PortalException if a cte collection with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CTECollection deleteCTECollection(long cteCollectionId)
		throws PortalException {
		return cteCollectionPersistence.remove(cteCollectionId);
	}

	/**
	 * Deletes the cte collection from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cteCollection the cte collection
	 * @return the cte collection that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CTECollection deleteCTECollection(CTECollection cteCollection) {
		return cteCollectionPersistence.remove(cteCollection);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CTECollection.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return cteCollectionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.change.tracking.engine.model.impl.CTECollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return cteCollectionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.change.tracking.engine.model.impl.CTECollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return cteCollectionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return cteCollectionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return cteCollectionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CTECollection fetchCTECollection(long cteCollectionId) {
		return cteCollectionPersistence.fetchByPrimaryKey(cteCollectionId);
	}

	/**
	 * Returns the cte collection with the primary key.
	 *
	 * @param cteCollectionId the primary key of the cte collection
	 * @return the cte collection
	 * @throws PortalException if a cte collection with the primary key could not be found
	 */
	@Override
	public CTECollection getCTECollection(long cteCollectionId)
		throws PortalException {
		return cteCollectionPersistence.findByPrimaryKey(cteCollectionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(cteCollectionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CTECollection.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("cteCollectionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(cteCollectionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CTECollection.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"cteCollectionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(cteCollectionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CTECollection.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("cteCollectionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return cteCollectionLocalService.deleteCTECollection((CTECollection)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return cteCollectionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the cte collections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.change.tracking.engine.model.impl.CTECollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cte collections
	 * @param end the upper bound of the range of cte collections (not inclusive)
	 * @return the range of cte collections
	 */
	@Override
	public List<CTECollection> getCTECollections(int start, int end) {
		return cteCollectionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cte collections.
	 *
	 * @return the number of cte collections
	 */
	@Override
	public int getCTECollectionsCount() {
		return cteCollectionPersistence.countAll();
	}

	/**
	 * Updates the cte collection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cteCollection the cte collection
	 * @return the cte collection that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CTECollection updateCTECollection(CTECollection cteCollection) {
		return cteCollectionPersistence.update(cteCollection);
	}

	/**
	 */
	@Override
	public void addCTEEntryCTECollection(long cteEntryId, long cteCollectionId) {
		cteEntryPersistence.addCTECollection(cteEntryId, cteCollectionId);
	}

	/**
	 */
	@Override
	public void addCTEEntryCTECollection(long cteEntryId,
		CTECollection cteCollection) {
		cteEntryPersistence.addCTECollection(cteEntryId, cteCollection);
	}

	/**
	 */
	@Override
	public void addCTEEntryCTECollections(long cteEntryId,
		long[] cteCollectionIds) {
		cteEntryPersistence.addCTECollections(cteEntryId, cteCollectionIds);
	}

	/**
	 */
	@Override
	public void addCTEEntryCTECollections(long cteEntryId,
		List<CTECollection> cteCollections) {
		cteEntryPersistence.addCTECollections(cteEntryId, cteCollections);
	}

	/**
	 */
	@Override
	public void clearCTEEntryCTECollections(long cteEntryId) {
		cteEntryPersistence.clearCTECollections(cteEntryId);
	}

	/**
	 */
	@Override
	public void deleteCTEEntryCTECollection(long cteEntryId,
		long cteCollectionId) {
		cteEntryPersistence.removeCTECollection(cteEntryId, cteCollectionId);
	}

	/**
	 */
	@Override
	public void deleteCTEEntryCTECollection(long cteEntryId,
		CTECollection cteCollection) {
		cteEntryPersistence.removeCTECollection(cteEntryId, cteCollection);
	}

	/**
	 */
	@Override
	public void deleteCTEEntryCTECollections(long cteEntryId,
		long[] cteCollectionIds) {
		cteEntryPersistence.removeCTECollections(cteEntryId, cteCollectionIds);
	}

	/**
	 */
	@Override
	public void deleteCTEEntryCTECollections(long cteEntryId,
		List<CTECollection> cteCollections) {
		cteEntryPersistence.removeCTECollections(cteEntryId, cteCollections);
	}

	/**
	 * Returns the cteEntryIds of the cte entries associated with the cte collection.
	 *
	 * @param cteCollectionId the cteCollectionId of the cte collection
	 * @return long[] the cteEntryIds of cte entries associated with the cte collection
	 */
	@Override
	public long[] getCTEEntryPrimaryKeys(long cteCollectionId) {
		return cteCollectionPersistence.getCTEEntryPrimaryKeys(cteCollectionId);
	}

	/**
	 */
	@Override
	public List<CTECollection> getCTEEntryCTECollections(long cteEntryId) {
		return cteEntryPersistence.getCTECollections(cteEntryId);
	}

	/**
	 */
	@Override
	public List<CTECollection> getCTEEntryCTECollections(long cteEntryId,
		int start, int end) {
		return cteEntryPersistence.getCTECollections(cteEntryId, start, end);
	}

	/**
	 */
	@Override
	public List<CTECollection> getCTEEntryCTECollections(long cteEntryId,
		int start, int end, OrderByComparator<CTECollection> orderByComparator) {
		return cteEntryPersistence.getCTECollections(cteEntryId, start, end,
			orderByComparator);
	}

	/**
	 */
	@Override
	public int getCTEEntryCTECollectionsCount(long cteEntryId) {
		return cteEntryPersistence.getCTECollectionsSize(cteEntryId);
	}

	/**
	 */
	@Override
	public boolean hasCTEEntryCTECollection(long cteEntryId,
		long cteCollectionId) {
		return cteEntryPersistence.containsCTECollection(cteEntryId,
			cteCollectionId);
	}

	/**
	 */
	@Override
	public boolean hasCTEEntryCTECollections(long cteEntryId) {
		return cteEntryPersistence.containsCTECollections(cteEntryId);
	}

	/**
	 */
	@Override
	public void setCTEEntryCTECollections(long cteEntryId,
		long[] cteCollectionIds) {
		cteEntryPersistence.setCTECollections(cteEntryId, cteCollectionIds);
	}

	/**
	 * Returns the cte collection local service.
	 *
	 * @return the cte collection local service
	 */
	public CTECollectionLocalService getCTECollectionLocalService() {
		return cteCollectionLocalService;
	}

	/**
	 * Sets the cte collection local service.
	 *
	 * @param cteCollectionLocalService the cte collection local service
	 */
	public void setCTECollectionLocalService(
		CTECollectionLocalService cteCollectionLocalService) {
		this.cteCollectionLocalService = cteCollectionLocalService;
	}

	/**
	 * Returns the cte collection persistence.
	 *
	 * @return the cte collection persistence
	 */
	public CTECollectionPersistence getCTECollectionPersistence() {
		return cteCollectionPersistence;
	}

	/**
	 * Sets the cte collection persistence.
	 *
	 * @param cteCollectionPersistence the cte collection persistence
	 */
	public void setCTECollectionPersistence(
		CTECollectionPersistence cteCollectionPersistence) {
		this.cteCollectionPersistence = cteCollectionPersistence;
	}

	/**
	 * Returns the cte entry local service.
	 *
	 * @return the cte entry local service
	 */
	public com.liferay.change.tracking.engine.service.CTEEntryLocalService getCTEEntryLocalService() {
		return cteEntryLocalService;
	}

	/**
	 * Sets the cte entry local service.
	 *
	 * @param cteEntryLocalService the cte entry local service
	 */
	public void setCTEEntryLocalService(
		com.liferay.change.tracking.engine.service.CTEEntryLocalService cteEntryLocalService) {
		this.cteEntryLocalService = cteEntryLocalService;
	}

	/**
	 * Returns the cte entry persistence.
	 *
	 * @return the cte entry persistence
	 */
	public CTEEntryPersistence getCTEEntryPersistence() {
		return cteEntryPersistence;
	}

	/**
	 * Sets the cte entry persistence.
	 *
	 * @param cteEntryPersistence the cte entry persistence
	 */
	public void setCTEEntryPersistence(CTEEntryPersistence cteEntryPersistence) {
		this.cteEntryPersistence = cteEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.change.tracking.engine.model.CTECollection",
			cteCollectionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.change.tracking.engine.model.CTECollection");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CTECollectionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CTECollection.class;
	}

	protected String getModelClassName() {
		return CTECollection.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = cteCollectionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = CTECollectionLocalService.class)
	protected CTECollectionLocalService cteCollectionLocalService;
	@BeanReference(type = CTECollectionPersistence.class)
	protected CTECollectionPersistence cteCollectionPersistence;
	@BeanReference(type = com.liferay.change.tracking.engine.service.CTEEntryLocalService.class)
	protected com.liferay.change.tracking.engine.service.CTEEntryLocalService cteEntryLocalService;
	@BeanReference(type = CTEEntryPersistence.class)
	protected CTEEntryPersistence cteEntryPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}