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

PortletURL searchURL = viewAccountDisplayContext.getPortletURL();
%>

<liferay-ui:tabs
	cssClass="related-accounts-tabs"
	names='<%= "all," + Account.Status.APPROVED.toString() + "," + Account.Status.CLOSED.toString() %>'
	param="tabs2"
	portletURL="<%= viewAccountDisplayContext.getPortletURL() %>"
>
	<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
		<liferay-portlet:renderURLParams portletURL="<%= searchURL %>" />

		<aui:input label="" name="keywords" placeholder="search" />
	</aui:form>

	<table>
		<tr>
			<th>
				<liferay-ui:message key="name-code" />
			</th>
			<th>
				<liferay-ui:message key="support-end-date" />
			</th>
			<th>
				<liferay-ui:message key="partner" />
			</th>
			<th>
				<liferay-ui:message key="region" />
			</th>
			<th>
				<liferay-ui:message key="sla-tier" />
			</th>
			<th>
				<liferay-ui:message key="status" />
			</th>
		</tr>

		<%
		Map<String, List<AccountDisplay>> accountDisplayMap = viewAccountDisplayContext.getRelatedAccountDisplaysMap();

		for (Map.Entry<String, List<AccountDisplay>> entry : accountDisplayMap.entrySet()) {
			List<AccountDisplay> accountDisplays = entry.getValue();

			if (accountDisplays.isEmpty()) {
				continue;
			}
		%>

			<tr>
				<td colspan="6">
					<div class="list-group-header">
						<liferay-ui:message key="<%= entry.getKey() %>" />
					</div>
				</td>
			</tr>

			<%
			for (AccountDisplay accountDisplay : accountDisplays) {
			%>

				<tr>
					<td>
						<%= accountDisplay.getName() %>

						<div class="secondary-information">
							<%= accountDisplay.getCode() %>
						</div>
					</td>
					<td>
						<%= accountDisplay.getSupportEndDate() %>
					</td>
					<td>
						<%= HtmlUtil.escape(accountDisplay.getPartnerTeamName()) %>
					</td>
					<td>
						<%= accountDisplay.getRegion() %>
					</td>
					<td>
						<%= HtmlUtil.escape(accountDisplay.getSLAName()) %>

						<div class="secondary-information">
							<%= accountDisplay.getTier() %>
						</div>
					</td>
					<td>
						<span class="label <%= accountDisplay.getStatusStyle() %>"><%= accountDisplay.getStatus() %></span>
					</td>
				</tr>

		<%
			}
		}
		%>

	</table>
</liferay-ui:tabs>