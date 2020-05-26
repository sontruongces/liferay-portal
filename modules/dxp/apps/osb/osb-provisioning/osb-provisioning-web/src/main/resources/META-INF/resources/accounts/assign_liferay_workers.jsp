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

ViewAccountLiferayWorkersDisplayContext viewAccountLiferayWorkersDisplayContext = ProvisioningWebComponentProvider.getViewAccountLiferayWorkersDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountLiferayWorkersDisplayContext.getAccountDisplay();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "assign-liferay-worker"));
%>

<portlet:actionURL name="/accounts/assign_contact_roles" var="assignContactRolesURL">
	<portlet:param name="mvcRenderCommandName" value="/accounts/assign_liferay_workers" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
</portlet:actionURL>

<aui:form action="<%= assignContactRolesURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm">
	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<aui:input name="emailAddress" />

	<aui:select label="roles" multiple="<%= true %>" name="addContactRoleKeys">

		<%
		List<ContactRole> contactRoles = viewAccountLiferayWorkersDisplayContext.getContactRoles();

		for (ContactRole contactRole : contactRoles) {
		%>

			<aui:option label="<%= contactRole.getName() %>" value="<%= contactRole.getKey() %>" />

		<%
		}
		%>

	</aui:select>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>