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

PortletURL searchURL = viewAccountDisplayContext.getPortletURL();
%>

<liferay-ui:tabs
	cssClass="related-accounts-tabs"
	names='<%= "all," + Account.Status.APPROVED.toString() + "," + Account.Status.CLOSED.toString() %>'
	param="tabs2"
	portletURL="<%= viewAccountDisplayContext.getPortletURL() %>"
>
	<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
		<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />

		<aui:input label="" name="keywords" placeholder="search" />
	</aui:form>

	<liferay-ui:search-container
		searchContainer="<%= viewAccountDisplayContext.getRelatedAccountsSearchContainer() %>"
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
			paginate="<%= false %>"
			resultRowSplitter="<%= viewAccountDisplayContext.getAccountResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</liferay-ui:tabs>