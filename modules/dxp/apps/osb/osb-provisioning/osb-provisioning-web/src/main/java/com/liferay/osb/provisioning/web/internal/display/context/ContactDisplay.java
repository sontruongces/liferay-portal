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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ContactDisplay {

	public ContactDisplay(
			HttpServletRequest httpServletRequest, Contact contact,
			List<ContactRole> contactRoles)
		throws Exception {

		_httpServletRequest = httpServletRequest;
		_contact = contact;
		_contactRoles = contactRoles;
	}

	public String getContactKey() {
		return _contact.getKey();
	}

	public List<String> getContactRoleNames() {
		if (_contactRoles == null) {
			return Collections.emptyList();
		}

		List<String> contactRoleNames = new ArrayList<>();

		for (ContactRole contactRole : _contactRoles) {
			contactRoleNames.add(contactRole.getName());
		}

		return contactRoleNames;
	}

	public String getEmailAddress() {
		return _contact.getEmailAddress();
	}

	public String getFullName() {
		StringBundler sb = new StringBundler(5);

		if (Validator.isNotNull(_contact.getFirstName())) {
			sb.append(_contact.getFirstName());
		}

		if (Validator.isNotNull(_contact.getMiddleName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(_contact.getMiddleName());
		}

		if (Validator.isNotNull(_contact.getLastName())) {
			if (sb.length() > 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(_contact.getLastName());
		}

		return sb.toString();
	}

	public String getStatus() {
		return StringPool.BLANK;
	}

	private final Contact _contact;
	private final List<ContactRole> _contactRoles;
	private final HttpServletRequest _httpServletRequest;

}