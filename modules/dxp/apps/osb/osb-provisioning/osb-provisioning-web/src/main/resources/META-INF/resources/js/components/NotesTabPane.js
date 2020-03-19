import PropTypes from 'prop-types';
import React from 'react';

function NotesTabPane() {
	return <>{Liferay.Language.get('no-notes-were-found')}</>;
}

NotesTabPane.propTypes = {
	notes: PropTypes.object
};

export default NotesTabPane;
