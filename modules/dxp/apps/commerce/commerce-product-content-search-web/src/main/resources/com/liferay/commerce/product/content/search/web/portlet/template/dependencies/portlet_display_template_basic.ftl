<#assign count = 0 />

<#if entries?has_content>
	<div class="row">
		<#list entries as curDocument>
			<#assign image = cpSearchResultsDisplayContext.getProductDefaultImage(curDocument, themeDisplay) />

			<#assign friendlyURL = cpSearchResultsDisplayContext.getProductFriendlyURL(themeDisplay.getPortalURL(), curDocument) />

			<#assign title = cpSearchResultsDisplayContext.getTitle(curDocument) />

			<div class="col-md-4">
				<div>
					<img src="${image}">
				</div>

				<div>
					<a href="${friendlyURL}">
						<strong>${title}</strong>
					</a>
				</div>
			</div>

			<#assign count = count + 1 />

			<#if count gte 3>
				</div>
				<div class="row">
			</#if>
		</#list>
	</div>
</#if>