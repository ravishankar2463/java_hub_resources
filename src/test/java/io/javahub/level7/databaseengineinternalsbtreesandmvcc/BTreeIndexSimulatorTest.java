package io.javahub.level7.databaseengineinternalsbtreesandmvcc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BTreeIndexSimulatorTest {

    private final BTreeIndexSimulator sim = new BTreeIndexSimulator();

    @Test
    @DisplayName("BTreeNode should detect capacity overflow triggering page split")
    void testBTreePageSplit() {
        var node = new BTreeIndexSimulator.BTreeNode(3);
        assertThat(node.insert(10)).isTrue();
        assertThat(node.insert(20)).isTrue();
        assertThat(node.insert(30)).isTrue();
        assertThat(node.insert(40)).isFalse(); // Exceeds capacity 3
    }

    @Test
    @DisplayName("MVCC should correctly evaluate snapshot visibility rules")
    void testMvccVisibility() {
        var version = new BTreeIndexSimulator.RowVersion(100L, 0L, "Active record");

        assertThat(sim.isVersionVisible(version, 105L)).isTrue();
        assertThat(sim.isVersionVisible(version, 90L)).isFalse(); // Created in future relative to tx 90
    }
}
