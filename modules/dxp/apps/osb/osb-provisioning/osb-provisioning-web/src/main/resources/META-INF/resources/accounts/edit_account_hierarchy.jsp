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

ViewAccountRelatedAccountsDisplayContext viewAccountRelatedAccountsDisplayContext = ProvisioningWebComponentProvider.getViewAccountRelatedAccountsDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountRelatedAccountsDisplayContext.getAccountDisplay();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "edit-account-hierarchy"));
%>

<portlet:actionURL name="/accounts/edit_account_hierarchy" var="editAccountHierarchyURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
</portlet:actionURL>

<aui:form action="<%= editAccountHierarchyURL.toString() %>" cssClass="container-fluid-1280" method="post" name="editAccountHierarchyFm">

	<%
	AccountDisplay parentAccountDisplay = viewAccountRelatedAccountsDisplayContext.getParentAccountDisplay();
	%>

	<aui:row>
		<aui:col width="<%= 20 %>">
			<liferay-ui:message key="parent-account" />
		</aui:col>

		<aui:col width="<%= 80 %>">
			<aui:row>
				<aui:col width="<%= 66 %>">
					<liferay-ui:message key="account-name" />
				</aui:col>

				<aui:col width="<%= 33 %>">
					<liferay-ui:message key="code" />
				</aui:col>
			</aui:row>

			<c:if test="<%= parentAccountDisplay != null %>">
				<aui:row>
					<aui:col width="<%= 66 %>">
						<%= parentAccountDisplay.getName() %>
					</aui:col>

					<aui:col width="<%= 33 %>">
						<%= parentAccountDisplay.getCode() %>
					</aui:col>
				</aui:row>
			</c:if>

			<aui:row>
				<aui:button value="select" />
			</aui:row>
		</aui:col>
	</aui:row>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>