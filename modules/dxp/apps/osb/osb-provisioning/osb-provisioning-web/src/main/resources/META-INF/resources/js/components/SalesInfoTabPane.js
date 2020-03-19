import PropTypes from 'prop-types';
import React from 'react';

function SalesInfoTabPane({info}) {
	return (
		<>
			{info ? (
				<div>Sales Info</div>
			) : (
				<div className="empty-state">
					{Liferay.Language.get('no-sales-info-were-found')}
				</div>
			)}
		</>
	);
}

SalesInfoTabPane.propTypes = {
	info: PropTypes.object
};

export default SalesInfoTabPane;
