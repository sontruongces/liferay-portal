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
ViewAccountDisplayContext viewAccountDisplayContext = ProvisioningWebComponentProvider.getViewAccountDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountDisplayContext.getAccountDisplay();
%>

<div>
	<%= viewAccountDisplayContext.getEditAccountURL() %>
</div>

<div>
	<label><liferay-ui:message key="account-name" /></label>

	<span><%= accountDisplay.getName() %></span>
</div>

<div>
	<label><liferay-ui:message key="status" /></label>

	<span><%= accountDisplay.getStatus() %></span>

	<aui:select name="status">
		<aui:option value="" />

		<%
		for (Account.Status status : Account.Status.values()) {
		%>

			<aui:option label="<%= status.toString() %>" value="<%= status.toString() %>" />

		<%
		}
		%>

	</aui:select>
</div>

<div>
	<label><liferay-ui:message key="code" /></label>

	<span><%= accountDisplay.getCode() %></span>
</div>

<div>
	<label><liferay-ui:message key="created" /></label>

	<span><%= accountDisplay.getDateCreated() %></span>
</div>

<div>
	<label><liferay-ui:message key="last-modified" /></label>

	<span><%= accountDisplay.getDateModified() %></span>
</div>

<div>
	<label><liferay-ui:message key="tier" /></label>

	<span><%= accountDisplay.getTier() %></span>

	<aui:select name="tier">
		<aui:option value="" />

		<%
		for (Account.Tier tier : Account.Tier.values()) {
		%>

			<aui:option label="<%= tier.toString() %>" value="<%= tier.toString() %>" />

		<%
		}
		%>

	</aui:select>
</div>

<div>
	<label><liferay-ui:message key="region" /></label>

	<span><%= accountDisplay.getRegion() %></span>

	<aui:select name="region">
		<aui:option value="" />

		<%
		for (Account.Region region : Account.Region.values()) {
		%>

			<aui:option label="<%= region.toString() %>" value="<%= region.toString() %>" />

		<%
		}
		%>

	</aui:select>
</div>

<div>
	<label><liferay-ui:message key="partner-reseller-si" /></label>

	<span><%= accountDisplay.getPartnerTeamName() %></span>
</div>

<div>
	<label><liferay-ui:message key="first-line-support" /></label>

	<span><%= accountDisplay.getFirstLineSupportTeamName() %></span>
</div>

<div>
	<%= viewAccountDisplayContext.getAddPostalAddressURL() %>
</div>

<%
List<PostalAddressDisplay> postalAddressDisplays = accountDisplay.getPostalAddressDisplays();

for (int i = 0; i < postalAddressDisplays.size(); i++) {
	PostalAddressDisplay postalAddressDisplay = postalAddressDisplays.get(i);
%>

	<div>
		<div>
			<%= viewAccountDisplayContext.getEditPostalAddressURL(postalAddressDisplay.getId()) %>
		</div>

		<div>
			<%= viewAccountDisplayContext.getDeletePostalAddressURL(postalAddressDisplay.getId()) %>
		</div>

		<div>
			<liferay-ui:message key="address" /> <%= i + 1 %>
		</div>

		<div>
			<liferay-ui:message key="street-1" /> <%= postalAddressDisplay.getStreetAddressLine1() %>
		</div>

		<div>
			<liferay-ui:message key="street-2" /> <%= postalAddressDisplay.getStreetAddressLine2() %>
		</div>

		<div>
			<liferay-ui:message key="street-3" /> <%= postalAddressDisplay.getStreetAddressLine3() %>
		</div>

		<div>
			<liferay-ui:message key="country" /> <%= postalAddressDisplay.getAddressCountry() %>
		</div>

		<div>
			<liferay-ui:message key="city" /> <%= postalAddressDisplay.getAddressLocality() %>
		</div>

		<div>
			<liferay-ui:message key="state-province" /> <%= postalAddressDisplay.getAddressRegion() %>
		</div>

		<div>
			<liferay-ui:message key="postal-code" /> <%= postalAddressDisplay.getPostalCode() %>
		</div>

		<div>
			<liferay-ui:message key="type" /> <%= postalAddressDisplay.getAddressType() %>
		</div>

		<div>
			<liferay-ui:message key="primary" /> <%= postalAddressDisplay.getPrimary() %>
		</div>
	</div>

<%
}
%>

<div>
	<label><liferay-ui:message key="dossiera-account" /></label>

	<span><%= accountDisplay.getDossieraAccountKey() %></span>
</div>

<div>
	<label><liferay-ui:message key="dossiera-project" /></label>

	<span><%= accountDisplay.getDossieraProjectKey() %></span>
</div>

<div>
	<label><liferay-ui:message key="salesforce-project" /></label>

	<span><%= accountDisplay.getSalesforceProjectKey() %></span>
</div>