# Koroneiki Environment Setup

## Downloads

1. Download [tomcat bundle](https://files.liferay.com/private/ee/portal/7.2.10.1/liferay-dxp-tomcat-7.2.10.1-sp1-20191009103614075.tar.gz).
2. Download [fixpack dxp-2-7210](https://files.liferay.com/private/ee/fix-packs/7.2.10/dxp/liferay-fix-pack-dxp-2-7210.zip).

## Fixpacks

1. Install fixpack dxp-2-7210.

If unsure how to install fixpacks, see [patching-liferay](https://portal.liferay.dev/docs/6-2/deploy/-/knowledge_base/d/patching-liferay).

## License

A license is needed to activate Liferay DXP. One will need to be generated from the license portlet.

1. Navigate to the [License Portlet](https://customer.liferay.com/group/license/).
2. Click on *Generate New License*.
3. Select *Liferay.com* as the project.
4. Select *Digital Enterprise Development* as the product.
5. Select the *Liferay Version* from the bundle that was downloaded.
6. Select *Digital Enterprise Development* as the type.
7. Choose the license with maximum *Lifetime*.
8. Click *Generate*.
9. Click *Download License File*.
10. Copy and paste the *XML* file into your bundle *deploy* folder.

Save this file some place on your computer so you can use it on any other Liferay DXP projects as well.

## Portal Properties

In your base `{liferay-home}/` bundle directory, create a file called portal-ext.properties.

It should look similar to the one in the [lrkoroneiki repo](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/common/portal-all.properties).

For the *JDBC* properties:

1. Replace `jdbc.default.url` property `database` with `localhost`. 
2. Replace `jdbc.default.url` property `lportal` with your own database name.
3. Replace `jdbc.default.username` property with your local database username.
4. Replace `jdbc.default.password` property with your local database pasword.

### GoGo Shell

If you would like to access the *GoGo Shell* from the terminal instead of just the UI, include the following property.

`module.framework.properties.osgi.console=localhost:11311`

## Configs

Create the following config files and place them in `{liferay-home}/osgi/configs`.

### Elasticsearch

Create a file called `com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config`.

Add [additionalTypeMappings](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/common/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config#L1) to the config file.

### REST API

Create a file called `com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.application.KoroneikiRESTApplication.config`.

Add [REST Configuration](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/common/com.liferay.osb.koroneiki.phloem.rest.internal.jaxrs.application.KoroneikiRESTApplication.config) to the confic file.

### RabbitMQ

We will be using the RabbitMQ Dev server for development purposes.

Each developer has different exchanges and queues based on a color of their choosing. For example: `dev_blue_queue`. You will use this color in some of your config files. This is so multiple users testing at the same time does not conflict with each other.

All [RabbitMQ config files](https://github.com/dxpcloud/lrkoroneiki/tree/master/lcp/liferay/config/dev).

#### Legacy RabbitMQ

The Legacy RabbitMQ configs are for connecting to web.liferay.com.

First we need the properties to create a connection to the RabbitMQ server.

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.LegacyConnection.config`.

Add the properties listed [here](https://github.com/liferay/lfrsite-team-able/blob/master/customer/sandbox-2/portal/modules/private/apps/osb-customer/osb-customer-rabbitmq-connector/osb-customer-rabbitmq-connector-service/src/main/resources/portlet-ext.properties).

Next we need properties for the Legacy *Message Broker*.

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.LegacyMessageBroker.config`.

Add the properties listed [here](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.LegacyMessageBroker.config). 

Replace `sandbox` value with `dev`, and also add your specific color to the name as well. Example `dev_blue_entity_exchange`.

Lastly, we need properties for the Legacy *Consumer*.

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config`.

Add the properties listed [here](com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config), and make similar changes as noted above.

#### Xylem RabbitMQ

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.XylemConnection.config`.

Add the properties listed [here](https://github.com/liferay/lfrsite-team-able/blob/master/customer/sandbox-2/portal/modules/private/apps/osb-customer/osb-customer-rabbitmq-connector/osb-customer-rabbitmq-connector-service/src/main/resources/portlet-ext.properties).

Create a file called `com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.XylemMessageBroker.config`.

Add the properties listed [here](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.rabbitmq.broker.XylemMessageBroker.config).

Update the properties to include your specific color as well. Example: `dev_blue_koroneiki_exchange`.

We do not need a consumer like the *Legacy Consumer* listed above, because Koroneiki is not consuming messages from itself.

See `/documentation/rabbitmq.markdown` for additional RabbitMQ information and setup.

### Connect to web.liferay

To connect to web.liferay for the WebContactIdentityProvider, create a file called `com.liferay.osb.koroneiki.root.identity.management.internal.provider.WebContactIdentityProvider.config`.

Add the necessary [properties](https://github.com/dxpcloud/lrkoroneiki/blob/master/lcp/liferay/config/dev/com.liferay.osb.koroneiki.root.identity.management.internal.provider.WebContactIdentityProvider.config).

If using a local instance of web.liferay, replace the necessary properties.

1. Replace `host` value with `localhost`.
2. If using `http` instead of `https`, replace property `protocol` with `http`.
3. Replace `port` with your local port running web.liferay.
4. Repace `api.token` with your local api token value.

## Modules

Deploy all modules in `osb-koroneiki` except for `osb-koroneiki-phloem-rest-test`. If Koroneiki has already been released, or you do not need to test the data migration, you can also ignore `osb-koroneiki-data-migration`.

If using RabbitMQ, also deploy modules in `osb-distributed-messaging`.

## Setting up API

To set up the API to be able to use it, a *Service Producer* will have to be created along with an API token.

1. Create a *Service Producer*. (See Service Producer documentation)
2. Go to Roles &rarr; Service Producer Role, and select all the necessary Koroneiki permissions that you need to use.
3. Create an API token. (See Authentication documentation)