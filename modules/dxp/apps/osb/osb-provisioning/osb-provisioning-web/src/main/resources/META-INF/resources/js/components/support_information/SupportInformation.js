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

import Instructions from './Instructions';
import SupportDetails from './SupportDetails';

function SupportInformation({
	account,
	accountAttachmentURL,
	instructions,
	language,
	languageList,
	oemInstructionsFileName,
	regionNames,
	updateAccountAttachmentURL,
	updateAccountURL,
	updateInstructionsURL,
	updateLanguageIdURL
}) {
	return (
		<>
			<SupportDetails
				account={account}
				language={language}
				languageList={languageList}
				regionNames={regionNames}
				updateAccountURL={updateAccountURL}
				updateLanguageIdURL={updateLanguageIdURL}
			/>

			<Instructions
				accountAttachmentURL={accountAttachmentURL}
				fileName={oemInstructionsFileName}
				instructions={instructions}
				updateAccountAttachmentURL={updateAccountAttachmentURL}
				updateInstructionsURL={updateInstructionsURL}
			/>
		</>
	);
}

SupportInformation.propTypes = {
	account: PropTypes.shape({
		code: PropTypes.string,
		editAccountURL: PropTypes.string,
		key: PropTypes.string,
		name: PropTypes.string,
		region: PropTypes.string,
		status: PropTypes.string,
		tier: PropTypes.string
	}),
	accountAttachmentURL: PropTypes.string,
	instructions: PropTypes.string,
	language: PropTypes.shape({
		id: PropTypes.string,
		name: PropTypes.string
	}),
	languageList: PropTypes.arrayOf(
		PropTypes.shape({
			id: PropTypes.string,
			name: PropTypes.string
		})
	),
	oemInstructionsFileName: PropTypes.string,
	regionNames: PropTypes.arrayOf(PropTypes.string),
	updateAccountAttachmentURL: PropTypes.string,
	updateAccountURL: PropTypes.string,
	updateInstructionsURL: PropTypes.string,
	updateLanguageIdURL: PropTypes.string
};

export default SupportInformation;
