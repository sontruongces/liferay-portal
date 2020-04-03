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

package com.liferay.osb.koroneiki.trunk.service.impl;

import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
import com.liferay.osb.koroneiki.root.util.ModelKeyGenerator;
import com.liferay.osb.koroneiki.trunk.exception.ProductEntryNameException;
import com.liferay.osb.koroneiki.trunk.exception.RequiredProductEntryException;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductConsumptionLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.osb.koroneiki.trunk.service.base.ProductEntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = "model.class.name=com.liferay.osb.koroneiki.trunk.model.ProductEntry",
	service = AopService.class
)
public class ProductEntryLocalServiceImpl
	extends ProductEntryLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	public ProductEntry addProductEntry(
			long userId, String name, List<ProductField> productFields)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(0, name);

		long productEntryId = counterLocalService.increment();

		ProductEntry productEntry = productEntryPersistence.create(
			productEntryId);

		productEntry.setCompanyId(user.getCompanyId());
		productEntry.setUserId(userId);
		productEntry.setProductEntryKey(
			ModelKeyGenerator.generate(productEntryId));
		productEntry.setName(name);

		productEntry = productEntryPersistence.update(productEntry);

		// Resources

		resourceLocalService.addResources(
			productEntry.getCompanyId(), 0, userId,
			ProductEntry.class.getName(), productEntry.getProductEntryId(),
			false, false, false);

		// Product fields

		for (ProductField productField : productFields) {
			_productFieldLocalService.addProductField(
				userId, ProductEntry.class.getName(), productEntryId,
				productField.getName(), productField.getValue());
		}

		return productEntry;
	}

	@Override
	public ProductEntry deleteProductEntry(long productEntryId)
		throws PortalException {

		return deleteProductEntry(
			productEntryLocalService.getProductEntry(productEntryId));
	}

	@Override
	public ProductEntry deleteProductEntry(ProductEntry productEntry)
		throws PortalException {

		int productConsumptionsCount =
			_productConsumptionLocalService.
				getProductEntryProductConsumptionsCount(
					productEntry.getProductEntryId());

		if (productConsumptionsCount > 0) {
			throw new RequiredProductEntryException.
				MustNotDeleteProductEntryReferencedByProductConsumption(
					productEntry.getProductEntryId());
		}

		int productPurchasesCount =
			_productPurchaseLocalService.getProductEntryProductPurchasesCount(
				productEntry.getProductEntryId());

		if (productPurchasesCount > 0) {
			throw new RequiredProductEntryException.
				MustNotDeleteProductEntryReferencedByProductPurchase(
					productEntry.getProductEntryId());
		}

		// External links

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		_externalLinkLocalService.deleteExternalLinks(
			classNameId, productEntry.getProductEntryId());

		// Resources

		resourceLocalService.deleteResource(
			productEntry.getCompanyId(), ProductEntry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			productEntry.getProductEntryId());

		return productEntryPersistence.remove(productEntry);
	}

	public ProductEntry fetchProductEntryByName(String name) {
		return productEntryPersistence.fetchByName(name);
	}

	public List<ProductEntry> getAccountProductEntries(
		long accountId, String[] products, String state, String search,
		int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (products.length > 0) {
			params.put("products", products);
		}

		if (Validator.isNotNull(state)) {
			params.put("state", state);
		}

		if (Validator.isNotNull(search)) {
			params.put("search", search);
		}

		return productEntryFinder.findByAccount(accountId, params, start, end);
	}

	public int getAccountProductEntriesCount(
		long accountId, String[] products, String state, String search) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (products.length > 0) {
			params.put("products", products);
		}

		if (Validator.isNotNull(state)) {
			params.put("state", state);
		}

		if (Validator.isNotNull(search)) {
			params.put("search", search);
		}

		return productEntryFinder.countByAccount(accountId, params);
	}

	public ProductEntry getProductEntry(String productEntryKey)
		throws PortalException {

		return productEntryPersistence.findByProductEntryKey(productEntryKey);
	}

	public ProductEntry getProductEntryByName(String name)
		throws PortalException {

		return productEntryPersistence.findByName(name);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProductEntry reindex(long productEntryId) throws PortalException {
		return productEntryPersistence.findByPrimaryKey(productEntryId);
	}

	public Hits search(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		try {
			Indexer<ProductEntry> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(ProductEntry.class);

			SearchContext searchContext = new SearchContext();

			searchContext.setAndSearch(false);

			Map<String, Serializable> attributes = new HashMap<>();

			attributes.put("externalLinkEntityIds", keywords);
			attributes.put("name", keywords);

			searchContext.setAttributes(attributes);

			searchContext.setCompanyId(companyId);
			searchContext.setEnd(end);

			if (sort != null) {
				searchContext.setSorts(sort);
			}

			searchContext.setStart(start);

			QueryConfig queryConfig = searchContext.getQueryConfig();

			queryConfig.setHighlightEnabled(false);
			queryConfig.setScoreEnabled(false);

			return indexer.search(searchContext);
		}
		catch (Exception exception) {
			throw new PortalException(exception);
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProductEntry updateProductEntry(
			long userId, long productEntryId, String name,
			List<ProductField> productFields)
		throws PortalException {

		validate(productEntryId, name);

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		productEntry.setName(name);

		// Product Fields

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		Map<String, ProductField> productFieldsMap = getProductFieldsMap(
			productEntryId);

		for (ProductField productField : productFields) {
			ProductField curProductField = productFieldsMap.remove(
				productField.getName());

			if (curProductField == null) {
				_productFieldLocalService.addProductField(
					userId, classNameId, productEntryId, productField.getName(),
					productField.getValue());
			}
			else {
				_productFieldLocalService.updateProductField(
					curProductField.getProductFieldId(),
					productField.getValue());
			}
		}

		for (ProductField productField : productFieldsMap.values()) {
			_productFieldLocalService.deleteProductField(
				productField.getProductFieldId());
		}

		return productEntryPersistence.update(productEntry);
	}

	protected Map<String, ProductField> getProductFieldsMap(
		long productEntryId) {

		Map<String, ProductField> productFieldsMap = new HashMap<>();

		long classNameId = classNameLocalService.getClassNameId(
			ProductEntry.class);

		List<ProductField> productFields =
			_productFieldLocalService.getProductFields(
				classNameId, productEntryId);

		for (ProductField productField : productFields) {
			productFieldsMap.put(productField.getName(), productField);
		}

		return productFieldsMap;
	}

	protected void validate(long productEntryId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new ProductEntryNameException();
		}

		ProductEntry productEntry = productEntryPersistence.fetchByName(name);

		if ((productEntry != null) &&
			(productEntry.getProductEntryId() != productEntryId)) {

			throw new ProductEntryNameException.MustNotBeDuplicate(name);
		}
	}

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

	@Reference
	private ProductConsumptionLocalService _productConsumptionLocalService;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

}