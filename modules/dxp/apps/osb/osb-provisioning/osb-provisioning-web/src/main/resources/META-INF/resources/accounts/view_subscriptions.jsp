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
ProductPurchaseViewSearchDisplayContext productPurchaseViewSearchDisplayContext = ProvisioningWebComponentProvider.getProductPurchaseViewSearchDisplayContext(renderRequest, renderResponse);
ViewAccountDisplayContext viewAccountDisplayContext = (ViewAccountDisplayContext)request.getAttribute("view_account.jsp-viewAccountDisplayContext");

Account koroneikiAccount = viewAccountDisplayContext.getAccount();
%>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= productPurchaseViewSearchDisplayContext.getSearchContainer(koroneikiAccount.getKey()) %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView"
			escapedModel="<%= true %>"
			keyProperty="key"
			modelVar="productPurchaseView"
		>

			<%
			Subscription subscription = new Subscription(productPurchaseView);

			Product product = subscription.getProduct();
			%>

			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_product_subscriptions" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKy" value="<%= koroneikiAccount.getKey() %>" />
				<portlet:param name="productKey" value="<%= product.getKey() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
			>
				<%= product.getName() %>

				<br />

				<c:if test="<%= subscription.getSizing() > 0 %>">
					<liferay-ui:message key="instance-size" />: <%= subscription.getSizing() %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-life"
			>
				<c:choose>
					<c:when test="<%= subscription.getStartDate() != null %>">
						<%= dateFormat.format(subscription.getStartDate()) + " - " + dateFormat.format(subscription.getEndDate()) %>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="perpetual" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="provisioned"
				value="<%= String.valueOf(subscription.getProvisionedCount()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="purchased"
				value="<%= String.valueOf(subscription.getPurchasedCount()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
			>
				<span class="label label-sm label-<%= StringUtil.lowerCase(subscription.getStatus()) %>"><%= StringUtil.lowerCase(subscription.getStatus()) %></span>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>