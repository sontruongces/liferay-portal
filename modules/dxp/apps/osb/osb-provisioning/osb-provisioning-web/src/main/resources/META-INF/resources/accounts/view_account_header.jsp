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
AccountDisplay accountDisplay = (AccountDisplay)request.getAttribute("view_account.jsp-accountDisplay");

Account koroneikiAccount = accountDisplay.getAccount();
%>

<div class="account-header autofit-row">
	<svg class="autofit-col header-icon">
		<use xlink:href="#account-icon" />
	</svg>

	<div class="autofit-col autofit-col-expand">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>

		<h3 class="account-name">
			<span class="account-code">
				<%= HtmlUtil.escape(koroneikiAccount.getCode()) %>
			</span>

			<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
		</h3>

		<ul class="header-details">
			<li>
				<div class="header-label">
					<liferay-ui:message key="status" />
				</div>

				<span class="label <%= accountDisplay.getStatusStyle() %>"><%= koroneikiAccount.getStatusAsString() %></span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="sup-region" />
				</div>

				<%= koroneikiAccount.getRegionAsString() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="country" />
				</div>

				<%= accountDisplay.getPrimaryCountry() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="sla" />
				</div>

				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="tier" />
				</div>

				<%= koroneikiAccount.getTierAsString() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="ewsa" />
				</div>

				<%= accountDisplay.getEWSA() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="developers" />
				</div>

				<%= accountDisplay.getDeveloperContactUsage() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="partner" />
				</div>

				<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>
			</li>
		</ul>
	</div>
</div>