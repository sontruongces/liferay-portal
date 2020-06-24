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
List<ContactRole> contactRoles = (List<ContactRole>)request.getAttribute(ProvisioningWebKeys.CONTACT_ROLES);

ViewAccountContactsDisplayContext viewAccountContactsDisplayContext = ProvisioningWebComponentProvider.getViewAccountContactsDisplayContext(renderRequest, renderResponse, request);

String redirect = viewAccountContactsDisplayContext.getRedirectURL();

AccountDisplay accountDisplay = viewAccountContactsDisplayContext.getAccountDisplay();

Map<String, Object> accountContactsDetailsData = viewAccountContactsDisplayContext.getAccountContactsDetailsData();

List<String> contactRoleKeys = new ArrayList<>();

if (contactRoles != null) {
	for (ContactRole contactRole : contactRoles) {
		contactRoleKeys.add(contactRole.getKey());
	}
}

accountContactsDetailsData.put("contactRoleKeys", contactRoleKeys);
%>

<div class="account-add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= (contactRoles != null) ? "edit-roles" : "assign-contact" %>'
	/>

	<portlet:actionURL name="/accounts/assign_contact_roles" var="assignContactRolesURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/assign_contacts" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
	</portlet:actionURL>

	<aui:form action="<%= assignContactRolesURL.toString() %>" cssClass="container-fluid-1280" method="post" name="assignContactsFm">
		<div class="assign-contacts-sheet sheet">
			<liferay-ui:error exception="<%= HttpException.class %>">

				<%
				HttpException httpException = (HttpException)errorException;
				%>

				<%= httpException.getMessage() %>
			</liferay-ui:error>

			<react:component
				data="<%= accountContactsDetailsData %>"
				module="js/AccountAddContactsApp"
			/>
		</div>
	</aui:form>
</div>