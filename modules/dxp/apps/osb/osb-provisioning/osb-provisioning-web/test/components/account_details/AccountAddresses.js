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

import {cleanup, fireEvent, render, wait} from '@testing-library/react';
import React from 'react';

import AccountAddress from '../../../src/main/resources/META-INF/resources/js/components/account_details/AccountAddresses';

const singleAddress = {
	addresses: [
		{
			addressCountry: 'United States',
			addressLocality: 'Diamond Bar',
			addressRegion: 'California',
			deletePostalAddressURL: '/',
			editPostalAddressURL: '/',
			id: '123',
			postalCode: '91765',
			streetAddressLine1: '1400 Montefino Ave',
			streetAddressLine2: '-',
			streetAddressLine3: '-'
		}
	]
};

function renderAccountAddress(props) {
	return render(
		<AccountAddress
			accountKey="key123"
			addresses={[
				{
					addressCountry: 'United States',
					addressLocality: 'Diamond Bar',
					addressRegion: 'California',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '123',
					postalCode: '91765',
					streetAddressLine1: '1400 Montefino Ave',
					streetAddressLine2: '-',
					streetAddressLine3: '-'
				},
				{
					addressCountry: 'United Arab Emirates',
					addressLocality: 'Dubai Media City',
					addressRegion: '-',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '456',
					postalCode: '-',
					streetAddressLine1: 'Building 8',
					streetAddressLine2: 'Office 207',
					streetAddressLine3: '-'
				},
				{
					addressCountry: 'P.R. China',
					addressLocality: 'Dalian',
					addressRegion: 'Liaoning',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '789',
					postalCode: '116023',
					streetAddressLine1: '537 Huangpu Road Taide Building',
					streetAddressLine2: '1005 High-Tech Zone',
					streetAddressLine3: '-'
				}
			]}
			addURL="/"
			{...props}
		/>
	);
}

