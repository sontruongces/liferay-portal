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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class NoteDisplay {

	public NoteDisplay(HttpServletRequest httpServletRequest, Note note) {
		_httpServletRequest = httpServletRequest;
		_note = note;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy hh:mm a");
	}

	public String getCreateDate() {
		return _dateFormat.format(_note.getDateCreated());
	}

	public String getCreatorName() throws Exception {
		if (Validator.isNotNull(_note.getCreatorName())) {
			return _note.getCreatorName();
		}

		return StringPool.DASH;
	}

	public String getCreatorPortraitURL() {
		return StringPool.BLANK;
	}

	public String getHtmlContent() {
		Note.Format format = _note.getFormat();

		if (format == Note.Format.HTML) {
			return _note.getContent();
		}

		if (format == Note.Format.PLAIN) {
			return StringUtil.replace(
				_note.getContent(), CharPool.NEW_LINE, "<br />");
		}

		return StringPool.BLANK;
	}

	public String getKey() {
		return _note.getKey();
	}

	public String getStatus() {
		return _note.getStatusAsString();
	}

	public String getType() {
		return _note.getTypeAsString();
	}

	public boolean isEdited() {
		if (_note.getDateModified() != null) {
			return true;
		}

		return false;
	}

	public boolean isPinned() {
		if (_note.getPriority() == 1) {
			return true;
		}

		return false;
	}

	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private final Note _note;

}