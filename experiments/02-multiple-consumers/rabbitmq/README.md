# Experiment 02 — Multiple Consumers with RabbitMQ

## Objective

Demonstrate how RabbitMQ distributes messages from a single queue among multiple consumers.

The experiment uses **one producer and multiple consumers**, where all consumers perform the same type of processing.

## Architecture

```text
                 ┌── Consumer 1
                 │
Producer ──> Queue ── Consumer 2
                 │
                 └── Consumer 3
```

The consumers are competing for messages from the same queue. Each message is delivered to only one consumer, allowing the workload to be distributed among multiple workers.

## Experiment

The producer publishes 12 messages to the `lab-multiple-consumers` queue.

Three consumers are started simultaneously:

```text
Consumer 1
Consumer 2
Consumer 3
```

Each consumer executes the same processing logic.

The expected behavior is that the messages are distributed among the consumers instead of being processed by all of them.

For example:

```text
message-1 → Consumer 1
message-2 → Consumer 2
message-3 → Consumer 3
message-4 → Consumer 1
...
```

The exact distribution may vary depending on the consumers' availability and processing speed.

## Key Concept — Competing Consumers

This pattern is commonly known as the **Competing Consumers** or **Work Queue** pattern.

The goal is not for every consumer to process every message.

Instead:

> Multiple consumers share the workload of a single queue.

This allows the system to increase its processing capacity by adding more consumers.

```text
                Queue
                  │
         ┌────────┼────────┐
         ↓        ↓        ↓
      Worker 1 Worker 2 Worker 3
```

If one consumer can process 100 tasks per second, multiple consumers can increase the overall processing capacity, assuming the workload can be effectively parallelized.

## Important Distinction

This experiment represents:

```text
One Producer
     ↓
One Queue
     ↓
Multiple Consumers
```

The consumers are **instances of the same worker process** and compete for messages.

This is different from a scenario where multiple independent services need to receive the same event.

For example:

```text
                 Exchange
                    │
          ┌─────────┼─────────┐
          ↓         ↓         ↓
       Queue A   Queue B   Queue C
          ↓         ↓         ↓
       Service A Service B Service C
```

In this second scenario, each service can receive its own copy of the message through separate queues and appropriate exchange bindings.

## Observation

The experiment demonstrates that RabbitMQ can use multiple consumers to distribute the workload of a queue.

The important idea is:

> **Multiple consumers on the same queue are used to divide the work, not to process the same message multiple times.**

This pattern is particularly useful for asynchronous task processing and worker-based architectures.
