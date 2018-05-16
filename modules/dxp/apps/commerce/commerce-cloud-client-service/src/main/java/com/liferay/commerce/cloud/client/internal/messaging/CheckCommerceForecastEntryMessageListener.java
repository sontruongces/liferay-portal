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

package com.liferay.commerce.cloud.client.internal.messaging;

import com.liferay.commerce.cloud.client.configuration.CommerceCloudClientConfiguration;
import com.liferay.commerce.cloud.client.constants.CommerceCloudClientConstants;
import com.liferay.commerce.cloud.client.util.CommerceCloudClient;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Andrea Di Giorgi
 */
@Component(
	configurationPid = CommerceCloudClientConstants.CONFIGURATION_PID,
	immediate = true, service = CheckCommerceForecastEntryMessageListener.class
)
public class CheckCommerceForecastEntryMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_commerceCloudClientConfiguration = ConfigurableUtil.createConfigurable(
			CommerceCloudClientConfiguration.class, properties);

		int interval =
			_commerceCloudClientConfiguration.forecastingEntriesCheckInterval();

		if (!_commerceCloudClientConfiguration.forecastingEnabled() ||
			_commerceCloudClientConfiguration.pushSynchronizationEnabled() ||
			(interval <= 0)) {

			return;
		}

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, interval, TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		if (_commerceCloudClientConfiguration.forecastingEnabled()) {
			_schedulerEngineHelper.unregister(this);
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		_commerceCloudClient.checkCommerceForecastEntries();
	}

	@Reference
	private CommerceCloudClient _commerceCloudClient;

	private volatile CommerceCloudClientConfiguration
		_commerceCloudClientConfiguration;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}