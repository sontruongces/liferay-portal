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

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductPurchaseViewUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-purchase-view.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductPurchaseViewResource.class
)
public class ProductPurchaseViewResourceImpl
	extends BaseProductPurchaseViewResourceImpl {

	@Override
	public ProductPurchaseView
			getAccountAccountKeyProductProductKeyProductPurchaseView(
				String accountKey, String productKey)
		throws Exception {

		Account account = _accountService.getAccount(accountKey);
		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		return ProductPurchaseViewUtil.toProductPurchaseView(
			productEntry,
			_productConsumptionService.
				getAccountProductEntryProductConsumptions(
					account.getAccountId(), productEntry.getProductEntryId()),
			_productPurchaseService.getAccountProductEntryProductPurchases(
				account.getAccountId(), productEntry.getProductEntryId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS));
	}

	@Override
	public Page<ProductPurchaseView>
			getAccountAccountKeyProductPurchaseViewsPage(
				String accountKey, Pagination pagination)
		throws Exception {

		Account account = _accountService.getAccount(accountKey);

		return Page.of(
			transform(
				_productEntryService.getAccountProductEntries(
					account.getAccountId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				productEntry -> ProductPurchaseViewUtil.toProductPurchaseView(
					productEntry,
					_productConsumptionService.
						getAccountProductEntryProductConsumptions(
							account.getAccountId(),
							productEntry.getProductEntryId()),
					_productPurchaseService.
						getAccountProductEntryProductPurchases(
							account.getAccountId(),
							productEntry.getProductEntryId(), QueryUtil.ALL_POS,
							QueryUtil.ALL_POS))),
			pagination,
			_productEntryService.getAccountProductEntriesCount(
				account.getAccountId()));
	}

	@Reference
	private AccountService _accountService;

	@Reference
	private ProductConsumptionService _productConsumptionService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductEntryService _productEntryService;

	@Reference
	private ProductPurchaseService _productPurchaseService;

}