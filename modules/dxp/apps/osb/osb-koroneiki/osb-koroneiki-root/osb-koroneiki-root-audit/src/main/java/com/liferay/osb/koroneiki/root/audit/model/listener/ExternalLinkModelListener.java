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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.model.ModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class ExternalLinkModelListener
	extends BaseAuditModelListener<ExternalLink> {

	@Override
	protected long getClassNameId(ExternalLink externalLink) {
		return externalLink.getClassNameId();
	}

	@Override
	protected long getClassPK(ExternalLink externalLink) {
		return externalLink.getClassPK();
	}

	@Override
	protected ExternalLink getModel(long classPK) throws PortalException {
		return _externalLinkLocalService.getExternalLink(classPK);
	}

	@Override
	protected boolean isSkipFieldUpdate(
		String field, Object oldValue, Object newValue) {

		if (field.equals("domain") || field.equals("entityId") ||
			field.equals("entityName")) {

			return false;
		}

		return super.isSkipFieldUpdate(field, oldValue, newValue);
	}

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}