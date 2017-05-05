<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPOptionValue cpOptionValue = (CPOptionValue)request.getAttribute(CPWebKeys.COMMERCE_PRODUCT_OPTION_VALUE);
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="custom-fields" />

<aui:model-context bean="<%= cpOptionValue %>" model="<%= CPOptionValue.class %>" />

<liferay-expando:custom-attribute-list
	className="<%= CPOptionValue.class.getName() %>"
	classPK="<%= (cpOptionValue != null) ? cpOptionValue.getCPOptionValueId() : 0 %>"
	editable="<%= true %>"
	label="<%= true %>"
/>