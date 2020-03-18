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

package com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.CrossClusterReplicationConfiguration",
	immediate = true,
	service = CrossClusterReplicationConfigurationWrapper.class
)
public class CrossClusterReplicationConfigurationWrapperImpl
	implements CrossClusterReplicationConfigurationWrapper {

	@Override
	public String getCertificateFormat() {
		return crossClusterReplicationConfiguration.certificateFormat();
	}

	@Override
	public String getClusterName() {
		return crossClusterReplicationConfiguration.clusterName();
	}

	@Override
	public String getNetworkHostAddress() {
		return crossClusterReplicationConfiguration.networkHostAddress();
	}

	@Override
	public String getPassword() {
		return crossClusterReplicationConfiguration.password();
	}

	@Override
	public String getRemoteClusterAlias() {
		return crossClusterReplicationConfiguration.remoteClusterAlias();
	}

	@Override
	public String[] getSslCertificateAuthoritiesPaths() {
		return crossClusterReplicationConfiguration.
			sslCertificateAuthoritiesPaths();
	}

	@Override
	public String getSslCertificatePath() {
		return crossClusterReplicationConfiguration.sslCertificatePath();
	}

	@Override
	public String getSslKeyPath() {
		return crossClusterReplicationConfiguration.sslKeyPath();
	}

	@Override
	public String getSslKeystorePassword() {
		return crossClusterReplicationConfiguration.sslKeystorePassword();
	}

	@Override
	public String getSslKeystorePath() {
		return crossClusterReplicationConfiguration.sslKeystorePath();
	}

	@Override
	public String getSslTruststorePassword() {
		return crossClusterReplicationConfiguration.sslTruststorePassword();
	}

	@Override
	public String getSslTruststorePath() {
		return crossClusterReplicationConfiguration.sslTruststorePath();
	}

	@Override
	public String[] getTransportAddresses() {
		return crossClusterReplicationConfiguration.transportAddresses();
	}

	@Override
	public String getTransportSSLVerificationMode() {
		return crossClusterReplicationConfiguration.
			transportSSLVerificationMode();
	}

	@Override
	public String getUsername() {
		return crossClusterReplicationConfiguration.username();
	}

	@Override
	public boolean isAuthenticationEnabled() {
		return crossClusterReplicationConfiguration.authenticationEnabled();
	}

	@Override
	public boolean isCCREnabled() {
		return crossClusterReplicationConfiguration.ccrEnabled();
	}

	@Override
	public boolean isClientTransportIgnoreClusterName() {
		return crossClusterReplicationConfiguration.
			clientTransportIgnoreClusterName();
	}

	@Override
	public boolean isTransportSSLEnabled() {
		return crossClusterReplicationConfiguration.transportSSLEnabled();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		crossClusterReplicationConfiguration =
			ConfigurableUtil.createConfigurable(
				CrossClusterReplicationConfiguration.class, properties);
	}

	protected volatile CrossClusterReplicationConfiguration
		crossClusterReplicationConfiguration;

}