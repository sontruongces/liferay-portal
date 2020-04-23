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

String tabs2 = ParamUtil.getString(request, "tabs2");

PortletURL portletURL = viewAccountDisplayContext.getPortletURL();
%>

<div class="details-table">
	<liferay-util:include page="/common/tabs.jsp" servletContext="<%= application %>">
		<liferay-util:param name="names" value="active,inactive,all" />
		<liferay-util:param name="param" value="tabs2" />
		<liferay-util:param name="url" value="<%= portletURL.toString() %>" />
		<liferay-util:param name="values" value="active,inactive,all" />
	</liferay-util:include>

	<c:choose>
		<c:when test='<%= tabs2.equals("all") %>'>
			<liferay-util:include page="/accounts/subscriptions_list.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs2.equals("inactive") %>'>
			<liferay-util:include page="/accounts/subscriptions_list.jsp" servletContext="<%= application %>">
				<liferay-util:param name="state" value="inactive" />
			</liferay-util:include>
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/accounts/subscriptions_list.jsp" servletContext="<%= application %>">
				<liferay-util:param name="state" value="active" />
			</liferay-util:include>
		</c:otherwise>
	</c:choose>
</div>