import PropTypes from 'prop-types';
import React from 'react';

function ActionMenu({handleEdit, handlePinning, pinned = false}) {
	return (
		<>
			<button
				className="btn btn-unstyled"
				onClick={handleEdit}
				role="button"
				type="button"
			>
				<svg
					aria-label={Liferay.Language.get('edit-note-icon')}
					role="img"
				>
					<use xlinkHref="#edit" />
				</svg>
			</button>

			{pinned ? (
				<button
					className="btn btn-unstyled"
					onClick={handlePinning}
					role="button"
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('unpin-note-icon')}
						role="img"
					>
						<use xlinkHref="#unpin" />
					</svg>
				</button>
			) : (
				<button
					className="btn btn-unstyled"
					onClick={handlePinning}
					role="button"
					type="button"
				>
					<svg
						aria-label={Liferay.Language.get('pin-note-icon')}
						role="img"
					>
						<use xlinkHref="#pin" />
					</svg>
				</button>
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
