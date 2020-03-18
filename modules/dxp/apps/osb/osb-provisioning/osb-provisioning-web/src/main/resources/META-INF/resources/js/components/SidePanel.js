import ClayTabs from '@clayui/tabs';
import PropTypes from 'prop-types';
import React, {useEffect, useState} from 'react';

function CollapsiblePanel({handleCollapse}) {
	const [activeIndex, setActiveIndex] = useState(0);

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
				<ClayTabs.TabPane
					id="tabPaneNotes"
				>
					{'Notes Tab Placeholder Text'}
				</ClayTabs.TabPane>
				<ClayTabs.TabPane
					id="tabPaneSalesInfo"
				>
					{'Sales Info Tab Placeholder Text'}
				</ClayTabs.TabPane>
				<ClayTabs.TabPane
					id="tabPaneExternalLinks"
				>
					{'External Links Placeholder Text'}
				</ClayTabs.TabPane>
			</ClayTabs.Content>
		</>
	);
}

CollapsiblePanel.propTypes = {
	handleCollapse: PropTypes.func.isRequired
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

function SidePanel() {
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
				<CollapsiblePanel handleCollapse={handleCollapse} />
			)}
		</>
	);
}

export default SidePanel;
