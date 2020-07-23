<footer>
	<div class="osb-commerce-section-wrapper">
		<div class="container site-map p-5"></div>
		<div class="container osb-commerce-copyright p-4">
			<div class="align-items-center osb-commerce-copyright-company d-md-inline-flex d-none d-sm-none logo-md">
				<span>
					<img alt="${logo_description}" class="mr-2" height="30" src="${company_logo}" />
					${company_name}
				</span>
			</div>
		</div>
	</div>

	<div class="osb-commerce-section-wrapper">
		<div class="container osb-commerce-copyright-notice p-4">
			<div class="row">
				<div class="col-md-6 d-flex align-items-center">
					<span>
						&copy; ${the_year} ${languageUtil.get(locale, "all-rights-reserved")}
					</span>
				</div>

				<div class="col-md-6 d-flex align-items-center justify-content-end osb-commerce-newsletter">
					<div class="form-group mb-0 d-flex">
						<label class="align-self-center autofit-col mr-3" for="osb-commerce-newsletter-input">
							${languageUtil.get(locale, "stay-up-to-date")}
						</label>
						<div class="input-group">
							<div class="input-group-item input-group-prepend">
								<input
									class="form-control"
									id="osb-commerce-newsletter-input"
									placeholder="${languageUtil.get(locale, "your-email")}"
									type="email"
								/>
							</div>
							<span
								class="align-self-center input-group-append input-group-item input-group-item-shrink"
							>
								<button class="btn btn-secondary" type="button">
									<svg class="lexicon-icon">
										<use href="${themeDisplay.getPathThemeImages()}/lexicon/icons.svg#angle-right"/>
									</svg>
								</button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>