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

package com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.function.UnsafeSupplier;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactAccountViewSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class ContactAccountView {

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccount(
		UnsafeSupplier<Account, Exception> accountUnsafeSupplier) {

		try {
			account = accountUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Account account;

	public ContactRole[] getContactRoles() {
		return contactRoles;
	}

	public void setContactRoles(ContactRole[] contactRoles) {
		this.contactRoles = contactRoles;
	}

	public void setContactRoles(
		UnsafeSupplier<ContactRole[], Exception> contactRolesUnsafeSupplier) {

		try {
			contactRoles = contactRolesUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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
		return ContactAccountViewSerDes.toJSON(this);
	}

}