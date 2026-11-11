package com.example.order_service.dto;

public record OrderRequest(Long customerId, String product, int quantity) {}