describe('AccountAddress', () => {
	beforeEach(() => {
		Liferay.Service.mockImplementation(() =>
			Promise.resolve([
				{
					countryId: '2',
					name: 'Liaoning',
					nameCurrentValue: 'China',
					regionId: '2019',
					zipRequired: true
				},
				{
					countryId: '217',
					nameCurrentValue: 'United Arab Emirates',
					zipRequired: false
				},
				{
					countryId: '19',
					name: 'California',
					nameCurrentValue: 'United States',
					regionId: '19005',
					zipRequired: true
				}
			])
		);
	});

	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountAddress();

		return wait(() => expect(container).toBeTruthy());
	});

	it('displays an addresses List Group with dashes for each field when no address was provided', () => {
		const {getAllByText} = renderAccountAddress({addresses: []});

		return wait(() => expect(getAllByText('-').length).toBe(7));
	});

	it('does not allow any address fields to be edited when no address was provided', () => {
		const {getAllByText, queryByText} = renderAccountAddress({
			addresses: []
		});

		fireEvent.click(getAllByText('-')[0]);

		return wait(() => {
			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});

	it('displays an add button when no address was provided', () => {
		const {queryByLabelText} = renderAccountAddress({addresses: []});

		return wait(() => expect(queryByLabelText('add')).toBeTruthy());
	});

	it('displays no delete button when no address was provided', () => {
		const {queryByLabelText} = renderAccountAddress({addresses: []});

		return wait(() => expect(queryByLabelText('delete')).toBeFalsy());
	});

	it('displays multiple address List Groups when multiple addresses are provided', () => {
		const {getByText} = renderAccountAddress();

		return wait(() => {
			getByText('address 1');
			getByText('address 2');
			getByText('address 3');
		});
	});

	it('allows address fields to be edited when at least one address was provided', () => {
		const {getByText} = renderAccountAddress();

		fireEvent.click(getByText('Diamond Bar'));

		return wait(() => {
			getByText('save');
			getByText('cancel');
		});
	});

	it('displays an add button for each provided addresses', () => {
		const {getAllByLabelText} = renderAccountAddress();

		return wait(() => expect(getAllByLabelText('add').length).toBe(3));
	});

	it('displays a delete button for each provided addresses', () => {
		const {getAllByLabelText} = renderAccountAddress();

		return wait(() => expect(getAllByLabelText('delete').length).toBe(3));
	});

	it('displays all address fields as editable when any one of the address fields is clicked', () => {
		const {container, getByText} = renderAccountAddress();

		fireEvent.click(getByText('Building 8'));

		return wait(() => {
			expect(container.querySelectorAll('select').length).toBe(2);
			expect(container.querySelectorAll('input[type=text]').length).toBe(
				5
			);

			getByText('save');
			getByText('cancel');
		});
	});

	it('restores all address fields when the cancel button is clicked', () => {
		const {container, getByText, queryByText} = renderAccountAddress();

		fireEvent.click(getByText('Building 8'));
		fireEvent.click(getByText('cancel'));

		return wait(() => {
			expect(container.querySelectorAll('select').length).toBe(0);
			expect(container.querySelectorAll('input[type=text]').length).toBe(
				0
			);

			expect(queryByText('save')).toBeFalsy();
			expect(queryByText('cancel')).toBeFalsy();
		});
	});

	it('displays PRC, UAE, and USA as country options when the user clicks on a Country field', () => {
		const {getByText} = renderAccountAddress(singleAddress);

		fireEvent.click(getByText('United States'));

		return wait(() => {
			getByText('China');
			getByText('United Arab Emirates');
			getByText('United States');
		});
	});

	it('displays country specific region options when a Country Field with valid Regions is selected', () => {
		const {getByText} = renderAccountAddress(singleAddress);

		return wait(() => {
			fireEvent.change(getByText('United States'), {
				target: {value: 'China'}
			});

			getByText('California');
		});
	});

	it('displays an asterisk next to postal code when one is required for the selected Country field', () => {
		const {getByText} = renderAccountAddress(singleAddress);

		return wait(() => {
			getByText('*');
		});
	});

	it('keeps the Save button disabled when a required Postal Code field contains no value', () => {
		const {getByText} = renderAccountAddress({
			addresses: [
				{
					addressCountry: 'United States',
					addressLocality: 'Diamond Bar',
					addressRegion: 'California',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '123',
					postalCode: '-',
					streetAddressLine1: '1400 Montefino Ave',
					streetAddressLine2: '-',
					streetAddressLine3: '-'
				}
			]
		});

		fireEvent.click(getByText('Diamond Bar'));

		return wait(() => {
			expect(getByText('save').disabled).toBeTruthy();
		});
	});

	it('displays no asterisk next to Postal Code field when one is not required for the selected Country field', () => {
		const {getByText, queryByText} = renderAccountAddress({
			addresses: [
				{
					addressCountry: 'United Arab Emirates',
					addressLocality: 'Dubai Media City',
					addressRegion: '-',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '456',
					postalCode: '-',
					streetAddressLine1: 'Building 8',
					streetAddressLine2: 'Office 207',
					streetAddressLine3: '-'
				}
			]
		});

		fireEvent.click(getByText('Office 207'));

		return wait(() => {
			expect(queryByText('*')).toBeFalsy();
		});
	});

	it('allows Save button to be clickable when Postal Code field is empty for a selected Country field that does not require postal code', () => {
		const {getByText} = renderAccountAddress({
			addresses: [
				{
					addressCountry: 'United Arab Emirates',
					addressLocality: 'Dubai Media City',
					addressRegion: '-',
					deletePostalAddressURL: '/',
					editPostalAddressURL: '/',
					id: '456',
					postalCode: '-',
					streetAddressLine1: 'Building 8',
					streetAddressLine2: 'Office 207',
					streetAddressLine3: '-'
				}
			]
		});

		fireEvent.click(getByText('Office 207'));

		return wait(() => {
			expect(getByText('save').disabled).toBeFalsy();
		});
	});
});
