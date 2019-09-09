# Koroneiki API

Koroneiki uses Liferay's [Headless REST APIs](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis). 

## Using Koroneiki API
The URL includes 3 things
- headless api (`koroneiki-rest`)
- version (ex. `v1.0`)
- schema (ex. `accounts`)

For example, the URL for accessing accounts would be `https://koroneiki.liferay.com/o/koroneiki-rest/v1.0/accounts`.

## Open API profile
The OpenAPI profile can be found under this schema `https://koroneiki.liferay.com/o/koroneiki-rest/[version]/openapi.yaml`.

Each model, field, API path, and parameter can be found in the OpenAPI profile.

### Required fields
Required fields for creation or updating are listed under `required` for each model.

### Enums
The fields with multiple string values have an `enum` which list each possible string value.

### Paths
All of the APIs that are available and their URLs are listed under `paths`.
- APIs that could return a large number of results are paginated to reduce response size.

## Search and Filters
Some of the APIs content can be [filtered, sorted, and searched](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/filter-sort-and-search).

### Filters
The collections that support filtering will contain the optional parameter `filter` in the OpenAPI profile. Below are the search filters and sorts that are currently supported.

**Search filters and sorts**

Account
- `code`
- `contactOktaIdContactRoleKeys`
- `contactOktaIds`
- `contactUuidContactRoleKeys`
- `contactUuids`
- `externalLinkDomains`
- `externalLinkEntityIds`
- `externalLinkEntityNames`
- `name`
- `status`
- `parentAccountKey`
- `productEntryKeys`

Contact
- `emailAddress`
- `externalLinkDomains`
- `externalLinkEntityIds`
- `externalLinkEntityNames`
- `firstName`
- `lastName`
- `middleName`

ContactRole
- `description`
- `name`
- `system`
- `type`

Team
- `accountKey`
- `externalLinkDomains`
- `externalLinkEntityIds`
- `externalLinkEntityNames`
- `name`

These search filters and sorts can be combined to filter out the unneeded information.

One example might be to only find the accounts that a specific contact is a part of, that has purchased a specific product, and the results should be sorted by name.

```
curl -H "API_Token: xxx" "http://koroneiki.liferay.com/o/koroneiki-rest/v1.0/accounts?filter=contactUuids/any(k:k%20eq%20'KOR-12345')%20and%20productEntryKeys/any(k:k%20eq%20'KOR-23456'%20or%20k%20eq%20'KOR-34567')&sort=name:asc"
```