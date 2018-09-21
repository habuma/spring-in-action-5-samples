= Taco Cloud v0.0.11 (MongoDB)

This folder contains the source code for the Taco Cloud sample from Spring in Action, 5th edition, as presented in Chapters 11 and 12.

There may be portions of the application that are not fully functional (yet), but the code that is relevant to chapter 11 and 12 should be complete and ready to run.

It's also important to note that example code tends to evolve throughout the course of a chapter. Moreover, there are often many different variants of a given piece of code presented. The sample code given here may only represent one such variant or evolution.

== Running Taco Cloud

Taco Cloud has RabbitMQ as an external requirement. Before running the application, be sure to fire up a RabbitMQ broker on localhost, listening on the default port. For more details on running RabbitMQ, see https://www.rabbitmq.com/#getstarted. For reference, I tested this with RabbitMQ 3.7.2, although it should work with most recent RabbitMQ releases.

As configured, Taco Cloud uses an embedded Mongo database (Flapdoodle) so there's no need to explicitly start a MongoDB server.

Starting with Chapter 6, Taco Cloud is broken into a multi-module Maven project. To build the full project, use `mvnw` (the Maven wrapper) at the command line like this:

[source,sh]
----
% ./mvnw clean package
----

Once the project is built, you can run the executable JAR file from the `tacos` project:

[source,sh]
----
% java -jar tacos/target/taco-cloud-0.0.11-SNAPSHOT.jar
----

Once the application has started, open your web browser and navigate to http://localhost:8080 to see the Taco Cloud home page.

== Kicking the tires

As mentioned above, not all of the Taco Cloud application is fully functional (yet). But the components that pertain to the topic of Chapters 6-9, as well as relevant code from Chapters 11 and 12 pertaining to reactive programming with Spring should be in place.

From the home page, you should be able to view some recently created tacos by clicking on "Latest designs" link at the top. The "Specials" and "Locations" links will navigate to their respective pages, but those pages are (for now) empty.

Click the "DESIGN A TACO" link to create a taco and ultimately create an order. You can view the contents of your shopping cart by clicking on the cart/price at the top right.

=== What's missing/broken...

The three most prominent pieces that still need to be fixed are:

 - Security isn't working yet, so there's no login page.
 - Likewise, there's not yet any way to register.

These will be certainly be sorted out eventually, but priority was placed on writing code that demonstrated the subject of Chapters 11 and 12.

== Taco Cloud modules

The multi-module Maven project is made up of the following modules:

 - `tacocloud-api` : The REST API. Modified to demonstrate Spring WebFlux.
 - `tacocloud-data-mongodb` : Mongo persistence module. Modified to demonstrate Spring Data's support for reactive programming.
 - `tacocloud-domain-mongodb` : The domain types
 - `tacocloud-email` : The email module that demonstrates using Spring Integration to handle orders by email.
 - `tacocloud-kitchen` : An application to be run in the Taco Cloud kitchen that will receive orders for kitchen staff to prepare.
 - `tacocloud-messaging-jms` : The Taco Cloud messaging module that sends messages using JMS.
 - `tacocloud-messaging-kafka` : The Taco Cloud messaging module that sends messages using Kafka.
 - `tacocloud-messaging-rabbitmq` : The Taco Cloud messaging module that sends messages using RabbitMQ.
 - `tacocloud-restclient` : Client code that consumes the API exposed from `tacocloud-api`.
 - `tacocloud-security` : The security module (TODO: Not fully functional yet.)
 - `tacocloud-ui` : A Typescript Angular UI
 - `tacocloud-web` : The web module (largely leftovers from previous chapters. TODO: Clean up and remove.)
 - `tacos` : The main module that pulls the other modules together and provides the Spring Boot main class.

In order to use the email integration module, you will need to edit the `src/main/resources/application/yml` file in the `tacocloud-email` module, providing the configuration for an email server of your choosing. The values configured in there now are just placeholders and will not work.

The `tacocloud-restclient` module, while part of the Maven multi-module build, is otherwise separate from the rest of the Taco Cloud application. It contains sample code that demonstrates how to use `RestTemplate` and Traverson to consume the APIs exposed by the Taco Cloud application.

Assuming that the Taco Cloud application has been built and is running, you can run the client application at the command line like this:

[source,sh]
----
% java -jar tacocloud-restclient/target/tacocloud-restclient-0.0.11-SNAPSHOT.jar
----

Review the source code in the `tacocloud-restclient` folder as you run the client to understand what is being emitted to the console.
