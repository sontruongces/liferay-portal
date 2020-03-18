# Provisioning Environment Setup

## Downloads

1. Download tomcat bundle with the same Liferay version as on the [lrprovisioning repo](https://github.com/dxpcloud/lrprovisioning/blob/master/gradle.properties).

## Fixpacks

1. Install the same fixpack as on the lrprovisioning repo if one exists.

## Portal Properties

In your base `{liferay-home}/` bundle directory, create a file called portal-ext.properties.

In your bundle, create a file called portal-ext.properties.

It should look similar to the one in the [lrprovisioning repo](https://github.com/dxpcloud/lrprovisioning/blob/master/lcp/liferay/config/common/portal-all.properties).

## Configs

Create the following config files and place them in `{liferay-home}/osgi/configs`.

### Koroneiki

To connect to Koroneiki, create a file named `com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration.config`.

Add the necessary [properties](https://github.com/dxpcloud/lrprovisioning/blob/master/lcp/liferay/config/dev/com.liferay.osb.provisioning.koroneiki.web.service.internal.configuration.KoroneikiConfiguration.config) for connecting to Koroneiki Dev.

### RabbitMQ

We will be using the RabbitMQ Dev server for development purposes.

Each developer has different exchanges and queues based on a color of their choosing. For example: `dev_blue_queue`. You will use this color in some of your config files. This is so multiple users testing at the same time does not conflict with each other.

All [RabbitMQ config files](https://github.com/dxpcloud/lrprovisioning/tree/master/lcp/liferay/config/dev).

#### Legacy RabbitMQ

The Provisioning server needs to connect to the Legacy RabbitMQ to retrieve messages from Dossiera.

First we need the properties to create a connection to the RabbitMQ server.

Create a file called `com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.LegacyConnection.config`.

Add the properties listed [here](https://github.com/dxpcloud/lrprovisioning/blob/master/lcp/liferay/config/dev/com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.LegacyConnection.config). Replace `host` property with value `rabbitmq-dev.liferay.com`.

Next we need a config file for the *Consumer*.

Create a file called `com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config`.

Add the properties listed [here](com.liferay.osb.provisioning.distributed.messaging.internal.rabbitmq.consumer.LegacyConsumer.config).

Replace `is_dxpcloud` with `dev` and also add your specific color to the name as well. Example `dev_blue_osb_provisioning_queue`.

## Modules

Deploy all modules in `osb-provisioning`.

If using RabbitMQ, also deploy modules in `osb-distributed-messaging`. (See RabbitMQ documentation for setup)