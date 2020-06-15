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

package com.liferay.osb.provisioning.web.internal.display.context;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kyle Bischof
 */
public class ViewContactDisplayContext {

	public ViewContactDisplayContext() {
	}

	public void addPortletBreadcrumbEntries() throws Exception {
		PortletURL accountsPortletURL = renderResponse.createRenderURL();

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, LanguageUtil.get(httpServletRequest, "users"),
			accountsPortletURL.toString());

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/users/view_contact");
		portletURL.setParameter(
			"contactEmailAddress", contact.getEmailAddress());

		PortalUtil.addPortletBreadcrumbEntry(
			httpServletRequest, contactDisplay.getFullName(),
			portletURL.toString());
	}

	public ContactDisplay getContactDisplay() {
		return contactDisplay;
	}

	public String getCurrentURL() {
		return currentURLObj.toString();
	}

	public SearchContainer getCustomerAccountsSearchContainer()
		throws Exception {

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(),
			"this-user-is-not-assigned-to-any-customer-roles-yet");

		List<Account> accounts = accountWebService.getContactAccounts(
			contact.getUuid());

		for (Account account : accounts) {
			List<ContactRole> customerContactRoles =
				contactRoleWebService.getAccountCustomerContactRoles(
					account.getKey(), contact.getEmailAddress(), 1, 1000);

			Contact curContact = new Contact();

			curContact.setContactRoles(
				customerContactRoles.toArray(new ContactRole[0]));

			account.setCustomerContacts(new Contact[] {curContact});
		}

		searchContainer.setResults(
			TransformUtil.transform(
				accounts,
				account -> contactDisplay.new AccountDisplay(account)));

		return searchContainer;
	}

	public PortletURL getPortletURL() {
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/users/view_contact");
		portletURL.setParameter(
			"tabs1", ParamUtil.getString(renderRequest, "tabs1"));
		portletURL.setParameter(
			"contactEmailAddress", contact.getEmailAddress());

		return portletURL;
	}

	public void init(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest,
			AccountWebService accountWebService,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService)
		throws Exception {

		this.renderRequest = renderRequest;
		this.renderResponse = renderResponse;
		this.httpServletRequest = httpServletRequest;
		this.accountWebService = accountWebService;
		this.contactRoleWebService = contactRoleWebService;
		this.contactWebService = contactWebService;

		doInit();
	}

	protected void doInit() throws Exception {
		contact = (Contact)renderRequest.getAttribute(
			ProvisioningWebKeys.CONTACT);

		List<ContactRole> contactRoles = null;

		if (contact.getContactRoles() != null) {
			contactRoles = new ArrayList<>(
				Arrays.asList(contact.getContactRoles()));
		}

		contactDisplay = new ContactDisplay(
			httpServletRequest,
			accountWebService.getContactAccountsCount(contact.getUuid()),
			contact, contactRoles);

		currentURLObj = PortletURLUtil.getCurrent(
			renderRequest, renderResponse);
	}

	protected long accountsCount;
	protected AccountWebService accountWebService;
	protected Contact contact;
	protected ContactDisplay contactDisplay;
	protected ContactRoleWebService contactRoleWebService;
	protected ContactWebService contactWebService;
	protected PortletURL currentURLObj;
	protected HttpServletRequest httpServletRequest;
	protected RenderRequest renderRequest;
	protected RenderResponse renderResponse;

}