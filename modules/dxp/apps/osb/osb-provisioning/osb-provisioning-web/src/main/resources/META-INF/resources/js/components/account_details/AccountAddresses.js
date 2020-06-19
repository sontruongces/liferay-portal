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

import ClayList from '@clayui/list';
import PropTypes from 'prop-types';
import React, {useEffect, useMemo, useState} from 'react';

import {
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import IconButton from '../IconButton';
import DetailField from './DetailField';

function AccountAddresses({accountKey, addURL, addresses}) {
	const [countryOptions, setCountryOptions] = useState([]);

	useEffect(() => {
		Liferay.Service('/country/get-countries', {
			active: true
		})
			.then(countries => {
				const options = countries.map(country => {
					return {
						label: country.nameCurrentValue,
						value: country.countryId
					};
				});

				setCountryOptions(options);
			})
			.catch(err => console.error(err));
	}, []);

	if (addresses.length === 0) {
		addresses.push({
			addressCountry: '-',
			addressLocality: '-',
			addressRegion: '-',
			id: '',
			postalCode: '-',
			streetAddressLine1: '-',
			streetAddressLine2: '-',
			streetAddressLine3: '-'
		});
	}

	return (
		<ClayList>
			{addresses.map((address, index) => (
				<Address
					accountKey={accountKey}
					address={address}
					addURL={addURL}
					count={index + 1}
					countryOptions={countryOptions}
					key={address.id}
				/>
			))}
		</ClayList>
	);
}

AccountAddresses.propTypes = {
	accountKey: PropTypes.string.isRequired,
	addURL: PropTypes.string.isRequired,
	addresses: PropTypes.arrayOf(
		PropTypes.shape({
			addressCountry: PropTypes.string,
			addressLocality: PropTypes.string,
			addressType: PropTypes.string,
			deletePostalAddressURL: PropTypes.string,
			editPostalAddressURL: PropTypes.string,
			id: PropTypes.string,
			mailing: PropTypes.string,
			postalCode: PropTypes.string,
			primary: PropTypes.string,
			streetAddressLine1: PropTypes.string,
			streetAddressLine2: PropTypes.string,
			streetAddressLine3: PropTypes.string
		})
	)
};

function Address({accountKey, addURL, address, count, countryOptions}) {
	const [regionOptions, setRegionOptions] = useState([]);

	const countryId = getCountryId();
	const regionId = getRegionId();

	const formData = useMemo(() => {
		return {
			accountKey,
			addressCountryId: countryId,
			addressLocality: convertDashToEmptyString(address.addressLocality),
			addressRegionId: regionId,
			addressType: convertDashToEmptyString(address.addressType),
			addressZip: convertDashToEmptyString(address.postalCode),
			mailing: address.mailing,
			primary: address.primary,
			streetAddressLine1: convertDashToEmptyString(
				address.streetAddressLine1
			),
			streetAddressLine2: convertDashToEmptyString(
				address.streetAddressLine2
			),
			streetAddressLine3: convertDashToEmptyString(
				address.streetAddressLine3
			)
		};
	}, [
		accountKey,
		address.addressLocality,
		address.addressType,
		address.mailing,
		address.postalCode,
		address.primary,
		address.streetAddressLine1,
		address.streetAddressLine2,
		address.streetAddressLine3,
		countryId,
		regionId
	]);

	useEffect(() => {
		Liferay.Service('/region/get-regions', {
			active: true,
			countryId: Number(countryId)
		})
			.then(regions => {
				const options = regions.map(region => {
					return {
						label: region.name,
						value: region.regionId
					};
				});

				setRegionOptions(options);
			})
			.catch(err => console.error(err));
	}, [countryId]);

	function getCountryId() {
		const country = countryOptions.find(
			option => option.label === address.addressCountry
		);

		if (country) {
			return country.value;
		}

		return '';
	}

	function getRegionId() {
		const region = regionOptions.find(
			option => option.label === address.addressRegion
		);

		if (region) {
			return region.value;
		}

		return '';
	}

	function setFieldType(fieldType = FIELD_TYPE_TEXT) {
		if (address.id) {
			return fieldType;
		}

		return FIELD_TYPE_NONEDITABLE;
	}

	return (
		<React.Fragment key={address.id}>
			<ClayList.Header>
				{Liferay.Language.get('address')} {count}
			</ClayList.Header>

			<DetailField
				fieldLabel={Liferay.Language.get('street-1')}
				fieldName="streetAddressLine1"
				formAction={address.editPostalAddressURL}
				formData={formData}
				type={setFieldType()}
			>
				{address.streetAddressLine1}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('city')}
				fieldName="addressLocality"
				formAction={address.editPostalAddressURL}
				formData={formData}
				type={setFieldType()}
			>
				{address.addressLocality}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('street-2')}
				fieldName="streetAddressLine2"
				formAction={address.editPostalAddressURL}
				formData={formData}
				type={setFieldType()}
			>
				{address.streetAddressLine2}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('state-province')}
				fieldName="addressRegionId"
				formAction={address.editPostalAddressURL}
				formData={formData}
				options={regionOptions}
				type={setFieldType(FIELD_TYPE_SELECT)}
			>
				{address.addressRegion}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('street-3')}
				fieldName="streetAddressLine3"
				formAction={address.editPostalAddressURL}
				formData={formData}
				type={setFieldType()}
			>
				{address.streetAddressLine3}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('postal-code')}
				fieldName="postalCode"
				formAction={address.editPostalAddressURL}
				formData={formData}
				type={setFieldType()}
			>
				{address.postalCode}
			</DetailField>

			<DetailField
				fieldLabel={Liferay.Language.get('country')}
				fieldName="addressCountryId"
				formAction={address.editPostalAddressURL}
				formData={formData}
				options={countryOptions}
				type={setFieldType(FIELD_TYPE_SELECT)}
			>
				{address.addressCountry}
			</DetailField>

			<ClayList.Item flex>
				<div className="address-controls btn-group" role="group">
					<div className="btn-group-item">
						<a
							className="add-address btn btn-secondary nav-btn nav-btn-monospaced"
							href={addURL}
							title={Liferay.Language.get('add')}
						>
							<svg
								aria-label={Liferay.Language.get('add')}
								className="lexicon-icon"
								role="img"
							>
								<use xlinkHref="#plus" />
							</svg>
						</a>
					</div>

					{!!address.deletePostalAddressURL && (
						<div className="btn-group-item">
							<IconButton
								cssClass="btn-secondary delete-address nav-btn nav-btn-monospaced"
								labelName={Liferay.Language.get('delete')}
								onClick={() => {
									if (
										window.confirm(
											Liferay.Language.get(
												'are-you-sure-you-want-to-delete-this-address'
											)
										)
									) {
										window.location.assign(
											address.deletePostalAddressURL
										);
									}
								}}
								svgId="#hr"
								title={Liferay.Language.get('delete')}
							/>
						</div>
					)}
				</div>
			</ClayList.Item>
		</React.Fragment>
	);
}

export default AccountAddresses;
