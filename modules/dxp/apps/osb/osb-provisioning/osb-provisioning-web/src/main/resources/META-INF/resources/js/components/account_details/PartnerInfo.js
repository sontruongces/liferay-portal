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

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_TOGGLE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from './DetailField';

function GeneralDetails({details}) {
	const formData = {
		code: convertDashToEmptyString(details.code),
		name: convertDashToEmptyString(details.name),
		region: convertDashToEmptyString(details.region),
		status: convertDashToEmptyString(details.status),
		tier: convertDashToEmptyString(details.tier),
		updateAccount: true
	};

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('partner-reseller-si')}
				fieldName="partnerTeamName"
				formAction={details.editAccountURL}
				formData={formData}
				type={FIELD_TYPE_EXTERNAL}
			>
				{details.partnerTeamName}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('first-line-support')}
				fieldName="firstLineSupportTeamName"
				formAction={details.editAccountURL}
				formData={formData}
				type={FIELD_TYPE_TOGGLE}
			>
				{details.firstLineSupportTeamName}
			</DetailField>
		</ClayList>
	);
}

GeneralDetails.propTypes = {
	details: PropTypes.shape({
		editAccountURL: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		parterTeamName: PropTypes.string
	})
};

export default GeneralDetails;
