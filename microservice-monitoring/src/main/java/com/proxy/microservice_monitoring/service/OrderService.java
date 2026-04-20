package com.proxy.microservice_monitoring.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String createOrder() {
        return "Order created successfully";
    }

    public String cancelOrder() {
        return "Order canceled successfully";
    }
}