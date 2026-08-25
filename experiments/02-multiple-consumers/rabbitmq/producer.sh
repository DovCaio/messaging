#!/bin/bash

QUEUE="lab-multiple-consumers"

echo "Publishing messages..."

for i in {1..12}; do
    curl -s -u guest:guest \
      -H "content-type: application/json" \
      -X POST \
      http://localhost:15672/api/exchanges/%2F/amq.default/publish \
      -d "{\"properties\":{},\"routing_key\":\"$QUEUE\",\"payload\":\"message-$i\",\"payload_encoding\":\"string\"}" \
      > /dev/null

    echo "Sent: message-$i"
done

echo "Finished."