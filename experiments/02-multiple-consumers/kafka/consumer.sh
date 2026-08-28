#!/bin/bash

docker exec -i kafka \
  /opt/kafka/bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic lab-multiple-consumers \
  --group workers