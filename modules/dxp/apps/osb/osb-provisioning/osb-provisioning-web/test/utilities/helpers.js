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

import {mapNoteByKey} from '../../src/main/resources/META-INF/resources/js/utilities/helpers';

describe('helper functions', () => {
	describe('mapNoteByKey', () => {
		const notes = [
			{
				edited: true,
				htmlContent: '<div>Bernard Lowe</div>',
				key: '123',
				pinned: true,
				status: 'Approved'
			},
			{
				edited: false,
				htmlContent: '<div>Arnold Weber</div>',
				key: '321',
				pinned: false,
				status: 'Archived'
			}
		];

		it('returns a map whose key is the key property on the object', () => {
			const outputKeys = ['123', '321'];

			expect(Array.from(mapNoteByKey(notes).keys())).toEqual(outputKeys);
		});

		it('returns a map whose value is the object itself', () => {
			expect(Array.from(mapNoteByKey(notes).values())).toEqual(notes);
		});
	});
});
