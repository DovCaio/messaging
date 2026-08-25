curl -u guest:guest \
  -H "content-type: application/json" \
  -X PUT \
  http://localhost:15672/api/queues/%2F/lab-multiple-consumers \
  -d '{"durable":true}'