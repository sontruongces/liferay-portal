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

<liferay-ui:tabs
	names="active,inactive,all"
	refresh="<%= false %>"
>
	<liferay-ui:section>
		<liferay-util:include page="/accounts/subscriptions_list.jsp" servletContext="<%= application %>">
			<liferay-util:param name="state" value="active" />
		</liferay-util:include>
	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:include page="/accounts/subscriptions_list.jsp" servletContext="<%= application %>">
			<liferay-util:param name="state" value="inactive" />
		</liferay-util:include>
	</liferay-ui:section>

	<liferay-ui:section>
		<liferay-util:include page="/accounts/subscriptions_list.jsp" servletContext="<%= application %>" />
	</liferay-ui:section>
</liferay-ui:tabs>