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
AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);

ViewAccountsManagementToolbarDisplayContext viewAccountsManagementToolbarDisplayContext = new ViewAccountsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, accountSearchDisplayContext.getSearchContainer());
%>

<div class="container-fluid container-fluid-max-xl">
	<div class="alert alert-info" role="alert">
		<div class="alert-autofit-row autofit-row">
			<div>
				<liferay-ui:message arguments='<%= new String[] {"<a href='https://reports.liferay.com/#/site/support/workbooks/810/views' target='_blank'>", "</a>"} %>' key="advanced-search-is-temporarily-provided-by-the-cas-monitoring-report" translateArguments="<%= false %>" />
			</div>
		</div>
	</div>

	<portlet:actionURL name="/search" var="searchURL" />

	<clay:management-toolbar
		displayContext="<%= viewAccountsManagementToolbarDisplayContext %>"
	/>

	<liferay-ui:search-container
		cssClass=""
		searchContainer="<%= accountSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			escapedModel="<%= true %>"
			keyProperty="accountKey"
			modelVar="accountDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name-code"
			>
				<%= accountDisplay.getName() %>

				<div class="secondary-information">
					<%= accountDisplay.getCode() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
				value="<%= accountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner"
				value="<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value="<%= accountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="sla-tier"
			>
				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= accountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label <%= accountDisplay.getStatusStyle() %>"><%= accountDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>