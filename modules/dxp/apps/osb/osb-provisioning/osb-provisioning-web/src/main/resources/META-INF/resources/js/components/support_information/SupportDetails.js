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

import {FIELD_TYPE_SELECT} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';

function SupportDetails({
	account,
	languageId,
	languageList,
	regionNames,
	updateAccountURL,
	updateLanguageIdURL
}) {
	const formData = {
		code: convertDashToEmptyString(account.code),
		name: convertDashToEmptyString(account.name),
		region: convertDashToEmptyString(account.region),
		status: convertDashToEmptyString(account.status),
		tier: convertDashToEmptyString(account.tier),
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
			<li className="list-group-item list-group-item-flex list-group-subheader">
				<div className="autofit-col autofit-col-expand">
					{Liferay.Language.get('details')}
				</div>
			</li>

			<DetailField
				fieldLabel={Liferay.Language.get('support-region')}
				fieldName="region"
				formAction={updateAccountURL}
				formData={formData}
				options={createSelectOptions(regionNames)}
				type={FIELD_TYPE_SELECT}
				value={account.region}
			/>

			<DetailField
				fieldLabel={Liferay.Language.get('support-language')}
				fieldName="languageId"
				formAction={updateLanguageIdURL}
				formData={{languageId}}
				options={languageList.map(({languageId, languageName}) => ({
					label: languageName,
					value: languageId
				}))}
				type={FIELD_TYPE_SELECT}
				value={languageId}
			/>
		</ClayList>
	);
}

SupportDetails.propTypes = {
	account: PropTypes.shape({
		code: PropTypes.string,
		editAccountURL: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		region: PropTypes.string,
		status: PropTypes.string,
		tier: PropTypes.string
	}),
	languageId: PropTypes.string,
	languageList: PropTypes.arrayOf(
		PropTypes.shape({
			languageId: PropTypes.string,
			languageName: PropTypes.string
		})
	),
	regionNames: PropTypes.arrayOf(PropTypes.string),
	updateAccountURL: PropTypes.string,
	updateLanguageIdURL: PropTypes.string
};

export default SupportDetails;
