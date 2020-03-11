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
long accountId = ParamUtil.getLong(request, "accountId");
long productEntryId = ParamUtil.getLong(request, "productEntryId");
long productPurchaseId = ParamUtil.getLong(request, "productPurchaseId");
%>

<div class="container-fluid-1280">
	<aui:form cssClass="container-fluid-1280" name="selectProductPurchaseFm">
		<liferay-ui:search-container
			emptyResultsMessage="no-product-purchases-were-found"
			total="<%= ProductPurchaseLocalServiceUtil.getAccountProductEntryProductPurchasesCount(accountId, productEntryId) %>"
		>
			<liferay-ui:search-container-results
				results="<%= ProductPurchaseLocalServiceUtil.getAccountProductEntryProductPurchases(accountId, productEntryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.koroneiki.trunk.model.ProductPurchase"
				escapedModel="<%= true %>"
				keyProperty="productPurchaseId"
				modelVar="productPurchase"
			>

				<%
				Account koroneikiAccount = productPurchase.getAccount();
				ProductEntry productEntry = productPurchase.getProductEntry();
				%>

				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/products_admin/edit_product_purchase" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="productPurchaseId" value="<%= String.valueOf(productPurchase.getProductPurchaseId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="account"
				>
					<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
						<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
					</span>

					<%= HtmlUtil.escape(koroneikiAccount.getName()) %>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="product"
					value="<%= HtmlUtil.escape(productEntry.getName()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="start-date"
				>
					<c:if test="<%= productPurchase.getStartDate() != null %>">
						<%= mediumDateFormatDate.format(productPurchase.getStartDate()) %>
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="end-date"
				>
					<c:if test="<%= productPurchase.getEndDate() != null %>">
						<%= mediumDateFormatDate.format(productPurchase.getEndDate()) %>
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="quantity"
					value="<%= String.valueOf(productPurchase.getQuantity()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="status"
				>
					<span class="label label-sm label-<%= StringUtil.lowerCase(productPurchase.getStatusLabel()) %>"><%= StringUtil.lowerCase(productPurchase.getStatusLabel()) %></span>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text>
					<c:if test="<%= productPurchaseId != productPurchase.getProductPurchaseId() %>">

						<%
						Map<String, Object> data = new HashMap<String, Object>();

						data.put("productpurchaseid", productPurchase.getProductPurchaseId());
						data.put("productpurchasekey", productPurchase.getProductPurchaseKey());
						%>

						<aui:button cssClass="selector-button" data="<%= data %>" value="select" />
					</c:if>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectProductPurchaseFm', 'selectProductPurchase');
</aui:script>