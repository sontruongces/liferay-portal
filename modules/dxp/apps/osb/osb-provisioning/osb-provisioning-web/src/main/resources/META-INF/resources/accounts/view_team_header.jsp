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
ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

TeamDisplay teamDisplay = viewTeamDisplayContext.getTeamDisplay();
%>

<div class="autofit-row team-header">
	<svg class="autofit-col header-icon">
		<use xlink:href="#team-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="team-name">
			<%= HtmlUtil.escape(teamDisplay.getName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="created" />
				</div>

				<%= teamDisplay.getDateCreated() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="last-modified" />
				</div>

				<%= teamDisplay.getDateModified() %>
			</li>
		</ul>
	</div>

	<div class="autofit-col">
		<c:if test="<%= !teamDisplay.isSystem() %>">

			<%
			String taglibOnClick = "if (confirm('" + LanguageUtil.get(request, "are-you-sure-you-want-to-delete-this-team") + "')) {submitForm(document.hrefFm, '" + teamDisplay.getDeleteTeamURL() + "');}";
			%>

			<aui:button cssClass="btn-secondary" onClick="<%= taglibOnClick %>" value="delete" />
		</c:if>
	</div>
</div>