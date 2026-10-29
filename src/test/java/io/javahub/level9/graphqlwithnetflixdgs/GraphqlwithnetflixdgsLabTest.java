package io.javahub.level9.graphqlwithnetflixdgs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verified test suite for Level 9: GraphQL with Netflix DGS.
 */
public class GraphqlwithnetflixdgsLabTest {

    @Test
    @DisplayName("Verify that lab initialization and execution succeeds")
    void testLabExecution() {
        GraphqlwithnetflixdgsLab lab = new GraphqlwithnetflixdgsLab();

        assertThat(lab.getLevel()).isEqualTo(9);
        assertThat(lab.getTopicTitle()).isEqualTo("GraphQL with Netflix DGS");
        assertThat(lab.execute()).contains("Executing JavaHub Lab");
    }
}
