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

import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from './DetailField';

function ExternalAccountKeys({details}) {
	return (
		<ClayList>
			<ClayList.Header>
				{Liferay.Language.get('external-account-keys')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('dossiera-account')}
				fieldName="entityId"
				formAction={details.updateDossieraAccountURL}
				formData={{
					domain: 'dossiera',
					entityId: convertDashToEmptyString(
						details.dossieraAccountKey
					),
					entityName: 'account'
				}}
			>
				{details.dossieraAccountKey}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('dossiera-project')}
				fieldName="entityId"
				formAction={details.updateDossieraProjectURL}
				formData={{
					domain: 'dossiera',
					entityId: convertDashToEmptyString(
						details.dossieraProjectKey
					),
					entityName: 'project'
				}}
			>
				{details.dossieraProjectKey}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('salesforce-project')}
				fieldName="entityId"
				formAction={details.updateSalesforceProjectURL}
				formData={{
					domain: 'salesforce',
					entityId: convertDashToEmptyString(
						details.salesforceProjectKey
					),
					entityName: 'project'
				}}
			>
				{details.salesforceProjectKey}
			</DetailField>
		</ClayList>
	);
}

ExternalAccountKeys.propTypes = {
	details: PropTypes.shape({
		dossieraAccountKey: PropTypes.string,
		dossieraProjectKey: PropTypes.string,
		key: PropTypes.string,
		salesforceProjectKey: PropTypes.string,
		updateDossieraAccountURL: PropTypes.string,
		updateDossieraProjectURL: PropTypes.string,
		updateSalesforceProjectURL: PropTypes.string
	})
};

export default ExternalAccountKeys;
