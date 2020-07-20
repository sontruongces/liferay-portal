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
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	NAMESPACE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';

function GeneralDetails({details, parentAccountName, statuses, tiers}) {
	const formData = {
		code: convertDashToEmptyString(details.code),
		name: convertDashToEmptyString(details.name),
		region: convertDashToEmptyString(details.region),
		status: convertDashToEmptyString(details.status),
		tier: convertDashToEmptyString(details.tier),
		updateAccount: true
	};

	function createSelectOptions(array) {
		return array.map(value => {
			return {
				label: value,
				value
			};
		});
	}

	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('general-details')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('account-name')}
				fieldName="name"
				formAction={details.editAccountURL}
				formData={formData}
				value={details.name}
			/>

			<DetailField
				displayAs="label"
				fieldLabel={Liferay.Language.get('status')}
				fieldName="status"
				formAction={details.editAccountURL}
				formData={formData}
				inputStyle={details.statusStyle}
				options={createSelectOptions(statuses)}
				type={FIELD_TYPE_SELECT}
				value={details.status}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('code')}
				fieldName="code"
				formAction={details.editAccountURL}
				formData={formData}
				value={details.code}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('created')}
				type={FIELD_TYPE_NONEDITABLE}
				value={details.dateCreated}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('tier')}
				fieldName="tier"
				formAction={details.editAccountURL}
				formData={formData}
				options={createSelectOptions(tiers)}
				type={FIELD_TYPE_SELECT}
				value={details.tier}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('last-modified')}
				type={FIELD_TYPE_NONEDITABLE}
				value={details.dateModified}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('parent')}
				openExternalFn={window[`${NAMESPACE}editAccountHierarchy`]}
				type={FIELD_TYPE_EXTERNAL}
				value={parentAccountName}
			/>
		</ClayList>
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
		region: PropTypes.string,
		status: PropTypes.string,
		statusStyle: PropTypes.string,
		tier: PropTypes.string
	}),
	parentAccountName: PropTypes.string,
	statuses: PropTypes.arrayOf(PropTypes.string).isRequired,
	tiers: PropTypes.arrayOf(PropTypes.string).isRequired
};

export default GeneralDetails;
