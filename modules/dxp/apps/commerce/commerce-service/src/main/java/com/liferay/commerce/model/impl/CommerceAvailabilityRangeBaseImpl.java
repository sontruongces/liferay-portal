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

package com.liferay.commerce.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.model.CommerceAvailabilityRange;
import com.liferay.commerce.service.CommerceAvailabilityRangeLocalServiceUtil;

/**
 * The extended model base implementation for the CommerceAvailabilityRange service. Represents a row in the &quot;CommerceAvailabilityRange&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CommerceAvailabilityRangeImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceAvailabilityRangeImpl
 * @see CommerceAvailabilityRange
 * @generated
 */
@ProviderType
public abstract class CommerceAvailabilityRangeBaseImpl
	extends CommerceAvailabilityRangeModelImpl
	implements CommerceAvailabilityRange {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a commerce availability range model instance should use the {@link CommerceAvailabilityRange} interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			CommerceAvailabilityRangeLocalServiceUtil.addCommerceAvailabilityRange(this);
		}
		else {
			CommerceAvailabilityRangeLocalServiceUtil.updateCommerceAvailabilityRange(this);
		}
	}
}