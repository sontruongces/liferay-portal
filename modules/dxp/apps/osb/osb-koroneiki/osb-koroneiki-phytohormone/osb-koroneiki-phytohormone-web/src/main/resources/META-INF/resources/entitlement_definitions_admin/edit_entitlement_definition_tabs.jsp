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
String tabs1 = ParamUtil.getString(request, "tabs1", "details");

EntitlementDefinition entitlementDefinition = (EntitlementDefinition)request.getAttribute(PhytohormoneWebKeys.ENTITLEMENT_DEFINITION);

long entitlementDefinitionId = BeanParamUtil.getLong(entitlementDefinition, request, "entitlementDefinitionId");
%>

<clay:navigation-bar
	inverted="<%= true %>"
	navigationItems='<%=
		new JSPNavigationItemList(pageContext) {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("details"));
						navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/entitlement_definitions_admin/edit_entitlement_definition", "tabs1", "details", "entitlementDefinitionId", entitlementDefinitionId);
						navigationItem.setLabel(LanguageUtil.get(request, "details"));
					});

				add(
					navigationItem -> {
						navigationItem.setActive(tabs1.equals("external-links"));

						if (entitlementDefinition != null) {
							navigationItem.setHref(renderResponse.createRenderURL(), "mvcRenderCommandName", "/entitlement_definitions_admin/edit_entitlement_definition", "tabs1", "external-links", "entitlementDefinitionId", entitlementDefinitionId);
						}

						navigationItem.setLabel(LanguageUtil.get(request, "external-links"));
					});
			}
		}
	%>'
/>