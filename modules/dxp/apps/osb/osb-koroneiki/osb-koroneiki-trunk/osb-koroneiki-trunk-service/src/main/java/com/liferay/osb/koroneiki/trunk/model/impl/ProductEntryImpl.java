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

package com.liferay.osb.koroneiki.trunk.model.impl;

import com.liferay.osb.koroneiki.root.model.ExternalLink;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalServiceUtil;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class ProductEntryImpl extends ProductEntryBaseImpl {

	public ProductEntryImpl() {
	}

	public List<ExternalLink> getExternalLinks() {
		return ExternalLinkLocalServiceUtil.getExternalLinks(
			ProductEntry.class.getName(), getProductEntryId(),
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<ProductField> getProductFields() {
		return ProductFieldLocalServiceUtil.getProductFields(
			ProductEntry.class.getName(), getProductEntryId());
	}

	public Map<String, String> getProductFieldsMap() {
		Map<String, String> productFieldsMap = new HashMap<>();

		List<ProductField> productFields = getProductFields();

		for (ProductField productField : productFields) {
			productFieldsMap.put(
				productField.getName(), productField.getValue());
		}

		return productFieldsMap;
	}

}