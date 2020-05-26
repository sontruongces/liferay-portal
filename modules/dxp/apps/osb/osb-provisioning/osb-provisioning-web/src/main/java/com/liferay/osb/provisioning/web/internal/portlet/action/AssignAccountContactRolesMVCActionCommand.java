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

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.exception.HttpException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/assign_contact_roles"
	},
	service = MVCActionCommand.class
)
public class AssignAccountContactRolesMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			String accountKey = ParamUtil.getString(
				actionRequest, "accountKey");

			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");
			String[] addContactRoleKeys = ParamUtil.getStringValues(
				actionRequest, "addContactRoleKeys");
			String[] deleteContactRoleKeys = ParamUtil.getStringValues(
				actionRequest, "deleteContactRoleKeys");

			if (!ArrayUtil.isEmpty(addContactRoleKeys)) {
				_accountWebService.assignContactRoles(
					user.getFullName(), StringPool.BLANK, accountKey,
					emailAddress, addContactRoleKeys);
			}

			if (!ArrayUtil.isEmpty(deleteContactRoleKeys)) {
				_accountWebService.unassignContactRoles(
					user.getFullName(), StringPool.BLANK, accountKey,
					emailAddress, deleteContactRoleKeys);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (HttpException httpException) {
			SessionErrors.add(
				actionRequest, httpException.getClass(), httpException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignAccountContactRolesMVCActionCommand.class);

	@Reference
	private AccountWebService _accountWebService;

}