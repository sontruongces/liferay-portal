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

ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewTeamDisplayContext.getAccountDisplay();

Team team = viewTeamDisplayContext.getTeam();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "new-team"));
%>

<portlet:actionURL name="/accounts/edit_team" var="editTeamURL">
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
	<portlet:param name="teamKey" value='<%= (team != null) ? team.getKey() : "" %>' />
</portlet:actionURL>

<aui:form action="<%= editTeamURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="name" value='<%= (team != null) ? team.getName() : "" %>' />

	<label><liferay-ui:message key="team-members" /></label>

	<aui:button value="select" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>