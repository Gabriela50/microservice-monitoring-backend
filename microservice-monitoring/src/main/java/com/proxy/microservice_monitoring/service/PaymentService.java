package com.proxy.microservice_monitoring.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final Random random = new Random();

    public String processPayment() {
        if (random.nextInt(10) == 1) {
            throw new RuntimeException("Payment failed");
        }
        return "Payment processed successfully";
    }

    public String refundPayment() {
        return "Refund completed successfully";
    }
}