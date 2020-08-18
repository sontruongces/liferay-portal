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
Map<String, Object> data = new HashMap<>();

PortletURL accountsHomeURL = PortletURLFactoryUtil.create(request, ProvisioningPortletKeys.ACCOUNTS, PortletRequest.RENDER_PHASE);

data.put("accountsHomeURL", accountsHomeURL.toString());

ResourceURL autocompleteAccountURL = PortletURLFactoryUtil.create(request, ProvisioningPortletKeys.ACCOUNTS, PortletRequest.RESOURCE_PHASE);

autocompleteAccountURL.setResourceID("/accounts/autocomplete");

data.put("resourceURL", autocompleteAccountURL.toString());
%>

<div class="search-menu">
	<div class="container-fluid">
		<ul class="control-menu-nav search-menu-nav">
			<li class="logo">
				<svg class="company-logo">
					<use xlink:href="#liferay-waffle" />
				</svg>

				<h3>Raysource</h3>
			</li>
			<li class="account-search hide">
				<react:component
					data="<%= data %>"
					module="js/SearchApp"
				/>
			</li>
		</ul>
	</div>
</div>

<aui:script>
	var accounts = document.querySelector('.provisioning-accounts');

	if (accounts) {
		var accountSearch = document.querySelector('.account-search');

		if (accountSearch) {
			accountSearch.classList.remove('hide');
		}
	}
</aui:script>