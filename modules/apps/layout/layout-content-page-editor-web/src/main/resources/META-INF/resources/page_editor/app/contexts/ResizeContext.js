/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import React, {useContext} from 'react';

const INITIAL_STATE = {
	resizing: false,
	setResizing: () => null,
	setUpdatedLayoutData: () => null,
	updatedLayoutData: null,
};

const ResizeContext = React.createContext(INITIAL_STATE);

const ResizeContextProvider = ResizeContext.Provider;

const useResizing = () => {
	return useContext(ResizeContext).resizing;
};

const useSetResizing = () => {
	return useContext(ResizeContext).setResizing;
};

const useSetUpdatedLayoutData = () => {
	return useContext(ResizeContext).setUpdatedLayoutData;
};

const useUpdatedLayoutData = () => {
	return useContext(ResizeContext).updatedLayoutData;
};

export {
	ResizeContext,
	ResizeContextProvider,
	useResizing,
	useSetResizing,
	useSetUpdatedLayoutData,
	useUpdatedLayoutData,
};
