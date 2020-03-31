import PropTypes from 'prop-types';
import React from 'react';

import IconButton from './IconButton';

function ActionMenu({onEdit, onPinning, pinned = false}) {
	return (
		<>
			<IconButton
				labelName="edit-note-icon"
				onClick={onEdit}
				svgId="#edit"
			/>

			{pinned ? (
				<IconButton
					labelName="unpin-note-icon"
					onClick={onPinning}
					svgId="#unpin"
				/>
			) : (
				<IconButton
					labelName="pin-note-icon"
					onClick={onPinning}
					svgId="#pin"
				/>
			)}
		</>
	);
}

ActionMenu.propTypes = {
	onEdit: PropTypes.func,
	onPinning: PropTypes.func,
	pinned: PropTypes.bool
};

export default ActionMenu;
