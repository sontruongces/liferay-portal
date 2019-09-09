# Models

### Account
A business or personal account which connects contacts to products they own. 

An account can also have parent and child accounts related to it.

### AuditEntry
An audit log that records changes to objects.

This is read only, since the audits are created automatically by Koroneiki.

### Contact
A contact that is connected to an account or team.

Contacts can be assigned to different Accounts and Teams, and given custom `contactRoles`.

### ContactRole
The role a contact has when connected to an account or a team.

By default, when a contact is added to an account or team, they get the `member` role.

### ExternalLink
Creates a link to an external entity.

Includes the domain, the external entity's name, and the external entity's id.

Example:

Domain: `salesforce`
EntityName: `salesforceAccountKey`
EntityId: `123456789`

### PostalAddress
The postal address for an Account.

This uses the same structure as the Liferay address model.

### Product
The name of a Product, which can be purchased by an Account.

### ProductConsumption
A product consumption is when an account consumes a specific item.

An example of this would be if an account has two licenses, when they use one of them, that product was consumed.

### ProductPurchase
The details of the product purchase. 

Includes details of the product purchase such as `quantity`, `startDate`, `endDate`, and also custom fields.

### Team
A team linked to an Account.

A team includes contacts that are assigned to an Account for a specific purpose.

An example of this would be a support team assigned to a specific account, so another system will know which contacts are working on which account.

### TeamRole
The role that is assigned to a team.

When a team is assigned to an account, they will have a team role of `type` `account`. Other `regular` roles can be assigned to teams as well.