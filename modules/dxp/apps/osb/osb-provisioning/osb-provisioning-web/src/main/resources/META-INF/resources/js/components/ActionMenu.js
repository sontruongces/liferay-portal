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

import {ClayTooltipProvider} from '@clayui/tooltip';
import PropTypes from 'prop-types';
import React from 'react';

import {NOTE_TYPE_GENERAL, NOTE_TYPE_SALES} from '../utilities/constants';
import IconButton from './IconButton';

function ActionMenu({
	onEdit,
	onPinning,
	pinned = false,
	tabType = NOTE_TYPE_GENERAL
}) {
	return (
		<>
			<ClayTooltipProvider>
				<IconButton
					data-tooltip-align="top"
					labelName="edit-note-icon"
					onClick={onEdit}
					svgId="#edit"
					title={Liferay.Language.get('edit')}
				/>
			</ClayTooltipProvider>

			{tabType === NOTE_TYPE_GENERAL && (
				<Pinning onPinning={onPinning} pinned={pinned} />
			)}
		</>
	);
}

ActionMenu.propTypes = {
	onEdit: PropTypes.func,
	onPinning: PropTypes.func,
	pinned: PropTypes.bool,
	tabType: PropTypes.oneOf([NOTE_TYPE_GENERAL, NOTE_TYPE_SALES])
};

function Pinning({onPinning, pinned}) {
	return (
		<>
			{pinned ? (
				<ClayTooltipProvider>
					<IconButton
						data-tooltip-align="top"
						labelName="unpin-note-icon"
						onClick={onPinning}
						svgId="#unpin"
						title={Liferay.Language.get('unpin')}
					/>
				</ClayTooltipProvider>
			) : (
				<ClayTooltipProvider>
					<IconButton
						data-tooltip-align="top"
						labelName="pin-note-icon"
						onClick={onPinning}
						svgId="#pin"
						title={Liferay.Language.get('pin')}
					/>
				</ClayTooltipProvider>
			)}
		</>
	);
}

export default ActionMenu;
