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

package com.liferay.asset.taglib.internal.servlet.taglib.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.taglib.internal.servlet.ServletContextUtil;
import com.liferay.asset.util.AssetEntryUsageRecorder;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class AssetEntryUsagesTaglibUtil {

	public static void recordAssetEntryUsage(String className, long classPK) {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
			className, classPK);

		try {
			Map<String, AssetEntryUsageRecorder> assetEntryUsageRecorders =
				ServletContextUtil.getAssetEntryUsageRecorders();

			AssetEntryUsageRecorder assetEntryUsageRecorder =
				assetEntryUsageRecorders.get(assetEntry.getClassName());

			if (assetEntryUsageRecorder != null) {
				assetEntryUsageRecorder.record(assetEntry);
			}
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					StringBundler.concat(
						"Unable to check asset entry usages for class name ",
						className, " and class PK ", classPK),
					portalException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryUsagesTaglibUtil.class);

}