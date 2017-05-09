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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CPOption service. Represents a row in the &quot;CPOption&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CPOptionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CPOptionImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPOption
 * @see com.liferay.commerce.product.model.impl.CPOptionImpl
 * @see com.liferay.commerce.product.model.impl.CPOptionModelImpl
 * @generated
 */
@ProviderType
public interface CPOptionModel extends BaseModel<CPOption>, GroupedModel,
	LocalizedModel, ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a cp option model instance should use the {@link CPOption} interface instead.
	 */

	/**
	 * Returns the primary key of this cp option.
	 *
	 * @return the primary key of this cp option
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this cp option.
	 *
	 * @param primaryKey the primary key of this cp option
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this cp option.
	 *
	 * @return the uuid of this cp option
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this cp option.
	 *
	 * @param uuid the uuid of this cp option
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the cp option ID of this cp option.
	 *
	 * @return the cp option ID of this cp option
	 */
	public long getCPOptionId();

	/**
	 * Sets the cp option ID of this cp option.
	 *
	 * @param CPOptionId the cp option ID of this cp option
	 */
	public void setCPOptionId(long CPOptionId);

	/**
	 * Returns the group ID of this cp option.
	 *
	 * @return the group ID of this cp option
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this cp option.
	 *
	 * @param groupId the group ID of this cp option
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this cp option.
	 *
	 * @return the company ID of this cp option
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this cp option.
	 *
	 * @param companyId the company ID of this cp option
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this cp option.
	 *
	 * @return the user ID of this cp option
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this cp option.
	 *
	 * @param userId the user ID of this cp option
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this cp option.
	 *
	 * @return the user uuid of this cp option
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this cp option.
	 *
	 * @param userUuid the user uuid of this cp option
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this cp option.
	 *
	 * @return the user name of this cp option
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this cp option.
	 *
	 * @param userName the user name of this cp option
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this cp option.
	 *
	 * @return the create date of this cp option
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this cp option.
	 *
	 * @param createDate the create date of this cp option
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this cp option.
	 *
	 * @return the modified date of this cp option
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this cp option.
	 *
	 * @param modifiedDate the modified date of this cp option
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this cp option.
	 *
	 * @return the title of this cp option
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this cp option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this cp option
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this cp option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this cp option. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this cp option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this cp option
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this cp option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this cp option
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this cp option.
	 *
	 * @return the locales and localized titles of this cp option
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this cp option.
	 *
	 * @param title the title of this cp option
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this cp option in the language.
	 *
	 * @param title the localized title of this cp option
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this cp option in the language, and sets the default locale.
	 *
	 * @param title the localized title of this cp option
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this cp option from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this cp option
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this cp option from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this cp option
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this cp option.
	 *
	 * @return the description of this cp option
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this cp option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this cp option
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this cp option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this cp option. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this cp option in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this cp option
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this cp option in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this cp option
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this cp option.
	 *
	 * @return the locales and localized descriptions of this cp option
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this cp option.
	 *
	 * @param description the description of this cp option
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this cp option in the language.
	 *
	 * @param description the localized description of this cp option
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this cp option in the language, and sets the default locale.
	 *
	 * @param description the localized description of this cp option
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this cp option from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this cp option
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this cp option from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this cp option
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the ddm form field type name of this cp option.
	 *
	 * @return the ddm form field type name of this cp option
	 */
	@AutoEscape
	public String getDDMFormFieldTypeName();

	/**
	 * Sets the ddm form field type name of this cp option.
	 *
	 * @param DDMFormFieldTypeName the ddm form field type name of this cp option
	 */
	public void setDDMFormFieldTypeName(String DDMFormFieldTypeName);

	/**
	 * Returns the name of this cp option.
	 *
	 * @return the name of this cp option
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this cp option.
	 *
	 * @param name the name of this cp option
	 */
	public void setName(String name);

	/**
	 * Returns the facetable of this cp option.
	 *
	 * @return the facetable of this cp option
	 */
	public boolean getFacetable();

	/**
	 * Returns <code>true</code> if this cp option is facetable.
	 *
	 * @return <code>true</code> if this cp option is facetable; <code>false</code> otherwise
	 */
	public boolean isFacetable();

	/**
	 * Sets whether this cp option is facetable.
	 *
	 * @param facetable the facetable of this cp option
	 */
	public void setFacetable(boolean facetable);

	/**
	 * Returns the required of this cp option.
	 *
	 * @return the required of this cp option
	 */
	public boolean getRequired();

	/**
	 * Returns <code>true</code> if this cp option is required.
	 *
	 * @return <code>true</code> if this cp option is required; <code>false</code> otherwise
	 */
	public boolean isRequired();

	/**
	 * Sets whether this cp option is required.
	 *
	 * @param required the required of this cp option
	 */
	public void setRequired(boolean required);

	/**
	 * Returns the sku contributor of this cp option.
	 *
	 * @return the sku contributor of this cp option
	 */
	public boolean getSkuContributor();

	/**
	 * Returns <code>true</code> if this cp option is sku contributor.
	 *
	 * @return <code>true</code> if this cp option is sku contributor; <code>false</code> otherwise
	 */
	public boolean isSkuContributor();

	/**
	 * Sets whether this cp option is sku contributor.
	 *
	 * @param skuContributor the sku contributor of this cp option
	 */
	public void setSkuContributor(boolean skuContributor);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(CPOption cpOption);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CPOption> toCacheModel();

	@Override
	public CPOption toEscapedModel();

	@Override
	public CPOption toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}