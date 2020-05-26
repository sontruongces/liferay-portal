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

import InlineEdit from '../InlineEdit';

function ExternalAccountKeys({details}) {
	return (
		<>
			<ClayList.Header>
				{Liferay.Language.get('external-account-keys')}
			</ClayList.Header>

			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('dossiera-account')}
					</ClayList.ItemTitle>

					<div className="list-group-text">
						<InlineEdit>{details.dossieraAccountKey}</InlineEdit>
					</div>
				</div>
			</ClayList.Item>

			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('dossiera-project')}
					</ClayList.ItemTitle>

					<div className="list-group-text">
						<InlineEdit>{details.dossieraProjectKey}</InlineEdit>
					</div>
				</div>
			</ClayList.Item>

			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('salesforce-project')}
					</ClayList.ItemTitle>

					<div className="list-group-text">
						<InlineEdit>{details.salesforceProjectKey}</InlineEdit>
					</div>
				</div>
			</ClayList.Item>
		</>
	);
}

ExternalAccountKeys.propTypes = {
	details: PropTypes.shape({
		dossieraAccountKey: PropTypes.string,
		dossieraProjectKey: PropTypes.string,
		key: PropTypes.string,
		salesforceProjectKey: PropTypes.string
	})
};

export default ExternalAccountKeys;
