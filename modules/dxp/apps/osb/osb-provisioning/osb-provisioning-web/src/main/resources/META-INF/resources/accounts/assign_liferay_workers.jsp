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

List<ContactRole> contactRoles = (List<ContactRole>)request.getAttribute(ProvisioningWebKeys.CONTACT_ROLES);

String emailAddress = ParamUtil.getString(request, "emailAddress");

String fullName = ParamUtil.getString(request, "fullName");

ViewAccountLiferayWorkersDisplayContext viewAccountLiferayWorkersDisplayContext = ProvisioningWebComponentProvider.getViewAccountLiferayWorkersDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountLiferayWorkersDisplayContext.getAccountDisplay();

Map<String, Object> accountContactsDetailsData = viewAccountLiferayWorkersDisplayContext.getAccountContactsDetailsData();

List<String> contactRoleKeys = new ArrayList<>();

if (contactRoles != null) {
	for (ContactRole contactRole : contactRoles) {
		contactRoleKeys.add(contactRole.getKey());
	}
}

if (emailAddress != null) {
	accountContactsDetailsData.put("emailAddress", emailAddress);
}

if (fullName != null) {
	accountContactsDetailsData.put("fullName", fullName);
}

accountContactsDetailsData.put("contactRoleKeys", contactRoleKeys);

accountContactsDetailsData.put("redirect", redirect);
%>

<div class="account-add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title='<%= !emailAddress.isEmpty() ? "edit-roles" : "assign-liferay-worker" %>'
	/>

	<portlet:actionURL name="/accounts/assign_contact_roles" var="assignContactRolesURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/assign_liferay_workers" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
	</portlet:actionURL>

	<aui:form action="<%= assignContactRolesURL.toString() %>" cssClass="container-fluid-1280" method="post" name="assignWorkersFm">
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