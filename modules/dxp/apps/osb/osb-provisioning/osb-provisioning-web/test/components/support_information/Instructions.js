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

import Instructions from '../../../src/main/resources/META-INF/resources/js/components/support_information/Instructions';

function renderInstructions(props) {
	return render(
		<Instructions
			accountAttachmentURL="account/attachment/url"
			fileName="OEM instruction file"
			instructions="Sample support instructions text"
			updateAccountAttachmentURL="update/account/attachment/URL"
			updateInstructionsURL="update/instructions/url"
			{...props}
		/>
	);
}

describe('Instructions', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderInstructions();

		expect(container).toBeTruthy();
	});

	it('displays Instructions title', () => {
		const {getByText} = renderInstructions();

		getByText('oem-instructions');
		getByText('support-instructions');
	});

	it('displays Support Instructions text', () => {
		const {getByText} = renderInstructions();

		getByText('Sample support instructions text');
	});

	it('shows Support Instructions as editable when clicked on', () => {
		const {getByText} = renderInstructions();

		fireEvent.click(getByText('Sample support instructions text'));

		getByText('save');
		getByText('cancel');
	});

	it('shows OEM instructions file when one is provided', () => {
		const {getByText} = renderInstructions();

		getByText('OEM instruction file');
	});

	it('shows no OEM instructions file when one is not provided', () => {
		const {container} = renderInstructions({fileName: ''});

		expect(container.querySelector('a')).toBe(null);
	});
});
