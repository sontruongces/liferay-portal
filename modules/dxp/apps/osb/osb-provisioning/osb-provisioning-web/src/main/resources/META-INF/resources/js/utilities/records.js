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

import {Record} from 'immutable';

import {
	NOTE_FORMAT_PLAIN,
	NOTE_STATUS_APPROVED,
	NOTE_TYPE_GENERAL
} from './constants';

export const Note = Record({
	content: '',
	createDate: null,
	creatorName: '-',
	creatorPortraitURL: null,
	edited: false,
	format: NOTE_FORMAT_PLAIN,
	id: null,
	pinned: false,
	status: NOTE_STATUS_APPROVED,
	type: NOTE_TYPE_GENERAL,
	updateNoteURL: null
});
