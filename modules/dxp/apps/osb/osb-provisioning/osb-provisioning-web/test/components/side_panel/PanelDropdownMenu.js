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

import PanelDropdownMenu from '../../../src/main/resources/META-INF/resources/js/components/side_panel/PanelDropdownMenu';
import {
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_SALES
} from '../../../src/main/resources/META-INF/resources/js/utilities/constants';

const mockOnArchiveFn = jest.fn();
const mockOnEditFn = jest.fn();
const mockOnPinningFn = jest.fn();

function renderPanelDropdownMenu(props) {
	return render(
		<PanelDropdownMenu
			id="123"
			onArchive={mockOnArchiveFn}
			onEdit={mockOnEditFn}
			onPinning={mockOnPinningFn}
			{...props}
		/>
	);
}

describe('PanelDropdownMenu', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderPanelDropdownMenu();

		expect(container).toBeTruthy();
	});

	it('shows "Edit" as a dropdown menu option for an unarchived note', () => {
		const {getByLabelText, getByText} = renderPanelDropdownMenu();

		fireEvent.click(getByLabelText('action-menu-icon'));

		getByText('edit');
	});

	it('shows "Pin" as a dropdown menu option for an unarchived note that has not been pinned on General tab', () => {
		const {
			getByLabelText,
			getByText,
			queryByText
		} = renderPanelDropdownMenu();

		fireEvent.click(getByLabelText('action-menu-icon'));

		getByText('pin');
		expect(queryByText('unpin')).toBeFalsy();
	});

	it('shows "Unpin" as a dropdown menu option for an unarchived note that has been pinned on General tab', () => {
		const {
			getByLabelText,
			getByText,
			queryByText
		} = renderPanelDropdownMenu({
			pinned: true
		});

		fireEvent.click(getByLabelText('action-menu-icon'));

		getByText('unpin');
		expect(queryByText('pin')).toBeFalsy();
	});

	it('shows no "Pin" or "Unpin" dropdown option for an unarchived note on Sales Info tab', () => {
		const {getByLabelText, queryByText} = renderPanelDropdownMenu({
			tabType: NOTE_TYPE_SALES
		});

		fireEvent.click(getByLabelText('action-menu-icon'));

		expect(queryByText('pin')).toBeFalsy();
		expect(queryByText('unpin')).toBeFalsy();
	});

	it('shows "Archive" as a dropdown menu option for an unarchived note', () => {
		const {
			getByLabelText,
			getByText,
			queryByText
		} = renderPanelDropdownMenu();

		fireEvent.click(getByLabelText('action-menu-icon'));

		getByText('archive');
		expect(queryByText('unarchive')).toBeFalsy();
	});

	it('shows only the "Unarchive" dropdown menu option for an archived note', () => {
		const {
			getByLabelText,
			getByText,
			queryByText
		} = renderPanelDropdownMenu({
			status: NOTE_STATUS_ARCHIVED
		});

		fireEvent.click(getByLabelText('action-menu-icon'));

		getByText('unarchive');
		expect(queryByText('archive')).toBeFalsy();
		expect(queryByText('edit')).toBeFalsy();
		expect(queryByText('pin')).toBeFalsy();
		expect(queryByText('unpin')).toBeFalsy();
	});

	it('calls onArchive function when the "Archive" menu item has been clicked', () => {
		const {getByLabelText, getByText} = renderPanelDropdownMenu();

		fireEvent.click(getByLabelText('action-menu-icon'));
		fireEvent.click(getByText('archive'));

		expect(mockOnArchiveFn).toHaveBeenCalled();
	});

	it('calls onEdit function when the "Edit" menu item has been clicked', () => {
		const {getByLabelText, getByText} = renderPanelDropdownMenu();

		fireEvent.click(getByLabelText('action-menu-icon'));
		fireEvent.click(getByText('edit'));

		expect(mockOnEditFn).toHaveBeenCalled();
	});

	it('calls onPinning function when the "Pin" menu item has been clicked', () => {
		const {getByLabelText, getByText} = renderPanelDropdownMenu();

		fireEvent.click(getByLabelText('action-menu-icon'));
		fireEvent.click(getByText('pin'));

		expect(mockOnPinningFn).toHaveBeenCalled();
	});
});
