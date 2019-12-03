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

package com.liferay.portal.search.elasticsearch6.internal.information;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.configuration.CrossClusterReplicationConfigurationWrapper;
import com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch6.configuration.OperationMode;
import com.liferay.portal.search.elasticsearch6.internal.ElasticsearchSearchEngine;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.engine.SearchEngineInformation;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.Version;
import org.elasticsearch.action.admin.cluster.node.info.NodeInfo;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoRequestBuilder;
import org.elasticsearch.action.admin.cluster.node.info.NodesInfoResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.unit.TimeValue;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * @author Adam Brandizzi
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration",
	immediate = true, service = SearchEngineInformation.class
)
public class ElasticsearchSearchEngineInformation
	implements SearchEngineInformation {

	@Override
	public String getClientVersionString() {
		return Version.CURRENT.toString();
	}

	@Override
	public String getNodesString() {
		String clusterNodesString = getClusterNodesString(
			elasticsearchConnectionManager.getClient());

		if (isCrossClusterReplicationEnabled()) {
			String localClusterNodesString = getClusterNodesString(
				elasticsearchConnectionManager.getClient(true));

			if (!Validator.isBlank(localClusterNodesString)) {
				StringBundler sb = new StringBundler(5);

				sb.append("Remote Cluster = ");
				sb.append(clusterNodesString);
				sb.append(StringPool.COMMA_AND_SPACE);
				sb.append("Local Cluster = ");
				sb.append(localClusterNodesString);

				clusterNodesString = sb.toString();
			}
		}

		return clusterNodesString;
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public String getStatusString() {
		StringBundler sb = new StringBundler(8);

		sb.append("Vendor: ");
		sb.append(getVendorString());
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("Client Version: ");
		sb.append(getClientVersionString());
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("Nodes: ");
		sb.append(getNodesString());

		return sb.toString();
	}

	@Override
	public String getVendorString() {
		OperationMode operationMode =
			elasticsearchConfiguration.operationMode();

		if (Objects.equals(operationMode, OperationMode.EMBEDDED)) {
			return elasticsearchSearchEngine.getVendor() + StringPool.SPACE +
				"(Embedded)";
		}

		return elasticsearchSearchEngine.getVendor();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		elasticsearchConfiguration = ConfigurableUtil.createConfigurable(
			ElasticsearchConfiguration.class, properties);
	}

	protected String getClusterNodesString(Client client) {
		try {
			if (client == null) {
				return StringPool.BLANK;
			}

			ClusterInfo clusterInfo = _getClusterInfo(client);

			String clusterName = clusterInfo.getClusterName();

			List<NodeInfo> nodeInfos = clusterInfo.getNodeInfoList();

			Stream<NodeInfo> stream = nodeInfos.stream();

			String nodesString = stream.map(
				nodeInfo -> {
					DiscoveryNode node = nodeInfo.getNode();

					StringBundler sb = new StringBundler(5);

					sb.append(node.getName());
					sb.append(StringPool.SPACE);
					sb.append(StringPool.OPEN_PARENTHESIS);
					sb.append(node.getVersion());
					sb.append(StringPool.CLOSE_PARENTHESIS);

					return sb.toString();
				}
			).collect(
				Collectors.joining(StringPool.COMMA_AND_SPACE)
			);

			StringBundler sb = new StringBundler(6);

			sb.append(clusterName);
			sb.append(StringPool.COLON);
			sb.append(StringPool.SPACE);
			sb.append(StringPool.OPEN_BRACKET);
			sb.append(nodesString);
			sb.append(StringPool.CLOSE_BRACKET);

			return sb.toString();
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get node information", exception);
			}

			StringBundler sb = new StringBundler(4);

			sb.append(StringPool.OPEN_PARENTHESIS);
			sb.append("Error: ");
			sb.append(exception.toString());
			sb.append(StringPool.CLOSE_PARENTHESIS);

			return sb.toString();
		}
	}

	protected boolean isCrossClusterReplicationEnabled() {
		if (crossClusterReplicationConfigurationWrapper == null) {
			return false;
		}

		return crossClusterReplicationConfigurationWrapper.isCCREnabled();
	}

	@Reference(cardinality = ReferenceCardinality.OPTIONAL)
	protected volatile CrossClusterReplicationConfigurationWrapper
		crossClusterReplicationConfigurationWrapper;

	protected volatile ElasticsearchConfiguration elasticsearchConfiguration;

	@Reference
	protected ElasticsearchConnectionManager elasticsearchConnectionManager;

	@Reference
	protected ElasticsearchSearchEngine elasticsearchSearchEngine;

	private ClusterInfo _getClusterInfo(Client client) {
		ClusterInfo clusterInfo = new ClusterInfo();

		AdminClient adminClient = client.admin();

		ClusterAdminClient clusterAdminClient = adminClient.cluster();

		NodesInfoRequestBuilder nodesInfoRequestBuilder =
			clusterAdminClient.prepareNodesInfo();

		TimeValue timeout = TimeValue.timeValueMillis(10000);

		NodesInfoResponse nodesInfoResponse = nodesInfoRequestBuilder.get(
			timeout);

		ClusterName clusterName = nodesInfoResponse.getClusterName();

		clusterInfo.setClusterName(clusterName.value());

		clusterInfo.setNodeInfoList(nodesInfoResponse.getNodes());

		return clusterInfo;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ElasticsearchSearchEngineInformation.class);

	private class ClusterInfo {

		public String getClusterName() {
			return _clusterName;
		}

		public List<NodeInfo> getNodeInfoList() {
			return _nodeInfoList;
		}

		public void setClusterName(String clusterName) {
			_clusterName = clusterName;
		}

		public void setNodeInfoList(List<NodeInfo> nodeInfoList) {
			_nodeInfoList = nodeInfoList;
		}

		private String _clusterName;
		private List<NodeInfo> _nodeInfoList;

	}

}