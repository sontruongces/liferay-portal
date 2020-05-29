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
import React from 'react';

import {
	FIELD_TYPE_NONEDITABLE,
	FIELD_TYPE_SELECT,
	FIELD_TYPE_TEXT
} from '../../utilities/constants';
import IconButton from '../IconButton';
import DetailField from './DetailField';

function AccountAddress({addURL, addresses}) {
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

	function getCountries() {
		return Liferay.Address.getCountries(countries =>
			countries.map(country => {
				return {
					label: country.nameCurrentValue,
					value: country.countryId
				};
			})
		);
	}

	function setFieldType(id, fieldType = FIELD_TYPE_TEXT) {
		if (id) {
			return fieldType;
		}

		return FIELD_TYPE_NONEDITABLE;
	}

	function setFormData(id) {
		const currentAddress = addresses.filter(address => address.id === id);

		return {
			addressCountryId: currentAddress.addressCountryId,
			addressLocality: currentAddress.addressLocality,
			addressRegionId: currentAddress.addressRegionId,
			addressType: currentAddress.addressType,
			addressZip: currentAddress.addressZip,
			mailing: currentAddress.mailing,
			primary: currentAddress.primary,
			streetAddressLine1: currentAddress.streetAddressLine1,
			streetAddressLine2: currentAddress.streetAddressLine2,
			streetAddressLine3: currentAddress.streetAddressLine3
		};
	}

	return (
		<ClayList>
			{addresses.map((address, index) => (
				<React.Fragment key={address.id}>
					<ClayList.Header>
						{Liferay.Language.get('address')} {index + 1}
					</ClayList.Header>

					<DetailField
						fieldLabel={Liferay.Language.get('street-1')}
						fieldName="streetAddressLine1"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						type={setFieldType(address.id)}
					>
						{address.streetAddressLine1}
					</DetailField>

					<DetailField
						fieldLabel={Liferay.Language.get('city')}
						fieldName="addressLocality"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						type={setFieldType(address.id)}
					>
						{address.addressLocality}
					</DetailField>

					<DetailField
						fieldLabel={Liferay.Language.get('street-2')}
						fieldName="streetAddressLine2"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						type={setFieldType(address.id)}
					>
						{address.streetAddressLine2}
					</DetailField>

					<DetailField
						fieldLabel={Liferay.Language.get('state-province')}
						fieldName="addressRegionId"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						// TODO
						options={[]}
						type={setFieldType(address.id, FIELD_TYPE_SELECT)}
					>
						{address.addressRegion}
					</DetailField>

					<DetailField
						fieldLabel={Liferay.Language.get('street-3')}
						fieldName="streetAddressLine3"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						type={setFieldType(address.id)}
					>
						{address.streetAddressLine3}
					</DetailField>

					<DetailField
						fieldLabel={Liferay.Language.get('postal-code')}
						fieldName="postalCode"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						type={setFieldType(address.id)}
					>
						{address.postalCode}
					</DetailField>

					<DetailField
						fieldLabel={Liferay.Language.get('country')}
						fieldName="addressCountryId"
						formAction={address.editPostalAddressURL}
						formData={setFormData(address.id)}
						// TODO
						options={getCountries()}
						type={setFieldType(address.id, FIELD_TYPE_SELECT)}
					>
						{address.addressCountry}
					</DetailField>

					<ClayList.Item flex>
						<div
							className="address-controls btn-group"
							role="group"
						>
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
										labelName={Liferay.Language.get(
											'delete'
										)}
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
			))}
		</ClayList>
	);
}

AccountAddress.propTypes = {
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

export default AccountAddress;
