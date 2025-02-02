<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/com.liferay.login.web/init.jsp" %>

<portlet:actionURL var="openIdConnectURL">
	<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="<%= OpenIdConnectWebKeys.OPEN_ID_CONNECT_REQUEST_ACTION_NAME %>" />
</portlet:actionURL>

<aui:form action="<%= openIdConnectURL %>" method="post" name="fm">
	<aui:input name="saveLastPath" type="hidden" value="<%= false %>" />
	<aui:input name="redirect" type="hidden" value='<%= ParamUtil.getString(renderRequest, "redirect") %>' />

	<liferay-ui:error key="MustNotUseCompanyMx" message="the-email-address-associated-with-your-openid-connect-account-cannot-be-used-to-register-a-new-user-because-its-email-domain-is-reserved" />
	<liferay-ui:error key="StrangersNotAllowedException" message="only-known-users-are-allowed-to-sign-in-using-openid-connect" />

	<aui:select label="openid-connect-client-name" name="oAuthClientEntryId">

		<%
		List<OAuthClientEntry> oAuthClientEntries = (List<OAuthClientEntry>)request.getAttribute(OpenIdConnectWebKeys.OAUTH_CLIENT_ENTRIES);

		for (OAuthClientEntry oAuthClientEntry : oAuthClientEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(oAuthClientEntry.getInfoJSON());

			String clientName = jsonObject.getString("client_name", null);

			if (clientName == null) {
				clientName = oAuthClientEntry.getClientId() + " at Provider: " + oAuthClientEntry.getAuthServerWellKnownURI();
			}
		%>

			<aui:option label="<%= HtmlUtil.escape(clientName) %>" value="<%= oAuthClientEntry.getOAuthClientEntryId() %>" />

		<%
		}
		%>

	</aui:select>

	<liferay-ui:error exception="<%= OpenIdConnectServiceException.AuthenticationException.class %>" message="an-error-occurred-while-attempting-to-authenticate-with-the-openid-connect-provider" />
	<liferay-ui:error exception="<%= OpenIdConnectServiceException.ProviderException.class %>" message="an-error-occurred-while-attempting-to-communicate-with-the-openid-connect-provider" />
	<liferay-ui:error exception="<%= OpenIdConnectServiceException.TokenException.class %>" message="an-error-occurred-while-parsing-the-token-from-the-openid-connect-provider" />
	<liferay-ui:error exception="<%= OpenIdConnectServiceException.UserInfoException.class %>" message="an-error-occurred-while-retrieving-user-info-from-the-openid-connect-provider" />
	<liferay-ui:error exception="<%= OpenIdConnectServiceException.UserMappingException.class %>" message="unable-to-obtain-user-info-from-the-openid-connect-provider" />

	<aui:fieldset>
		<aui:button-row>
			<aui:button type="submit" value="sign-in" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>