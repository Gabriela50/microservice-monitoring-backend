package com.proxy.microservice_monitoring.controller;

import com.proxy.microservice_monitoring.proxy.LoggingProxy;
import com.proxy.microservice_monitoring.service.InventoryService;
import com.proxy.microservice_monitoring.service.OrderService;
import com.proxy.microservice_monitoring.service.PaymentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    private final LoggingProxy loggingProxy;
    private final InventoryService inventoryService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public ServiceController(
            LoggingProxy loggingProxy,
            InventoryService inventoryService,
            OrderService orderService,
            PaymentService paymentService
    ) {
        this.loggingProxy = loggingProxy;
        this.inventoryService = inventoryService;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/inventory/{operation}")
    public Object inventory(@PathVariable String operation) {
        return loggingProxy.execute("Inventory", inventoryService, operation);
    }

    @PostMapping("/orders/{operation}")
    public Object orders(@PathVariable String operation) {
        return loggingProxy.execute("Orders", orderService, operation);
    }

    @PostMapping("/payments/{operation}")
    public Object payments(@PathVariable String operation) {
        return loggingProxy.execute("Payments", paymentService, operation);
    }
}