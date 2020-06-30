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
import React, {useState} from 'react';

import {
	FIELD_TYPE_EXTERNAL,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	FIELD_TYPE_TOGGLE,
	NAMESPACE
} from '../utilities/constants';
import {convertDashToEmptyString} from '../utilities/helpers';

function InlineEdit({
	children,
	displayAs = 'text',
	fieldName,
	inputStyle = '',
	options = [{label: '', value: ''}],
	save,
	type = FIELD_TYPE_TEXT
}) {
	const [fieldEditable, setFieldEditable] = useState(false);
	const [showEditor, setShowEditor] = useState(false);
	const [value, setValue] = useState(children);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleCancel() {
		setFieldEditable(false);
		setShowEditor(false);
		setValue(children);
	}

	function handleChange(event) {
		setValue(event.currentTarget.value);
	}

	function handleToggle() {
		setValue(!convertDashToEmptyString(value));
	}

	function handleSave() {
		save(value);
	}

	return (
		<div className="inline-edit">
			{!showEditor && (
				<div
					onClick={() => setShowEditor(true)}
					onMouseEnter={() => setFieldEditable(true)}
					onMouseLeave={() => setFieldEditable(false)}
				>
					{fieldEditable ? (
						<div className="editable-field">
							<div className="field">{value}</div>
							<div className="edit-icon">
								<svg
									aria-label={Liferay.Language.get(
										'edit-field-icon'
									)}
									className="lexicon-icon-pencil"
									role="img"
								>
									<use xlinkHref="#pencil" />
								</svg>
							</div>
						</div>
					) : displayAs === 'label' ? (
						<Label inputStyle={inputStyle} value={value} />
					) : (
						value
					)}
				</div>
			)}

			{showEditor && (
				<>
					{type === FIELD_TYPE_SELECT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<select
								className="form-control"
								disabled={options.length === 0}
								id={namespacedFieldName}
								onChange={handleChange}
								value={value}
							>
								{options.map(option => (
									<option
										key={option.value}
										value={option.value}
									>
										{option.label}
									</option>
								))}
							</select>
						</label>
					)}

					{type === FIELD_TYPE_TEXT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<input
								className="form-control"
								id={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={value}
							/>
						</label>
					)}

					{type === FIELD_TYPE_TOGGLE && (
						<label
							className="simple-toggle-switch toggle-switch"
							htmlFor={namespacedFieldName}
						>
							<span className="toggle-switch-check-bar">
								<input
									className="toggle-switch-check"
									id={namespacedFieldName}
									onChange={handleToggle}
									type="checkbox"
									value={value}
								/>
								<span
									aria-hidden="true"
									className="toggle-switch-bar"
								>
									<span className="toggle-switch-handle"></span>
								</span>
							</span>
						</label>
					)}

					<div className="button-row" role="group">
						<button
							className="btn btn-primary btn-sm save-btn"
							onClick={handleSave}
							role="button"
							type="button"
						>
							{Liferay.Language.get('save')}
						</button>

						<button
							className="btn btn-secondary btn-sm cancel-btn"
							onClick={handleCancel}
							role="button"
							type="button"
						>
							{Liferay.Language.get('cancel')}
						</button>
					</div>
				</>
			)}
		</div>
	);
}

function Label({inputStyle, value}) {
	return <span className={`label ${inputStyle}`}>{value}</span>;
}

InlineEdit.propTypes = {
	children: PropTypes.string,
	displayAs: PropTypes.oneOf(['label', 'text']),
	fieldName: PropTypes.string,
	inputStyle: PropTypes.string,
	options: PropTypes.arrayOf(
		PropTypes.shape({
			label: PropTypes.string,
			value: PropTypes.string
		})
	),
	save: PropTypes.func.isRequired,
	type: PropTypes.oneOf([
		FIELD_TYPE_EXTERNAL,
		FIELD_TYPE_SELECT,
		FIELD_TYPE_TEXT,
		FIELD_TYPE_TOGGLE
	])
};

export default InlineEdit;
