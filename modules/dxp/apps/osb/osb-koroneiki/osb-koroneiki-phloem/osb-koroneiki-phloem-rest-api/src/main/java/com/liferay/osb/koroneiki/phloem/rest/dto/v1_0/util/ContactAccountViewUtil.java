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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ContactAccountViewUtil {

	public static ContactAccountView toContactAccountView(
			com.liferay.osb.koroneiki.taproot.model.Account taprootAccount,
			List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
				taprootContactRoles)
		throws Exception {

		return new ContactAccountView() {
			{
				account = AccountUtil.toAccount(taprootAccount);
				contactRoles = TransformUtil.transformToArray(
					taprootContactRoles, ContactRoleUtil::toContactRole,
					ContactRole.class);
			}
		};
	}

}