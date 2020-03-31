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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import NotesTabPane from '../../src/main/resources/META-INF/resources/js/components/NotesTabPane';

function renderNotesTabPane() {
	return render(
		<NotesTabPane
			addURL="/"
			notes={[
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'Jane Doe',
					creatorPortraitURL: '/',
					edited: false,
					htmlContent: '<div>note 1</div>',
					key: '123',
					pinned: true,
					status: 'Approved',
					type: 'General'
				},
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'Jane Doe',
					creatorPortraitURL: '/',
					edited: false,
					htmlContent: '<div>note 2</div>',
					key: '456',
					pinned: false,
					status: 'Approved',
					type: 'General'
				},
				{
					createDate: new Date().toLocaleString('en-US'),
					creatorName: 'Jane Doe',
					creatorPortraitURL: '/',
					edited: false,
					htmlContent: '<div>note 3</div>',
					key: '789',
					pinned: false,
					status: 'Archived',
					type: 'General'
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
		it('displays a pinned note', () => {
			const {getByText} = renderNotesTabPane();

			getByText('pinned');
		});

		it('displays an approved general note', () => {
			const {getByText} = renderNotesTabPane();

			getByText('general');
		});

		it('displays a button to view archived notes when there are archives', () => {
			const {getByText} = renderNotesTabPane();

			getByText('view-archived-notes');
		});

		it('does not display a button to view archived notes when none are available', () => {
			const {queryByText} = render(
				<NotesTabPane
					notes={[
						{
							createDate: new Date().toLocaleString('en-US'),
							creatorName: 'Jane Doe',
							creatorPortraitURL: '/',
							edited: false,
							htmlContent: '<div>note 1</div>',
							key: '123',
							pinned: true,
							status: 'Approved',
							type: 'General'
						}
					]}
				/>
			);

			expect(queryByText('view-archived-notes')).toBeNull();
		});

		it('displays a message when there is no data', () => {
			const {container} = render(<NotesTabPane />);

			expect(container.querySelector('.empty-state').textContent).toEqual(
				'no-notes-were-found'
			);
		});
	});
});
