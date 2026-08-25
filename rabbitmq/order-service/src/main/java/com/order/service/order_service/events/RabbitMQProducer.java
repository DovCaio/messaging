package com.order.service.order_service.events;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(OrderCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                "orders.exchange",
                "order.created",
                event);
    }
}
