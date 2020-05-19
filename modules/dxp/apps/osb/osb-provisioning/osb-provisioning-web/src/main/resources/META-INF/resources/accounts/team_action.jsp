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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TeamDisplay teamDisplay = (TeamDisplay)row.getObject();
%>

<c:if test="<%= !teamDisplay.isSystem() %>">
	<liferay-ui:icon-menu
		direction="left-side"
		icon="<%= StringPool.BLANK %>"
		markupView="lexicon"
		message="<%= StringPool.BLANK %>"
		showWhenSingleIcon="<%= true %>"
	>
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcRenderCommandName" value="/accounts/edit_team" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="teamKey" value="<%= teamDisplay.getKey() %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>

		<liferay-ui:icon-delete
			confirmation="are-you-sure-you-want-to-delete-this-team"
			url="<%= teamDisplay.getDeleteTeamURL() %>"
		/>
	</liferay-ui:icon-menu>
</c:if>