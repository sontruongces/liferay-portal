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

package com.liferay.portal.search.elasticsearch6.internal.ccr;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.ccr.CrossClusterReplicationHelper;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.configuration.ElasticsearchConnectionConfigurationWrapper;
import com.liferay.portal.search.elasticsearch6.internal.connection.CCRElasticsearchConnection;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;

import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.client.CcrClient;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ccr.PauseFollowRequest;
import org.elasticsearch.client.ccr.PutFollowRequest;
import org.elasticsearch.client.ccr.UnfollowRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Bryan Engler
 */
@Component(immediate = true, service = CrossClusterReplicationHelper.class)
public class CrossClusterReplicationHelperImpl
	implements CrossClusterReplicationHelper {

	@Override
	public void follow(String indexName) {
		if (!elasticsearchConnectionManager.
				isCrossClusterReplicationEnabled()) {

			return;
		}

		try {
			_putFollow(indexName);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to follow the index ", indexName, " in the ",
						crossClusterReplicationConfigurationWrapper.
							getRemoteClusterAlias(),
						" cluster"),
					exception);
			}
		}
	}

	@Override
	public void unfollow(String indexName) {
		if (!elasticsearchConnectionManager.
				isCrossClusterReplicationEnabled()) {

			return;
		}

		try {
			_pauseFollow(indexName);

			_closeIndex(indexName);

			_unfollow(indexName);

			_deleteIndex(indexName);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to unfollow the index " + indexName, exception);
			}
		}
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile ElasticsearchConnectionConfigurationWrapper
		elasticsearchConnectionConfigurationWrapper;

	@Reference
	protected ElasticsearchConnectionManager elasticsearchConnectionManager;

	private void _closeIndex(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		IndicesClient indices = restHighLevelClient.indices();

		CloseIndexRequest closeIndexRequest = new CloseIndexRequest(indexName);

		indices.close(closeIndexRequest, RequestOptions.DEFAULT);
	}

	private void _configureSecurity(RestClientBuilder restClientBuilder) {
		restClientBuilder.setHttpClientConfigCallback(
			httpClientBuilder -> {
				httpClientBuilder.setDefaultCredentialsProvider(
					_createCredentialsProvider());

				if (elasticsearchConnectionConfigurationWrapper.
						isTransportSSLEnabled(
							CCRElasticsearchConnection.CONNECTION_ID)) {

					httpClientBuilder.setSSLContext(_createSSLContext());
				}

				return httpClientBuilder;
			});
	}

	private CredentialsProvider _createCredentialsProvider() {
		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			AuthScope.ANY,
			new UsernamePasswordCredentials(
				elasticsearchConnectionConfigurationWrapper.getUsername(
					CCRElasticsearchConnection.CONNECTION_ID),
				elasticsearchConnectionConfigurationWrapper.getPassword(
					CCRElasticsearchConnection.CONNECTION_ID)));

		return credentialsProvider;
	}

	private RestHighLevelClient _createRestHighLevelClient() {
		RestClientBuilder restClientBuilder = RestClient.builder(
			HttpHost.create(
				elasticsearchConnectionConfigurationWrapper.
					getNetworkHostAddress(
						CCRElasticsearchConnection.CONNECTION_ID)));

		if (elasticsearchConnectionConfigurationWrapper.isAuthenticationEnabled(
				CCRElasticsearchConnection.CONNECTION_ID)) {

			_configureSecurity(restClientBuilder);
		}

		return new RestHighLevelClient(restClientBuilder);
	}

	private SSLContext _createSSLContext() {
		try {
			Path path = Paths.get(
				elasticsearchConnectionConfigurationWrapper.
					getSslTruststorePath(
						CCRElasticsearchConnection.CONNECTION_ID));

			InputStream is = Files.newInputStream(path);

			KeyStore keyStore = KeyStore.getInstance(
				elasticsearchConnectionConfigurationWrapper.
					getCertificateFormat(
						CCRElasticsearchConnection.CONNECTION_ID));
			String truststorePassword =
				elasticsearchConnectionConfigurationWrapper.
					getSslTruststorePassword(
						CCRElasticsearchConnection.CONNECTION_ID);

			keyStore.load(is, truststorePassword.toCharArray());

			SSLContextBuilder sslContextBuilder = SSLContexts.custom();

			sslContextBuilder.loadKeyMaterial(
				keyStore, truststorePassword.toCharArray());
			sslContextBuilder.loadTrustMaterial(keyStore, null);

			return sslContextBuilder.build();
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private void _deleteIndex(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		IndicesClient indices = restHighLevelClient.indices();

		DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(
			indexName);

		indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
	}

	private void _pauseFollow(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		CcrClient ccrClient = restHighLevelClient.ccr();

		PauseFollowRequest pauseFollowRequest = new PauseFollowRequest(
			indexName);

		ccrClient.pauseFollow(pauseFollowRequest, RequestOptions.DEFAULT);
	}

	private void _putFollow(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		CcrClient ccrClient = restHighLevelClient.ccr();

		PutFollowRequest putFollowRequest = new PutFollowRequest(
			crossClusterReplicationConfigurationWrapper.getRemoteClusterAlias(),
			indexName, indexName, ActiveShardCount.from(1));

		ccrClient.putFollow(putFollowRequest, RequestOptions.DEFAULT);
	}

	private void _unfollow(String indexName) throws Exception {
		RestHighLevelClient restHighLevelClient = _createRestHighLevelClient();

		CcrClient ccrClient = restHighLevelClient.ccr();

		UnfollowRequest unfollowRequest = new UnfollowRequest(indexName);

		ccrClient.unfollow(unfollowRequest, RequestOptions.DEFAULT);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CrossClusterReplicationHelperImpl.class);

}