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

package com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseViewSerDes;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Amos Fong
 * @generated
 */
@Generated("")
public abstract class BaseProductPurchaseViewResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_productPurchaseViewResource.setContextCompany(testCompany);

		ProductPurchaseViewResource.Builder builder =
			ProductPurchaseViewResource.builder();

		productPurchaseViewResource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProductPurchaseView productPurchaseView1 = randomProductPurchaseView();

		String json = objectMapper.writeValueAsString(productPurchaseView1);

		ProductPurchaseView productPurchaseView2 =
			ProductPurchaseViewSerDes.toDTO(json);

		Assert.assertTrue(equals(productPurchaseView1, productPurchaseView2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		ProductPurchaseView productPurchaseView = randomProductPurchaseView();

		String json1 = objectMapper.writeValueAsString(productPurchaseView);
		String json2 = ProductPurchaseViewSerDes.toJSON(productPurchaseView);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		ProductPurchaseView productPurchaseView = randomProductPurchaseView();

		String json = ProductPurchaseViewSerDes.toJSON(productPurchaseView);

		Assert.assertFalse(json.contains(regex));

		productPurchaseView = ProductPurchaseViewSerDes.toDTO(json);
	}

	@Test
	public void testGetAccountAccountKeyProductPurchaseViewsPage()
		throws Exception {

		Page<ProductPurchaseView> page =
			productPurchaseViewResource.
				getAccountAccountKeyProductPurchaseViewsPage(
					testGetAccountAccountKeyProductPurchaseViewsPage_getAccountKey(),
					null, RandomTestUtil.randomString(),
					RandomTestUtil.randomString(), Pagination.of(1, 2));

		Assert.assertEquals(0, page.getTotalCount());

		String accountKey =
			testGetAccountAccountKeyProductPurchaseViewsPage_getAccountKey();
		String irrelevantAccountKey =
			testGetAccountAccountKeyProductPurchaseViewsPage_getIrrelevantAccountKey();

		if ((irrelevantAccountKey != null)) {
			ProductPurchaseView irrelevantProductPurchaseView =
				testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
					irrelevantAccountKey,
					randomIrrelevantProductPurchaseView());

			page =
				productPurchaseViewResource.
					getAccountAccountKeyProductPurchaseViewsPage(
						irrelevantAccountKey, null, null, null,
						Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantProductPurchaseView),
				(List<ProductPurchaseView>)page.getItems());
			assertValid(page);
		}

		ProductPurchaseView productPurchaseView1 =
			testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
				accountKey, randomProductPurchaseView());

		ProductPurchaseView productPurchaseView2 =
			testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
				accountKey, randomProductPurchaseView());

		page =
			productPurchaseViewResource.
				getAccountAccountKeyProductPurchaseViewsPage(
					accountKey, null, null, null, Pagination.of(1, 2));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(productPurchaseView1, productPurchaseView2),
			(List<ProductPurchaseView>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetAccountAccountKeyProductPurchaseViewsPageWithPagination()
		throws Exception {

		String accountKey =
			testGetAccountAccountKeyProductPurchaseViewsPage_getAccountKey();

		ProductPurchaseView productPurchaseView1 =
			testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
				accountKey, randomProductPurchaseView());

		ProductPurchaseView productPurchaseView2 =
			testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
				accountKey, randomProductPurchaseView());

		ProductPurchaseView productPurchaseView3 =
			testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
				accountKey, randomProductPurchaseView());

		Page<ProductPurchaseView> page1 =
			productPurchaseViewResource.
				getAccountAccountKeyProductPurchaseViewsPage(
					accountKey, null, null, null, Pagination.of(1, 2));

		List<ProductPurchaseView> productPurchaseViews1 =
			(List<ProductPurchaseView>)page1.getItems();

		Assert.assertEquals(
			productPurchaseViews1.toString(), 2, productPurchaseViews1.size());

		Page<ProductPurchaseView> page2 =
			productPurchaseViewResource.
				getAccountAccountKeyProductPurchaseViewsPage(
					accountKey, null, null, null, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<ProductPurchaseView> productPurchaseViews2 =
			(List<ProductPurchaseView>)page2.getItems();

		Assert.assertEquals(
			productPurchaseViews2.toString(), 1, productPurchaseViews2.size());

		Page<ProductPurchaseView> page3 =
			productPurchaseViewResource.
				getAccountAccountKeyProductPurchaseViewsPage(
					accountKey, null, null, null, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(
				productPurchaseView1, productPurchaseView2,
				productPurchaseView3),
			(List<ProductPurchaseView>)page3.getItems());
	}

	protected ProductPurchaseView
			testGetAccountAccountKeyProductPurchaseViewsPage_addProductPurchaseView(
				String accountKey, ProductPurchaseView productPurchaseView)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyProductPurchaseViewsPage_getAccountKey()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetAccountAccountKeyProductPurchaseViewsPage_getIrrelevantAccountKey()
		throws Exception {

		return null;
	}

	@Test
	public void testGetAccountAccountKeyProductProductKeyProductPurchaseView()
		throws Exception {

		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetAccountAccountKeyProductProductKeyProductPurchaseView()
		throws Exception {

		Assert.assertTrue(true);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		ProductPurchaseView productPurchaseView1,
		ProductPurchaseView productPurchaseView2) {

		Assert.assertTrue(
			productPurchaseView1 + " does not equal " + productPurchaseView2,
			equals(productPurchaseView1, productPurchaseView2));
	}

	protected void assertEquals(
		List<ProductPurchaseView> productPurchaseViews1,
		List<ProductPurchaseView> productPurchaseViews2) {

		Assert.assertEquals(
			productPurchaseViews1.size(), productPurchaseViews2.size());

		for (int i = 0; i < productPurchaseViews1.size(); i++) {
			ProductPurchaseView productPurchaseView1 =
				productPurchaseViews1.get(i);
			ProductPurchaseView productPurchaseView2 =
				productPurchaseViews2.get(i);

			assertEquals(productPurchaseView1, productPurchaseView2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<ProductPurchaseView> productPurchaseViews1,
		List<ProductPurchaseView> productPurchaseViews2) {

		Assert.assertEquals(
			productPurchaseViews1.size(), productPurchaseViews2.size());

		for (ProductPurchaseView productPurchaseView1 : productPurchaseViews1) {
			boolean contains = false;

			for (ProductPurchaseView productPurchaseView2 :
					productPurchaseViews2) {

				if (equals(productPurchaseView1, productPurchaseView2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				productPurchaseViews2 + " does not contain " +
					productPurchaseView1,
				contains);
		}
	}

	protected void assertEqualsJSONArray(
		List<ProductPurchaseView> productPurchaseViews, JSONArray jsonArray) {

		for (ProductPurchaseView productPurchaseView : productPurchaseViews) {
			boolean contains = false;

			for (Object object : jsonArray) {
				if (equalsJSONObject(productPurchaseView, (JSONObject)object)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				jsonArray + " does not contain " + productPurchaseView,
				contains);
		}
	}

	protected void assertValid(ProductPurchaseView productPurchaseView) {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("product", additionalAssertFieldName)) {
				if (productPurchaseView.getProduct() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"productConsumptions", additionalAssertFieldName)) {

				if (productPurchaseView.getProductConsumptions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("productPurchases", additionalAssertFieldName)) {
				if (productPurchaseView.getProductPurchases() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<ProductPurchaseView> page) {
		boolean valid = false;

		java.util.Collection<ProductPurchaseView> productPurchaseViews =
			page.getItems();

		int size = productPurchaseViews.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			graphQLFields.add(new GraphQLField(additionalAssertFieldName));
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		ProductPurchaseView productPurchaseView1,
		ProductPurchaseView productPurchaseView2) {

		if (productPurchaseView1 == productPurchaseView2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("product", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchaseView1.getProduct(),
						productPurchaseView2.getProduct())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"productConsumptions", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						productPurchaseView1.getProductConsumptions(),
						productPurchaseView2.getProductConsumptions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("productPurchases", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						productPurchaseView1.getProductPurchases(),
						productPurchaseView2.getProductPurchases())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equalsJSONObject(
		ProductPurchaseView productPurchaseView, JSONObject jsonObject) {

		for (String fieldName : getAdditionalAssertFieldNames()) {
			throw new IllegalArgumentException(
				"Invalid field name " + fieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_productPurchaseViewResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_productPurchaseViewResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator,
		ProductPurchaseView productPurchaseView) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("product")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productConsumptions")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("productPurchases")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected ProductPurchaseView randomProductPurchaseView() throws Exception {
		return new ProductPurchaseView() {
			{
			}
		};
	}

	protected ProductPurchaseView randomIrrelevantProductPurchaseView()
		throws Exception {

		ProductPurchaseView randomIrrelevantProductPurchaseView =
			randomProductPurchaseView();

		return randomIrrelevantProductPurchaseView;
	}

	protected ProductPurchaseView randomPatchProductPurchaseView()
		throws Exception {

		return randomProductPurchaseView();
	}

	protected ProductPurchaseViewResource productPurchaseViewResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

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

				sb.setLength(sb.length() - 1);

				sb.append(")");
			}

			if (_graphQLFields.length > 0) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(",");
				}

				sb.setLength(sb.length() - 1);

				sb.append("}");
			}

			return sb.toString();
		}

		private final GraphQLField[] _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseProductPurchaseViewResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.
		ProductPurchaseViewResource _productPurchaseViewResource;

}