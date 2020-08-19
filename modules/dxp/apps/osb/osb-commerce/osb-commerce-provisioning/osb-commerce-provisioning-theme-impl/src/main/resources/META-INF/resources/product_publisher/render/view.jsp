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
Map<String, Object> cpEntriesMap = (Map<String, Object>)request.getAttribute("osb-commerce-provisioning:CPEntriesMap");
%>

<div class="container product-publisher-container">
	<react:component
		data="<%= cpEntriesMap %>"
		module="js/components/list_renderer/ListRenderer"
	/>
</div>