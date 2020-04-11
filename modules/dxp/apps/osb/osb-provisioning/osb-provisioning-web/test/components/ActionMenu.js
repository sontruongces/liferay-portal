import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ActionMenu from '../../src/main/resources/META-INF/resources/js/components/ActionMenu';

function renderActionMenu(props) {
	return render(
		<ActionMenu onEdit={jest.fn()} onPinning={jest.fn()} {...props} />
	);
}

describe('ActionMenu', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderActionMenu();

		expect(container).toBeTruthy();
	});

	it('displays an edit icon', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('edit-note-icon');
	});

	it('displays a pin icon if note has not been pinned on Genearl Notes tab', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('pin-note-icon');
	});

	it('displays an unpin icon if note has been pinned on Genearl Notes tab', () => {
		const {getByLabelText} = renderActionMenu({pinned: true});

		getByLabelText('unpin-note-icon');
	});

	it('displays no pin or unpin icon on Sales Info tab', () => {
		const {queryByLabelText} = renderActionMenu({tabType: 'Sales'});

		expect(queryByLabelText('pin-note-icon')).toBeFalsy();
		expect(queryByLabelText('unpin-note-icon')).toBeFalsy();
	});
});
