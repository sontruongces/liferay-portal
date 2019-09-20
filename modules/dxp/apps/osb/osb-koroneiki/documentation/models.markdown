## Models

### Account

An `Account` is a customer entity which can be represented by a business or
personal user. It connects `Contacts` to `Products` they have purchased. An
`Account` can also have parent and child `Accounts` related to it.

### AuditEntry

An `Audit Entry` is a log that records changes to the other Koroneiki models
listed here. This is read only because the `Audit Entries` are created
automatically by Koroneiki.

### Contact

A `Contact` represents a user that can be assigned to an `Account` or `Team`.
`Contacts` can be given `Contact Roles` when assigned to `Accounts` or `Teams`.

### ContactRole

A `Contact Role` is role a `Contact` has when assigned to an `Account` or
`Team`. By default, when a `Contact` is assigned to an `Account` or `Team`, they
get the `Member` role.

### ExternalLink

An `External Link` creates a relationship between Koroneiki models and external
entities. It includes the external domain, entity's name, and entity's id.

Examples:

Domain: `marketplace`
EntityName: `developerEntry`
EntityId: `ABC123`

Domain: `salesforce`
EntityName: `account`
EntityId: `ABC456`

Domain: `training`
EntityName: `course`
EntityId: `dxp-system-admin`

### PostalAddress

A `Postal Address` describes an address for an `Account`. This uses the same
structure as the Liferay address model.

### Product

A `Product` is anything that can be purchased or rendered as a service to a
customer.

A `Product` can only be deleted once all related `Product Consumption` or
`Product Purchase` records are deleted.

### ProductConsumption

A `Product Consumption` is a record of an `Account` consuming a `Product`. It
can contain custom key/value fields.

Example:

There is an `Account` that purchased two DXP licenses. When they use one of them
via a license.xml or maybe DXP Cloud, a `Product Consumption` record should be
added by the system that consumed it.

### ProductPurchase

A `Product Purchase` is an entitlement to the `Product` that was purchased. It
includes details/limitations of the purchase such as `quantity`, `startDate`,
`endDate`, and custom key/value fields.

### Team

A `Team` is a group of `Contacts` within an `Account`. It can be assigned to
other `Accounts` with `Team Roles`.

Example:

A `Team` is created under a partner `Account` and assigned to a customer
`Account` with the First Line Support `Team Role`.

### TeamRole

A `Team Role` is the role a `Team` has when assigned to an `Account`. When a
`Team` is assigned to an `Account`, they will have a `Team Role` of type
`Account`.