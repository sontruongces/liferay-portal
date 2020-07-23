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
%>

<div class="account-add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="<%= viewAccountLiferayWorkersDisplayContext.getAssignLiferayWorkerTitle() %>"
	/>

	<portlet:actionURL name="/accounts/assign_contact_roles" var="assignContactRolesURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/assign_liferay_workers" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
	</portlet:actionURL>

	<aui:form action="<%= assignContactRolesURL.toString() %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="assignLiferayWorkersFm">
		<div class="assign-contacts-sheet sheet">
			<liferay-ui:error exception="<%= HttpException.class %>">

				<%
				HttpException httpException = (HttpException)errorException;
				%>

				<%= httpException.getMessage() %>
			</liferay-ui:error>

			<react:component
				data="<%= viewAccountLiferayWorkersDisplayContext.getAssignLiferayWorkerData() %>"
				module="js/AccountAddContactsApp"
			/>
		</div>
	</aui:form>
</div>