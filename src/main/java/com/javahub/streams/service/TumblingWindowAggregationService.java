package com.javahub.streams.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class TumblingWindowAggregationService {
    public record WindowSummary(String key, long windowStart, long windowEnd, double totalSum, int count) {}
    private final Map<String, List<Double>> windowBucket = new ConcurrentHashMap<>();
    public synchronized WindowSummary recordTrade(String ticker, double amount) {
        windowBucket.computeIfAbsent(ticker, k -> new ArrayList<>()).add(amount);
        List<Double> list = windowBucket.get(ticker);
        double sum = list.stream().mapToDouble(Double::doubleValue).sum();
        long now = System.currentTimeMillis();
        return new WindowSummary(ticker, now - 60000, now, sum, list.size());
    }
    public Map<String, List<Double>> getWindows() { return windowBucket; }
}
