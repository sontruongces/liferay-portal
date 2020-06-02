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
String redirect = ParamUtil.getString(request, "redirect");

ViewTeamDisplayContext viewTeamDisplayContext = ProvisioningWebComponentProvider.getViewTeamDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewTeamDisplayContext.getAccountDisplay();

Team team = viewTeamDisplayContext.getTeam();
%>

<div class="account-add-items">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="new-team"
	/>

	<portlet:actionURL name="/accounts/edit_team" var="editTeamURL">
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
		<portlet:param name="teamKey" value='<%= (team != null) ? team.getKey() : "" %>' />
	</portlet:actionURL>

	<aui:form action="<%= editTeamURL.toString() %>" cssClass="container-fluid-1280" method="post" name="editTeamFm">
		<div class="add-items-sheet sheet sheet-lg">
			<aui:input inlineLabel="left" name="name" required="<%= true %>" value='<%= (team != null) ? team.getName() : "" %>' />

			<div class="form-group form-inline last">
				<label class="control-label">
					<liferay-ui:message key="team-members" />
				</label>

				<div class="field-input">
					<c:if test="<%= true %>">
						<table class="table table-list table-sm">
							<thead>
								<tr>
									<th>
										<liferay-ui:message key="user-name" />
									</th>
									<th>
										<liferay-ui:message key="user-email" />
									</th>
									<th>
									</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<td>
										<liferay-ui:message key="user-name" />
									</td>
									<td>
										<liferay-ui:message key="user-email" />
									</td>
									<td>
										x
									</td>
								</tr>
							</tbody>
						</table>
					</c:if>

					<aui:button cssClass="btn-secondary" onClick='<%= renderResponse.getNamespace() + "assignContacts();" %>' value="select" />
				</div>
			</div>

			<aui:input name="addEmailAddresses" type="hidden" />

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />assignContacts',
		function() {
			<portlet:renderURL var="assignTeamContactsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/accounts/assign_team_contacts" />
				<portlet:param name="teamKey" value="<%= (team != null) ? team.getKey() : "" %>" />
			</portlet:renderURL>

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />assignContacts',
				title: '<liferay-ui:message key="select-team-members" />',
				url: '<%= assignTeamContactsURL %>'
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var selectedItems = event.newVal;

				if (selectedItems) {
					Liferay.Util.postForm(
						document.<portlet:namespace />editTeamFm,
						{
							data: {
								addEmailAddresses: selectedItems
							}
						}
					);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);
</aui:script>