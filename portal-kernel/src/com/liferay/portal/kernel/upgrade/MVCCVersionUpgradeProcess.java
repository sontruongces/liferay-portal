/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.dao.db.DBInspector;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.xml.Element;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * @author Shuyang Zhou
 */
public class MVCCVersionUpgradeProcess extends UpgradeProcess {

	public void upgradeMVCCVersion(
			DatabaseMetaData databaseMetaData, String tableName)
		throws Exception {

		DBInspector dbInspector = new DBInspector(connection);

		tableName = dbInspector.normalizeName(tableName, databaseMetaData);

		ensureTableExists(databaseMetaData, dbInspector, tableName);

		try (ResultSet columnResultSet = databaseMetaData.getColumns(
				dbInspector.getCatalog(), dbInspector.getSchema(), tableName,
				dbInspector.normalizeName("mvccVersion", databaseMetaData))) {

			if (columnResultSet.next()) {
				return;
			}

			alterTableAddColumn(
				tableName, "mvccVersion", "LONG default 0 not null");

			if (_log.isDebugEnabled()) {
				_log.debug("Added column mvccVersion to table " + tableName);
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeModuleTableMVCCVersions();
	}

	protected String[] getTableNames() {
		return new String[] {"BackgroundTask", "Lock_"};
	}

	protected void upgradeModuleTableMVCCVersions() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			DatabaseMetaData databaseMetaData = connection.getMetaData();

			String[] tableNames = getTableNames();

			for (String tableName : tableNames) {
				upgradeMVCCVersion(databaseMetaData, tableName);
			}
		}
	}

	protected void upgradeMVCCVersion(
			DatabaseMetaData databaseMetaData, Element classElement)
		throws Exception {

		String tableName = classElement.attributeValue("table");

		upgradeMVCCVersion(databaseMetaData, tableName);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MVCCVersionUpgradeProcess.class);

}