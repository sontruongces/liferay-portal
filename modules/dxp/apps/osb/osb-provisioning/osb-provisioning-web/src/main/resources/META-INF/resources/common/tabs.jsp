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
String[] names = StringUtil.split(ParamUtil.getString(request, "names"));
String param = ParamUtil.getString(request, "param");
String url = ParamUtil.getString(request, "url");
String[] values = StringUtil.split(ParamUtil.getString(request, "values"));

String paramValue = ParamUtil.getString(request, param);
%>

<ul class="mb-3 mb-lg-4 nav nav-underline">

	<%
	for (int i = 0; i < names.length; i++) {
		StringBundler sb = new StringBundler();

		sb.append(url);
		sb.append("&");
		sb.append(renderResponse.getNamespace());
		sb.append(param);
		sb.append("=");
		sb.append(values[i]);
	%>

		<li class="nav-item">
			<a class="nav-link <%= (((i == 0) && Validator.isNull(paramValue)) || paramValue.equals(values[i])) ? "active" : "" %>" href="<%= sb.toString() %>">
				<%= LanguageUtil.get(request, names[i]) %>
			</a>
		</li>

	<%
	}
	%>

</ul>