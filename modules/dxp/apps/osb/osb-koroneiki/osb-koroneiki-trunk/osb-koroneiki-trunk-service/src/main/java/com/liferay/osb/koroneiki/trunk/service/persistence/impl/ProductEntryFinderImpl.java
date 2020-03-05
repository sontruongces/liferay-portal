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

package com.liferay.osb.koroneiki.trunk.service.persistence.impl;

import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.osb.koroneiki.trunk.model.impl.ProductEntryImpl;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductEntryFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(service = ProductEntryFinder.class)
public class ProductEntryFinderImpl
	extends ProductEntryFinderBaseImpl implements ProductEntryFinder {

	public static final String COUNT_BY_ACCOUNT =
		ProductEntryFinder.class.getName() + ".countByAccount";

	public static final String FIND_BY_ACCOUNT =
		ProductEntryFinder.class.getName() + ".findByAccount";

	@Override
	public int countByAccount(long accountId) {
		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_ACCOUNT);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(accountId);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<ProductEntry> findByAccount(
		long accountId, int start, int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_ACCOUNT);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Koroneiki_ProductEntry", ProductEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(accountId);

			return (List<ProductEntry>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Reference
	private CustomSQL _customSQL;

}