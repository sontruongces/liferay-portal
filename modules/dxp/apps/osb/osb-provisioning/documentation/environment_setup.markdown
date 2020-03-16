# Provisioning Environment Setup

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

In your bundle, create a file called portal-ext.properties.

It should look similar to the one in the [lrprovisioning repo](https://github.com/dxpcloud/lrprovisioning/blob/master/lcp/liferay/config/common/portal-all.properties).

For the *JDBC* properties:
1. Replace `jdbc.default.url` property `database` with `localhost`. 
2. Replace `jdbc.default.url` property `lportal` with your own database name.
3. Replace `jdbc.default.username` property with your local database username.
4. Replace `jdbc.default.password` property with your local database password.

### GoGo Shell

If you would like to access the *GoGo Shell* from the terminal instead of just the UI, include the following property.

`module.framework.properties.osgi.console=localhost:11311`

## Configs

Create the following config files and place them in `{liferay-home}/osgi/configs`.

### Koroneiki

To connect to Koroneiki, create a file named `com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration.config`.

Add the [properties](https://github.com/dxpcloud/lrprovisioning/blob/master/lcp/liferay/config/dev/com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration.config) for connecting to Koroneiki Dev.

If you are using the Koroneiki Dev server for development, you can leave it as is.

If connecting to a local Koroneiki instance:
1. Replace `port` with local Koroneiki `port` number.
2. Replace `apiToken` with local Koroneiki `apiToken` value.
3. Replace `host` value with `localhost`.
4. If using `http` instead of `https` replace `scheme` property.

### RabbitMQ

We will be using the RabbitMQ Dev server for development purposes.

Each developer has different exchanges and queues based on a color of their choosing. For example: `dev_blue_queue`. You will use this color in some of your config files. This is so multiple users testing at the same time does not conflict with each other.

All [RabbitMQ config files](https://github.com/dxpcloud/lrprovisioning/tree/master/lcp/liferay/config/dev).

#### Legacy RabbitMQ

The Provisioning server needs to connect to the Legacy RabbitMQ to retrieve messages from Dossiera.

First we need the properties to create a connection to the RabbitMQ server.

Create a file called `com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.LegacyConnection.config`.

Add the properties listed [here](https://github.com/liferay/lfrsite-team-able/blob/master/customer/sandbox-2/portal/modules/private/apps/osb-customer/osb-customer-rabbitmq-connector/osb-customer-rabbitmq-connector-service/src/main/resources/portlet-ext.properties).

Next we need a config file for the *Consumer*.

Create a file called `com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config`.

Add the properties listed [here](com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config).

Replace `is_dxpcloud` with `dev` and also add your specific color to the name as well. Example `dev_blue_osb_provisioning_queue`.

## Modules

Deploy all modules in `osb-provisioning`.

If using RabbitMQ, also deploy modules in `osb-distributed-messaging`. (See RabbitMQ documentation for setup)