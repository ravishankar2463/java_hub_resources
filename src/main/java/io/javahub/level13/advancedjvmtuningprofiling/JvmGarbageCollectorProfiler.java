package io.javahub.level13.advancedjvmtuningprofiling;

/**
 * Analyzes JVM memory allocation rates and GC pause SLAs.
 */
public class JvmGarbageCollectorProfiler {

    public record GcPauseReport(long pauseDurationMillis, long maxAllowedSlaMillis) {
        public boolean isSlaBreached() {
            return pauseDurationMillis > maxAllowedSlaMillis;
        }
    }
}
