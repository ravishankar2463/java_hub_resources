package io.javahub.level1.memoryreferencescopying;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

public class PassByValueProofTest {

    @Test
    @DisplayName("Parameter reassignment must NOT alter caller's reference variable")
    void testPassByValueReassignment() {
        PassByValueProof.Car original = new PassByValueProof.Car("Red");
        PassByValueProof.attemptReassign(original);

        assertThat(original.getColor()).isEqualTo("Red");
    }

    @Test
    @DisplayName("Calling setter on reference parameter must alter shared heap object")
    void testPassByValueMutation() {
        PassByValueProof.Car original = new PassByValueProof.Car("Red");
        PassByValueProof.mutateColor(original, "Green");

        assertThat(original.getColor()).isEqualTo("Green");
    }

    @Test
    @DisplayName("Defensive copies must prevent external date mutation from corrupting state")
    void testDefensiveCopy() {
        Date start = new Date(1000L);
        Date end = new Date(5000L);

        PassByValueProof.SecurePeriod period = new PassByValueProof.SecurePeriod(start, end);

        // Mutate original external date
        start.setTime(999999L);
        assertThat(period.getStart().getTime()).isEqualTo(1000L);

        // Mutate returned date
        Date retrieved = period.getStart();
        retrieved.setTime(888888L);
        assertThat(period.getStart().getTime()).isEqualTo(1000L);
    }
}
