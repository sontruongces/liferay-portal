import {ClayTooltipProvider} from '@clayui/tooltip';
import PropTypes from 'prop-types';
import React from 'react';

import IconButton from './IconButton';

function ActionMenu({onEdit, onPinning, pinned = false}) {
	return (
		<>
			<ClayTooltipProvider>
				<IconButton
					data-tooltip-align="top"
					labelName="edit-note-icon"
					onClick={onEdit}
					svgId="#edit"
					title={Liferay.Language.get('edit')}
				/>
			</ClayTooltipProvider>

			{pinned ? (
				<ClayTooltipProvider>
					<IconButton
						data-tooltip-align="top"
						labelName="unpin-note-icon"
						onClick={onPinning}
						svgId="#unpin"
						title={Liferay.Language.get('unpin')}
					/>
				</ClayTooltipProvider>
			) : (
				<ClayTooltipProvider>
					<IconButton
						data-tooltip-align="top"
						labelName="pin-note-icon"
						onClick={onPinning}
						svgId="#pin"
						title={Liferay.Language.get('pin')}
					/>
				</ClayTooltipProvider>
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
