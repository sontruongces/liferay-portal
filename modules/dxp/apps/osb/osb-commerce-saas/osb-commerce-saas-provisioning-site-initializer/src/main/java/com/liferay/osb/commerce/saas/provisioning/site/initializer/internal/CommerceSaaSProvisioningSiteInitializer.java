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

package com.liferay.osb.commerce.saas.provisioning.site.initializer.internal;

import com.liferay.commerce.theme.minium.SiteInitializerDependencyResolver;
import com.liferay.commerce.theme.minium.SiteInitializerDependencyResolverThreadLocal;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	property = "site.initializer.key=" + CommerceSaaSProvisioningSiteInitializer.KEY,
	service = SiteInitializer.class
)
public class CommerceSaaSProvisioningSiteInitializer
	implements SiteInitializer {

	public static final String KEY = "commerce-saas-provisioning-initializer";

	@Override
	public String getDescription(Locale locale) {
		return _siteInitializer.getDescription(locale);
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "commerce-saas-provisioning");
	}

	@Override
	public String getThumbnailSrc() {
		return _servletContext.getContextPath() + "/images/thumbnail.png";
	}

	@Override
	public void initialize(long groupId) throws InitializationException {
		try {
			SiteInitializerDependencyResolverThreadLocal.
				setSiteInitializerDependencyResolver(
					_osbCommerceProvisioningSiteInitializerDependencyResolver);

			_siteInitializer.initialize(groupId);
		}
		catch (InitializationException initializationException) {
			throw initializationException;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new InitializationException(exception);
		}
	}

	@Override
	public boolean isActive(long companyId) {
		return _siteInitializer.isActive(companyId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceSaaSProvisioningSiteInitializer.class);

	@Reference(
		target = "(site.initializer.key=commerce-saas-provisioning-initializer)"
	)
	private SiteInitializerDependencyResolver
		_osbCommerceProvisioningSiteInitializerDependencyResolver;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.osb.commerce.saas.provisioning.site.initializer)"
	)
	private ServletContext _servletContext;

	@Reference(target = "(site.initializer.key=minium-initializer)")
	private SiteInitializer _siteInitializer;

}