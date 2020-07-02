const planManagementMock = {
	activePlan: {
		cancelPlanURL: 'http://someUrl.com',
		currency: '$',
		endDate: new Date().toDateString(),
		planName: 'Liferay Commerce Basic',
		planPrice: '20.000',
		recurrence: 'year',
		startDate: new Date().toDateString(),
		switchBillingURL: 'http://someUrl.com'
	},

	planFeatures: {
		activeFeatures: [
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
		],
		inactiveFeatures: [
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
			{
				description: 'This is an amazing description of the above Cool Feature',
				id: `feature-${Math.floor(Math.random() * Math.floor(1000))}`,
				name: 'Some Cool Feature',
				toggleURL: 'http://someToggleURL.com'
			},
		]
	},

	spritemap: ''
};

export default planManagementMock;