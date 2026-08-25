package com.order.service.order_service.controller;

import com.order.service.order_service.events.OrderCreatedEvent;
import com.order.service.order_service.events.RabbitMQProducer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RabbitMQProducer rabbitMQProducer;

    public OrderController(
            RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderCreatedEvent event) {
        rabbitMQProducer.send(event);
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("ok");
    }

}