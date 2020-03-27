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
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
	<portlet:param name="tabs1" value="contacts" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="accountKey" value="<%= accountDisplay.getAccountKey() %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<aui:input label="" name="keywords" placeholder="search" />

	<aui:select label="roles" multiple="<%= true %>" name="contactRoleKeys">

		<%
		for (ContactRole contactRole : viewAccountDisplayContext.getContactRoles(ContactRole.Type.ACCOUNT_CUSTOMER.toString())) {
		%>

			<aui:option label="<%= contactRole.getName() %>" value="<%= contactRole.getKey() %>" />

		<%
		}
		%>

	</aui:select>
</aui:form>

<liferay-ui:search-container
	searchContainer="<%= viewAccountDisplayContext.getContactsSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay"
		escapedModel="<%= true %>"
		modelVar="contactDisplay"
	>
		<liferay-ui:search-container-column-text
			name="name-email"
		>
			<%= contactDisplay.getFullName() %>

			<div class="secondary-information">
				<%= contactDisplay.getEmailAddress() %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="role"
		>
			<%= StringUtil.merge(contactDisplay.getContactRoleNames(), "<br />") %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="status"
		>
			<span class="label"><%= contactDisplay.getStatus() %></span>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			path="/accounts/contact_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>