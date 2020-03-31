import PropTypes from 'prop-types';
import React from 'react';

import IconButton from './IconButton';

function ActionMenu({handleEdit, handlePinning, pinned = false}) {
	return (
		<>
			<IconButton
				labelName="edit-note-icon"
				onClick={handleEdit}
				svgID="#edit"
			/>

			{pinned ? (
				<IconButton
					labelName="unpin-note-icon"
					onClick={handlePinning}
					svgID="#unpin"
				/>
			) : (
				<IconButton
					labelName="pin-note-icon"
					onClick={handlePinning}
					svgID="#pin"
				/>
			)}
		</>
	);
}

ActionMenu.propTypes = {
	handleEdit: PropTypes.func,
	handlePinning: PropTypes.func,
	pinned: PropTypes.bool
};

export default ActionMenu;
