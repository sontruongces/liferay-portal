# Using RabbitMQ

## Credentials
A set of unique credentials will need to be manually created for each system. If you do not already have a RabbitMQ username and password, please create a "LHC" jira ticket to request one.

## Modules
The [Distributed Messaging](https://github.com/liferay/liferay-portal-ee/tree/7.2.x-private/modules/private/apps/osb-distributed-messaging) modules are needed to implement your system with Koroneiki's RabbitMQ service.

These modules classes will be able to be extended to implement your own connections, publishers, brokers, routers, and consumers within your own module.

## Connection
First a connection will need to be made with the Koroneiki RabbitMQ service.

This can be done by extending `BaseConnection` in `com.liferay.osb.distributed.messaging.rabbitmq.connector`.

There are a few `properties` that need to be set in each `Connection` class:
- `host`
- `password`
- `port`
- `username`

The custom `Connection` class should look something like this:

```
@Component(
	immediate = true, property = {"host=", "password=", "port=", "username="},
	service = CustomConnection.class
)
public class CustomConnection extends BaseConnection {
}
```
An OSGi config file can be created for the specific properties that are needed. The _Custom_ naming can be replaced with whatever naming scheme is best fit for your modules.
```
host="rabbitmq-lrkoroneiki-prod--ports.lfr.cloud"
password="password"
port="5672"
username="username"
```
The username and password will be the same ones provided from the jira ticket.

Multiple `Connection` classes can be created to connect to multiple RabbitMQ services if needed. 

Each connection will have a `Publisher`, which can have multiple `Broker` classes, and a `Router`, which can have multiple `Subscriber` classes.

## Message Publisher
If the server needs to publish messages to Koroneiki, a `MessagePublisher` will need to be created to register the `MessageBroker` classes.

There should only need to be one `MessagePublisher` since there can be multiple `MessageBroker` classes registered within a single `MessagePublisher`, depending on how many you need.

The custom `MessagePublisher` class should look something like this:
```
@Component(immediate = true, service = MessagePublisher.class)
public class CustomMessagePublisher extends BaseMessagePublisher {

	@Reference(unbind = "-")
	protected void setCustomMessageBroker(
		CustomMessageBroker customMessageBroker, Map<String, Object> properties) {

		addMessageBroker(customMessageBroker, properties);
	}

}
```

The `MessagePublisher` will ensure that the `MessageBroker` classes are ready before registering.

## Message Broker
After creating a `MessagePublisher`, a `MessageBroker` will need to be created to route the messages to the correct place.

The `MessageBroker` classes are for the routing of messages to specific exchanges and routing topics. If messages need to be sent to multiple exchanges, then multiple `MessageBroker` classes can be created.

There are a couple `properties` that need to be set in each `MessageBroker`:
- `exchange`
- `publishing.topic.pattern`

The `MessageBroker` references the connection to the exchange that it is sending to (ex. `koroneiki_exchange`).

The custom `MessageBroker` class should look something like this:
```
@Component(
	immediate = true, property = {"exchange=", "publishing.topic.pattern=.*"},
	service = CustomMessageBroker.class
)
public class CustomMessageBroker extends BaseMessageBroker {

	@Override
	protected Connection getConnection() {
		return _customConnection;
	}

	@Reference
	private CustomConnection _customConnection;

}
```
Again, an OSGi config file can be created for the specific properties that are needed in your specific instance.

With the `MessagePublisher` and `MessageBroker` set up, you should now be able to publish messages to the RabbitMQ service.

## Consumer
If the server needs to consume messages from Koroneiki, a `Consumer` will need to be created to create the queue and subscribe to specific routing keys.

There are a few `properties` that need to be set in the `Consumer`:
- `exchange`
	- This will already exist on Koroneiki.
- `queue`
	- When adding this property, the queue will automatically be created on RabbitMQ for your server to start consuming from.
- `routing.key`
	- The routing keys that should be sent to the queue.
	- Multiple routing keys can be listed.

The consumer also references the connection to the queue.

The `Consumer` should look something like this:
```
@Component(
	immediate = true,
	property = {
		"exchange=custom_exchange", "queue=is_custom_queue",
		"routing.key=routing.key.name"
	},
	service = CustomConsumer.class
)
public class CustomConsumer extends BaseConsumer {

	@Override
	protected Connection getConnection() {
		return _customConnection;
	}

	@Reference
	private CustomConnection _customConnection;

}
```

Currently, the existing Koroneiki topics are:
- `koroneiki.account.create`
- `koroneiki.account.delete`
- `koroneiki.account.update`
- `koroneiki.account.contact.assigned`
- `koroneiki.account.contactrole.assigned`
- `koroneiki.account.contact.unassigned`
- `koroneiki.account.contactrole.unassigned`
- `koroneiki.contact.create`
- `koroneiki.contact.delete`
- `koroneiki.contact.update`
- `koroneiki.contactrole.create`
- `koroneiki.contactrole.delete`
- `koroneiki.contactrole.update`
- `koroneiki.productconsumption.create`
- `koroneiki.productconsumption.delete`
- `koroneiki.productconsumption.update`
- `koroneiki.product.create`
- `koroneiki.product.delete`
- `koroneiki.product.update`
- `koroneiki.productpurchase.create`
- `koroneiki.productpurchase.delete`
- `koroneiki.productpurchase.update`
- `koroneiki.account.teamrole.assigned`
- `koroneiki.account.teamrole.unassigned`
- `koroneiki.team.create`
- `koroneiki.team.delete`
- `koroneiki.team.update`
- `koroneiki.teamrole.create`
- `koroneiki.teamrole.delete`
- `koroneiki.teamrole.update`

## Message Router
If the server needs to consume messages from Koroneiki, a `MessageRouter` will need to be created to register the `Subscriber` classes.

There should only need to be one `MessageRouter` since there can be multiple `Subscriber` classes registered within a single `MessageRouter`, depending on how many you need.

The custom `MessageRouter` class should look similar to `MessagePublisher` where the `MessageBroker` classes were registered:
```
@Component(immediate = true, service = MessageRouter.class)
public class CustomMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setCustomMessageSubscriber(
		CustomMessageSubscriber customMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(customMessageSubscriber, properties);
	}

}
```

## Subscriber
The `Subscriber` classes that were registered in the `MessageRouter` will hold the logic for the different routing keys the server is consuming from.

There will need to be a `topic.pattern` `property` for this class that has the specific routing key which this class will subscribe from.

There can be as many `Subscriber` classes as needed depending on how many routing keys are being consumed from. Each Koroneiki RabbitMQ message will be in JSON, so these classes will need to parse it and implement any logic needed for your own system.

Each custom `Subscriber` class should look something like this:
```
@Component(
	immediate = true, property = "topic.pattern=custom.routing.key.name",
	service = CustomMessageSubscriber.class
)
public class CustomMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		// Custom logic
	}
}
```
These `Subscriber` classes should reference the `localService` classes needed to implement the logic, so the consumer wont start until the `localService` classes are registered.