# Experiment 02 — Multiple Consumers and Consumer Groups with Kafka

## Objective

Understand how Kafka distributes work among multiple consumers using **Partitions** and **Consumer Groups**, and observe how **offsets** allow events to be consumed again.

## Multiple Consumers

In Kafka, multiple consumers can belong to the same **Consumer Group**.

```text
                    Topic
                      │
          ┌───────────┼───────────┐
          ↓           ↓           ↓
     Partition 0  Partition 1  Partition 2
          │           │           │
          ↓           ↓           ↓
     Consumer 1  Consumer 2  Consumer 3
          └───────────┼───────────┘
                Consumer Group
                    "workers"
```

Consumers within the same group **share the partitions**. A partition is assigned to only one consumer within the same group at a given time.

Therefore, increasing the number of consumers does not automatically increase parallelism.

The maximum parallelism within a consumer group is limited by the number of partitions.

For example:

```text
3 Consumers + 1 Partition
→ only 1 consumer can process messages

3 Consumers + 3 Partitions
→ up to 3 consumers can process messages simultaneously
```

## Consumer Groups

A **Consumer Group** represents a set of consumers working together to consume a topic.

In our experiment, all consumers used:

```text
--group workers
```

This means they belong to the same group and share the workload instead of each receiving every event.

Different Consumer Groups, however, can consume the same events independently.

```text
             Topic
               │
       ┌───────┴────────┐
       ↓                ↓
   Group A            Group B
   Consumer 1         Consumer 1
   Consumer 2         Consumer 2
```

Each group maintains its own offsets.

## Offsets

Kafka does not remove an event simply because a consumer processed it.

Instead, the Consumer Group keeps track of its progress using **offsets**.

For example:

```text
CURRENT-OFFSET = 24
LOG-END-OFFSET = 24
LAG = 0
```

This means the group has processed all available events up to that point.

The events themselves remain stored in the partition according to the configured retention policy.

## Replay

Because the events remain available, we can move the Consumer Group's offset back to an earlier position.

In the experiment, we reset the group:

```text
workers
CURRENT-OFFSET: 24
        ↓
reset to earliest
        ↓
CURRENT-OFFSET: 0
```

After starting the consumer again, the previously consumed events were available for processing again.

This is known as **event replay**.

## Main Difference from RabbitMQ

The experiment reinforced an important difference between Kafka and RabbitMQ.

### RabbitMQ

```text
Queue
  ↓
Consumer
  ↓
ACK
  ↓
Message removed
```

The message is normally removed from the queue after successful acknowledgement.

### Kafka

```text
Partition
  ↓
Consumer
  ↓
Offset advances
  ↓
Event remains stored
```

The consumer's progress is represented by the offset, while the event remains available until it is removed according to the retention policy.

## Main Learning

The key concepts learned in this experiment were:

- **Partitions** provide the basis for parallelism in Kafka.
- **Consumer Groups** allow multiple consumers to share the processing workload.
- Consumers in the same group do not independently receive every event.
- The number of partitions limits the maximum parallelism of a consumer group.
- **Offsets** represent the progress of a consumer group.
- Consumed events are not immediately deleted.
- Events can be **replayed** by moving the consumer group's offset to an earlier position.

The main mental model is:

> **RabbitMQ distributes messages between consumers through queues, while Kafka distributes partitions among consumers in a consumer group and tracks consumption through offsets.**
