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

package com.liferay.commerce.data.integration.apio.internal.security.permission;

import com.liferay.apio.architect.credentials.Credentials;
import com.liferay.apio.architect.functional.Try;
import com.liferay.commerce.data.integration.apio.security.permission.CollectionPermissionChecker;
import com.liferay.commerce.product.constants.CPActionKeys;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.service.CPOptionService;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.UserService;

import java.util.function.BiFunction;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 */
@Component(immediate = true, service = OptionPermissionChecker.class)
public class OptionPermissionChecker implements CollectionPermissionChecker {

	public BiFunction<Credentials, Long, Boolean> forAdding() {
		return (credentials, cpOptionId) -> {
			Try<PermissionChecker> permissionCheckerTry =
				_getPermissionCheckerTry(credentials);

			Try<CPOption> cpOptionTry = Try.fromFallible(
				() -> _cpOptionService.fetchCPOption(cpOptionId));

			return permissionCheckerTry.map(
				permissionChecker -> _portletResourcePermission.contains(
					permissionChecker,
					cpOptionTry.map(
						CPOption::getGroupId
					).orElseThrow(
						() -> new NotFoundException()
					),
					CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION)
			).orElse(
				false
			);
		};
	}

	public BiFunction<Credentials, Long, Boolean> forDeleting() {
		return _permissionBridge();
	}

	public BiFunction<Credentials, Long, Boolean> forUpdating() {
		return (credentials, cpOptionId) -> {
			Try<PermissionChecker> permissionCheckerTry =
				_getPermissionCheckerTry(credentials);

			Try<CPOption> optionTry = Try.fromFallible(
				() -> _cpOptionService.fetchCPOption(cpOptionId));

			return permissionCheckerTry.map(
				permissionChecker -> _portletResourcePermission.contains(
					permissionChecker,
					optionTry.map(
						CPOption::getGroupId
					).orElseThrow(
						() -> new NotFoundException()
					),
					CPActionKeys.ADD_COMMERCE_PRODUCT_OPTION)
			).orElse(
				false
			);
		};
	}

	private Try<PermissionChecker> _getPermissionCheckerTry(
		Credentials credentials) {

		return Try.success(
			credentials.get()
		).map(
			Object::toString
		).map(
			Long::valueOf
		).map(
			_userService::getUserById
		).map(
			PermissionCheckerFactoryUtil::create
		).recoverWith(
			__ -> Try.fromFallible(PermissionThreadLocal::getPermissionChecker)
		);
	}

	private BiFunction<Credentials, Long, Boolean> _permissionBridge() {
		return forUpdating();
	}

	@Reference
	private CPOptionService _cpOptionService;

	@Reference
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private UserService _userService;

}