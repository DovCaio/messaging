# Messaging Lab

A practical laboratory for exploring asynchronous communication between microservices using Spring Boot, Apache Kafka, and RabbitMQ.

## Objective

The goal of this project is to study and experiment with messaging systems and their integration with Spring Boot applications.

The laboratory starts with Apache Kafka and will later implement the same communication flow using RabbitMQ, allowing a practical comparison between both technologies.

## Architecture

### Apache Kafka and RabbitMQ

```text
┌─────────────────┐
│  order-service  │
└────────┬────────┘
         │
         │ OrderCreatedEvent
         ▼
┌─────────────────┐
│   messaging     │
│  orders.events  │
└────────┬────────┘
         │
         ▼
┌────────────────────────┐
│  notification-service  │
└────────────────────────┘
```

## Services

### order-service

Responsible for creating orders and publishing `OrderCreatedEvent` messages to the messaging.

### notification-service

Responsible for consuming order creation events from messaging and processing them as notification events.

## Apache Kafka

The first stage of the laboratory uses Apache Kafka as the messaging platform.

### Producer

The `order-service` publishes `OrderCreatedEvent` messages to the following topic:

```text
orders.events
```

### Consumer

The `notification-service` consumes messages using the following consumer group:

```text
notification-service
```

### Event

The current event structure is:

```json
{
  "orderId": 1,
  "customerId": 10,
  "productId": 20,
  "quantity": 2
}
```

## Technologies

- Java 17
- Spring Boot
- Spring Kafka
- Apache Kafka
- Maven
- Docker
- Docker Compose

## Concepts Studied

- Asynchronous communication
- Message producers
- Message consumers
- Kafka topics
- Consumer groups
- Kafka offsets
- JSON serialization
- JSON deserialization
- Event-driven communication
- Service decoupling

## Issues and Lessons Learned

### JSON Deserialization and Class Resolution

During the implementation, the Kafka consumer initially attempted to resolve the Java class used by the producer:

```text
com.order.service.order_service.events.OrderCreatedEvent
```

Since this class belongs to the `order-service` application and was not available in the `notification-service`, the consumer failed with a `ClassNotFoundException`.

This highlighted an important aspect of event-driven architectures: integration events should not unnecessarily depend on the internal Java class structure of another service.

The configuration was subsequently adjusted so that the consumer could deserialize the event according to its own local representation.

## Project Status

### Kafka

- [x] Kafka infrastructure
- [x] Producer
- [x] Consumer
- [x] JSON event serialization
- [x] JSON event deserialization
- [x] Consumer group
- [x] End-to-end event flow

### RabbitMQ

- [x] RabbitMQ infrastructure
- [x] Producer
- [x] Exchange
- [x] Queue
- [x] Binding
- [x] Routing key
- [x] Consumer
- [x] End-to-end event flow

## Next Steps

The next stage of the laboratory will implement the same event-driven flow using RabbitMQ.

The two implementations will then be compared in terms of:

- Messaging model
- Message routing
- Delivery semantics
- Consumer behavior
- Acknowledgements
- Retry strategies
- Dead-letter queues
- Scalability
- Failure handling

Later stages will explore more advanced messaging patterns, including idempotency and the Outbox Pattern.
