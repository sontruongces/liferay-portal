import {cleanup, render} from '@testing-library/react';
import React from 'react';

import SalesInfoTabPane from '../../src/main/resources/META-INF/resources/js/components/SalesInfoTabPane';

function renderSalesInfoTabPane() {
	return render(<SalesInfoTabPane info={{}} />);
}

describe('SalesInfoTabPane', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderSalesInfoTabPane();

		expect(container).toBeTruthy();
	});

	it('displays a message when there is no data', () => {
		const {container} = render(<SalesInfoTabPane />);

		expect(container.textContent).toEqual('no-sales-info-were-found');
	});
});
