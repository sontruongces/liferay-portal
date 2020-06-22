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

package com.liferay.portal.file.install.internal.properties;

import static com.liferay.portal.file.install.internal.properties.InterpolationUtil.substVars;

import com.liferay.petra.string.CharPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import java.net.URL;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Matthew Tambara
 */
public class TypedProperties extends AbstractMap<String, Object> {

	public static final String ENV_PREFIX = "env:";

	public static Map<String, Map<String, String>> prepare(
		Map<String, TypedProperties> properties) {

		Map<String, Map<String, String>> dynamic = new HashMap<>();

		for (Map.Entry<String, TypedProperties> entry : properties.entrySet()) {
			String name = entry.getKey();

			TypedProperties typedProperties = entry.getValue();

			dynamic.put(name, new DynamicMap(name, typedProperties._storage));
		}

		return dynamic;
	}

	public static void substitute(
		Map<String, TypedProperties> properties,
		Map<String, Map<String, String>> dynamic, SubstitutionCallback callback,
		boolean finalSubstitution) {

		for (Map<String, String> map : dynamic.values()) {
			DynamicMap dynamicMap = (DynamicMap)map;

			dynamicMap.init(callback, finalSubstitution);
		}

		for (Map.Entry<String, TypedProperties> entry : properties.entrySet()) {
			TypedProperties typedProperties = entry.getValue();

			typedProperties._storage.putAllSubstituted(
				dynamic.get(entry.getKey()));
		}
	}

	public TypedProperties() {
		this(null, true);
	}

	public TypedProperties(boolean substitute) {
		this(null, substitute);
	}

	public TypedProperties(SubstitutionCallback callback) {
		this(callback, true);
	}

	public TypedProperties(SubstitutionCallback callback, boolean substitute) {
		_storage = new Properties(false);
		_callback = callback;
		_substitute = substitute;
	}

	@Override
	public void clear() {
		_storage.clear();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return new AbstractSet<Entry<String, Object>>() {

			@Override
			public Iterator<Entry<String, Object>> iterator() {
				return new KeyIterator();
			}

			@Override
			public int size() {
				return _storage.size();
			}

		};
	}

	@Override
	public Object get(Object key) {
		String value = _storage.get(key);

		if ((value != null) && _storage.isTyped()) {
			return _convertFromString(value);
		}

		return value;
	}

	public List<String> getComments(String key) {
		return _storage.getComments(key);
	}

	public List<String> getFooter() {
		return _storage.getFooter();
	}

	public List<String> getHeader() {
		return _storage.getHeader();
	}

	public List<String> getRaw(String key) {
		return _storage.getRaw(key);
	}

	public void load(File file) throws IOException {
		try (InputStream inputStream = new FileInputStream(file)) {
			load(inputStream);
		}
	}

