package com.order.service.order_service.controller;

import com.order.service.order_service.events.KafkaProducer;
import com.order.service.order_service.events.OrderCreatedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaProducer kafkaProducer;

    public OrderController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(
            @RequestBody OrderCreatedEvent event) {

        kafkaProducer.send(event);

        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("ok");
    }

}