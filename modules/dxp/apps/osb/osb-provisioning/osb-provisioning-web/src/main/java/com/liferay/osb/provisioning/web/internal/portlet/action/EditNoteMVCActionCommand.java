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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.PROVISIONING,
		"mvc.command.name=/edit_note"
	},
	service = MVCActionCommand.class
)
public class EditNoteMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteNote(ActionRequest actionRequest, User user)
		throws Exception {

		String noteKey = ParamUtil.getString(actionRequest, "noteKey");

		_noteWebService.deleteNote(
			user.getFullName(), StringPool.BLANK, noteKey);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			User user = themeDisplay.getUser();

			if (cmd.equals(Constants.DELETE)) {
				deleteNote(actionRequest, user);
			}
			else {
				updateNote(actionRequest, user);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	protected void updateNote(ActionRequest actionRequest, User user)
		throws Exception {

		String noteKey = ParamUtil.getString(actionRequest, "noteKey");

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		int priority = ParamUtil.getInteger(actionRequest, "priority");
		String content = ParamUtil.getString(actionRequest, "content");
		String format = ParamUtil.getString(actionRequest, "format");
		String status = ParamUtil.getString(actionRequest, "status");
		String type = ParamUtil.getString(actionRequest, "type");

		Note note = new Note();

		if (priority > 0) {
			note.setPriority(priority);
		}

		if (Validator.isNotNull(content)) {
			note.setContent(content);
		}

		if (Validator.isNotNull(format)) {
			note.setFormat(Note.Format.create(format));
		}

		if (Validator.isNotNull(status)) {
			note.setStatus(Note.Status.create(status));
		}

		if (Validator.isNotNull(noteKey)) {
			_noteWebService.updateNote(
				user.getFullName(), StringPool.BLANK, noteKey, note);
		}
		else {
			note.setType(Note.Type.create(type));

			_noteWebService.addNote(
				user.getFullName(), StringPool.BLANK, accountKey, note);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditNoteMVCActionCommand.class);

	@Reference
	private NoteWebService _noteWebService;

}