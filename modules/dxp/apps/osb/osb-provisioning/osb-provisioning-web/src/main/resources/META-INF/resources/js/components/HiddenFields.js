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

import PropTypes from 'prop-types';
import React from 'react';

import {NAMESPACE} from '../utilities/constants';

function HiddenFields({data}) {
	return Object.entries(data).map(([key, value]) => (
		<input
			key={`${NAMESPACE}${key}`}
			name={`${NAMESPACE}${key}`}
			type="hidden"
			value={value}
		/>
	));
}

HiddenFields.propTypes = {
	data: PropTypes.object
};

export default HiddenFields;
