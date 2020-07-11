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

import {FIELD_TYPE_TEXTAREA, NAMESPACE} from '../../utilities/constants';
import LiveUpdateableField from './LiveUpdateableField';

function Instructions({
	accountAttachmentURL,
	fileName,
	instructions,
	updateAccountAttachmentURL,
	updateInstructionsURL
}) {
	function handleUpdateSupportInstructions(instructions) {
		return {
			instructions
		};
	}

	return (
		<ClayList className="support-instructions">
			<ClayList.Header>
				{Liferay.Language.get('instructions')}
			</ClayList.Header>

			<ClayList.Item flex>
				<div className="detail-field">
					<ClayList.ItemTitle>
						{Liferay.Language.get('oem-instructions')}
					</ClayList.ItemTitle>

					{!!fileName && (
						<a
							className="account-attachment"
							href={accountAttachmentURL}
							target="_blank"
						>
							{fileName}
						</a>
					)}

					<label
						className="form-control-label"
						htmlFor={`${NAMESPACE}oemInstructions`}
					>
						<input
							className="form-control"
							id={`${NAMESPACE}oemInstructions`}
							type="file"
						/>
					</label>
				</div>
			</ClayList.Item>

			<LiveUpdateableField
				fieldLabel={Liferay.Language.get('support-instructions')}
				fieldName="instructions"
				formAction={updateInstructionsURL}
				type={FIELD_TYPE_TEXTAREA}
				updateFormData={handleUpdateSupportInstructions}
				value={instructions}
			/>
		</ClayList>
	);
}

Instructions.propTypes = {
	accountAttachmentURL: PropTypes.string,
	fileName: PropTypes.string,
	instructions: PropTypes.string,
	updateAccountAttachmentURL: PropTypes.string,
	updateInstructionsURL: PropTypes.string
};

export default Instructions;
