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

function AccountAddress({addresses}) {
	if (addresses.length === 0) {
		addresses.push({
			addressCountry: '-',
			addressLocality: '-',
			addressRegion: '-',
			id: 'default',
			postalCode: '-',
			streetAddressLine1: '-',
			streetAddressLine2: '-',
			streetAddressLine3: '-'
		});
	}

	return (
		<>
			{addresses.map((address, index) => (
				<React.Fragment key={address.id}>
					<ClayList.Header>
						{Liferay.Language.get('address')} {index + 1}
					</ClayList.Header>
					<ClayList.Item flex>
						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('street-1')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.streetAddressLine1}
							</ClayList.ItemText>
						</div>

						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('city')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.addressLocality}
							</ClayList.ItemText>
						</div>
					</ClayList.Item>
					<ClayList.Item flex>
						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('street-2')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.streetAddressLine2}
							</ClayList.ItemText>
						</div>

						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('state-province')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.addressRegion}
							</ClayList.ItemText>
						</div>
					</ClayList.Item>
					<ClayList.Item flex>
						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('street-3')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.streetAddressLine3}
							</ClayList.ItemText>
						</div>

						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('postal-code')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.postalCode}
							</ClayList.ItemText>
						</div>
					</ClayList.Item>
					<ClayList.Item flex>
						<div className="account-field">
							<ClayList.ItemTitle>
								{Liferay.Language.get('country')}
							</ClayList.ItemTitle>
							<ClayList.ItemText>
								{address.addressCountry}
							</ClayList.ItemText>
						</div>
					</ClayList.Item>
				</React.Fragment>
			))}
		</>
	);
}

AccountAddress.propTypes = {
	addresses: PropTypes.arrayOf(
		PropTypes.shape({
			addressCountry: PropTypes.string,
			addressLocality: PropTypes.string,
			id: PropTypes.string,
			postalCode: PropTypes.string,
			streetAddressLine1: PropTypes.string,
			streetAddressLine2: PropTypes.string,
			streetAddressLine3: PropTypes.string
		})
	)
};

export default AccountAddress;
