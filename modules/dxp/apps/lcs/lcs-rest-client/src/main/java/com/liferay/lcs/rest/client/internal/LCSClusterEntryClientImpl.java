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

package com.liferay.lcs.rest.client.internal;

import com.liferay.lcs.rest.client.LCSClusterEntry;
import com.liferay.lcs.rest.client.LCSClusterEntryClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterEntryClient.class)
public class LCSClusterEntryClientImpl implements LCSClusterEntryClient {

	@Override
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException {

		return _jsonWebServiceClient.doGetToObject(
			LCSClusterEntry.class,
			_URL_LCS_CLUSTER_ENTRY + "/" + lcsClusterEntryId);
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	private static final String _URL_LCS_CLUSTER_ENTRY =
		"/o/osb-lcs-rest/LCSClusterEntry";

	@Reference(target = "(component.name=OSBLCSJSONWebServiceClient)")
	private JSONWebServiceClient _jsonWebServiceClient;

}