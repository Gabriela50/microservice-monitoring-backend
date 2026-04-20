package com.proxy.microservice_monitoring.proxy;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Component
public class LoggingProxy {

    public Object execute(String serviceName, Object target, String operation) {
        long start = System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString();

        try {
            Method method = target.getClass().getMethod(operation);
            Object result = method.invoke(target);

            long duration = System.currentTimeMillis() - start;

            System.out.println("SUCCESS | " +
                    requestId + " | " +
                    serviceName + " | " +
                    operation + " | " +
                    duration + "ms");

            return result;

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - start;

            System.out.println("ERROR | " +
                    requestId + " | " +
                    serviceName + " | " +
                    operation + " | " +
                    duration + "ms");

            return "Execution failed";
        }
    }
}