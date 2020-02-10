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

package com.liferay.portal.security.service.access.policy.service.base;

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
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.service.SAPEntryLocalService;
import com.liferay.portal.security.service.access.policy.service.persistence.SAPEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the sap entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.security.service.access.policy.service.impl.SAPEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.security.service.access.policy.service.impl.SAPEntryLocalServiceImpl
 * @generated
 */
public abstract class SAPEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, IdentifiableOSGiService, SAPEntryLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SAPEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.security.service.access.policy.service.SAPEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the sap entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param sapEntry the sap entry
	 * @return the sap entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SAPEntry addSAPEntry(SAPEntry sapEntry) {
		sapEntry.setNew(true);

		return sapEntryPersistence.update(sapEntry);
	}

	/**
	 * Creates a new sap entry with the primary key. Does not add the sap entry to the database.
	 *
	 * @param sapEntryId the primary key for the new sap entry
	 * @return the new sap entry
	 */
	@Override
	@Transactional(enabled = false)
	public SAPEntry createSAPEntry(long sapEntryId) {
		return sapEntryPersistence.create(sapEntryId);
	}

	/**
	 * Deletes the sap entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sapEntryId the primary key of the sap entry
	 * @return the sap entry that was removed
	 * @throws PortalException if a sap entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SAPEntry deleteSAPEntry(long sapEntryId) throws PortalException {
		return sapEntryPersistence.remove(sapEntryId);
	}

	/**
	 * Deletes the sap entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sapEntry the sap entry
	 * @return the sap entry that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SAPEntry deleteSAPEntry(SAPEntry sapEntry) throws PortalException {
		return sapEntryPersistence.remove(sapEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			SAPEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return sapEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.service.access.policy.model.impl.SAPEntryModelImpl</code>.
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

		return sapEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.service.access.policy.model.impl.SAPEntryModelImpl</code>.
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

		return sapEntryPersistence.findWithDynamicQuery(
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
		return sapEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return sapEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SAPEntry fetchSAPEntry(long sapEntryId) {
		return sapEntryPersistence.fetchByPrimaryKey(sapEntryId);
	}

	/**
	 * Returns the sap entry with the matching UUID and company.
	 *
	 * @param uuid the sap entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching sap entry, or <code>null</code> if a matching sap entry could not be found
	 */
	@Override
	public SAPEntry fetchSAPEntryByUuidAndCompanyId(
		String uuid, long companyId) {

		return sapEntryPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the sap entry with the primary key.
	 *
	 * @param sapEntryId the primary key of the sap entry
	 * @return the sap entry
	 * @throws PortalException if a sap entry with the primary key could not be found
	 */
	@Override
	public SAPEntry getSAPEntry(long sapEntryId) throws PortalException {
		return sapEntryPersistence.findByPrimaryKey(sapEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(sapEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SAPEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("sapEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			sapEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SAPEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("sapEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(sapEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SAPEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("sapEntryId");
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

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SAPEntry>() {

				@Override
				public void performAction(SAPEntry sapEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, sapEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(SAPEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return sapEntryLocalService.deleteSAPEntry((SAPEntry)persistedModel);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return sapEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the sap entry with the matching UUID and company.
	 *
	 * @param uuid the sap entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching sap entry
	 * @throws PortalException if a matching sap entry could not be found
	 */
	@Override
	public SAPEntry getSAPEntryByUuidAndCompanyId(String uuid, long companyId)
		throws PortalException {

		return sapEntryPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns a range of all the sap entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.security.service.access.policy.model.impl.SAPEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of sap entries
	 * @param end the upper bound of the range of sap entries (not inclusive)
	 * @return the range of sap entries
	 */
	@Override
	public List<SAPEntry> getSAPEntries(int start, int end) {
		return sapEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of sap entries.
	 *
	 * @return the number of sap entries
	 */
	@Override
	public int getSAPEntriesCount() {
		return sapEntryPersistence.countAll();
	}

	/**
	 * Updates the sap entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param sapEntry the sap entry
	 * @return the sap entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SAPEntry updateSAPEntry(SAPEntry sapEntry) {
		return sapEntryPersistence.update(sapEntry);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SAPEntryLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		sapEntryLocalService = (SAPEntryLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SAPEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SAPEntry.class;
	}

	protected String getModelClassName() {
		return SAPEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = sapEntryPersistence.getDataSource();

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

	protected SAPEntryLocalService sapEntryLocalService;

	@Reference
	protected SAPEntryPersistence sapEntryPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourcePermissionLocalService
		resourcePermissionLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.RoleLocalService
		roleLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}