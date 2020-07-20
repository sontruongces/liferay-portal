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
	FIELD_TYPE_TEXTAREA,
	FIELD_TYPE_TOGGLE
} from '../utilities/constants';
import HiddenForm from './HiddenForm';
import InlineEdit from './InlineEdit';

function DetailField({
	displayAs,
	displayValue,
	fieldLabel,
	fieldName = fieldLabel,
	formAction,
	formData,
	inputStyle,
	openExternalFn,
	options = [],
	type = FIELD_TYPE_TEXT,
	value
}) {
	const formRef = useRef();
	const [data, setData] = useState(formData);

	useEffect(() => {
		if (formRef.current && data[fieldName] !== formData[fieldName]) {
			if (
				type === FIELD_TYPE_TEXT ||
				type === FIELD_TYPE_TEXTAREA ||
				type === FIELD_TYPE_TOGGLE
			) {
				formRef.current.submit();
			}

			if (type === FIELD_TYPE_SELECT && data[fieldName] !== '') {
				formRef.current.submit();
			}
		}
	}, [data, fieldName, formData, type]);

	function handleOpenExternal() {
		if (openExternalFn) {
			openExternalFn();
		}
	}

	function handleSubmit(value) {
		setData({...formData, [fieldName]: value});
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				{fieldLabel && (
					<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>
				)}

				<div className="list-group-text">
					{type === FIELD_TYPE_NONEDITABLE && <>{value}</>}

					{type === FIELD_TYPE_EXTERNAL && (
						<InlineEdit
							displayAs={displayAs}
							displayValue={displayValue}
							fieldName={fieldName}
							fieldValue={value}
							inputStyle={inputStyle}
							options={options}
							save={handleOpenExternal}
							type={type}
						/>
					)}

					{type !== FIELD_TYPE_EXTERNAL &&
						type !== FIELD_TYPE_NONEDITABLE && (
							<>
								<HiddenForm
									fields={data}
									formAction={formAction}
									ref={formRef}
								/>

								<InlineEdit
									displayAs={displayAs}
									displayValue={displayValue}
									fieldName={fieldName}
									fieldValue={value}
									inputStyle={inputStyle}
									options={options}
									save={handleSubmit}
									type={type}
								/>
							</>
						)}
				</div>
			</div>
		</ClayList.Item>
	);
}

DetailField.propTypes = {
	displayAs: PropTypes.oneOf(['label', 'text']),
	displayValue: PropTypes.string,
	fieldLabel: PropTypes.string,
	fieldName: PropTypes.string,
	formAction: PropTypes.string,
	formData: PropTypes.object,
	inputStyle: PropTypes.string,
	openExternalFn: PropTypes.func,
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
		FIELD_TYPE_TEXTAREA,
		FIELD_TYPE_TOGGLE
	]),
	value: PropTypes.string.isRequired
};

export default DetailField;
