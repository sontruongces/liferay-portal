# Admin

Koroniki Admin is accessed via *Control Panel* &rarr; *Koroneiki*.

## Entitlement Definition

An *Entitlement Definition* is the query that defines an *Entitlement*.

For an *Entitlement Definition* to work property, there needs to be existing *Accounts* or *Contacts*.

To create an *Entitlement Definition* navigate to the *Entitlement Definition* section of the Koroneiki Admin.

1. Select either the *Account* or *Contact* tab.
2. Click the *Add* button.
3. Fill out the required fields, for examples of a *definition* see below, and click *Save*.

There are many possibilies of what an *Entitlement Definition* could consist of. Below are a few examples.

#### Partner Account

The following gives an example where an *Account* owns an active Partnership product therefore making them a Partner.

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

The following gives an example where a *Contact* is part of an *Account* which is a Partner.

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

The following gives an example where a *Contact* is part of an *Account* which owns Analytics Cloud, and they also have the Analytics Cloud Admin *Contact Role*.

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

While an *External Link* includes the domain, entity name, and entity ID of a link, you can also create a mapping to a specific URL for specific domains and entities.

Navigate to Koroneiki Admin &rarr; *External Links*.

1. Enter the *Domain* of the *External Link* you would like to create a URL for. Example: `salesforce`
2. Enter the *Entity Name* of the *External Link* you would like the URL to be fore. Example: `account`
3. Enter the URL you would like to map to the *External Links* containing that *Domain* and *Entity Name* listed. The URL must include `https://`. Use the `[$ENTITY_ID$]` where you want the *External Id* to be replaced. 
Example: `https://salesforce.com/[$ENTITY_ID$]`

In the above example, it would take all *External Links* where *Domain* is `salesforce`, *Entity Name* is `account`, and make the URL `https://salesforce.com/12345` if the *Entity ID* was `12345`.