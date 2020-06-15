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

viewContactDisplayContext.addPortletBreadcrumbEntries();
%>

<liferay-util:include page="/users/view_contact_header.jsp" servletContext="<%= application %>" />

<div class="contact" id="contact">
	<div class="contact-content">
		<liferay-ui:tabs
			names="accounts"
			portletURL="<%= viewContactDisplayContext.getPortletURL() %>"
		/>

		<liferay-util:include page="/users/view_contact_accounts.jsp" servletContext="<%= application %>" />
	</div>
</div>