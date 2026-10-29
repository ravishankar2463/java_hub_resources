package com.javahub.testing.controller;

import com.javahub.testing.service.TaxCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tax")
public class TaxController {

    private final TaxCalculatorService taxService;

    public TaxController(TaxCalculatorService taxService) {
        this.taxService = taxService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculate(
            @RequestParam double amount,
            @RequestParam(defaultValue = "0.20") double rate) {
        double vat = taxService.calculateVat(amount, rate);
        return ResponseEntity.ok(Map.of(
            "subtotal", amount,
            "rate", rate,
            "vat", vat,
            "total", amount + vat
        ));
    }
}
