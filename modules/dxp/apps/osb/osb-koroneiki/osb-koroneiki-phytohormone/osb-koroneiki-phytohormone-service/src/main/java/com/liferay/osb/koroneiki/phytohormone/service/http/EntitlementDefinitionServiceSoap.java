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

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>EntitlementDefinitionServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinitionSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition</code>, that is translated to a
 * <code>com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinitionSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionServiceHttp
 * @generated
 */
public class EntitlementDefinitionServiceSoap {

	public static
		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinitionSoap
				addEntitlementDefinition(
					long classNameId, String name, String description,
					String definition, int status)
			throws RemoteException {

		try {
			com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				returnValue =
					EntitlementDefinitionServiceUtil.addEntitlementDefinition(
						classNameId, name, description, definition, status);

			return com.liferay.osb.koroneiki.phytohormone.model.
				EntitlementDefinitionSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static
		com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinitionSoap
				deleteEntitlementDefinition(long entitlementDefinitionId)
			throws RemoteException {

		try {
			com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition
				returnValue =
					EntitlementDefinitionServiceUtil.
						deleteEntitlementDefinition(entitlementDefinitionId);

			return com.liferay.osb.koroneiki.phytohormone.model.
				EntitlementDefinitionSoap.toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void synchronizeEntitlementDefinition(
			long entitlementDefinitionId)
		throws RemoteException {

		try {
			EntitlementDefinitionServiceUtil.synchronizeEntitlementDefinition(
				entitlementDefinitionId);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		EntitlementDefinitionServiceSoap.class);

}