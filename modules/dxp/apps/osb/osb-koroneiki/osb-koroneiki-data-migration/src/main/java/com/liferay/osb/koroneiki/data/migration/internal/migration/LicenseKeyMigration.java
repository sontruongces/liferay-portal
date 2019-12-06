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

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = LicenseKeyMigration.class)
public class LicenseKeyMigration {

	public void migrate(long userId) throws Exception {
		User user = _userLocalService.getUser(userId);

		StringBundler sb = new StringBundler(10);

		sb.append("select corpProjectId, OSB_ProductEntry.name, maxServers, ");
		sb.append("maxHttpSessions, description, serverId, key_, startDate, ");
		sb.append("expirationDate, complimentary, active_, ");
		sb.append("maxConcurrentUsers, maxUsers, hostName, ipAddresses, ");
		sb.append("macAddresses, additionalInfo, sizing from OSB_LicenseKey ");
		sb.append("inner join OSB_AccountEntry on ");
		sb.append("OSB_LicenseKey.accountEntryId = ");
		sb.append("OSB_AccountEntry.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_LicenseKey.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId where productId = 'Portal'");

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

				Hits hits = _productEntryLocalService.search(
					user.getCompanyId(), productEntryName, 0, 1, null);

				long productEntryId = 0;

				ProductEntry productEntry = null;

				for (Document document : hits.getDocs()) {
					productEntryId = GetterUtil.getLong(
						document.get(Field.ENTRY_CLASS_PK));

					productEntry = _productEntryLocalService.fetchProductEntry(
						productEntryId);
				}

				if (productEntry == null) {
					_log.error(
						"Unable to find product with productEntryId " +
							productEntryId);

					continue;
				}

				List<ProductField> productFields = new ArrayList<>();

				for (int i = 3; i < 19; i++) {
					ProductField productField =
						_productFieldLocalService.createProductField(0);

					String value = resultSet.getString(i);

					if (Validator.isNull(value)) {
						continue;
					}

					String name = metaData.getColumnName(i);

					productField.setName(name);

					productField.setValue(value);

					productFields.add(productField);
				}

				_productConsumptionLocalService.addProductConsumption(
					userId, corpProjectId, productEntryId, productFields);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyMigration.class);

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private UserLocalService _userLocalService;

}