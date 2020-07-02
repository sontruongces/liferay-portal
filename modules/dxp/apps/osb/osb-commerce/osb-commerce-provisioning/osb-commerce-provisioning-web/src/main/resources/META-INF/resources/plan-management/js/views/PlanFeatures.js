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

import PropTypes from 'prop-types';
import ClayPanel from '@clayui/panel';
import {ClayToggle} from '@clayui/form';
import React from 'react';
import {liferayNavigate} from '../../../js/utilities/helpers';

function Feature({
	description,
	enabled,
	name,
	spritemap,
	toggleURL
}) {
	return (
		<ClayPanel
			collapsable
			displayTitle={
				<div className={'row py-3'}>
					<div className={'col-10 d-flex align-items-center'}>
						<h4 className={'m-0'}>{name}</h4>
					</div>
					<div className={'align-items-center justify-content-center col-2 d-flex'}>
						<ClayToggle
							onToggle={() => {
								liferayNavigate(toggleURL);
							}}
							toggled={enabled}
						/>
					</div>
				</div>
			}
			displayType={'secondary'}
			showCollapseIcon={true}
			spritemap={spritemap}
		>
			<ClayPanel.Body>
				<p>{description}</p>
			</ClayPanel.Body>
		</ClayPanel>
	);
}

function PlanFeatures({
	activeFeatures,
	inactiveFeatures,
	spritemap
}) {
	return (
		<div className={'col-12 mt-4 osb-commerce-plan-features'}>
			{activeFeatures.length > 0 && (
				 <div className={'features-active mt-4'}>
					 <h3 className={'text-capitalize mb-4'}>
						 {Liferay.Language.get('active-features')}
					 </h3>

					 <div className={'feature-set'}>
						 {activeFeatures.map(feature => (
							 <Feature
								 enabled={true}
								 key={feature.id}
								 spritemap={spritemap}
								 {...feature}
							 />
						 ))}
					 </div>
				 </div>
			)}

			{inactiveFeatures.length > 0 && (
				<div className={'features-inactive mt-4'}>
					<h3 className={'text-capitalize mb-4'}>
						{Liferay.Language.get('add-more-features')}
					</h3>

					<div className={'feature-set'}>
						{inactiveFeatures.map(feature => (
							<Feature
								enabled={false}
								key={feature.id}
								spritemap={spritemap}
								{...feature}
							/>
						))}
					</div>
				</div>
			)}
		</div>
	);
}

PlanFeatures.propTypes = {
	activeFeatures: PropTypes.arrayOf(
		PropTypes.shape({
			description: PropTypes.string,
			id: PropTypes.number,
			enabled: PropTypes.bool,
			name: PropTypes.string,
			toggleURL: PropTypes.string
		})
	),
	inactiveFeatures: PropTypes.arrayOf(
		PropTypes.shape({
			description: PropTypes.string,
			id: PropTypes.number,
			enabled: PropTypes.bool,
			title: PropTypes.string,
			toggleURL: PropTypes.string
		})
	),
	spritemap: PropTypes.string

};

export default PlanFeatures;