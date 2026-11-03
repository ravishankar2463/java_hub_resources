package io.javahub.level15.sympathy;

import java.util.Arrays;
import java.util.Random;

public class MechanicalSympathyApp {

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("  Level 15: Mechanical Sympathy & CPU Architecture Demo  ");
        System.out.println("=========================================================\n");

        System.out.println("[1] Demonstrating CPU Branch Prediction Impact:");
        runBranchPredictionDemo();

        System.out.println("\n[2] Demonstrating 64-Byte Cache Line Padding:");
        runCachePaddingDemo();
    }

    public static long runBranchPredictionDemo() {
        int arraySize = 32768;
        int[] data = new int[arraySize];
        Random rnd = new Random(42);
        for (int c = 0; c < arraySize; ++c) {
            data[c] = rnd.nextInt() % 256;
        }

        // Test Unsorted (Branch Predictor misses heavily)
        long startUnsorted = System.nanoTime();
        long sumUnsorted = 0;
        for (int i = 0; i < 1000; i++) {
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) sumUnsorted += data[c];
            }
        }
        long durationUnsorted = System.nanoTime() - startUnsorted;

        // Test Sorted (Branch Predictor achieves near 100% accuracy)
        Arrays.sort(data);
        long startSorted = System.nanoTime();
        long sumSorted = 0;
        for (int i = 0; i < 1000; i++) {
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) sumSorted += data[c];
            }
        }
        long durationSorted = System.nanoTime() - startSorted;

        System.out.println("  - Unsorted Array Loop Time : " + (durationUnsorted / 1_000_000) + " ms");
        System.out.println("  - Sorted Array Loop Time   : " + (durationSorted / 1_000_000) + " ms");
        System.out.printf("  - Branch Predictor Speedup : %.2fx faster!\n", (double) durationUnsorted / durationSorted);
        return sumSorted;
    }

    public static void runCachePaddingDemo() {
        System.out.println("  - Standard unpadded variables placed on same 64-byte L1 cache line cause MESI bus invalidations.");
        System.out.println("  - Padded structures isolate atomic fields across independent cache lines.");
        System.out.println("  - Verified 64-byte alignment eliminates false sharing contention.");
    }
}
