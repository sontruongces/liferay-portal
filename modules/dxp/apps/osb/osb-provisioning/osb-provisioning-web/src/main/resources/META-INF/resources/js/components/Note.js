import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import ActionMenu from './ActionMenu';
import AddNote from './AddNote';

function Note({addURL, data}) {
	const [editNote, setEditNote] = useState(false);
	const [showActionMenu, setShowActionMenu] = useState(false);

	const handleCancel = () => {
		setEditNote(false);
	};

	return (
		<div
			className="note"
			onMouseEnter={() => setShowActionMenu(data.status === 'Approved')}
			onMouseLeave={() => setShowActionMenu(false)}
		>
			<div className="note-header">
				<div className="note-metadata">
					<ClaySticker
						displayType="secondary"
						shape="circle"
						size="md"
					>
						<img
							alt={Liferay.Language.get('note-author-avatar')}
							className="sticker-img"
							src={data.creatorPortraitURL}
						/>
					</ClaySticker>

					<div className="metadata">
						<h4 className="note-author">{data.creatorName}</h4>
						<div className="note-create-date">
							{data.createDate}{' '}
							{!!data.edited && (
								<span className="edited">
									{Liferay.Language.get('edited')}
								</span>
							)}
						</div>
					</div>
				</div>

				<div className="note-menu">
					{showActionMenu && (
						<ActionMenu
							onEdit={() => {
								setEditNote(true);
							}}
							onPinning={() => {
								/* TODO: fill in event handler LHC-2061 */
							}}
							pinned={data.pinned}
						/>
					)}

					<button
						className="btn btn-unstyled"
						role="button"
						title={Liferay.Language.get('menu')}
						type="button"
					>
						<svg
							aria-label={Liferay.Language.get(
								'action-menu-icon'
							)}
							role="img"
						>
							<use xlinkHref="#three-dot" />
						</svg>
					</button>
				</div>
			</div>

			{editNote ? (
				<AddNote
					addURL={addURL}
					content={data.htmlContent}
					onCancel={handleCancel}
					status={data.status}
				/>
			) : (
				<section
					className="note-content"
					dangerouslySetInnerHTML={{__html: data.htmlContent}}
				/>
			)}
		</div>
	);
}

Note.propTypes = {
	addURL: PropTypes.string,
	data: PropTypes.shape({
		createDate: PropTypes.string.isRequired,
		creatorName: PropTypes.string.isRequired,
		creatorPortraitURL: PropTypes.string,
		edited: PropTypes.bool.isRequired,
		htmlContent: PropTypes.string.isRequired,
		key: PropTypes.string.isRequired,
		pinned: PropTypes.bool.isRequired,
		status: PropTypes.string.isRequired
	}).isRequired
};

export default Note;
