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

import {FIELD_TYPE_EXTERNAL, NAMESPACE} from '../../utilities/constants';
import DetailField from '../DetailField';

function PartnerInfo({
	details,
	editFirstLineSupportTeamURL = '',
	editPartnerTeamURL = ''
}) {
	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>

			<DetailField
				externalData={{
					formField: 'partnerTeamKey',
					formName: `${NAMESPACE}updatePartnerFm`,
					title: Liferay.Language.get('select-partner-team'),
					url: editPartnerTeamURL
				}}
				fieldLabel={Liferay.Language.get('partner-reseller-si')}
				type={FIELD_TYPE_EXTERNAL}
				value={details.partnerTeamName}
			/>

			<DetailField
				externalData={{
					formField: 'firstLineSupportTeamKey',
					formName: `${NAMESPACE}updateFirstLineSupportFm`,
					title: Liferay.Language.get(
						'select-first-line-support-team'
					),
					url: editFirstLineSupportTeamURL
				}}
				fieldLabel={Liferay.Language.get('first-line-support')}
				type={FIELD_TYPE_EXTERNAL}
				value={details.firstLineSupportTeamName}
			/>
		</ClayList>
	);
}

PartnerInfo.propTypes = {
	details: PropTypes.shape({
		firstLineSupportTeamName: PropTypes.string,
		key: PropTypes.string,
		partnerTeamName: PropTypes.string
	}),
	editFirstLineSupportTeamURL: PropTypes.string,
	editPartnerTeamURL: PropTypes.string
};

export default PartnerInfo;
