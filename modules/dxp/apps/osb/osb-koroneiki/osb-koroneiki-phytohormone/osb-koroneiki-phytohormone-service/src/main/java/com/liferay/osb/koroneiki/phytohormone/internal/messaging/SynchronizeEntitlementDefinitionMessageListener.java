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

package com.liferay.osb.koroneiki.phytohormone.internal.messaging;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneDestinationNames;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.MapUtil;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "destination.name=" + PhytohormoneDestinationNames.SYNCHRONIZE_ENTITLEMENT_DEFINITION,
	service = MessageListener.class
)
public class SynchronizeEntitlementDefinitionMessageListener
	extends BaseEntitlementsMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_SERIAL,
				PhytohormoneDestinationNames.
					SYNCHRONIZE_ENTITLEMENT_DEFINITION);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		_destinationServiceRegistration = bundleContext.registerService(
			Destination.class, destination,
			MapUtil.singletonDictionary(
				"destination.name", destination.getName()));
	}

	@Deactivate
	protected void deactivate() {
		_destinationServiceRegistration.unregister();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long entitlementDefinitionId = message.getLong(
			"entitlementDefinitionId");

		if (entitlementDefinitionId > 0) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Running entitlement synchronization for " +
						entitlementDefinitionId);
			}

			EntitlementDefinition entitlementDefinition =
				entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionId);

			processEntitlementDefinition(
				entitlementDefinition.getCompanyId(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getClassNameId(),
				entitlementDefinition.getName(),
				entitlementDefinition.getDefinition());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SynchronizeEntitlementDefinitionMessageListener.class);

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _destinationServiceRegistration;

}