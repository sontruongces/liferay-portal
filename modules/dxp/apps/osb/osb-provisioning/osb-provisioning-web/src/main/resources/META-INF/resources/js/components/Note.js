import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import ActionMenu from './ActionMenu';
import AddNote from './AddNote';
import PanelDropdownMenu from './PanelDropdownMenu';

function Note({data}) {
	const [editNote, setEditNote] = useState(false);
	const [showActionMenu, setShowActionMenu] = useState(false);

	const handleCancel = () => {
		setEditNote(false);
	};

	const handleEdit = () => {
		setEditNote(true);
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
							{data.edited && (
								<span className="edited">
									({Liferay.Language.get('edited')})
								</span>
							)}
						</div>
					</div>
				</div>

				<div className="note-menu">
					{showActionMenu && (
						<ActionMenu
							onEdit={handleEdit}
							onPinning={() => {
								/* TODO: fill in event handler LHC-2061 */
							}}
							pinned={data.pinned}
						/>
					)}

					<PanelDropdownMenu
						id={data.key}
						onArchive={() => {
							/* TODO: fill in event handler LHC-2116 */
						}}
						onEdit={handleEdit}
						onPinning={() => {
							/* TODO: fill in event handler LHC-2061 */
						}}
						pinned={data.pinned}
						status={data.status}
					/>
				</div>
			</div>

			{editNote ? (
				<AddNote
					actionURL={data.updateNoteURL}
					content={data.htmlContent}
					format={data.format}
					onCancel={handleCancel}
					pinned={data.pinned}
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
	data: PropTypes.shape({
		createDate: PropTypes.string.isRequired,
		creatorName: PropTypes.string.isRequired,
		creatorPortraitURL: PropTypes.string,
		edited: PropTypes.bool.isRequired,
		format: PropTypes.string.isRequired,
		htmlContent: PropTypes.string.isRequired,
		key: PropTypes.string.isRequired,
		pinned: PropTypes.bool.isRequired,
		status: PropTypes.string.isRequired,
		updateNoteURL: PropTypes.string.isRequired
	}).isRequired
};

export default Note;
