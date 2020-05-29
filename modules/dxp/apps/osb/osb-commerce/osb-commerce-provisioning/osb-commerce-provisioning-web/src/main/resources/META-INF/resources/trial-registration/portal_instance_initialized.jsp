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
String portalInstanceURL = (String)request.getAttribute(OSBCommerceProvisioningWebKeys.PORTAL_INSTANCE_URL);
%>

<h1>Welcome to Your Demo</h1>

<p class="lead">Please check your email for instructions on how to log in to your demo. If you have any questions, let us know at hello@liferay.com.</p>

<p>Trial expires at 20.06.2020</p>

<a class="btn btn-primary" href="<%= portalInstanceURL %>" target="_blank">Open COMMERCE demo</a>