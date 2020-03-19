import PropTypes from 'prop-types';
import React from 'react';

function SalesInfoTabPane() {
	return <>{Liferay.Language.get('no-sales-info-were-found')}</>;
}

SalesInfoTabPane.propTypes = {
	info: PropTypes.object
};

export default SalesInfoTabPane;
