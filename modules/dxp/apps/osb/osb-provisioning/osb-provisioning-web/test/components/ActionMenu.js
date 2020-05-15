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

import {cleanup, render} from '@testing-library/react';
import React from 'react';

import ActionMenu from '../../src/main/resources/META-INF/resources/js/components/ActionMenu';
import {NOTE_TYPE_SALES} from '../../src/main/resources/META-INF/resources/js/utilities/constants';

function renderActionMenu(props) {
	return render(
		<ActionMenu onEdit={jest.fn()} onPinning={jest.fn()} {...props} />
	);
}

describe('ActionMenu', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderActionMenu();

		expect(container).toBeTruthy();
	});

	it('displays an edit icon', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('edit-note-icon');
	});

	it('displays a pin icon if note has not been pinned on Genearl Notes tab', () => {
		const {getByLabelText} = renderActionMenu();

		getByLabelText('pin-note-icon');
	});

	it('displays an unpin icon if note has been pinned on Genearl Notes tab', () => {
		const {getByLabelText} = renderActionMenu({pinned: true});

		getByLabelText('unpin-note-icon');
	});

	it('displays no pin or unpin icon on Sales Info tab', () => {
		const {queryByLabelText} = renderActionMenu({tabType: NOTE_TYPE_SALES});

		expect(queryByLabelText('pin-note-icon')).toBeFalsy();
		expect(queryByLabelText('unpin-note-icon')).toBeFalsy();
	});
});
