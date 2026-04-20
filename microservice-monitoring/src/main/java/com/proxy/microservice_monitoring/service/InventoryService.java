package com.proxy.microservice_monitoring.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public String checkStock() {
        return "Inventory checked successfully";
    }

    public String updateStock() {
        return "Inventory updated successfully";
    }
}