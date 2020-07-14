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

import PartnerInfo from '../../../src/main/resources/META-INF/resources/js/components/account_details/PartnerInfo';

function renderPartnerInfo(props) {
	return render(
		<PartnerInfo
			details={{
				editAccountURL: 'edit/account/url',
				firstLineSupportTeamKey: 'first-line-123',
				firstLineSupportTeamName: 'Test Support Team',
				key: '123',
				partnerTeamKey: 'partner-123',
				partnerTeamName: 'Test Partner Team'
			}}
			{...props}
		/>
	);
}

describe('PartnerInfo', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderPartnerInfo();

		expect(container).toBeTruthy();
	});
});
