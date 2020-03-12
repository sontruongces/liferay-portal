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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.service.AccountNoteLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountNoteModelListener
	extends BaseAuditModelListener<AccountNote> {

	@Override
	protected long getClassNameId(AccountNote accountNote) {
		return classNameLocalService.getClassNameId(Account.class);
	}

	@Override
	protected long getClassPK(AccountNote accountNote) {
		return accountNote.getAccountId();
	}

	@Override
	protected AccountNote getModel(long classPK) throws PortalException {
		return _accountNoteLocalService.getAccountNote(classPK);
	}

	@Override
	protected boolean isSkipFieldUpdate(
		String field, Object oldValue, Object newValue) {

		if (field.equals("content")) {
			return false;
		}

		return super.isSkipFieldUpdate(field, oldValue, newValue);
	}

	@Reference
	private AccountNoteLocalService _accountNoteLocalService;

}