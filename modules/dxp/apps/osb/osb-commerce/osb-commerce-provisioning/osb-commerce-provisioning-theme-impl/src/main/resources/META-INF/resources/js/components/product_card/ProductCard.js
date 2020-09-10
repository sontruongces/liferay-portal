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

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import classnames from 'classnames';
import {navigate} from 'frontend-js-web';
import React, {createRef, useEffect, useState} from 'react';

const PRODUCT_HIGHLIGHT = 'highlightProduct';

function ProductCard({
	detailURL,
	isFeatured,
	isTrial,
	name,
	namespace,
	productImageURL,
	skuId,
	spritemap
}) {
	const [isHighlighted, setIsHighlighted] = useState(isFeatured),
		cardRef = createRef();

	useEffect(() => {
		Liferay.on(`${namespace}_${PRODUCT_HIGHLIGHT}`, ({id}) =>
			!id ? setIsHighlighted(isFeatured) : setIsHighlighted(id === skuId)
		);
	}, [isFeatured, namespace, skuId]);

	useEffect(() => {
		const productCard = cardRef.current,
			onHover = () =>
				Liferay.fire(`${namespace}_${PRODUCT_HIGHLIGHT}`, {id: skuId}),
			onOut = () =>
				Liferay.fire(`${namespace}_${PRODUCT_HIGHLIGHT}`, {id: ''});

		productCard.addEventListener('mouseover', onHover);
		productCard.addEventListener('mouseout', onOut);
	}, [cardRef, namespace, skuId]);

	return (
		<div
			className={classnames(
				'card',
				'osb-commerce-product-card',
				'd-flex flex-column justify-content-between text-center',
				isHighlighted && 'is-highlighted'
			)}
			ref={cardRef}
		>
			<div className={'product-card-header'}>
				<div className={'product-image'}>
					<img alt={name} src={productImageURL} />
				</div>

				<div className={'product-name'}>
					<h1>{name}</h1>
				</div>

				<div className={'product-description'}>
					<p>
						number <big>1</big> <b>feature</b>
					</p>
				</div>
			</div>

			<div className={'product-features'}>
				<ul>
					<li>
						<div className={'list-icon'}>
							<ClayIcon spritemap={spritemap} symbol={'check'} />
						</div>
						<div className={'feature'}>
							<span className={'text-truncate'}>
								This is an amazing feature
							</span>
						</div>
					</li>
					<li>
						<div className={'list-icon'}>
							<ClayIcon spritemap={spritemap} symbol={'check'} />
						</div>
						<div className={'feature'}>
							<span className={'text-truncate'}>
								This is an amazing feature with a super long
								text
							</span>
						</div>
					</li>
					<li>
						<div className={'list-icon'}>
							<ClayIcon spritemap={spritemap} symbol={'check'} />
						</div>
						<div className={'feature'}>
							<span className={'text-truncate'}>
								This is yet another cool feature
							</span>
						</div>
					</li>
					<li>
						<div className={'list-icon'}>
							<ClayIcon spritemap={spritemap} symbol={'check'} />
						</div>
						<div className={'feature'}>
							<span className={'text-truncate'}>
								And much more. No, really. I mean it.
							</span>
						</div>
					</li>
				</ul>
			</div>

			<div className={'product-card-actions'}>
				<div>
					<ClayButton
						displayType={
							isFeatured || isHighlighted
								? 'primary'
								: 'secondary'
						}
					>
						{Liferay.Language.get(
							isTrial ? 'start-trial' : 'subscribe'
						)}
					</ClayButton>
				</div>

				<div>
					<ClayButton
						displayType={'link'}
						onClick={() => {
							navigate(detailURL);
						}}
						type={'button'}
					>
						{Liferay.Language.get('learn-more')}
					</ClayButton>
				</div>
			</div>
		</div>
	);
}

export default ProductCard;
