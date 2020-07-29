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

ProductPurchaseDisplayContext productPurchaseDisplayContext = new ProductPurchaseDisplayContext(renderRequest, renderResponse, request);

ProductPurchaseDisplay productPurchaseDisplay = productPurchaseDisplayContext.getProductPurchaseDisplay();

Calendar cal = CalendarFactoryUtil.getCalendar(timeZone, locale);
%>

<div class="account-add-items nav">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="edit-subscription"
	/>

	<portlet:actionURL name="/accounts/edit_product_purchase" var="editSubscriptionURL">
		<portlet:param name="mvcRenderCommandName" value="/accounts/edit_product_purchase" />
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="productPurchaseKey" value="<%= productPurchaseDisplay.getKey() %>" />
	</portlet:actionURL>

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<aui:form action="<%= editSubscriptionURL.toString() %>" method="post" name="fm">
		<div>
			<table class="table table-autofit table-list table-nowrap">
				<thead>
					<tr>
						<th class="table-cell-expand">
							<liferay-ui:message key="product" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="purchased" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="perpetual" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="start-date" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="end-date" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="instance-size" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="grace-period-end-date" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="status" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="subscription-term" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="salesforce-opportunity-key" />
						</th>
						<th class="table-cell-expand">
							<liferay-ui:message key="account-name" />
						</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td class="table-cell-expand">
							<%= productPurchaseDisplay.getProductName() %>
						</td>
						<td class="table-cell-expand">
							<aui:input cssClass="account-edit-subscription" label="" name="purchased" value="<%= productPurchaseDisplay.getQuantity() %>" />
						</td>

						<%
						String taglibOnClick = renderResponse.getNamespace() + "toggleDate(this.checked, 'startDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'endDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'gracePeriodEndDate');";
						%>

						<td class="table-cell-expand">
							<aui:input checked="<%= productPurchaseDisplay.isPerpetual() ? true : false %>" cssClass="account-edit-subscription" label="" name="perpetual" onClick="<%= taglibOnClick %>" type="checkbox" />
						</td>

						<%
						if (productPurchaseDisplay.getStartDate() != null) {
							cal.setTime(productPurchaseDisplay.getStartDate());
						}
						%>

						<td class="table-cell-expand">
							<liferay-ui:input-date
								dayParam="startDateDay"
								dayValue="<%= cal.get(Calendar.DATE) %>"
								disabled="<%= productPurchaseDisplay.isPerpetual() %>"
								monthParam="startDateMonth"
								monthValue="<%= cal.get(Calendar.MONTH) %>"
								name="startDate"
								yearParam="startDateYear"
								yearValue="<%= cal.get(Calendar.YEAR) %>"
							/>
						</td>

						<%
						cal = CalendarFactoryUtil.getCalendar(timeZone, locale);

						if (productPurchaseDisplay.getEndDate() != null) {
							cal.setTime(productPurchaseDisplay.getEndDate());
						}
						%>

						<td class="table-cell-expand">
							<liferay-ui:input-date
								dayParam="endDateDay"
								dayValue="<%= cal.get(Calendar.DATE) %>"
								disabled="<%= productPurchaseDisplay.isPerpetual() %>"
								monthParam="endDateMonth"
								monthValue="<%= cal.get(Calendar.MONTH) %>"
								name="endDate"
								yearParam="endDateYear"
								yearValue="<%= cal.get(Calendar.YEAR) %>"
							/>
						</td>
						<td class="table-cell-expand">
							<aui:input cssClass="account-edit-subscription" label="" name="instanceSize" value="<%= productPurchaseDisplay.getSizing() %>" />
						</td>

						<%
						cal = CalendarFactoryUtil.getCalendar(timeZone, locale);

						if (productPurchaseDisplay.getOriginalEndDate() != null) {
							cal.setTime(productPurchaseDisplay.getOriginalEndDate());
						}
						%>

						<td class="table-cell-expand">
							<liferay-ui:input-date
								dayParam="gracePeriodEndDateDay"
								dayValue="<%= cal.get(Calendar.DATE) %>"
								disabled="<%= productPurchaseDisplay.isPerpetual() %>"
								monthParam="gracePeriodEndDateMonth"
								monthValue="<%= cal.get(Calendar.MONTH) %>"
								name="gracePeriodEndDate"
								yearParam="gracePeriodEndDateYear"
								yearValue="<%= cal.get(Calendar.YEAR) %>"
							/>
						</td>
						<td class="table-cell-expand">
							<aui:select cssClass="account-edit-subscription" label="" name="status">
								<aui:option label="<%= productPurchaseDisplay.getStatus() %>" value="<%= productPurchaseDisplay.getStatus() %>" />

								<%
								for (ProductPurchase.Status status : ProductPurchase.Status.values()) {
									if (!productPurchaseDisplay.getStatus().equals(status.toString())) {
								%>

										<aui:option label="<%= status.toString() %>" value="<%= status.toString() %>" />

								<%
									}
								}
								%>

							</aui:select>
						</td>
						<td class="table-cell-expand">
							<%= productPurchaseDisplay.getSupportLife() %>
						</td>
						<td class="table-cell-expand">
							<%= productPurchaseDisplay.getSalesforceOpportunityKey() %>
						</td>
						<td class="table-cell-expand">
							<%= productPurchaseDisplayContext.getAccountName() %>
						</td>
					</tr>
				</tbody>
			</table>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</div>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />toggleDate',
		function(checked, dateParam) {
			var A = AUI();

			if (checked) {
				A.one('#<portlet:namespace />perpetual').val(true);
			}
			else {
				A.one('#<portlet:namespace />perpetual').val(false);
			}

			var dayField = A.one('#<portlet:namespace />' + dateParam + 'Day');

			if (dayField) {
				dayField.attr('disabled', checked);
			}

			var inputDateField = A.one('#<portlet:namespace />' + dateParam);

			if (inputDateField) {
				inputDateField.attr('disabled', checked);

				if (checked) {
					inputDateField.addClass('disabled');
				}
				else {
					inputDateField.removeClass('disabled');
				}
			}

			var monthField = A.one('#<portlet:namespace />' + dateParam + 'Month');

			if (monthField) {
				monthField.attr('disabled', checked);
			}

			var yearField = A.one('#<portlet:namespace />' + dateParam + 'Year');

			if (yearField) {
				yearField.attr('disabled', checked);
			}
		},
		['aui-base']
	);
</aui:script>