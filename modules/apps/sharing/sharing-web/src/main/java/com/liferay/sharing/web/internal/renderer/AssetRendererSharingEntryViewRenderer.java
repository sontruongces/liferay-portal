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

package com.liferay.sharing.web.internal.renderer;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.renderer.SharingEntryViewRenderer;
import com.liferay.sharing.web.internal.display.context.ViewSharedAssetsDisplayContext;
import com.liferay.sharing.web.internal.display.context.ViewSharingEntryAssetEntryDisplayContext;
import com.liferay.sharing.web.internal.util.AssetRendererSharingUtil;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alejandro Tardín
 */
public class AssetRendererSharingEntryViewRenderer
	implements SharingEntryViewRenderer {

	public AssetRendererSharingEntryViewRenderer(
		ServletContext servletContext) {

		_servletContext = servletContext;
	}

	@Override
	public void render(
			SharingEntry sharingEntry, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, PortalException {

		try {
			httpServletRequest.setAttribute(
				ViewSharingEntryAssetEntryDisplayContext.class.getName(),
				new ViewSharingEntryAssetEntryDisplayContext(
					AssetRendererSharingUtil.getAssetRenderer(sharingEntry),
					PortalUtil.getLiferayPortletRequest(
						(PortletRequest)httpServletRequest.getAttribute(
							JavaConstants.JAVAX_PORTLET_REQUEST)),
					PortalUtil.getLiferayPortletResponse(
						(PortletResponse)httpServletRequest.getAttribute(
							JavaConstants.JAVAX_PORTLET_RESPONSE)),
					sharingEntry,
					(ViewSharedAssetsDisplayContext)
						httpServletRequest.getAttribute(
							ViewSharedAssetsDisplayContext.class.getName())));

			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(_JSP_PATH);

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (IOException | ServletException exception) {
			_log.error("Unable to include JSP " + _JSP_PATH, exception);

			throw new IOException(
				"Unable to include JSP " + _JSP_PATH, exception);
		}
	}

	private static final String _JSP_PATH =
		"/shared_assets/view_asset_entry_sharing_entry.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		AssetRendererSharingEntryViewRenderer.class);

	private final ServletContext _servletContext;

}