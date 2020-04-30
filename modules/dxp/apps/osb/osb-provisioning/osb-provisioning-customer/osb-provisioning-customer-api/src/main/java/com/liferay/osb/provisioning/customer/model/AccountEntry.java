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

package com.liferay.osb.provisioning.customer.model;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
public class AccountEntry {

	public AccountEntry(JSONObject jsonObject) {
		_instructions = jsonObject.getString("instructions");

		JSONArray languageIdsJSONArray = jsonObject.getJSONArray("languageIds");

		if ((languageIdsJSONArray != null) &&
			(languageIdsJSONArray.length() > 0)) {

			_languageId = languageIdsJSONArray.getString(0);
		}
		else {
			_languageId = StringPool.BLANK;
		}

		JSONObject accountAttachmentJSONbject = _getAccountAttachmentJSONObject(
			jsonObject);

		if (accountAttachmentJSONbject != null) {
			_oemInstructionsFileName = accountAttachmentJSONbject.getString(
				"fileName");

			_oemInstructionsAccountAttachmentId =
				accountAttachmentJSONbject.getLong("accountAttachmentId");
		}
		else {
			_oemInstructionsFileName = StringPool.BLANK;
			_oemInstructionsAccountAttachmentId = 0;
		}
	}

	public String getInstructions() {
		return _instructions;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public long getOEMInstructionsAccountAttachmentId() {
		return _oemInstructionsAccountAttachmentId;
	}

	public String getOEMInstructionsFileName() {
		return _oemInstructionsFileName;
	}

	private JSONObject _getAccountAttachmentJSONObject(JSONObject jsonObject) {
		JSONArray accountAttachmentsJSONArray = jsonObject.getJSONArray(
			"accountAttachments");

		if (accountAttachmentsJSONArray != null) {
			for (int i = 0; i < accountAttachmentsJSONArray.length(); i++) {
				JSONObject accountAttachmentJSONbject =
					accountAttachmentsJSONArray.getJSONObject(i);

				int type = accountAttachmentJSONbject.getInt("type");

				if (type == 1) {
					return accountAttachmentJSONbject;
				}
			}
		}

		return null;
	}

	private final String _instructions;
	private final String _languageId;
	private final long _oemInstructionsAccountAttachmentId;
	private final String _oemInstructionsFileName;

}