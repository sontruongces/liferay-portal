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

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountNoteService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteService
 * @generated
 */
public class AccountNoteServiceWrapper
	implements AccountNoteService, ServiceWrapper<AccountNoteService> {

	public AccountNoteServiceWrapper(AccountNoteService accountNoteService) {
		_accountNoteService = accountNoteService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote addAccountNote(
			String creatorOktaId, String creatorName, long accountId,
			String type, int priority, String content, String format,
			String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.addAccountNote(
			creatorOktaId, creatorName, accountId, type, priority, content,
			format, status);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			deleteAccountNote(String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.deleteAccountNote(accountNoteKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote getAccountNote(
			String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.getAccountNote(accountNoteKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountNote>
			getAccountNotes(
				long accountId, String[] types, int[] priorities,
				String[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	@Override
	public int getAccountNotesCount(
			long accountId, String[] types, int[] priorities, String[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountNoteService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			updateAccountNote(
				long accountNoteId, String modifierOktaId, String modifierName,
				int priority, String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteService.updateAccountNote(
			accountNoteId, modifierOktaId, modifierName, priority, content,
			format, status);
	}

	@Override
	public AccountNoteService getWrappedService() {
		return _accountNoteService;
	}

	@Override
	public void setWrappedService(AccountNoteService accountNoteService) {
		_accountNoteService = accountNoteService;
	}

	private AccountNoteService _accountNoteService;

}