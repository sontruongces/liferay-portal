import ClayTabs from '@clayui/tabs';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

import ExternalLinksTabPane from './ExternalLinksTabPane';
import NotesTabPane from './NotesTabPane';
import SalesInfoTabPane from './SalesInfoTabPane';

function CollapsiblePanel({addNoteURL, handleCollapse, notes = []}) {
	const [activeIndex, setActiveIndex] = useState(0);

	const generalNotes = notes.filter(note => note.type === 'General');
	const salesNotes = notes.filter(note => note.type === 'Sales');

	return (
		<>
			<ClayTabs modern>
				<ClayTabs.Item
					active={activeIndex === 0}
					innerProps={{
						'aria-controls': 'tabPaneNotes'
					}}
					onClick={() => setActiveIndex(0)}
				>
					{Liferay.Language.get('notes')}
				</ClayTabs.Item>
				<ClayTabs.Item
					active={activeIndex === 1}
					innerProps={{
						'aria-controls': 'tabPaneSalesInfo'
					}}
					onClick={() => setActiveIndex(1)}
				>
					{Liferay.Language.get('sales-info')}
				</ClayTabs.Item>
				<ClayTabs.Item
					active={activeIndex === 2}
					innerProps={{
						'aria-controls': 'tabPaneExternalLinks'
					}}
					onClick={() => setActiveIndex(2)}
				>
					{Liferay.Language.get('external-links')}
				</ClayTabs.Item>
				<ClayTabs.Item
					className="panel-collapse"
					onClick={handleCollapse}
				>
					<svg
						aria-label={Liferay.Language.get(
							'collapse-panel-button'
						)}
						role="img"
					>
						<use xlinkHref="#collapse" />
					</svg>
				</ClayTabs.Item>
			</ClayTabs>

			<ClayTabs.Content activeIndex={activeIndex}>
				<ClayTabs.TabPane id="tabPaneNotes">
					<NotesTabPane addURL={addNoteURL} notes={generalNotes} />
				</ClayTabs.TabPane>
				<ClayTabs.TabPane id="tabPaneSalesInfo">
					<SalesInfoTabPane addURL={addNoteURL} notes={salesNotes} />
				</ClayTabs.TabPane>
				<ClayTabs.TabPane id="tabPaneExternalLinks">
					<ExternalLinksTabPane />
				</ClayTabs.TabPane>
			</ClayTabs.Content>
		</>
	);
}

CollapsiblePanel.propTypes = {
	addNoteURL: PropTypes.string,
	handleCollapse: PropTypes.func.isRequired,
	notes: PropTypes.arrayOf(
		PropTypes.shape({
			createDate: PropTypes.string.isRequired,
			creatorName: PropTypes.string.isRequired,
			creatorPortraitURL: PropTypes.string,
			edited: PropTypes.bool.isRequired,
			htmlContent: PropTypes.string.isRequired,
			key: PropTypes.string.isRequired,
			pinned: PropTypes.bool.isRequired,
			status: PropTypes.string.isRequired,
			type: PropTypes.string.isRequired
		})
	)
};

function ExpandPanelButton({handleCollapse}) {
	return (
		<button
			className="btn btn-unstyled panel-expand"
			onClick={handleCollapse}
			role="button"
			type="button"
		>
			<svg
				aria-label={Liferay.Language.get('expand-panel-button')}
				role="img"
			>
				<use xlinkHref="#expand" />
			</svg>
		</button>
	);
}

ExpandPanelButton.propTypes = {
	handleCollapse: PropTypes.func.isRequired
};

function SidePanel(props) {
	const [collapse, setCollapse] = useState(false);

	const handleCollapse = () => {
		setCollapse(!collapse);
	};

	useEffect(() => {
		const account = document.getElementById('account');

		if (account && account.classList.contains('full-view')) {
			account.classList.remove('full-view');
		} else {
			account.classList.add('full-view');
		}
	});

	return (
		<>
			{collapse ? (
				<ExpandPanelButton handleCollapse={handleCollapse} />
			) : (
				<CollapsiblePanel handleCollapse={handleCollapse} {...props} />
			)}
		</>
	);
}

export default SidePanel;
