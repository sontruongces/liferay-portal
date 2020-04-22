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
import React, {useEffect, useState} from 'react';

import {
	NOTE_FORMAT_HTML,
	NOTE_FORMAT_PLAIN,
	NOTE_PRIORITY_PINNED,
	NOTE_PRIORITY_UNPINNED,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED,
	NOTE_TYPE_GENERAL,
	NOTE_TYPE_SALES
} from '../utilities/constants';
import {postData} from '../utilities/helpers';

function AddNote({
	actionURL = '',
	content = '',
	format = NOTE_FORMAT_PLAIN,
	id = '',
	onCancel,
	pinned = false,
	status = NOTE_STATUS_APPROVED,
	type = NOTE_TYPE_GENERAL
}) {
	const [noteContent, setNoteContent] = useState(content);
	const [savingNote, setSavingNote] = useState(false);
	const [showButtons, setShowButtons] = useState(!!content);

	const handleCancel = () => {
		setNoteContent(content ? content : '');
		setShowButtons(false);

		if (onCancel) {
			onCancel();
		}
	};

	const noteData = {
		content: noteContent,
		format,
		priority: pinned ? NOTE_PRIORITY_PINNED : NOTE_PRIORITY_UNPINNED,
		status,
		type
	};

	useEffect(() => {
		if (savingNote) {
			postData(actionURL, noteData, 'formData')
				.then(({data}) => {
					console.log(data, data.note.key);
					setSavingNote(false);
				})
				.catch(err => console.error(err));
		}
	}, [actionURL, noteData, savingNote]);

	return (
		<form className="new-note" method="post">
			<label
				className="form-control-label"
				htmlFor={`addNoteContent${id}`}
			>
				<textarea
					className="form-control"
					id={`addNoteContent${id}`}
					name="content"
					onChange={event =>
						setNoteContent(event.currentTarget.value)
					}
					onFocus={() => setShowButtons(true)}
					placeholder={
						type === NOTE_TYPE_GENERAL
							? Liferay.Language.get('write-a-note')
							: Liferay.Language.get('write-sales-info')
					}
					value={noteContent}
				/>
			</label>

			{showButtons && (
				<div className="button-row">
					<button
						className="btn btn-secondary cancel-btn"
						disabled={savingNote}
						onClick={handleCancel}
						role="button"
						type="button"
					>
						{Liferay.Language.get('cancel')}
					</button>

					<button
						className="btn btn-primary save-btn"
						disabled={!noteContent || savingNote}
						onClick={() => setSavingNote(true)}
						role="button"
						type="button"
					>
						{Liferay.Language.get('save')}
					</button>
				</div>
			)}
		</form>
	);
}

AddNote.propTypes = {
	actionURL: PropTypes.string,
	content: PropTypes.string,
	format: PropTypes.oneOf([NOTE_FORMAT_HTML, NOTE_FORMAT_PLAIN]),
	id: PropTypes.string,
	onCancel: PropTypes.func,
	pinned: PropTypes.bool,
	status: PropTypes.oneOf([NOTE_STATUS_APPROVED, NOTE_STATUS_ARCHIVED]),
	type: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES])
};

export default AddNote;
