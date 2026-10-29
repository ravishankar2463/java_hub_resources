package io.javahub.level10.kafkaconsumergroupsandrebalances;

import java.util.*;

/**
 * Emulates Kafka CooperativeStickyAssignor partition assignment across active consumers.
 */
public class RebalancePartitionAssignor {

    public Map<String, List<Integer>> assignPartitions(List<String> consumers, int totalPartitions) {
        Map<String, List<Integer>> assignments = new HashMap<>();
        for (String c : consumers) {
            assignments.put(c, new ArrayList<>());
        }

        for (int p = 0; p < totalPartitions; p++) {
            String targetConsumer = consumers.get(p % consumers.size());
            assignments.get(targetConsumer).add(p);
        }
        return assignments;
    }
}
