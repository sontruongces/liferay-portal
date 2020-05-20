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

import {NAMESPACE} from '../utilities/constants';

function InlineEdit({children, fieldName, submit}) {
	const [fieldEditable, setFieldEditable] = useState(false);
	const [showEditor, setShowEditor] = useState(false);
	const [value, setValue] = useState(children);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleCancel() {
		setFieldEditable(false);
		setShowEditor(false);
		setValue(children);
	}

	function handleSubmit() {
		submit(namespacedFieldName, value);
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
					) : (
						value
					)}
				</div>
			)}

			{showEditor && (
				<form method="post">
					<label
						className="form-control-label"
						htmlFor={namespacedFieldName}
					>
						<input
							className="form-control"
							id={namespacedFieldName}
							name={namespacedFieldName}
							onChange={event =>
								setValue(event.currentTarget.value)
							}
							value={value}
						/>
					</label>

					<div className="button-row">
						<button
							className="btn btn-primary btn-sm save-btn"
							onClick={handleSubmit}
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
				</form>
			)}
		</div>
	);
}

InlineEdit.propTypes = {
	children: PropTypes.string,
	fieldName: PropTypes.string,
	submit: PropTypes.func
};

export default InlineEdit;
