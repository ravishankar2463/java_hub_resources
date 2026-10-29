package io.javahub.level4.advancedstreamscollectors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Advanced multi-level grouping and statistical aggregation with Java Collectors.
 */
public class AdvancedCollectorsAggregator {

    public record Transaction(String country, String currency, double amount) {}

    public Map<String, Double> sumAmountByCountry(List<Transaction> transactions) {
        return transactions.stream()
            .collect(Collectors.groupingBy(
                Transaction::country,
                Collectors.summingDouble(Transaction::amount)
            ));
    }

    public Map<Boolean, List<Transaction>> partitionHighValue(List<Transaction> transactions, double threshold) {
        return transactions.stream()
            .collect(Collectors.partitioningBy(tx -> tx.amount() >= threshold));
    }
}
