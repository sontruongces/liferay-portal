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

import AccountDetails from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountDetails';

function renderAccountDetails(props) {
	return render(
		<AccountDetails
			details={{
				addPostalAddressURL: '',
				code: '123',
				dateCreated: new Date().toLocaleString('en-US'),
				dateModified: new Date().toLocaleString('en-US'),
				dossieraAccountKey: 'testDossieraAccountKey',
				dossieraProjectKey: 'testDossieraProjectKey',
				firstLineSupportTeamName: 'Test Support Team',
				key: '123',
				name: 'Test Account',
				parterTeamName: 'Test Partner Team',
				postalAddressDisplays: [],
				salesforceProjectKey: 'TestSalesForceProjectKey',
				status: 'Approved',
				statusStyle: 'label-success',
				tier: 'Regular'
			}}
			{...props}
		/>
	);
}

describe('AccountDetails', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountDetails();

		expect(container).toBeTruthy();
	});

	it('displays General Details section', () => {
		const {getByText} = renderAccountDetails();

		getByText('general-details');
	});

	it('displays Partner Info section', () => {
		const {getByText} = renderAccountDetails();

		getByText('partner-info');
	});

	it('displays Address 1 section', () => {
		const {getByText} = renderAccountDetails();

		getByText('address 1');
	});

	it('displays External Account Keys section', () => {
		const {getByText} = renderAccountDetails();

		getByText('external-account-keys');
	});
});
