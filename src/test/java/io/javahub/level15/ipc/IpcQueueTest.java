package io.javahub.level15.ipc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static org.assertj.core.api.Assertions.assertThat;

class IpcQueueTest {

    @Test
    @DisplayName("Verify memory-mapped buffer IPC read and write speed")
    void shouldWriteAndReadMappedMemory() throws Exception {
        File temp = File.createTempFile("test_ipc", ".dat");
        temp.deleteOnExit();

        try (RandomAccessFile raf = new RandomAccessFile(temp, "rw")) {
            raf.setLength(4096);
            MappedByteBuffer map = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, 4096);

            map.putLong(0, 888999111L);
            assertThat(map.getLong(0)).isEqualTo(888999111L);
        }
    }
}
