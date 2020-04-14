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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactRoleResource;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ContactRoleWebService.class
)
public class ContactRoleWebServiceImpl implements ContactRoleWebService {

	public List<ContactRole> getAccountCustomerContactRoles(
			String accountKey, String emailAddress, int page, int pageSize)
		throws Exception {

		Page<ContactRole> contactRolesPage =
			_contactRoleResource.
				getAccountAccountKeyContactByEmailAddresContactEmailAddressRolesPage(
					accountKey, emailAddress, Pagination.of(page, pageSize));

		if ((contactRolesPage != null) &&
			(contactRolesPage.getItems() != null)) {

			return new ArrayList<>(contactRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	public ContactRole getContactRole(String contactRoleKey) throws Exception {
		return _contactRoleResource.getContactRole(contactRoleKey);
	}

	public List<ContactRole> search(
			String filterString, int page, int pageSize, String sortString)
		throws Exception {

		Page<ContactRole> contactRolesPage =
			_contactRoleResource.getContactRolesPage(
				StringPool.BLANK, filterString, Pagination.of(page, pageSize),
				sortString);

		if ((contactRolesPage != null) &&
			(contactRolesPage.getItems() != null)) {

			return new ArrayList<>(contactRolesPage.getItems());
		}

		return Collections.emptyList();
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ContactRoleResource.Builder builder = ContactRoleResource.builder();

		_contactRoleResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ContactRoleResource _contactRoleResource;

}