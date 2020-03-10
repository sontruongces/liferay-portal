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

request.setAttribute("view_account.jsp-viewAccountDisplayContext", viewAccountDisplayContext);

AccountDisplay accountDisplay = new AccountDisplay(request, accountReader, viewAccountDisplayContext.getAccount());

request.setAttribute("view_account.jsp-accountDisplay", accountDisplay);
%>

<liferay-util:include page="/accounts/view_account_header.jsp" servletContext="<%= application %>" />

<div class="subscriptions">
	<div class="subscription-details">
		<liferay-util:include page="/accounts/view_subscriptions.jsp" servletContext="<%= application %>" />
	</div>

	<div class="side-panel" id="sidePanel">
		<react:component
			data="<%= Collections.emptyMap() %>"
			module="js/components/SidePanel"
		/>
	</div>
</div>