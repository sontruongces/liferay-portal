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

package com.liferay.osb.provisioning.koroneiki.web.service.internal;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Note;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.NoteResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.NoteSerDes;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = NoteWebService.class
)
public class NoteWebServiceImpl
	extends BaseWebService implements NoteWebService {

	public Note addNote(
			String agentName, String agentUID, String accountKey, Note note)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_noteResource.postAccountAccountKeyNoteHttpResponse(
				agentName, agentUID, accountKey, note);

		return processDTO(httpResponse, NoteSerDes::toDTO);
	}

	public void deleteNote(String agentName, String agentUID, String noteKey)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_noteResource.deleteNoteHttpResponse(agentName, agentUID, noteKey);

		validateResponse(httpResponse);
	}

	public List<Note> getNotes(
			String accountKey, String type, String status, int page,
			int pageSize)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_noteResource.getAccountAccountKeyNotesPageHttpResponse(
				accountKey, status, type, Pagination.of(page, pageSize));

		return processDTOList(httpResponse, NoteSerDes::toDTO);
	}

	public Note updateNote(
			String agentName, String agentUID, String noteKey, Note note)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_noteResource.putNoteHttpResponse(
				agentName, agentUID, noteKey, note);

		return processDTO(httpResponse, NoteSerDes::toDTO);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		NoteResource.Builder builder = NoteResource.builder();

		_noteResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private NoteResource _noteResource;

}