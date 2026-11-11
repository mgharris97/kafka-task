package com.example.inventory_service.listener;

import com.example.inventory_service.dto.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @KafkaListener(topics = "order-created")
    public void handle(OrderCreatedEvent event) {
        System.out.println("Processing order:");
        System.out.println("OrderId: " + event.orderId());
        System.out.println("Product: " + event.product());
        System.out.println("Quantity: " + event.quantity());
    }
}