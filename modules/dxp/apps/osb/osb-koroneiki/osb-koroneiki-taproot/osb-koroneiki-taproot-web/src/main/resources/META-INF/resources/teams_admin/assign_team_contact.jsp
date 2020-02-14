<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

Team team = (Team)request.getAttribute(TaprootWebKeys.TEAM);

renderResponse.setTitle(team.getName());
%>

<portlet:actionURL name="/teams_admin/assign_team_contact" var="assignTeamContactRoleURL" />

<div class="main-content-body">
	<aui:form action="<%= assignTeamContactRoleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="teamId" type="hidden" value="<%= team.getTeamId() %>" />

		<liferay-ui:error exception="<%= NoSuchContactException.class %>" message="the-contact-could-not-be-found" />

		<aui:fieldset-group>
			<aui:fieldset>
				<aui:input name="emailAddress" type="text" />

				<aui:select label="contact-role" name="contactRoleId">

					<%
					List<ContactRole> contactRoles = ContactRoleLocalServiceUtil.getContactRoles(com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole.Type.TEAM.toString(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					for (ContactRole contactRole : contactRoles) {
					%>

						<aui:option label="<%= contactRole.getName() %>" value="<%= contactRole.getContactRoleId() %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>