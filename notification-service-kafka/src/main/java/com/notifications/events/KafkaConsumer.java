package com.notifications.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

        @KafkaListener(topics = "orders.events", groupId = "notification-service")
        public void consume(OrderCreatedEvent event) {

                System.out.println(
                                "📨 Pedido recebido: " + event.orderId());

                System.out.println(
                                "👤 Cliente: " + event.customerId());

                System.out.println(
                                "📦 Produto: " + event.productId());

                System.out.println(
                                "🔢 Quantidade: " + event.quantity());
        }
}