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

package com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamSerDes;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public interface TeamResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<Team> getAccountAccountKeyTeamsPage(
			String accountKey, Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse getAccountAccountKeyTeamsPageHttpResponse(
			String accountKey, Pagination pagination)
		throws Exception;

	public Team postAccountAccountKeyTeam(String accountKey, Team team)
		throws Exception;

	public HttpInvoker.HttpResponse postAccountAccountKeyTeamHttpResponse(
			String accountKey, Team team)
		throws Exception;

	public Page<Team> getTeamsPage(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public HttpInvoker.HttpResponse getTeamsPageHttpResponse(
			String search, String filterString, Pagination pagination,
			String sortString)
		throws Exception;

	public Page<Team> getTeamByExternalLinkDomainEntityNameEntity(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception;

	public HttpInvoker.HttpResponse
			getTeamByExternalLinkDomainEntityNameEntityHttpResponse(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception;

	public void deleteTeam(String teamKey) throws Exception;

	public HttpInvoker.HttpResponse deleteTeamHttpResponse(String teamKey)
		throws Exception;

	public Team getTeam(String teamKey) throws Exception;

	public HttpInvoker.HttpResponse getTeamHttpResponse(String teamKey)
		throws Exception;

	public Team putTeam(String teamKey, Team team) throws Exception;

	public HttpInvoker.HttpResponse putTeamHttpResponse(
			String teamKey, Team team)
		throws Exception;

	public void deleteTeamContactByOkta(String teamKey, String[] oktaIds)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamContactByOktaHttpResponse(
			String teamKey, String[] oktaIds)
		throws Exception;

	public void putTeamContactByOkta(String teamKey, String[] oktaIds)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamContactByOktaHttpResponse(
			String teamKey, String[] oktaIds)
		throws Exception;

	public void deleteTeamContactByOktaRole(
			String teamKey, String oktaId, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamContactByOktaRoleHttpResponse(
			String teamKey, String oktaId, String[] contactRoleKeys)
		throws Exception;

	public void putTeamContactByOktaRole(
			String teamKey, String oktaId, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamContactByOktaRoleHttpResponse(
			String teamKey, String oktaId, String[] contactRoleKeys)
		throws Exception;

	public void deleteTeamContactByUuid(String teamKey, String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamContactByUuidHttpResponse(
			String teamKey, String[] contactUuids)
		throws Exception;

	public void putTeamContactByUuid(String teamKey, String[] contactUuids)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamContactByUuidHttpResponse(
			String teamKey, String[] contactUuids)
		throws Exception;

	public void deleteTeamContactByUuidContactUuidRole(
			String teamKey, String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			deleteTeamContactByUuidContactUuidRoleHttpResponse(
				String teamKey, String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void putTeamContactByUuidContactUuidRole(
			String teamKey, String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public HttpInvoker.HttpResponse
			putTeamContactByUuidContactUuidRoleHttpResponse(
				String teamKey, String contactUuid, String[] contactRoleKeys)
		throws Exception;

	public void deleteTeamTeamPermission(
			String teamKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamPermission
				teamPermission)
		throws Exception;

	public HttpInvoker.HttpResponse deleteTeamTeamPermissionHttpResponse(
			String teamKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamPermission
				teamPermission)
		throws Exception;

	public void putTeamTeamPermission(
			String teamKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamPermission
				teamPermission)
		throws Exception;

	public HttpInvoker.HttpResponse putTeamTeamPermissionHttpResponse(
			String teamKey,
			com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamPermission
				teamPermission)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public TeamResource build() {
			return new TeamResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		private Builder() {
		}

		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class TeamResourceImpl implements TeamResource {

		public Page<Team> getAccountAccountKeyTeamsPage(
				String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getAccountAccountKeyTeamsPageHttpResponse(
					accountKey, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, TeamSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getAccountAccountKeyTeamsPageHttpResponse(
					String accountKey, Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/teams",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Team postAccountAccountKeyTeam(String accountKey, Team team)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postAccountAccountKeyTeamHttpResponse(accountKey, team);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return TeamSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse postAccountAccountKeyTeamHttpResponse(
				String accountKey, Team team)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(team.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/accounts/{accountKey}/teams",
				accountKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Team> getTeamsPage(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse = getTeamsPageHttpResponse(
				search, filterString, pagination, sortString);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, TeamSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse getTeamsPageHttpResponse(
				String search, String filterString, Pagination pagination,
				String sortString)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (search != null) {
				httpInvoker.parameter("search", String.valueOf(search));
			}

			if (filterString != null) {
				httpInvoker.parameter("filter", filterString);
			}

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			if (sortString != null) {
				httpInvoker.parameter("sort", sortString);
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/teams");

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<Team> getTeamByExternalLinkDomainEntityNameEntity(
				String domain, String entityName, String entityId,
				Pagination pagination)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getTeamByExternalLinkDomainEntityNameEntityHttpResponse(
					domain, entityName, entityId, pagination);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, TeamSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getTeamByExternalLinkDomainEntityNameEntityHttpResponse(
					String domain, String entityName, String entityId,
					Pagination pagination)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (pagination != null) {
				httpInvoker.parameter(
					"page", String.valueOf(pagination.getPage()));
				httpInvoker.parameter(
					"pageSize", String.valueOf(pagination.getPageSize()));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/by-external-link/{domain}/{entityName}/{entityId}",
				domain, entityName, entityId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeam(String teamKey) throws Exception {
			HttpInvoker.HttpResponse httpResponse = deleteTeamHttpResponse(
				teamKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteTeamHttpResponse(String teamKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/teams/{teamKey}",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Team getTeam(String teamKey) throws Exception {
			HttpInvoker.HttpResponse httpResponse = getTeamHttpResponse(
				teamKey);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return TeamSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse getTeamHttpResponse(String teamKey)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/teams/{teamKey}",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Team putTeam(String teamKey, Team team) throws Exception {
			HttpInvoker.HttpResponse httpResponse = putTeamHttpResponse(
				teamKey, team);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			try {
				return TeamSerDes.toDTO(content);
			}
			catch (Exception e) {
				_logger.log(
					Level.WARNING,
					"Unable to process HTTP response: " + content, e);

				throw e;
			}
		}

		public HttpInvoker.HttpResponse putTeamHttpResponse(
				String teamKey, Team team)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(team.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port + "/o/koroneiki-rest/v1.0/teams/{teamKey}",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByOkta(String teamKey, String[] oktaIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByOktaHttpResponse(teamKey, oktaIds);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteTeamContactByOktaHttpResponse(
				String teamKey, String[] oktaIds)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (oktaIds != null) {
				for (int i = 0; i < oktaIds.length; i++) {
					httpInvoker.parameter(
						"oktaIds", String.valueOf(oktaIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-okta-id",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamContactByOkta(String teamKey, String[] oktaIds)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByOktaHttpResponse(teamKey, oktaIds);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putTeamContactByOktaHttpResponse(
				String teamKey, String[] oktaIds)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(oktaIds.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (oktaIds != null) {
				for (int i = 0; i < oktaIds.length; i++) {
					httpInvoker.parameter(
						"oktaIds", String.valueOf(oktaIds[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-okta-id",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByOktaRole(
				String teamKey, String oktaId, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByOktaRoleHttpResponse(
					teamKey, oktaId, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteTeamContactByOktaRoleHttpResponse(
				String teamKey, String oktaId, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-okta-id/{oktaId}/roles",
				teamKey, oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamContactByOktaRole(
				String teamKey, String oktaId, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByOktaRoleHttpResponse(
					teamKey, oktaId, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putTeamContactByOktaRoleHttpResponse(
				String teamKey, String oktaId, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRoleKeys.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-okta-id/{oktaId}/roles",
				teamKey, oktaId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByUuid(
				String teamKey, String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByUuidHttpResponse(teamKey, contactUuids);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteTeamContactByUuidHttpResponse(
				String teamKey, String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamContactByUuid(String teamKey, String[] contactUuids)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByUuidHttpResponse(teamKey, contactUuids);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putTeamContactByUuidHttpResponse(
				String teamKey, String[] contactUuids)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactUuids.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (contactUuids != null) {
				for (int i = 0; i < contactUuids.length; i++) {
					httpInvoker.parameter(
						"contactUuids", String.valueOf(contactUuids[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamContactByUuidContactUuidRole(
				String teamKey, String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamContactByUuidContactUuidRoleHttpResponse(
					teamKey, contactUuid, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				deleteTeamContactByUuidContactUuidRoleHttpResponse(
					String teamKey, String contactUuid,
					String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles",
				teamKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamContactByUuidContactUuidRole(
				String teamKey, String contactUuid, String[] contactRoleKeys)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamContactByUuidContactUuidRoleHttpResponse(
					teamKey, contactUuid, contactRoleKeys);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putTeamContactByUuidContactUuidRoleHttpResponse(
					String teamKey, String contactUuid,
					String[] contactRoleKeys)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(contactRoleKeys.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			if (contactRoleKeys != null) {
				for (int i = 0; i < contactRoleKeys.length; i++) {
					httpInvoker.parameter(
						"contactRoleKeys", String.valueOf(contactRoleKeys[i]));
				}
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/contacts/by-uuid/{contactUuid}/roles",
				teamKey, contactUuid);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void deleteTeamTeamPermission(
				String teamKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					TeamPermission teamPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				deleteTeamTeamPermissionHttpResponse(teamKey, teamPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse deleteTeamTeamPermissionHttpResponse(
				String teamKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					TeamPermission teamPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.DELETE);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/team-permissions",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putTeamTeamPermission(
				String teamKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					TeamPermission teamPermission)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putTeamTeamPermissionHttpResponse(teamKey, teamPermission);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse putTeamTeamPermissionHttpResponse(
				String teamKey,
				com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.
					TeamPermission teamPermission)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(teamPermission.toString(), "application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/koroneiki-rest/v1.0/teams/{teamKey}/team-permissions",
				teamKey);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private TeamResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			TeamResource.class.getName());

		private Builder _builder;

	}

}