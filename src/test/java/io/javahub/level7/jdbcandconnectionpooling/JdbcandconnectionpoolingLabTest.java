package io.javahub.level7.jdbcandconnectionpooling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 7: JDBC and Connection Pooling.
 */
public class JdbcandconnectionpoolingLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        JdbcandconnectionpoolingLab lab = new JdbcandconnectionpoolingLab();

        assertThat(lab.getLevel()).isEqualTo(7);
        assertThat(lab.getTopicTitle()).isEqualTo("JDBC and Connection Pooling");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
