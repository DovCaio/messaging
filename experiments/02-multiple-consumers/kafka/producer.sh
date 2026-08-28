#!/bin/bash

TOPIC="lab-multiple-consumers"

echo "Publishing messages..."

for i in {1..12}; do
    echo "message-$i"
    echo "Sent: message-$i" >&2
    sleep 0.5
done | docker exec -i kafka \
    /opt/kafka/bin/kafka-console-producer.sh \
    --topic "$TOPIC" \
    --bootstrap-server localhost:9092 \
    --property "partitioner.class=org.apache.kafka.clients.producer.RoundRobinPartitioner"

echo "Finished."