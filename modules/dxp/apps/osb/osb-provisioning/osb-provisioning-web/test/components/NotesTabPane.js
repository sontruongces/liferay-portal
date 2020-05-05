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

import NotesTabPane from '../../src/main/resources/META-INF/resources/js/components/NotesTabPane';
import {NotesProvider} from '../../src/main/resources/META-INF/resources/js/hooks/notes';
import {
	NOTE_FORMAT_HTML,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../../src/main/resources/META-INF/resources/js/utilities/constants';

function mockNotes({type}) {
	return [
		{
			createDate: new Date().toLocaleString('en-US'),
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>pinned note</div>',
			key: '123',
			pinned: true,
			status: NOTE_STATUS_APPROVED,
			type,
			updateNoteURL: '/'
		},
		{
			createDate: new Date().toLocaleString('en-US'),
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>unpinned note</div>',
			key: '456',
			pinned: false,
			status: NOTE_STATUS_APPROVED,
			type,
			updateNoteURL: '/'
		},
		{
			createDate: new Date().toLocaleString('en-US'),
			creatorName: 'Jane Doe',
			creatorPortraitURL: '/',
			edited: false,
			format: NOTE_FORMAT_HTML,
			htmlContent: '<div>archived note</div>',
			key: '789',
			pinned: false,
			status: NOTE_STATUS_ARCHIVED,
			type,
			updateNoteURL: '/'
		}
	];
}

function renderNotesTabPane({
	type = NOTE_TYPE_GENERAL,
	notes = mockNotes({type}),
	...props
} = {}) {
	return render(
		<NotesProvider initialNotes={notes}>
			<NotesTabPane addURL="/" tabType={type} {...props} />
		</NotesProvider>
	);
}

describe('NotesTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNotesTabPane();

		expect(container).toBeTruthy();
	});

	describe('approved notes', () => {
		it('displays a pinned general note in a "Pinned" section', () => {
			const {getByText} = renderNotesTabPane();

			getByText('pinned');
			getByText('pinned note');
		});

		it('displays an approved general note in a "General" section', () => {
			const {getByText} = renderNotesTabPane();

			getByText('general');
			getByText('unpinned note');
		});

		it('displays a sales note with no special section', () => {
			const {getByText, queryByText} = renderNotesTabPane({
				type: NOTE_TYPE_SALES
			});

			getByText('pinned note');
			getByText('unpinned note');
			expect(queryByText('pinned')).toBeFalsy();
			expect(queryByText('general')).toBeFalsy();
		});

		it('displays a button to view archived notes when there are archives', () => {
			const {getByText} = renderNotesTabPane();

			getByText('view-archived-notes');
		});

		it('does not display a button to view archived notes when none are available', () => {
			const {queryByText} = renderNotesTabPane({
				notes: [],
				type: NOTE_TYPE_SALES
			});

			expect(queryByText('view-archived-notes')).toBeNull();
		});

		it('displays a message when there is no data for general notes', () => {
			const {container} = renderNotesTabPane({
				notes: [],
				type: NOTE_TYPE_GENERAL
			});

			expect(container.querySelector('.empty-state').textContent).toEqual(
				'no-notes-were-found'
			);
		});

		it('displays a message when there is no data for sales notes', () => {
			const {container} = renderNotesTabPane({
				notes: [],
				type: NOTE_TYPE_SALES
			});

			expect(container.textContent).toEqual('no-sales-info-were-found');
		});
	});

	describe('archived notes', () => {
		it('displays archived notes when "view Archived Notes" button is clicked', () => {
			const {getByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));

			getByText('archived note');
		});

		it('displays a heading', () => {
			const {getByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));

			getByText('archive');
		});

		it('displays a back button', () => {
			const {getByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));

			getByText('back');
		});

		it('goes back to the general notes when the back button is clicked', () => {
			const {getByText, queryByText} = renderNotesTabPane();

			fireEvent.click(getByText('view-archived-notes'));
			fireEvent.click(getByText('back'));

			expect(queryByText('archived note')).toBeNull();
		});
	});
});
