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

package com.liferay.osb.provisioning.customer.web.service;

import com.liferay.osb.provisioning.customer.model.AccountEntry;

/**
 * @author Amos Fong
 */
public interface AccountEntryWebService {

	public AccountEntry fetchAccountEntry(String koroneikiAccountKey)
		throws Exception;

	public String getAccountAttachmentURL(long accountAttachmentId)
		throws Exception;

	public String getUpdateAccountAttachmentURL() throws Exception;

	public void syncToZendesk(String koroneikiAccountKey) throws Exception;

	public void updateInstructions(
			String koroneikiAccountKey, String instructions)
		throws Exception;

	public void updateLanguageId(String koroneikiAccountKey, String languageId)
		throws Exception;

}