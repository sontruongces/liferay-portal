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

EntitlementDefinition entitlementDefinition = (EntitlementDefinition)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/entitlement_definitions_admin/edit_entitlement_definition" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="entitlementDefinitionId" value="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		message="edit"
		url="<%= editURL %>"
	/>

	<liferay-security:permissionsURL
		modelResource="<%= EntitlementDefinition.class.getName() %>"
		modelResourceDescription="<%= entitlementDefinition.getName() %>"
		resourcePrimKey="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>"
		var="permissionsURL"
		windowState="<%= LiferayWindowState.POP_UP.toString() %>"
	/>

	<liferay-ui:icon
		message="permissions"
		method="get"
		url="<%= permissionsURL %>"
		useDialog="<%= true %>"
	/>

	<portlet:actionURL name="/entitlement_definitions_admin/edit_entitlement_definition" var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="entitlementDefinitionId" value="<%= String.valueOf(entitlementDefinition.getEntitlementDefinitionId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="are-you-sure-you-want-to-delete-this-entitlement-definition"
		url="<%= deleteURL %>"
	/>
</liferay-ui:icon-menu>