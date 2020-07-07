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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React from 'react';

import {FIELD_TYPE_TEXTAREA} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import DetailField from '../DetailField';

function Instructions({instructions, updateInstructionsURL}) {
	return (
		<ClayList className="support-instructions">
			<ClayList.Header>
				{Liferay.Language.get('instructions')}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('support-instructions')}
				fieldName="instructions"
				formAction={updateInstructionsURL}
				formData={{
					instructions: convertDashToEmptyString(instructions)
				}}
				type={FIELD_TYPE_TEXTAREA}
				value={instructions}
			/>
		</ClayList>
	);
}

Instructions.propTypes = {
	instructions: PropTypes.string,
	updateInstructionsURL: PropTypes.string
};

export default Instructions;
