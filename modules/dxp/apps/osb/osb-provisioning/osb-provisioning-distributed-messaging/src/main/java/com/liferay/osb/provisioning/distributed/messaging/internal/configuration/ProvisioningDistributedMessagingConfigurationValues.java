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

package com.liferay.osb.provisioning.distributed.messaging.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class ProvisioningDistributedMessagingConfigurationValues {

	public static final String PROVISIONING_EMAIL_ADDRESS =
		"provisioning.email.address";

	public static final long PROVISIONING_ZENDESK_GROUP_ID = GetterUtil.getLong(
		ProvisioningDistributedMessagingConfigurationUtil.get(
			"provisioning.zendesk.group.id"));

	public static final long PROVISIONING_ZENDESK_ORGANIZATION_ID =
		GetterUtil.getLong(
			ProvisioningDistributedMessagingConfigurationUtil.get(
				"provisioning.zendesk.organization.id"));

	public static final long PROVISIONING_ZENDESK_REQUESTER_ID =
		GetterUtil.getLong(
			ProvisioningDistributedMessagingConfigurationUtil.get(
				"provisioning.zendesk.requester.id"));

	public static final long ZENDESK_CUSTOM_FIELD_OPPORTUNITY_OWNER_ID =
		GetterUtil.getLong(
			ProvisioningDistributedMessagingConfigurationUtil.get(
				"zendesk.custom.field.opportunity.owner.id"));

	public static final long ZENDESK_CUSTOM_FIELD_PRIMARY_ADDRESS_COUNTRY_ID =
		GetterUtil.getLong(
			ProvisioningDistributedMessagingConfigurationUtil.get(
				"zendesk.custom.field.primary.address.country.id"));

	public static final long ZENDESK_CUSTOM_FIELD_PRODUCT_ID =
		GetterUtil.getLong(
			ProvisioningDistributedMessagingConfigurationUtil.get(
				"zendesk.custom.field.product.id"));

	public static final long ZENDESK_CUSTOM_FIELD_SUPPORT_REGION_ID =
		GetterUtil.getLong(
			ProvisioningDistributedMessagingConfigurationUtil.get(
				"zendesk.custom.field.support.region.id"));

}