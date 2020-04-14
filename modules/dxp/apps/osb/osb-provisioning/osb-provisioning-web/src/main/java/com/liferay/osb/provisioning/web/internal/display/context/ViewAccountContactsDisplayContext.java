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

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.LabelItemList;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Collections;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ViewAccountContactsDisplayContext
	extends ViewAccountDisplayContext {

	public ViewAccountContactsDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			AccountWebService accountWebService,
			AuditEntryWebService auditEntryWebService,
			ContactRoleWebService contactRoleWebService,
			ContactWebService contactWebService,
			ExternalLinkWebService externalLinkWebService,
			NoteWebService noteWebService,
			ProductPurchaseViewWebService productPurchaseViewWebService,
			TeamWebService teamWebService)
		throws Exception {

		super(
			renderRequest, renderResponse, httpServletRequest, accountReader,
			accountWebService, auditEntryWebService, contactRoleWebService,
			contactWebService, externalLinkWebService, noteWebService,
			productPurchaseViewWebService, teamWebService);
	}

	public List<DropdownItem> getFilterDropdownItems() throws Exception {
		return new DropdownItemList() {
			{
				addGroup(
					dropdownGroupItem -> {
						dropdownGroupItem.setDropdownItems(
							_getFilterRoleDropdownItems());
						dropdownGroupItem.setLabel(
							LanguageUtil.get(
								httpServletRequest, "filter-by-role"));
					});
			}
		};
	}

	public List<LabelItem> getFilterLabelItems() {
		return new LabelItemList() {
			{
				String[] contactRoleKeys = ParamUtil.getStringValues(
					renderRequest, "contactRoleKeys");

				for (String contactRoleKey : contactRoleKeys) {
					add(
						labelItem -> {
							PortletURL removeLabelURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] removeContactRoleKeys = ArrayUtil.remove(
								contactRoleKeys, contactRoleKey);

							removeLabelURL.setParameter(
								"contactRoleKeys",
								StringUtil.merge(removeContactRoleKeys));

							labelItem.putData(
								"removeLabelURL", removeLabelURL.toString());

							labelItem.setCloseable(true);

							ContactRole contactRole =
								contactRoleWebService.getContactRole(
									contactRoleKey);

							String label = String.format(
								"%s: %s",
								LanguageUtil.get(
									httpServletRequest, "contact-role"),
								contactRole.getName());

							labelItem.setLabel(label);
						});
				}
			}
		};
	}

	public SearchContainer getSearchContainer() throws Exception {
		String keywords = ParamUtil.getString(renderRequest, "keywords");

		SearchContainer searchContainer = new SearchContainer(
			renderRequest, renderResponse.createRenderURL(),
			Collections.emptyList(), "no-contacts-were-found");

		StringBundler sb = new StringBundler();

		sb.append("customerAccountKeys/any(s:s eq '");
		sb.append(account.getKey());
		sb.append("')");

		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		if (!ArrayUtil.isEmpty(contactRoleKeys)) {
			sb.append(" and accountKeysContactRoleKeys/any(s:");

			for (int i = 0; i < contactRoleKeys.length; i++) {
				if (i > 0) {
					sb.append(" or ");
				}

				sb.append("s eq '");
				sb.append(account.getKey());
				sb.append("_");
				sb.append(contactRoleKeys[i]);
				sb.append("'");
			}

			sb.append(")");
		}

		List<Contact> contacts = contactWebService.search(
			keywords, sb.toString(), searchContainer.getCur(),
			searchContainer.getEnd() - searchContainer.getStart(), "firstName");

		searchContainer.setResults(
			TransformUtil.transform(
				contacts,
				contact -> {
					List<ContactRole> contactRoles =
						contactRoleWebService.getAccountCustomerContactRoles(
							account.getKey(), contact.getEmailAddress(), 1,
							1000);

					return new ContactDisplay(
						httpServletRequest, contact, contactRoles);
				}));

		searchContainer.setRowChecker(
			new EmptyOnClickRowChecker(renderResponse));

		int count = (int)contactWebService.searchCount(keywords, sb.toString());

		searchContainer.setTotal(count);

		return searchContainer;
	}

	private List<ContactRole> _getContactRoles(String type) throws Exception {
		return contactRoleWebService.search(
			"type eq '" + type + "'", 1, 1000, "name");
	}

	private List<DropdownItem> _getFilterRoleDropdownItems() throws Exception {
		String[] contactRoleKeys = ParamUtil.getStringValues(
			renderRequest, "contactRoleKeys");

		return new DropdownItemList() {
			{
				for (ContactRole contactRole :
						_getContactRoles(
							ContactRole.Type.ACCOUNT_CUSTOMER.toString())) {

					add(
						dropdownItem -> {
							dropdownItem.setActive(
								ArrayUtil.contains(
									contactRoleKeys, contactRole.getKey()));

							PortletURL portletURL = PortletURLUtil.clone(
								currentURLObj, renderResponse);

							String[] newContactRoleKeys = ArrayUtil.append(
								contactRoleKeys, contactRole.getKey());

							dropdownItem.setHref(
								portletURL, "contactRoleKeys",
								StringUtil.merge(newContactRoleKeys));

							dropdownItem.setLabel(contactRole.getName());
						});
				}
			}
		};
	}

}