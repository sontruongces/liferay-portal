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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPOptionCategoryService}.
 *
 * @author Marco Leo
 * @see CPOptionCategoryService
 * @generated
 */
@ProviderType
public class CPOptionCategoryServiceWrapper implements CPOptionCategoryService,
	ServiceWrapper<CPOptionCategoryService> {
	public CPOptionCategoryServiceWrapper(
		CPOptionCategoryService cpOptionCategoryService) {
		_cpOptionCategoryService = cpOptionCategoryService;
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory addCPOptionCategory(
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		double priority,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.addCPOptionCategory(name, titleMap,
			descriptionMap, priority, serviceContext);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory deleteCPOptionCategory(
		com.liferay.commerce.product.model.CPOptionCategory cpOptionCategory)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.deleteCPOptionCategory(cpOptionCategory);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory deleteCPOptionCategory(
		long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.deleteCPOptionCategory(cpOptionCategoryId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory fetchCPOptionCategory(
		long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.fetchCPOptionCategory(cpOptionCategoryId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory getCPOptionCategory(
		long cpOptionCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.getCPOptionCategory(cpOptionCategoryId);
	}

	@Override
	public com.liferay.commerce.product.model.CPOptionCategory updateCPOptionCategory(
		long cpOptionCategoryId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		double priority,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpOptionCategoryService.updateCPOptionCategory(cpOptionCategoryId,
			name, titleMap, descriptionMap, priority, serviceContext);
	}

	@Override
	public int getCPOptionCategoriesCount(long groupId) {
		return _cpOptionCategoryService.getCPOptionCategoriesCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cpOptionCategoryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOptionCategory> getCPOptionCategories(
		long groupId, int start, int end) {
		return _cpOptionCategoryService.getCPOptionCategories(groupId, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPOptionCategory> getCPOptionCategories(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPOptionCategory> orderByComparator) {
		return _cpOptionCategoryService.getCPOptionCategories(groupId, start,
			end, orderByComparator);
	}

	@Override
	public CPOptionCategoryService getWrappedService() {
		return _cpOptionCategoryService;
	}

	@Override
	public void setWrappedService(
		CPOptionCategoryService cpOptionCategoryService) {
		_cpOptionCategoryService = cpOptionCategoryService;
	}

	private CPOptionCategoryService _cpOptionCategoryService;
}