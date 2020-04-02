import {cleanup, fireEvent, render} from '@testing-library/react';
import React from 'react';

import NewNote from '../../src/main/resources/META-INF/resources/js/components/NewNote';

function renderNewNote() {
	return render(<NewNote addURL="/" />);
}

describe('New Note', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNewNote();

		expect(container).toBeTruthy();
	});

	it('displays a textarea for adding new notes', () => {
		const {getByPlaceholderText} = renderNewNote();

		getByPlaceholderText('write-a-note');
	});

	it('displays a cancel button when the textarea is focused', () => {
		const {container, getByText} = renderNewNote();

		fireEvent.click(container.querySelector('textarea'));

		getByText('cancel');
	});

	it('displays a save button when the textarea is focused', () => {
		const {container, getByText} = renderNewNote();

		fireEvent.click(container.querySelector('textarea'));

		getByText('save');
	});

	it('clears the textarea when cancel button is pressed', () => {
		const {container, getByText} = renderNewNote();

		const textarea = container.querySelector('textarea');

		fireEvent.click(textarea);
		fireEvent.change(textarea, {
			target: {value: 'test'}
		});
		fireEvent.click(getByText('cancel'));

		expect(textarea.value).toMatch('');
	});
});
