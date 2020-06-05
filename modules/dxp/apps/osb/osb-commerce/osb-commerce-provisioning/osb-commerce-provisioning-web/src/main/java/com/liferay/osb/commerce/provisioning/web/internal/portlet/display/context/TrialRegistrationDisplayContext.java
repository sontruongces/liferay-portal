package com.liferay.osb.commerce.provisioning.web.internal.portlet.display.context;

import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceCountryService;

import java.util.List;

/**
 * @author Gianmarco Brunialti Masera
 */
public class TrialRegistrationDisplayContext {

	public TrialRegistrationDisplayContext(
		CommerceCountryService commerceCountryService) {

		_commerceCountryService = commerceCountryService;
	}

	public List<CommerceCountry> getCommerceCountries(long companyId) {
		return _commerceCountryService.getCommerceCountries(companyId, true);
	}

	private final CommerceCountryService _commerceCountryService;

}