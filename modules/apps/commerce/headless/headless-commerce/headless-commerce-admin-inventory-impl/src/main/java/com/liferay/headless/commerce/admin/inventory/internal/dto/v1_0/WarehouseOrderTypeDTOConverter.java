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

package com.liferay.headless.commerce.admin.inventory.internal.dto.v1_0;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseRel;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseRelService;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseService;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.headless.commerce.admin.inventory.dto.v1_0.WarehouseOrderType;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Crescenzo Rega
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.inventory.model.CommerceInventoryWarehouseRel-OrderType",
	service = {DTOConverter.class, WarehouseOrderTypeDTOConverter.class}
)
public class WarehouseOrderTypeDTOConverter
	implements DTOConverter<CommerceInventoryWarehouseRel, WarehouseOrderType> {

	@Override
	public String getContentType() {
		return WarehouseOrderType.class.getSimpleName();
	}

	@Override
	public WarehouseOrderType toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceInventoryWarehouseRel commerceInventoryWarehouseRel =
			_commerceInventoryWarehouseRelService.
				getCommerceInventoryWarehouseRel(
					(Long)dtoConverterContext.getId());

		CommerceOrderType commerceOrderType =
			_commerceOrderTypeService.getCommerceOrderType(
				commerceInventoryWarehouseRel.getClassPK());

		CommerceInventoryWarehouse commerceInventoryWarehouse =
			_commerceInventoryWarehouseService.getCommerceInventoryWarehouse(
				commerceInventoryWarehouseRel.
					getCommerceInventoryWarehouseId());

		return new WarehouseOrderType() {
			{
				actions = dtoConverterContext.getActions();
				orderTypeExternalReferenceCode =
					commerceOrderType.getExternalReferenceCode();
				orderTypeId = commerceOrderType.getCommerceOrderTypeId();
				warehouseExternalReferenceCode =
					commerceInventoryWarehouse.getExternalReferenceCode();
				warehouseId =
					commerceInventoryWarehouse.
						getCommerceInventoryWarehouseId();
				warehouseOrderTypeId =
					commerceInventoryWarehouseRel.
						getCommerceInventoryWarehouseRelId();
			}
		};
	}

	@Reference
	private CommerceInventoryWarehouseRelService
		_commerceInventoryWarehouseRelService;

	@Reference
	private CommerceInventoryWarehouseService
		_commerceInventoryWarehouseService;

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

}