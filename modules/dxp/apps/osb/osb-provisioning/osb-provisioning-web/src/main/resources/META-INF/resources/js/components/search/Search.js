/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import ClayAutocomplete from '@clayui/autocomplete';
import ClayDropDown from '@clayui/drop-down';
import PropTypes from 'prop-types';
import React, {useState} from 'react';

function Search() {
	const [value, setValue] = useState('');

	return (
		<ClayAutocomplete>
			<ClayAutocomplete.Input
				className="search-input"
				onChange={event => setValue(event.target.value)}
				placeholder={Liferay.Language.get('search-accounts')}
				value={value}
			/>

			<button className="btn btn-default search-btn" type="button">
				<svg
					aria-hidden="true"
					className="lexicon-icon lexicon-icon-search"
					role="image"
				>
					<use xlinkHref="#search" />
				</svg>
			</button>

			<ClayAutocomplete.DropDown active={value}>
				<ClayDropDown.ItemList>
					<ClayAutocomplete.Item
						href="/"
						key="1"
						match={value}
						value="test"
					/>
				</ClayDropDown.ItemList>

				<a className="dropdown-item" href="">{Liferay.Language.get('see-all-results')}</a>
			</ClayAutocomplete.DropDown>
		</ClayAutocomplete>
	);
}

Search.propTypes = {
	endpoint: PropTypes.string.isRequired
};

export default Search;
