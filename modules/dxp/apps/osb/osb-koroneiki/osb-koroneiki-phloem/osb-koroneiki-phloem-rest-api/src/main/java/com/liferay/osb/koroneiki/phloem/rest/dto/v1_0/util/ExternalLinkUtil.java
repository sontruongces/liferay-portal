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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.root.util.ExternalLinkUrlGenerator;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Amos Fong
 */
public class ExternalLinkUtil {

	public static ExternalLink toExternalLink(
			com.liferay.osb.koroneiki.root.model.ExternalLink externalLink)
		throws Exception {

		Company company = CompanyLocalServiceUtil.getCompanyByWebId(
			PropsValues.COMPANY_DEFAULT_WEB_ID);

		return new ExternalLink() {
			{
				dateCreated = externalLink.getCreateDate();
				domain = externalLink.getDomain();
				entityId = externalLink.getEntityId();
				entityName = externalLink.getEntityName();
				key = externalLink.getExternalLinkKey();
				url = ExternalLinkUrlGenerator.generate(
					company.getCompanyId(), domain, entityName, entityId);
			}
		};
	}

}