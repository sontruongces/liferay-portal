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

List<ContactRole> contactRoles = (List<ContactRole>)request.getAttribute(ProvisioningWebKeys.CONTACT_ROLES);

String emailAddress = ParamUtil.getString(request, "emailAddress");

ViewAccountLiferayWorkersDisplayContext viewAccountLiferayWorkersDisplayContext = ProvisioningWebComponentProvider.getViewAccountLiferayWorkersDisplayContext(renderRequest, renderResponse, request);

AccountDisplay accountDisplay = viewAccountLiferayWorkersDisplayContext.getAccountDisplay();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "assign-liferay-worker"));
%>

<portlet:actionURL name="/accounts/assign_contact_roles" var="assignContactRolesURL">
	<portlet:param name="mvcRenderCommandName" value="/accounts/assign_liferay_workers" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="accountKey" value="<%= accountDisplay.getKey() %>" />
</portlet:actionURL>

<aui:form action="<%= assignContactRolesURL.toString() %>" cssClass="container-fluid-1280" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "submitForm();" %>'>
	<aui:input name="addContactRoleKeys" type="hidden" />
	<aui:input name="deleteContactRoleKeys" type="hidden" />

	<liferay-ui:error exception="<%= HttpException.class %>">

		<%
		HttpException httpException = (HttpException)errorException;
		%>

		<%= httpException.getMessage() %>
	</liferay-ui:error>

	<c:choose>
		<c:when test="<%= contactRoles != null %>">
			<aui:input name="emailAddress" readonly="<%= true %>" value="<%= emailAddress %>" />
		</c:when>
		<c:otherwise>
			<aui:input name="emailAddress" value="<%= emailAddress %>" />
		</c:otherwise>
	</c:choose>

	<label>
		<liferay-ui:message key="roles" />
	</label>

	<div class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#1">
			<liferay-ui:message key="select" />

			<aui:icon image="caret-double-l" markupView="lexicon" />
		</a>

		<ul class="dropdown-menu" role="menu">

			<%
			List<ContactRole> curContactRoles = viewAccountLiferayWorkersDisplayContext.getContactRoles();

			for (ContactRole contactRole : curContactRoles) {
				boolean hasContactRole = (contactRoles != null) && contactRoles.contains(contactRole);
			%>

				<li class="dropdown-item">
					<div class="checkbox-inline">

						<%
						StringBundler sb = new StringBundler();

						sb.append(renderResponse.getNamespace());
						sb.append("updateContactRole(");

						if (hasContactRole) {
							sb.append("true");
						}
						else {
							sb.append("false");
						}

						sb.append(", '");
						sb.append(contactRole.getKey());
						sb.append("');");
						%>

						<aui:input checked="<%= hasContactRole %>" id="<%= contactRole.getKey() %>" inline="<%= true %>" label="<%= contactRole.getName() %>" name="contactRole" onChange="<%= sb.toString() %>" type="checkbox" value="<%= contactRole.getKey() %>" />
					</div>
				</li>

			<%
			}
			%>

		</ul>
	</div>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />submitForm() {
		var A = AUI();

		var form = document.getElementById('<portlet:namespace />fm');

		var addContactRoleKeys = [];

		var addContactRoleCheckboxes = A.all('input[action=add]');

		addContactRoleCheckboxes.each(function(item, index, collection) {
			addContactRoleKeys.push(item.val());
		});

		var addContactRoleKeysInput = form.querySelector(
			'#<portlet:namespace />addContactRoleKeys'
		);

		addContactRoleKeysInput.setAttribute('value', addContactRoleKeys.join(','));

		var deleteContactRoleKeys = [];

		var deleteContactRoleCheckboxes = A.all('input[action=delete]');

		deleteContactRoleCheckboxes.each(function(item, index, collection) {
			deleteContactRoleKeys.push(item.val());
		});

		var deleteContactRoleKeysInput = form.querySelector(
			'#<portlet:namespace />deleteContactRoleKeys'
		);

		deleteContactRoleKeysInput.setAttribute(
			'value',
			deleteContactRoleKeys.join(',')
		);

		form.submit();
	}

	function <portlet:namespace />updateContactRole(
		hasContactRole,
		contactRoleKey
	) {
		var A = AUI();

		var checkbox = A.one('#<portlet:namespace />' + contactRoleKey);

		if (hasContactRole && !checkbox.attr('checked')) {
			checkbox.attr('action', 'delete');
		}
		else if (!hasContactRole && checkbox.attr('checked')) {
			checkbox.attr('action', 'add');
		}
		else {
			checkbox.attr('action', '');
		}
	}
</aui:script>