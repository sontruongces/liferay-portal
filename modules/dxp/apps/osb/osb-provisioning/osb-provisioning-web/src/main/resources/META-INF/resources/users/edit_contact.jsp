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
Contact curContact = (Contact)renderRequest.getAttribute(ProvisioningWebKeys.CONTACT);
%>

<portlet:actionURL name="/users/edit_contact" var="editContactURL">
	<portlet:param name="mvcRenderCommandName" value="/users/view_contact" />
	<portlet:param name="tabs1" value="general" />
	<portlet:param name="contactEmailAddress" value="<%= curContact.getEmailAddress() %>" />
</portlet:actionURL>

<liferay-ui:error exception="<%= HttpException.class %>">

	<%
	HttpException httpException = (HttpException)errorException;
	%>

	<%= httpException.getMessage() %>
</liferay-ui:error>

<aui:form action="<%= editContactURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="key" type="resource" value="<%= curContact.getKey() %>" />

			<aui:input name="emailAddress" type="resource" value="<%= curContact.getEmailAddress() %>" />

			<aui:input name="languageId" type="resource" value="<%= curContact.getLanguageId() %>" />

			<aui:input name="uuid" value="<%= curContact.getUuid() %>" />

			<aui:input name="oktaId" value="<%= curContact.getOktaId() %>" />

			<aui:input name="firstName" value="<%= curContact.getFirstName() %>" />

			<aui:input name="middleName" value="<%= curContact.getMiddleName() %>" />

			<aui:input name="lastName" value="<%= curContact.getLastName() %>" />

			<aui:input checked="<%= curContact.getEmailAddressVerified() %>" name="emailAddressVerified" type="checkbox" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= currentURL %>" type="cancel" />
	</aui:button-row>
</aui:form>