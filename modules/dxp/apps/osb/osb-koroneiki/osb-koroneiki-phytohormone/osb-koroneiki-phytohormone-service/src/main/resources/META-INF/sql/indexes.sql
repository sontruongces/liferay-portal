create index IX_7261CFB8 on Koroneiki_Entitlement (classNameId, classPK);
create index IX_E47A17C8 on Koroneiki_Entitlement (entitlementDefinitionId);

create index IX_7D1EA8B7 on Koroneiki_EntitlementDefinition (classNameId, name[$COLUMN_LENGTH:75$]);
create index IX_911039DE on Koroneiki_EntitlementDefinition (classNameId, status);
create unique index IX_AEA5C5A9 on Koroneiki_EntitlementDefinition (entitlementDefinitionKey[$COLUMN_LENGTH:75$]);
create index IX_A5F6828A on Koroneiki_EntitlementDefinition (uuid_[$COLUMN_LENGTH:75$], companyId);