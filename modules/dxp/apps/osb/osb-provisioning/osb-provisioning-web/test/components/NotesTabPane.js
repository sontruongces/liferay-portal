import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import NotesTabPane from '../../src/main/resources/META-INF/resources/js/components/NotesTabPane';

function renderNotesTabPane() {
	return render(<NotesTabPane notes={{}} />);
}

describe('NotesTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNotesTabPane();

		expect(container).toBeTruthy();
	});

	it('displays a message when there is no data', () => {
		const {container} = render(<NotesTabPane />);

		expect(container.textContent).toEqual('no-notes-were-found');
	});
});
