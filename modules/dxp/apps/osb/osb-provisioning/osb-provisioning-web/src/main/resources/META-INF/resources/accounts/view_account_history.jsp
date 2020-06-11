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
			<span class="list-group-header-title"><%= auditEntryDisplay.getDateCreated() %></span>
		</div>
	</c:if>

	<c:if test="<%= auditSetId != auditEntryDisplay.getAuditSetId() %>">
		<aui:row cssClass="detail-title">
			<aui:col cssClass="description" width="<%= 80 %>">
				<c:if test="<%= Validator.isNotNull(auditEntryDisplay.getAgentPortraitURL()) %>">
					<span class="sticker sticker-circle sticker-secondary sticker-sm">
						<span class="sticker-overlay">
							<img alt="<%= LanguageUtil.get(request, "agent-avatar") %>" class="sticker-img" src="<%= auditEntryDisplay.getAgentPortraitURL() %>" />
						</span>
					</span>
				</c:if>

				<%= auditEntryDisplay.getAgentName() %> > <%= auditEntryDisplay.getSummary() %>

				<c:if test="<%= Validator.isNotNull(auditEntryDisplay.getDescription()) %>">
					> <%= auditEntryDisplay.getDescription() %>
				</c:if>
			</aui:col>

			<aui:col cssClass="timestamp" width="<%= 20 %>">
				<%= auditEntryDisplay.getTimeCreated() %>
			</aui:col>
		</aui:row>

		<aui:row cssClass="detail-label">
			<aui:col width="<%= 20 %>">
				<span class="list-group-header-title"><liferay-ui:message key="field" /></span>
			</aui:col>

			<aui:col width="<%= 40 %>">
				<span class="list-group-header-title"><liferay-ui:message key="original-value" /></span>
			</aui:col>

			<aui:col width="<%= 40 %>">
				<span class="list-group-header-title"><liferay-ui:message key="new-value" /></span>
			</aui:col>
		</aui:row>
	</c:if>

	<aui:row>
		<aui:col cssClass="col-field" width="<%= 20 %>">
			<%= auditEntryDisplay.getField() %>
		</aui:col>

		<aui:col width="<%= 40 %>">
			<%= auditEntryDisplay.getOldValue() %>
		</aui:col>

		<aui:col width="<%= 40 %>">
			<%= auditEntryDisplay.getNewValue() %>
		</aui:col>
	</aui:row>

<%
	auditSetId = auditEntryDisplay.getAuditSetId();
	currentDate = auditEntryDisplay.getDateCreated();
}
%>