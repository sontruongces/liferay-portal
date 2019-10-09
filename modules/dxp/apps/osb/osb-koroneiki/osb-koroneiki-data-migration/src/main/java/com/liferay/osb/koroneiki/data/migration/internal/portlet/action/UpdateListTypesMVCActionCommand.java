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

package com.liferay.osb.koroneiki.data.migration.internal.portlet.action;

import com.liferay.osb.koroneiki.data.migration.internal.constants.DataMigrationPortletKeys;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.persistence.ListTypeUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + DataMigrationPortletKeys.ADMIN,
		"mvc.command.name=/update_list_types"
	},
	service = MVCActionCommand.class
)
public class UpdateListTypesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			DB db = DBManagerUtil.getDB();

			db.runSQL(
				"update ListType set name = 'Business' where name = " +
					"'business' and type_ = '" + Contact.class.getName() +
						".address'");
			db.runSQL(
				"update ListType set name = 'Other' where name = 'other' and " +
					"type_ = '" + Contact.class.getName() + ".address'");
			db.runSQL(
				"update ListType set name = 'Personal' where name = " +
					"'personal' and type_ = '" + Contact.class.getName() +
						".address'");

			ListTypeUtil.clearCache();

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw e;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpdateListTypesMVCActionCommand.class);

}