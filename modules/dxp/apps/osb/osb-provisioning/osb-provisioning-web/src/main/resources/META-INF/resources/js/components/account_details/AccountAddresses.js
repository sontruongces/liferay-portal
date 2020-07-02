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
import React, {useEffect, useRef, useState} from 'react';

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

				options.unshift({label: '-', value: 0, zipRequired: false});

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
		<>
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
		</>
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
	const [countryId, setCountryId] = useState(
		getFieldId(countryOptions, address.addressCountry)
	);
	const [regionOptions, setRegionOptions] = useState([]);
	const [zipCode, setZipCode] = useState(
		convertDashToEmptyString(address.postalCode)
	);
	const [zipRequired, setZipRequired] = useState(false);
	const formRef = useRef();

	const regionId = getFieldId(regionOptions, address.addressRegion);

	useEffect(() => {
		setCountryId(getFieldId(countryOptions, address.addressCountry));
	}, [address, countryOptions]);

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

	function getZipRequirement(id) {
		const currentCountry = countryOptions.find(
			option => option.value === id
		);

		return currentCountry ? currentCountry.zipRequired : false;
	}

	function handleCancel() {
		// reset all values
		// exit out of edit mode
	}

	function handleCountryUpdate(id) {
		setCountryId(id);
		setZipRequired(getZipRequirement(id));
	}

	function handlePostalCodeUpdate(value) {
		setZipCode(value);
	}

	function handleSave() {
		formRef.current.submit();
	}

	function setFieldType(fieldType = FIELD_TYPE_TEXT) {
		if (address.id) {
			return fieldType;
		}

		return FIELD_TYPE_NONEDITABLE;
	}

	return (
		<form
			action={address.editPostalAddressURL}
			key={address.id}
			method="post"
			ref={formRef}
		>
			<input
				name={`${NAMESPACE}accountKey`}
				type="hidden"
				value={accountKey}
			/>
			<input
				name={`${NAMESPACE}addressType`}
				type="hidden"
				value={convertDashToEmptyString(address.addressType)}
			/>
			<input
				name={`${NAMESPACE}mailing`}
				type="hidden"
				value={convertDashToEmptyString(address.mailing)}
			/>
			<input
				name={`${NAMESPACE}primary`}
				type="hidden"
				value={convertDashToEmptyString(address.primary)}
			/>

			<ClayList>
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
					displayValue={address.addressRegion}
					fieldLabel={Liferay.Language.get('state-province')}
					fieldName="addressRegionId"
					options={regionOptions}
					type={setFieldType(FIELD_TYPE_SELECT)}
					value={regionId}
				/>

				<AddressField
					fieldLabel={Liferay.Language.get('street-3')}
					fieldName="streetAddressLine3"
					type={setFieldType()}
					value={address.streetAddressLine3}
				/>

				<AddressField
					fieldLabel={Liferay.Language.get('postal-code')}
					fieldName="addressZip"
					required={zipRequired}
					type={setFieldType()}
					updateFn={handlePostalCodeUpdate}
					value={address.postalCode}
				/>

				<AddressField
					displayValue={address.addressCountry}
					fieldLabel={Liferay.Language.get('country')}
					fieldName="addressCountryId"
					options={countryOptions}
					type={setFieldType(FIELD_TYPE_SELECT)}
					updateFn={handleCountryUpdate}
					value={countryId}
				/>

				<ClayList.Item className="address-controls" flex>
					<div className="btn-group" role="group">
						<div className="btn-group-item">
							<button
								className="btn btn-primary btn-sm save-btn"
								disabled={zipRequired && !zipCode}
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
			</ClayList>
		</form>
	);
}

function AddressField({
	displayValue = value,
	fieldLabel,
	fieldName,
	options = [],
	required = false,
	type = FIELD_TYPE_TEXT,
	updateFn,
	value
}) {
	const [fieldValue, setFieldValue] = useState(value);
	const namespacedFieldName = `${NAMESPACE}${fieldName}`;

	useEffect(() => {
		setFieldValue(value);
	}, [value]);

	function handleChange(event) {
		const currentTarget = event.currentTarget;

		setFieldValue(currentTarget.value);
		updateFn(currentTarget.value);
	}

	return (
		<ClayList.Item flex>
			<div className="detail-field">
				<ClayList.ItemTitle>
					{fieldLabel}{' '}
					{required && <span className="text-warning">{'*'}</span>}
				</ClayList.ItemTitle>

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
								name={namespacedFieldName}
								onChange={handleChange}
								value={convertDashToEmptyString(fieldValue)}
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
								name={namespacedFieldName}
								onChange={handleChange}
								type="text"
								value={convertDashToEmptyString(fieldValue)}
							/>
						</label>
					)}
				</div>
			</div>
		</ClayList.Item>
	);
}

export default AccountAddresses;
