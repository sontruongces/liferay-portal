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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamRolePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.TeamRoleUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.TeamRoleEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.KoroneikiPhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamRoleResource;
import com.liferay.osb.koroneiki.taproot.constants.TeamRoleType;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamRoleService;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/team-role.properties",
	scope = ServiceScope.PROTOTYPE, service = TeamRoleResource.class
)
public class TeamRoleResourceImpl
	extends BaseTeamRoleResourceImpl implements EntityModelResource {

	@Override
	public void deleteTeamRole(String teamRoleKey) throws Exception {
		_teamRoleService.deleteTeamRole(teamRoleKey);
	}

	@Override
	public Page<TeamRole> getAccountAccountKeyTeamTeamKeyRolesPage(
			String accountKey, String teamKey, Pagination pagination)
		throws Exception {

		Account account = _accountLocalService.getAccount(accountKey);

		Team team = _teamLocalService.getTeam(teamKey);

		return Page.of(
			transform(
				_teamRoleService.getTeamAccountTeamRoles(
					account.getAccountId(), team.getTeamId(),
					pagination.getStartPosition(), pagination.getEndPosition()),
				TeamRoleUtil::toTeamRole),
			pagination,
			_teamRoleService.getTeamAccountTeamRolesCount(
				account.getAccountId(), team.getTeamId()));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public TeamRole getTeamRole(String teamRoleKey) throws Exception {
		return TeamRoleUtil.toTeamRole(
			_teamRoleService.getTeamRole(teamRoleKey));
	}

	@Override
	public Page<TeamRole> getTeamRolesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.TeamRole.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> TeamRoleUtil.toTeamRole(
				_teamRoleLocalService.getTeamRole(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public TeamRole postTeamRole(TeamRole teamRole) throws Exception {
		TeamRole.Type teamRoleType = teamRole.getType();

		int type = TeamRoleType.fromLabel(teamRoleType.toString());

		return TeamRoleUtil.toTeamRole(
			_teamRoleService.addTeamRole(
				teamRole.getName(), teamRole.getDescription(), type));
	}

	@Override
	public void postTeamRoleTeamRolePermission(
			String teamRoleKey, String operation,
			TeamRolePermission teamRolePermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.TeamRole teamRole =
			_teamRoleLocalService.getTeamRole(teamRoleKey);

		_teamRolePermission.check(
			PermissionThreadLocal.getPermissionChecker(), teamRole,
			"PERMISSIONS");

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(teamRolePermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(teamRolePermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		KoroneikiPhloemPermissionUtil.persistModelPermission(
			actionIds, contextCompany, teamRole.getTeamRoleId(), operation,
			com.liferay.osb.koroneiki.taproot.model.TeamRole.class.getName(),
			_resourcePermissionLocalService, _roleLocalService,
			teamRolePermission.getRoleNames(), 0);
	}

	@Override
	public TeamRole putTeamRole(String teamRoleKey, TeamRole teamRole)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.TeamRole curTeamRole =
			_teamRoleLocalService.getTeamRole(teamRoleKey);

		String description = GetterUtil.getString(
			teamRole.getDescription(), curTeamRole.getDescription());

		return TeamRoleUtil.toTeamRole(
			_teamRoleService.updateTeamRole(
				curTeamRole.getTeamRoleId(), teamRole.getName(), description));
	}

	private static final EntityModel _entityModel = new TeamRoleEntityModel();

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamRoleLocalService _teamRoleLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.TeamRolePermission
		_teamRolePermission;

	@Reference
	private TeamRoleService _teamRoleService;

}