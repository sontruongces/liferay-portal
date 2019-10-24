create table Koroneiki_Entitlement (
	entitlementId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	entitlementDefinitionId LONG,
	classNameId LONG,
	classPK LONG,
	name VARCHAR(75) null
);

create table Koroneiki_EntitlementDefinition (
	uuid_ VARCHAR(75) null,
	entitlementDefinitionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	definition STRING null,
	status INTEGER
);