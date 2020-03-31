import PropTypes from 'prop-types';
import React from 'react';

function IconButton({cssClass, labelName, onClick, svgId, ...otherProps}) {
	return (
		<button
			className={`btn btn-unstyled ${cssClass}`}
			onClick={onClick}
			role="button"
			type="button"
			{...otherProps}
		>
			<svg aria-label={Liferay.Language.get(labelName)} role="img">
				<use xlinkHref={svgId} />
			</svg>
		</button>
	);
}

IconButton.propTypes = {
	cssClass: PropTypes.string,
	labelName: PropTypes.string.isRequired,
	onClick: PropTypes.func.isRequired,
	svgId: PropTypes.string.isRequired
};

export default IconButton;
