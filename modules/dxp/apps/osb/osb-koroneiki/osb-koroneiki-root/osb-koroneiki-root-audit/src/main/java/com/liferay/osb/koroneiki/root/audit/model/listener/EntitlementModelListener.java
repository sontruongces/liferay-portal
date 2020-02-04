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

package com.liferay.osb.koroneiki.root.audit.model.listener;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementLocalService;
import com.liferay.osb.koroneiki.root.audit.model.BaseAuditModelListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class EntitlementModelListener
	extends BaseAuditModelListener<Entitlement> {

	@Override
	protected long getClassNameId(Entitlement entitlement) {
		return entitlement.getClassNameId();
	}

	@Override
	protected long getClassPK(Entitlement entitlement) {
		return entitlement.getClassPK();
	}

	@Override
	protected Entitlement getModel(long classPK) throws PortalException {
		return _entitlementLocalService.getEntitlement(classPK);
	}

	@Override
	protected ServiceContext getServiceContext(long classNameId, long classPK) {
		ServiceContext serviceContext = super.getServiceContext(
			classNameId, classPK);

		serviceContext.setAttribute("agentName", StringPool.BLANK);

		return serviceContext;
	}

	@Override
	protected long getUserId() throws PortalException {
		long companyId = portalInstancesLocalService.getDefaultCompanyId();

		return userLocalService.getDefaultUserId(companyId);
	}

	@Reference
	private EntitlementLocalService _entitlementLocalService;

}