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

viewAccountDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/accounts/view_account_header.jsp" servletContext="<%= application %>" />

<div class="account full-view" id="account">
	<div class="account-details">
		<liferay-ui:tabs
			names="subscriptions,details,contacts,liferay-workers,related-accounts,support,history,opportunities"
			portletURL="<%= viewAccountDisplayContext.getPortletURL() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("details") %>'>
				<liferay-util:include page="/accounts/view_account_details.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<div class="subscription-details" id="subscriptionDetails">
					<liferay-util:include page="/accounts/view_account_subscriptions.jsp" servletContext="<%= application %>" />
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="side-panel" id="sidePanel">
		<react:component
			data="<%= viewAccountDisplayContext.getPanelData() %>"
			module="js/SidePanelApp"
		/>
	</div>
</div>