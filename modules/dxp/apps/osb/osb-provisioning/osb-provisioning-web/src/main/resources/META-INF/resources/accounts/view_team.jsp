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

<liferay-util:include page="/accounts/view_account_search_header.jsp" servletContext="<%= application %>" />

<%
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

viewTeamDisplayContext.addPortletBreadcrumbEntries();
%>

<liferay-util:include page="/accounts/view_team_header.jsp" servletContext="<%= application %>" />

<div class="account team" id="team">
	<div class="account-content team-details">
		<liferay-ui:tabs
			names="team-members"
			portletURL="<%= viewTeamDisplayContext.getPortletURL() %>"
		/>

		<div class="details-table member-details" id="memberDetails">
			<liferay-util:include page="/accounts/view_team_members.jsp" servletContext="<%= application %>" />
		</div>
	</div>

	<div class="side-panel" id="sidePanel">
		<react:component
			data="<%= viewTeamDisplayContext.getPanelData() %>"
			module="js/SidePanelApp"
		/>
	</div>
</div>