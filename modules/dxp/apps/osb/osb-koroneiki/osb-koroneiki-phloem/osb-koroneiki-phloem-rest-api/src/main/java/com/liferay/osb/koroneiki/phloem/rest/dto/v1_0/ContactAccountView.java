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

package com.liferay.osb.koroneiki.phloem.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.validation.Valid;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
@GraphQLName("ContactAccountView")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "ContactAccountView")
public class ContactAccountView {

	@Schema
	@Valid
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@JsonIgnore
	public void setAccount(
		UnsafeSupplier<Account, Exception> accountUnsafeSupplier) {

		try {
			account = accountUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Account account;

	@Schema
	@Valid
	public ContactRole[] getContactRoles() {
		return contactRoles;
	}

	public void setContactRoles(ContactRole[] contactRoles) {
		this.contactRoles = contactRoles;
	}

	@JsonIgnore
	public void setContactRoles(
		UnsafeSupplier<ContactRole[], Exception> contactRolesUnsafeSupplier) {

		try {
			contactRoles = contactRolesUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected ContactRole[] contactRoles;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ContactAccountView)) {
			return false;
		}

		ContactAccountView contactAccountView = (ContactAccountView)object;

		return Objects.equals(toString(), contactAccountView.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (account != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"account\": ");

			sb.append(String.valueOf(account));
		}

		if (contactRoles != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contactRoles\": ");

			sb.append("[");

			for (int i = 0; i < contactRoles.length; i++) {
				sb.append(String.valueOf(contactRoles[i]));

				if ((i + 1) < contactRoles.length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		defaultValue = "com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactAccountView",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");
			sb.append("\"");
			sb.append(entry.getValue());
			sb.append("\"");

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}