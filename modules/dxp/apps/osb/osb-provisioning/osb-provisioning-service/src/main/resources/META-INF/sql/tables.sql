create table Provisioning_ProductBundle (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	productBundleId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);

create table Provisioning_ProductBundleProducts (
	mvccVersion LONG default 0 not null,
	productBundleId LONG not null,
	productKey VARCHAR(75) not null,
	primary key (productBundleId, productKey)
);