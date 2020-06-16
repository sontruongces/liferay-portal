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

import Instructions from '../../../src/main/resources/META-INF/resources/js/components/support_information/Instructions';

function renderInstructions() {
	return render(
		<Instructions updateInstructionsURL="update/instructions/url" />
	);
}

describe('SupportInformation', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderInstructions();

		expect(container).toBeTruthy();
	});

	it('displays OEM Instructions field', () => {
		const {getByText} = renderInstructions();

		getByText('oem-instructions');
	});
});
