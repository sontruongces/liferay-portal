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
