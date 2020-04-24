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

import {Map} from 'immutable';

import {
	ADD_NOTE,
	ARCHIVE_NOTE,
	EDIT_NOTE,
	PIN_NOTE
} from './../utilities/constants';

const initialState = Map();

export default function notes(state = initialState, action) {
	switch (action.type) {
		case ADD_NOTE:
			return state.set(action.note.id, action.note);
		case ARCHIVE_NOTE:
			return state.setIn([action.id, 'status'], action.status);
		case EDIT_NOTE:
			return state
				.setIn([action.id, 'content'], action.content)
				.setIn([action.id, 'edited'], true);
		case PIN_NOTE:
			return state.setIn([action.id, 'pinned'], action.priority === 1);
		default:
			return state;
	}
}
