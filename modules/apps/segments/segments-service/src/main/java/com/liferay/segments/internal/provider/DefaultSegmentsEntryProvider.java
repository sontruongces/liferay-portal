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

package com.liferay.segments.internal.provider;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.segments.constants.SegmentsEntryConstants;
import com.liferay.segments.odata.retriever.ODataRetriever;
import com.liferay.segments.provider.SegmentsEntryProvider;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Eduardo García
 */
@Component(
	property = {
		"segments.entry.provider.order:Integer=100",
		"segments.entry.provider.source=" + SegmentsEntryConstants.SOURCE_DEFAULT
	},
	service = SegmentsEntryProvider.class
)
public class DefaultSegmentsEntryProvider
	extends BaseSegmentsEntryProvider implements SegmentsEntryProvider {

	@Activate
	protected void activate(BundleContext bundleContext) {
		serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext,
			(Class<ODataRetriever<BaseModel<?>>>)(Class<?>)ODataRetriever.class,
			"model.class.name");
	}

	@Deactivate
	protected void deactivate() {
		serviceTrackerMap.close();
	}

	@Override
	protected String getSource() {
		return SegmentsEntryConstants.SOURCE_DEFAULT;
	}

}