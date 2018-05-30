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

import com.liferay.commerce.user.segment.model.CommerceUserSegmentCriterion;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionService;
import com.liferay.commerce.user.segment.service.persistence.CommerceUserSegmentCriterionPersistence;
import com.liferay.commerce.user.segment.service.persistence.CommerceUserSegmentEntryPersistence;

import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce user segment criterion remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.user.segment.service.impl.CommerceUserSegmentCriterionServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.user.segment.service.impl.CommerceUserSegmentCriterionServiceImpl
 * @see com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionServiceUtil
 * @generated
 */
public abstract class CommerceUserSegmentCriterionServiceBaseImpl
	extends BaseServiceImpl implements CommerceUserSegmentCriterionService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.commerce.user.segment.service.CommerceUserSegmentCriterionServiceUtil} to access the commerce user segment criterion remote service.
	 */

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
	 * Returns the commerce user segment criterion remote service.
	 *
	 * @return the commerce user segment criterion remote service
	 */
	public CommerceUserSegmentCriterionService getCommerceUserSegmentCriterionService() {
		return commerceUserSegmentCriterionService;
	}

	/**
	 * Sets the commerce user segment criterion remote service.
	 *
	 * @param commerceUserSegmentCriterionService the commerce user segment criterion remote service
	 */
	public void setCommerceUserSegmentCriterionService(
		CommerceUserSegmentCriterionService commerceUserSegmentCriterionService) {
		this.commerceUserSegmentCriterionService = commerceUserSegmentCriterionService;
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
	public com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService getCommerceUserSegmentEntryLocalService() {
		return commerceUserSegmentEntryLocalService;
	}

	/**
	 * Sets the commerce user segment entry local service.
	 *
	 * @param commerceUserSegmentEntryLocalService the commerce user segment entry local service
	 */
	public void setCommerceUserSegmentEntryLocalService(
		com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService commerceUserSegmentEntryLocalService) {
		this.commerceUserSegmentEntryLocalService = commerceUserSegmentEntryLocalService;
	}

	/**
	 * Returns the commerce user segment entry remote service.
	 *
	 * @return the commerce user segment entry remote service
	 */
	public com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService getCommerceUserSegmentEntryService() {
		return commerceUserSegmentEntryService;
	}

	/**
	 * Sets the commerce user segment entry remote service.
	 *
	 * @param commerceUserSegmentEntryService the commerce user segment entry remote service
	 */
	public void setCommerceUserSegmentEntryService(
		com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService commerceUserSegmentEntryService) {
		this.commerceUserSegmentEntryService = commerceUserSegmentEntryService;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {
		this.userService = userService;
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
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceUserSegmentCriterionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceUserSegmentCriterion.class;
	}

	protected String getModelClassName() {
		return CommerceUserSegmentCriterion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = commerceUserSegmentCriterionPersistence.getDataSource();

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
	@BeanReference(type = CommerceUserSegmentCriterionService.class)
	protected CommerceUserSegmentCriterionService commerceUserSegmentCriterionService;
	@BeanReference(type = CommerceUserSegmentCriterionPersistence.class)
	protected CommerceUserSegmentCriterionPersistence commerceUserSegmentCriterionPersistence;
	@BeanReference(type = com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService.class)
	protected com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalService commerceUserSegmentEntryLocalService;
	@BeanReference(type = com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService.class)
	protected com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryService commerceUserSegmentEntryService;
	@BeanReference(type = CommerceUserSegmentEntryPersistence.class)
	protected CommerceUserSegmentEntryPersistence commerceUserSegmentEntryPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameService.class)
	protected com.liferay.portal.kernel.service.ClassNameService classNameService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserService.class)
	protected com.liferay.portal.kernel.service.UserService userService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService expandoRowLocalService;
	@ServiceReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;
}