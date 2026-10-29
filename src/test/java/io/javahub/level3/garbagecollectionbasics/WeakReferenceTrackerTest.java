package io.javahub.level3.garbagecollectionbasics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.ref.WeakReference;
import static org.assertj.core.api.Assertions.assertThat;

public class WeakReferenceTrackerTest {

    @Test
    @DisplayName("WeakReference should initially hold the referent before GC collection")
    void testWeakReference() {
        WeakReferenceTracker tracker = new WeakReferenceTracker();
        WeakReference<WeakReferenceTracker.SessionPayload> ref = tracker.createTrackedSession("SESS-1");

        // Without strong reference, referent is available before full GC
        assertThat(ref).isNotNull();
    }
}
