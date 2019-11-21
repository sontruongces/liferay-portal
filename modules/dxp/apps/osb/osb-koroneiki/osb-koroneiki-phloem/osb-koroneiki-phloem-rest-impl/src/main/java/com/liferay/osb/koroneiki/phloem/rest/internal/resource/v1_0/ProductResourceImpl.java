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

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductEntryEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.trunk.constants.TrunkActionKeys;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.permission.ProductEntryPermission;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductEntryService;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductResource.class
)
public class ProductResourceImpl
	extends BaseProductResourceImpl implements EntityModelResource {

	@Override
	public void deleteProduct(String productKey) throws Exception {
		_productEntryService.deleteProductEntry(productKey);
	}

	@Override
	public void deleteProductProductPermission(
			String productKey, ProductPermission productPermission)
		throws Exception {

		_updateProductPermission(productKey, "delete", productPermission);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Product getProduct(String productKey) throws Exception {
		return ProductUtil.toProduct(
			_productEntryService.getProductEntry(productKey));
	}

	@Override
	public Page<Product> getProductByExternalLinkDomainEntityNameEntityPage(
			String domain, String entityName, String entityId,
			Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productEntryService.getProductEntries(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductUtil::toProduct),
			pagination,
			_productEntryService.getProductEntriesCount(
				domain, entityName, entityId));
	}

	@Override
	public Page<Product> getProductsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, ProductEntry.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductUtil.toProduct(
				_productEntryLocalService.getProductEntry(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public Product postProduct(Product product) throws Exception {
		Product curProduct = ProductUtil.toProduct(
			_productEntryService.addProductEntry(product.getName()));

		if (!ArrayUtil.isEmpty(product.getExternalLinks())) {
			for (ExternalLink externalLink : product.getExternalLinks()) {
				_externalLinkResource.postProductProductKeyExternalLink(
					curProduct.getKey(), externalLink);
			}
		}

		return curProduct;
	}

	@Override
	public Product putProduct(String productKey, Product product)
		throws Exception {

		return ProductUtil.toProduct(
			_productEntryService.updateProductEntry(
				productKey, product.getName()));
	}

	@Override
	public void putProductProductPermission(
			String productKey, ProductPermission productPermission)
		throws Exception {

		_updateProductPermission(productKey, "add", productPermission);
	}

	private void _updateProductPermission(
			String productKey, String operation,
			ProductPermission productPermission)
		throws Exception {

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productKey);

		_productEntryPermission.check(
			PermissionThreadLocal.getPermissionChecker(), productEntry,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(productPermission.getConsume())) {
			actionIds.add(TrunkActionKeys.CONSUME);
		}

		if (GetterUtil.getBoolean(productPermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(productPermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(productPermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(productPermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			ProductEntry.class.getName(), productEntry.getProductEntryId(),
			productPermission.getRoleNames(), actionIds);
	}

	private static final EntityModel _entityModel =
		new ProductEntryEntityModel();

	@Reference
	private ExternalLinkResource _externalLinkResource;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductEntryPermission _productEntryPermission;

	@Reference
	private ProductEntryService _productEntryService;

}