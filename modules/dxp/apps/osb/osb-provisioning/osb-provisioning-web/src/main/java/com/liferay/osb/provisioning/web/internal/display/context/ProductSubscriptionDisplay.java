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
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ProductSubscriptionDisplay {

	public ProductSubscriptionDisplay(
			HttpServletRequest httpServletRequest, Account account,
			ProductPurchaseView productPurchaseView)
		throws Exception {

		_httpServletRequest = httpServletRequest;
		_account = account;

		_dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"MMM dd, yyyy");
		_product = productPurchaseView.getProduct();

		ProductConsumption[] productConsumptions =
			productPurchaseView.getProductConsumptions();

		if (productConsumptions != null) {
			_provisionedCount = productConsumptions.length;
		}
		else {
			_provisionedCount = 0;
		}

		Date now = new Date();

		_initProductPurchases(productPurchaseView.getProductPurchases(), now);

		if (((_startDate == null) || _startDate.before(now)) &&
			((_endDate == null) || _endDate.after(now))) {

			_status = "active";
		}
		else if ((_startDate != null) && _startDate.after(now)) {
			_status = "unactivated";
		}
		else {
			_status = "inactive";
		}
	}

	public String getAccountKey() {
		return _account.getKey();
	}

	public String getName() {
		return _product.getName();
	}

	public String getProductKey() {
		return _product.getKey();
	}

	public String getProvisionedCount() {
		return String.valueOf(_provisionedCount);
	}

	public String getPurchasedCount() {
		return String.valueOf(_purchasedCount);
	}

	public String getSizing() {
		if (_sizing > 0) {
			return LanguageUtil.get(_httpServletRequest, "instance-size") +
				": " + _sizing;
		}

		return StringPool.BLANK;
	}

	public String getStatus() {
		return LanguageUtil.get(_httpServletRequest, _status);
	}

	public String getStatusStyle() {
		return "label-" + _status;
	}

	public String getSupportLife() {
		if (_startDate == null) {
			return LanguageUtil.get(_httpServletRequest, "perpetual");
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_dateFormat.format(_startDate));
		sb.append(" - ");

		if (_endDate != null) {
			sb.append(_dateFormat.format(_endDate));
		}
		else {
			sb.append(LanguageUtil.get(_httpServletRequest, "perpetual"));
		}

		return sb.toString();
	}

	public String getType() {
		Map<String, String> properties = _product.getProperties();

		if (properties != null) {
			String type = properties.get("type");

			if (Validator.isNotNull(type)) {
				return type;
			}
		}

		return StringPool.BLANK;
	}

	private void _initProductPurchases(
		ProductPurchase[] productPurchases, Date now) {

		for (ProductPurchase productPurchase : productPurchases) {
			Date startDate = productPurchase.getStartDate();
			Date endDate = productPurchase.getEndDate();

			if (_startDateInitialized || (startDate == null) ||
				((_startDate != null) && _startDate.after(startDate))) {

				_startDate = startDate;
				_startDateInitialized = true;
			}

			if (_endDateInitialized || (endDate == null) ||
				((_endDate != null) && _endDate.before(endDate))) {

				_endDate = endDate;
				_endDateInitialized = true;
			}

			Map<String, String> properties = productPurchase.getProperties();

			if (properties != null) {
				int sizing = GetterUtil.getInteger(properties.get("sizing"));

				if ((sizing > _sizing) &&
					((startDate == null) || startDate.before(now)) &&
					((endDate == null) || endDate.after(now))) {

					_sizing = sizing;
				}
			}

			_purchasedCount += productPurchase.getQuantity();
		}
	}

	private final Account _account;
	private final Format _dateFormat;
	private Date _endDate;
	private boolean _endDateInitialized;
	private final HttpServletRequest _httpServletRequest;
	private final Product _product;
	private final int _provisionedCount;
	private int _purchasedCount;
	private int _sizing;
	private Date _startDate;
	private boolean _startDateInitialized;
	private final String _status;

}