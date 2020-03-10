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
ViewAccountDisplayContext viewAccountDisplayContext = (ViewAccountDisplayContext)request.getAttribute("view_account.jsp-viewAccountDisplayContext");

Account koroneikiAccount = viewAccountDisplayContext.getAccount();
%>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= viewAccountDisplayContext.getProductPurchaseViewSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView"
			escapedModel="<%= true %>"
			keyProperty="key"
			modelVar="productPurchaseView"
		>

			<%
			ProductSubscriptionDisplay productSubscriptionDisplay = new ProductSubscriptionDisplay(request, productPurchaseView);
			%>

			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_product_subscription" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= koroneikiAccount.getKey() %>" />
				<portlet:param name="productKey" value="<%= productSubscriptionDisplay.getProductKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
			>
				<%= productSubscriptionDisplay.getName() %>

				<br />

				<%= productSubscriptionDisplay.getSizing() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-life"
				value="<%= productSubscriptionDisplay.getSupportLife() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="provisioned"
				value="<%= productSubscriptionDisplay.getProvisionedCount() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="purchased"
				value="<%= productSubscriptionDisplay.getPurchasedCount() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label label-sm <%= productSubscriptionDisplay.getStatusStyle() %>"><%= productSubscriptionDisplay.getStatus() %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>