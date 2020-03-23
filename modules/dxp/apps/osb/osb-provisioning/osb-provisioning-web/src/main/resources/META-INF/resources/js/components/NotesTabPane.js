import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React from 'react';

function Note({data}) {
	return (
		<div className="note">
			<div className="note-metadata">
				<ClaySticker displayType="secondary" shape="circle" size="md">
					<img
						alt={Liferay.Language.get('note-author-avatar')}
						className="sticker-img"
						src={data.creatorPortraitURL}
					/>
				</ClaySticker>

				<div className="metadata">
					<h4 className="note-author">{data.creatorName}</h4>
					<div className="note-create-date">{data.createDate}</div>
				</div>
			</div>

			<section
				className="note-content"
				dangerouslySetInnerHTML={{__html: data.htmlContent}}
			/>
		</div>
	);
}

Note.propTypes = {
	data: PropTypes.shape({
		createDate: PropTypes.string.isRequired,
		creatorName: PropTypes.string.isRequired,
		creatorPortraitURL: PropTypes.string,
		htmlContent: PropTypes.string.isRequired,
		key: PropTypes.string.isRequired,
		pinned: PropTypes.bool.isRequired
	})
};

function NotesTabPane({archivedNotes = [], generalNotes = []}) {
	const pinnedGeneralNotes = generalNotes.filter(note => note.pinned);
	const unpinnedGeneralNotes = generalNotes.filter(note => !note.pinned);

	return (
		<div className="notes-container">
			<div className="notes">
				{pinnedGeneralNotes.length ? (
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

						{pinnedGeneralNotes.map(pinned => (
							<Note data={pinned} key={pinned.key} />
						))}
					</div>
				) : (
					''
				)}

				{unpinnedGeneralNotes.length ? (
					<div className="general-notes">
						<div className="notes-header">
							{Liferay.Language.get('general')}
						</div>

						{unpinnedGeneralNotes.map(unpinned => (
							<Note data={unpinned} key={unpinned.key} />
						))}
					</div>
				) : (
					''
				)}

				{!pinnedGeneralNotes.length && !unpinnedGeneralNotes.length ? (
					<div className="empty-state">
						{Liferay.Language.get('no-notes-were-found')}
					</div>
				) : (
					''
				)}
			</div>

			{archivedNotes.length ? (
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
	archivedNotes: PropTypes.arrayOf(
		PropTypes.shape({
			createDate: PropTypes.string.isRequired,
			creatorName: PropTypes.string.isRequired,
			creatorPortraitURL: PropTypes.string,
			htmlContent: PropTypes.string.isRequired,
			key: PropTypes.string.isRequired,
			pinned: PropTypes.bool.isRequired
		})
	),
	generalNotes: PropTypes.arrayOf(
		PropTypes.shape({
			createDate: PropTypes.string.isRequired,
			creatorName: PropTypes.string.isRequired,
			creatorPortraitURL: PropTypes.string,
			htmlContent: PropTypes.string.isRequired,
			key: PropTypes.string.isRequired,
			pinned: PropTypes.bool.isRequired
		})
	)
};

export default NotesTabPane;
