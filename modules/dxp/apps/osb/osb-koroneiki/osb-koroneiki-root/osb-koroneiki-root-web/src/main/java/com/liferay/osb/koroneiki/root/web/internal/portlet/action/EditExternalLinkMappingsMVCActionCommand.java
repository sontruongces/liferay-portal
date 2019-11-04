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

package com.liferay.osb.koroneiki.root.web.internal.portlet.action;

import com.liferay.osb.koroneiki.root.constants.RootPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + RootPortletKeys.EXTERNAL_LINKS_ADMIN,
		"mvc.command.name=/external_links_admin/edit_external_link_mappings"
	},
	service = MVCActionCommand.class
)
public class EditExternalLinkMappingsMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		int[] externalLinkIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "externalLinkIndexes"), 0);

		for (int externalLinkIndex : externalLinkIndexes) {
			String domain = ParamUtil.getString(
				actionRequest, "domain_" + externalLinkIndex);
			String entityName = ParamUtil.getString(
				actionRequest, "entityName_" + externalLinkIndex);
			String url = ParamUtil.getString(
				actionRequest, "url_" + externalLinkIndex);

			portletPreferences.setValue(
				domain + StringPool.UNDERLINE + entityName, url);
		}

		portletPreferences.store();
	}

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}