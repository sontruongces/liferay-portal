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
 * Creates a new Map for all the notes, where
 * each key is the note's key and
 * each value is the corresponding note object.
 * @param {array} notes The notes array
 * @returns {Map<String, Object>} A map of all the notes
 */
export function mapNoteByKey(notes) {
	const map = new Map();

	notes.map(note => map.set(note.key, note));

	return map;
}
