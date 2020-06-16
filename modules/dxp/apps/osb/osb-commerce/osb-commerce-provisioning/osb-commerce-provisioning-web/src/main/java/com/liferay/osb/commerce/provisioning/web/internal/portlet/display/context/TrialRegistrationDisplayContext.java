package com.liferay.osb.commerce.provisioning.web.internal.portlet.display.context;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.osb.commerce.provisioning.constants.OSBCommercePortalInstanceConstants;
import com.liferay.osb.commerce.provisioning.util.OSBCommercePortalInstanceUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;
import java.util.Objects;

/**
 * @author Gianmarco Brunialti Masera
 */
public class TrialRegistrationDisplayContext {

	public TrialRegistrationDisplayContext(
		CommerceCountryService commerceCountryService,
		CommerceSubscriptionEntryLocalService
			commerceSubscriptionEntryLocalService) {

		_commerceCountryService = commerceCountryService;
		_commerceSubscriptionEntryLocalService =
			commerceSubscriptionEntryLocalService;
	}

	public List<CommerceCountry> getCommerceCountries(long companyId) {
		return _commerceCountryService.getCommerceCountries(companyId, true);
	}

	public String getPortalInstanceURL(long commerceOrderItemId) {
		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.
				fetchCommerceSubscriptionEntryByCommerceOrderItemId(
					commerceOrderItemId);

		UnicodeProperties unicodeProperties =
			commerceSubscriptionEntry.getSubscriptionTypeSettingsProperties();

		return OSBCommercePortalInstanceUtil.getPortalInstanceURL(
			unicodeProperties.get(
				OSBCommercePortalInstanceConstants.
					PORTAL_INSTANCE_VIRTUAL_HOSTNAME));
	}

	public int getTrialLengthInDays(long commerceOrderItemId) {
		CommerceSubscriptionEntry commerceSubscriptionEntry =
			_commerceSubscriptionEntryLocalService.
				fetchCommerceSubscriptionEntryByCommerceOrderItemId(
					commerceOrderItemId);

		int trialLength = commerceSubscriptionEntry.getSubscriptionLength();

		if (Objects.equals(
				commerceSubscriptionEntry.getSubscriptionType(),
				CPConstants.WEEKLY_SUBSCRIPTION_TYPE)) {

			trialLength *= 7;
		}
		else if (Objects.equals(
					commerceSubscriptionEntry.getSubscriptionType(),
					CPConstants.MONTHLY_SUBSCRIPTION_TYPE)) {

			trialLength *= 30;
		}

		return trialLength;
	}

	private final CommerceCountryService _commerceCountryService;
	private final CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

}