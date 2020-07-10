create index IX_7B878084 on Provisioning_ProductBundle (name[$COLUMN_LENGTH:75$]);
create index IX_37F3B339 on Provisioning_ProductBundle (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_77F68DED on Provisioning_ProductBundleProducts (productKey[$COLUMN_LENGTH:75$]);