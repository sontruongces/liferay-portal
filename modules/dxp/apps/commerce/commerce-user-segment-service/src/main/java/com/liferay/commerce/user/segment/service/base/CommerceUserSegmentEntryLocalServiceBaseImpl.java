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

package com.liferay.commerce.user.segment.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService;
import com.liferay.commerce.user.segment.service.persistence.CommerceUserSegmentCriterionPersistence;
import com.liferay.commerce.user.segment.service.persistence.CommerceUserSegmentEntryPersistence;

import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;

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
import com.liferay.portal.kernel.service.persistence.OrganizationPersistence;
import com.liferay.portal.kernel.service.persistence.RolePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce user segment entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.user.segment.service.impl.CommerceUserSegmentEntryLocalServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.user.segment.service.impl.CommerceUserSegmentEntryLocalServiceImpl
 * @see com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class CommerceUserSegmentEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CommerceUserSegmentEntryLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalServiceUtil} to access the commerce user segment entry local service.
	 */

	/**
	 * Adds the commerce user segment entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceUserSegmentEntry the commerce user segment entry
	 * @return the commerce user segment entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceUserSegmentEntry addCommerceUserSegmentEntry(
		CommerceUserSegmentEntry commerceUserSegmentEntry) {
		commerceUserSegmentEntry.setNew(true);

		return commerceUserSegmentEntryPersistence.update(commerceUserSegmentEntry);
	}

	/**
	 * Creates a new commerce user segment entry with the primary key. Does not add the commerce user segment entry to the database.
	 *
	 * @param commerceUserSegmentEntryId the primary key for the new commerce user segment entry
	 * @return the new commerce user segment entry
	 */
	@Override
	@Transactional(enabled = false)
	public CommerceUserSegmentEntry createCommerceUserSegmentEntry(
		long commerceUserSegmentEntryId) {
		return commerceUserSegmentEntryPersistence.create(commerceUserSegmentEntryId);
	}

	/**
	 * Deletes the commerce user segment entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceUserSegmentEntryId the primary key of the commerce user segment entry
	 * @return the commerce user segment entry that was removed
	 * @throws PortalException if a commerce user segment entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceUserSegmentEntry deleteCommerceUserSegmentEntry(
		long commerceUserSegmentEntryId) throws PortalException {
		return commerceUserSegmentEntryPersistence.remove(commerceUserSegmentEntryId);
	}

	/**
	 * Deletes the commerce user segment entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceUserSegmentEntry the commerce user segment entry
	 * @return the commerce user segment entry that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommerceUserSegmentEntry deleteCommerceUserSegmentEntry(
		CommerceUserSegmentEntry commerceUserSegmentEntry)
		throws PortalException {
		return commerceUserSegmentEntryPersistence.remove(commerceUserSegmentEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CommerceUserSegmentEntry.class,
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
		return commerceUserSegmentEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.user.segment.model.impl.CommerceUserSegmentEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return commerceUserSegmentEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.user.segment.model.impl.CommerceUserSegmentEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return commerceUserSegmentEntryPersistence.findWithDynamicQuery(dynamicQuery,
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
		return commerceUserSegmentEntryPersistence.countWithDynamicQuery(dynamicQuery);
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
		return commerceUserSegmentEntryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CommerceUserSegmentEntry fetchCommerceUserSegmentEntry(
		long commerceUserSegmentEntryId) {
		return commerceUserSegmentEntryPersistence.fetchByPrimaryKey(commerceUserSegmentEntryId);
	}

	/**
	 * Returns the commerce user segment entry with the primary key.
	 *
	 * @param commerceUserSegmentEntryId the primary key of the commerce user segment entry
	 * @return the commerce user segment entry
	 * @throws PortalException if a commerce user segment entry with the primary key could not be found
	 */
	@Override
	public CommerceUserSegmentEntry getCommerceUserSegmentEntry(
		long commerceUserSegmentEntryId) throws PortalException {
		return commerceUserSegmentEntryPersistence.findByPrimaryKey(commerceUserSegmentEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(commerceUserSegmentEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceUserSegmentEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceUserSegmentEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(commerceUserSegmentEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(CommerceUserSegmentEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceUserSegmentEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(commerceUserSegmentEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(CommerceUserSegmentEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"commerceUserSegmentEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return commerceUserSegmentEntryLocalService.deleteCommerceUserSegmentEntry((CommerceUserSegmentEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return commerceUserSegmentEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the commerce user segment entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.commerce.user.segment.model.impl.CommerceUserSegmentEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce user segment entries
	 * @param end the upper bound of the range of commerce user segment entries (not inclusive)
	 * @return the range of commerce user segment entries
	 */
	@Override
	public List<CommerceUserSegmentEntry> getCommerceUserSegmentEntries(
		int start, int end) {
		return commerceUserSegmentEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of commerce user segment entries.
	 *
	 * @return the number of commerce user segment entries
	 */
	@Override
	public int getCommerceUserSegmentEntriesCount() {
		return commerceUserSegmentEntryPersistence.countAll();
	}

	/**
	 * Updates the commerce user segment entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commerceUserSegmentEntry the commerce user segment entry
	 * @return the commerce user segment entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommerceUserSegmentEntry updateCommerceUserSegmentEntry(
		CommerceUserSegmentEntry commerceUserSegmentEntry) {
		return commerceUserSegmentEntryPersistence.update(commerceUserSegmentEntry);
	}

	/**
	 * Returns the commerce user segment criterion local service.
	 *
	 * @return the commerce user segment criterion local service
	 */
	public com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService getCommerceUserSegmentCriterionLocalService() {
		return commerceUserSegmentCriterionLocalService;
	}

	/**
	 * Sets the commerce user segment criterion local service.
	 *
	 * @param commerceUserSegmentCriterionLocalService the commerce user segment criterion local service
	 */
	public void setCommerceUserSegmentCriterionLocalService(
		com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService commerceUserSegmentCriterionLocalService) {
		this.commerceUserSegmentCriterionLocalService = commerceUserSegmentCriterionLocalService;
	}

	/**
	 * Returns the commerce user segment criterion persistence.
	 *
	 * @return the commerce user segment criterion persistence
	 */
	public CommerceUserSegmentCriterionPersistence getCommerceUserSegmentCriterionPersistence() {
		return commerceUserSegmentCriterionPersistence;
	}

	/**
	 * Sets the commerce user segment criterion persistence.
	 *
	 * @param commerceUserSegmentCriterionPersistence the commerce user segment criterion persistence
	 */
	public void setCommerceUserSegmentCriterionPersistence(
		CommerceUserSegmentCriterionPersistence commerceUserSegmentCriterionPersistence) {
		this.commerceUserSegmentCriterionPersistence = commerceUserSegmentCriterionPersistence;
	}

	/**
	 * Returns the commerce user segment entry local service.
	 *
	 * @return the commerce user segment entry local service
	 */
	public CommerceUserSegmentEntryLocalService getCommerceUserSegmentEntryLocalService() {
		return commerceUserSegmentEntryLocalService;
	}

	/**
	 * Sets the commerce user segment entry local service.
	 *
	 * @param commerceUserSegmentEntryLocalService the commerce user segment entry local service
	 */
	public void setCommerceUserSegmentEntryLocalService(
		CommerceUserSegmentEntryLocalService commerceUserSegmentEntryLocalService) {
		this.commerceUserSegmentEntryLocalService = commerceUserSegmentEntryLocalService;
	}

	/**
	 * Returns the commerce user segment entry persistence.
	 *
	 * @return the commerce user segment entry persistence
	 */
	public CommerceUserSegmentEntryPersistence getCommerceUserSegmentEntryPersistence() {
		return commerceUserSegmentEntryPersistence;
	}

	/**
	 * Sets the commerce user segment entry persistence.
	 *
	 * @param commerceUserSegmentEntryPersistence the commerce user segment entry persistence
	 */
	public void setCommerceUserSegmentEntryPersistence(
		CommerceUserSegmentEntryPersistence commerceUserSegmentEntryPersistence) {
		this.commerceUserSegmentEntryPersistence = commerceUserSegmentEntryPersistence;
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
	 * Returns the organization local service.
	 *
	 * @return the organization local service
	 */
	public com.liferay.portal.kernel.service.OrganizationLocalService getOrganizationLocalService() {
		return organizationLocalService;
	}

	/**
	 * Sets the organization local service.
	 *
	 * @param organizationLocalService the organization local service
	 */
	public void setOrganizationLocalService(
		com.liferay.portal.kernel.service.OrganizationLocalService organizationLocalService) {
		this.organizationLocalService = organizationLocalService;
	}

	/**
	 * Returns the organization persistence.
	 *
	 * @return the organization persistence
	 */
	public OrganizationPersistence getOrganizationPersistence() {
		return organizationPersistence;
	}

	/**
	 * Sets the organization persistence.
	 *
	 * @param organizationPersistence the organization persistence
	 */
	public void setOrganizationPersistence(
		OrganizationPersistence organizationPersistence) {
		this.organizationPersistence = organizationPersistence;
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
	 * Returns the role local service.
	 *
	 * @return the role local service
	 */
	public com.liferay.portal.kernel.service.RoleLocalService getRoleLocalService() {
		return roleLocalService;
	}

	/**
	 * Sets the role local service.
	 *
	 * @param roleLocalService the role local service
	 */
	public void setRoleLocalService(
		com.liferay.portal.kernel.service.RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	/**
	 * Returns the role persistence.
	 *
	 * @return the role persistence
	 */
	public RolePersistence getRolePersistence() {
		return rolePersistence;
	}

	/**
	 * Sets the role persistence.
	 *
	 * @param rolePersistence the role persistence
	 */
	public void setRolePersistence(RolePersistence rolePersistence) {
		this.rolePersistence = rolePersistence;
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

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService getExpandoRowLocalService() {
		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService expandoRowLocalService) {
		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {
		this.expandoRowPersistence = expandoRowPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry",
			commerceUserSegmentEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.commerce.user.segment.model.CommerceUserSegmentEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceUserSegmentEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceUserSegmentEntry.class;
	}

	protected String getModelClassName() {
		return CommerceUserSegmentEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = commerceUserSegmentEntryPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService.class)
	protected com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionLocalService commerceUserSegmentCriterionLocalService;
	@BeanReference(type = CommerceUserSegmentCriterionPersistence.class)
	protected CommerceUserSegmentCriterionPersistence commerceUserSegmentCriterionPersistence;
	@BeanReference(type = CommerceUserSegmentEntryLocalService.class)
	protected CommerceUserSegmentEntryLocalService commerceUserSegmentEntryLocalService;
	@BeanReference(type = CommerceUserSegmentEntryPersistence.class)
	protected CommerceUserSegmentEntryPersistence commerceUserSegmentEntryPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.OrganizationLocalService.class)
	protected com.liferay.portal.kernel.service.OrganizationLocalService organizationLocalService;
	@ServiceReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.RoleLocalService.class)
	protected com.liferay.portal.kernel.service.RoleLocalService roleLocalService;
	@ServiceReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService expandoRowLocalService;
	@ServiceReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}