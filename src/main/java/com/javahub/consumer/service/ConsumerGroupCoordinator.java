package com.javahub.consumer.service;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class ConsumerGroupCoordinator {
    public record MemberAssignment(String memberId, List<Integer> assignedPartitions) {}
    private final Set<String> activeMembers = ConcurrentHashMap.newKeySet();
    private final int totalPartitions = 6;
    public synchronized List<MemberAssignment> registerMember(String memberId) {
        activeMembers.add(memberId);
        return computeAssignments();
    }
    public synchronized List<MemberAssignment> removeMember(String memberId) {
        activeMembers.remove(memberId);
        return computeAssignments();
    }
    public List<MemberAssignment> computeAssignments() {
        List<String> sorted = new ArrayList<>(activeMembers);
        Collections.sort(sorted);
        List<MemberAssignment> result = new ArrayList<>();
        if (sorted.isEmpty()) return result;
        for (int i = 0; i < sorted.size(); i++) {
            List<Integer> parts = new ArrayList<>();
            for (int p = 0; p < totalPartitions; p++) {
                if (p % sorted.size() == i) parts.add(p);
            }
            result.add(new MemberAssignment(sorted.get(i), parts));
        }
        return result;
    }
}
