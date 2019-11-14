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

import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.RangeTermFilter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.koroneiki.trunk.model.ProductPurchase",
	service = IndexerPostProcessor.class
)
public class ProductPurchaseIndexerPostProcessor
	extends BaseIndexerPostProcessor {

	@Override
	public void postProcessFullQuery(
			BooleanQuery fullQuery, SearchContext searchContext)
		throws Exception {

		List<BooleanClause<Query>> booleanClauses = fullQuery.clauses();

		for (BooleanClause<Query> booleanClause : booleanClauses) {
			Query query = booleanClause.getClause();

			_processBooleanFilter(query.getPreBooleanFilter());
		}
	}

	private BooleanFilter _getActiveFilter() {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		BooleanFilter activeDateFilter = new BooleanFilter();

		BooleanFilter perpetualFilter = new BooleanFilter();

		perpetualFilter.addRequiredTerm("perpetual", true);

		activeDateFilter.add(perpetualFilter, BooleanClauseOccur.SHOULD);

		BooleanFilter rangeBooleanFilter = new BooleanFilter();

		long now = System.currentTimeMillis();

		RangeTermFilter startRangeTermFilter = new RangeTermFilter(
			"startDate_sortable", true, true, String.valueOf(0),
			String.valueOf(now));

		rangeBooleanFilter.add(startRangeTermFilter, BooleanClauseOccur.MUST);

		RangeTermFilter endRangeTermFilter = new RangeTermFilter(
			"endDate_sortable", true, true, String.valueOf(now),
			String.valueOf(now + (Time.YEAR * 100)));

		rangeBooleanFilter.add(endRangeTermFilter, BooleanClauseOccur.MUST);

		activeDateFilter.add(rangeBooleanFilter, BooleanClauseOccur.SHOULD);

		booleanFilter.add(activeDateFilter, BooleanClauseOccur.MUST);

		return booleanFilter;
	}

	private BooleanFilter _getExpiredFilter() {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		RangeTermFilter rangeTermFilter = new RangeTermFilter(
			"endDate_sortable", true, true, String.valueOf(0),
			String.valueOf(System.currentTimeMillis()));

		booleanFilter.add(rangeTermFilter, BooleanClauseOccur.MUST);

		return booleanFilter;
	}

	private BooleanFilter _getUnactivatedFilter() {
		BooleanFilter booleanFilter = new BooleanFilter();

		booleanFilter.addRequiredTerm(
			Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		long now = System.currentTimeMillis();

		RangeTermFilter rangeTermFilter = new RangeTermFilter(
			"startDate_sortable", true, true, String.valueOf(now),
			String.valueOf(now + (Time.YEAR * 100)));

		booleanFilter.add(rangeTermFilter, BooleanClauseOccur.MUST);

		return booleanFilter;
	}

	private void _processBooleanClauses(
			BooleanFilter booleanFilter,
			List<BooleanClause<Filter>> booleanClauses,
			BooleanClauseOccur booleanClauseOccur)
		throws Exception {

		String state = null;

		Iterator<BooleanClause<Filter>> iterator = booleanClauses.iterator();

		while (iterator.hasNext()) {
			BooleanClause<Filter> booleanClause = iterator.next();

			Filter filter = booleanClause.getClause();

			if (filter instanceof BooleanFilter) {
				_processBooleanFilter((BooleanFilter)filter);
			}
			else if (filter instanceof TermFilter) {
				TermFilter termFilter = (TermFilter)filter;

				String field = termFilter.getField();

				if (field.equals("state")) {
					state = termFilter.getValue();

					iterator.remove();
				}
			}
		}

		if (Validator.isNull(state)) {
			return;
		}

		if (StringUtil.equalsIgnoreCase(state, "active")) {
			booleanFilter.add(_getActiveFilter(), booleanClauseOccur);
		}
		else if (StringUtil.equalsIgnoreCase(state, "expired")) {
			booleanFilter.add(_getExpiredFilter(), booleanClauseOccur);
		}
		else if (StringUtil.equalsIgnoreCase(state, "unactivated")) {
			booleanFilter.add(_getUnactivatedFilter(), booleanClauseOccur);
		}
	}

	private void _processBooleanFilter(BooleanFilter booleanFilter)
		throws Exception {

		_processBooleanClauses(
			booleanFilter, booleanFilter.getMustBooleanClauses(),
			BooleanClauseOccur.MUST);
		_processBooleanClauses(
			booleanFilter, booleanFilter.getMustNotBooleanClauses(),
			BooleanClauseOccur.MUST_NOT);
		_processBooleanClauses(
			booleanFilter, booleanFilter.getShouldBooleanClauses(),
			BooleanClauseOccur.SHOULD);
	}

}