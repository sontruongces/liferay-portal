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

package com.liferay.osb.koroneiki.taproot.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AccountNote. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountNoteLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteLocalService
 * @generated
 */
public class AccountNoteLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountNoteLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the account note to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNote the account note
	 * @return the account note that was added
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
		addAccountNote(
			com.liferay.osb.koroneiki.taproot.model.AccountNote accountNote) {

		return getService().addAccountNote(accountNote);
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			addAccountNote(
				long userId, String creatorOktaId, String creatorName,
				long accountId, String type, int priority, String content,
				String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addAccountNote(
			userId, creatorOktaId, creatorName, accountId, type, priority,
			content, format, status);
	}

	/**
	 * Creates a new account note with the primary key. Does not add the account note to the database.
	 *
	 * @param accountNoteId the primary key for the new account note
	 * @return the new account note
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
		createAccountNote(long accountNoteId) {

		return getService().createAccountNote(accountNoteId);
	}

	/**
	 * Deletes the account note from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNote the account note
	 * @return the account note that was removed
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
		deleteAccountNote(
			com.liferay.osb.koroneiki.taproot.model.AccountNote accountNote) {

		return getService().deleteAccountNote(accountNote);
	}

	/**
	 * Deletes the account note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note that was removed
	 * @throws PortalException if a account note with the primary key could not be found
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			deleteAccountNote(long accountNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAccountNote(accountNoteId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
		fetchAccountNote(long accountNoteId) {

		return getService().fetchAccountNote(accountNoteId);
	}

	/**
	 * Returns the account note with the matching UUID and company.
	 *
	 * @param uuid the account note's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
		fetchAccountNoteByUuidAndCompanyId(String uuid, long companyId) {

		return getService().fetchAccountNoteByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns the account note with the primary key.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note
	 * @throws PortalException if a account note with the primary key could not be found
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			getAccountNote(long accountNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountNote(accountNoteId);
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			getAccountNote(String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountNote(accountNoteKey);
	}

	/**
	 * Returns the account note with the matching UUID and company.
	 *
	 * @param uuid the account note's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account note
	 * @throws PortalException if a matching account note could not be found
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			getAccountNoteByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountNoteByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of account notes
	 */
	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.AccountNote> getAccountNotes(
			int start, int end) {

		return getService().getAccountNotes(start, end);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.AccountNote> getAccountNotes(
			long accountId, int start, int end) {

		return getService().getAccountNotes(accountId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.koroneiki.taproot.model.AccountNote> getAccountNotes(
			long accountId, String[] types, String[] statuses, int start,
			int end) {

		return getService().getAccountNotes(
			accountId, types, statuses, start, end);
	}

	/**
	 * Returns the number of account notes.
	 *
	 * @return the number of account notes
	 */
	public static int getAccountNotesCount() {
		return getService().getAccountNotesCount();
	}

	public static int getAccountNotesCount(long accountId) {
		return getService().getAccountNotesCount(accountId);
	}

	public static int getAccountNotesCount(
		long accountId, String[] types, String[] statuses) {

		return getService().getAccountNotesCount(accountId, types, statuses);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNote the account note
	 * @return the account note that was updated
	 */
	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
		updateAccountNote(
			com.liferay.osb.koroneiki.taproot.model.AccountNote accountNote) {

		return getService().updateAccountNote(accountNote);
	}

	public static com.liferay.osb.koroneiki.taproot.model.AccountNote
			updateAccountNote(
				long accountNoteId, String modifierOktaId, String modifierName,
				int priority, String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAccountNote(
			accountNoteId, modifierOktaId, modifierName, priority, content,
			format, status);
	}

	public static AccountNoteLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AccountNoteLocalService, AccountNoteLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AccountNoteLocalService.class);

		ServiceTracker<AccountNoteLocalService, AccountNoteLocalService>
			serviceTracker =
				new ServiceTracker
					<AccountNoteLocalService, AccountNoteLocalService>(
						bundle.getBundleContext(),
						AccountNoteLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}