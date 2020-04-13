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

import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

import {
	NOTE_FORMAT_HTML,
	NOTE_FORMAT_PLAIN,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../utilities/constants';
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
			onMouseEnter={() =>
				setShowActionMenu(data.status === NOTE_STATUS_APPROVED)
			}
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
							tabType={data.type}
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
						tabType={data.type}
					/>
				</div>
			</div>

			{editNote ? (
				<AddNote
					actionURL={data.updateNoteURL}
					content={data.htmlContent}
					format={data.format}
					id={data.key}
					onCancel={handleCancel}
					pinned={data.pinned}
					status={data.status}
					type={data.type}
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
		format: PropTypes.oneOf([NOTE_FORMAT_HTML, NOTE_FORMAT_PLAIN])
			.isRequired,
		htmlContent: PropTypes.string.isRequired,
		key: PropTypes.string.isRequired,
		pinned: PropTypes.bool.isRequired,
		status: PropTypes.oneOf([NOTE_STATUS_APPROVED, NOTE_STATUS_ARCHIVED])
			.isRequired,
		type: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES]).isRequired,
		updateNoteURL: PropTypes.string.isRequired
	}).isRequired
};

export default Note;
