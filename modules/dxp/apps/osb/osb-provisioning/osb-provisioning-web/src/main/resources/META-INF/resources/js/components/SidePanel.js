import ClayTabs from '@clayui/tabs';
import React, {useState} from 'react';

import ErrorBoundary from './ErrorBoundary';

const CollapsiblePanel = () => {
	const [activeIndex, setActiveIndex] = useState(0);
	const [collapse, setCollapse] = useState(false);

	return (
		<>
			<ClayTabs modern>
				<ClayTabs.Item
					active={activeIndex === 0}
					innerProps={{
						'aria-controls': 'tabpanel-notes'
					}}
					onClick={() => setActiveIndex(0)}
				>
					{Liferay.Language.get('notes')}
				</ClayTabs.Item>
				<ClayTabs.Item
					active={activeIndex === 1}
					innerProps={{
						'aria-controls': 'tabpanel-sales-info'
					}}
					onClick={() => setActiveIndex(1)}
				>
					{Liferay.Language.get('sales-info')}
				</ClayTabs.Item>
				<ClayTabs.Item
					active={activeIndex === 2}
					innerProps={{
						'aria-controls': 'tabpanel-external-links'
					}}
					onClick={() => setActiveIndex(2)}
				>
					{Liferay.Language.get('external-links')}
				</ClayTabs.Item>
				<ClayTabs.Item
					className="panel-collapse"
					onClick={() => setCollapse(!collapse)}
				>
					<svg>
						<use xlinkHref="#collapse" />
					</svg>
				</ClayTabs.Item>
			</ClayTabs>

			<ClayTabs.Content activeIndex={activeIndex}>
				<ClayTabs.TabPane aria-labelledby="tab-notes">
					{'Notes Tab Placeholder Text'}
				</ClayTabs.TabPane>
				<ClayTabs.TabPane aria-labelledby="tab-sales-info">
					{'Sales Info Tab Placeholder Text'}
				</ClayTabs.TabPane>
				<ClayTabs.TabPane aria-labelledby="tab-external-links">
					{'External Links Placeholder Text'}
				</ClayTabs.TabPane>
			</ClayTabs.Content>
		</>
	);
};

export default function SidePanel() {
	return (
		<ErrorBoundary>
			<CollapsiblePanel />
		</ErrorBoundary>
	);
}
