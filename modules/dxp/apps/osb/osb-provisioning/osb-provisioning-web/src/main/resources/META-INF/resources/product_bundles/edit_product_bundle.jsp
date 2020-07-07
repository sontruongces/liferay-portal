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
%>

<portlet:actionURL name="/product_bundles/edit_product_bundle" var="editProductBundleURL">
	<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="productBundleId" value="" />
</portlet:actionURL>

<aui:form action="<%= editProductBundleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="productKey" type="hidden" />

	<liferay-ui:error exception="<%= ProductBundleNameException.MustNotBeDuplicate.class %>">

		<%
		ProductBundleNameException.MustNotBeDuplicate productBundleNameException = (ProductBundleNameException.MustNotBeDuplicate)errorException;
		%>

		<%= productBundleNameException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-select-at-least-one-product" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="name" value="">
				<aui:validator name="required" />
			</aui:input>

			<h5><liferay-ui:message key="products" /></h5>

			<div id="<portlet:namespace />productName">
			</div>

			<aui:button onClick='<%= renderResponse.getNamespace() + "assignProducts();" %>' value="select" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />assignProducts',
		function() {
			<portlet:renderURL var="assignProductsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcRenderCommandName" value="/product_bundles/assign_products" />
				<portlet:param name="productBundleId" value="" />
			</portlet:renderURL>

			var A = AUI();

			var itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: '<portlet:namespace />assignProducts',
				title: '<liferay-ui:message key="select-products" />',
				url: '<%= assignProductsURL %>'
			});

			itemSelectorDialog.on('selectedItemChange', function(event) {
				var selectedItems = event.newVal;

				if (selectedItems) {
					var productKey = [];
					var productName = [];
					var display = '<p>';

					for (var i = 0; i < selectedItems.length; i++) {
						var selectItem = selectedItems[i].split(' ');

						productKey.push(selectItem[0]);
						productName.push(selectItem[1]);

						display += selectItem[1] + '<br />';
					}

					display += '</p>';

					A.one('#<portlet:namespace />productName').html(display);
					A.one('#<portlet:namespace />productKey').val(
						productKey.join(',')
					);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);
</aui:script>