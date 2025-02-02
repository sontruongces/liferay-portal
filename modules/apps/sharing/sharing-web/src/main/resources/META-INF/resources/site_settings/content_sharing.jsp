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

<%@ include file="/site_settings/init.jsp" %>

<%
int companyContentSharingWithChildrenEnabled = PrefsPropsUtil.getInteger(company.getCompanyId(), PropsKeys.SITES_CONTENT_SHARING_WITH_CHILDREN_ENABLED);
UnicodeProperties groupTypeSettingsUnicodeProperties = (UnicodeProperties)request.getAttribute("site.groupTypeSettings");
%>

<aui:select label="allow-subsites-to-display-content-from-this-site" name="contentSharingWithChildrenEnabled" value='<%= PropertiesParamUtil.getInteger(groupTypeSettingsUnicodeProperties, request, "contentSharingWithChildrenEnabled", Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE) %>'>
	<aui:option label='<%= (companyContentSharingWithChildrenEnabled == Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED_BY_DEFAULT) ? "default-value-enabled" : "default-value-disabled" %>' value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DEFAULT_VALUE %>" />
	<aui:option label="enabled" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_ENABLED %>" />
	<aui:option label="disabled" value="<%= Sites.CONTENT_SHARING_WITH_CHILDREN_DISABLED %>" />
</aui:select>