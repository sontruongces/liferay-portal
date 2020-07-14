/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

AUI().ready(() => {
	const IS_OPEN = 'is-open',
		MAX_LEVEL = 5,
		SIGN_IN_BTN = 'sign-in-btn',
		SIGN_IN_WRAPPER = 'sign-in-wrapper';

	function isButton(target) {
		if (target.classList.contains(SIGN_IN_BTN)) {
			return true;
		}

		var parentElement = target.parentElement;

		for (var i = 0; i < MAX_LEVEL; i++) {
			if (parentElement.classList.contains(SIGN_IN_BTN)) {
				return true;
			}

			parentElement = parentElement.parentElement;
		}

		return false;
	}

	function isOpen(element) {
		return element.classList.contains(IS_OPEN);
	}

	function toggleSignIn(e) {
		if (isButton(e.target)) {
			const wrapper = window.document
				.querySelector('.' + SIGN_IN_WRAPPER);

			wrapper.classList.toggle(IS_OPEN, !isOpen(wrapper));
		}
	}

	const signInBtn = window.document.querySelector('.' + SIGN_IN_BTN);

	if (signInBtn) {
		signInBtn.addEventListener('click', toggleSignIn);
	}
});
