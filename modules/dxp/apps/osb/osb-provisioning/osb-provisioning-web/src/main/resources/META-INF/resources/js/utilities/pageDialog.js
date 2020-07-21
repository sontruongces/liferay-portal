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

// Blackbox the AUI dependency Liferay Item Selector Dialog

function pageDialog({formField, formName, title, url}) {
	const A = AUI();

	if (A) {
		A.use('liferay-item-selector-dialog', A => {
			const itemSelectorDialog = new A.LiferayItemSelectorDialog({
				eventName: 'selectedItemChange',
				on: {
					selectedItemChange: event => {
						const selectedItems = event.newVal;

						if (selectedItems) {
							Liferay.Util.postForm(document[formName], {
								data: {[formField]: selectedItems}
							});
						}
					}
				},
				title,
				url
			});

			itemSelectorDialog.open();
		});
	}
}

export default pageDialog;
