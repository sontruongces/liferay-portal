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

package com.liferay.site.navigation.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import com.liferay.site.navigation.service.persistence.SiteNavigationMenuItemPersistence;
import com.liferay.site.navigation.service.persistence.SiteNavigationMenuPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the site navigation menu item local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.site.navigation.service.impl.SiteNavigationMenuItemLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.site.navigation.service.impl.SiteNavigationMenuItemLocalServiceImpl
 * @generated
 */
public abstract class SiteNavigationMenuItemLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService,
			   SiteNavigationMenuItemLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SiteNavigationMenuItemLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.site.navigation.service.SiteNavigationMenuItemLocalServiceUtil</code>.
	 */

	/**
	 * Adds the site navigation menu item to the database. Also notifies the appropriate model listeners.
	 *
	 * @param siteNavigationMenuItem the site navigation menu item
	 * @return the site navigation menu item that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SiteNavigationMenuItem addSiteNavigationMenuItem(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		siteNavigationMenuItem.setNew(true);

		return siteNavigationMenuItemPersistence.update(siteNavigationMenuItem);
	}

	/**
	 * Creates a new site navigation menu item with the primary key. Does not add the site navigation menu item to the database.
	 *
	 * @param siteNavigationMenuItemId the primary key for the new site navigation menu item
	 * @return the new site navigation menu item
	 */
	@Override
	@Transactional(enabled = false)
	public SiteNavigationMenuItem createSiteNavigationMenuItem(
		long siteNavigationMenuItemId) {

		return siteNavigationMenuItemPersistence.create(
			siteNavigationMenuItemId);
	}

	/**
	 * Deletes the site navigation menu item with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param siteNavigationMenuItemId the primary key of the site navigation menu item
	 * @return the site navigation menu item that was removed
	 * @throws PortalException if a site navigation menu item with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SiteNavigationMenuItem deleteSiteNavigationMenuItem(
			long siteNavigationMenuItemId)
		throws PortalException {

		return siteNavigationMenuItemPersistence.remove(
			siteNavigationMenuItemId);
	}

	/**
	 * Deletes the site navigation menu item from the database. Also notifies the appropriate model listeners.
	 *
	 * @param siteNavigationMenuItem the site navigation menu item
	 * @return the site navigation menu item that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SiteNavigationMenuItem deleteSiteNavigationMenuItem(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		return siteNavigationMenuItemPersistence.remove(siteNavigationMenuItem);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			SiteNavigationMenuItem.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return siteNavigationMenuItemPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.site.navigation.model.impl.SiteNavigationMenuItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return siteNavigationMenuItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.site.navigation.model.impl.SiteNavigationMenuItemModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return siteNavigationMenuItemPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return siteNavigationMenuItemPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return siteNavigationMenuItemPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SiteNavigationMenuItem fetchSiteNavigationMenuItem(
		long siteNavigationMenuItemId) {

		return siteNavigationMenuItemPersistence.fetchByPrimaryKey(
			siteNavigationMenuItemId);
	}

	/**
	 * Returns the site navigation menu item matching the UUID and group.
	 *
	 * @param uuid the site navigation menu item's UUID
	 * @param groupId the primary key of the group
	 * @return the matching site navigation menu item, or <code>null</code> if a matching site navigation menu item could not be found
	 */
	@Override
	public SiteNavigationMenuItem fetchSiteNavigationMenuItemByUuidAndGroupId(
		String uuid, long groupId) {

		return siteNavigationMenuItemPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the site navigation menu item with the primary key.
	 *
	 * @param siteNavigationMenuItemId the primary key of the site navigation menu item
	 * @return the site navigation menu item
	 * @throws PortalException if a site navigation menu item with the primary key could not be found
	 */
	@Override
	public SiteNavigationMenuItem getSiteNavigationMenuItem(
			long siteNavigationMenuItemId)
		throws PortalException {

		return siteNavigationMenuItemPersistence.findByPrimaryKey(
			siteNavigationMenuItemId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			siteNavigationMenuItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SiteNavigationMenuItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"siteNavigationMenuItemId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			siteNavigationMenuItemLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			SiteNavigationMenuItem.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"siteNavigationMenuItemId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			siteNavigationMenuItemLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SiteNavigationMenuItem.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"siteNavigationMenuItemId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<SiteNavigationMenuItem>() {

				@Override
				public void performAction(
						SiteNavigationMenuItem siteNavigationMenuItem)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, siteNavigationMenuItem);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					SiteNavigationMenuItem.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return siteNavigationMenuItemLocalService.deleteSiteNavigationMenuItem(
			(SiteNavigationMenuItem)persistedModel);
	}

	public BasePersistence<SiteNavigationMenuItem> getBasePersistence() {
		return siteNavigationMenuItemPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return siteNavigationMenuItemPersistence.findByPrimaryKey(
			primaryKeyObj);
	}

	/**
	 * Returns all the site navigation menu items matching the UUID and company.
	 *
	 * @param uuid the UUID of the site navigation menu items
	 * @param companyId the primary key of the company
	 * @return the matching site navigation menu items, or an empty list if no matches were found
	 */
	@Override
	public List<SiteNavigationMenuItem>
		getSiteNavigationMenuItemsByUuidAndCompanyId(
			String uuid, long companyId) {

		return siteNavigationMenuItemPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of site navigation menu items matching the UUID and company.
	 *
	 * @param uuid the UUID of the site navigation menu items
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of site navigation menu items
	 * @param end the upper bound of the range of site navigation menu items (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching site navigation menu items, or an empty list if no matches were found
	 */
	@Override
	public List<SiteNavigationMenuItem>
		getSiteNavigationMenuItemsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<SiteNavigationMenuItem> orderByComparator) {

		return siteNavigationMenuItemPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the site navigation menu item matching the UUID and group.
	 *
	 * @param uuid the site navigation menu item's UUID
	 * @param groupId the primary key of the group
	 * @return the matching site navigation menu item
	 * @throws PortalException if a matching site navigation menu item could not be found
	 */
	@Override
	public SiteNavigationMenuItem getSiteNavigationMenuItemByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return siteNavigationMenuItemPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the site navigation menu items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.site.navigation.model.impl.SiteNavigationMenuItemModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of site navigation menu items
	 * @param end the upper bound of the range of site navigation menu items (not inclusive)
	 * @return the range of site navigation menu items
	 */
	@Override
	public List<SiteNavigationMenuItem> getSiteNavigationMenuItems(
		int start, int end) {

		return siteNavigationMenuItemPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of site navigation menu items.
	 *
	 * @return the number of site navigation menu items
	 */
	@Override
	public int getSiteNavigationMenuItemsCount() {
		return siteNavigationMenuItemPersistence.countAll();
	}

	/**
	 * Updates the site navigation menu item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param siteNavigationMenuItem the site navigation menu item
	 * @return the site navigation menu item that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SiteNavigationMenuItem updateSiteNavigationMenuItem(
		SiteNavigationMenuItem siteNavigationMenuItem) {

		return siteNavigationMenuItemPersistence.update(siteNavigationMenuItem);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SiteNavigationMenuItemLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		siteNavigationMenuItemLocalService =
			(SiteNavigationMenuItemLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SiteNavigationMenuItemLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SiteNavigationMenuItem.class;
	}

	protected String getModelClassName() {
		return SiteNavigationMenuItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				siteNavigationMenuItemPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected SiteNavigationMenuItemLocalService
		siteNavigationMenuItemLocalService;

	@Reference
	protected SiteNavigationMenuItemPersistence
		siteNavigationMenuItemPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected SiteNavigationMenuPersistence siteNavigationMenuPersistence;

}