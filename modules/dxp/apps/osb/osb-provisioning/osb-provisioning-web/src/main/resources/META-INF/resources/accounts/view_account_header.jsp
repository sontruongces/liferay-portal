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

<liferay-ui:breadcrumb
	showCurrentGroup="<%= false %>"
	showGuestGroup="<%= false %>"
	showLayout="<%= true %>"
	showParentGroups="<%= false %>"
/>

<div>
	<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
</div>

<div>
	<liferay-ui:message key="status" />:

	<%= koroneikiAccount.getStatus() %>
</div>

<div>
	<liferay-ui:message key="sup-region" />:
</div>

<div>
	<liferay-ui:message key="country" />:

	<%= viewAccountDisplayContext.getPrimaryCountry() %>
</div>

<div>
	<liferay-ui:message key="sla" />:

	<c:if test="<%= slaProductPurchase != null %>">

		<%
		Product product = slaProductPurchase.getProduct();

		String name = StringUtil.removeSubstring(product.getName(), " Subscription");
		%>

		<%= HtmlUtil.escape(name) %>
	</c:if>
</div>

<div>
	<liferay-ui:message key="tier" />:

	<%= koroneikiAccount.getTier() %>
</div>

<div>
	<liferay-ui:message key="ewsa" />:

	<c:if test="<%= viewAccountDisplayContext.isEWSA() %>">
		<liferay-ui:message key="yes" />
	</c:if>
</div>

<div>
	<liferay-ui:message key="developers" />:

	<%
	int developerCount = accountReader.getDeveloperCount(koroneikiAccount);
	int maxDeveloperCount = accountReader.getMaxDeveloperCount(koroneikiAccount);
	%>

	<%= developerCount %> / <%= maxDeveloperCount %>

	<liferay-ui:message key="filled" />
</div>

<div>
	<liferay-ui:message key="partner" />:

	<c:if test="<%= partnerTeam != null %>">
		<%= HtmlUtil.escape(partnerTeam.getName()) %>
	</c:if>
</div>