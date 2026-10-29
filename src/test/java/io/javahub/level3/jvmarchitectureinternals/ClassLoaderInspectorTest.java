package io.javahub.level3.jvmarchitectureinternals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClassLoaderInspectorTest {

    private final ClassLoaderInspector inspector = new ClassLoaderInspector();

    @Test
    @DisplayName("Core Java classes like String should be loaded by Bootstrap ClassLoader (null)")
    void testBootstrapClassLoader() {
        String chain = inspector.getClassLoaderChain(String.class);
        assertThat(chain).contains("Bootstrap ClassLoader");
    }

    @Test
    @DisplayName("Application classes should be loaded by AppClassLoader")
    void testAppClassLoader() {
        String chain = inspector.getClassLoaderChain(ClassLoaderInspector.class);
        assertThat(chain).contains("AppClassLoader");
    }
}
