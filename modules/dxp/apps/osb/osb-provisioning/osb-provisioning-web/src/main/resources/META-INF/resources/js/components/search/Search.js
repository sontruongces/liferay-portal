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

import {request} from '../../utilities/helpers';

const MAX_RESULTS = 7;

function Search({resourceURL}) {
	const [keywords, setKeywords] = useState('');
	const [results, setResults] = useState([]);

	function handleOnChange(event) {
		const newValue = event.target.value;

		setKeywords(newValue);

		request(resourceURL, {keywords: newValue, maxResults: MAX_RESULTS})
			.then(({data}) => {
				setResults(data);
			})
			.catch(err => {
				console.error(err);
			});
	}

	return (
		<ClayAutocomplete>
			<ClayAutocomplete.Input
				className="search-input"
				onChange={handleOnChange}
				placeholder={Liferay.Language.get('search-accounts')}
				value={keywords}
			/>

			<button className="btn btn-default search-btn" type="button">
				<svg
					aria-hidden="true"
					aria-label={Liferay.Language.get('search-icon')}
					className="lexicon-icon lexicon-icon-search"
					role="image"
				>
					<use xlinkHref="#search" />
				</svg>
			</button>

			<ClayAutocomplete.DropDown active={keywords}>
				<ClayDropDown.ItemList>
					{results.map(result => (
						<ClayAutocomplete.Item
							href={result.url}
							key={result.key}
							match={keywords}
							value={result.name}
						/>
					))}
				</ClayDropDown.ItemList>

				<a className="all-results dropdown-item" href="">
					{Liferay.Language.get('see-all-results')}
				</a>
			</ClayAutocomplete.DropDown>
		</ClayAutocomplete>
	);
}

Search.propTypes = {
	resourceURL: PropTypes.string.isRequired
};

export default Search;
