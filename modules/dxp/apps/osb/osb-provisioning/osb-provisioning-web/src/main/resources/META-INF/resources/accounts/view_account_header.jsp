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
%>

<div class="account-header">
	<svg class="header-icon">
		<use xlink:href="#account-icon" />
	</svg>
	
	<div>
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= true %>"
			showParentGroups="<%= false %>"
		/>
	
		<h3>
			<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
		</h3>
	
		<ul>
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="status" />
				</div>
				<%= koroneikiAccount.getStatus() %>
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="sup-region" />
				</div>
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="country" />
				</div>
	
				<%= viewAccountDisplayContext.getPrimaryCountry() %>
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="sla" />
				</div>
	
				<c:if test="<%= slaProductPurchase != null %>">
	
					<%
					Product product = slaProductPurchase.getProduct();
	
					String name = StringUtil.removeSubstring(product.getName(), " Subscription");
					%>
	
					<%= HtmlUtil.escape(name) %>
				</c:if>
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="tier" />
				</div>
	
				<%= koroneikiAccount.getTier() %>
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="ewsa" />
				</div>
	
				<c:if test="<%= viewAccountDisplayContext.isEWSA() %>">
					<liferay-ui:message key="yes" />
				</c:if>
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="developers" />
				</div>
	
				<%
				int developerCount = accountReader.getDeveloperCount(koroneikiAccount);
				int maxDeveloperCount = accountReader.getMaxDeveloperCount(koroneikiAccount);
				%>
	
				<%= developerCount %> / <%= maxDeveloperCount %>
	
				<liferay-ui:message key="filled" />
			</div>
	
			<div>
				<div class="list-group-headersmall-heading">
					<liferay-ui:message key="partner" />
				</div>
	
				<c:if test="<%= partnerTeam != null %>">
					<%= HtmlUtil.escape(partnerTeam.getName()) %>
				</c:if>
			</div>
		</ul>
	</div>
</div>
