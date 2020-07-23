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
AssignProductBundleProductsDisplayContext assignProductBundleProductsDisplayContext = ProvisioningWebComponentProvider.getAssignProductBundleProductsDisplayContext(renderRequest, renderResponse, request);

String[] productKeys = ParamUtil.getStringValues(renderRequest, "productKeys");

SearchContainer searchContainer = assignProductBundleProductsDisplayContext.getSearchContainer(productKeys);
%>

<clay:management-toolbar
	clearResultsURL="<%= assignProductBundleProductsDisplayContext.getClearResultsURL() %>"
	itemsTotal="<%= searchContainer.getTotal() %>"
	searchActionURL="<%= assignProductBundleProductsDisplayContext.getCurrentURL() %>"
	searchContainerId="assignProducts"
	selectable="<%= true %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		id="assignProducts"
		searchContainer="<%= searchContainer %>"
		var="productsSearchContainer"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay"
			escapedModel="<%= true %>"
			keyProperty="key"
			modelVar="productDisplay"
		>

			<%
			Map<String, Object> productData = new HashMap<String, Object>();

			productData.put("key", productDisplay.getKey());
			productData.put("name", productDisplay.getName());

			row.setData(productData);
			%>

			<liferay-ui:search-container-column-text
				name="products"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get(
		'<portlet:namespace />assignProducts'
	);

	searchContainer.on('rowToggled', function(event) {
		var selectedItems = event.elements.allSelectedElements;
		var data = [];

		if (selectedItems.size() > 0) {
			selectedItems.each(function() {
				var row = this.ancestor('tr');
				var rowData = row.getDOM().dataset;

				data.push([rowData.key, rowData.name]);
			});
		}

		Liferay.Util.getOpener().Liferay.fire(
			'<portlet:namespace />assignProducts',
			{
				data: data
			}
		);
	});
</aui:script>