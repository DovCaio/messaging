docker exec kafka /opt/kafka/bin/kafka-topics.sh \
  --create \
  --topic lab-multiple-consumers \
  --bootstrap-server localhost:9092 \
  --partitions 3 \
  --replication-factor 1

docker exec kafka /opt/kafka/bin/kafka-topics.sh \
  --describe \
  --topic lab-multiple-consumers \
  --bootstrap-server localhost:9092
