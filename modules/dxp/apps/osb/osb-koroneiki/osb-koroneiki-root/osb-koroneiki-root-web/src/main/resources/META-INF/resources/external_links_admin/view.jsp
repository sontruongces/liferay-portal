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
%>

<portlet:actionURL name="/external_links_admin/edit_external_link_mappings" var="editExternalLinkMappingsURL" />

<aui:form action="<%= editExternalLinkMappingsURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset-group>
		<h3 class="sheet-subtitle"><liferay-ui:message key="external-link-mappings" /></h3>

		<aui:fieldset id='<%= renderResponse.getNamespace() + "externalLinks" %>'>

			<%
			Map<String, String[]> map = portletPreferences.getMap();

			int[] externalLinkIndexes = new int[map.size()];

			int i = 0;

			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String key = entry.getKey();

				String domain = key.split(StringPool.UNDERLINE)[0];
				String entityName = key.split(StringPool.UNDERLINE)[1];
				String url = entry.getValue()[0];

				externalLinkIndexes[i] = i;
			%>

				<div class="lfr-form-row lfr-form-row-inline">
					<div class="row-fields">
						<aui:row>
							<aui:col md="4">
								<aui:input label="domain" name='<%= "domain_" + i %>' type="text" value="<%= domain %>" />
							</aui:col>

							<aui:col md="4">
								<aui:input label="entity-name" name='<%= "entityName_" + i %>' type="text" value="<%= entityName %>" />
							</aui:col>

							<aui:col md="4">
								<aui:input label="url" name='<%= "url_" + i %>' type="text" value="<%= url %>" />
							</aui:col>
						</aui:row>
					</div>
				</div>

			<%
				i++;
			}
			%>

			<aui:input name="externalLinkIndexes" type="hidden" value="<%= StringUtil.merge(externalLinkIndexes) %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<liferay-frontend:fieldset
		collapsed="<%= true %>"
		collapsible="<%= true %>"
		label="definition-of-terms"
	>
		<dt>
			[$ENTITY_ID$]
		</dt>
		<dd>
			The unique identifier of the external object.
		</dd>
	</liferay-frontend:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,liferay-auto-fields">
	var autoFields = new Liferay.AutoFields(
		{
			contentBox: 'fieldset#<portlet:namespace />externalLinks',
			fieldIndexes: '<portlet:namespace />externalLinkIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();
</aui:script>