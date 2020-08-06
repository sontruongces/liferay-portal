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
String teamKey = ParamUtil.getString(request, "teamKey");

AssignAccountTeamDisplayContext assignAccountTeamDisplayContext = ProvisioningWebComponentProvider.getAssignAccountTeamDisplayContext(renderRequest, renderResponse, request);

String teamRoleName = ParamUtil.getString(request, "teamRoleName");

SearchContainer searchContainer = assignAccountTeamDisplayContext.getSearchContainer(teamRoleName);
%>

<clay:management-toolbar
	clearResultsURL="<%= assignAccountTeamDisplayContext.getClearResultsURL() %>"
	elementClasses="full-width"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= assignAccountTeamDisplayContext.getCurrentURL() %>"
	searchContainerId="teamContainer"
	searchFormName="searchFm"
	selectable="<%= true %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		cssClass="details-search-container"
		id="teamContainer"
		searchContainer="<%= searchContainer %>"
		var="teamsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.TeamDisplay"
			escapedModel="<%= true %>"
			keyProperty="teamKey"
			modelVar="teamDisplay"
		>

			<%
			Map<String, Object> accountData = new HashMap<String, Object>();

			accountData.put("key", teamDisplay.getKey());

			row.setData(accountData);

			if (teamKey.equals(teamDisplay.getKey())) {
				row.setCssClass("active");
			}
			%>

			<liferay-ui:search-container-column-text
				name="team-name"
			>
				<%= teamDisplay.getName() %>
			</liferay-ui:search-container-column-text>

			<%
			Account teamAccount = teamDisplay.getAccount();
			%>

			<liferay-ui:search-container-column-text
				name="account-name"
			>
				<%= teamAccount.getName() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="account-code"
			>
				<%= teamAccount.getCode() %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script>
	var searchContainer = document.getElementById(
		'<portlet:namespace />teamContainerSearchContainer'
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