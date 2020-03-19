import {cleanup, render} from '@testing-library/react';
import React from 'react';

import NotesTabPane from '../../src/main/resources/META-INF/resources/js/components/NotesTabPane';

function renderNotesTabPane() {
	return render(
		<NotesTabPane
			notes={{
				archived: [
					{
						authorName: 'Jane Doe',
						content: '<div>note 1</div>',
						createDate: new Date().toLocaleString('en-US'),
						key: '123',
						pinned: true,
						portraitURL: '/'
					},
					{
						authorName: 'Jane Doe',
						content: '<div>note 2</div>',
						createDate: new Date().toLocaleString('en-US'),
						key: '456',
						pinned: false,
						portraitURL: '/'
					}
				],
				general: [
					{
						authorName: 'John Doe',
						content: '<div>note 3</div>',
						createDate: new Date().toLocaleString('en-US'),
						key: '789',
						pinned: true,
						portraitURL: '/'
					},
					{
						authorName: 'Joe Bloggs',
						content: '<div>note 4</div>',
						createDate: new Date().toLocaleString('en-US'),
						key: '321',
						pinned: false,
						portraitURL: '/'
					}
				]
			}}
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
					notes={{
						archived: [],
						general: [
							{
								authorName: 'John Doe',
								content: '<div>note 1</div>',
								createDate: new Date().toLocaleString('en-US'),
								key: '123',
								pinned: true,
								portraitURL: '/'
							}
						]
					}}
				/>
			);

			getByText('pinned');
		});

		it('displays a general note', () => {
			const {getByText} = render(
				<NotesTabPane
					notes={{
						archived: [],
						general: [
							{
								authorName: 'John Doe',
								content: '<div>note 1</div>',
								createDate: new Date().toLocaleString('en-US'),
								key: '123',
								pinned: false,
								portraitURL: '/'
							}
						]
					}}
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
					notes={{
						archived: [],
						general: [
							{
								authorName: 'John Doe',
								content: '<div>note 3</div>',
								createDate: new Date().toLocaleString('en-US'),
								key: '789',
								pinned: true,
								portraitURL: '/'
							}
						]
					}}
				/>
			);

			expect(queryByText('view-archived-notes')).toBeNull();
		});

		it('displays a message when there is no data', () => {
			const {container} = render(
				<NotesTabPane
					notes={{
						archived: [],
						general: []
					}}
				/>
			);

			expect(container.textContent).toEqual('no-notes-were-found');
		});
	});
});
