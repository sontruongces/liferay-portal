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

import React, {useContext, useState} from 'react';

import {mapNoteByKey} from '../utilities/helpers';

const NotesContext = React.createContext([[], () => {}]);

export function NotesProvider({initialNotes = [], children}) {
	// TODO: modify and then call mapNoteByKey
	const processedNotes = initialNotes;

	const [notes, setNotes] = useState(processedNotes);

	return (
		<NotesContext.Provider value={[notes, setNotes]}>
			{children}
		</NotesContext.Provider>
	);
}

export function useNotes() {
	return useContext(NotesContext);
}
