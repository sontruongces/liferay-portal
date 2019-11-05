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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.TeamPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.TeamUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.TeamEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.TeamResource;
import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.constants.ContactRoleType;
import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
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
	properties = "OSGI-INF/liferay/rest/v1_0/team.properties",
	scope = ServiceScope.PROTOTYPE, service = TeamResource.class
)
public class TeamResourceImpl
	extends BaseTeamResourceImpl implements EntityModelResource {

	@Override
	public void deleteTeam(String teamKey) throws Exception {
		_teamService.deleteTeam(teamKey);
	}

	@Override
	public void deleteTeamContactByOkta(String teamKey, String[] oktaIds)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(oktaIds.length);

		for (String oktaId : oktaIds) {
			contacts.add(
				_oktaContactIdentityProvider.getContactByProviderId(oktaId));
		}

		_deleteTeamContacts(contacts, teamKey);
	}

	@Override
	public void deleteTeamContactByOktaRole(
			String teamKey, String oktaId, String[] contactRoleKeys)
		throws Exception {

		_deleteTeamContactRole(
			_oktaContactIdentityProvider.getContactByProviderId(oktaId),
			teamKey, contactRoleKeys);
	}

	@Override
	public void deleteTeamContactByUuid(String teamKey, String[] contactUuids)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(contactUuids.length);

		for (String contactUuid : contactUuids) {
			contacts.add(
				_webContactIdentityProvider.getContactByProviderId(
					contactUuid));
		}

		_deleteTeamContacts(contacts, teamKey);
	}

	@Override
	public void deleteTeamContactByUuidContactUuidRole(
			String teamKey, String contactUuid, String[] contactRoleKeys)
		throws Exception {

		_deleteTeamContactRole(
			_webContactIdentityProvider.getContactByProviderId(contactUuid),
			teamKey, contactRoleKeys);
	}

	@Override
	public void deleteTeamTeamPermission(
			String teamKey, TeamPermission teamPermission)
		throws Exception {

		_updateTeamPermission(teamKey, "delete", teamPermission);
	}

	@Override
	public Page<Team> getAccountAccountKeyTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_teamService.getAccountTeams(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				TeamUtil::toTeam),
			pagination, _teamService.getAccountTeamsCount(accountKey));
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Team getTeam(String teamKey) throws Exception {
		return TeamUtil.toTeam(_teamService.getTeam(teamKey));
	}

	@Override
	public Page<Team> getTeamByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_teamService.getTeams(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				team -> TeamUtil.toTeam(team)),
			pagination,
			_teamService.getTeamsCount(domain, entityName, entityId));
	}

	@Override
	public Page<Team> getTeamsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.taproot.model.Team.class, search,
			pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> TeamUtil.toTeam(
				_teamLocalService.getTeam(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Team postAccountAccountKeyTeam(String accountKey, Team team)
		throws Exception {

		return TeamUtil.toTeam(
			_teamService.addTeam(accountKey, team.getName()));
	}

	@Override
	public Team putTeam(String teamKey, Team team) throws Exception {
		return TeamUtil.toTeam(
			_teamService.updateTeam(teamKey, team.getName()));
	}

	@Override
	public void putTeamContactByOkta(String teamKey, String[] oktaIds)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(oktaIds.length);

		for (String oktaId : oktaIds) {
			contacts.add(
				_oktaContactIdentityProvider.getContactByProviderId(oktaId));
		}

		_putTeamContacts(contacts, teamKey);
	}

	@Override
	public void putTeamContactByOktaRole(
			String teamKey, String oktaId, String[] contactRoleKeys)
		throws Exception {

		_putTeamContactRole(
			_oktaContactIdentityProvider.getContactByProviderId(oktaId),
			teamKey, contactRoleKeys);
	}

	@Override
	public void putTeamContactByUuid(String teamKey, String[] contactUuids)
		throws Exception {

		List<Contact> contacts = new ArrayList<>(contactUuids.length);

		for (String contactUuid : contactUuids) {
			contacts.add(
				_webContactIdentityProvider.getContactByProviderId(
					contactUuid));
		}

		_putTeamContacts(contacts, teamKey);
	}

	@Override
	public void putTeamContactByUuidContactUuidRole(
			String teamKey, String contactUuid, String[] contactRoleKeys)
		throws Exception {

		_putTeamContactRole(
			_webContactIdentityProvider.getContactByProviderId(contactUuid),
			teamKey, contactRoleKeys);
	}

	@Override
	public void putTeamTeamPermission(
			String teamKey, TeamPermission teamPermission)
		throws Exception {

		_updateTeamPermission(teamKey, "add", teamPermission);
	}

	private void _deleteTeamContactRole(
			Contact contact, String teamKey, String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		for (String contactRoleKey : contactRoleKeys) {
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactRoleKey);

			_contactTeamRoleService.deleteContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}
	}

	private void _deleteTeamContacts(List<Contact> contacts, String teamKey)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		for (Contact contact : contacts) {
			_contactTeamRoleService.deleteContactTeamRoles(
				contact.getContactId(), team.getTeamId());
		}
	}

	private void _putTeamContactRole(
			Contact contact, String teamKey, String[] contactRoleKeys)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		for (String contactRoleKey : contactRoleKeys) {
			ContactRole contactRole = _contactRoleLocalService.getContactRole(
				contactRoleKey);

			_contactTeamRoleService.addContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}
	}

	private void _putTeamContacts(List<Contact> contacts, String teamKey)
		throws PortalException {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		ContactRole contactRole = _contactRoleLocalService.getMemberContactRole(
			ContactRoleType.TEAM);

		for (Contact contact : contacts) {
			_contactTeamRoleService.addContactTeamRole(
				contact.getContactId(), team.getTeamId(),
				contactRole.getContactRoleId());
		}
	}

	private void _updateTeamPermission(
			String teamKey, String operation, TeamPermission teamPermission)
		throws Exception {

		com.liferay.osb.koroneiki.taproot.model.Team team =
			_teamLocalService.getTeam(teamKey);

		_teamPermission.check(
			PermissionThreadLocal.getPermissionChecker(), team,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(teamPermission.getAssignContact())) {
			actionIds.add(TaprootActionKeys.ASSIGN_CONTACT);
		}

		if (GetterUtil.getBoolean(teamPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(teamPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(teamPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(teamPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.taproot.model.Team.class.getName(),
			team.getTeamId(), teamPermission.getRoleNames(), actionIds);
	}

	private static final EntityModel _entityModel = new TeamEntityModel();

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactTeamRoleService _contactTeamRoleService;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _oktaContactIdentityProvider;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private com.liferay.osb.koroneiki.taproot.permission.TeamPermission
		_teamPermission;

	@Reference
	private TeamService _teamService;

	@Reference(target = "(provider=web)")
	private ContactIdentityProvider _webContactIdentityProvider;

}