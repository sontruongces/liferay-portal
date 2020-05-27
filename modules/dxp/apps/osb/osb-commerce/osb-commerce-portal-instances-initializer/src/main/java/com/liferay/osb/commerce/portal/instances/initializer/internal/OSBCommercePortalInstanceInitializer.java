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

package com.liferay.osb.commerce.portal.instances.initializer.internal;

import com.liferay.commerce.account.constants.CommerceAccountActionKeys;
import com.liferay.commerce.constants.CommerceActionKeys;
import com.liferay.commerce.currency.constants.CommerceCurrencyActionKeys;
import com.liferay.commerce.discount.constants.CommerceDiscountActionKeys;
import com.liferay.commerce.inventory.constants.CommerceInventoryActionKeys;
import com.liferay.commerce.price.list.constants.CommercePriceListActionKeys;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.theme.minium.SiteInitializerDependencyResolver;
import com.liferay.commerce.theme.minium.SiteInitializerDependencyResolverThreadLocal;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.instances.exception.InitializationException;
import com.liferay.portal.instances.initializer.PortalInstanceInitializer;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.site.initializer.SiteInitializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = "portal.instance.initializer.key=" + OSBCommercePortalInstanceInitializer.KEY,
	service = PortalInstanceInitializer.class
)
public class OSBCommercePortalInstanceInitializer
	implements PortalInstanceInitializer {

	public static final String KEY = "osb-commerce-portal-instance-initializer";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public void initialize(String webId, String virtualHostname, String mx)
		throws InitializationException {

		try {
			Company company = _addCompany(mx, webId, virtualHostname);

			_initializeOSBCommercePortalInstance(company.getWebId());

			_addOSBCommerceAdministratorRole(company.getCompanyId());

			long osbCommerceSiteGroupId = _addOSBCommerceSiteGroup(
				company.getCompanyId());

			_initializeOSBCommerceSite(
				osbCommerceSiteGroupId,
				_getDefaultUserId(company.getCompanyId()));
		}
		catch (Exception exception) {
			throw new InitializationException(exception);
		}
	}

	@Override
	public boolean isActive() {
		return true;
	}

	private Company _addCompany(String mx, String webId, String virtualHostname)
		throws PortalException {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(_getAdministratorUser()));

		try {
			return _companyLocalService.addCompany(
				webId, virtualHostname, mx, false, 0, true);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	private void _addOSBCommerceAdministratorRole(long companyId)
		throws PortalException {

		ServiceContext serviceContext = _getDefaultServiceContext(companyId);

		Role role = _roleLocalService.addRole(
			_getDefaultUserId(companyId), null, 0,
			_OSB_COMMERCE_ADMINISTRATOR_ROLE_NAME,
			Collections.singletonMap(
				serviceContext.getLocale(),
				_OSB_COMMERCE_ADMINISTRATOR_ROLE_NAME),
			Collections.emptyMap(), RoleConstants.TYPE_REGULAR,
			StringPool.BLANK, serviceContext);

		for (Map.Entry<String, String[]> entry : _actionIdMap.entrySet()) {
			for (String actionId : entry.getValue()) {
				_resourcePermissionLocalService.addResourcePermission(
					companyId, entry.getKey(), ResourceConstants.SCOPE_COMPANY,
					String.valueOf(companyId), role.getRoleId(), actionId);
			}
		}
	}

	private long _addOSBCommerceSiteGroup(long companyId) throws Exception {
		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getDefault(), "OSB Commerce");

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(LocaleUtil.getDefault(), StringPool.BLANK);

		Group group = _groupLocalService.addGroup(
			_getDefaultUserId(companyId),
			GroupConstants.DEFAULT_PARENT_GROUP_ID, null, 0, 0, nameMap,
			descriptionMap, GroupConstants.TYPE_SITE_OPEN, true,
			GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, null, true, true,
			_getDefaultServiceContext(companyId));

		return group.getGroupId();
	}

	private User _getAdministratorUser() throws PortalException {
		Role role = _roleLocalService.getRole(
			_portal.getDefaultCompanyId(), RoleConstants.ADMINISTRATOR);

		for (User user : _userLocalService.getRoleUsers(role.getRoleId())) {
			return user;
		}

		throw new IllegalStateException("Administrator user does not exist");
	}

	private long _getDefaultCompanyGroupId(long companyId)
		throws PortalException {

		Company company = _companyLocalService.getCompany(companyId);

		return company.getGroupId();
	}

	private ServiceContext _getDefaultServiceContext(long companyId)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setScopeGroupId(_getDefaultCompanyGroupId(companyId));
		serviceContext.setUserId(_getDefaultUserId(companyId));

		return serviceContext;
	}

	private long _getDefaultUserId(long companyId) throws PortalException {
		return _userLocalService.getDefaultUserId(companyId);
	}

	private void _initializeOSBCommercePortalInstance(String webId) {
		_portalInstancesLocalService.initializePortalInstance(
			_servletContext, webId);

		_portalInstancesLocalService.synchronizePortalInstances();
	}

	private void _initializeOSBCommerceSite(
			long osbCommerceSiteGroupId, long userId)
		throws Exception {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(
				_userLocalService.getUser(userId)));

		long currentUserId = PrincipalThreadLocal.getUserId();

		PrincipalThreadLocal.setName(userId);

		SiteInitializerDependencyResolverThreadLocal.
			setSiteInitializerDependencyResolver(
				_siteInitializerDependencyResolver);

		try {
			_siteInitializer.initialize(osbCommerceSiteGroupId);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(permissionChecker);
			PrincipalThreadLocal.setName(currentUserId);
		}
	}

	private static final String _OSB_COMMERCE_ADMINISTRATOR_ROLE_NAME =
		"OSB Commerce Administrator";

	private static final Map<String, String[]> _actionIdMap =
		new HashMap<String, String[]>() {
			{
				put(
					CPPortletKeys.COMMERCE_INVENTORY,
					new String[] {ActionKeys.ACCESS_IN_CONTROL_PANEL});
				put(
					PortletKeys.PORTAL,
					new String[] {
						CommerceAccountActionKeys.ADD_ACCOUNT,
						CommerceAccountActionKeys.ADD_ACCOUNT_GROUP,
						CPActionKeys.ADD_COMMERCE_CATALOG,
						CPActionKeys.ADD_COMMERCE_CHANNEL,
						CommerceDiscountActionKeys.ADD_COMMERCE_DISCOUNT,
						CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION,
						CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION_CATEGORY,
						CPActionKeys.ADD_COMMERCE_PRODUCT_SPECIFICATION_OPTION,
						CommerceInventoryActionKeys.ADD_WAREHOUSE,
						CommerceAccountActionKeys.MANAGE_ALL_ACCOUNTS,
						CommerceAccountActionKeys.MANAGE_AVAILABLE_ACCOUNTS,
						CommerceActionKeys.
							MANAGE_COMMERCE_AVAILABILITY_ESTIMATES,
						CommerceActionKeys.MANAGE_COMMERCE_COUNTRIES,
						CommerceCurrencyActionKeys.MANAGE_COMMERCE_CURRENCIES,
						CommerceActionKeys.MANAGE_COMMERCE_ORDER_PRICES,
						CommercePriceListActionKeys.MANAGE_COMMERCE_PRICE_LISTS,
						CPActionKeys.MANAGE_COMMERCE_PRODUCT_MEASUREMENT_UNITS,
						CPActionKeys.MANAGE_COMMERCE_PRODUCT_TAX_CATEGORIES,
						CommerceActionKeys.MANAGE_COMMERCE_SHIPMENTS,
						CommerceActionKeys.MANAGE_COMMERCE_SUBSCRIPTIONS,
						CommerceInventoryActionKeys.MANAGE_INVENTORY,
						CommerceAccountActionKeys.VIEW_COMMERCE_ACCOUNT_GROUPS,
						"VIEW_COMMERCE_CATALOGS", "VIEW_COMMERCE_CHANNELS",
						ActionKeys.VIEW_CONTROL_PANEL,
						CommerceDiscountActionKeys.VIEW_COMMERCE_DISCOUNTS
					});
			}
		};

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.osb.commerce.portal.instances.initializer)"
	)
	private ServletContext _servletContext;

	@Reference(target = "(site.initializer.key=minium-initializer)")
	private SiteInitializer _siteInitializer;

	@Reference(target = "(site.initializer.key=minium-initializer)")
	private SiteInitializerDependencyResolver
		_siteInitializerDependencyResolver;

	@Reference
	private UserLocalService _userLocalService;

}