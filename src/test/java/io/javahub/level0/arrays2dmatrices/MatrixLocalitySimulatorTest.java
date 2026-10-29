package io.javahub.level0.arrays2dmatrices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MatrixLocalitySimulatorTest {

    private final MatrixLocalitySimulator simulator = new MatrixLocalitySimulator();

    @Test
    @DisplayName("Should calculate byte target address using base + (index * size)")
    void testAddressArithmetic() {
        long address = simulator.calculateTargetAddress(0x5010L, 3, 4);
        assertThat(address).isEqualTo(0x501CL);
    }

    @Test
    @DisplayName("Row-major and column-major traversals must yield identical sums")
    void testMatrixTraversalEquality() {
        int[][] matrix = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };

        long sumRow = simulator.sumRowMajor(matrix);
        long sumCol = simulator.sumColumnMajor(matrix);

        assertThat(sumRow).isEqualTo(45L);
        assertThat(sumCol).isEqualTo(45L);
    }

    @Test
    @DisplayName("System.arraycopy should copy contiguous memory segments quickly")
    void testSubArrayCopy() {
        int[] original = { 10, 20, 30, 40, 50 };
        int[] slice = simulator.copySubArray(original, 1, 3);

        assertThat(slice).containsExactly(20, 30, 40);
    }
}
