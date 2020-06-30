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
import React, {useEffect, useState} from 'react';

import {
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT,
	NAMESPACE
} from '../../utilities/constants';
import {convertDashToEmptyString} from '../../utilities/helpers';
import IconButton from '../IconButton';

function AccountAddresses({accountKey, addURL, addresses}) {
	const [countryOptions, setCountryOptions] = useState([]);

	useEffect(() => {
		Liferay.Service('/country/get-countries', {active: true})
			.then(countries => {
				const options = countries.map(country => {
					return {
						label: country.nameCurrentValue,
						value: country.countryId,
						zipRequired: country.zipRequired
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

	const countryId = getFieldId(countryOptions, address.addressCountry);
	const regionId = getFieldId(regionOptions, address.addressRegion);

	const formData = {
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
		streetAddressLine3: convertDashToEmptyString(address.streetAddressLine3)
	};

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

	function getFieldId(fieldOptions, currentValue) {
		const currentField = fieldOptions.find(
			option => option.label === currentValue
		);

		if (currentField) {
			return currentField.value;
		}

		return '';
	}

	function handleCancel() {
		// reset all values
		// exit out of edit mode
	}

	function handleSave() {
		// form submission
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

			<AddressField
				fieldLabel={Liferay.Language.get('street-1')}
				fieldName="streetAddressLine1"
				type={setFieldType()}
				value={address.streetAddressLine1}
			/>

			<AddressField
				fieldLabel={Liferay.Language.get('city')}
				fieldName="addressLocality"
				type={setFieldType()}
				value={address.addressLocality}
			/>

			<AddressField
				fieldLabel={Liferay.Language.get('street-2')}
				fieldName="streetAddressLine2"
				type={setFieldType()}
				value={address.streetAddressLine2}
			/>

			<AddressField
				fieldLabel={Liferay.Language.get('state-province')}
				fieldName="addressRegionId"
				options={regionOptions}
				type={setFieldType(FIELD_TYPE_SELECT)}
				value={address.addressRegion}
			/>

			<AddressField
				fieldLabel={Liferay.Language.get('street-3')}
				fieldName="streetAddressLine3"
				type={setFieldType()}
				value={address.streetAddressLine3}
			/>

			<AddressField
				fieldLabel={Liferay.Language.get('postal-code')}
				fieldName="postalCode"
				type={setFieldType()}
				value={address.postalCode}
			/>

			<AddressField
				fieldLabel={Liferay.Language.get('country')}
				fieldName="addressCountryId"
				options={countryOptions}
				type={setFieldType(FIELD_TYPE_SELECT)}
				value={address.addressCountry}
			/>

			<ClayList.Item className="address-controls" flex>
				<div className="btn-group" role="group">
					<div className="btn-group-item">
						<button
							className="btn btn-primary btn-sm save-btn"
							onClick={handleSave}
							role="button"
							type="button"
						>
							{Liferay.Language.get('save')}
						</button>
					</div>

					<div className="btn-group-item">
						<button
							className="btn btn-secondary btn-sm cancel-btn"
							onClick={handleCancel}
							role="button"
							type="button"
						>
							{Liferay.Language.get('cancel')}
						</button>
					</div>
				</div>

				<div className="btn-group" role="group">
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

function AddressField({
	fieldLabel,
	fieldName,
	options = [],
	type = FIELD_TYPE_TEXT,
	value
}) {
	const [fieldValue, setFieldValue] = useState(value);

	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	function handleChange(event) {
		setFieldValue(event.currentTarget.value);
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>{fieldLabel}</ClayList.ItemTitle>

				<div className="list-group-text">
					{type === FIELD_TYPE_SELECT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<select
								className="form-control"
								disabled={options.length === 0}
								id={namespacedFieldName}
								onChange={handleChange}
								value={fieldValue}
							>
								{options.map(option => (
									<option
										key={option.value}
										value={option.value}
									>
										{option.label}
									</option>
								))}
							</select>
						</label>
					)}

					{type === FIELD_TYPE_TEXT && (
						<label
							className="form-control-label"
							htmlFor={namespacedFieldName}
						>
							<input
								className="form-control"
								id={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={fieldValue}
							/>
						</label>
					)}
				</div>
			</div>
		</ClayList.Item>
	);
}

export default AccountAddresses;
