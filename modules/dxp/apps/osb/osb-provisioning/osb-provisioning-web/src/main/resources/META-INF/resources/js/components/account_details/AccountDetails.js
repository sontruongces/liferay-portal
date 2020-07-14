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
import React from 'react';

import AccountAddresses from './AccountAddresses';
import ExternalAccountKeys from './ExternalAccountKeys';
import GeneralDetails from './GeneralDetails';
import PartnerInfo from './PartnerInfo';

function AccountDetails({details, parentAccountName, statusNames, tierNames}) {
	return (
		<>
			<GeneralDetails
				details={details}
				parentAccountName={parentAccountName}
				statuses={statusNames}
				tiers={tierNames}
			/>

			<PartnerInfo details={details} />

			<AccountAddresses
				accountKey={details.key}
				addresses={details.postalAddressDisplays}
				addURL={details.addPostalAddressURL}
			/>

			<ExternalAccountKeys details={details} />
		</>
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
		firstLineSupportTeamKey: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		parterTeamKey: PropTypes.string,
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
		region: PropTypes.string,
		salesforceProjectKey: PropTypes.string,
		status: PropTypes.string,
		statusStyle: PropTypes.string,
		tier: PropTypes.string,
		updateDossieraAccountURL: PropTypes.string,
		updateDossieraProjectURL: PropTypes.string,
		updateSalesforceProjectURL: PropTypes.string
	}),
	parentAccountName: PropTypes.string,
	statusNames: PropTypes.arrayOf(PropTypes.string),
	tierNames: PropTypes.arrayOf(PropTypes.string)
};

export default AccountDetails;
