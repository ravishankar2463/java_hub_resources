package io.javahub.level8.buildingrestfulapis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProblemDetailResponseBuilderTest {

    @Test
    @DisplayName("Should build RFC 7807 ProblemDetail with correct status and error properties")
    void testProblemDetail() {
        var pd = ProblemDetailResponseBuilder.ProblemDetail.forNotFound("Order", "99");

        assertThat(pd.status()).isEqualTo(404);
        assertThat(pd.title()).isEqualTo("Entity Not Found");
        assertThat(pd.detail()).contains("Order with ID 99");
        assertThat(pd.properties()).containsEntry("errorCode", "ERR_404_NOT_FOUND");
    }
}
