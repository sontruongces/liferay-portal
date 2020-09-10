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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rest.dto.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.portal.vulcan.util.TransformUtil;

/**
 * @author Kyle Bischof
 */
public class ContactUtil {

	public static Contact toContact(
			com.liferay.osb.koroneiki.taproot.model.Contact contact)
		throws Exception {

		return new Contact() {
			{
				dateCreated = contact.getCreateDate();
				dateModified = contact.getModifiedDate();
				emailAddress = contact.getEmailAddress();
				entitlements = TransformUtil.transformToArray(
					contact.getEntitlements(), EntitlementUtil::toEntitlement,
					Entitlement.class);
				externalLinks = TransformUtil.transformToArray(
					contact.getExternalLinks(),
					ExternalLinkUtil::toExternalLink, ExternalLink.class);
				firstName = contact.getFirstName();
				key = contact.getContactKey();
				languageId = contact.getLanguageId();
				lastName = contact.getLastName();
				middleName = contact.getMiddleName();
				uuid = contact.getUuid();
			}
		};
	}

}