package io.javahub.level2.exceptionhandling;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates try-with-resources and suppressed exception extraction.
 */
public class ResilientFileReader {

    public static class FaultyResource implements Closeable {
        private final String name;
        private final boolean failOnClose;

        public FaultyResource(String name, boolean failOnClose) {
            this.name = name;
            this.failOnClose = failOnClose;
        }

        public void doWork(boolean throwInWork) throws IOException {
            if (throwInWork) {
                throw new IOException("Primary failure in resource work: " + name);
            }
        }

        @Override
        public void close() throws IOException {
            if (failOnClose) {
                throw new IOException("Secondary close failure in: " + name);
            }
        }
    }

    public List<String> processWithSuppressedHandling() {
        List<String> recordedExceptions = new ArrayList<>();
        try (FaultyResource r = new FaultyResource("PrimaryDatabase", true)) {
            r.doWork(true);
        } catch (IOException primaryEx) {
            recordedExceptions.add("Primary: " + primaryEx.getMessage());
            for (Throwable suppressed : primaryEx.getSuppressed()) {
                recordedExceptions.add("Suppressed: " + suppressed.getMessage());
            }
        }
        return recordedExceptions;
    }
}
