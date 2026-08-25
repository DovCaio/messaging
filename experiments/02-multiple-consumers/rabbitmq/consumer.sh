#!/bin/bash

QUEUE="lab-multiple-consumers"

CONSUMER_ID=${1:-"consumer-unknown"}

echo "[$CONSUMER_ID] Started."

while true; do

    RESPONSE=$(curl -s -u guest:guest \
      -H "content-type: application/json" \
      -X POST \
      http://localhost:15672/api/queues/%2F/$QUEUE/get \
      -d '{"count":1,"ackmode":"ack_requeue_false","encoding":"auto","truncate":50000}')

    MESSAGE=$(echo "$RESPONSE" | grep -o '"payload":"[^"]*"' | cut -d'"' -f4)

    if [ -n "$MESSAGE" ]; then
        echo "[$CONSUMER_ID] Received: $MESSAGE"
        sleep 2
    else
        sleep 1
    fi

done