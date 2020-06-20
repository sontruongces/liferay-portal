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

package com.liferay.headless.delivery.internal.resource.v1_0;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.kernel.service.DLFileEntryService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureService;
import com.liferay.dynamic.data.mapping.util.DDMBeanTranslator;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.headless.common.spi.resource.SPIRatingResource;
import com.liferay.headless.common.spi.service.context.ServiceContextUtil;
import com.liferay.headless.delivery.dto.v1_0.ContentField;
import com.liferay.headless.delivery.dto.v1_0.CustomField;
import com.liferay.headless.delivery.dto.v1_0.Document;
import com.liferay.headless.delivery.dto.v1_0.DocumentType;
import com.liferay.headless.delivery.dto.v1_0.Rating;
import com.liferay.headless.delivery.internal.dto.v1_0.converter.DocumentDTOConverter;
import com.liferay.headless.delivery.internal.dto.v1_0.util.CustomFieldsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.DDMFormValuesUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.EntityFieldsUtil;
import com.liferay.headless.delivery.internal.dto.v1_0.util.RatingUtil;
import com.liferay.headless.delivery.internal.odata.entity.v1_0.DocumentEntityModel;
import com.liferay.headless.delivery.resource.v1_0.DocumentResource;
import com.liferay.journal.service.JournalArticleService;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.multipart.BinaryFile;
import com.liferay.portal.vulcan.multipart.MultipartBody;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;
import com.liferay.ratings.kernel.service.RatingsEntryLocalService;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/document.properties",
	scope = ServiceScope.PROTOTYPE, service = DocumentResource.class
)
public class DocumentResourceImpl
	extends BaseDocumentResourceImpl implements EntityModelResource {

	@Override
	public void deleteDocument(Long documentId) throws Exception {
		_dlAppService.deleteFileEntry(documentId);
	}

	@Override
	public void deleteDocumentMyRating(Long documentId) throws Exception {
		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		spiRatingResource.deleteRating(documentId);
	}

	@Override
	public Document getDocument(Long documentId) throws Exception {
		return _toDocument(_dlAppService.getFileEntry(documentId));
	}

	@Override
	public Page<Document> getDocumentFolderDocumentsPage(
			Long documentFolderId, Boolean flatten, String search,
			Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		Folder folder = _dlAppService.getFolder(documentFolderId);

		return _getDocumentsPage(
			_getDocumentFolderListActions(folder.getGroupId()),
			booleanQuery -> {
				BooleanFilter booleanFilter =
					booleanQuery.getPreBooleanFilter();

				String field = Field.FOLDER_ID;

				if (GetterUtil.getBoolean(flatten)) {
					field = "treePath";
				}

				booleanFilter.add(
					new TermFilter(field, String.valueOf(documentFolderId)),
					BooleanClauseOccur.MUST);
			},
			search, filter, pagination, sorts);
	}

	public Rating getDocumentMyRating(Long documentId) throws Exception {
		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		return spiRatingResource.getRating(documentId);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return new DocumentEntityModel(
			EntityFieldsUtil.getEntityFields(
				_portal.getClassNameId(DLFileEntry.class.getName()),
				contextCompany.getCompanyId(), _expandoColumnLocalService,
				_expandoTableLocalService));
	}

	@Override
	public Page<Document> getSiteDocumentsPage(
			Long siteId, Boolean flatten, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		return _getDocumentsPage(
			_getSiteListActions(siteId),
			booleanQuery -> {
				BooleanFilter booleanFilter =
					booleanQuery.getPreBooleanFilter();

				if (!GetterUtil.getBoolean(flatten)) {
					booleanFilter.add(
						new TermFilter(
							Field.FOLDER_ID,
							String.valueOf(
								DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)),
						BooleanClauseOccur.MUST);
				}

				if (siteId != null) {
					booleanFilter.add(
						new TermFilter(Field.GROUP_ID, String.valueOf(siteId)),
						BooleanClauseOccur.MUST);
				}
			},
			search, filter, pagination, sorts);
	}

	@Override
	public Document patchDocument(Long documentId, MultipartBody multipartBody)
		throws Exception {

		FileEntry existingFileEntry = _dlAppService.getFileEntry(documentId);

		BinaryFile binaryFile = Optional.ofNullable(
			multipartBody.getBinaryFile("file")
		).orElse(
			new BinaryFile(
				existingFileEntry.getMimeType(),
				existingFileEntry.getFileName(),
				existingFileEntry.getContentStream(),
				existingFileEntry.getSize())
		);

		Optional<Document> documentOptional =
			multipartBody.getValueAsInstanceOptional(
				"document", Document.class);

		return _toDocument(
			_dlAppService.updateFileEntry(
				documentId, binaryFile.getFileName(),
				binaryFile.getContentType(),
				documentOptional.map(
					Document::getTitle
				).orElse(
					existingFileEntry.getTitle()
				),
				documentOptional.map(
					Document::getDescription
				).orElse(
					existingFileEntry.getDescription()
				),
				null, DLVersionNumberIncrease.AUTOMATIC,
				binaryFile.getInputStream(), binaryFile.getSize(),
				_getServiceContext(
					() -> ArrayUtil.toArray(
						_assetCategoryLocalService.getCategoryIds(
							DLFileEntry.class.getName(), documentId)),
					() -> _assetTagLocalService.getTagNames(
						DLFileEntry.class.getName(), documentId),
					existingFileEntry.getFolderId(), documentOptional,
					existingFileEntry.getGroupId())));
	}

	@Override
	public Document postDocumentFolderDocument(
			Long documentFolderId, MultipartBody multipartBody)
		throws Exception {

		Folder folder = _dlAppService.getFolder(documentFolderId);

		return _addDocument(
			folder.getRepositoryId(), documentFolderId, folder.getGroupId(),
			multipartBody);
	}

	public Rating postDocumentMyRating(Long documentId, Rating rating)
		throws Exception {

		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		return spiRatingResource.addOrUpdateRating(
			rating.getRatingValue(), documentId);
	}

	@Override
	public Document postSiteDocument(Long siteId, MultipartBody multipartBody)
		throws Exception {

		return _addDocument(siteId, 0L, siteId, multipartBody);
	}

	@Override
	public Document putDocument(Long documentId, MultipartBody multipartBody)
		throws Exception {

		Optional<Document> documentOptional =
			multipartBody.getValueAsInstanceOptional(
				"document", Document.class);

		if ((multipartBody.getBinaryFile("file") == null) &&
			!documentOptional.isPresent()) {

			throw new BadRequestException("No document or file found in body");
		}

		FileEntry existingFileEntry = _dlAppService.getFileEntry(documentId);

		BinaryFile binaryFile = Optional.ofNullable(
			multipartBody.getBinaryFile("file")
		).orElse(
			new BinaryFile(
				existingFileEntry.getMimeType(),
				existingFileEntry.getFileName(),
				existingFileEntry.getContentStream(),
				existingFileEntry.getSize())
		);

		return _toDocument(
			_dlAppService.updateFileEntry(
				documentId, binaryFile.getFileName(),
				binaryFile.getContentType(),
				documentOptional.map(
					Document::getTitle
				).orElse(
					existingFileEntry.getTitle()
				),
				documentOptional.map(
					Document::getDescription
				).orElse(
					null
				),
				null, DLVersionNumberIncrease.AUTOMATIC,
				binaryFile.getInputStream(), binaryFile.getSize(),
				_getServiceContext(
					() -> new Long[0], () -> new String[0],
					existingFileEntry.getFolderId(), documentOptional,
					existingFileEntry.getGroupId())));
	}

	@Override
	public Rating putDocumentMyRating(Long documentId, Rating rating)
		throws Exception {

		SPIRatingResource<Rating> spiRatingResource = _getSPIRatingResource();

		return spiRatingResource.addOrUpdateRating(
			rating.getRatingValue(), documentId);
	}

	private Document _addDocument(
			Long repositoryId, long documentFolderId, Long groupId,
			MultipartBody multipartBody)
		throws Exception {

		BinaryFile binaryFile = multipartBody.getBinaryFile("file");

		if (binaryFile == null) {
			throw new BadRequestException("No file found in body");
		}

		Optional<Document> documentOptional =
			multipartBody.getValueAsInstanceOptional(
				"document", Document.class);

		return _toDocument(
			_dlAppService.addFileEntry(
				repositoryId, documentFolderId, binaryFile.getFileName(),
				binaryFile.getContentType(),
				documentOptional.map(
					Document::getTitle
				).orElse(
					binaryFile.getFileName()
				),
				documentOptional.map(
					Document::getDescription
				).orElse(
					null
				),
				null, binaryFile.getInputStream(), binaryFile.getSize(),
				_getServiceContext(
					() -> new Long[0], () -> new String[0], documentFolderId,
					documentOptional, groupId)));
	}

	private Optional<DLFileEntryType> _getDLFileEntryTypeOptional(
		long documentFolderId, Optional<Document> documentOptional,
		Long groupId) {

		return documentOptional.map(
			Document::getDocumentType
		).map(
			DocumentType::getName
		).map(
			name -> {
				try {
					for (DLFileEntryType dlFileEntryType :
							_dlFileEntryTypeLocalService.
								getFolderFileEntryTypes(
									new long[] {groupId}, documentFolderId,
									true)) {

						if (name.equals(
								dlFileEntryType.getName(
									contextAcceptLanguage.
										getPreferredLocale()))) {

							return dlFileEntryType;
						}
					}
				}
				catch (Exception exception) {
					if (_log.isDebugEnabled()) {
						_log.debug(exception, exception);
					}
				}

				return null;
			}
		);
	}

	private Map<String, Map<String, String>> _getActions(FileEntry fileEntry) {
		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"DELETE", fileEntry.getPrimaryKey(), "deleteDocument",
				fileEntry.getUserId(),
				"com.liferay.document.library.kernel.model.DLFileEntry",
				fileEntry.getGroupId())
		).put(
			"get",
			addAction(
				"VIEW", fileEntry.getPrimaryKey(), "getDocument",
				fileEntry.getUserId(),
				"com.liferay.document.library.kernel.model.DLFileEntry",
				fileEntry.getGroupId())
		).put(
			"replace",
			addAction(
				"UPDATE", fileEntry.getPrimaryKey(), "putDocument",
				fileEntry.getUserId(),
				"com.liferay.document.library.kernel.model.DLFileEntry",
				fileEntry.getGroupId())
		).put(
			"update",
			addAction(
				"UPDATE", fileEntry.getPrimaryKey(), "patchDocument",
				fileEntry.getUserId(),
				"com.liferay.document.library.kernel.model.DLFileEntry",
				fileEntry.getGroupId())
		).build();
	}

	private Map<String, Map<String, String>> _getDocumentFolderListActions(
		Long groupId) {

		return HashMapBuilder.<String, Map<String, String>>put(
			"create",
			addAction(
				"ADD_DOCUMENT", "postDocumentFolderDocument",
				"com.liferay.document.library", groupId)
		).put(
			"get",
			addAction(
				"VIEW", "getDocumentFolderDocumentsPage",
				"com.liferay.document.library", groupId)
		).build();
	}

	private Page<Document> _getDocumentsPage(
			Map<String, Map<String, String>> actions,
			UnsafeConsumer<BooleanQuery, Exception> booleanQueryUnsafeConsumer,
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			actions, booleanQueryUnsafeConsumer, filter, DLFileEntry.class,
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> searchContext.setCompanyId(
				contextCompany.getCompanyId()),
			sorts,
			document -> _toDocument(
				_dlAppService.getFileEntry(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))));
	}

	private CustomField[] _getExpandoBridgeAttributes(
		Optional<Document> documentOptional) {

		return documentOptional.map(
			Document::getCustomFields
		).orElse(
			null
		);
	}

	private Map<String, Serializable> _getExpandoBridgeAttributes1(
		Optional<Document> documentOptional) {

		return CustomFieldsUtil.toMap(
			DLFileEntry.class.getName(), contextCompany.getCompanyId(),
			_getExpandoBridgeAttributes(documentOptional),
			contextAcceptLanguage.getPreferredLocale());
	}

	private Map<String, Map<String, String>> _getSiteListActions(Long siteId) {
		return HashMapBuilder.<String, Map<String, String>>put(
			"create",
			addAction(
				"ADD_DOCUMENT", "postSiteDocument",
				"com.liferay.document.library", siteId)
		).put(
			"get",
			addAction(
				"VIEW", "getSiteDocumentsPage", "com.liferay.document.library",
				siteId)
		).build();
	}

	private ServiceContext _getServiceContext(
			Supplier<Long[]> defaultCategoriesSupplier,
			Supplier<String[]> defaultKeywordsSupplier, Long documentFolderId,
			Optional<Document> documentOptional, Long groupId)
		throws Exception {

		ServiceContext serviceContext = ServiceContextUtil.createServiceContext(
			documentOptional.map(
				Document::getTaxonomyCategoryIds
			).orElseGet(
				defaultCategoriesSupplier
			),
			documentOptional.map(
				Document::getKeywords
			).orElseGet(
				defaultKeywordsSupplier
			),
			_getExpandoBridgeAttributes1(documentOptional), groupId,
			documentOptional.map(
				Document::getViewableByAsString
			).orElse(
				Document.ViewableBy.OWNER.getValue()
			));

		serviceContext.setUserId(contextUser.getUserId());

		Optional<DLFileEntryType> dlFileEntryTypeOptional =
			_getDLFileEntryTypeOptional(
				documentFolderId, documentOptional, groupId);

		if (dlFileEntryTypeOptional.isPresent()) {
			DLFileEntryType dlFileEntryType = dlFileEntryTypeOptional.get();

			serviceContext.setAttribute(
				"fileEntryTypeId", dlFileEntryType.getFileEntryTypeId());

			Document document = documentOptional.get();

			List<DDMStructure> ddmStructures =
				dlFileEntryType.getDDMStructures();

			DocumentType documentType = document.getDocumentType();

			ContentField[] contentFields = documentType.getContentFields();

			for (DDMStructure ddmStructure : ddmStructures) {
				com.liferay.dynamic.data.mapping.model.DDMStructure
					modelDDMStructure = _ddmStructureService.getStructure(
						ddmStructure.getStructureId());

				com.liferay.dynamic.data.mapping.storage.DDMFormValues
					ddmFormValues = DDMFormValuesUtil.toDDMFormValues(
						contentFields, modelDDMStructure.getDDMForm(),
						_dlAppService, groupId, _journalArticleService,
						_layoutLocalService,
						contextAcceptLanguage.getPreferredLocale(),
						transform(
							ddmStructure.getRootFieldNames(),
							modelDDMStructure::getDDMFormField));

				serviceContext.setAttribute(
					DDMFormValues.class.getName() + StringPool.POUND +
						ddmStructure.getStructureId(),
					_ddmBeanTranslator.translate(ddmFormValues));
			}
		}

		return serviceContext;
	}

	private SPIRatingResource<Rating> _getSPIRatingResource() {
		return new SPIRatingResource<>(
			DLFileEntry.class.getName(), _ratingsEntryLocalService,
			ratingsEntry -> RatingUtil.toRating(
				_portal, ratingsEntry, _userLocalService),
			contextUser);
	}

	private Document _toDocument(FileEntry fileEntry) throws Exception {
		return _documentDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(fileEntry), _dtoConverterRegistry,
				fileEntry.getFileEntryId(), null, contextUriInfo, contextUser));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DocumentResourceImpl.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private AssetTagLocalService _assetTagLocalService;

	@Reference
	private DDMBeanTranslator _ddmBeanTranslator;

	@Reference
	private DDMStructureService _ddmStructureService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLFileEntryService _dlFileEntryService;

	@Reference
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

	@Reference
	private DocumentDTOConverter _documentDTOConverter;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private JournalArticleService _journalArticleService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RatingsEntryLocalService _ratingsEntryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}