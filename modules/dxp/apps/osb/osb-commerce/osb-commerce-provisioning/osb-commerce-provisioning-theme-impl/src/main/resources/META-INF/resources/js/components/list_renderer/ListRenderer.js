import React from 'react';
import ProductCard from '../product_card/ProductCard';

function ListRenderer({
	CPEntries,
	portletNamespace
}) {
	return (
		<div className={'align-items-center d-flex'}>
			{CPEntries.map((entry, index) => (
				<div
					className={
						'col-sm-12 col-md-4 osb-commerce-product-card-container'
					}
				>
					<ProductCard
						key={index}
						isFeatured={index === 1}
						isTrial={index === 0}
						namespace={portletNamespace}
						{...entry}
					/>
				</div>
			))}
		</div>
	)
}

export default ListRenderer;