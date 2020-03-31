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

import Note from './Note';

function NotesTabPane({notes = []}) {
	const approved = notes.filter(note => note.status === 'Approved');
	const archived = notes.filter(note => note.status === 'Archived');

	const pinned = approved.filter(note => note.pinned);
	const unpinned = approved.filter(note => !note.pinned);

	return (
		<div className="notes-container">
			<div className="notes">
				{pinned.length && (
					<div className="pinned-notes">
						<div className="notes-section-header">
							<svg
								aria-label={Liferay.Language.get(
									'pinned-notes-icon'
								)}
							>
								<use xlinkHref="#pin" />
							</svg>
							{Liferay.Language.get('pinned')}
						</div>

						{pinned.map(note => (
							<Note data={note} key={note.key} />
						))}
					</div>
				)}

				{unpinned.length && (
					<div className="general-notes">
						<div className="notes-section-header">
							{Liferay.Language.get('general')}
						</div>

						{unpinned.map(note => (
							<Note data={note} key={note.key} />
						))}
					</div>
				)}

				{!pinned.length && !unpinned.length && (
					<div className="empty-state">
						{Liferay.Language.get('no-notes-were-found')}
					</div>
				)}
			</div>

			{archived.length && (
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
