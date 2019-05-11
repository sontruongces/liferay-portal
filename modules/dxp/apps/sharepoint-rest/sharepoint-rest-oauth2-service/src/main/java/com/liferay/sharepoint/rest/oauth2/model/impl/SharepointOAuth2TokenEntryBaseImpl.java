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

package com.liferay.sharepoint.rest.oauth2.model.impl;

import com.liferay.sharepoint.rest.oauth2.model.SharepointOAuth2TokenEntry;
import com.liferay.sharepoint.rest.oauth2.service.SharepointOAuth2TokenEntryLocalServiceUtil;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model base implementation for the SharepointOAuth2TokenEntry service. Represents a row in the &quot;SharepointOAuth2TokenEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SharepointOAuth2TokenEntryImpl}.
 * </p>
 *
 * @author Adolfo Pérez
 * @see SharepointOAuth2TokenEntryImpl
 * @see SharepointOAuth2TokenEntry
 * @generated
 */
@ProviderType
public abstract class SharepointOAuth2TokenEntryBaseImpl
	extends SharepointOAuth2TokenEntryModelImpl
	implements SharepointOAuth2TokenEntry {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sharepoint o auth2 token entry model instance should use the <code>SharepointOAuth2TokenEntry</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			SharepointOAuth2TokenEntryLocalServiceUtil.
				addSharepointOAuth2TokenEntry(this);
		}
		else {
			SharepointOAuth2TokenEntryLocalServiceUtil.
				updateSharepointOAuth2TokenEntry(this);
		}
	}

}