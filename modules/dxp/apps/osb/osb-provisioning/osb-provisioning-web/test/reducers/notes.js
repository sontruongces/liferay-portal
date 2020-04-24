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
	addNote,
	archiveNote,
	editNote,
	pinNote
} from '../../src/main/resources/META-INF/resources/js/actions/notes';
import notesReducer from '../../src/main/resources/META-INF/resources/js/reducers/notes';
import {
	NOTE_PRIORITY_PINNED,
	NOTE_PRIORITY_UNPINNED,
	NOTE_STATUS_APPROVED,
	NOTE_STATUS_ARCHIVED
} from '../../src/main/resources/META-INF/resources/js/utilities/constants';
import {Note} from '../../src/main/resources/META-INF/resources/js/utilities/records';

const defaultNote = Note({id: 'id123'});
const note = Note({
	id: 'id456',
	pinned: true,
	status: NOTE_STATUS_ARCHIVED
});

const emptyState = Map();
const defaultState = emptyState.set(defaultNote.id, defaultNote);
const state = defaultState.set(note.id, note);

describe('notes reducer', () => {
	describe('add note', () => {
		it('adds a note to the store', () => {
			const store = notesReducer(emptyState, addNote(defaultNote));

			expect(store.get(defaultNote.id)).toEqual(defaultNote);
		});
	});

	describe('archive note', () => {
		it("updates the existing note's status from Approved to Archived in the store", () => {
			expect(state.getIn([defaultNote.id, 'status'])).toBe(
				NOTE_STATUS_APPROVED
			);

			const store = notesReducer(
				state,
				archiveNote(defaultNote.id, NOTE_STATUS_ARCHIVED)
			);

			expect(store.get(defaultNote.id).status).toBe(NOTE_STATUS_ARCHIVED);
		});

		it("updates the existing note's status from Archived to Approved in the store", () => {
			expect(state.getIn([note.id, 'status'])).toBe(NOTE_STATUS_ARCHIVED);

			const store = notesReducer(
				state,
				archiveNote(note.id, NOTE_STATUS_APPROVED)
			);

			expect(store.getIn([note.id, 'status'])).toBe(NOTE_STATUS_APPROVED);
		});
	});

	describe('edit note', () => {
		it("updates the existing note's content in the store", () => {
			const store = notesReducer(
				state,
				editNote(note.id, 'This note has been edited!')
			);

			expect(store.getIn([note.id, 'content'])).toBe(
				'This note has been edited!'
			);
		});

		it('marks an existing note in the store as edited after the first edit', () => {
			expect(state.getIn([note.id, 'edited'])).toBeFalsy();

			const store = notesReducer(
				state,
				editNote(note.id, 'This note has been edited!')
			);

			expect(store.getIn([note.id, 'edited'])).toBeTruthy();
		});
	});

	describe('pin note', () => {
		it("updates the existing note's priority from Pinned to Unpinned in the store", () => {
			expect(state.getIn([note.id, 'pinned'])).toBe(true);

			const store = notesReducer(
				state,
				pinNote(note.id, NOTE_PRIORITY_UNPINNED)
			);

			expect(store.getIn([note.id, 'pinned'])).toBe(false);
		});

		it("updates the existing note's priority from Unpinned to Pinned in the store", () => {
			expect(state.getIn([defaultNote.id, 'pinned'])).toBe(false);

			const store = notesReducer(
				state,
				pinNote(defaultNote.id, NOTE_PRIORITY_PINNED)
			);

			expect(store.getIn([defaultNote.id, 'pinned'])).toBe(true);
		});
	});

	describe('invalid action', () => {
		it('returns the previous state when an invalid action was supplied', () => {
			const store = notesReducer(state, 'Test Action');

			expect(store.get(defaultNote.id)).toEqual(defaultNote);
			expect(store.get(note.id)).toEqual(note);
		});
	});
});
