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
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static final String JOIN_BY_ACTIVE =
		ProductEntryFinder.class.getName() + ".joinByActive";

	public static final String JOIN_BY_INACTIVE =
		ProductEntryFinder.class.getName() + ".joinByInactive";

	@Override
	public int countByAccount(
		long accountId, LinkedHashMap<String, Object> params) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_ACCOUNT);

			String[] keywords = _customSQL.keywords(
				(String)params.get("search"), true, WildcardMode.SURROUND);

			boolean keywordsEmpty = _isKeywordsEmpty(keywords);

			if (keywordsEmpty) {
				sql = _replaceKeywordConditionsWithBlank(sql);
			}
			else {
				sql = _customSQL.replaceKeywords(
					sql, "Koroneiki_ProductEntry.name", StringPool.LIKE, false,
					keywords);
				sql = StringUtil.replaceLast(
					_customSQL.replaceAndOperator(sql, false), "OR",
					StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(accountId);

			if (!keywordsEmpty) {
				qPos.add(keywords, 2);
			}

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
		long accountId, LinkedHashMap<String, Object> params, int start,
		int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_ACCOUNT);

			String[] keywords = _customSQL.keywords(
				(String)params.get("search"), true, WildcardMode.SURROUND);

			boolean keywordsEmpty = _isKeywordsEmpty(keywords);

			if (keywordsEmpty) {
				sql = _replaceKeywordConditionsWithBlank(sql);
			}
			else {
				sql = _customSQL.replaceKeywords(
					sql, "Koroneiki_ProductEntry.name", StringPool.LIKE, false,
					keywords);
				sql = StringUtil.replaceLast(
					_customSQL.replaceAndOperator(sql, false), "OR",
					StringPool.BLANK);
			}

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("Koroneiki_ProductEntry", ProductEntryImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(accountId);

			if (!keywordsEmpty) {
				qPos.add(keywords, 2);
			}

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

	protected String getJoin(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (Validator.isNotNull(entry.getValue())) {
				sb.append(getJoin(entry.getKey(), entry.getValue()));
			}
		}

		return sb.toString();
	}

	protected String getJoin(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("state") &&
			StringUtil.equalsIgnoreCase((String)value, "inactive")) {

			join = _customSQL.get(getClass(), JOIN_BY_INACTIVE);
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(0, pos);
			}
		}

		return join;
	}

	protected String getWhere(LinkedHashMap<String, Object> params) {
		if ((params == null) || params.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(params.size());

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (Validator.isNotNull(entry.getValue())) {
				sb.append(getWhere(entry.getKey(), entry.getValue()));
			}
		}

		return sb.toString();
	}

	protected String getWhere(String key, Object value) {
		String join = StringPool.BLANK;

		if (key.equals("state")) {
			if (StringUtil.equalsIgnoreCase((String)value, "active")) {
				join = _customSQL.get(getClass(), JOIN_BY_ACTIVE);
			}
			else {
				join = _customSQL.get(getClass(), JOIN_BY_INACTIVE);
			}
		}

		if (Validator.isNotNull(join)) {
			int pos = join.indexOf("WHERE");

			if (pos != -1) {
				join = join.substring(pos + 5);

				join = join.concat(" AND ");
			}
			else {
				join = StringPool.BLANK;
			}
		}

		return join;
	}

	private boolean _isKeywordsEmpty(String[] keywords) {
		boolean emptyKeywords = false;
		boolean nonEmptyKeywords = false;

		for (String keyword : keywords) {
			emptyKeywords = Validator.isNull(keyword);
			nonEmptyKeywords = Validator.isNotNull(keyword);
		}

		if (emptyKeywords && !nonEmptyKeywords) {
			return true;
		}

		return false;
	}

	private String _replaceKeywordConditionsWithBlank(String sql) {
		Matcher matcher = _pattern.matcher(sql);

		return matcher.replaceAll(StringPool.BLANK);
	}

	private static final Pattern _pattern = Pattern.compile(
		"AND\\s*\\(*[A-Za-z\\.\\s\\?\\[\\]$_]*\\s*\\)*\\s*[A-Z\\[\\]$_]*\\s*" +
			"\\)*");

	@Reference
	private CustomSQL _customSQL;

}