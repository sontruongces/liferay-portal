import PropTypes from 'prop-types';
import React, {useState} from 'react';

const NAMESPACE = window.ProvisioningConstants.namespace;
const NOTE_FORMAT_PLAIN = window.ProvisioningConstants.noteFormat.plaintext;
const NOTE_STATUS_APPROVED = window.ProvisioningConstants.noteStatus.approved;
const NOTE_TYPE_GENERAL = window.ProvisioningConstants.noteType.general;
const UNNPINNED_PRIORITY = 2;

function NewNote({
	addURL,
	format = NOTE_FORMAT_PLAIN,
	status = NOTE_STATUS_APPROVED,
	type = NOTE_TYPE_GENERAL
}) {
	const [noteContent, setNoteContent] = useState('');
	const [showButtons, setShowButtons] = useState(false);

	return (
		<form action={addURL} className="add-new-note" method="post">
			<input name={`${NAMESPACE}format`} type="hidden" value={format} />
			<input
				name={`${NAMESPACE}priority`}
				type="hidden"
				value={UNNPINNED_PRIORITY}
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
						onClick={() => setNoteContent('')}
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

NewNote.propTypes = {
	addURL: PropTypes.string
};

export default NewNote;
