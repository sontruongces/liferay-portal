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

package com.liferay.account.admin.web.internal.portlet.action;

import com.liferay.account.constants.AccountPortletKeys;
import com.liferay.account.service.AccountEntryUserRelLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseTransactionalMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Drew Brokke
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + AccountPortletKeys.ACCOUNT_ENTRIES_MANAGEMENT,
		"mvc.command.name=/account_admin/assign_account_users",
		"service.ranking:Integer=100"
	},
	service = MVCActionCommand.class
)
public class AssignAccountUsersSiteMembershipMVCActionCommand extends
	BaseTransactionalMVCActionCommand {

	@Override
	protected void doTransactionalCommand(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long groupId = _portal.getScopeGroupId(actionRequest);

		long[] accountUserIds = ParamUtil.getLongValues(
			actionRequest, "accountUserIds");

		_userLocalService.addGroupUsers(groupId, accountUserIds);

		_mvcActionCommand.processAction(actionRequest, actionResponse);

	}

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

	@Reference(target = "(component.name=com.liferay.account.admin.web.internal.portlet.action.AssignAccountUsersMVCActionCommand)")
	private MVCActionCommand _mvcActionCommand;

}