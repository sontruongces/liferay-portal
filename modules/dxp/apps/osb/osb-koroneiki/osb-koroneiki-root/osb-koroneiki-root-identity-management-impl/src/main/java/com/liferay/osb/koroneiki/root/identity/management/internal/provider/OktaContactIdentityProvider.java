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

package com.liferay.osb.koroneiki.root.identity.management.internal.provider;

import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactException;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"api.token=", "error.email.address=", "host=", "port=", "protocol=",
		"provider=okta"
	},
	service = ContactIdentityProvider.class
)
public class OktaContactIdentityProvider implements ContactIdentityProvider {

	public Contact fetchContactByEmailAddress(String emailAddress)
		throws Exception {

		Contact contact = _contactLocalService.fetchContactByEmailAddress(
			emailAddress);

		if (contact == null) {
			contact = _importContactByEmailAddress();
		}

		return contact;
	}

	public Contact fetchContactByProviderId(String providerId)
		throws Exception {

		Contact contact = _contactLocalService.fetchContactByOktaId(providerId);

		if (contact == null) {
			contact = _importContactByOktaId();
		}

		return contact;
	}

	public Contact getContactByEmailAddress(String emailAddress)
		throws Exception {

		Contact contact = fetchContactByEmailAddress(emailAddress);

		if (contact == null) {
			throw new NoSuchContactException();
		}

		return contact;
	}

	public Contact getContactByProviderId(String providerId) throws Exception {
		Contact contact = fetchContactByProviderId(providerId);

		if (contact == null) {
			throw new NoSuchContactException();
		}

		return contact;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
	}

	@Deactivate
	protected void deactivate() {
	}

	private Contact _importContactByEmailAddress() throws Exception {
		return null;
	}

	private Contact _importContactByOktaId() throws Exception {
		return null;
	}

	@Reference
	private ContactLocalService _contactLocalService;

}