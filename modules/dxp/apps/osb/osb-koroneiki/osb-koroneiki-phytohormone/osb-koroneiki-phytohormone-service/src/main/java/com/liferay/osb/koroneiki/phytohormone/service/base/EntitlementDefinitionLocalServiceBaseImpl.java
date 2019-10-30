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

package com.liferay.osb.koroneiki.phytohormone.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.EntitlementDefinitionPersistence;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.EntitlementPersistence;
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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the entitlement definition local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionLocalServiceImpl
 * @generated
 */
public abstract class EntitlementDefinitionLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, EntitlementDefinitionLocalService,
			   IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>EntitlementDefinitionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalServiceUtil</code>.
	 */

	/**
	 * Adds the entitlement definition to the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinition the entitlement definition
	 * @return the entitlement definition that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EntitlementDefinition addEntitlementDefinition(
		EntitlementDefinition entitlementDefinition) {

		entitlementDefinition.setNew(true);

		return entitlementDefinitionPersistence.update(entitlementDefinition);
	}

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	@Override
	@Transactional(enabled = false)
	public EntitlementDefinition createEntitlementDefinition(
		long entitlementDefinitionId) {

		return entitlementDefinitionPersistence.create(entitlementDefinitionId);
	}

	/**
	 * Deletes the entitlement definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws PortalException if a entitlement definition with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		return entitlementDefinitionPersistence.remove(entitlementDefinitionId);
	}

	/**
	 * Deletes the entitlement definition from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinition the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public EntitlementDefinition deleteEntitlementDefinition(
			EntitlementDefinition entitlementDefinition)
		throws PortalException {

		return entitlementDefinitionPersistence.remove(entitlementDefinition);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			EntitlementDefinition.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return entitlementDefinitionPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
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

		return entitlementDefinitionPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
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

		return entitlementDefinitionPersistence.findWithDynamicQuery(
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
		return entitlementDefinitionPersistence.countWithDynamicQuery(
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

		return entitlementDefinitionPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public EntitlementDefinition fetchEntitlementDefinition(
		long entitlementDefinitionId) {

		return entitlementDefinitionPersistence.fetchByPrimaryKey(
			entitlementDefinitionId);
	}

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchEntitlementDefinitionByUuidAndCompanyId(
		String uuid, long companyId) {

		return entitlementDefinitionPersistence.fetchByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns the entitlement definition with the primary key.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws PortalException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition getEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		return entitlementDefinitionPersistence.findByPrimaryKey(
			entitlementDefinitionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			entitlementDefinitionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(EntitlementDefinition.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"entitlementDefinitionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			entitlementDefinitionLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			EntitlementDefinition.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"entitlementDefinitionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			entitlementDefinitionLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(EntitlementDefinition.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"entitlementDefinitionId");
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

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<EntitlementDefinition>() {

				@Override
				public void performAction(
						EntitlementDefinition entitlementDefinition)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, entitlementDefinition);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					EntitlementDefinition.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return entitlementDefinitionLocalService.deleteEntitlementDefinition(
			(EntitlementDefinition)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return entitlementDefinitionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the entitlement definition with the matching UUID and company.
	 *
	 * @param uuid the entitlement definition's UUID
	 * @param companyId the primary key of the company
	 * @return the matching entitlement definition
	 * @throws PortalException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition getEntitlementDefinitionByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return entitlementDefinitionPersistence.findByUuid_C_First(
			uuid, companyId, null);
	}

	/**
	 * Returns a range of all the entitlement definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of entitlement definitions
	 */
	@Override
	public List<EntitlementDefinition> getEntitlementDefinitions(
		int start, int end) {

		return entitlementDefinitionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	@Override
	public int getEntitlementDefinitionsCount() {
		return entitlementDefinitionPersistence.countAll();
	}

	/**
	 * Updates the entitlement definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinition the entitlement definition
	 * @return the entitlement definition that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public EntitlementDefinition updateEntitlementDefinition(
		EntitlementDefinition entitlementDefinition) {

		return entitlementDefinitionPersistence.update(entitlementDefinition);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			EntitlementDefinitionLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		entitlementDefinitionLocalService =
			(EntitlementDefinitionLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return EntitlementDefinitionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return EntitlementDefinition.class;
	}

	protected String getModelClassName() {
		return EntitlementDefinition.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				entitlementDefinitionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Reference
	protected EntitlementPersistence entitlementPersistence;

	protected EntitlementDefinitionLocalService
		entitlementDefinitionLocalService;

	@Reference
	protected EntitlementDefinitionPersistence entitlementDefinitionPersistence;

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
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}