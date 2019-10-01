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

package com.liferay.portal.output.stream.container.internal;

import com.liferay.petra.io.DummyOutputStream;
import com.liferay.portal.output.stream.container.OutputStreamContainer;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactory;
import com.liferay.portal.output.stream.container.OutputStreamContainerFactoryTracker;

import java.io.OutputStream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mariano Álvaro Sáiz
 */
@Component(
	immediate = true,
	property = {
		"name=" + OutputStreamContainerFactoryTracker.DUMMY_OUTPUT_STREAM_CONTAINER_FACTORY_NAME,
		"service.ranking:Integer=-100"
	},
	service = OutputStreamContainerFactory.class
)
public class DummyOutputStreamContainerFactory
	implements OutputStreamContainerFactory {

	@Override
	public OutputStreamContainer create(String hint) {
		return _OUTPUT_STREAM_CONTAINER;
	}

	private static final DummyOutputStream _DUMMY_OUTPUT_STREAM =
		new DummyOutputStream();

	private static final OutputStreamContainer _OUTPUT_STREAM_CONTAINER =
		new OutputStreamContainer() {

			@Override
			public String getDescription() {
				return "Dummy";
			}

			@Override
			public OutputStream getOutputStream() {
				return _DUMMY_OUTPUT_STREAM;
			}

		};

}