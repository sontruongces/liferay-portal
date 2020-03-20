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
						src={data.portraitURL}
					/>
				</ClaySticker>

				<div className="metadata">
					<h4 className="note-author">{data.authorName}</h4>
					<div className="note-create-date">{data.createDate}</div>
				</div>
			</div>

			<section
				className="note-content"
				dangerouslySetInnerHTML={{__html: data.content}}
			/>
		</div>
	);
}

Note.propTypes = {
	data: PropTypes.shape({
		authorName: PropTypes.string.isRequired,
		content: PropTypes.string.isRequired,
		createDate: PropTypes.string.isRequired,
		key: PropTypes.string.isRequired,
		pinned: PropTypes.bool.isRequired,
		portraitURL: PropTypes.string
	})
};

function NotesTabPane({notes}) {
	const pinnedGeneralNotes = notes.general.filter(note => note.pinned);
	const unpinnedGeneralNotes = notes.general.filter(note => !note.pinned);

	return (
		<>
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

			{notes.archived.length ? (
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
		</>
	);
}

NotesTabPane.propTypes = {
	notes: PropTypes.shape({
		archived: PropTypes.arrayOf(
			PropTypes.shape({
				authorName: PropTypes.string.isRequired,
				content: PropTypes.string.isRequired,
				createDate: PropTypes.string.isRequired,
				key: PropTypes.string.isRequired,
				pinned: PropTypes.bool.isRequired,
				portraitURL: PropTypes.string
			})
		),
		general: PropTypes.arrayOf(
			PropTypes.shape({
				authorName: PropTypes.string.isRequired,
				content: PropTypes.string.isRequired,
				createDate: PropTypes.string.isRequired,
				key: PropTypes.string.isRequired,
				pinned: PropTypes.bool.isRequired,
				portraitURL: PropTypes.string
			})
		)
	})
};

export default NotesTabPane;
