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

	it('displays a pin icon if note has not been pinned', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('pin-note-icon');
	});

	it('displays an unpin icon if note has been pinned', () => {
		const {getByLabelText} = renderActionMenu({pinned: true});

		getByLabelText('unpin-note-icon');
	});
});
