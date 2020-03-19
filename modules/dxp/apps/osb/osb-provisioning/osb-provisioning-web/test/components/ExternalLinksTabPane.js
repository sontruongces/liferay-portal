import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ExternalLinksTabPane from '../../src/main/resources/META-INF/resources/js/components/ExternalLinksTabPane';

function renderExternalLinksTabPane() {
	return render(<ExternalLinksTabPane />);
}

describe('ExternalLinksTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderExternalLinksTabPane();

		expect(container).toBeTruthy();
	});
});
