/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import '@testing-library/jest-dom/extend-expect';
import {cleanup, render} from '@testing-library/react';
import React from 'react';

import SegmentsExperimentsClickGoal from '../../../src/main/resources/META-INF/resources/js/components/ClickGoalPicker/ClickGoalPicker.es';
import {segmentsExperiment} from '../fixtures.es';

describe('SegmentsExperimentsClickGoal', () => {
	afterEach(cleanup);

	it('renders when goal value is "click"', () => {
		const experiment = {
			...segmentsExperiment,
			goal: {
				value: 'click'
			}
		};

		const {asFragment} = render(
			<SegmentsExperimentsClickGoal segmentsExperiment={experiment} />
		);

		expect(asFragment().children.length).not.toBe(0);
	});

	test.todo('actually make the one above a meaningful test');
});
