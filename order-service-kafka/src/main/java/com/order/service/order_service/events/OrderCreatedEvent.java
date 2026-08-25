package com.order.service.order_service.events;

public record OrderCreatedEvent(
                Long orderId,
                Long customerId,
                Long productId,
                Integer quantity) {
}