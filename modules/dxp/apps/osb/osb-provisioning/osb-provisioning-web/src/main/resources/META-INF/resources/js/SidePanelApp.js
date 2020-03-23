import React from 'react';

import ErrorBoundary from './ErrorBoundary';
import SidePanel from './components/SidePanel';

export default props => (
	<ErrorBoundary>
		<SidePanel {...props} />
	</ErrorBoundary>
);
