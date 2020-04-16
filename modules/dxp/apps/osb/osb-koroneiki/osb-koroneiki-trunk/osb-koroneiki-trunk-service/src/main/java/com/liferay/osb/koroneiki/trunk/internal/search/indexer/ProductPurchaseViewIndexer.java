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

package com.liferay.osb.koroneiki.trunk.internal.search.indexer;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.model.impl.view.ProductPurchaseViewImpl;
import com.liferay.osb.koroneiki.trunk.model.view.ProductPurchaseView;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	service = {ProductPurchaseViewIndexer.class, Indexer.class}
)
public class ProductPurchaseViewIndexer
	extends BaseIndexer<ProductPurchaseView> {

	public static final String CLASS_NAME = ProductPurchaseView.class.getName();

	public ProductPurchaseViewIndexer() {
		setDefaultSelectedFieldNames(Field.NAME);
		setPermissionAware(true);
		setStagingAware(false);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void reindex(ProductPurchaseView productPurchaseView)
		throws SearchException {

		try {
			if (IndexWriterHelperUtil.isIndexReadOnly() ||
				IndexWriterHelperUtil.isIndexReadOnly(getClassName()) ||
				!isIndexerEnabled()) {

				return;
			}

			if (productPurchaseView == null) {
				return;
			}

			doReindex(productPurchaseView);
		}
		catch (SearchException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SearchException(e);
		}
	}

	@Override
	protected void doDelete(ProductPurchaseView productPurchaseView)
		throws Exception {

		Document document = new DocumentImpl();

		document.addUID(
			getClassName(), (String)productPurchaseView.getPrimaryKeyObj());

		IndexWriterHelperUtil.deleteDocument(
			getSearchEngineId(), productPurchaseView.getCompanyId(),
			document.get(Field.UID), isCommitImmediately());
	}

	@Override
	protected Document doGetDocument(ProductPurchaseView productPurchaseView)
		throws Exception {

		Document document = getBaseModelDocument(productPurchaseView);

		document.addKeyword(
			Field.COMPANY_ID, productPurchaseView.getCompanyId());

		document.addKeyword("accountId", productPurchaseView.getAccountId());
		document.addKeyword(
			"productEntryId", productPurchaseView.getProductEntryId());

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productPurchaseView.getProductEntryId());

		document.addKeyword("name", productEntry.getName());

		document.addKeyword(
			"productPurchaseIds",
			getProductPurchaseIds(
				productPurchaseView.getAccountId(),
				productPurchaseView.getProductEntryId()));
		document.addKeyword(
			"productConsumptionIds",
			getProductConsumptionIds(
				productPurchaseView.getAccountId(),
				productPurchaseView.getProductEntryId()));

		return document;
	}

	@Override
	protected Summary doGetSummary(
			Document document, Locale locale, String snippet,
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			document.get("productEntryId"));

		return new Summary(productEntry.getName(), null);
	}

	@Override
	protected void doReindex(ProductPurchaseView productPurchaseView)
		throws Exception {

		IndexWriterHelperUtil.updateDocument(
			getSearchEngineId(), productPurchaseView.getCompanyId(),
			getDocument(productPurchaseView), isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexProductPurchaseViews(companyId);
	}

	protected Document getBaseModelDocument(
		ProductPurchaseView productPurchaseView) {

		Document document = newDocument();

		String className = productPurchaseView.getModelClassName();
		String classPK = (String)productPurchaseView.getPrimaryKeyObj();

		document.addKeyword(Field.ENTRY_CLASS_NAME, className);
		document.addKeyword(Field.ENTRY_CLASS_PK, classPK);

		document.addUID(className, classPK);

		return document;
	}

	protected long[] getProductConsumptionIds(
		long accountId, long productEntryId) {

		List<ProductConsumption> productConsumptions =
			_productConsumptionLocalService.
				getAccountProductEntryProductConsumptions(
					accountId, productEntryId);

		Stream<ProductConsumption> productConsumptionsStream =
			productConsumptions.stream();

		return productConsumptionsStream.mapToLong(
			ProductConsumption::getProductConsumptionId
		).toArray();
	}

	protected long[] getProductPurchaseIds(
		long accountId, long productEntryId) {

		List<ProductPurchase> productPurchases =
			_productPurchaseLocalService.getAccountProductEntryProductPurchases(
				accountId, productEntryId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		Stream<ProductPurchase> productPurchasesStream =
			productPurchases.stream();

		return productPurchasesStream.mapToLong(
			ProductPurchase::getProductPurchaseId
		).toArray();
	}

	protected void reindexProductPurchaseViews(long companyId)
		throws PortalException {

		List<Account> accounts = _accountLocalService.getAccounts(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Account account : accounts) {
			List<ProductPurchase> productPurchases =
				_productPurchaseLocalService.getAccountProductPurchases(
					account.getAccountId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			Stream<ProductPurchase> productPurchaseStream =
				productPurchases.stream();

			Set<Long> productEntryIds = productPurchaseStream.collect(
				Collectors.mapping(
					ProductPurchase::getProductEntryId, Collectors.toSet()));

			for (long productEntryId : productEntryIds) {
				ProductPurchaseView productPurchaseView =
					new ProductPurchaseViewImpl();

				productPurchaseView.setCompanyId(companyId);
				productPurchaseView.setAccountId(account.getAccountId());
				productPurchaseView.setProductEntryId(productEntryId);

				reindex(productPurchaseView);
			}
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}