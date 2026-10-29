package io.javahub.level4.lambdasandfunctionalinterfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 4: Lambdas and Functional Interfaces.
 */
public class LambdasandfunctionalinterfacesLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        LambdasandfunctionalinterfacesLab lab = new LambdasandfunctionalinterfacesLab();

        assertThat(lab.getLevel()).isEqualTo(4);
        assertThat(lab.getTopicTitle()).isEqualTo("Lambdas and Functional Interfaces");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
