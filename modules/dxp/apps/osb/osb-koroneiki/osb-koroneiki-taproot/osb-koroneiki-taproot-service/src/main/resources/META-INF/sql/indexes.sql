create index IX_946B1DD7 on Koroneiki_Account (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_875E5602 on Koroneiki_Contact (emailAddress[$COLUMN_LENGTH:75$]);
create index IX_23B9404A on Koroneiki_Contact (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_5C65FBE2 on Koroneiki_ContactAccountRole (accountId, contactRoleId);
create index IX_7C3346A8 on Koroneiki_ContactAccountRole (contactRoleId);

create index IX_8536913C on Koroneiki_ContactProjectRole (contactRoleId);
create index IX_A0D1BE9F on Koroneiki_ContactProjectRole (projectId);

create index IX_689B34B6 on Koroneiki_ContactRole (name[$COLUMN_LENGTH:75$], type_, system_);
create index IX_3DBAA169 on Koroneiki_ContactRole (type_);
create index IX_856F85E0 on Koroneiki_ContactRole (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_CCCB218 on Koroneiki_ContactTeamRole (contactRoleId);
create index IX_216B6075 on Koroneiki_ContactTeamRole (teamId);

create index IX_478EC5E9 on Koroneiki_Project (accountId);
create index IX_E74A30C3 on Koroneiki_Project (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_DC3C7305 on Koroneiki_Team (accountId);
create index IX_AD200E72 on Koroneiki_Team (name[$COLUMN_LENGTH:75$]);
create index IX_ADF0CC27 on Koroneiki_Team (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_EA7613FC on Koroneiki_TeamProjectRole (projectId);
create index IX_D2BDFAA on Koroneiki_TeamProjectRole (teamRoleId);

create index IX_37F7F24D on Koroneiki_TeamRole (name[$COLUMN_LENGTH:75$], type_);
create index IX_22D6042C on Koroneiki_TeamRole (type_);
create index IX_CF13DB3D on Koroneiki_TeamRole (uuid_[$COLUMN_LENGTH:75$], companyId);