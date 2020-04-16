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

import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.service.base.AccountNoteLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.AccountNote",
	service = AopService.class
)
public class AccountNoteLocalServiceImpl
	extends AccountNoteLocalServiceBaseImpl {

	public AccountNote addAccountNote(
			long userId, String creatorOktaId, String creatorName,
			long accountId, String type, int priority, String content,
			String format, String status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long accountNoteId = counterLocalService.increment();

		AccountNote accountNote = accountNotePersistence.create(accountNoteId);

		accountNote.setCompanyId(user.getCompanyId());
		accountNote.setUserId(userId);
		accountNote.setCreatorOktaId(creatorOktaId);
		accountNote.setCreatorName(creatorName);
		accountNote.setAccountNoteKey(
			ModelKeyGenerator.generate(accountNoteId));
		accountNote.setAccountId(accountId);
		accountNote.setType(type);
		accountNote.setPriority(priority);
		accountNote.setContent(content);
		accountNote.setFormat(format);
		accountNote.setStatus(status);

		return accountNotePersistence.update(accountNote);
	}

	public AccountNote getAccountNote(String accountNoteKey)
		throws PortalException {

		return accountNotePersistence.findByAccountNoteKey(accountNoteKey);
	}

	public List<AccountNote> getAccountNotes(
		long accountId, int start, int end) {

		return accountNotePersistence.findByAccountId(accountId, start, end);
	}

	public List<AccountNote> getAccountNotes(
		long accountId, String[] types, String[] statuses, int start, int end) {

		return accountNotePersistence.findByAI_T_S(
			accountId, types, statuses, start, end);
	}

	public int getAccountNotesCount(long accountId) {
		return accountNotePersistence.countByAccountId(accountId);
	}

	public int getAccountNotesCount(
		long accountId, String[] types, String[] statuses) {

		return accountNotePersistence.countByAI_T_S(accountId, types, statuses);
	}

	public AccountNote updateAccountNote(
			long accountNoteId, String modifierOktaId, String modifierName,
			int priority, String content, String format, String status)
		throws PortalException {

		AccountNote accountNote = accountNotePersistence.findByPrimaryKey(
			accountNoteId);

		if (!content.equals(accountNote.getContent())) {
			accountNote.setModifiedDate(new Date());
			accountNote.setModifierOktaId(modifierOktaId);
			accountNote.setModifierName(modifierName);
		}
		else {
			accountNote.setModifiedDate(accountNote.getModifiedDate());
		}

		accountNote.setPriority(priority);
		accountNote.setContent(content);
		accountNote.setFormat(format);
		accountNote.setStatus(status);

		return accountNotePersistence.update(accountNote);
	}

}