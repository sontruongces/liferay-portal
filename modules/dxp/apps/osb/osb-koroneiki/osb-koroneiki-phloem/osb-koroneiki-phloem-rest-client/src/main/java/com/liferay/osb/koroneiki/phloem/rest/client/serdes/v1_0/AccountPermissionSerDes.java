/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AccountPermission;
import com.liferay.osb.koroneiki.phloem.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public class AccountPermissionSerDes {

	public static AccountPermission toDTO(String json) {
		AccountPermissionJSONParser accountPermissionJSONParser =
			new AccountPermissionJSONParser();

		return accountPermissionJSONParser.parseToDTO(json);
	}

	public static AccountPermission[] toDTOs(String json) {
		AccountPermissionJSONParser accountPermissionJSONParser =
			new AccountPermissionJSONParser();

		return accountPermissionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AccountPermission accountPermission) {
		if (accountPermission == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (accountPermission.getAssignContact() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignContact\": ");

			sb.append(accountPermission.getAssignContact());
		}

		if (accountPermission.getAssignTeam() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"assignTeam\": ");

			sb.append(accountPermission.getAssignTeam());
		}

		if (accountPermission.getDelete() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"delete\": ");

			sb.append(accountPermission.getDelete());
		}

		if (accountPermission.getPermissions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"permissions\": ");

			sb.append(accountPermission.getPermissions());
		}

		if (accountPermission.getRoleNames() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"roleNames\": ");

			sb.append("[");

			for (int i = 0; i < accountPermission.getRoleNames().length; i++) {
				sb.append("\"");

				sb.append(_escape(accountPermission.getRoleNames()[i]));

				sb.append("\"");

				if ((i + 1) < accountPermission.getRoleNames().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (accountPermission.getUpdate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"update\": ");

			sb.append(accountPermission.getUpdate());
		}

		if (accountPermission.getView() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"view\": ");

			sb.append(accountPermission.getView());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AccountPermissionJSONParser accountPermissionJSONParser =
			new AccountPermissionJSONParser();

		return accountPermissionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		AccountPermission accountPermission) {

		if (accountPermission == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (accountPermission.getAssignContact() == null) {
			map.put("assignContact", null);
		}
		else {
			map.put(
				"assignContact",
				String.valueOf(accountPermission.getAssignContact()));
		}

		if (accountPermission.getAssignTeam() == null) {
			map.put("assignTeam", null);
		}
		else {
			map.put(
				"assignTeam",
				String.valueOf(accountPermission.getAssignTeam()));
		}

		if (accountPermission.getDelete() == null) {
			map.put("delete", null);
		}
		else {
			map.put("delete", String.valueOf(accountPermission.getDelete()));
		}

		if (accountPermission.getPermissions() == null) {
			map.put("permissions", null);
		}
		else {
			map.put(
				"permissions",
				String.valueOf(accountPermission.getPermissions()));
		}

		if (accountPermission.getRoleNames() == null) {
			map.put("roleNames", null);
		}
		else {
			map.put(
				"roleNames", String.valueOf(accountPermission.getRoleNames()));
		}

		if (accountPermission.getUpdate() == null) {
			map.put("update", null);
		}
		else {
			map.put("update", String.valueOf(accountPermission.getUpdate()));
		}

		if (accountPermission.getView() == null) {
			map.put("view", null);
		}
		else {
			map.put("view", String.valueOf(accountPermission.getView()));
		}

		return map;
	}

	public static class AccountPermissionJSONParser
		extends BaseJSONParser<AccountPermission> {

		@Override
		protected AccountPermission createDTO() {
			return new AccountPermission();
		}

		@Override
		protected AccountPermission[] createDTOArray(int size) {
			return new AccountPermission[size];
		}

		@Override
		protected void setField(
			AccountPermission accountPermission, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "assignContact")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setAssignContact(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "assignTeam")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setAssignTeam(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "delete")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setDelete((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "permissions")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setPermissions(
						(Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "roleNames")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setRoleNames(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "update")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setUpdate((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "view")) {
				if (jsonParserFieldValue != null) {
					accountPermission.setView((Boolean)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}