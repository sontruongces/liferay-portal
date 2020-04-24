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

import {
	ADD_NOTE,
	ARCHIVE_NOTE,
	EDIT_NOTE,
	PIN_NOTE
} from './../utilities/constants';

export function addNote(note) {
	return {
		note,
		type: ADD_NOTE
	};
}

export function archiveNote(id, status) {
	return {
		id,
		status,
		type: ARCHIVE_NOTE
	};
}

export function editNote(id, content) {
	return {content, id, type: EDIT_NOTE};
}

export function pinNote(id, priority) {
	return {
		id,
		priority,
		type: PIN_NOTE
	};
}
