package io.javahub.level3.javaplatformmodulesystemjpms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ModuleBoundaryValidatorTest {

    @Test
    @DisplayName("Should inspect JPMS module runtime descriptor")
    void testModuleInspection() {
        ModuleBoundaryValidator validator = new ModuleBoundaryValidator();
        ModuleBoundaryValidator.ModuleInfoData info = validator.inspectCurrentModule();

        assertThat(info).isNotNull();
        assertThat(info.moduleName()).isNotBlank();
    }
}
