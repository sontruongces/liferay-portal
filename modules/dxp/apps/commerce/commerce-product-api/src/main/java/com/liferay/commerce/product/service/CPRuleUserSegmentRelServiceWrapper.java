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

package com.liferay.commerce.product.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPRuleUserSegmentRelService}.
 *
 * @author Marco Leo
 * @see CPRuleUserSegmentRelService
 * @generated
 */
@ProviderType
public class CPRuleUserSegmentRelServiceWrapper
	implements CPRuleUserSegmentRelService,
		ServiceWrapper<CPRuleUserSegmentRelService> {
	public CPRuleUserSegmentRelServiceWrapper(
		CPRuleUserSegmentRelService cpRuleUserSegmentRelService) {
		_cpRuleUserSegmentRelService = cpRuleUserSegmentRelService;
	}

	@Override
	public com.liferay.commerce.product.model.CPRuleUserSegmentRel addCPRuleUserSegmentRel(
		long cpRuleId, long commerceUserSegmentEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleUserSegmentRelService.addCPRuleUserSegmentRel(cpRuleId,
			commerceUserSegmentEntryId, serviceContext);
	}

	@Override
	public void deleteCPRuleUserSegmentRel(long cpRuleUserSegmentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_cpRuleUserSegmentRelService.deleteCPRuleUserSegmentRel(cpRuleUserSegmentRelId);
	}

	@Override
	public com.liferay.commerce.product.model.CPRuleUserSegmentRel getCPRuleUserSegmentRel(
		long cpRuleUserSegmentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleUserSegmentRelService.getCPRuleUserSegmentRel(cpRuleUserSegmentRelId);
	}

	@Override
	public java.util.List<com.liferay.commerce.product.model.CPRuleUserSegmentRel> getCPRuleUserSegmentRels(
		long cpRuleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.product.model.CPRuleUserSegmentRel> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleUserSegmentRelService.getCPRuleUserSegmentRels(cpRuleId,
			start, end, orderByComparator);
	}

	@Override
	public int getCPRuleUserSegmentRelsCount(long cpRuleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cpRuleUserSegmentRelService.getCPRuleUserSegmentRelsCount(cpRuleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpRuleUserSegmentRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CPRuleUserSegmentRelService getWrappedService() {
		return _cpRuleUserSegmentRelService;
	}

	@Override
	public void setWrappedService(
		CPRuleUserSegmentRelService cpRuleUserSegmentRelService) {
		_cpRuleUserSegmentRelService = cpRuleUserSegmentRelService;
	}

	private CPRuleUserSegmentRelService _cpRuleUserSegmentRelService;
}