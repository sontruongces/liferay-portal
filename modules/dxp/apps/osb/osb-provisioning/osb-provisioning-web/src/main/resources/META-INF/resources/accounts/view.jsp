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
AccountSearchDisplayContext accountSearchDisplayContext = ProvisioningWebComponentProvider.getAccountSearchDisplayContext(renderRequest, renderResponse);
%>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		searchContainer="<%= accountSearchDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account"
			escapedModel="<%= true %>"
			keyProperty="key"
			modelVar="koroneikiAccount"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/accounts/view_account" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="accountKey" value="<%= koroneikiAccount.getKey() %>" />
			</portlet:renderURL>

			<%
			Team partnerTeam = accountReader.getPartnerTeam(koroneikiAccount);
			ProductPurchase slaProductPurchase = accountReader.getSLAProductPurchase(koroneikiAccount);
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
			>
				<span class="lfr-portal-tooltip" data-title="<liferay-ui:message key="account" />">
					<aui:icon cssClass="icon-monospaced" image="users" markupView="lexicon" />
				</span>

				<%= koroneikiAccount.getName() %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="code"
				value="<%= koroneikiAccount.getCode() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="partner"
			>
				<c:if test="<%= partnerTeam != null %>">
					<%= HtmlUtil.escape(partnerTeam.getName()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="support-end-date"
			>
				<c:if test="<%= slaProductPurchase != null %>">
					<c:choose>
						<c:when test="<%= slaProductPurchase.getPerpetual() %>">
							<liferay-ui:message key="perpetual" />
						</c:when>
						<c:otherwise>
							<%= dateFormat.format(slaProductPurchase.getEndDate()) %>
						</c:otherwise>
					</c:choose>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="licenses"
				value=""
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="sla"
			>
				<c:if test="<%= slaProductPurchase != null %>">

					<%
					Product product = slaProductPurchase.getProduct();

					String name = StringUtil.replace(product.getName(), " Subscription", StringPool.BLANK);
					%>

					<%= HtmlUtil.escape(name) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="tier"
				value="<%= koroneikiAccount.getTierAsString() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="region"
				value=""
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="status"
				value="<%= koroneikiAccount.getStatusAsString() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>