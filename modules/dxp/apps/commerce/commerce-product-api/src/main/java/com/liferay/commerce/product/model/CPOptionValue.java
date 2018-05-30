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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CPOptionValue service. Represents a row in the &quot;CPOptionValue&quot; database table, with each column mapped to a property of this class.
 *
 * @author Marco Leo
 * @see CPOptionValueModel
 * @see com.liferay.commerce.product.model.impl.CPOptionValueImpl
 * @see com.liferay.commerce.product.model.impl.CPOptionValueModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.commerce.product.model.impl.CPOptionValueImpl")
@ProviderType
public interface CPOptionValue extends CPOptionValueModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.commerce.product.model.impl.CPOptionValueImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CPOptionValue, Long> CP_OPTION_VALUE_ID_ACCESSOR =
		new Accessor<CPOptionValue, Long>() {
			@Override
			public Long get(CPOptionValue cpOptionValue) {
				return cpOptionValue.getCPOptionValueId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CPOptionValue> getTypeClass() {
				return CPOptionValue.class;
			}
		};

	public CPOption getCPOption()
		throws com.liferay.portal.kernel.exception.PortalException;
}