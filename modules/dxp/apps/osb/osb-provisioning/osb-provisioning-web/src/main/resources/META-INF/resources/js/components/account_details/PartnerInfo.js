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

import {FIELD_TYPE_EXTERNAL} from '../../utilities/constants';
import DetailField from '../DetailField';

function PartnerInfo({details}) {
	const partnerFormData = {
		partnerTeamKey: details.partnerTeamKey,
		updatePartner: true
	};

	const firstLineSupportFormData = {
		firstLineSupportTeamKey: details.firstLineSupportTeamKey,
		updateFirstLineSupport: true
	};

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>

			<DetailField
				displayValue={details.partnerTeamName}
				fieldLabel={Liferay.Language.get('partner-reseller-si')}
				fieldName="partnerTeamKey"
				formAction={details.editAccountURL}
				formData={partnerFormData}
				type={FIELD_TYPE_EXTERNAL}
				value={details.partnerTeamKey}
			/>

			<DetailField
				displayValue={details.firstLineSupportTeamName}
				fieldLabel={Liferay.Language.get('first-line-support')}
				fieldName="firstLineSupportTeamKey"
				formAction={details.editAccountURL}
				formData={firstLineSupportFormData}
				type={FIELD_TYPE_EXTERNAL}
				value={details.firstLineSupportTeamKey}
			/>
		</ClayList>
	);
}

PartnerInfo.propTypes = {
	details: PropTypes.shape({
		editAccountURL: PropTypes.string,
		firstLineSupportTeamKey: PropTypes.string,
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		partnerTeamKey: PropTypes.string,
		partnerTeamName: PropTypes.string
	})
};

export default PartnerInfo;
