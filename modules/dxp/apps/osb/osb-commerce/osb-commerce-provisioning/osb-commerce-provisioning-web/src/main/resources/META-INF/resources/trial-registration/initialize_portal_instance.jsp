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
long commerceOrderItemId = ParamUtil.getLong(request, "commerceOrderItemId");
%>

<h1>Hello</h1>

<p class="lead">Let's get Liferay Commerce instance up and running</p>
<p>This usually takes about 2 minutes:</p>

<ul>
	<li>Setting up a unique link</li>
	<li>Setting up you as an administrator</li>
	<li><span class="icon-spin icon-spinner text-primary"></span> Creating a new instance for you</li>
</ul>

<portlet:resourceURL id="portalInstanceStatus" var="portalInstanceStatusResourceURL">
	<portlet:param name="commerceOrderItemId" value="<%= String.valueOf(commerceOrderItemId) %>" />
</portlet:resourceURL>

<portlet:renderURL var="portalInstanceInitializedURL">
	<portlet:param name="mvcRenderCommandName" value="portalInstanceInitialized" />
	<portlet:param name="commerceOrderItemId" value="<%= String.valueOf(commerceOrderItemId) %>" />
</portlet:renderURL>

<aui:script>
	setTimeout(function() {
		function callOnTimeOut() {
			var resourceURL = '<%= portalInstanceStatusResourceURL %>';

			fetch(resourceURL, {
				credentials: 'include',
				headers: new Headers({'x-csrf-token': Liferay.authToken}),
				method: 'post'
			})
			.then(function(res) {
				return res.json();
			})
			.then(function(payload) {
				if (payload.status === 0) {
					window.location="<%= portalInstanceInitializedURL %>";
				}
				else {
					setTimeout(function() {
						callOnTimeOut();
					}, 2000);
				}
			});
		}

		callOnTimeOut();
	}, 2000);
</aui:script>