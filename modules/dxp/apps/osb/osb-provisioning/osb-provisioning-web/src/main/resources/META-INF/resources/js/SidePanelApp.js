import React from 'react';

import ErrorBoundary from './ErrorBoundary';
import SidePanel from './components/SidePanel';

export default () => (
	<ErrorBoundary>
		<SidePanel />
	</ErrorBoundary>
);
