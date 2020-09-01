<#if !trial_started>
	<#assign
		trial_navigation_preferences = freeMarkerPortletPreferences.getPreferences({
			"portletSetupPortletDecoratorId": "barebone",
			"displayStyle": "ddmTemplate_NAV-PILLS-FTL"
		})
	/>

	<button aria-controls="navigationCollapse" aria-expanded="false" aria-label="Toggle navigation" class="d-md-none navbar-toggler navbar-toggler-right" data-target="#navigationCollapse" data-toggle="collapse" type="button">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div aria-expanded="false" class="collapse navbar-collapse" id="navigationCollapse">
		<@liferay.navigation_menu default_preferences="${trial_navigation_preferences}" />
	</div>
</#if>