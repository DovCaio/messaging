package com.notifications.events;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "orders.queue")
    public void consume(OrderCreatedEvent event) {
        System.out.println("RabbitMQ - Order received: " + event);
    }
}