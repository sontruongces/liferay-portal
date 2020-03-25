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
	<span><liferay-ui:message key="account-name" /></span>

	<span><%= accountDisplay.getName() %></span>
</div>

<div>
	<span><liferay-ui:message key="status" /></span>

	<span><%= accountDisplay.getStatus() %></span>
</div>

<div>
	<span><liferay-ui:message key="code" /></span>

	<span><%= accountDisplay.getCode() %></span>
</div>

<div>
	<span><liferay-ui:message key="created" /></span>

	<span><%= accountDisplay.getDateCreated() %></span>
</div>

<div>
	<span><liferay-ui:message key="last-modified" /></span>

	<span><%= accountDisplay.getDateModified() %></span>
</div>

<div>
	<span><liferay-ui:message key="tier" /></span>

	<span><%= accountDisplay.getTier() %></span>
</div>

<div>
	<span><liferay-ui:message key="partner-reseller-si" /></span>

	<span><%= accountDisplay.getPartnerTeamName() %></span>
</div>

<div>
	<span><liferay-ui:message key="first-line-support" /></span>

	<span><%= accountDisplay.getFirstLineSupportTeamName() %></span>
</div>

<%
List<PostalAddressDisplay> postalAddressDisplays = accountDisplay.getPostalAddressDisplays();

for (int i = 0; i < postalAddressDisplays.size(); i++) {
	PostalAddressDisplay postalAddressDisplay = postalAddressDisplays.get(i);
%>

	<div>
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
	<span><liferay-ui:message key="dossiera-account" /></span>

	<span><%= accountDisplay.getDossieraAccountKey() %></span>
</div>

<div>
	<span><liferay-ui:message key="dossiera-project" /></span>

	<span><%= accountDisplay.getDossieraProjectKey() %></span>
</div>

<div>
	<span><liferay-ui:message key="salesforce-project" /></span>

	<span><%= accountDisplay.getSalesforceProjectKey() %></span>
</div>