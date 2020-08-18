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
String productKey = ParamUtil.getString(renderRequest, "productKey");

EditProductPurchaseDisplayContext editProductPurchaseDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchaseDisplayContext(renderRequest, renderResponse, request);

SearchContainer productSearchContainer = editProductPurchaseDisplayContext.getProductPurchaseProductSearchContainer();
%>

<clay:management-toolbar
	clearResultsURL="<%= editProductPurchaseDisplayContext.getProductPurchaseProductClearResultsURL() %>"
	itemsTotal="<%= productSearchContainer.getTotal() %>"
	searchActionURL="<%= currentURL %>"
	searchContainerId="assignProduct"
	selectable="<%= false %>"
	showSearch="<%= true %>"
/>

<div class="container-fluid container-fluid-max-xl">
	<liferay-ui:search-container
		id="assignProduct"
		searchContainer="<%= productSearchContainer %>"
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

			if (productKey.equals(productDisplay.getKey())) {
				row.setCssClass("active");
			}
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

<aui:script>
	var searchContainer = document.getElementById('<portlet:namespace />assignProductSearchContainer');

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
				Liferay.Util.getOpener().Liferay.fire(
					'<portlet:namespace />assignProduct',
					{
						data: rowData
					}
				);
			}
		});
	}

	function resetRow(row) {
		row.classList.remove('active');
	}
</aui:script>