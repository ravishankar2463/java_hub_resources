package com.javahub.saga.service;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class SagaOrchestratorService {
    public record SagaExecutionResult(String sagaId, String status, List<String> stepsExecuted, List<String> compensationsTriggered) {}
    public SagaExecutionResult executeOrderSaga(String orderId, boolean simulatePaymentFailure) {
        List<String> steps = new ArrayList<>();
        List<String> compensations = new ArrayList<>();
        steps.add("Step 1: Reserve Inventory in InventoryService");
        if (simulatePaymentFailure) {
            compensations.add("Compensate Step 1: Release reserved inventory in InventoryService");
            return new SagaExecutionResult(orderId, "COMPENSATED_FAILED", steps, compensations);
        }
        steps.add("Step 2: Charge Customer Card in PaymentService");
        steps.add("Step 3: Create Delivery Ticket in ShippingService");
        return new SagaExecutionResult(orderId, "COMMITTED_SUCCESS", steps, compensations);
    }
}
