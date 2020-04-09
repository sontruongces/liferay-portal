import {Align, ClayDropDownWithItems} from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React from 'react';

const NOTE_STATUS_APPROVED = window.ProvisioningConstants.noteStatus.approved;

function PanelDropdownMenu({
	id,
	onArchive,
	onEdit,
	onPinning,
	pinned = false,
	status = NOTE_STATUS_APPROVED
}) {
	const generateDropdownItems = () => {
		const dropdownItems = [];

		if (status === NOTE_STATUS_APPROVED) {
			dropdownItems.push({
				label: Liferay.Language.get('edit'),
				onClick: onEdit
			});
			dropdownItems.push({
				label: pinned
					? Liferay.Language.get('unpin')
					: Liferay.Language.get('pin'),
				onClick: onPinning
			});
			dropdownItems.push({
				label: Liferay.Language.get('archive'),
				onClick: onArchive
			});
		} else {
			dropdownItems.push({
				label: Liferay.Language.get('unarchive'),
				onClick: onArchive
			});
		}

		return dropdownItems;
	};

	return (
		// Clay drop-down expects a HTMLButtonElement as a trigger

		<ClayDropDownWithItems
			alignmentPosition={Align.BottomRight}
			className="panel-dropdown-menu"
			items={generateDropdownItems()}
			key={`dropdownMenu-${id}`}
			trigger={
				<button
					className="btn btn-unstyled"
					role="button"
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('action-menu-icon')}
						role="img"
					>
						<use xlinkHref="#three-dot" />
					</svg>
				</button>
			}
		/>
	);
}

PanelDropdownMenu.propTypes = {
	id: PropTypes.string.isRequired,
	onEdit: PropTypes.func.isRequired,
	pinned: PropTypes.bool,
	status: PropTypes.string
};

export default PanelDropdownMenu;
