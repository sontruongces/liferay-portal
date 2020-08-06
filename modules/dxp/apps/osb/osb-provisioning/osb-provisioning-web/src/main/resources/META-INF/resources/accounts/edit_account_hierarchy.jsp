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
String parentAccountKey = ParamUtil.getString(request, "parentAccountKey");

AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse, request);

SearchContainer accountSearchContainer = accountSearchDisplayContext.getSearchContainer();
%>

<div class="container-fluid container-fluid-max-xl">
	<clay:management-toolbar
		clearResultsURL="<%= currentURL %>"
		elementClasses="full-width"
		itemsTotal="<%= accountSearchContainer.getTotal() %>"
		searchActionURL="<%= currentURL %>"
		searchContainerId="parentAccountContainer"
		selectable="<%= false %>"
		showSearch="<%= true %>"
	/>

	<liferay-ui:search-container
		cssClass="parent-account-container"
		id="parentAccountContainer"
		searchContainer="<%= accountSearchContainer %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.AccountDisplay"
			escapedModel="<%= true %>"
			keyProperty="accountKey"
			modelVar="accountDisplay"
		>

			<%
			Map<String, Object> accountData = new HashMap<String, Object>();

			accountData.put("key", accountDisplay.getKey());

			row.setData(accountData);

			if (parentAccountKey.equals(accountDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name-code"
			>
				<%= accountDisplay.getName() %>

				<div class="secondary-information">
					<%= accountDisplay.getCode() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="support-end-date"
				value="<%= accountDisplay.getSupportEndDate() %>"
			/>

			<liferay-ui:search-container-column-text
				name="partner"
				value="<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>"
			/>

			<liferay-ui:search-container-column-text
				name="region"
				value="<%= accountDisplay.getRegion() %>"
			/>

			<liferay-ui:search-container-column-text
				name="sla-tier"
			>
				<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

				<div class="secondary-information">
					<%= accountDisplay.getTier() %>
				</div>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="status"
			>
				<span class="label <%= accountDisplay.getStatusStyle() %>">
					<%= accountDisplay.getStatus() %>
				</span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	var searchContainer = document.getElementById(
		'<portlet:namespace />parentAccountContainerSearchContainer'
	);

	var table = searchContainer.querySelector('tbody');

	if (table) {
		var tableRows = table.querySelectorAll('tr');

		tableRows.forEach(selectRow);
	}

	function selectRow(row) {
		row.addEventListener('click', function() {
			var activeRows = Array.from(document.getElementsByClassName('active'));

			activeRows.forEach(resetRow);

			this.classList.add('active');

			var rowData = this.dataset;

			if (rowData) {
				Liferay.Util.getOpener().Liferay.fire('selectedItemChange', {
					data: rowData.key
				});
			}
		});
	}

	function resetRow(row) {
		row.classList.remove('active');
	}
</aui:script>