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

import InlineEdit from '../InlineEdit';

function DetailField({children, editable = true, name, save, type = 'input'}) {
	return (
		<ClayList.Item flex>
			<div className="account-field">
				<ClayList.ItemTitle>{name}</ClayList.ItemTitle>

				<div className="list-group-text">
					{editable && (
						<InlineEdit fieldName="name" save={save} type={type}>
							{children}
						</InlineEdit>
					)}

					{!editable && <>{children}</>}
				</div>
			</div>
		</ClayList.Item>
	);
}

DetailField.propTypes = {
	children: PropTypes.node,
	editable: PropTypes.bool,
	name: PropTypes.string,
	save: PropTypes.func,
	type: PropTypes.oneOf(['external', 'input', 'radio', 'select'])
};

export default DetailField;
