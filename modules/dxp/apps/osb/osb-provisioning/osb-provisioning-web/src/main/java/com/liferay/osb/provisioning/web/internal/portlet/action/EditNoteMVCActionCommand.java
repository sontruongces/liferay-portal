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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletResponse;

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

	protected JSONObject deleteNote(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String noteKey = ParamUtil.getString(actionRequest, "noteKey");

		_noteWebService.deleteNote(
			user.getFullName(), StringPool.BLANK, noteKey);

		return JSONUtil.put(
			"successMessage",
			LanguageUtil.get(
				themeDisplay.getRequest(), "note-deleted-successfully"));
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = null;

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.DELETE)) {
				jsonObject = deleteNote(actionRequest);
			}
			else {
				jsonObject = updateNote(actionRequest);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(actionResponse);

			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			if (Validator.isNotNull(exception.getMessage())) {
				jsonObject = JSONUtil.put(
					"errorMessage", exception.getMessage());
			}
			else {
				jsonObject = JSONUtil.put(
					"errorMessage",
					LanguageUtil.get(
						themeDisplay.getRequest(),
						"an-unexpected-error-occurred"));
			}
		}

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	protected JSONObject updateNote(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String noteKey = ParamUtil.getString(actionRequest, "noteKey");

		String accountKey = ParamUtil.getString(actionRequest, "accountKey");
		String content = ParamUtil.getString(actionRequest, "content");
		String format = ParamUtil.getString(actionRequest, "format");
		int priority = ParamUtil.getInteger(actionRequest, "priority");
		String status = ParamUtil.getString(actionRequest, "status");
		String type = ParamUtil.getString(actionRequest, "type");

		Note note = new Note();

		if (Validator.isNotNull(content)) {
			note.setContent(content);
		}

		if (Validator.isNotNull(format)) {
			note.setFormat(Note.Format.create(format));
		}

		if (priority > 0) {
			note.setPriority(priority);
		}

		if (Validator.isNotNull(status)) {
			note.setStatus(Note.Status.create(status));
		}

		JSONObject jsonObject = null;

		if (Validator.isNotNull(noteKey)) {
			note = _noteWebService.updateNote(
				user.getFullName(), StringPool.BLANK, noteKey, note);

			JSONObject noteJsonObject = JSONFactoryUtil.createJSONObject(
				note.toString());

			jsonObject = JSONUtil.put(
				"note", noteJsonObject
			).put(
				"successMessage",
				LanguageUtil.get(
					themeDisplay.getRequest(), "note-updated-successfully")
			);
		}
		else {
			note.setType(Note.Type.create(type));

			note = _noteWebService.addNote(
				user.getFullName(), StringPool.BLANK, accountKey, note);

			JSONObject noteJsonObject = JSONFactoryUtil.createJSONObject(
				note.toString());

			jsonObject = JSONUtil.put(
				"note", noteJsonObject
			).put(
				"successMessage",
				LanguageUtil.get(
					themeDisplay.getRequest(), "note-added-successfully")
			);
		}

		return jsonObject;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditNoteMVCActionCommand.class);

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private Portal _portal;

}