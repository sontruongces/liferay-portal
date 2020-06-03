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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/edit_external_link"
	},
	service = MVCActionCommand.class
)
public class EditExternalLinkMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteExternalLink(ActionRequest actionRequest, User user)
		throws Exception {

		String externalLinkKey = ParamUtil.getString(
			actionRequest, "externalLinkKey");

		_externalLinkWebService.deleteExternalLink(
			user.getFullName(), StringPool.BLANK, externalLinkKey);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteExternalLink(actionRequest, user);
			}
			else {
				updateExternalLink(actionRequest, user);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateExternalLink(ActionRequest actionRequest, User user)
		throws Exception {

		String externalLinkKey = ParamUtil.getString(
			actionRequest, "externalLinkKey");

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		String domain = ParamUtil.getString(actionRequest, "domain");
		String entityName = ParamUtil.getString(actionRequest, "entityName");
		String entityId = ParamUtil.getString(actionRequest, "entityId");

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(domain);
		externalLink.setEntityName(entityName);
		externalLink.setEntityId(entityId);

		if (Validator.isNotNull(externalLinkKey)) {
			_externalLinkWebService.updateExternalLink(
				user.getFullName(), StringPool.BLANK, externalLinkKey,
				externalLink);
		}
		else {
			_externalLinkWebService.addAccountExternalLink(
				user.getFullName(), StringPool.BLANK, accountKey, externalLink);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditExternalLinkMVCActionCommand.class);

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

}