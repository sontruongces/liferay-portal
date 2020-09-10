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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Note;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;

/**
 * @author Amos Fong
 */
public class NoteUtil {

	public static Note toNote(AccountNote accountNote) throws Exception {
		return new Note() {
			{
				content = accountNote.getContent();
				creatorName = accountNote.getCreatorName();
				creatorUID = accountNote.getCreatorUID();
				dateCreated = accountNote.getCreateDate();
				dateModified = accountNote.getModifiedDate();
				format = Format.create(accountNote.getFormat());
				key = accountNote.getAccountNoteKey();
				modifierName = accountNote.getModifierName();
				modifierUID = accountNote.getModifierUID();
				priority = accountNote.getPriority();
				status = Status.create(accountNote.getStatus());
				type = Type.create(accountNote.getType());
			}
		};
	}

}