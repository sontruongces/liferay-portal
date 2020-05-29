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

<style>
	.container-fluid {
		background-color: #EEF2FA;
		font-family: "Source Sans Pro", Helvetica, Arial, sans-serif;
	}

	.punch-line {
		box-sizing: border-box;
		margin: 0 auto;
		padding: 12em 5em 0 0;
		width: 500px;
	}

	.punch-line h1 {
		font-weight: 600;
	}

	.punch-line p {
		font-size: 32px;
		font-weight: 200;
		line-height: 1.3;
	}

	.form-container {
		background-color: #FFF;
		box-sizing: border-box;
		padding: 3em;
		margin: 4em auto;
		width: 465px;
		box-shadow: 0 10px 20px 20px rgba(0, 0, 0, .05);
	}

	.form-container button,
	.form-container input,
	.form-container select {
		background-color: #FFF;
		width: 100%;
		border-radius: 5px;
		border: 2px solid #E5E8F8;
		box-sizing: border-box;
		padding: 10px;
	}

	.form-container button {
		background-color: #0B63CE;
		border-radius: 8px;
		font-variant: small-caps;
		font-weight: 600;
		text-transform: lowercase;
		letter-spacing: 2px;
		color: #FFF;
	}

	.form-container select {
		height: 48px;
	}

	.form-container label {
		text-transform: capitalize;
		font-weight: 600;
		margin-bottom: 4px;
		margin-top: 1em;
		margin-left: 10px;
	}

	.form-container .disclaimer {
		margin: 1em 0 1.5em 0;
		font-weight: 300;
		font-size: 12px;
		color: #394452;
	}

	​ .form-container .success.hide {
		display: none;
	}

	​ .form-container .success.show {
		display: block;
	}
</style>

<div class="container-fluid">
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
							<aui:option value="HR">Croatia</aui:option>
							<aui:option value="US">United States</aui:option>
							<aui:option value="IT">Italy</aui:option>
						</aui:select>

						<p class="disclaimer">
							I understand and accept the terms and conditions of the following agreements: Evaluation License Agreement, EULA for Liferay Developer Studio, Privacy Policy.
						</p>

						<button type="submit">Start Trial</button>
					</aui:form>
				</div>
			</div>
		</div>
	</div>
</div>