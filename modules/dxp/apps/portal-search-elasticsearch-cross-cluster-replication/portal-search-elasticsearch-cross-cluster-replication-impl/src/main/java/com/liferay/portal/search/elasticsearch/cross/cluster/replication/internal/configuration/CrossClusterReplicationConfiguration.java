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

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Bryan Engler
 */
@ExtendedObjectClassDefinition(category = "search")
@Meta.OCD(
	id = "com.liferay.portal.search.elasticsearch.cross.cluster.replication.internal.configuration.CrossClusterReplicationConfiguration",
	localization = "content/Language",
	name = "cross-cluster-replication-configuration-name"
)
public interface CrossClusterReplicationConfiguration {

	@Meta.AD(
		deflt = "false", description = "cross-cluster-replication-enabled-help",
		name = "cross-cluster-replication-enabled", required = false
	)
	public boolean ccrEnabled();

	@Meta.AD(
		deflt = "LiferayElasticsearchCluster2",
		description = "cluster-name-help", name = "cluster-name",
		required = false
	)
	public String clusterName();

	@Meta.AD(
		deflt = "false",
		description = "client-transport-ignore-cluster-name-help",
		name = "client-transport-ignore-cluster-name", required = false
	)
	public boolean clientTransportIgnoreClusterName();

	@Meta.AD(
		deflt = "localhost:9301", description = "transport-addresses-help",
		name = "transport-addresses", required = false
	)
	public String[] transportAddresses();

	@Meta.AD(
		deflt = "http://localhost:9201",
		description = "network-host-address-help",
		name = "network-host-address", required = false
	)
	public String networkHostAddress();

	@Meta.AD(
		deflt = "leader", description = "remote-cluster-alias-help",
		name = "remote-cluster-alias", required = false
	)
	public String remoteClusterAlias();

	@Meta.AD(
		deflt = "false", description = "authentication-enabled-help",
		name = "authentication-enabled", required = false
	)
	public boolean authenticationEnabled();

	@Meta.AD(
		deflt = "elastic", description = "username-help", name = "username",
		required = false
	)
	public String username();

	@Meta.AD(
		description = "password-help", name = "password", required = false,
		type = Meta.Type.Password
	)
	public String password();

	@Meta.AD(
		deflt = "false", description = "transport-ssl-enabled-help",
		name = "transport-ssl-enabled", required = false
	)
	public boolean transportSSLEnabled();

	@Meta.AD(
		deflt = "certificate",
		description = "transport-ssl-verification-mode-help",
		name = "transport-ssl-verification-mode",
		optionValues = {"certificate", "full", "none"}, required = false
	)
	public String transportSSLVerificationMode();

	@Meta.AD(
		deflt = "PKCS#12", description = "certificate-format-help",
		name = "certificate-format", optionValues = {"PEM", "PKCS#12"},
		required = false
	)
	public String certificateFormat();

	@Meta.AD(
		deflt = "/path/to/elastic-certificates.p12",
		description = "ssl-keystore-path-help", name = "ssl-keystore-path",
		required = false
	)
	public String sslKeystorePath();

	@Meta.AD(
		description = "ssl-keystore-password-help",
		name = "ssl-keystore-password", required = false,
		type = Meta.Type.Password
	)
	public String sslKeystorePassword();

	@Meta.AD(
		deflt = "/path/to/elastic-certificates.p12",
		description = "ssl-truststore-path-help", name = "ssl-truststore-path",
		required = false
	)
	public String sslTruststorePath();

	@Meta.AD(
		description = "ssl-truststore-password-help",
		name = "ssl-truststore-password", required = false,
		type = Meta.Type.Password
	)
	public String sslTruststorePassword();

	@Meta.AD(
		deflt = "/path/to/instance.key", description = "ssl-key-path-help",
		name = "ssl-key-path", required = false
	)
	public String sslKeyPath();

	@Meta.AD(
		deflt = "/path/to/instance.crt",
		description = "ssl-certificate-path-help",
		name = "ssl-certificate-path", required = false
	)
	public String sslCertificatePath();

	@Meta.AD(
		deflt = "/path/to/ca.crt",
		description = "ssl-certificate-authorities-paths-help",
		name = "ssl-certificate-authorities-paths", required = false
	)
	public String[] sslCertificateAuthoritiesPaths();

}