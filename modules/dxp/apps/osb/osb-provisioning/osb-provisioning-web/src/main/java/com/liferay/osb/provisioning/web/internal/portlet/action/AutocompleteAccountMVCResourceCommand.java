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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/autocomplete"
	},
	service = MVCResourceCommand.class
)
public class AutocompleteAccountMVCResourceCommand
	extends BaseMVCResourceCommand {

	@Override
	public void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			JSONArray jsonArray = getJSONArray(
				resourceRequest, resourceResponse);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(resourceResponse);

			httpServletResponse.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(
				httpServletResponse, jsonArray.toString());
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	protected JSONArray getJSONArray(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String[] keywords = StringUtil.split(
			ParamUtil.getString(resourceRequest, "keywords"), StringPool.SPACE);

		int maxResults = ParamUtil.getInteger(resourceRequest, "maxResults", 20);

		if (!ArrayUtil.isEmpty(keywords)) {
			StringBundler sb = new StringBundler();

			for (int i = 0; i < keywords.length; i++) {
				String keyword = keywords[i];

				sb.append("(contains(code, '");
				sb.append(keyword);
				sb.append("') or contains(name, '");
				sb.append(keyword);
				sb.append("') or accountKey eq '");
				sb.append(keyword);
				sb.append("')");

				if (i < (keywords.length - 1)) {
					sb.append(" or ");
				}
			}

			List<Account> accounts = _accountWebService.search(
				StringPool.BLANK, sb.toString(), 1, maxResults, null);

			for (Account account : accounts) {
				JSONObject jsonObject = null;

				PortletURL portletURL = PortletURLUtil.getCurrent(
					resourceRequest, resourceResponse);

				portletURL.setParameter(
					"mvcRenderCommandName", "/accounts/view_account");
				portletURL.setParameter("accountKey", account.getKey());

				if (Validator.isNotNull(account.getCode())) {
					jsonObject = JSONUtil.put(
						"key", account.getKey()
					).put(
						"label",
						StringBundler.concat(
							account.getName(), " [", account.getCode(), "]")
					).put(
						"url", portletURL.toString()
					).put(
						"value", account.getCode()
					);
				}
				else {
					jsonObject = JSONUtil.put(
						"key", account.getKey()
					).put(
						"label", account.getName()
					).put(
						"url", portletURL.toString()
					).put(
						"value", account.getName()
					);
				}

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AutocompleteAccountMVCResourceCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private Portal _portal;

}