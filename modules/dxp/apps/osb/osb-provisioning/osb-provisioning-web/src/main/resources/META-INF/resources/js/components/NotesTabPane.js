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

import PropTypes from 'prop-types';
import React from 'react';

import {mapNoteByKey} from '../utilities/helpers';
import Note from './Note';

function NotesTabPane({notes = []}) {
	const approved = notes.filter(note => note.status === 'Approved');
	const archived = notes.filter(note => note.status === 'Archived');

	const pinned = approved.filter(note => note.pinned);
	const pinnedNotes = mapNoteByKey(pinned);

	const unpinned = approved.filter(note => !note.pinned);
	const unpinnedNotes = mapNoteByKey(unpinned);

	const archivedNotes = mapNoteByKey(archived);

	return (
		<div className="notes-container">
			<div className="notes">
				{pinnedNotes.size > 0 ? (
					<div className="pinned-notes">
						<div className="notes-header">
							<svg
								aria-label={Liferay.Language.get(
									'pinned-notes-icon'
								)}
							>
								<use xlinkHref="#pin" />
							</svg>
							{Liferay.Language.get('pinned')}
						</div>

						{pinnedNotes.forEach(note => (
							<Note data={note} key={note.key} />
						))}
					</div>
				) : (
					''
				)}

				{unpinnedNotes.size > 0 ? (
					<div className="general-notes">
						<div className="notes-header">
							{Liferay.Language.get('general')}
						</div>

						{unpinnedNotes.forEach(note => (
							<Note data={note} key={note.key} />
						))}
					</div>
				) : (
					''
				)}

				{pinnedNotes.size === 0 && unpinnedNotes.size === 0 ? (
					<div className="empty-state">
						{Liferay.Language.get('no-notes-were-found')}
					</div>
				) : (
					''
				)}
			</div>

			{archivedNotes.size > 0 ? (
				<button className="archive-btn btn btn-link">
					{Liferay.Language.get('view-archived-notes')}{' '}
					<svg
						aria-label={Liferay.Language.get(
							'view-archived-notes-link'
						)}
						role="img"
					>
						<use xlinkHref="#angle-right" />
					</svg>
				</button>
			) : (
				''
			)}
		</div>
	);
}

NotesTabPane.propTypes = {
	addURL: PropTypes.string,
	notes: PropTypes.arrayOf(
		PropTypes.shape({
			createDate: PropTypes.string.isRequired,
			creatorName: PropTypes.string.isRequired,
			creatorPortraitURL: PropTypes.string,
			edited: PropTypes.bool.isRequired,
			htmlContent: PropTypes.string.isRequired,
			key: PropTypes.string.isRequired,
			pinned: PropTypes.bool.isRequired,
			status: PropTypes.string.isRequired,
			type: PropTypes.string.isRequired
		})
	)
};

export default NotesTabPane;
