# Admin

Koroneiki Admin is accessed via *Control Panel* &rarr; *Koroneiki*.

## Entitlement Definition

Navigate to the `Entitlement Definition` section of the Koroneiki Admin.

The definition field is an SQL query that returns `Account` or `Contact` IDs of the `Account` or `Contact` which is granted that `Entitlement`. Below are a few definition examples.

#### Partner Account

The following is an example where an `Account` owns an active Partnership `Product Entry`.

```
SELECT
	DISTINCT(Koroneiki_Account.accountId)
FROM
	Koroneiki_Account
INNER JOIN
	Koroneiki_ProductPurchase ON
		Koroneiki_ProductPurchase.accountId = Koroneiki_Account.accountId
WHERE
	(
		(Koroneiki_ProductPurchase.startDate IS NULL) OR
		(
			(Koroneiki_ProductPurchase.startDate <= '[$NOW$]') AND
			(Koroneiki_ProductPurchase.endDate >= '[$NOW$]')
		)
	) AND
	(Koroneiki_ProductPurchase.productEntryId in (39003, 39014))
```

#### Partner Contact

The following is an example where a `Contact` is assigned to a Partner `Account`.

```
SELECT
	DISTINCT(Koroneiki_Contact.contactId)
FROM
	Koroneiki_Contact
INNER JOIN
	Koroneiki_ContactAccountRole ON
		Koroneiki_ContactAccountRole.contactId = Koroneiki_Contact.contactId
INNER JOIN
	Koroneiki_Account ON
		Koroneiki_Account.accountId = Koroneiki_ContactAccountRole.accountId
INNER JOIN
	Koroneiki_Entitlement ON
		Koroneiki_Entitlement.classPK = Koroneiki_Account.accountId
WHERE
	(Koroneiki_Entitlement.entitlementDefinitionId = 39025) AND
	(Koroneiki_Account.status = 0)
```

#### Analytics Cloud Admin

The following is an example where a `Contact` is assigned the Analytics Cloud Admin `Contact Role` to an `Account` which owns the Analytics Cloud `Product Entry`.

```
SELECT
	DISTINCT(Koroneiki_Contact.contactId)
FROM
	Koroneiki_Contact
INNER JOIN
	Koroneiki_ContactAccountRole ON
		Koroneiki_ContactAccountRole.contactId = Koroneiki_Contact.contactId
INNER JOIN
	Koroneiki_ProductPurchase ON
		Koroneiki_ProductPurchase.accountId = Koroneiki_ContactAccountRole.accountId
WHERE
	(Koroneiki_ContactAccountRole.contactRoleId = 39720) AND
	(
		(Koroneiki_ProductPurchase.startDate IS NULL) OR
		(
			(Koroneiki_ProductPurchase.startDate < '[$NOW$]') AND
			(Koroneiki_ProductPurchase.endDate >= '[$NOW$]')
		)
	) AND
	(Koroneiki_ProductPurchase.productEntryId = 39756)
```

## External Link Mappings

An `External Link Mapping` is a URL for specific `External Link` domains and entity names.

Navigate to Koroneiki Admin &rarr; *External Links*.

1. Enter the *Domain* of the `External Link` the URL is for. Example: `salesforce`
2. Enter the *Entity Name* of the `External Link` the URL is for. Example: `account`
3. Enter the *URL* the *External Links* containing the above *Domain* and *Entity Name* will use. The URL must include `https://`. Use `[$ENTITY_ID$]` where you want the `External Link's` entity ID to be inserted.
Example: `https://salesforce.com/account/[$ENTITY_ID$]`

In the above example, an `External Link` with the *Domain* `salesforce`, *Entity Name* `account`, and *Entity ID* `12345` will have the URL `https://salesforce.com/account/12345`.