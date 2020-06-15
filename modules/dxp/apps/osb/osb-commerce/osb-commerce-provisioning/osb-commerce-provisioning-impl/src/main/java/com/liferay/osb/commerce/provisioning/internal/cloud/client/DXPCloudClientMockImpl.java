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

package com.liferay.osb.commerce.provisioning.internal.cloud.client;

import com.liferay.osb.commerce.provisioning.internal.cloud.client.dto.PortalInstance;
import com.liferay.portal.instances.initializer.PortalInstanceInitializer;
import com.liferay.portal.instances.initializer.PortalInstanceInitializerRegistry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;

/**
 * @author Ivica Cardic
 */
public class DXPCloudClientMockImpl implements DXPCloudClient {

	public DXPCloudClientMockImpl(
		CompanyLocalService companyLocalService,
		PortalInstanceInitializerRegistry portalInstanceInitializerRegistry) {

		_companyLocalService = companyLocalService;
		_portalInstanceInitializerRegistry = portalInstanceInitializerRegistry;
	}

	@Override
	public void destroy() {
	}

	@Override
	public PortalInstance postPortalInstance(String portalInitializerKey) {
		PortalInstanceInitializer portalInstanceInitializer =
			_portalInstanceInitializerRegistry.getPortalInstanceInitializer(
				portalInitializerKey);

		String webId = _generateWebId();

		String virtualHostname = _toVirtualHostname(webId);

		try {
			portalInstanceInitializer.initialize(
				webId, virtualHostname, virtualHostname);

			return _toPortalInstance(webId);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private String _generateWebId() {
		return "commerce" + (_companyLocalService.getCompaniesCount() + 1);
	}

	private PortalInstance _toPortalInstance(String webId)
		throws PortalException {

		Company company = _companyLocalService.getCompanyByWebId(webId);

		PortalInstance portalInstance = new PortalInstance();

		portalInstance.setVirtualHostname(company.getVirtualHostname());
		portalInstance.setWebId(company.getWebId());

		return portalInstance;
	}

	private String _toVirtualHostname(String webId) {
		return webId + ".test";
	}

	private final CompanyLocalService _companyLocalService;
	private final PortalInstanceInitializerRegistry
		_portalInstanceInitializerRegistry;

}