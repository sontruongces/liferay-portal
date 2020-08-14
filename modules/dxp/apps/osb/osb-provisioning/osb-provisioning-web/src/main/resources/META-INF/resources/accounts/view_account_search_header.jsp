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
AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);
%>

<div class="search-menu">
	<div class="container-fluid">
		<ul class="control-menu-nav">
			<li class="logo">
				<svg class="company-logo">
					<use xlink:href="#liferay-waffle" />
				</svg>
				Raysource
			</li>
			<li class="account-search">
				<react:component
					data="<%= accountSearchDisplayContext.getAutocompleteAccountData() %>"
					module="js/SearchApp"
				/>
			</li>
		</ul>
	</div>
</div>

<aui:script>
	
</aui:script>