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

package com.liferay.osb.provisioning.model;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class Subscription {

	public Subscription(ProductPurchaseView productPurchaseView) {
		Product product = productPurchaseView.getProduct();

		setType(_getProductType(product));

		setProduct(product);
		setProductName(product.getName());

		ProductConsumption[] productConsumptions =
			productPurchaseView.getProductConsumptions();

		if (productConsumptions != null) {
			setProvisionedCount(productConsumptions.length);
		}

		ProductPurchase[] productPurchases =
			productPurchaseView.getProductPurchases();

		for (ProductPurchase productPurchase : productPurchases) {
			setStartDate(_getEarliestStartDate(productPurchase));
			setEndDate(_getLatestEndDate(productPurchase));
			setPurchasedCount(_purchasedCount + 1);
			setSizing(_getCurrentSizing(productPurchase));
			setStatus(_getStatus());
		}
	}

	public Date getEndDate() {
		return _endDate;
	}

	public Product getProduct() {
		return _product;
	}

	public String getProductName() {
		return _productName;
	}

	public int getProvisionedCount() {
		return _provisionedCount;
	}

	public int getPurchasedCount() {
		return _purchasedCount;
	}

	public int getSizing() {
		return _sizing;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public String getStatus() {
		return _status;
	}

	public String getType() {
		return _type;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public void setProduct(Product product) {
		_product = product;
	}

	public void setProductName(String productName) {
		_productName = productName;
	}

	public void setProvisionedCount(int provisionedCount) {
		_provisionedCount = provisionedCount;
	}

	public void setPurchasedCount(int purchasedCount) {
		_purchasedCount = purchasedCount;
	}

	public void setSizing(int sizing) {
		_sizing = sizing;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setType(String type) {
		_type = type;
	}

	private int _getCurrentSizing(ProductPurchase productPurchase) {
		Map<String, String> properties = productPurchase.getProperties();

		if (properties != null) {
			String sizing = properties.get("sizing");

			if (Validator.isNull(sizing)) {
				return _sizing;
			}

			int curSizing = Integer.valueOf(sizing);

			if ((curSizing > _sizing) && (_endDate != null) &&
				_endDate.after(new Date())) {

				return curSizing;
			}
		}

		return _sizing;
	}

	private Date _getEarliestStartDate(ProductPurchase productPurchase) {
		if ((_startDate == null) ||
			_startDate.after(productPurchase.getStartDate())) {

			return productPurchase.getStartDate();
		}

		return _startDate;
	}

	private Date _getLatestEndDate(ProductPurchase productPurchase) {
		if ((_endDate == null) ||
			_endDate.before(productPurchase.getEndDate())) {

			return productPurchase.getEndDate();
		}

		return _endDate;
	}

	private String _getProductType(Product product) {
		Map<String, String> properties = product.getProperties();

		if (properties != null) {
			String type = properties.get("type");

			if (Validator.isNotNull(type)) {
				return type;
			}
		}

		return StringPool.BLANK;
	}

	private String _getStatus() {
		if ((_startDate == null) ||
			((_endDate != null) && _endDate.after(new Date()))) {

			return "active";
		}

		return "inactive";
	}

	private Date _endDate;
	private Product _product;
	private String _productName;
	private int _provisionedCount;
	private int _purchasedCount;
	private int _sizing;
	private Date _startDate;
	private String _status;
	private String _type;

}