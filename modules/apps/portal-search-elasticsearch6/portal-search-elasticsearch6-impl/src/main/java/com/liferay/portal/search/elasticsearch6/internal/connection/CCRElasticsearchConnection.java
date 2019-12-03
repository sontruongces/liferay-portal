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

package com.liferay.portal.search.elasticsearch6.internal.connection;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch6.internal.settings.SettingsBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.Map;
import java.util.Set;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Bryan Engler
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.CrossClusterReplicationConfiguration",
	immediate = true, property = "operation.mode=CCR",
	service = ElasticsearchConnection.class
)
public class CCRElasticsearchConnection extends BaseElasticsearchConnection {

	public static final String CONNECTION_ID = "CCR";

	@Override
	public String getConnectionId() {
		return CONNECTION_ID;
	}

	@Override
	public OperationMode getOperationMode() {
		return OperationMode.REMOTE;
	}

	public void setElasticsearchConfiguration(
		ElasticsearchConfiguration elasticsearchConfiguration) {

		this.elasticsearchConfiguration = elasticsearchConfiguration;

		reconnect();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		reconnect();
	}

	protected void addTransportAddress(
			TransportClient transportClient, String transportAddress)
		throws UnknownHostException {

		String[] transportAddressParts = StringUtil.split(
			transportAddress, StringPool.COLON);

		String host = transportAddressParts[0];

		int port = GetterUtil.getInteger(transportAddressParts[1]);

		InetAddress inetAddress = InetAddressUtil.getInetAddressByName(host);

		transportClient.addTransportAddress(
			new TransportAddress(inetAddress, port));
	}

	protected void configureAuthentication(SettingsBuilder settingsBuilder) {
		String user =
			crossClusterReplicationConfigurationWrapper.getUsername() + ":" +
				crossClusterReplicationConfigurationWrapper.getPassword();

		settingsBuilder.put("xpack.security.user", user);
	}

	protected void configurePEMPaths(SettingsBuilder settingsBuilder) {
		settingsBuilder.put(
			"xpack.security.transport.ssl.certificate",
			crossClusterReplicationConfigurationWrapper.
				getSslCertificatePath());
		settingsBuilder.putList(
			"xpack.security.transport.ssl.certificate_authorities",
			crossClusterReplicationConfigurationWrapper.
				getSslCertificateAuthoritiesPaths());
		settingsBuilder.put(
			"xpack.security.transport.ssl.key",
			crossClusterReplicationConfigurationWrapper.getSslKeyPath());
	}

	protected void configurePKCSPaths(SettingsBuilder settingsBuilder) {
		settingsBuilder.put(
			"xpack.security.transport.ssl.keystore.password",
			crossClusterReplicationConfigurationWrapper.
				getSslKeystorePassword());
		settingsBuilder.put(
			"xpack.security.transport.ssl.keystore.path",
			crossClusterReplicationConfigurationWrapper.getSslKeystorePath());
		settingsBuilder.put(
			"xpack.security.transport.ssl.truststore.password",
			crossClusterReplicationConfigurationWrapper.
				getSslTruststorePassword());
		settingsBuilder.put(
			"xpack.security.transport.ssl.truststore.path",
			crossClusterReplicationConfigurationWrapper.getSslTruststorePath());
	}

	protected void configureSSL(SettingsBuilder settingsBuilder) {
		settingsBuilder.put("xpack.security.transport.ssl.enabled", "true");
		settingsBuilder.put(
			"xpack.security.transport.ssl.verification_mode",
			StringUtil.toLowerCase(
				crossClusterReplicationConfigurationWrapper.
					getTransportSSLVerificationMode()));

		String certificateFormat =
			crossClusterReplicationConfigurationWrapper.getCertificateFormat();

		if (certificateFormat.equals("PKCS#12")) {
			configurePKCSPaths(settingsBuilder);
		}
		else {
			configurePEMPaths(settingsBuilder);
		}
	}

	@Override
	protected Client createClient() {
		Set<String> transportAddresses = SetUtil.fromArray(
			crossClusterReplicationConfigurationWrapper.
				getTransportAddresses());

		if (transportAddresses.isEmpty()) {
			throw new IllegalStateException(
				"There must be at least one transport address");
		}

		Thread thread = Thread.currentThread();

		ClassLoader contextClassLoader = thread.getContextClassLoader();

		Class<?> clazz = getClass();

		thread.setContextClassLoader(clazz.getClassLoader());

		try {
			TransportClient transportClient = createTransportClient();

			for (String transportAddress : transportAddresses) {
				try {
					addTransportAddress(transportClient, transportAddress);
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to add transport address " +
								transportAddress,
							exception);
					}
				}
			}

			return transportClient;
		}
		finally {
			thread.setContextClassLoader(contextClassLoader);
		}
	}

	protected TransportClient createTransportClient() {
		if (crossClusterReplicationConfigurationWrapper.
				isAuthenticationEnabled()) {

			configureAuthentication(settingsBuilder);

			if (crossClusterReplicationConfigurationWrapper.
					isTransportSSLEnabled()) {

				configureSSL(settingsBuilder);
			}
		}

		Settings settings = settingsBuilder.build();

		if (_log.isDebugEnabled()) {
			_log.debug("Settings: " + settings.toString());
		}

		if (crossClusterReplicationConfigurationWrapper.
				isAuthenticationEnabled()) {

			return new PreBuiltXPackTransportClient(settings);
		}

		return new PreBuiltTransportClient(settings);
	}

	@Deactivate
	protected void deactivate(Map<String, Object> properties) {
		close();
	}

	protected boolean isCrossClusterReplicationEnabled() {
		if (crossClusterReplicationConfigurationWrapper == null) {
			return false;
		}

		return crossClusterReplicationConfigurationWrapper.isCCREnabled();
	}

	@Override
	protected void loadRequiredDefaultConfigurations() {
		settingsBuilder.put(
			"client.transport.ignore_cluster_name",
			crossClusterReplicationConfigurationWrapper.
				isClientTransportIgnoreClusterName());
		settingsBuilder.put(
			"client.transport.nodes_sampler_interval",
			elasticsearchConfiguration.clientTransportNodesSamplerInterval());
		settingsBuilder.put(
			"client.transport.ping_timeout",
			elasticsearchConfiguration.clientTransportPingTimeout());
		settingsBuilder.put(
			"client.transport.sniff",
			elasticsearchConfiguration.clientTransportSniff());
		settingsBuilder.put(
			"cluster.name",
			crossClusterReplicationConfigurationWrapper.getClusterName());
		settingsBuilder.put(
			"request.headers.X-Found-Cluster",
			crossClusterReplicationConfigurationWrapper.getClusterName());
	}

	protected void reconnect() {
		if (isConnected()) {
			close();
		}

		if (elasticsearchConfiguration == null) {
			return;
		}

		if (!isConnected() &&
			(elasticsearchConfiguration.operationMode() ==
				com.liferay.portal.search.elasticsearch6.configuration.
					OperationMode.REMOTE) &&
			isCrossClusterReplicationEnabled()) {

			connect();
		}
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference
	protected Props props;

	private static final Log _log = LogFactoryUtil.getLog(
		CCRElasticsearchConnection.class);

}