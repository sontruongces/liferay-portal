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

import {NoteRecord} from '../hooks/notes';
import {NOTE_STATUS_APPROVED} from '../utilities/constants';
import ActionMenu from './ActionMenu';
import AddNote from './AddNote';
import PanelDropdownMenu from './PanelDropdownMenu';

function Note({note}) {
	const [editNote, setEditNote] = useState(false);
	const [showActionMenu, setShowActionMenu] = useState(false);

	const {content, id, pinned, status, type} = note;

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
				setShowActionMenu(status === NOTE_STATUS_APPROVED)
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
							src={note.creatorPortraitURL}
						/>
					</ClaySticker>

					<div className="metadata">
						<h4 className="note-author">{note.creatorName}</h4>
						<div className="note-create-date">
							{note.createDate}{' '}
							{note.edited && (
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
							pinned={pinned}
							tabType={type}
						/>
					)}

					<PanelDropdownMenu
						id={id}
						onArchive={() => {
							/* TODO: fill in event handler LHC-2116 */
						}}
						onEdit={handleEdit}
						onPinning={() => {
							/* TODO: fill in event handler LHC-2061 */
						}}
						pinned={pinned}
						status={status}
						tabType={type}
					/>
				</div>
			</div>

			{editNote ? (
				<AddNote
					actionURL={note.updateURL}
					content={content}
					format={note.format}
					id={id}
					onCancel={handleCancel}
					pinned={pinned}
					status={status}
					type={type}
				/>
			) : (
				<section
					className="note-content"
					dangerouslySetInnerHTML={{__html: content}}
				/>
			)}
		</div>
	);
}

Note.propTypes = {
	note: PropTypes.instanceOf(NoteRecord)
};

export default Note;
