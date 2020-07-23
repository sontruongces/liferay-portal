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
String redirect = ParamUtil.getString(request, "redirect");
%>

<portlet:actionURL name="/accounts/edit_account" var="addAccountURL" />

<aui:form action="<%= addAccountURL %>" cssClass="container-fluid container-fluid-max-xl" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="name" />

			<aui:input name="code" />

			<aui:select name="tier">
				<aui:option value="" />

				<%
				for (Account.Tier tier : Account.Tier.values()) {
				%>

					<aui:option label="<%= tier %>" value="<%= tier %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="region">
				<aui:option value="" />

				<%
				for (Account.Region region : Account.Region.values()) {
				%>

					<aui:option label="<%= region %>" value="<%= region %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select name="status">
				<aui:option value="" />

				<%
				for (Account.Status status : Account.Status.values()) {
				%>

					<aui:option label="<%= status %>" value="<%= status %>" />

				<%
				}
				%>

			</aui:select>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>