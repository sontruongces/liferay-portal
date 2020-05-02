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
ViewSubscriptionDisplayContext viewSubscriptionDisplayContext = ProvisioningWebComponentProvider.getViewSubscriptionDisplayContext(renderRequest, renderResponse, request);

viewSubscriptionDisplayContext.addPortletBreadcrumbEntries();

String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/accounts/view_subscription_header.jsp" servletContext="<%= application %>" />

<div class="subscription" id="subscription">
	<div class="subscription-details">
		<liferay-ui:tabs
			names="terms,details"
			portletURL="<%= viewSubscriptionDisplayContext.getPortletURL() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs1.equals("details") %>'>
				<liferay-util:include page="/accounts/view_subscription_details.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/accounts/view_subscription_terms.jsp" servletContext="<%= application %>" />
			</c:otherwise>
		</c:choose>
	</div>

	<div class="side-panel" id="sidePanel">
		<react:component
			data="<%= viewSubscriptionDisplayContext.getPanelData() %>"
			module="js/SidePanelApp"
		/>
	</div>
</div>