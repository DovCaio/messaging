#!/bin/bash

echo "Starting 3 RabbitMQ consumers..."

./consumer.sh consumer-1 &
PID1=$!

./consumer.sh consumer-2 &
PID2=$!

./consumer.sh consumer-3 &
PID3=$!

echo "Consumers running:"
echo "consumer-1 -> PID $PID1"
echo "consumer-2 -> PID $PID2"
echo "consumer-3 -> PID $PID3"

trap "echo 'Stopping consumers...'; kill $PID1 $PID2 $PID3; exit" INT

wait   