	public void load(InputStream inputStream) throws IOException {
		try (InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, Properties.DEFAULT_ENCODING)) {

			load(inputStreamReader);
		}
	}

	public void load(Reader reader) throws IOException {
		_storage.loadLayout(reader, true);

		substitute(_callback);
	}

	public void load(URL url) throws IOException {
		try (InputStream inputStream = url.openStream()) {
			load(inputStream);
		}
	}

	public Object put(
		String key, List<String> commentLines, List<String> valueLines) {

		String old = _storage.put(key, commentLines, valueLines);

		if (old == null) {
			return null;
		}

		if (_storage.isTyped()) {
			return _convertFromString(old);
		}

		return old;
	}

	public Object put(String key, List<String> commentLines, Object value) {
		if ((value instanceof String) && !_storage.isTyped()) {
			return _storage.put(key, commentLines, (String)value);
		}

		_ensureTyped();

		String string = _convertToString(value);

		return put(key, commentLines, Arrays.asList(string.split("\n")));
	}

	@Override
	public Object put(String key, Object value) {
		if ((value instanceof String) && !_storage.isTyped()) {
			return _storage.put(key, (String)value);
		}

		_ensureTyped();

		String old = _storage.put(key, _convertToString(value));

		if (old == null) {
			return null;
		}

		return _convertFromString(old);
	}

	public Object put(String key, String comment, Object value) {
		return put(key, Collections.singletonList(comment), value);
	}

	@Override
	public Object remove(Object key) {
		return _storage.remove(key);
	}

	public void save(File location) throws IOException {
		_storage.save(location);
	}

	public void save(OutputStream outputStream) throws IOException {
		_storage.save(outputStream);
	}

	public void save(Writer writer) throws IOException {
		_storage.save(writer);
	}

	public void setFooter(List<String> footer) {
		_storage.setFooter(footer);
	}

	public void setHeader(List<String> header) {
		_storage.setHeader(header);
	}

	public void store(OutputStream outputStream, String comment)
		throws IOException {

		_storage.store(outputStream, comment);
	}

	public void substitute(final SubstitutionCallback substitutionCallback) {
		if (!_substitute) {
			return;
		}

		SubstitutionCallback callback = substitutionCallback;

		if (callback == null) {
			callback = new SubstitutionCallback() {

				@Override
				public String getValue(String name, String key, String value) {
					if (value.startsWith(ENV_PREFIX)) {
						return System.getenv(
							value.substring(ENV_PREFIX.length()));
					}

					return System.getProperty(value);
				}

			};
		}

		Map<String, TypedProperties> map = Collections.singletonMap(
			"root", this);

		substitute(map, prepare(map), callback, true);
	}

	public boolean update(Map<String, Object> map) {
		TypedProperties typedProperties = new TypedProperties();

		if (map instanceof TypedProperties) {
			typedProperties = (TypedProperties)map;
		}
		else {
			for (Entry<String, Object> entry : map.entrySet()) {
				typedProperties.put(entry.getKey(), entry.getValue());
			}
		}

		return update(typedProperties);
	}

	public boolean update(TypedProperties properties) {
		return _storage.update(properties._storage);
	}

	public interface SubstitutionCallback {

		public String getValue(String name, String key, String value);

	}

	private static Object _convertFromString(String value) {
		try {
			return ConfigurationHandler.read(value);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private static String _convertToString(Object value) {
		try {
			return ConfigurationHandler.write(value);
		}
		catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}

	private void _ensureTyped() {
		if (!_storage.isTyped()) {
			_storage.setTyped(true);

			Set<String> keys = new HashSet<>(_storage.keySet());

			for (String key : keys) {
				String string = _convertToString(_storage.get(key));

				_storage.put(
					key, _storage.getComments(key),
					Arrays.asList(string.split("\n")));
			}
		}
	}

	private final SubstitutionCallback _callback;
	private final Properties _storage;
	private final boolean _substitute;

	private static class DynamicMap extends AbstractMap<String, String> {

		public DynamicMap(String name, Properties storage) {
			_name = name;
			_storage = storage;
		}

		@Override
		public Set<Entry<String, String>> entrySet() {
			return new AbstractSet<Entry<String, String>>() {

				@Override
				public Iterator<Entry<String, String>> iterator() {
					Set<String> keys = _storage.keySet();

					return new ComputedIterator(keys.iterator());
				}

				@Override
				public int size() {
					return _storage.size();
				}

			};
		}

		public void init(
			SubstitutionCallback callback, boolean finalSubstitution) {

			_callback = callback;
			_finalSubstitution = finalSubstitution;
		}

		private String _compute(final String key) {
			InterpolationUtil.SubstitutionCallback wrapper =
				new InterpolationUtil.SubstitutionCallback() {

					@Override
					public String getValue(String value) {
						String string = DynamicMap.this.get(value);

						if (!_finalSubstitution || (string == null)) {
							return _callback.getValue(_name, key, value);
						}

						if (!_storage.isTyped()) {
							return string;
						}

						boolean mult = false;

						boolean hasType = false;

						char t = string.charAt(0);

						if ((t == CharPool.OPEN_BRACKET) ||
							(t == CharPool.OPEN_PARENTHESIS)) {

							mult = true;
						}
						else {
							t = string.charAt(1);

							if ((t == CharPool.OPEN_BRACKET) ||
								(t == CharPool.OPEN_PARENTHESIS)) {

								mult = true;
							}

							hasType = true;
						}

						if (mult) {
							throw new IllegalArgumentException(
								"Cannot substitute from a collection/array " +
									"value: " + value);
						}

						if (hasType) {
							return (String)_convertFromString(
								string.substring(1));
						}

						return (String)_convertFromString(string);
					}

				};

			String value = _storage.get(key);

			return substVars(
				value, key, _cycles, this, wrapper, false, _finalSubstitution,
				_finalSubstitution);
		}

		private SubstitutionCallback _callback;
		private final Map<String, String> _computed = new HashMap<>();
		private final Map<String, String> _cycles = new HashMap<>();
		private boolean _finalSubstitution;
		private final String _name;
		private final Properties _storage;

		private class ComputedIterator
			implements Iterator<Entry<String, String>> {

			public ComputedIterator(Iterator<String> iterator) {
				_iterator = iterator;
			}

			@Override
			public boolean hasNext() {
				return _iterator.hasNext();
			}

			@Override
			public Entry<String, String> next() {
				String key = _iterator.next();

				return new Entry<String, String>() {

					@Override
					public String getKey() {
						return key;
					}

					@Override
					public String getValue() {
						String v = _computed.get(key);

						if (v == null) {
							v = _compute(key);
						}

						return v;
					}

					@Override
					public String setValue(String value) {
						throw new UnsupportedOperationException();
					}

				};
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			private final Iterator<String> _iterator;

		}

	}

	private class KeyIterator implements Iterator {

		public KeyIterator() {
			Set<String> entries = _storage.keySet();

			_iterator = entries.iterator();
		}

		@Override
		public boolean hasNext() {
			return _iterator.hasNext();
		}

		@Override
		public Entry<String, Object> next() {
			String key = _iterator.next();

			return new Entry<String, Object>() {

				@Override
				public String getKey() {
					return key;
				}

				@Override
				public Object getValue() {
					return TypedProperties.this.get(key);
				}

				@Override
				public Object setValue(Object value) {
					return TypedProperties.this.put(key, value);
				}

			};
		}

		@Override
		public void remove() {
			_iterator.remove();
		}

		private final Iterator<String> _iterator;

	}

}