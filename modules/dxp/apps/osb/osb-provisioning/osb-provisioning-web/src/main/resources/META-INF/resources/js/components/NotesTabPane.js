import PropTypes from 'prop-types';
import React from 'react';

function NotesTabPane({notes}) {
	return (
		<>
			{notes ? (
				<div>Notes</div>
			) : (
				<div className="empty-state">
					{Liferay.Language.get('no-notes-were-found')}
				</div>
			)}
		</>
	);
}

NotesTabPane.propTypes = {
	notes: PropTypes.object
};

export default NotesTabPane;
