#!/bin/bash

echo "Starting consumers..."

./consumer.sh &
PID1=$!

./consumer.sh &
PID2=$!

./consumer.sh &
PID3=$!

echo "Consumers started:"
echo "PID 1: $PID1"
echo "PID 2: $PID2"
echo "PID 3: $PID3"

trap 'echo "Stopping consumers..."; kill $PID1 $PID2 $PID3' INT TERM

wait