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

package com.liferay.osb.koroneiki.taproot.internal.search.spi.model.index.contributor;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.taproot.model.TeamRole",
	service = ModelDocumentContributor.class
)
public class TeamRoleModelDocumentContributor
	implements ModelDocumentContributor<TeamRole> {

	@Override
	public void contribute(Document document, TeamRole teamRole) {
		document.addKeyword(Field.COMPANY_ID, teamRole.getCompanyId());
		document.addDate(Field.CREATE_DATE, teamRole.getCreateDate());
		document.addText(Field.DESCRIPTION, teamRole.getDescription());
		document.addDate(Field.MODIFIED_DATE, teamRole.getModifiedDate());
		document.addText(Field.NAME, teamRole.getName());
		document.addKeyword(Field.TYPE, teamRole.getType());
		document.addKeyword(Field.USER_ID, teamRole.getUserId());

		document.addKeyword("teamRoleKey", teamRole.getTeamRoleKey());

		document.addDateSortable(Field.CREATE_DATE, teamRole.getCreateDate());
		document.addDateSortable(
			Field.MODIFIED_DATE, teamRole.getModifiedDate());
		document.addTextSortable(Field.NAME, teamRole.getName());
	}

}