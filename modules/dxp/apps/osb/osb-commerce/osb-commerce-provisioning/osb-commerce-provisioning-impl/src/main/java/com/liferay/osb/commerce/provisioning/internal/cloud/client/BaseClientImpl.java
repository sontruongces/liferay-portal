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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Base64;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * @author Ivica Cardic
 */
public abstract class BaseClientImpl implements Client {

	public BaseClientImpl(
		String dxpCloudAPIURL, String password, String username) {

		this.dxpCloudAPIURL = dxpCloudAPIURL;

		_password = password;
		_username = username;

		_closeableHttpClient = _createCloseableHttpClient();
	}

	@Override
	public void destroy() {
		try {
			_closeableHttpClient.close();
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected <T> T convert(String content, Class<T> valueType) {
		try {
			return _objectMapper.readValue(content, valueType);
		}
		catch (JsonProcessingException jsonProcessingException) {
			throw new SystemException(jsonProcessingException);
		}
	}

	protected <T> T convert(String content, TypeReference<T> valueTypeRef) {
		try {
			return _objectMapper.readValue(content, valueTypeRef);
		}
		catch (JsonProcessingException jsonProcessingException) {
			throw new SystemException(jsonProcessingException);
		}
	}

	protected String execute(HttpUriRequest httpUriRequest) {
		_setAuthHeader(httpUriRequest);

		try (CloseableHttpResponse closeableHttpResponse =
				_closeableHttpClient.execute(httpUriRequest)) {

			return getResponseContent(closeableHttpResponse);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected void executeDelete(String uri) {
		execute(new HttpDelete(uri));
	}

	protected <T> T executeGet(Class<T> valueType, String uri) {
		return convert(execute(new HttpGet(uri)), valueType);
	}

	protected <T> T executeGet(
		TypeReference<T> valueTypeReference, String uri) {

		return convert(execute(new HttpGet(uri)), valueTypeReference);
	}

	protected <T> T executePost(
		Object content, Class<T> valueType, String uri) {

		HttpPost httpPost = new HttpPost(uri);

		httpPost.setEntity(
			new StringEntity(
				writeValueAsString(content), ContentType.APPLICATION_JSON));

		return convert(execute(httpPost), valueType);
	}

	protected <T> T executeUpdate(
		Object content, Class<T> valueType, String uri) {

		HttpPatch httpPatch = new HttpPatch(uri);

		httpPatch.setEntity(
			new StringEntity(
				writeValueAsString(content), ContentType.APPLICATION_JSON));

		return convert(execute(httpPatch), valueType);
	}

	protected String getResponseContent(
			CloseableHttpResponse closeableHttpResponse)
		throws IOException {

		StatusLine statusLine = closeableHttpResponse.getStatusLine();

		if (statusLine.getStatusCode() >= HttpStatus.SC_BAD_REQUEST) {
			throw new SystemException(statusLine.toString());
		}

		return EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	}

	protected String writeValueAsString(Object value) {
		try {
			return _objectMapper.writeValueAsString(value);
		}
		catch (JsonProcessingException jsonProcessingException) {
			throw new SystemException(jsonProcessingException);
		}
	}

	private CloseableHttpClient _createCloseableHttpClient() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
			new PoolingHttpClientConnectionManager();

		poolingHttpClientConnectionManager.setMaxTotal(100);

		httpClientBuilder.setConnectionManager(
			poolingHttpClientConnectionManager);

		httpClientBuilder.useSystemProperties();

		return httpClientBuilder.build();
	}

	private void _setAuthHeader(HttpUriRequest httpUriRequest) {
		String authorization = _username + ":" + _password;

		httpUriRequest.setHeader(
			HttpHeaders.AUTHORIZATION,
			"Basic " +
				Base64.encode(
					authorization.getBytes(StandardCharsets.ISO_8859_1)));
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
	};

	private final CloseableHttpClient _closeableHttpClient;
	private final String _password;
	private final String _username;

	protected final String dxpCloudAPIURL;

}