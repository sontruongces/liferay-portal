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

package com.liferay.osb.koroneiki.phytohormone.service.impl;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneActionKeys;
import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneDestinationNames;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.permission.EntitlementDefinitionPermission;
import com.liferay.osb.koroneiki.phytohormone.service.base.EntitlementDefinitionServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=EntitlementDefinition"
	},
	service = AopService.class
)
public class EntitlementDefinitionServiceImpl
	extends EntitlementDefinitionServiceBaseImpl {

	public EntitlementDefinition addEntitlementDefinition(
			long classNameId, String name, String description,
			String definition, int status)
		throws PortalException {

		_entitlementDefinitionPermission.check(
			getPermissionChecker(),
			PhytohormoneActionKeys.ADD_ENTITLEMENT_DEFINITION);

		return entitlementDefinitionLocalService.addEntitlementDefinition(
			getUserId(), classNameId, name, description, definition, status);
	}

	public EntitlementDefinition deleteEntitlementDefinition(
			long entitlementDefinitionId)
		throws PortalException {

		_entitlementDefinitionPermission.check(
			getPermissionChecker(), entitlementDefinitionId, ActionKeys.DELETE);

		return entitlementDefinitionLocalService.deleteEntitlementDefinition(
			entitlementDefinitionId);
	}

	public void synchronizeEntitlementDefinition(long entitlementDefinitionId)
		throws Exception {

		_entitlementDefinitionPermission.check(
			getPermissionChecker(), entitlementDefinitionId, ActionKeys.UPDATE);

		Message message = new Message();

		message.put("entitlementDefinitionId", entitlementDefinitionId);

		_messageBus.sendMessage(
			PhytohormoneDestinationNames.SYNCHRONIZE_ENTITLEMENT_DEFINITION,
			message);
	}

	@Reference
	private EntitlementDefinitionPermission _entitlementDefinitionPermission;

	@Reference
	private MessageBus _messageBus;

}