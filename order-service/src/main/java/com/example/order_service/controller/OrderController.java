package com.example.order_service.controller;

import com.example.order_service.dto.OrderCreatedEvent;
import com.example.order_service.dto.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final KafkaTemplate<String, OrderCreatedEvent> kafka;

    public OrderController(KafkaTemplate<String, OrderCreatedEvent> kafka) {
        this.kafka = kafka;
    }

    @PostMapping
    public ResponseEntity<OrderCreatedEvent> create(@RequestBody OrderRequest req) {
        OrderCreatedEvent event = new OrderCreatedEvent(
                UUID.randomUUID(), req.customerId(),
                req.product(), req.quantity(), Instant.now());
        kafka.send("order-created", event.orderId().toString(), event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }
}