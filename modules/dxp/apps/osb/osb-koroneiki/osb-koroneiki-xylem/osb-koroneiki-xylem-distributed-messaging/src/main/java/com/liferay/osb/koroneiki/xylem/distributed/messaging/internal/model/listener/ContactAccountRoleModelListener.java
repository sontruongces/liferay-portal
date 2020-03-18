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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.model.listener;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"create.topic=koroneiki.account.contactrole.assigned",
		"remove.topic=koroneiki.account.contactrole.unassigned"
	},
	service = ModelListener.class
)
public class ContactAccountRoleModelListener
	extends BaseXylemModelListener<ContactAccountRole> {

	@Override
	protected Callable<Message> getCallable(
			ContactAccountRole contactAccountRole)
		throws Exception {

		Account account = contactAccountRole.getAccount();
		Contact contact = contactAccountRole.getContact();
		ContactRole contactRole = contactAccountRole.getContactRole();

		return () -> messageFactory.create(account, contact, contactRole);
	}

}