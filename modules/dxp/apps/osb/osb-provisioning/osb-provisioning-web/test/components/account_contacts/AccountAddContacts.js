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

import {cleanup, fireEvent, render, within} from '@testing-library/react';
import React from 'react';

import AccountAddContacts from '../../../src/main/resources/META-INF/resources/js/components/account_contacts/AccountAddContacts';

function renderAccountAddContacts(props) {
	const allContactRoles = [
		{key: 'KEY-100', name: 'Manager'},
		{key: 'KEY-101', name: 'Member'},
		{key: 'KEY-102', name: 'Analyst'},
		{key: 'KEY-103', name: 'Designer'}
	];

	return render(
		<AccountAddContacts
			accountName={'Test Account'}
			allContactRoles={allContactRoles}
			redirect="/"
			{...props}
		/>
	);
}

describe('AccountAddress', () => {
	afterEach(cleanup);

	it('renders', () => {
		const {container} = renderAccountAddContacts();

		expect(container).toBeTruthy();
	});

	it('displays title headers', () => {
		const {getByText} = renderAccountAddContacts({});

		getByText('email');
		getByText('roles');
		getByText('account');
	});

	it('displays full name if provided', () => {
		const {getByText} = renderAccountAddContacts({
			initialContactRoleKeys: ['KEY-100'],
			userEmailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		getByText('name');
		getByText('Test One');
	});

	it('displays contact roles if provided', () => {
		const {container} = renderAccountAddContacts({
			initialContactRoleKeys: ['KEY-100', 'KEY-101'],
			userEmailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		const {getByText} = within(
			container.querySelector('.input-group-item')
		);

		getByText('Manager');
		getByText('Member');
	});

	it('does not display full name without email', () => {
		const {queryByText} = renderAccountAddContacts({
			userFullName: 'Test One'
		});

		expect(queryByText('name')).toBeFalsy();
		expect(queryByText('Test One')).toBeFalsy();
	});

	it('disables save if email is blank', () => {
		const {getByText} = renderAccountAddContacts({
			initialContactRoleKeys: ['KEY-100', 'KEY-101'],
			userEmailAddress: ''
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('disables save if no contact roles are selected', () => {
		const {getByText} = renderAccountAddContacts({
			initialContactRoleKeys: [],
			userEmailAddress: 'test1@liferay.com'
		});

		expect(getByText('save').disabled).toBeTruthy();
	});

	it('enables save if email and contact roles are populated', () => {
		const {getByText} = renderAccountAddContacts({
			initialContactRoleKeys: ['KEY-100', 'KEY-101'],
			userEmailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		expect(getByText('save').disabled).toBeFalsy();
	});

	it('adds contact roles when selected from dropdown', () => {
		const {container, getByText, getByTitle} = renderAccountAddContacts();

		fireEvent.click(getByTitle('add-roles'));

		fireEvent.click(getByText('Manager'));

		expect(
			within(container.querySelector('.input-group-item')).queryByText(
				'Manager'
			)
		).toBeTruthy();
	});

	it('removes contact roles when clicked on close', () => {
		const {container, queryAllByTitle} = renderAccountAddContacts({
			initialContactRoleKeys: ['KEY-100', 'KEY-101'],
			userEmailAddress: 'test1@liferay.com',
			userFullName: 'Test One'
		});

		fireEvent.click(queryAllByTitle('delete')[0]);

		expect(
			within(container.querySelector('.input-group-item')).queryByText(
				'Manager'
			)
		).toBeFalsy();
	});
});
