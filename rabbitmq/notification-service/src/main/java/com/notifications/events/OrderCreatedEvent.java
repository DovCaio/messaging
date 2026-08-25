package com.notifications.events;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        Long productId,
        Integer quantity) {
}