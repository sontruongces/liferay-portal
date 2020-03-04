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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.provisioning.koroneiki.constants.ProductConstants;
import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.text.Format;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class AccountDisplay {

	public AccountDisplay(
			HttpServletRequest httpServletRequest, AccountReader accountReader,
			Account account)
		throws Exception {

		_httpServletRequest = httpServletRequest;
		_accountReader = accountReader;
		_account = account;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
	}

	public Account getAccount() {
		return _account;
	}

	public String getDeveloperContactUsage() {
		StringBundler sb = new StringBundler(5);

		sb.append(_accountReader.getDeveloperCount(_account));
		sb.append(" / ");
		sb.append(_accountReader.getMaxDeveloperCount(_account));
		sb.append(" ");
		sb.append(LanguageUtil.get(_httpServletRequest, "filled"));

		return sb.toString();
	}

	public String getEWSA() throws Exception {
		if (isEWSA()) {
			return LanguageUtil.get(_httpServletRequest, "yes");
		}

		return LanguageUtil.get(_httpServletRequest, "no");
	}

	public String getPartnerTeamName() {
		if (_partnerTeam == null) {
			_partnerTeam = _accountReader.getPartnerTeam(_account);
		}

		if (_partnerTeam != null) {
			return _partnerTeam.getName();
		}

		return StringPool.DASH;
	}

	public String getPrimaryCountry() {
		PostalAddress[] postalAddresses = _account.getPostalAddresses();

		if (postalAddresses != null) {
			for (PostalAddress postalAddress : postalAddresses) {
				if ((postalAddress.getPrimary() != null) &&
					postalAddress.getPrimary()) {

					return postalAddress.getAddressCountry();
				}
			}
		}

		return StringPool.BLANK;
	}

	public String getSLAName() {
		ProductPurchase slaProductPurchase = _getSLAProductPurchase();

		if (slaProductPurchase != null) {
			Product product = slaProductPurchase.getProduct();

			return StringUtil.removeSubstring(
				product.getName(), " Subscription");
		}

		return StringPool.DASH;
	}

	public String getStatusStyle() {
		Account.Status status = _account.getStatus();

		if (status == Account.Status.APPROVED) {
			return "label-success";
		}
		else if (status == Account.Status.CLOSED) {
			return "label-secondary";
		}
		else if (status == Account.Status.INACTIVE) {
			return "label-warning";
		}
		else if (status == Account.Status.PENDING) {
			return "label-primary";
		}
		else {
			return "label-danger";
		}
	}

	public String getSupportEndDate() {
		ProductPurchase slaProductPurchase = _getSLAProductPurchase();

		if (slaProductPurchase != null) {
			if (slaProductPurchase.getPerpetual()) {
				return LanguageUtil.get(_httpServletRequest, "perpetual");
			}

			return _dateFormat.format(slaProductPurchase.getEndDate());
		}

		return StringPool.BLANK;
	}

	public boolean isEWSA() throws Exception {
		if (_ancestorAccounts == null) {
			_ancestorAccounts = _accountReader.getAncestorAccounts(_account);
		}

		for (Account account : _ancestorAccounts) {
			if (_isEWSA(account)) {
				return true;
			}
		}

		return false;
	}

	private ProductPurchase _getSLAProductPurchase() {
		if (_slaProductPurchase != null) {
			return _slaProductPurchase;
		}

		return _accountReader.getSLAProductPurchase(_account);
	}

	private boolean _isEWSA(Account account) throws Exception {
		ProductPurchase[] productPurchases = account.getProductPurchases();

		if (productPurchases != null) {
			for (ProductPurchase productPurchase : productPurchases) {
				Product product = productPurchase.getProduct();

				String name = product.getName();

				if (name.equals(ProductConstants.NAME_DXP_EWSA) ||
					name.equals(ProductConstants.NAME_PORTAL_EWSA)) {

					return true;
				}
			}
		}

		return false;
	}

	private final Account _account;
	private final AccountReader _accountReader;
	private List<Account> _ancestorAccounts;
	private final Format _dateFormat;
	private final HttpServletRequest _httpServletRequest;
	private Team _partnerTeam;
	private ProductPurchase _slaProductPurchase;

}