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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PROVISIONING,
		"mvc.command.name=/search"
	},
	service = MVCActionCommand.class
)
public class SearchMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			hideDefaultSuccessMessage(actionRequest);

			String redirect = getRedirect(actionRequest, actionResponse);

			actionResponse.sendRedirect(redirect);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected String getRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		LiferayPortletResponse liferayPortletResponse =
			_portal.getLiferayPortletResponse(actionResponse);

		String[] keywords = StringUtil.split(
			ParamUtil.getString(actionRequest, "keywords"), StringPool.SPACE);

		if (keywords.length == 1) {
			String keyword = keywords[0];

			if (StringUtil.isUpperCase(keyword)) {
				List<Account> accounts = _accountWebService.search(
					StringPool.BLANK, "code eq '" + keyword + "'", 0, 1, null);

				if (!accounts.isEmpty()) {
					Account account = accounts.get(0);

					PortletURL portletURL =
						liferayPortletResponse.createRenderURL();

					portletURL.setParameter(
						"mvcRenderCommandName", "/accounts/view_account");
					portletURL.setParameter("accountKey", account.getKey());

					return portletURL.toString();
				}
			}
		}

		PortletURL portletURL = PortletURLUtil.getCurrent(
			_portal.getLiferayPortletRequest(actionRequest),
			liferayPortletResponse);

		portletURL.setParameter("mvcRenderCommandName", "/accounts/view");

		return portletURL.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SearchMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private Portal _portal;

}