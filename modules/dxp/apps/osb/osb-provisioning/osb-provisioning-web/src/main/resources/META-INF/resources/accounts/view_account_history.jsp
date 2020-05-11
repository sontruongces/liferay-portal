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

List<AuditEntryDisplay> auditEntryDisplays = viewAccountDisplayContext.getAuditEntryDisplays();

long auditSetId = 0;
String currentDate = StringPool.BLANK;

for (AuditEntryDisplay auditEntryDisplay : auditEntryDisplays) {
%>

	<c:if test="<%= !currentDate.equals(auditEntryDisplay.getDateCreated()) %>">
		<div class="list-group-header">
			<%= auditEntryDisplay.getDateCreated() %>
		</div>
	</c:if>

	<div class="details-table">
		<c:if test="<%= auditSetId != auditEntryDisplay.getAuditSetId() %>">
			<h2>
				<%= auditEntryDisplay.getAgentName() %> > <%= auditEntryDisplay.getSummary() %>

				<c:if test="<%= Validator.isNotNull(auditEntryDisplay.getDescription()) %>">
					> <%= auditEntryDisplay.getDescription() %>
				</c:if>

				<%= auditEntryDisplay.getTimeCreated() %>
			</h2>

			<aui:row>
				<aui:col width="<%= 14 %>">
					<liferay-ui:message key="field" />
				</aui:col>

				<aui:col width="<%= 33 %>">
					<liferay-ui:message key="original-value" />
				</aui:col>

				<aui:col width="<%= 33 %>">
					<liferay-ui:message key="new-value" />
				</aui:col>
			</aui:row>
		</c:if>

		<aui:row>
			<aui:col width="<%= 14 %>">
				<strong><%= auditEntryDisplay.getField() %></strong>
			</aui:col>

			<aui:col width="<%= 33 %>">
				<%= auditEntryDisplay.getOldValue() %>
			</aui:col>

			<aui:col width="<%= 33 %>">
				<%= auditEntryDisplay.getNewValue() %>
			</aui:col>
		</aui:row>
	</div>

<%
	auditSetId = auditEntryDisplay.getAuditSetId();
	currentDate = auditEntryDisplay.getDateCreated();
}
%>