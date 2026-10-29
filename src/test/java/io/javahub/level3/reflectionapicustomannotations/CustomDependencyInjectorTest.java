package io.javahub.level3.reflectionapicustomannotations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CustomDependencyInjectorTest {

    public interface DatabaseService {
        String query();
    }

    public static class MockDatabase implements DatabaseService {
        public String query() { return "Connected to mock DB"; }
    }

    public static class OrderController {
        @CustomDependencyInjector.AutoInject
        private DatabaseService db;

        public String handle() {
            return db.query();
        }
    }

    @Test
    @DisplayName("Reflection container should inject dependencies into annotated fields")
    void testReflectionInjection() throws Exception {
        CustomDependencyInjector injector = new CustomDependencyInjector();
        injector.registerSingleton(DatabaseService.class, new MockDatabase());

        OrderController controller = injector.createInstance(OrderController.class);

        assertThat(controller.handle()).isEqualTo("Connected to mock DB");
    }
}
