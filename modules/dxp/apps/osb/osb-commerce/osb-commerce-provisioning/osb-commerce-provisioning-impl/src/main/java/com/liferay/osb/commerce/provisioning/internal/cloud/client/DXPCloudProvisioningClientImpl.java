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

import com.fasterxml.jackson.core.type.TypeReference;

import com.liferay.osb.commerce.provisioning.internal.cloud.client.dto.PortalInstance;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.List;

/**
 * @author Ivica Cardic
 */
public class DXPCloudProvisioningClientImpl
	extends BaseClientImpl implements DXPCloudProvisioningClient {

	public DXPCloudProvisioningClientImpl(
		String dxpCloudAPIURL, String password, String username) {

		super(dxpCloudAPIURL, password, username);
	}

	@Override
	public void deletePortalInstance(String portalInstanceId) {
		executeDelete(_getProvisioningPortalInstancesURI(portalInstanceId));
	}

	@Override
	public PortalInstance getPortalInstance(String portalInstanceId) {
		return executeGet(
			PortalInstance.class,
			_getProvisioningPortalInstancesURI(portalInstanceId));
	}

	@Override
	public List<PortalInstance> getPortalInstances() {
		return executeGet(
			new TypeReference<List<PortalInstance>>() {
			},
			_getProvisioningPortalInstancesURI());
	}

	@Override
	public PortalInstance postPortalInstance(
		String domain, String portalInitializerKey) {

		return executePost(
			HashMapBuilder.put(
				"domain", domain
			).build(),
			PortalInstance.class, _getProvisioningPortalInstancesURI());
	}

	@Override
	public PortalInstance updatePortalInstance(
		String domain, String portalInstanceId) {

		return executeUpdate(
			HashMapBuilder.put(
				"domain", domain
			).build(),
			PortalInstance.class,
			_getProvisioningPortalInstancesURI(portalInstanceId));
	}

	private String _getProvisioningPortalInstancesURI() {
		return dxpCloudAPIURL + _PROVISIONING_SAAS_PORTAL_INSTANCES_PATH;
	}

	private String _getProvisioningPortalInstancesURI(String portalInstanceId) {
		return StringBundler.concat(
			dxpCloudAPIURL, _PROVISIONING_SAAS_PORTAL_INSTANCES_PATH, "/",
			portalInstanceId);
	}

	private static final String _PROVISIONING_SAAS_PORTAL_INSTANCES_PATH =
		"/provisioning/saas/portal-instances";

}