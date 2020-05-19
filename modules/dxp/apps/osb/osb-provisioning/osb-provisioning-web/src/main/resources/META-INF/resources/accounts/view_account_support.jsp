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

AccountEntry accountEntry = viewAccountDisplayContext.getAccountEntry();
%>

<div class="details-table">
	<aui:row>
		<aui:col width="<%= 40 %>">
			<div class="sheet">
				<div class="sheet-title">
					<liferay-ui:message key="details" />
				</div>

				<div class="sheet-section">
					<aui:row>
						<aui:col width="<%= 33 %>">
							<liferay-ui:message key="region" />
						</aui:col>

						<aui:col width="<%= 66 %>">
							<%= accountDisplay.getRegion() %>
						</aui:col>
					</aui:row>
				</div>

				<div class="sheet-section">
					<aui:row>
						<aui:col width="<%= 33 %>">
							<liferay-ui:message key="language" />
						</aui:col>

						<aui:col width="<%= 66 %>">
							<c:if test="<%= accountEntry != null %>">
								<%= accountEntry.getLanguageId() %>

								<%= viewAccountDisplayContext.getUpdateLanguageIdURL() %>
							</c:if>
						</aui:col>
					</aui:row>
				</div>
			</div>

			<div class="sheet">
				<div class="sheet-title">
					<liferay-ui:message key="oem-instructions" />

					<div class="sheet-section">
						<c:if test="<%= accountEntry != null %>">
							<c:if test="<%= accountEntry.getOEMInstructionsAccountAttachmentId() > 0 %>">
								<a href="<%= viewAccountDisplayContext.getAccountAttachmentURL(accountEntry.getOEMInstructionsAccountAttachmentId()) %>" target="_blank"><%= accountEntry.getOEMInstructionsFileName() %></a>
							</c:if>

							<input name="oemInstructions" type="file" />

							<%= viewAccountDisplayContext.getUpdateAccountAttachmentURL() %>
						</c:if>
					</div>
				</div>
			</div>
		</aui:col>

		<aui:col width="<%= 60 %>">
			<div class="sheet">
				<div class="sheet-title">
					<liferay-ui:message key="support-instructions" />
				</div>

				<div class="sheet-section">
					<c:if test="<%= accountEntry != null %>">
						<%= accountEntry.getInstructions() %>

						<%= viewAccountDisplayContext.getUpdateInstructionsURL() %>
					</c:if>
				</div>
			</div>
		</aui:col>
	</aui:row>
</div>