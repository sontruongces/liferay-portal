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

import ExternalAccountKeys from '../../../src/main/resources/META-INF/resources/js/components/account_details/ExternalAccountKeys';

function renderExternalAccountKeys(props) {
	return render(
		<ExternalAccountKeys
			details={{
				dossieraAccountKey: 'testDossieraAccountKey',
				dossieraProjectKey: 'testDossieraProjectKey',
				key: '123',
				salesforceProjectKey: 'testSalesForceProjectKey',
				updateDossieraAccountURL: '/update/dossiera/account',
				updateDossieraProjectURL: '/update/dossiera/project',
				updateSalesforceProjectURL: 'update/salesforce/project'
			}}
			{...props}
		/>
	);
}

describe('ExternalAccountKeys', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExternalAccountKeys();

		expect(container).toBeTruthy();
	});

	it('displays Dossiera Account field with the correct value', () => {
		const {getByText} = renderExternalAccountKeys();

		getByText('dossiera-account');
		getByText('testDossieraAccountKey');
	});

	it('displays Dossiera Project field with the correct value', () => {
		const {getByText} = renderExternalAccountKeys();

		getByText('dossiera-project');
		getByText('testDossieraProjectKey');
	});

	it('displays Salesforce Project field with the correct value', () => {
		const {getByText} = renderExternalAccountKeys();

		getByText('salesforce-project');
		getByText('testSalesForceProjectKey');
	});
});
