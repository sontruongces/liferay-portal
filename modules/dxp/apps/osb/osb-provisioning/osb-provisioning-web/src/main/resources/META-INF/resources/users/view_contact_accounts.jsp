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
ViewContactDisplayContext viewContactDisplayContext = ProvisioningWebComponentProvider.getViewContactDisplayContext(renderRequest, renderResponse, request);
%>

<div class="details-table table-striped">
	<h3 class="panel-title">
		<liferay-ui:message key="contact" />
	</h3>

	<liferay-ui:search-container
		id="contacts"
		searchContainer="<%= viewContactDisplayContext.getCustomerAccountsSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ContactDisplay.AccountDisplay"
			escapedModel="<%= true %>"
			modelVar="accountDisplay"
		>
			<liferay-ui:search-container-column-text
				name="account-name-code"
			>
				<%= accountDisplay.getName() %>

				<div class="secondary-information">
					<%= accountDisplay.getCode() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="region"
			>
				<%= accountDisplay.getRegion() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="roles"
			>
				<%= StringUtil.merge(accountDisplay.getContactRoleNames(), "<br />") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-status"
			>
				<span class="label <%= accountDisplay.getStatusStyle() %>"><%= accountDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>