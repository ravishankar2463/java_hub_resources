package io.javahub.level15.ipc;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IpcDemoApp {

    public static void main(String[] args) throws Exception {
        System.out.println("=========================================================");
        System.out.println("  Level 15: Ultra-Low-Latency Memory-Mapped IPC Demo    ");
        System.out.println("=========================================================\n");

        File tempFile = File.createTempFile("javahub_ipc_", ".dat");
        tempFile.deleteOnExit();

        try (RandomAccessFile raf = new RandomAccessFile(tempFile, "rw")) {
            long fileSize = 1024 * 1024; // 1MB shared memory file
            raf.setLength(fileSize);

            MappedByteBuffer buffer = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, fileSize);

            // Nanosecond IPC Write
            long start = System.nanoTime();
            buffer.putLong(0, 1001L); // Order ID
            buffer.putDouble(8, 450.25); // Price
            long elapsedNs = System.nanoTime() - start;

            System.out.println("Written off-heap IPC record to mapped memory in: " + elapsedNs + " nanoseconds!");
            System.out.println("Read back: OrderId=" + buffer.getLong(0) + ", Price=" + buffer.getDouble(8));
        }
    }
}
