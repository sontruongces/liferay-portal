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

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TOGGLE
} from '../../utilities/constants';
import InlineEdit from '../InlineEdit';

function DetailField({
	children,
	displayAs,
	inputStyle,
	name,
	options,
	save,
	type = FIELD_TYPE_TEXT
}) {
	return (
		<ClayList.Item flex>
			<div className="account-field">
				<ClayList.ItemTitle>{name}</ClayList.ItemTitle>

				<div className="list-group-text">
					{type === FIELD_TYPE_NONEDITABLE ? (
						<>{children}</>
					) : (
						<InlineEdit
							displayAs={displayAs}
							fieldName={name}
							inputStyle={inputStyle}
							options={options}
							save={save}
							type={type}
						>
							{children}
						</InlineEdit>
					)}
				</div>
			</div>
		</ClayList.Item>
	);
}

DetailField.propTypes = {
	children: PropTypes.string,
	displayAs: PropTypes.oneOf(['label', 'text']),
	inputStyle: PropTypes.string,
	name: PropTypes.string,
	options: PropTypes.arrayOf(PropTypes.string),
	save: PropTypes.func,
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_NONEDITABLE,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TOGGLE
	])
};

export default DetailField;
