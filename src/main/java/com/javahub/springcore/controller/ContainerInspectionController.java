package com.javahub.springcore.controller;

import com.javahub.springcore.service.OrderProcessingService;
import com.javahub.springcore.service.ReportCoordinatorService;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/container")
public class ContainerInspectionController {

    private final ApplicationContext applicationContext;
    private final OrderProcessingService orderService;
    private final ReportCoordinatorService reportCoordinatorService;

    public ContainerInspectionController(
            ApplicationContext applicationContext,
            OrderProcessingService orderService,
            ReportCoordinatorService reportCoordinatorService) {
        this.applicationContext = applicationContext;
        this.orderService = orderService;
        this.reportCoordinatorService = reportCoordinatorService;
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getContainerInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("applicationName", applicationContext.getId());
        info.put("startupDate", applicationContext.getStartupDate());
        info.put("beanDefinitionCount", applicationContext.getBeanDefinitionCount());
        info.put("status", "RUNNING");
        return ResponseEntity.ok(info);
    }

    @GetMapping("/beans")
    public ResponseEntity<List<String>> listBeans(@RequestParam(defaultValue = "service") String filter) {
        String[] beans = applicationContext.getBeanDefinitionNames();
        List<String> filtered = Arrays.stream(beans)
                .filter(name -> name.toLowerCase().contains(filter.toLowerCase()))
                .sorted()
                .toList();
        return ResponseEntity.ok(filtered);
    }

    @PostMapping("/orders/process")
    public ResponseEntity<Map<String, String>> processOrder(@RequestParam String orderId) {
        String result = orderService.processOrder(orderId);
        return ResponseEntity.ok(Map.of("orderId", orderId, "result", result, "status", "SUCCESS"));
    }

    @GetMapping("/reports/prototype-demo")
    public ResponseEntity<List<String>> demonstratePrototypeScope() {
        List<String> reports = reportCoordinatorService.produceTwoReports();
        return ResponseEntity.ok(reports);
    }
}
