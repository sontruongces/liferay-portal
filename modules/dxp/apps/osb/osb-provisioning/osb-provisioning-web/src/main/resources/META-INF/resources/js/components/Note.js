import ClaySticker from '@clayui/sticker';
import PropTypes from 'prop-types';
import React from 'react';

function Note({data}) {
	return (
		<div className="note">
			<div className="note-metadata">
				<ClaySticker displayType="secondary" shape="circle" size="md">
					<img
						alt={Liferay.Language.get('note-author-avatar')}
						className="sticker-img"
						src={data.creatorPortraitURL}
					/>
				</ClaySticker>

				<div className="metadata">
					<h4 className="note-author">{data.creatorName}</h4>
					<div className="note-create-date">{data.createDate}</div>
				</div>
			</div>

			<section
				className="note-content"
				dangerouslySetInnerHTML={{__html: data.htmlContent}}
			/>
		</div>
	);
}

Note.propTypes = {
	data: PropTypes.shape({
		createDate: PropTypes.string.isRequired,
		creatorName: PropTypes.string.isRequired,
		creatorPortraitURL: PropTypes.string,
		edited: PropTypes.bool.isRequired,
		htmlContent: PropTypes.string.isRequired,
		key: PropTypes.string.isRequired
	})
};

export default Note;