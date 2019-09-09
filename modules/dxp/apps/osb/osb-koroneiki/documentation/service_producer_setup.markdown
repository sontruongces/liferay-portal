## Service Producer Setup
First there needs to be a Service Producer setup for the system that will use Koroneiki. If you do not already have a Service Producer for your system, please create a "LHC" jira ticket to request one be created.

An admin will create a Service Producer for the needed system. Creating a Service Producer will do a few things:
- Create a Liferay user (email: `service_producer.#####@liferay.com`)
- Create a Liferay role (title: `{Service Producer Name} Service Producer`)
- Add the newly created Liferay user to the new Liferay role.

An admin will then assign the permissions needed to that Service Producer role.

This Service Produce User can then be used to login to Koroneiki.
- Login email: `service_producer.#####@liferay.com`
- Login password: `{Service Producer Name}`

This user will be needed to create Authentication Tokens to use the Koroneiki API.