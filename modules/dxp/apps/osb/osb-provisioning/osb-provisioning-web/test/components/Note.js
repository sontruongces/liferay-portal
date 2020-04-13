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

import Note from '../../src/main/resources/META-INF/resources/js/components/Note';

function renderNote(props) {
	return render(
		<Note
			data={{
				createDate: new Date().toLocaleString('en-US'),
				creatorName: 'Jane Doe',
				creatorPortraitURL: '/',
				edited: false,
				format: 'HTML',
				htmlContent: '<div>note 1</div>',
				key: '123',
				pinned: true,
				status: 'Approved',
				type: 'General',
				updateNoteURL: '/',
				...props
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
		const {getAllByText} = renderNote({createDate: 'Today'});

		getAllByText('Today');
	});

	it('displays a three-dot menu icon', () => {
		const {getByLabelText} = renderNote();

		getByLabelText('action-menu-icon');
	});

	it('displays the action menu icons on hover', () => {
		const {container, getByLabelText, queryByLabelText} = renderNote();

		expect(queryByLabelText('edit-note-icon')).toBeNull();

		const note = container.querySelector('.note');

		fireEvent.mouseEnter(note);

		getByLabelText('edit-note-icon');

		fireEvent.mouseLeave(note);

		expect(queryByLabelText('edit-note-icon')).toBeNull();
	});

	it('displays no action menu icons for an archived note', () => {
		const {container, queryByLabelText} = renderNote({status: 'Archived'});

		const note = container.querySelector('.note');

		fireEvent.mouseEnter(note);

		expect(queryByLabelText('edit-note-icon')).toBeNull();
	});

	it('displays the "edited" text next to the date of the note that has been modified', () => {
		const {getByText} = renderNote({edited: true});

		getByText('(edited)');
	});
});
