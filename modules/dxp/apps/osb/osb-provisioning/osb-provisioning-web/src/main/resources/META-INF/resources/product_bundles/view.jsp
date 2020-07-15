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

<div class="container-fluid-1280">
	<clay:management-toolbar
		creationMenu='<%=
			new JSPCreationMenu(pageContext) {
				{
					addDropdownItem(
						dropdownItem -> {
							dropdownItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/product_bundles/edit_product_bundle", "redirect", PortalUtil.getCurrentURL(request));
							dropdownItem.setLabel(LanguageUtil.get(request, "new-product-bundle"));
						});
				}
			}
		%>'
		selectable="<%= false %>"
		showSearch="<%= false %>"
	/>

	<liferay-ui:search-container
		emptyResultsMessage="no-product-bundles-were-found"
		headerNames="name"
		iteratorURL="<%= renderResponse.createRenderURL() %>"
		total="<%= ProductBundleLocalServiceUtil.getProductBundlesCount() %>"
	>
		<liferay-ui:search-container-results
			results="<%= ProductBundleLocalServiceUtil.getProductBundles(searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.provisioning.model.ProductBundle"
			escapedModel="<%= true %>"
			keyProperty="productBundleId"
			modelVar="productBundle"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/product_bundles/edit_product_bundle" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="productBundleId" value="<%= String.valueOf(productBundle.getProductBundleId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				value="<%= productBundle.getName() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/product_bundles/product_bundles_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>