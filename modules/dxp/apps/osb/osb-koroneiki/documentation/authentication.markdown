## Authentication

Once there is a Service Producer created for the system, you will need to create
an Authentication Token to be able to use the Koroneiki API.

### Creating an Authentication Token

Login to [Koroneiki](https://koroneiki.liferay.com) using the Service Producer
login.
1. Navigate to the [Authentication Token Portlet](https://koroneiki.liferay.com/authentication-token).
2. Click on "Add Authentication Token" button
3. Enter a name for the token
4. Copy the token string some place safe (**Once you save you will not be able
   to see the token again**)
5. Click save

Authentication Tokens can also be renamed and deactivated from this portlet via
the 3 dot menu.

Multiple Authentication Tokens can be created for multiple servers, instances,
devs, etc. if needed.

### API Authentication

The Authentication Token will need to be included as a header in the API
request.
- ex. `curl -H "API_Token: xxxxx"`