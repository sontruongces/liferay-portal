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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import AccountAddress from './AccountAddress';

function AccountDetails({details}) {
	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('general-details')}
			</ClayList.Header>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('account-name')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>{details.name}</ClayList.ItemText>
				</div>

				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('status')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						<span className={`label ${details.statusStyle}`}>
							{details.status}
						</span>
					</ClayList.ItemText>
				</div>
			</ClayList.Item>

			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('code')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>{details.code}</ClayList.ItemText>
				</div>

				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('created')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>{details.dateCreated}</ClayList.ItemText>
				</div>
			</ClayList.Item>

			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('tier')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>{details.tier}</ClayList.ItemText>
				</div>

				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('last-modified')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.dateModified}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>

			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('partner-reseller-si')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.partnerTeamName}
					</ClayList.ItemText>
				</div>

				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('first-line-support')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.firstLineSupportTeamName}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>

			<AccountAddress
				addresses={details.postalAddressDisplays}
				addURL={details.addPostalAddressURL}
			/>

			<ClayList.Header>
				{Liferay.Language.get('external-account-keys')}
			</ClayList.Header>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('dossiera-account')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.dossieraAccountKey}
					</ClayList.ItemText>
				</div>

				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('dossiera-project')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.dossieraProjectKey}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>

			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('salesforce-project')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.salesforceProjectKey}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>
		</ClayList>
	);
}

AccountDetails.propTypes = {
	details: PropTypes.shape({
		addPostalAddressURL: PropTypes.string,
		code: PropTypes.string,
		dateCreated: PropTypes.string,
		dateModified: PropTypes.string,
		dossieraAccountKey: PropTypes.string,
		dossieraProjectKey: PropTypes.string,
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		parterTeamName: PropTypes.string,
		postalAddressDisplays: PropTypes.arrayOf(
			PropTypes.shape({
				addressCountry: PropTypes.string,
				addressLocality: PropTypes.string,
				addressRegion: PropTypes.string,
				addressType: PropTypes.string,
				deletePostalAddressURL: PropTypes.string,
				editPostalAddressURL: PropTypes.string,
				id: PropTypes.string,
				postalCode: PropTypes.string,
				primary: PropTypes.string,
				streetAddressLine1: PropTypes.string,
				streetAddressLine2: PropTypes.string,
				streetAddressLine3: PropTypes.string
			})
		),
		salesforceProjectKey: PropTypes.string,
		status: PropTypes.string,
		statusStyle: PropTypes.string,
		tier: PropTypes.string
	})
};

export default AccountDetails;
