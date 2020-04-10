import PropTypes from 'prop-types';
import React, {useState} from 'react';

const NAMESPACE = window.ProvisioningConstants.namespace;
const NOTE_FORMAT_PLAIN = window.ProvisioningConstants.noteFormat.plaintext;
const NOTE_STATUS_APPROVED = window.ProvisioningConstants.noteStatus.approved;
const NOTE_TYPE_GENERAL = window.ProvisioningConstants.noteType.general;
const PRIORITY_PINNED = 1;
const PRIORITY_UNPINNED = 2;

function AddNote({
	actionURL = '',
	content = '',
	format = NOTE_FORMAT_PLAIN,
	onCancel,
	pinned = false,
	status = NOTE_STATUS_APPROVED,
	type = NOTE_TYPE_GENERAL
}) {
	const [noteContent, setNoteContent] = useState(content);
	const [showButtons, setShowButtons] = useState(!!content);

	const handleCancel = () => {
		setNoteContent(content ? content : '');
		setShowButtons(false);

		if (onCancel) {
			onCancel();
		}
	};

	return (
		<form action={actionURL} className="new-note" method="post">
			<input name={`${NAMESPACE}format`} type="hidden" value={format} />
			<input
				name={`${NAMESPACE}priority`}
				type="hidden"
				value={pinned ? PRIORITY_PINNED : PRIORITY_UNPINNED}
			/>
			<input name={`${NAMESPACE}status`} type="hidden" value={status} />
			<input name={`${NAMESPACE}type`} type="hidden" value={type} />

			<label className="form-control-label" htmlFor="addNoteContent">
				<textarea
					className="form-control"
					id="addNoteContent"
					name={`${NAMESPACE}content`}
					onChange={event =>
						setNoteContent(event.currentTarget.value)
					}
					onClick={() => setShowButtons(true)}
					placeholder={Liferay.Language.get('write-a-note')}
					value={noteContent}
				/>
			</label>

			{showButtons && (
				<div className="button-row">
					<button
						className="btn btn-secondary cancel-btn"
						onClick={handleCancel}
						role="button"
						type="button"
					>
						{Liferay.Language.get('cancel')}
					</button>

					<button
						className="btn btn-primary save-btn"
						disabled={!noteContent}
						role="button"
						type="submit"
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
	format: PropTypes.string,
	onCancel: PropTypes.func,
	pinned: PropTypes.bool,
	status: PropTypes.string,
	type: PropTypes.string
};

export default AddNote;
