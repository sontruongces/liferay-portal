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

package com.liferay.portal.workflow.metrics.service.internal.search.index.test.base;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.engine.adapter.document.UpdateDocumentRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.test.log.CaptureAppender;
import com.liferay.portal.test.log.Log4JLoggerTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.util.WorkflowContextUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalService;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskLocalService;
import com.liferay.portal.workflow.metrics.service.internal.util.WorkflowDefinitionUtil;
import com.liferay.portal.workflow.metrics.service.test.base.BaseWorkflowMetricsTestCase;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

import org.apache.log4j.Level;

import org.junit.After;
import org.junit.Before;

/**
 * @author Rafael Praxedes
 */
public abstract class BaseWorkflowMetricsIndexerTestCase
	extends BaseWorkflowMetricsTestCase {

	@Before
	public void setUp() throws Exception {
		_deployWorkflowDefinition();
	}

	@After
	public void tearDown() throws Exception {
		undeployWorkflowDefinition();
	}

	protected BlogsEntry addBlogsEntry() throws PortalException {
		try (CaptureAppender captureAppender =
				Log4JLoggerTestUtil.configureLog4JLogger(
					"com.liferay.petra.mail.MailEngine", Level.OFF)) {

			_blogsEntry = _blogsEntryLocalService.addEntry(
				TestPropsValues.getUserId(), StringUtil.randomString(),
				StringUtil.randomString(), new Date(),
				ServiceContextTestUtil.getServiceContext());

			return _blogsEntry;
		}
	}

	protected KaleoInstance addKaleoInstance() throws Exception {
		return addKaleoInstance(getKaleoDefinition(), _createWorkflowContext());
	}

	protected KaleoInstance addKaleoInstance(KaleoDefinition kaleoDefinition)
		throws Exception {

		return addKaleoInstance(kaleoDefinition, _createWorkflowContext());
	}

	protected KaleoInstance addKaleoInstance(
			KaleoDefinition kaleoDefinition,
			Map<String, Serializable> workflowContext)
		throws Exception {

		List<KaleoDefinitionVersion> kaleoDefinitionVersions =
			kaleoDefinition.getKaleoDefinitionVersions();

		KaleoDefinitionVersion kaleoDefinitionVersion =
			kaleoDefinitionVersions.get(0);

		KaleoInstance kaleoInstance =
			_kaleoInstanceLocalService.addKaleoInstance(
				kaleoDefinitionVersion.getKaleoDefinitionVersionId(),
				kaleoDefinitionVersion.getName(), kaleoDefinition.getVersion(),
				_createWorkflowContext(),
				ServiceContextTestUtil.getServiceContext());

		_kaleoInstances.add(kaleoInstance);

		return kaleoInstance;
	}

	protected KaleoNode addKaleoNode(KaleoDefinition kaleoDefinition, Node node)
		throws Exception {

		List<KaleoDefinitionVersion> kaleoDefinitionVersions =
			kaleoDefinition.getKaleoDefinitionVersions();

		KaleoDefinitionVersion kaleoDefinitionVersion =
			kaleoDefinitionVersions.get(0);

		KaleoNode kaleoNode = _kaleoNodeLocalService.addKaleoNode(
			kaleoDefinitionVersion.getKaleoDefinitionVersionId(), node,
			ServiceContextTestUtil.getServiceContext());

		_kaleoNodes.add(kaleoNode);

		return kaleoNode;
	}

	protected KaleoNode addKaleoNode(Node node) throws Exception {
		return addKaleoNode(getKaleoDefinition(), node);
	}

	protected KaleoTask addKaleoTask(Task task) throws Exception {
		return addKaleoTask(task, getKaleoDefinition());
	}

	protected KaleoTask addKaleoTask(Task task, KaleoDefinition kaleoDefinition)
		throws Exception {

		KaleoNode kaleoNode = addKaleoNode(kaleoDefinition, task);

		KaleoTask kaleoTask = _kaleoTaskLocalService.addKaleoTask(
			kaleoNode.getKaleoDefinitionVersionId(), kaleoNode.getKaleoNodeId(),
			task, ServiceContextTestUtil.getServiceContext());

		_kaleoTasks.add(kaleoTask);

		return kaleoTask;
	}

	protected KaleoTaskInstanceToken addKaleoTaskInstanceToken(String taskName)
		throws Exception {

		Map<String, Serializable> workflowContext = _createWorkflowContext();

		KaleoInstance kaleoInstance = addKaleoInstance(
			getKaleoDefinition(), workflowContext);

		KaleoInstanceToken rootKaleoInstanceToken =
			kaleoInstance.getRootKaleoInstanceToken(
				workflowContext, ServiceContextTestUtil.getServiceContext());

		Task task = new Task(taskName, StringPool.BLANK);

		task.setAssignments(Collections.emptySet());

		KaleoTask kaleoTask = addKaleoTask(task, getKaleoDefinition());

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			_kaleoTaskInstanceTokenLocalService.addKaleoTaskInstanceToken(
				rootKaleoInstanceToken.getKaleoInstanceTokenId(),
				kaleoTask.getKaleoTaskId(), kaleoTask.getName(),
				Collections.emptyList(), null, workflowContext,
				ServiceContextTestUtil.getServiceContext());

		_kaleoTaskInstanceTokens.add(kaleoTaskInstanceToken);

		return kaleoTaskInstanceToken;
	}

	protected void assertReindex(
			Indexer<?> indexer, Map<String, Integer> indexNamesMap,
			String[] indexTypes, Object... parameters)
		throws Exception {

		if (searchEngineAdapter == null) {
			return;
		}

		String[] indexNames = ArrayUtil.toStringArray(indexNamesMap.keySet());

		for (int i = 0; i < indexNames.length; i++) {
			retryAssertCount(
				indexNamesMap.get(indexNames[i]), indexNames[i], indexTypes[i],
				ArrayUtil.append(new Object[] {"deleted", false}, parameters));
		}

		clearIndices(indexNames, indexTypes, parameters);

		for (int i = 0; i < indexNames.length; i++) {
			retryAssertCount(
				indexNamesMap.get(indexNames[i]), indexNames[i], indexTypes[i],
				ArrayUtil.append(new Object[] {"deleted", true}, parameters));
		}

		indexer.reindex(
			new String[] {String.valueOf(TestPropsValues.getCompanyId())});

		for (int i = 0; i < indexNames.length; i++) {
			retryAssertCount(
				indexNamesMap.get(indexNames[i]), indexNames[i], indexTypes[i],
				ArrayUtil.append(new Object[] {"deleted", false}, parameters));
		}
	}

	protected void assertReindex(
			Indexer<?> indexer, String[] indexNames, String[] indexTypes,
			Object... parameters)
		throws Exception {

		Map<String, Integer> indexNamesMap = Stream.of(
			indexNames
		).collect(
			LinkedHashMap::new, (map, indexName) -> map.put(indexName, 1),
			Map::putAll
		);

		assertReindex(indexer, indexNamesMap, indexTypes, parameters);
	}

	protected KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws PortalException {

		KaleoInstance kaleoInstance = getKaleoInstance();

		return _kaleoTaskInstanceTokenLocalService.assignKaleoTaskInstanceToken(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
			User.class.getName(), TestPropsValues.getUserId(),
			WorkflowContextUtil.convert(kaleoInstance.getWorkflowContext()),
			ServiceContextTestUtil.getServiceContext());
	}

	protected void clearIndices(
		String[] indexNames, String[] indexTypes, Object... parameters) {

		if (searchEngineAdapter == null) {
			return;
		}

		for (int i = 0; i < indexNames.length; i++) {
			SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

			String indexName = indexNames[i];

			searchSearchRequest.setIndexNames(indexName);

			BooleanQuery booleanQuery = queries.booleanQuery();

			for (int j = 0; j < parameters.length; j = j + 2) {
				booleanQuery.addMustQueryClauses(
					queries.term(
						String.valueOf(parameters[j]), parameters[j + 1]));
			}

			searchSearchRequest.setQuery(booleanQuery);

			String indexType = indexTypes[i];

			searchSearchRequest.setTypes(indexType);

			SearchSearchResponse searchSearchResponse =
				searchEngineAdapter.execute(searchSearchRequest);

			Stream.of(
				searchSearchResponse
			).map(
				SearchSearchResponse::getSearchHits
			).map(
				SearchHits::getSearchHits
			).flatMap(
				List::stream
			).map(
				SearchHit::getDocument
			).map(
				_documentBuilderFactory::builder
			).map(
				documentBuilder -> {
					documentBuilder.setValue("deleted", true);

					return documentBuilder.build();
				}
			).forEach(
				document -> {
					UpdateDocumentRequest updateDocumentRequest =
						new UpdateDocumentRequest(
							indexName, document.getString("uid"), document);

					updateDocumentRequest.setType(indexType);

					searchEngineAdapter.execute(updateDocumentRequest);
				}
			);
		}
	}

	protected KaleoInstance completeKaleoInstance(KaleoInstance kaleoInstance)
		throws Exception {

		return _kaleoInstanceLocalService.completeKaleoInstance(
			kaleoInstance.getKaleoInstanceId());
	}

	protected void completeKaleoTaskInstanceToken(KaleoInstance kaleoInstance)
		throws PortalException {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		serviceContext.setUserId(0);

		List<KaleoTaskInstanceToken> kaleoTaskInstanceTokens =
			_kaleoTaskInstanceTokenLocalService.getKaleoTaskInstanceTokens(
				kaleoInstance.getKaleoInstanceId(), false, -1, -1, null,
				serviceContext);

		for (KaleoTaskInstanceToken kaleoTaskInstanceToken :
				kaleoTaskInstanceTokens) {

			_kaleoTaskInstanceTokenLocalService.assignKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
				User.class.getName(), TestPropsValues.getUserId(), null,
				ServiceContextTestUtil.getServiceContext());

			_kaleoTaskInstanceTokenLocalService.completeKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
				ServiceContextTestUtil.getServiceContext());
		}
	}

	protected KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws PortalException {

		kaleoTaskInstanceToken = assignKaleoTaskInstanceToken(
			kaleoTaskInstanceToken);

		return _kaleoTaskInstanceTokenLocalService.
			completeKaleoTaskInstanceToken(
				kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId(),
				ServiceContextTestUtil.getServiceContext());
	}

	protected void deleteKaleoInstance(KaleoInstance kaleoInstance)
		throws PortalException {

		_kaleoInstances.remove(kaleoInstance);

		_kaleoInstanceLocalService.deleteKaleoDefinitionVersionKaleoInstances(
			kaleoInstance.getKaleoDefinitionVersionId());
	}

	protected void deleteKaleoNode(KaleoNode kaleoNode) {
		_kaleoNodes.remove(kaleoNode);
		_kaleoNodeLocalService.deleteKaleoNode(kaleoNode);
	}

	protected void deleteKaleoTask(KaleoTask kaleoTask) {
		_kaleoTasks.remove(kaleoTask);
		_kaleoTaskLocalService.deleteKaleoTask(kaleoTask);
	}

	protected void deleteKaleoTaskInstanceToken(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {

		_kaleoTaskInstanceTokens.remove(kaleoTaskInstanceToken);
		_kaleoTaskInstanceTokenLocalService.deleteKaleoTaskInstanceToken(
			kaleoTaskInstanceToken);
	}

	protected KaleoDefinition getKaleoDefinition() {
		return _kaleoDefinition;
	}

	protected KaleoInstance getKaleoInstance() {
		return _kaleoInstances.peek();
	}

	protected KaleoInstance getKaleoInstance(BlogsEntry blogsEntry)
		throws Exception {

		List<KaleoInstance> kaleoInstances =
			_kaleoInstanceLocalService.getKaleoInstances(
				null, BlogsEntry.class.getName(), blogsEntry.getPrimaryKey(),
				null, 0, 1, null, ServiceContextTestUtil.getServiceContext());

		return kaleoInstances.get(0);
	}

	protected void undeployWorkflowDefinition() throws Exception {
		_deleteBlogsEntry();
		_deleteKaleoInstances();
		_deleteKaleoNodes();
		_deleteKaleoTaskInstanceTokens();
		_deleteKaleoTasks();

		if (_workflowDefinition != null) {
			_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
				TestPropsValues.getUserId(), TestPropsValues.getCompanyId(),
				TestPropsValues.getGroupId(), BlogsEntry.class.getName(), 0, 0,
				null);

			_workflowDefinitionManager.updateActive(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				_workflowDefinition.getName(), _workflowDefinition.getVersion(),
				false);

			_workflowDefinitionManager.undeployWorkflowDefinition(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				_workflowDefinition.getName(),
				_workflowDefinition.getVersion());

			_workflowDefinition = null;
		}
	}

	protected void updateKaleoDefinition() throws Exception {
		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				_workflowDefinition.getTitle(), _workflowDefinition.getName(),
				WorkflowDefinitionUtil.getBytes());
	}

	private Map<String, Serializable> _createWorkflowContext()
		throws PortalException {

		BlogsEntry blogsEntry = addBlogsEntry();

		return HashMapBuilder.<String, Serializable>put(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME,
			BlogsEntry.class.getName()
		).put(
			WorkflowConstants.CONTEXT_ENTRY_CLASS_PK,
			String.valueOf(blogsEntry.getEntryId())
		).build();
	}

	private void _deleteBlogsEntry() throws Exception {
		if (_blogsEntry != null) {
			_blogsEntryLocalService.deleteEntry(_blogsEntry);

			_workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
				TestPropsValues.getCompanyId(), TestPropsValues.getGroupId(),
				BlogsEntry.class.getName(), _blogsEntry.getPrimaryKey());
		}
	}

	private void _deleteKaleoInstances() {
		for (Iterator<KaleoInstance> kaleoInstanceIterator =
				_kaleoInstances.iterator();
			 kaleoInstanceIterator.hasNext();) {

			KaleoInstance kaleoInstance = kaleoInstanceIterator.next();

			_kaleoInstanceLocalService.
				deleteKaleoDefinitionVersionKaleoInstances(
					kaleoInstance.getKaleoDefinitionVersionId());

			kaleoInstanceIterator.remove();
		}
	}

	private void _deleteKaleoNodes() {
		for (Iterator<KaleoNode> kaleoNodeIterator = _kaleoNodes.iterator();
			 kaleoNodeIterator.hasNext();) {

			KaleoNode kaleoNode = kaleoNodeIterator.next();

			_kaleoNodeLocalService.deleteKaleoDefinitionVersionKaleoNodes(
				kaleoNode.getKaleoDefinitionVersionId());

			kaleoNodeIterator.remove();
		}
	}

	private void _deleteKaleoTaskInstanceTokens() {
		for (Iterator<KaleoTaskInstanceToken> kaleoTaskInstanceTokenIterator =
				_kaleoTaskInstanceTokens.iterator();
			 kaleoTaskInstanceTokenIterator.hasNext();) {

			KaleoTaskInstanceToken kaleoTaskInstanceToken =
				kaleoTaskInstanceTokenIterator.next();

			_kaleoTaskInstanceTokenLocalService.
				deleteKaleoDefinitionVersionKaleoTaskInstanceTokens(
					kaleoTaskInstanceToken.getKaleoDefinitionVersionId());

			kaleoTaskInstanceTokenIterator.remove();
		}
	}

	private void _deleteKaleoTasks() {
		for (Iterator<KaleoTask> kaleoTaskIterator = _kaleoTasks.iterator();
			 kaleoTaskIterator.hasNext();) {

			KaleoTask kaleoTask = kaleoTaskIterator.next();

			_kaleoTaskLocalService.deleteKaleoDefinitionVersionKaleoTasks(
				kaleoTask.getKaleoDefinitionVersionId());

			kaleoTaskIterator.remove();
		}
	}

	private void _deployWorkflowDefinition() throws Exception {
		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
				StringUtil.randomId(), StringUtil.randomId(),
				WorkflowDefinitionUtil.getBytes());

		_kaleoDefinition = _kaleoDefinitionLocalService.getKaleoDefinition(
			_workflowDefinition.getName(),
			ServiceContextTestUtil.getServiceContext());

		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLink(
			TestPropsValues.getUserId(), TestPropsValues.getCompanyId(),
			TestPropsValues.getGroupId(), BlogsEntry.class.getName(), 0, 0,
			_workflowDefinition.getName(), _workflowDefinition.getVersion());
	}

	private BlogsEntry _blogsEntry;

	@Inject
	private BlogsEntryLocalService _blogsEntryLocalService;

	@Inject
	private DocumentBuilderFactory _documentBuilderFactory;

	private KaleoDefinition _kaleoDefinition;

	@Inject
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Inject
	private KaleoInstanceLocalService _kaleoInstanceLocalService;

	private final Stack<KaleoInstance> _kaleoInstances = new Stack<>();

	@Inject
	private KaleoInstanceTokenLocalService _kaleoInstanceTokenLocalService;

	@Inject
	private KaleoNodeLocalService _kaleoNodeLocalService;

	private final List<KaleoNode> _kaleoNodes = new ArrayList<>();

	@Inject
	private KaleoTaskInstanceTokenLocalService
		_kaleoTaskInstanceTokenLocalService;

	private final List<KaleoTaskInstanceToken> _kaleoTaskInstanceTokens =
		new ArrayList<>();

	@Inject
	private KaleoTaskLocalService _kaleoTaskLocalService;

	private final List<KaleoTask> _kaleoTasks = new ArrayList<>();
	private WorkflowDefinition _workflowDefinition;

	@Inject
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

	@Inject
	private WorkflowDefinitionManager _workflowDefinitionManager;

	@Inject
	private WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;

}