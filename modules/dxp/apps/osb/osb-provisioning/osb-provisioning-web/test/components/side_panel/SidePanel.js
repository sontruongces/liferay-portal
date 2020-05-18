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

import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import SidePanel from '../../../src/main/resources/META-INF/resources/js/components/side_panel/SidePanel';

function renderSidePanel() {
	return render(<SidePanel />);
}

describe('SidePanel', () => {
	beforeEach(() => {
		document.body.innerHTML = `<div id="account">dummy account node</div>`;
	});

	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSidePanel();

		expect(container).toBeTruthy();
	});

	it('has a "Notes" tab', () => {
		const {getByText} = renderSidePanel();

		getByText('notes');
	});

	it('has a "Sales Info" tab', () => {
		const {getByText} = renderSidePanel();

		getByText('sales-info');
	});

	it('has an "External Links" tab', () => {
		const {getByText} = renderSidePanel();

		getByText('external-links');
	});

	it('shows the corresponding tab content when a tab is clicked', () => {
		const {getByText} = renderSidePanel();

		fireEvent.click(getByText('sales-info'));

		expect(getByText('sales-info').className).toEqual(
			expect.stringContaining('active')
		);

		fireEvent.click(getByText('external-links'));

		expect(getByText('external-links').className).toEqual(
			expect.stringContaining('active')
		);
	});

	it('shows an expand button when collapsed', () => {
		const {getByLabelText} = renderSidePanel();

		fireEvent.click(getByLabelText('collapse-panel-button'));

		getByLabelText('expand-panel-button');
	});
});
