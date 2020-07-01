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

package com.liferay.osb.provisioning.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ProductBundleProducts service. Represents a row in the &quot;Provisioning_ProductBundleProducts&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductsModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.provisioning.model.impl.ProductBundleProductsImpl"
)
@ProviderType
public interface ProductBundleProducts
	extends PersistedModel, ProductBundleProductsModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProductBundleProducts, Long>
		PRODUCT_BUNDLE_ID_ACCESSOR =
			new Accessor<ProductBundleProducts, Long>() {

				@Override
				public Long get(ProductBundleProducts productBundleProducts) {
					return productBundleProducts.getProductBundleId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ProductBundleProducts> getTypeClass() {
					return ProductBundleProducts.class;
				}

			};
	public static final Accessor<ProductBundleProducts, String>
		PRODUCT_KEY_ACCESSOR = new Accessor<ProductBundleProducts, String>() {

			@Override
			public String get(ProductBundleProducts productBundleProducts) {
				return productBundleProducts.getProductKey();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<ProductBundleProducts> getTypeClass() {
				return ProductBundleProducts.class;
			}

		};

}