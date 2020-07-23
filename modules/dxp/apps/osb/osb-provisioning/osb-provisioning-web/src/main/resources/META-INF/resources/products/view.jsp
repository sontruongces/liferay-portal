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
ProductSearchDisplayContext productSearchDisplayContext = ProvisioningWebComponentProvider.getProductSearchDisplayContext(renderRequest, renderResponse, request);

ViewProductsManagementToolbarDisplayContext viewProductsManagementToolbarDisplayContext = new ViewProductsManagementToolbarDisplayContext(liferayPortletRequest, liferayPortletResponse, request, productSearchDisplayContext.getSearchContainer());
%>

<div class="container-fluid container-fluid-max-xl">
	<portlet:actionURL name="/search" var="searchURL" />

	<liferay-ui:error key="<%= RequiredProductException.class.getName() %>" message="please-remove-the-product-from-all-product-bundles-before-deleting" />

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<clay:management-toolbar
		displayContext="<%= viewProductsManagementToolbarDisplayContext %>"
	/>

	<liferay-ui:search-container
		cssClass=""
		searchContainer="<%= productSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.web.internal.display.context.ProductDisplay"
			escapedModel="<%= true %>"
			keyProperty="productKey"
			modelVar="productDisplay"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/products/edit_product" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productKey" value="<%= productDisplay.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= productDisplay.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="type"
				value="<%= productDisplay.getType() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/products/product_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>