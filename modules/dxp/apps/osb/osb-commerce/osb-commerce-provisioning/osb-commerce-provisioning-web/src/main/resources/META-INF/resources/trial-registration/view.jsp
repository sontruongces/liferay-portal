<%@ page import="com.liferay.commerce.model.CommerceCountry" %>
<%@ page import="java.util.List" %><%--
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

<%@ include file="../init.jsp" %>

<%
	TrialRegistrationDisplayContext trialRegistrationDisplayContext =
		(TrialRegistrationDisplayContext) request
			.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

	List<CommerceCountry> commerceCountryList = trialRegistrationDisplayContext
		.getCommerceCountries(company.getCompanyId());
%>

<div id="trial-registration" class="container-fluid">
	<div class="row">
		<div class="col-md-6 col-xs-12">
			<div class="punch-line">
				<h1>Try Liferay Commerce for 30 days</h1>

				<p>
					Explore Liferay Commerce on a private demo site. We'll save your credentials and content for 30 days.
				</p>
			</div>
		</div>

		<div class="col-md-6 col-xs-12">
			<div class="form-container">
				<div class="form-title">
					<h4>​Start your private demo</h4>
				</div>

				<div class="form-wrapper">
					<portlet:actionURL name="registerTrial" var="registerTrialURL" />

					<aui:form action="<%= registerTrialURL %>" method="post" name="fm">
						<aui:input class="form-field" id="name" name="name" placeholder="Your name" required="true" type="input" />

						<aui:input class="form-field" id="workEmail" name="workEmail" placeholder="Your work e-mail" required="true" type="email" />

						<aui:input class="form-field" id="jobTitle" name="jobTitle" placeholder="Job title" required="true" type="text" />

						<aui:input class="form-field" id="companyName" name="companyName" placeholder="Company" required="true" type="text" />

						<aui:select class="form-field" id="countryCode" name="countryCode" required="true">
							<%
								for (CommerceCountry commerceCountry : commerceCountryList) {
							%>
									<aui:option value="<%= commerceCountry.getTwoLettersISOCode() %>">
										<%= commerceCountry.getName(locale) %>
									</aui:option>
							<%
								}
							%>
						</aui:select>

						<p class="disclaimer">
							I understand and accept the terms and conditions of the following agreements: Evaluation License Agreement, EULA for Liferay Developer Studio, Privacy Policy.
						</p>

						<button class="btn btn-primary" type="submit">Start Trial</button>
					</aui:form>
				</div>
			</div>
		</div>
	</div>
</div>