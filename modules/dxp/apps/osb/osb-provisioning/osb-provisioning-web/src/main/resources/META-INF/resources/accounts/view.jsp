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
AccountsDisplayContext accountsDisplayContext = (AccountsDisplayContext)liferayPortletRequest.getAttribute(AccountsDisplayContext.class.getName());
%>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= accountsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account"
			escapedModel="<%= true %>"
			keyProperty="key"
			modelVar="koroneikiAccount"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/edit_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="key" value="<%= String.valueOf(koroneikiAccount.getKey()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= koroneikiAccount.getName() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="code"
				value="<%= koroneikiAccount.getCode() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner"
				value="<%= AccountUtil.getPartner(koroneikiAccount) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
				value="<%= AccountUtil.getSupportEndDate(koroneikiAccount) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="licenses"
				value=""
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="highest-sla"
				value="<%= AccountUtil.getHighestSLA(koroneikiAccount) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="tier"
				value="<%= koroneikiAccount.getTierAsString() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value=""
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
				value="<%= koroneikiAccount.getStatusAsString() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>