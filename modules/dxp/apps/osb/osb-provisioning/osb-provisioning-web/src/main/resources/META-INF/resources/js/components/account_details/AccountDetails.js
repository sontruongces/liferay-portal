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
import ExternalAccountKeys from './ExternalAccountKeys';
import GeneralDetails from './GeneralDetails';

function AccountDetails({details, statusNames, tierNames}) {
	return (
		<ClayList>
			<GeneralDetails
				details={details}
				statuses={statusNames}
				tiers={tierNames}
			/>

			<AccountAddress
				addresses={details.postalAddressDisplays}
				addURL={details.addPostalAddressURL}
			/>

			<ExternalAccountKeys details={details} />
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
	}),
	statusNames: PropTypes.arrayOf(PropTypes.string),
	tierNames: PropTypes.arrayOf(PropTypes.string)
};

export default AccountDetails;
