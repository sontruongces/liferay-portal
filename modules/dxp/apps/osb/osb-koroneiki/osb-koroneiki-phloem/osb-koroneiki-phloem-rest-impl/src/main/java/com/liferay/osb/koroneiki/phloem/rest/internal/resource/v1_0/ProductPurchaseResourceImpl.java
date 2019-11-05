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
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ProductPurchasePermission;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.ProductPurchaseUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.odata.entity.v1_0.ProductPurchaseEntityModel;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.PhloemPermissionUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ExternalLinkResource;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.ProductPurchaseResource;
import com.liferay.osb.koroneiki.taproot.constants.WorkflowConstants;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.trunk.model.ProductField;
import com.liferay.osb.koroneiki.trunk.service.ProductFieldLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseLocalService;
import com.liferay.osb.koroneiki.trunk.service.ProductPurchaseService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/product-purchase.properties",
	scope = ServiceScope.PROTOTYPE, service = ProductPurchaseResource.class
)
public class ProductPurchaseResourceImpl
	extends BaseProductPurchaseResourceImpl implements EntityModelResource {

	@Override
	public void deleteProductPurchase(String productPurchaseKey)
		throws Exception {

		_productPurchaseService.deleteProductPurchase(productPurchaseKey);
	}

	@Override
	public void deleteProductPurchaseProductPurchasePermission(
			String productPurchaseKey,
			ProductPurchasePermission productPurchasePermission)
		throws Exception {

		_updateProductPurchasePermission(
			productPurchaseKey, "delete", productPurchasePermission);
	}

	@Override
	public Page<ProductPurchase> getAccountAccountKeyProductPurchasesPage(
			String accountKey, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getAccountProductPurchases(
					accountKey, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getAccountProductPurchasesCount(
				accountKey));
	}

	@Override
	public Page<ProductPurchase> getContactByOktaProductPurchasesPage(
			String oktaId, Pagination pagination)
		throws Exception {

		return _getContactProductPurchasesPage(
			_contactLocalService.getContactByOktaId(oktaId), pagination);
	}

	@Override
	public Page<ProductPurchase>
			getContactByUuidContactUuidProductPurchasesPage(
				String contactUuid, Pagination pagination)
		throws Exception {

		return _getContactProductPurchasesPage(
			_contactLocalService.getContactByUuid(contactUuid), pagination);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		long classNameId = _classNameLocalService.getClassNameId(
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class);

		return new ProductPurchaseEntityModel(
			_productFieldLocalService.getProductFieldNames(classNameId));
	}

	@NestedField("productPurchases")
	public List<ProductPurchase> getNestedFieldProductPurchases(
			@NestedFieldId("key") String accountKey)
		throws Exception {

		return transform(
			_productPurchaseService.getAccountProductPurchases(
				accountKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS),
			ProductPurchaseUtil::toProductPurchase);
	}

	@Override
	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception {

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.getProductPurchase(productPurchaseKey));
	}

	@Override
	public Page<ProductPurchase>
			getProductPurchaseByExternalLinkDomainEntityNameEntityPage(
				String domain, String entityName, String entityId,
				Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getProductPurchases(
					domain, entityName, entityId, pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getProductPurchasesCount(
				domain, entityName, entityId));
	}

	@Override
	public Page<ProductPurchase> getProductPurchasesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			document -> ProductPurchaseUtil.toProductPurchase(
				_productPurchaseLocalService.getProductPurchase(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public ProductPurchase postAccountAccountKeyProductPurchase(
			String accountKey, ProductPurchase productPurchase)
		throws Exception {

		Date startDate = productPurchase.getStartDate();
		Date endDate = productPurchase.getEndDate();

		if ((productPurchase.getPerpetual() != null) &&
			productPurchase.getPerpetual()) {

			startDate = null;
			endDate = null;
		}

		int quantity = GetterUtil.getInteger(productPurchase.getQuantity(), 1);

		int status = WorkflowConstants.STATUS_APPROVED;

		ProductPurchase.Status productPurchaseStatus =
			productPurchase.getStatus();

		if (productPurchaseStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				productPurchaseStatus.toString());
		}

		List<ProductField> productFields = getProductFields(
			productPurchase.getProperties(), null);

		ProductPurchase curProductPurchase =
			ProductPurchaseUtil.toProductPurchase(
				_productPurchaseService.addProductPurchase(
					accountKey, productPurchase.getProductKey(), startDate,
					endDate, quantity, status, productFields));

		if (!ArrayUtil.isEmpty(productPurchase.getExternalLinks())) {
			for (ExternalLink externalLink :
					productPurchase.getExternalLinks()) {

				_externalLinkResource.
					postProductPurchaseProductPurchaseKeyExternalLink(
						curProductPurchase.getKey(), externalLink);
			}
		}

		return curProductPurchase;
	}

	@Override
	public ProductPurchase putProductPurchase(
			String productPurchaseKey, ProductPurchase productPurchase)
		throws Exception {

		com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			curProductPurchase =
				_productPurchaseLocalService.getProductPurchase(
					productPurchaseKey);

		Date startDate = productPurchase.getStartDate();

		if (startDate == null) {
			startDate = curProductPurchase.getStartDate();
		}

		Date endDate = productPurchase.getEndDate();

		if (endDate == null) {
			endDate = curProductPurchase.getEndDate();
		}

		if ((productPurchase.getPerpetual() != null) &&
			productPurchase.getPerpetual()) {

			startDate = null;
			endDate = null;
		}

		int quantity = GetterUtil.getInteger(
			productPurchase.getQuantity(), curProductPurchase.getQuantity());

		int status = curProductPurchase.getStatus();

		ProductPurchase.Status productPurchaseStatus =
			productPurchase.getStatus();

		if (productPurchaseStatus != null) {
			status = WorkflowConstants.getLabelStatus(
				productPurchaseStatus.toString());
		}

		List<ProductField> productFields = getProductFields(
			productPurchase.getProperties(),
			curProductPurchase.getProductFields());

		return ProductPurchaseUtil.toProductPurchase(
			_productPurchaseService.updateProductPurchase(
				curProductPurchase.getProductPurchaseId(), startDate, endDate,
				quantity, status, productFields));
	}

	@Override
	public void putProductPurchaseProductPurchasePermission(
			String productPurchaseKey,
			ProductPurchasePermission productPurchasePermission)
		throws Exception {

		_updateProductPurchasePermission(
			productPurchaseKey, "add", productPurchasePermission);
	}

	protected List<ProductField> getProductFields(
		Map<String, String> properties,
		List<ProductField> defaultProductFields) {

		if (properties == null) {
			return defaultProductFields;
		}

		List<ProductField> productFields = new ArrayList<>();

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			ProductField productField =
				_productFieldLocalService.createProductField(0);

			productField.setName(entry.getKey());
			productField.setValue(entry.getValue());

			productFields.add(productField);
		}

		return productFields;
	}

	private Page<ProductPurchase> _getContactProductPurchasesPage(
			Contact contact, Pagination pagination)
		throws Exception {

		return Page.of(
			transform(
				_productPurchaseService.getContactProductPurchases(
					contact.getContactId(), pagination.getStartPosition(),
					pagination.getEndPosition()),
				ProductPurchaseUtil::toProductPurchase),
			pagination,
			_productPurchaseService.getContactProductPurchasesCount(
				contact.getContactId()));
	}

	private void _updateProductPurchasePermission(
			String productPurchaseKey, String operation,
			ProductPurchasePermission productPurchasePermission)
		throws Exception {

		com.liferay.osb.koroneiki.trunk.model.ProductPurchase productPurchase =
			_productPurchaseLocalService.getProductPurchase(productPurchaseKey);

		_productPurchasePermission.check(
			PermissionThreadLocal.getPermissionChecker(), productPurchase,
			ActionKeys.PERMISSIONS);

		List<String> actionIds = new ArrayList<>();

		if (GetterUtil.getBoolean(productPurchasePermission.getDelete())) {
			actionIds.add(ActionKeys.DELETE);
		}

		if (GetterUtil.getBoolean(productPurchasePermission.getPermissions())) {
			actionIds.add(ActionKeys.PERMISSIONS);
		}

		if (GetterUtil.getBoolean(productPurchasePermission.getUpdate())) {
			actionIds.add(ActionKeys.UPDATE);
		}

		if (GetterUtil.getBoolean(productPurchasePermission.getView())) {
			actionIds.add(ActionKeys.VIEW);
		}

		if (actionIds.isEmpty()) {
			return;
		}

		_phloemPermissionUtil.persistModelPermission(
			operation, contextCompany.getCompanyId(),
			com.liferay.osb.koroneiki.trunk.model.ProductPurchase.class.
				getName(),
			productPurchase.getProductPurchaseId(),
			productPurchasePermission.getRoleNames(), actionIds);
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ExternalLinkResource _externalLinkResource;

	@Reference
	private PhloemPermissionUtil _phloemPermissionUtil;

	@Reference
	private ProductFieldLocalService _productFieldLocalService;

	@Reference
	private ProductPurchaseLocalService _productPurchaseLocalService;

	@Reference
	private com.liferay.osb.koroneiki.trunk.permission.ProductPurchasePermission
		_productPurchasePermission;

	@Reference
	private ProductPurchaseService _productPurchaseService;

}