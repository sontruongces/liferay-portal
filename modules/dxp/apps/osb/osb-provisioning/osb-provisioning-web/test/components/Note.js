import {cleanup, render} from '@testing-library/react';
import React from 'react';

import Note from '../../src/main/resources/META-INF/resources/js/components/Note';

function renderNote() {
	return render(
		<Note
			data={{
				createDate: new Date().toLocaleString('en-US'),
				creatorName: 'Jane Doe',
				creatorPortraitURL: '/',
				edited: false,
				htmlContent: '<div>note 1</div>',
				key: '123'
			}}
		/>
	);
}

describe('Note', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNote();

		expect(container).toBeTruthy();
	});

	it('displays a note with author avatar', () => {
		const {getAllByAltText} = renderNote();

		getAllByAltText('note-author-avatar');
	});

	it('displays a note with author name', () => {
		const {getByText} = renderNote();

		getByText('Jane Doe');
	});

	it('displays a note with the create date', () => {
		const {getAllByText} = renderNote();

		getAllByText(new Date().toLocaleString('en-US'));
	});
});
