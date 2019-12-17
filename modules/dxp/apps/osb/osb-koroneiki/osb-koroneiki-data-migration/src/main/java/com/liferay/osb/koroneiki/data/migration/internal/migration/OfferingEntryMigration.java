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

package com.liferay.osb.koroneiki.data.migration.internal.migration;

import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = OfferingEntryMigration.class)
public class OfferingEntryMigration {

	public void migrate(long userId) throws Exception {
		StringBundler sb = new StringBundler(9);

		sb.append("select corpProjectId, OSB_ProductEntry.name, startDate, ");
		sb.append("supportEndDate, quantity, OSB_OfferingEntry.status, ");
		sb.append("licenses, sizing, version from OSB_OfferingEntry inner ");
		sb.append("join OSB_AccountEntry on OSB_OfferingEntry.accountEntryId ");
		sb.append("= OSB_AccountEntry.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_OfferingEntry.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId inner join OSB_OrderEntry ");
		sb.append("on OSB_OfferingEntry.orderEntryId = ");
		sb.append("OSB_OrderEntry.orderEntryId");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
				sb.toString());
			ResultSet resultSet = preparedStatement.executeQuery()) {

			ResultSetMetaData metaData = resultSet.getMetaData();

			while (resultSet.next()) {
				long corpProjectId = resultSet.getLong(1);

				Account account = _accountLocalService.fetchAccount(
					corpProjectId);

				if (account == null) {
					_log.error(
						"Unable to find account with accountId " +
							corpProjectId);

					continue;
				}

				String productEntryName = resultSet.getString(2);

				ProductEntry productEntry =
					_productEntryLocalService.fetchProductEntryByName(
						productEntryName);

				Date startDate = resultSet.getDate(3);
				Date endDate = resultSet.getDate(4);

				int quantity = resultSet.getInt(5);

				int status = resultSet.getInt(6);

				if (status != 2) {
					status = WorkflowConstants.STATUS_APPROVED;
				}
				else {
					status = WorkflowConstants.STATUS_CANCELLED;
				}

				List<ProductField> productFields = new ArrayList<>();

				for (int i = 7; i < 10; i++) {
					ProductField productField =
						_productFieldLocalService.createProductField(0);

					String value = resultSet.getString(i);

					if (Validator.isNull(value)) {
						continue;
					}

					String name = metaData.getColumnName(i);

					if (name.equals("version") && !value.equals("0")) {
						value = _liferayVersionMap.get(value);
					}

					productField.setName(name);

					productField.setValue(value);

					productFields.add(productField);
				}

				_productPurchaseLocalService.addProductPurchase(
					userId, account.getAccountId(),
					productEntry.getProductEntryId(), startDate, endDate,
					endDate, quantity, status, productFields);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OfferingEntryMigration.class);

	private static final Map<String, String> _liferayVersionMap =
		new HashMap<String, String>() {
			{
				put("21000", "5");
				put("21001", "6");
				put("22002", "6.0");
				put("22003", "6.1");
				put("22004", "6.2");
				put("42000", "7");
			}
		};

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}