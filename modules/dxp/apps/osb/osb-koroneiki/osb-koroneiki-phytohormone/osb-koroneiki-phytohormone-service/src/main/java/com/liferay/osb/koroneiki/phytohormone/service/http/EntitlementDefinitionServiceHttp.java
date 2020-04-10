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

package com.liferay.osb.koroneiki.phytohormone.service.http;

import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>EntitlementDefinitionServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionServiceSoap
 * @generated
 */
public class EntitlementDefinitionServiceHttp {

	public static
		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				addEntitlementDefinition(
					HttpPrincipal httpPrincipal, long classNameId, String name,
					String description, String definition, int status)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				EntitlementDefinitionServiceUtil.class,
				"addEntitlementDefinition",
				_addEntitlementDefinitionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, classNameId, name, description, definition, status);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.phytohormone.model.
				EntitlementDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				deleteEntitlementDefinition(
					HttpPrincipal httpPrincipal, long entitlementDefinitionId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				EntitlementDefinitionServiceUtil.class,
				"deleteEntitlementDefinition",
				_deleteEntitlementDefinitionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, entitlementDefinitionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.koroneiki.phytohormone.model.
				EntitlementDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void synchronizeEntitlementDefinition(
			HttpPrincipal httpPrincipal, long entitlementDefinitionId)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				EntitlementDefinitionServiceUtil.class,
				"synchronizeEntitlementDefinition",
				_synchronizeEntitlementDefinitionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, entitlementDefinitionId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		EntitlementDefinitionServiceHttp.class);

	private static final Class<?>[] _addEntitlementDefinitionParameterTypes0 =
		new Class[] {
			long.class, String.class, String.class, String.class, int.class
		};
	private static final Class<?>[]
		_deleteEntitlementDefinitionParameterTypes1 = new Class[] {long.class};
	private static final Class<?>[]
		_synchronizeEntitlementDefinitionParameterTypes2 = new Class[] {
			long.class
		};

}