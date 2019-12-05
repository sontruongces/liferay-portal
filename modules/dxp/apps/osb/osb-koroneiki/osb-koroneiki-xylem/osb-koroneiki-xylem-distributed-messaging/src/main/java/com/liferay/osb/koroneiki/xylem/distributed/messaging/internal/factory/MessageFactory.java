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

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.factory;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.AccountUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.ContactRoleUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.ContactUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.ProductConsumptionUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.ProductPurchaseUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.ProductUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.TeamRoleUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.util.TeamUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductConsumptionSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamRoleSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamSerDes;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = MessageFactory.class)
public class MessageFactory {

	public Message create(Account account) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account
			dtoAccount = AccountUtil.toAccount(account);

		return new Message(AccountSerDes.toJSON(dtoAccount));
	}

	public Message create(Contact contact) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact
			dtoContact = ContactUtil.toContact(contact);

		return new Message(ContactSerDes.toJSON(dtoContact));
	}

	public Message create(ContactAccountRole contactAccountRole)
		throws Exception {

		JSONObject jsonObject = JSONUtil.put(
			"account", toJSONObject(contactAccountRole.getAccount())
		).put(
			"contact", toJSONObject(contactAccountRole.getContact())
		).put(
			"contactRole", toJSONObject(contactAccountRole.getContactRole())
		);

		return new Message(jsonObject.toString());
	}

	public Message create(ContactRole contactRole) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole
			dtoContactRole = ContactRoleUtil.toContactRole(contactRole);

		return new Message(ContactRoleSerDes.toJSON(dtoContactRole));
	}

	public Message create(ProductConsumption productConsumption)
		throws Exception {

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption
			dtoProductConsumption = ProductConsumptionUtil.toProductConsumption(
				productConsumption);

		return new Message(
			ProductConsumptionSerDes.toJSON(dtoProductConsumption));
	}

	public Message create(ProductEntry productEntry) throws Exception {
		Product dtoProduct = ProductUtil.toProduct(productEntry);

		return new Message(ProductSerDes.toJSON(dtoProduct));
	}

	public Message create(ProductPurchase productPurchase) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase
			dtoProductPurchase = ProductPurchaseUtil.toProductPurchase(
				productPurchase);

		return new Message(ProductPurchaseSerDes.toJSON(dtoProductPurchase));
	}

	public Message create(Team team) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team dtoTeam =
			TeamUtil.toTeam(team);

		return new Message(TeamSerDes.toJSON(dtoTeam));
	}

	public Message create(TeamAccountRole teamAccountRole) throws Exception {
		JSONObject jsonObject = JSONUtil.put(
			"account", toJSONObject(teamAccountRole.getAccount())
		).put(
			"team", toJSONObject(teamAccountRole.getTeam())
		).put(
			"teamRole", toJSONObject(teamAccountRole.getTeamRole())
		);

		return new Message(jsonObject.toString());
	}

	public Message create(TeamRole teamRole) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole
			dtoTeamRole = TeamRoleUtil.toTeamRole(teamRole);

		return new Message(TeamRoleSerDes.toJSON(dtoTeamRole));
	}

	protected JSONObject toJSONObject(Account account) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account
			dtoAccount = AccountUtil.toAccount(account);

		return _jsonFactory.createJSONObject(AccountSerDes.toJSON(dtoAccount));
	}

	protected JSONObject toJSONObject(Contact contact) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact
			dtoContact = ContactUtil.toContact(contact);

		return _jsonFactory.createJSONObject(ContactSerDes.toJSON(dtoContact));
	}

	protected JSONObject toJSONObject(ContactRole contactRole)
		throws Exception {

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole
			dtoContactRole = ContactRoleUtil.toContactRole(contactRole);

		return _jsonFactory.createJSONObject(
			ContactRoleSerDes.toJSON(dtoContactRole));
	}

	protected JSONObject toJSONObject(Team team) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team dtoTeam =
			TeamUtil.toTeam(team);

		return _jsonFactory.createJSONObject(TeamSerDes.toJSON(dtoTeam));
	}

	protected JSONObject toJSONObject(TeamRole teamRole) throws Exception {
		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole
			dtoTeamRole = TeamRoleUtil.toTeamRole(teamRole);

		return _jsonFactory.createJSONObject(
			TeamRoleSerDes.toJSON(dtoTeamRole));
	}

	@Reference
	private JSONFactory _jsonFactory;

}