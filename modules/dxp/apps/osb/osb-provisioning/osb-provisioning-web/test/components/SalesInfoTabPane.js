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

import SalesInfoTabPane from '../../src/main/resources/META-INF/resources/js/components/SalesInfoTabPane';

function renderSalesInfoTabPane() {
	return render(<SalesInfoTabPane info={{}} />);
}

describe('SalesInfoTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSalesInfoTabPane();

		expect(container).toBeTruthy();
	});

	it('displays a message when there is no data', () => {
		const {container} = render(<SalesInfoTabPane />);

		expect(container.textContent).toEqual('no-sales-info-were-found');
	});
});
