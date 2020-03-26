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
