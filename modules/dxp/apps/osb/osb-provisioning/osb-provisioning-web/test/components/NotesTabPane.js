import {cleanup, render} from '@testing-library/react';
import React from 'react';

import NotesTabPane from '../../src/main/resources/META-INF/resources/js/components/NotesTabPane';

function renderNotesTabPane() {
	return render(
		<NotesTabPane
			addURL="/"
			archivedNotes={[
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'Jane Doe',
					creatorPortraitURL: '/',
					htmlContent: '<div>note 1</div>',
					key: '123',
					pinned: true
				},
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'Jane Doe',
					creatorPortraitURL: '/',
					htmlContent: '<div>note 2</div>',
					key: '456',
					pinned: false
				}
			]}
			generalNotes={[
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'John Doe',
					creatorPortraitURL: '/',
					htmlContent: '<div>note 3</div>',
					key: '789',
					pinned: true
				},
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'Joe Bloggs',
					creatorPortraitURL: '/',
					htmlContent: '<div>note 4</div>',
					key: '321',
					pinned: false
				}
			]}
		/>
	);
}

describe('NotesTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderNotesTabPane();

		expect(container).toBeTruthy();
	});

	describe('display', () => {
		it('displays a note with author avatar', () => {
			const {getAllByAltText} = renderNotesTabPane();

			getAllByAltText('note-author-avatar');
		});

		it('displays a note with author name', () => {
			const {getByText} = renderNotesTabPane();

			getByText('Joe Bloggs');
		});

		it('displays a note with the create date', () => {
			const {getAllByText} = renderNotesTabPane();

			getAllByText(new Date().toLocaleString('en-US'));
		});

		it('displays a pinned note', () => {
			const {getByText} = render(
				<NotesTabPane
					generalNotes={[
						{
							createDate: new Date().toLocaleString('en-US'),
							creatorName: 'John Doe',
							creatorPortraitURL: '/',
							htmlContent: '<div>note 3</div>',
							key: '789',
							pinned: true
						}
					]}
				/>
			);

			getByText('pinned');
		});

		it('displays a general note', () => {
			const {getByText} = render(
				<NotesTabPane
					generalNotes={[
						{
							createDate: new Date().toLocaleString('en-US'),
							creatorName: 'John Doe',
							creatorPortraitURL: '/',
							htmlContent: '<div>note 3</div>',
							key: '789',
							pinned: false
						}
					]}
				/>
			);

			getByText('general');
		});

		it('displays a button to view archived notes when there are archives', () => {
			const {getByText} = renderNotesTabPane();

			getByText('view-archived-notes');
		});

		it('does not display a button to view archived notes when none are available', () => {
			const {queryByText} = render(
				<NotesTabPane
					generalNotes={[
						{
							createDate: new Date().toLocaleString('en-US'),
							creatorName: 'John Doe',
							creatorPortraitURL: '/',
							htmlContent: '<div>note 3</div>',
							key: '789',
							pinned: false
						}
					]}
				/>
			);

			expect(queryByText('view-archived-notes')).toBeNull();
		});

		it('displays a message when there is no data', () => {
			const {container} = render(<NotesTabPane />);

			expect(container.textContent).toEqual('no-notes-were-found');
		});
	});
});
