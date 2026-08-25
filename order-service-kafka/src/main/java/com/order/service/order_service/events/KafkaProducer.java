package com.order.service.order_service.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "orders.events";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderCreatedEvent event) {
        kafkaTemplate.send(
                TOPIC,
                event.orderId().toString(),
                event);
    }
}
