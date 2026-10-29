package io.javahub.level0.arrays2dmatrices;

/**
 * Demonstrates contiguous memory layouts, L1 cache line exploitation, and arraycopy.
 */
public class MatrixLocalitySimulator {

    public long calculateTargetAddress(long baseAddress, int index, int dataTypeBytes) {
        return baseAddress + ((long) index * dataTypeBytes);
    }

    public long sumRowMajor(int[][] matrix) {
        long sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sum += matrix[row][col];
            }
        }
        return sum;
    }

    public long sumColumnMajor(int[][] matrix) {
        long sum = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                sum += matrix[row][col];
            }
        }
        return sum;
    }

    public int[] copySubArray(int[] source, int start, int length) {
        int[] destination = new int[length];
        System.arraycopy(source, start, destination, 0, length);
        return destination;
    }
}
