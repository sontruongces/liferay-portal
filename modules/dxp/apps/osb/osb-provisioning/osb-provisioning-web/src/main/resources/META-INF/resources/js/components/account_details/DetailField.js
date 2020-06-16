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
import React, {useEffect, useRef, useState} from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TOGGLE
} from '../../utilities/constants';
import HiddenForm from '../HiddenForm';
import InlineEdit from '../InlineEdit';

function DetailField({
	children,
	displayAs,
	fieldLabel,
	fieldName = fieldLabel,
	formAction,
	formData,
	inputStyle,
	options = [],
	type = FIELD_TYPE_TEXT
}) {
	const formRef = useRef();

	const [data, setData] = useState(formData);

	useEffect(() => {
		if (!Object.is(data, formData)) {
			formRef.current.submit();
		}
	}, [data, formData]);

	function handleSubmit(fieldName, value) {
		setData({...formData, [fieldName]: value});
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>

				<div className="list-group-text">
					{type === FIELD_TYPE_NONEDITABLE ? (
						<>{children}</>
					) : (
						<>
							<HiddenForm
								fields={data}
								formAction={formAction}
								ref={formRef}
							/>

							<InlineEdit
								displayAs={displayAs}
								fieldName={fieldName}
								inputStyle={inputStyle}
								options={options}
								save={handleSubmit}
								type={type}
							>
								{children}
							</InlineEdit>
						</>
					)}
				</div>
			</div>
		</ClayList.Item>
	);
}

DetailField.propTypes = {
	children: PropTypes.string,
	displayAs: PropTypes.oneOf(['label', 'text']),
	fieldLabel: PropTypes.string,
	fieldName: PropTypes.string,
	formAction: PropTypes.string,
	formData: PropTypes.object,
	inputStyle: PropTypes.string,
	options: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string
		})
	),
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_NONEDITABLE,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TOGGLE
	])
};

export default DetailField;
