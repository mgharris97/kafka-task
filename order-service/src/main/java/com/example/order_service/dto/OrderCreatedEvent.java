package com.example.order_service.dto;

import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(UUID orderId, Long customerId,
                                String product, int quantity, Instant createdTime) {}