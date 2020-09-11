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

package com.liferay.osb.koroneiki.taproot.service.http;

import com.liferay.osb.koroneiki.taproot.service.AccountNoteServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AccountNoteServiceUtil</code> service
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
 * @see AccountNoteServiceSoap
 * @generated
 */
public class AccountNoteServiceHttp {

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			addAccountNote(
				HttpPrincipal httpPrincipal, String creatorOktaId,
				String creatorName, long accountId, String type, int priority,
				String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountNoteServiceUtil.class, "addAccountNote",
				_addAccountNoteParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, creatorOktaId, creatorName, accountId, type,
				priority, content, format, status);

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

			return (com.liferay.osb.koroneiki.taproot.model.AccountNote)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			deleteAccountNote(
				HttpPrincipal httpPrincipal, String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountNoteServiceUtil.class, "deleteAccountNote",
				_deleteAccountNoteParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountNoteKey);

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

			return (com.liferay.osb.koroneiki.taproot.model.AccountNote)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			getAccountNote(HttpPrincipal httpPrincipal, String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountNoteServiceUtil.class, "getAccountNote",
				_getAccountNoteParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountNoteKey);

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

			return (com.liferay.osb.koroneiki.taproot.model.AccountNote)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.AccountNote> getAccountNotes(
				HttpPrincipal httpPrincipal, long accountId, String[] types,
				int[] priorities, String[] statuses, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountNoteServiceUtil.class, "getAccountNotes",
				_getAccountNotesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, types, priorities, statuses, start, end);

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

			return (java.util.List
				<com.liferay.osb.koroneiki.taproot.model.AccountNote>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getAccountNotesCount(
			HttpPrincipal httpPrincipal, long accountId, String[] types,
			int[] priorities, String[] statuses)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountNoteServiceUtil.class, "getAccountNotesCount",
				_getAccountNotesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountId, types, priorities, statuses);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			updateAccountNote(
				HttpPrincipal httpPrincipal, long accountNoteId,
				String modifierOktaId, String modifierName, int priority,
				String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountNoteServiceUtil.class, "updateAccountNote",
				_updateAccountNoteParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountNoteId, modifierOktaId, modifierName,
				priority, content, format, status);

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

			return (com.liferay.osb.koroneiki.taproot.model.AccountNote)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountNoteServiceHttp.class);

	private static final Class<?>[] _addAccountNoteParameterTypes0 =
		new Class[] {
			String.class, String.class, long.class, String.class, int.class,
			String.class, String.class, String.class
		};
	private static final Class<?>[] _deleteAccountNoteParameterTypes1 =
		new Class[] {String.class};
	private static final Class<?>[] _getAccountNoteParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[] _getAccountNotesParameterTypes3 =
		new Class[] {
			long.class, String[].class, int[].class, String[].class, int.class,
			int.class
		};
	private static final Class<?>[] _getAccountNotesCountParameterTypes4 =
		new Class[] {long.class, String[].class, int[].class, String[].class};
	private static final Class<?>[] _updateAccountNoteParameterTypes5 =
		new Class[] {
			long.class, String.class, String.class, int.class, String.class,
			String.class, String.class
		};

}