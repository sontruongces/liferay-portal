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

function GeneralDetails({details}) {
	return (
		<>
			<ClayList.Header>
				{Liferay.Language.get('general-details')}
			</ClayList.Header>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('account-name')}
					</ClayList.ItemTitle>

					<div className="list-group-text">
						<InlineEdit fieldName="name">{details.name}</InlineEdit>
					</div>
				</div>
			</ClayList.Item>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('status')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						<span className={`label ${details.statusStyle}`}>
							{details.status}
						</span>
					</ClayList.ItemText>
				</div>
			</ClayList.Item>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('code')}
					</ClayList.ItemTitle>

					<div className="list-group-text">
						<InlineEdit fieldName="code">{details.code}</InlineEdit>
					</div>
				</div>
			</ClayList.Item>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('created')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>{details.dateCreated}</ClayList.ItemText>
				</div>
			</ClayList.Item>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('tier')}
					</ClayList.ItemTitle>

					<div className="list-group-text">
						<InlineEdit fieldName="tier">{details.tier}</InlineEdit>
					</div>
				</div>
			</ClayList.Item>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('last-modified')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.dateModified}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>

			<ClayList.Header>
				{Liferay.Language.get('partner-info')}
			</ClayList.Header>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('partner-reseller-si')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.partnerTeamName}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>
			<ClayList.Item flex>
				<div className="account-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('first-line-support')}
					</ClayList.ItemTitle>
					<ClayList.ItemText>
						{details.firstLineSupportTeamName}
					</ClayList.ItemText>
				</div>
			</ClayList.Item>
		</>
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
