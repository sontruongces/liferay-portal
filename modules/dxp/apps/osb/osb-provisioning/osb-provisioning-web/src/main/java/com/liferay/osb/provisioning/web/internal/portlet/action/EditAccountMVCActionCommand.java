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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PROVISIONING,
		"mvc.command.name=/edit_account"
	},
	service = MVCActionCommand.class
)
public class EditAccountMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			updateAccount(actionRequest);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateAccount(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");

		String name = ParamUtil.getString(actionRequest, "name");
		String code = ParamUtil.getString(actionRequest, "code");
		String tier = ParamUtil.getString(actionRequest, "tier");
		String region = ParamUtil.getString(actionRequest, "region");

		Account account = new Account();

		account.setName(name);

		if (Validator.isNotNull(code)) {
			account.setCode(code);
		}

		if (Validator.isNotNull(tier)) {
			account.setTier(Account.Tier.create(tier));
		}

		if (Validator.isNotNull(region)) {
			account.setRegion(Account.Region.create(region));
		}

		_accountWebService.putAccount(
			user.getFullName(), StringPool.BLANK, accountKey, account);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditAccountMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

}