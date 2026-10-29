package com.javahub.microservices.service;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class BoundedContextBoundaryService {
    public Map<String, Object> describeContext() {
        return Map.of(
            "serviceName", "OrderFulfillmentService",
            "boundedContext", "OrderingDomain",
            "databaseOwnership", "PRIVATE_ORDER_DB",
            "contractType", "REST_JSON_AND_ASYNC_EVENTS"
        );
    }
}
