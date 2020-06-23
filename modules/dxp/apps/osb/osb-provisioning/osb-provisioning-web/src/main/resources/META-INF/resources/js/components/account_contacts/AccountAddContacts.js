/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {NAMESPACE} from '../../utilities/constants';
import ContactLine from './ContactLine';

const ContactsContext = React.createContext();

export default function AccountAddContacts({
	accountName,
	allContactRoles,
	initialContactRoleKeys = [],
	redirect,
	userEmailAddress,
	userFullName
}) {
	const [emailAddress, setEmailAddress] = useState(userEmailAddress);
	const [contactRoleKeys, setContactRoleKeys] = useState(
		initialContactRoleKeys
	);

	const disableEmail = !!(
		userFullName &&
		userEmailAddress &&
		initialContactRoleKeys.length !== 0
	);

	return (
		<>
			<input
				name={`${NAMESPACE}addContactRoleKeys`}
				type="hidden"
				value={contactRoleKeys.join(',')}
			/>
			<input
				name={`${NAMESPACE}deleteContactRoleKeys`}
				type="hidden"
				value={allContactRoles
					.map(item => item.key)
					.filter(key => !contactRoleKeys.includes(key))
					.join(',')}
			/>

			<table className="table table-autofit table-list table-nowrap">
				<thead>
					<tr>
						{disableEmail && (
							<th className="table-cell-expand">
								<span className="text-truncate-inline">
									<span className="text-secondary text-truncate">
										{Liferay.Language.get('name')}
									</span>
								</span>
							</th>
						)}
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('email')}
									{!disableEmail && (
										<span className="text-warning">
											{'*'}
										</span>
									)}
								</span>
							</span>
						</th>
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('roles')}
									<span className="text-warning">{'*'}</span>
								</span>
							</span>
						</th>
						<th className="table-cell-expand">
							<span className="text-truncate-inline">
								<span className="text-secondary text-truncate">
									{Liferay.Language.get('account')}
								</span>
							</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<ContactsContext.Provider value={allContactRoles}>
						<ContactLine
							accountName={accountName}
							contactRoleKeys={contactRoleKeys}
							disableEmail={disableEmail}
							emailAddress={emailAddress}
							setContactRoleKeys={setContactRoleKeys}
							setEmailAddress={setEmailAddress}
							userFullName={userFullName}
						/>
					</ContactsContext.Provider>
				</tbody>
			</table>

			<div className="button-row">
				<button
					className="btn btn-primary save-btn"
					disabled={!(contactRoleKeys.length > 0 && emailAddress)}
					role="button"
					type="submit"
				>
					{Liferay.Language.get('save')}
				</button>

				<a className="btn btn-secondary" href={redirect}>
					{Liferay.Language.get('cancel')}
				</a>
			</div>
		</>
	);
}

AccountAddContacts.propTypes = {
	accountName: PropTypes.string,
	allContactRoles: PropTypes.arrayOf(
		PropTypes.shape({
			key: PropTypes.string,
			name: PropTypes.string
		})
	),
	initialContactRoleKeys: PropTypes.arrayOf(PropTypes.string),
	redirect: PropTypes.string,
	userEmailAddress: PropTypes.string,
	userFullName: PropTypes.string
};

export {ContactsContext};
