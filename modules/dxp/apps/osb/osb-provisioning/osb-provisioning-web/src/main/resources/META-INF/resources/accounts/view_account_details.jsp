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

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<div class="account-details details-table" id="accountDetails">
	<react:component
		data="<%= viewAccountDisplayContext.getAccountDetailsData() %>"
		module="js/AccountDetailsApp"
	/>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />editAccountHierarchy',
		function() {
			<portlet:renderURL var="editAccountHierarchyURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/accounts/edit_account_hierarchy" />
				<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
			</portlet:renderURL>

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />editAccountHierarchy',
				title: '<liferay-ui:message key="select-parent-account" />',
				url: '<%= editAccountHierarchyURL %>'
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var parentAccountKey = event.newVal;

				if (selectedItems) {
					Liferay.Util.postForm(
						document.<portlet:namespace />editAccountHierarchyFm,
						{
							data: {
								parentAccountKey: parentAccountKey
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