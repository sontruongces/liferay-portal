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

package com.liferay.osb.provisioning.message.service.connector;

import com.liferay.osb.provisioning.message.connector.WebServiceConnector;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClientFactory;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URLEncoder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = {
		"api.token=", "host=", "port=", "protocol=", "system=koroneiki"
	},
	service = WebServiceConnector.class
)
public class KoroneikiWebServiceConnector implements WebServiceConnector {

	public JSONObject getContactByEmailAddress(String emailAddress)
		throws Exception {

		return _getToJSONObject(
			_URL_API_REST + _URL_API_REST_CONTACTS + _BY_EMAIL_ADDRESS +
				StringPool.SLASH + emailAddress,
			new HashMap<>());
	}

	public JSONObject getContactRoles(String name) throws Exception {
		Map<String, String> parameters = new HashMap<>();

		parameters.put("search", name);

		return _getToJSONObject(
			_URL_API_REST + _URL_API_REST_CONTACT_ROLES, parameters);
	}

	public JSONObject getProductByExternalLink(
			String domain, String entityName, String entityId)
		throws Exception {

		return _getToJSONObject(
			_URL_API_REST + _URL_API_REST_PRODUCTS + _BY_EXTERNAL_LINK +
				StringPool.SLASH + domain + StringPool.SLASH + entityName +
					StringPool.SLASH + _encode(entityId),
			new HashMap<>());
	}

	public JSONObject postAccount(String json) throws Exception {
		return _postToJSONObject(_URL_API_REST + _URL_API_REST_ACCOUNTS, json);
	}

	public JSONObject postContactRole(String json) throws Exception {
		return _postToJSONObject(
			_URL_API_REST + _URL_API_REST_CONTACT_ROLES, json);
	}

	public JSONObject postProduct(String json) throws Exception {
		return _postToJSONObject(_URL_API_REST + _URL_API_REST_PRODUCTS, json);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		if (Validator.isNotNull(properties.get("host"))) {
			Map<String, Object> jsonWebServiceClientProperties =
				new HashMap<>();

			jsonWebServiceClientProperties.put(
				"headers", "API_Token=" + properties.get("api.token"));
			jsonWebServiceClientProperties.put(
				"hostName", properties.get("host"));
			jsonWebServiceClientProperties.put(
				"hostPort", properties.get("port"));
			jsonWebServiceClientProperties.put(
				"protocol", properties.get("protocol"));

			_jsonWebServiceClient = _jsonWebServiceClientFactory.getInstance(
				jsonWebServiceClientProperties, false);
		}
	}

	@Deactivate
	protected void deactivate() {
		if (_jsonWebServiceClient != null) {
			_jsonWebServiceClient.destroy();
		}
	}

	private String _encode(String string) throws Exception {
		String encodedString = URLEncoder.encode(string, "UTF-8");

		return StringUtil.replace(encodedString, CharPool.PLUS, "%20");
	}

	private JSONObject _getToJSONObject(
			String url, Map<String, String> parameters)
		throws Exception {

		if (_jsonWebServiceClient == null) {
			return null;
		}

		try {
			String response = _jsonWebServiceClient.doGet(url, parameters);

			return _jsonFactory.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw jsonwsie;
		}
		catch (Exception e) {
			throw e;
		}
	}

	private JSONObject _postToJSONObject(String url, String json)
		throws Exception {

		if (_jsonWebServiceClient == null) {
			return null;
		}

		try {
			String response = _jsonWebServiceClient.doPostAsJSON(url, json);

			return _jsonFactory.createJSONObject(response);
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw jsonwsie;
		}
		catch (Exception e) {
			throw e;
		}
	}

	private static final String _BY_EMAIL_ADDRESS = "/by-email-address";

	private static final String _BY_EXTERNAL_LINK = "/by-external-link";

	private static final String _URL_API_REST = "/o/koroneiki-rest/v1.0";

	private static final String _URL_API_REST_ACCOUNTS = "/accounts";

	private static final String _URL_API_REST_CONTACT_ROLES = "/contact-roles";

	private static final String _URL_API_REST_CONTACTS = "/contacts";

	private static final String _URL_API_REST_PRODUCTS = "/products";

	@Reference
	private JSONFactory _jsonFactory;

	private JSONWebServiceClient _jsonWebServiceClient;

	@Reference
	private JSONWebServiceClientFactory _jsonWebServiceClientFactory;

}