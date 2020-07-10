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
ProductBundle productBundle = (ProductBundle)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE);
List<Product> products = (List<Product>)renderRequest.getAttribute(ProvisioningWebKeys.PRODUCT_BUNDLE_PRODUCTS);

String productKeys = null;

if (products != null) {
	List<String> allProductKeys = TransformUtil.transform(products, product -> product.getKey());

	productKeys = StringUtil.merge(allProductKeys, ",");
}
%>

<portlet:actionURL name="/product_bundles/edit_product_bundle" var="editProductBundleURL">
	<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="productBundleId" value="<%= (productBundle != null) ? String.valueOf(productBundle.getProductBundleId()) : null %>" />
</portlet:actionURL>

<aui:form action="<%= editProductBundleURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="productKeys" type="hidden" value="<%= productKeys %>" />

	<liferay-ui:error exception="<%= ProductBundleNameException.MustNotBeDuplicate.class %>">

		<%
		ProductBundleNameException.MustNotBeDuplicate productBundleNameException = (ProductBundleNameException.MustNotBeDuplicate)errorException;
		%>

		<%= productBundleNameException.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-select-at-least-one-product" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="name" value='<%= (productBundle != null) ? productBundle.getName() : "" %>'>
				<aui:validator name="required" />
			</aui:input>

			<h5><liferay-ui:message key="products" /></h5>

			<div id="<portlet:namespace />productName">

				<%
				if (products != null) {
					for (Product product : products) {
				%>

						<div id="<%= product.getKey() %>"><%= product.getName() %> <button type="button" class="btn" onclick="removeName(this)">remove</button><br /></div>

				<%
					}
				}
				%>

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
					var productKeys = [];

					A.one('#<portlet:namespace />productName').html('');

					for (var i = 0; i < selectedItems.length; i++) {
						var selectItem = selectedItems[i].split(' ');

						productKeys.push(selectItem[0]);

						A.one('#<portlet:namespace />productName').append(
							'<div id="' +
								selectItem[0] +
								'">' +
								selectItem[1] +
								' <button type="button" class="btn" onclick="removeName(this)">remove</button><br /></div>'
						);
					}

					A.one('#<portlet:namespace />productKeys').val(
						productKeys.join(',')
					);
				}
			});

			itemSelectorDialog.open();
		},
		['aui-base', 'liferay-item-selector-dialog']
	);
</aui:script>

<aui:script>
	function removeKey(key, value) {
		return key !== value;
	}

	function removeName(Object) {
		var productKeys = document.getElementById(
			'<portlet:namespace />productKeys'
		);

		if (productKeys) {
			productKeys.value = productKeys.value
				.split(',')
				.filter(removeKey.bind(this, Object.parentElement.id))
				.join(',');
		}

		Object.parentElement.remove();
	}
</aui:script>