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

package com.liferay.osb.koroneiki.phloem.rest.graphql.v1_0.test;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.AuditEntry;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseAuditEntryGraphQLTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testGetAuditEntry() throws Exception {
		Assert.assertTrue(true);
	}

	protected boolean equals(AuditEntry auditEntry, JSONObject jsonObject) {
		List<String> fieldNames = new ArrayList(
			Arrays.asList(getAdditionalAssertFieldNames()));

		fieldNames.add("id");

		for (String fieldName : fieldNames) {
			if (Objects.equals("auditSetId", fieldName)) {
				if (!Objects.equals(
						auditEntry.getAuditSetId(),
						(Long)jsonObject.getLong("auditSetId"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("className", fieldName)) {
				if (!Objects.equals(
						auditEntry.getClassName(),
						(String)jsonObject.getString("className"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("classPK", fieldName)) {
				if (!Objects.equals(
						auditEntry.getClassPK(),
						(Long)jsonObject.getLong("classPK"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", fieldName)) {
				if (!Objects.equals(
						auditEntry.getDescription(),
						(String)jsonObject.getString("description"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("field", fieldName)) {
				if (!Objects.equals(
						auditEntry.getField(),
						(String)jsonObject.getString("field"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fieldClassName", fieldName)) {
				if (!Objects.equals(
						auditEntry.getFieldClassName(),
						(String)jsonObject.getString("fieldClassName"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("fieldClassPK", fieldName)) {
				if (!Objects.equals(
						auditEntry.getFieldClassPK(),
						(Long)jsonObject.getLong("fieldClassPK"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("key", fieldName)) {
				if (!Objects.equals(
						auditEntry.getKey(),
						(String)jsonObject.getString("key"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("newValue", fieldName)) {
				if (!Objects.equals(
						auditEntry.getNewValue(),
						(String)jsonObject.getString("newValue"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("oldValue", fieldName)) {
				if (!Objects.equals(
						auditEntry.getOldValue(),
						(String)jsonObject.getString("oldValue"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userId", fieldName)) {
				if (!Objects.equals(
						auditEntry.getUserId(),
						(Long)jsonObject.getLong("userId"))) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userName", fieldName)) {
				if (!Objects.equals(
						auditEntry.getUserName(),
						(String)jsonObject.getString("userName"))) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected AuditEntry randomAuditEntry() throws Exception {
		return new AuditEntry() {
			{
				auditSetId = RandomTestUtil.randomLong();
				className = RandomTestUtil.randomString();
				classPK = RandomTestUtil.randomLong();
				dateCreated = RandomTestUtil.nextDate();
				description = RandomTestUtil.randomString();
				field = RandomTestUtil.randomString();
				fieldClassName = RandomTestUtil.randomString();
				fieldClassPK = RandomTestUtil.randomLong();
				key = RandomTestUtil.randomString();
				newValue = RandomTestUtil.randomString();
				oldValue = RandomTestUtil.randomString();
				userId = RandomTestUtil.randomLong();
				userName = RandomTestUtil.randomString();
			}
		};
	}

	protected Company testCompany;
	protected Group testGroup;

	private String _invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		JSONObject jsonObject = JSONUtil.put("query", query);

		httpInvoker.body(jsonObject.toString(), "application/json");

		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	private class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(":");
					sb.append(entry.getValue());
					sb.append(",");
				}

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

}