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

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntitlementDefinitionService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionService
 * @generated
 */
public class EntitlementDefinitionServiceWrapper
	implements EntitlementDefinitionService,
			   ServiceWrapper<EntitlementDefinitionService> {

	public EntitlementDefinitionServiceWrapper(
		EntitlementDefinitionService entitlementDefinitionService) {

		_entitlementDefinitionService = entitlementDefinitionService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntitlementDefinitionServiceUtil} to access the entitlement definition remote service. Add custom service methods to <code>com.liferay.osb.koroneiki.phytohormone.service.impl.EntitlementDefinitionServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			addEntitlementDefinition(
				long classNameId, String name, String description,
				String definition, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionService.addEntitlementDefinition(
			classNameId, name, description, definition, status);
	}

	@Override
	public com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
			deleteEntitlementDefinition(long entitlementDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _entitlementDefinitionService.deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entitlementDefinitionService.getOSGiServiceIdentifier();
	}

	@Override
	public void synchronizeEntitlementDefinition(long entitlementDefinitionId)
		throws Exception {

		_entitlementDefinitionService.synchronizeEntitlementDefinition(
			entitlementDefinitionId);
	}

	@Override
	public EntitlementDefinitionService getWrappedService() {
		return _entitlementDefinitionService;
	}

	@Override
	public void setWrappedService(
		EntitlementDefinitionService entitlementDefinitionService) {

		_entitlementDefinitionService = entitlementDefinitionService;
	}

	private EntitlementDefinitionService _entitlementDefinitionService;

}