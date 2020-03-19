import {cleanup, render} from '@testing-library/react';
import React from 'react';

import SalesInfoTabPane from '../../src/main/resources/META-INF/resources/js/components/SalesInfoTabPane';

function renderSalesInfoTabPane() {
	return render(<SalesInfoTabPane />);
}

describe('SalesInfoTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSalesInfoTabPane();

		expect(container).toBeTruthy();
	});
});
