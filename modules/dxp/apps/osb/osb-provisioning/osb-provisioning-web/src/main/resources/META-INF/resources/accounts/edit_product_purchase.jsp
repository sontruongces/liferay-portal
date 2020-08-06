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

EditProductPurchaseDisplayContext editProductPurchaseDisplayContext = ProvisioningWebComponentProvider.getEditProductPurchaseDisplayContext(renderRequest, renderResponse, request);

ProductPurchase productPurchase = editProductPurchaseDisplayContext.getProductPurchase();
ProductPurchaseDisplay productPurchaseDisplay = editProductPurchaseDisplayContext.getProductPurchaseDisplay();
%>

<div class="account-add-items nav">
	<liferay-ui:header
		backURL="<%= redirect %>"
		cssClass="add-items-header"
		title="edit-subscription"
	/>

	<portlet:actionURL name="/accounts/edit_product_purchase" var="editProductPurchaseURL">
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

	<aui:form action="<%= editProductPurchaseURL.toString() %>" method="post" name="fm">
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
							<aui:input cssClass="account-edit-subscription" label="" name="quantity" value="<%= productPurchase.getQuantity() %>" />
						</td>

						<%
						String taglibOnClick = renderResponse.getNamespace() + "toggleDate(this.checked, 'startDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'endDate');" + renderResponse.getNamespace() + "toggleDate(this.checked, 'gracePeriodEndDate');";
						%>

						<td class="table-cell-expand">
							<aui:input checked="<%= productPurchase.getPerpetual() %>" cssClass="account-edit-subscription" label="" name="perpetual" onClick="<%= taglibOnClick %>" type="checkbox" />
						</td>

						<%
						Calendar startCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

						if (productPurchase.getStartDate() != null) {
							startCal.setTime(productPurchase.getStartDate());
						}
						%>

						<td class="table-cell-expand">
							<liferay-ui:input-date
								dayParam="startDateDay"
								dayValue="<%= startCal.get(Calendar.DATE) %>"
								disabled="<%= productPurchase.getPerpetual() %>"
								monthParam="startDateMonth"
								monthValue="<%= startCal.get(Calendar.MONTH) %>"
								name="startDate"
								yearParam="startDateYear"
								yearValue="<%= startCal.get(Calendar.YEAR) %>"
							/>
						</td>

						<%
						Calendar endCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

						if (productPurchase.getEndDate() != null) {
							endCal.setTime(productPurchase.getEndDate());
						}
						%>

						<td class="table-cell-expand">
							<liferay-ui:input-date
								dayParam="endDateDay"
								dayValue="<%= endCal.get(Calendar.DATE) %>"
								disabled="<%= productPurchase.getPerpetual() %>"
								monthParam="endDateMonth"
								monthValue="<%= endCal.get(Calendar.MONTH) %>"
								name="endDate"
								yearParam="endDateYear"
								yearValue="<%= endCal.get(Calendar.YEAR) %>"
							/>
						</td>
						<td class="table-cell-expand">

							<%
							int sizing = 0;

							Map<String, String> properties = productPurchase.getProperties();

							if (properties != null) {
								sizing = GetterUtil.getInteger(properties.get("sizing"));
							}
							%>

							<aui:input cssClass="account-edit-subscription" label="" name="sizing" value="<%= sizing %>" />
						</td>

						<%
						Calendar originalEndCal = CalendarFactoryUtil.getCalendar(timeZone, locale);

						if (productPurchase.getOriginalEndDate() != null) {
							originalEndCal.setTime(productPurchase.getOriginalEndDate());
						}
						%>

						<td class="table-cell-expand">
							<liferay-ui:input-date
								dayParam="gracePeriodEndDateDay"
								dayValue="<%= originalEndCal.get(Calendar.DATE) %>"
								disabled="<%= productPurchase.getPerpetual() %>"
								monthParam="gracePeriodEndDateMonth"
								monthValue="<%= originalEndCal.get(Calendar.MONTH) %>"
								name="gracePeriodEndDate"
								yearParam="gracePeriodEndDateYear"
								yearValue="<%= originalEndCal.get(Calendar.YEAR) %>"
							/>
						</td>
						<td class="table-cell-expand">
							<aui:select cssClass="account-edit-subscription" label="" name="status">

								<%
								for (ProductPurchase.Status status : ProductPurchase.Status.values()) {
								%>

									<aui:option label="<%= status.toString() %>" selected="<%= productPurchase.getStatus() == status %>" value="<%= status.toString() %>" />

								<%
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
							<%= editProductPurchaseDisplayContext.getAccountName() %>
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