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

package com.liferay.osb.provisioning.web.internal.util;

import com.liferay.osb.provisioning.koroneiki.reader.AccountReader;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.NoteWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.TeamWebService;
import com.liferay.osb.provisioning.web.internal.display.context.AccountSearchDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountContactsDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewAccountLiferayWorkersDisplayContext;
import com.liferay.osb.provisioning.web.internal.display.context.ViewTeamDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class ProvisioningWebComponentProvider {

	public static AccountSearchDisplayContext getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return _provisioningWebComponentProvider.
			_getAccountSearchDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static ProvisioningWebComponentProvider
		getProvisioningWebComponentProvider() {

		return _provisioningWebComponentProvider;
	}

	public static ViewAccountContactsDisplayContext
			getViewAccountContactsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getViewAccountContactsDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static ViewAccountDisplayContext getViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewAccountDisplayContext(
			renderRequest, renderResponse, httpServletRequest);
	}

	public static ViewAccountLiferayWorkersDisplayContext
			getViewAccountLiferayWorkersDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider.
			_getViewAccountLiferayWorkersDisplayContext(
				renderRequest, renderResponse, httpServletRequest);
	}

	public static ViewTeamDisplayContext getViewTeamDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		return _provisioningWebComponentProvider._getViewTeamDisplayContext(
			renderRequest, renderResponse, httpServletRequest);
	}

	@Activate
	protected void activate() {
		_provisioningWebComponentProvider = this;
	}

	@Deactivate
	protected void deactivate() {
		_provisioningWebComponentProvider = null;
	}

	private AccountSearchDisplayContext _getAccountSearchDisplayContext(
		RenderRequest renderRequest, RenderResponse renderResponse,
		HttpServletRequest httpServletRequest) {

		return new AccountSearchDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService);
	}

	private ViewAccountContactsDisplayContext
			_getViewAccountContactsDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		ViewAccountContactsDisplayContext viewAccountContactsDisplayContext =
			(ViewAccountContactsDisplayContext)httpServletRequest.getAttribute(
				ViewAccountContactsDisplayContext.class.getName());

		if (viewAccountContactsDisplayContext != null) {
			return viewAccountContactsDisplayContext;
		}

		viewAccountContactsDisplayContext =
			new ViewAccountContactsDisplayContext(
				renderRequest, renderResponse, httpServletRequest,
				_accountReader, _accountWebService, _auditEntryWebService,
				_contactRoleWebService, _contactWebService,
				_externalLinkWebService, _noteWebService,
				_productPurchaseViewWebService, _teamWebService);

		httpServletRequest.setAttribute(
			ViewAccountContactsDisplayContext.class.getName(),
			viewAccountContactsDisplayContext);

		return viewAccountContactsDisplayContext;
	}

	private ViewAccountDisplayContext _getViewAccountDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		ViewAccountDisplayContext viewAccountDisplayContext =
			(ViewAccountDisplayContext)httpServletRequest.getAttribute(
				ViewAccountDisplayContext.class.getName());

		if (viewAccountDisplayContext != null) {
			return viewAccountDisplayContext;
		}

		viewAccountDisplayContext = new ViewAccountDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService, _auditEntryWebService, _contactRoleWebService,
			_contactWebService, _externalLinkWebService, _noteWebService,
			_productPurchaseViewWebService, _teamWebService);

		httpServletRequest.setAttribute(
			ViewAccountDisplayContext.class.getName(),
			viewAccountDisplayContext);

		return viewAccountDisplayContext;
	}

	private ViewAccountLiferayWorkersDisplayContext
			_getViewAccountLiferayWorkersDisplayContext(
				RenderRequest renderRequest, RenderResponse renderResponse,
				HttpServletRequest httpServletRequest)
		throws Exception {

		ViewAccountLiferayWorkersDisplayContext
			viewAccountLiferayWorkersDisplayContext =
				(ViewAccountLiferayWorkersDisplayContext)
					httpServletRequest.getAttribute(
						ViewAccountLiferayWorkersDisplayContext.class.
							getName());

		if (viewAccountLiferayWorkersDisplayContext != null) {
			return viewAccountLiferayWorkersDisplayContext;
		}

		viewAccountLiferayWorkersDisplayContext =
			new ViewAccountLiferayWorkersDisplayContext(
				renderRequest, renderResponse, httpServletRequest,
				_accountReader, _accountWebService, _auditEntryWebService,
				_contactRoleWebService, _contactWebService,
				_externalLinkWebService, _noteWebService,
				_productPurchaseViewWebService, _teamWebService);

		httpServletRequest.setAttribute(
			ViewAccountLiferayWorkersDisplayContext.class.getName(),
			viewAccountLiferayWorkersDisplayContext);

		return viewAccountLiferayWorkersDisplayContext;
	}

	private ViewTeamDisplayContext _getViewTeamDisplayContext(
			RenderRequest renderRequest, RenderResponse renderResponse,
			HttpServletRequest httpServletRequest)
		throws Exception {

		ViewTeamDisplayContext viewTeamDisplayContext =
			(ViewTeamDisplayContext)httpServletRequest.getAttribute(
				ViewTeamDisplayContext.class.getName());

		if (viewTeamDisplayContext != null) {
			return viewTeamDisplayContext;
		}

		viewTeamDisplayContext = new ViewTeamDisplayContext(
			renderRequest, renderResponse, httpServletRequest, _accountReader,
			_accountWebService, _auditEntryWebService, _contactRoleWebService,
			_contactWebService, _externalLinkWebService, _noteWebService,
			_productPurchaseViewWebService, _teamWebService);

		httpServletRequest.setAttribute(
			ViewTeamDisplayContext.class.getName(), viewTeamDisplayContext);

		return viewTeamDisplayContext;
	}

	private static ProvisioningWebComponentProvider
		_provisioningWebComponentProvider;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private AuditEntryWebService _auditEntryWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

	@Reference
	private NoteWebService _noteWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private TeamWebService _teamWebService;

}