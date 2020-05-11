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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import AccountAddress from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountAddress';

function renderAccountAddress(props) {
	return render(
		<AccountAddress
			addresses={[
				{
					addressCountry: 'USA',
					addressLocality: 'Diamond Bar',
					addressRegion: 'CA',
					id: '123',
					postalCode: '91765',
					streetAddressLine1: '1400 Montefino Ave',
					streetAddressLine2: '-',
					streetAddressLine3: '-'
				},
				{
					addressCountry: 'United Arab Emirates',
					addressLocality: 'Dubai Media City',
					addressRegion: '-',
					id: '456',
					postalCode: '-',
					streetAddressLine1: 'Building 8',
					streetAddressLine2: 'Office 207',
					streetAddressLine3: '-'
				}
			]}
			{...props}
		/>
	);
}

describe('AccountAddress', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountAddress();

		expect(container).toBeTruthy();
	});

	it('displays an addresses List Group with dashes for each field when no addresses are provided', () => {
		const {getAllByText} = renderAccountAddress({addresses: []});

		expect(getAllByText('-').length).toBe(7);
	});

	it('displays multiple address List Groups when multiple addresses are provided', () => {
		const {getByText} = renderAccountAddress();

		getByText('address 1');
		getByText('address 2');
	});
});
