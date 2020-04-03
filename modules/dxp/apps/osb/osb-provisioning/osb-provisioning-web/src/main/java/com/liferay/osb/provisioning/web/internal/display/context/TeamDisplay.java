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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class TeamDisplay {

	public TeamDisplay(HttpServletRequest httpServletRequest, Team team) {
		_httpServletRequest = httpServletRequest;
		_team = team;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a");
	}

	public String getContactNames() {
		List<String> contactNames = new ArrayList<>();

		Contact[] contacts = _team.getContacts();

		if (contacts != null) {
			for (Contact contact : contacts) {
				ContactDisplay contactDisplay = new ContactDisplay(
					_httpServletRequest, contact, null);

				contactNames.add(contactDisplay.getFullName());
			}
		}

		return StringUtil.merge(contactNames, StringPool.COMMA_AND_SPACE);
	}

	public String getDateCreated() {
		return _dateFormat.format(_team.getDateCreated());
	}

	public String getDateModified() {
		return _dateFormat.format(_team.getDateModified());
	}

	public String getKey() {
		return _team.getKey();
	}

	public String getName() {
		return _team.getName();
	}

	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final Team _team;

}