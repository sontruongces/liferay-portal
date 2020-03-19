# Koroneiki Environment Setup

## Downloads

Download the tomcat bundle with the Liferay version in [lrkoroneiki repo](https://github.com/dxpcloud/lrkoroneiki/blob/master/gradle.properties).

## Fixpacks

Install the same fixpack as on the lrkoroneiki repo if one exists.

## Portal Properties

See the [lrkoroneiki repo](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/common/portal-all.properties) for portal-ext.properties.

## Configs

Create the following config files and place them in `{liferay-home}/osgi/configs`.

### Elasticsearch

Add [additionalTypeMappings](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/common/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config#L1) to your Elasticsearch config file.

### REST API

Add the [REST Configuration](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/common/com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.application.KoroneikiRESTApplication.config).

### RabbitMQ

Use the RabbitMQ Dev server for development purposes.

Each developer has different exchanges and queues based on a color of their choosing. For example: `dev_blue_queue`. You will use this color in some of your config files. This is so multiple developers testing at the same time do not conflict with each other.

All [RabbitMQ config files](https://github.com/dxpcloud/lrkoroneiki/tree/master/lcp/liferay/config/dev).

#### Legacy RabbitMQ

The Legacy RabbitMQ configs are for connecting to web.liferay.com.

First setup the configuration to create a connection to the RabbitMQ server.

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.LegacyConnection.config`.

Add the properties listed [here](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.LegacyConnection.config). Replace `host` property with value `rabbitmq-dev.liferay.com`.

Next configure the Legacy *Message Broker*.

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.LegacyMessageBroker.config`.

Add the properties listed [here](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.LegacyMessageBroker.config).

Replace `sandbox` with `dev`, and also add your specific color to the name. Example: `dev_blue_entity_exchange`.

Lastly configure the Legacy *Consumer*.

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config`.

Add the properties listed [here](com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config), and make similar changes as noted above.

#### Xylem RabbitMQ

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.XylemConnection.config`.

Add the properties listed [here](https://github.com/liferay/lfrsite-team-able/blob/master/customer/sandbox-2/portal/modules/private/apps/osb-customer/osb-customer-rabbitmq-connector/osb-customer-rabbitmq-connector-service/src/main/resources/portlet-ext.properties).

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.XylemMessageBroker.config`.

Add the properties listed [here](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.XylemMessageBroker.config).

Update the properties to include your specific color. Example: `dev_blue_koroneiki_exchange`.

We do not need a consumer like the *Legacy Consumer* listed above, because Koroneiki is not consuming messages from itself.

See `/documentation/rabbitmq.markdown` for additional RabbitMQ information and setup.

### Connect to web.liferay

To connect to web.liferay for the WebContactIdentityProvider, create a file called `com.liferay.osb.koroneiki.root.identity.management.internal.provider.WebContactIdentityProvider.config`.

Add the necessary [properties](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.root.identity.management.internal.provider.WebContactIdentityProvider.config).

## Modules

Deploy all modules in `osb-koroneiki` except for `osb-koroneiki-phloem-rest-test`. If Koroneiki has already been released, or you do not need to test the data migration, you can also ignore `osb-koroneiki-data-migration`.

If using RabbitMQ, also deploy modules in `osb-distributed-messaging`.

## Setting up API

To set up authentication for the REST API, a *Service Producer* needs to be created along with an API token.

1. Create a `Service Producer`. (See Service Producer documentation)
2. Go to Roles &rarr; Service Producer Role, and select all the necessary Koroneiki permissions that you need to use.
3. Create an API token. (See Authentication documentation)