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

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.service.base.AccountNoteServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=AccountNote"
	},
	service = AopService.class
)
public class AccountNoteServiceImpl extends AccountNoteServiceBaseImpl {

	public AccountNote addAccountNote(
			String creatorOktaId, String creatorName, long accountId,
			String type, int priority, String content, String format,
			String status)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.UPDATE);

		return accountNoteLocalService.addAccountNote(
			getUserId(), creatorOktaId, creatorName, accountId, type, priority,
			content, format, status);
	}

	public AccountNote deleteAccountNote(String accountNoteKey)
		throws PortalException {

		AccountNote accountNote = accountNoteLocalService.getAccountNote(
			accountNoteKey);

		_accountPermission.check(
			getPermissionChecker(), accountNote.getAccountId(),
			ActionKeys.VIEW);

		return accountNoteLocalService.deleteAccountNote(
			accountNote.getAccountNoteId());
	}

	public AccountNote getAccountNote(String accountNoteKey)
		throws PortalException {

		AccountNote accountNote = accountNoteLocalService.getAccountNote(
			accountNoteKey);

		_accountPermission.check(
			getPermissionChecker(), accountNote.getAccountId(),
			ActionKeys.VIEW);

		return accountNote;
	}

	public List<AccountNote> getAccountNotes(
			long accountId, String[] types, int[] priorities, String[] statuses,
			int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return accountNoteLocalService.getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	public int getAccountNotesCount(
			long accountId, String[] types, int[] priorities, String[] statuses)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return accountNoteLocalService.getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	public AccountNote updateAccountNote(
			long accountNoteId, String modifierOktaId, String modifierName,
			int priority, String content, String format, String status)
		throws PortalException {

		AccountNote accountNote = accountNoteLocalService.getAccountNote(
			accountNoteId);

		_accountPermission.check(
			getPermissionChecker(), accountNote.getAccountId(),
			ActionKeys.UPDATE);

		return accountNoteLocalService.updateAccountNote(
			accountNoteId, modifierOktaId, modifierName, priority, content,
			format, status);
	}

	@Reference
	private AccountPermission _accountPermission;

}