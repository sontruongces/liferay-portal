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
ViewAccountDisplayContext viewAccountDisplayContext = (ViewAccountDisplayContext)request.getAttribute("view_account.jsp-viewAccountDisplayContext");

Account koroneikiAccount = viewAccountDisplayContext.getAccount();

Team partnerTeam = accountReader.getPartnerTeam(koroneikiAccount);
ProductPurchase slaProductPurchase = accountReader.getSLAProductPurchase(koroneikiAccount);

String accountStatus = koroneikiAccount.getStatusAsString();

String statusCssClass;

if (accountStatus.equals("Approved")) {
	statusCssClass = "label-success";
}
else if (accountStatus.equals("Closed")) {
	statusCssClass = "label-secondary";
}
else if (accountStatus.equals("Inactive")) {
		statusCssClass = "label-warning";
}
else if (accountStatus.startsWith("Pending")) {
	statusCssClass = "label-primary";
}
else {
	statusCssClass = "label-danger";
}
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

				<span class="label <%= statusCssClass %>"><%= accountStatus %></span>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="sup-region" />
				</div>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="country" />
				</div>

				<%= viewAccountDisplayContext.getPrimaryCountry() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="sla" />
				</div>

				<c:choose>
					<c:when test="<%= slaProductPurchase != null %>">

						<%
						Product product = slaProductPurchase.getProduct();

						String name = StringUtil.removeSubstring(product.getName(), " Subscription");
						%>

						<%= HtmlUtil.escape(name) %>
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="tier" />
				</div>

				<%= koroneikiAccount.getTier() %>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="ewsa" />
				</div>

				<c:choose>
					<c:when test="<%= viewAccountDisplayContext.isEWSA() %>">
						<liferay-ui:message key="yes" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="no" />
					</c:otherwise>
				</c:choose>
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="developers" />
				</div>

				<%
				int developerCount = accountReader.getDeveloperCount(koroneikiAccount);
				int maxDeveloperCount = accountReader.getMaxDeveloperCount(koroneikiAccount);
				%>

				<%= developerCount %> / <%= maxDeveloperCount %>

				<liferay-ui:message key="filled" />
			</li>
			<li>
				<div class="header-label">
					<liferay-ui:message key="partner" />
				</div>

				<c:choose>
					<c:when test="<%= partnerTeam != null %>">
						<%= HtmlUtil.escape(partnerTeam.getName()) %>
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>
</div>