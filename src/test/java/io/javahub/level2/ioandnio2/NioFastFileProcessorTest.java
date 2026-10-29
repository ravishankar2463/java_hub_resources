package io.javahub.level2.ioandnio2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

public class NioFastFileProcessorTest {

    private final NioFastFileProcessor processor = new NioFastFileProcessor();

    @Test
    @DisplayName("Should write and read file using NIO.2 and direct ByteBuffer")
    void testNioBufferRoundtrip(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("nio-test.txt");
        String content = "Zero-Copy High-Throughput I/O with Java 21";

        processor.writeAndReadBuffer(file, content);
        String readBack = processor.readViaDirectBuffer(file);

        assertThat(readBack).isEqualTo(content);
    }
}
