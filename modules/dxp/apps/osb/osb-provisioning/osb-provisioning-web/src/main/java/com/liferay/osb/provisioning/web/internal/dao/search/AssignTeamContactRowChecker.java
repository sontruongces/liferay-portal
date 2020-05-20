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

package com.liferay.osb.provisioning.web.internal.dao.search;

import com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import javax.portlet.RenderResponse;

/**
 * @author Amos Fong
 */
public class AssignTeamContactRowChecker extends EmptyOnClickRowChecker {

	public AssignTeamContactRowChecker(
		RenderResponse renderResponse, List<String> contactKeys) {

		super(renderResponse);

		_contactKeys = contactKeys;
	}

	@Override
	public boolean isChecked(Object obj) {
		ContactDisplay contactDisplay = (ContactDisplay)obj;

		try {
			return _contactKeys.contains(contactDisplay.getKey());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		return isChecked(obj);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignTeamContactRowChecker.class);

	private final List<String> _contactKeys;

}