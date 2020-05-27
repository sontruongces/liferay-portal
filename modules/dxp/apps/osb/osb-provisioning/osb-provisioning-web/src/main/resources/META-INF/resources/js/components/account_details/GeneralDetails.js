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
import React, {useEffect, useRef, useState} from 'react';

import {convertDashToEmptyString} from '../../utilities/helpers';
import HiddenFields from '../HiddenFields';
import DetailField from './DetailField';

function GeneralDetails({details, statuses, tiers}) {
	const refForm = useRef();

	const initialDetails = {
		code: convertDashToEmptyString(details.code),
		name: convertDashToEmptyString(details.name),
		region: convertDashToEmptyString(details.region),
		status: convertDashToEmptyString(details.status),
		tier: convertDashToEmptyString(details.tier),
		updateAccount: true
	};

	const [accountDetails, setAccountDetails] = useState(initialDetails);

	useEffect(() => {
		if (!Object.is(initialDetails, accountDetails)) {
			refForm.current.submit();
		}
	}, [accountDetails, initialDetails]);

	function handleSubmit(fieldName, value) {
		setAccountDetails({...initialDetails, [fieldName]: value});
	}

	return (
		<form
			action={details.editAccountURL}
			method="post"
			name="generalDetailsForm"
			ref={refForm}
		>
			<HiddenFields data={accountDetails} />
			<ClayList>
				<ClayList.Header>
					{Liferay.Language.get('general-details')}
				</ClayList.Header>

				<DetailField
					name={Liferay.Language.get('account-name')}
					save={handleSubmit}
				>
					{details.name}
				</DetailField>

				<DetailField
					displayAs="label"
					inputStyle={details.statusStyle}
					name={Liferay.Language.get('status')}
					options={statuses}
					save={handleSubmit}
					type="select"
				>
					{details.status}
				</DetailField>

				<DetailField
					name={Liferay.Language.get('code')}
					save={handleSubmit}
				>
					{details.code}
				</DetailField>

				<DetailField
					name={Liferay.Language.get('created')}
					save={handleSubmit}
					type="noneditable"
				>
					{details.dateCreated}
				</DetailField>

				<DetailField
					name={Liferay.Language.get('tier')}
					options={tiers}
					save={handleSubmit}
					type="select"
				>
					{details.tier}
				</DetailField>

				<DetailField
					name={Liferay.Language.get('last-modified')}
					save={handleSubmit}
					type="noneditable"
				>
					{details.dateModified}
				</DetailField>

				<ClayList.Header>
					{Liferay.Language.get('partner-info')}
				</ClayList.Header>

				<DetailField
					name={Liferay.Language.get('partner-reseller-si')}
					save={handleSubmit}
					type="external"
				>
					{details.partnerTeamName}
				</DetailField>

				<DetailField
					name={Liferay.Language.get('first-line-support')}
					save={handleSubmit}
					type="toggle"
				>
					{details.firstLineSupportTeamName}
				</DetailField>
			</ClayList>
		</form>
	);
}

GeneralDetails.propTypes = {
	details: PropTypes.shape({
		code: PropTypes.string,
		dateCreated: PropTypes.string,
		dateModified: PropTypes.string,
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		parterTeamName: PropTypes.string,
		status: PropTypes.string,
		statusStyle: PropTypes.string,
		tier: PropTypes.string
	}),
	statuses: PropTypes.arrayOf(PropTypes.string),
	tiers: PropTypes.arrayOf(PropTypes.string)
};

export default GeneralDetails;
