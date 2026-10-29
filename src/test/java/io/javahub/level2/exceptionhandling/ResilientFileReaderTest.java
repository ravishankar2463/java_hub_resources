package io.javahub.level2.exceptionhandling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ResilientFileReaderTest {

    @Test
    @DisplayName("Try-with-resources should preserve secondary close exceptions via getSuppressed()")
    void testSuppressedExceptions() {
        ResilientFileReader reader = new ResilientFileReader();
        List<String> errors = reader.processWithSuppressedHandling();

        assertThat(errors).hasSize(2);
        assertThat(errors.get(0)).contains("Primary: Primary failure");
        assertThat(errors.get(1)).contains("Suppressed: Secondary close failure");
    }
}
