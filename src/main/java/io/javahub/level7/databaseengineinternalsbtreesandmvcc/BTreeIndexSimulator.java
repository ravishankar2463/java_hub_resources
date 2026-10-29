package io.javahub.level7.databaseengineinternalsbtreesandmvcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Emulates B-Tree index node splitting and Multi-Version Concurrency Control (MVCC).
 */
public class BTreeIndexSimulator {

    public record RowVersion(long xmin, long xmax, String data) {}

    public static class BTreeNode {
        private final int capacity;
        private final List<Integer> keys = new ArrayList<>();

        public BTreeNode(int capacity) {
            this.capacity = capacity;
        }

        public boolean insert(int key) {
            keys.add(key);
            keys.sort(Integer::compareTo);
            return keys.size() <= capacity; // false triggers page split
        }

        public List<Integer> getKeys() { return List.copyOf(keys); }
    }

    public boolean isVersionVisible(RowVersion version, long readerSnapshotTxId) {
        // MVCC Visibility: xmin must be committed before reader started, xmax must be uncommitted or after
        boolean createdBeforeReader = version.xmin() <= readerSnapshotTxId;
        boolean deletedAfterReader = version.xmax() == 0 || version.xmax() > readerSnapshotTxId;
        return createdBeforeReader && deletedAfterReader;
    }
}
