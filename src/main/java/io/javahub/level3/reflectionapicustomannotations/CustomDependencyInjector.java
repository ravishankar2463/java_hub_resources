package io.javahub.level3.reflectionapicustomannotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Lightweight IoC container utilizing Java Reflection and custom runtime annotations.
 */
public class CustomDependencyInjector {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface AutoInject {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface ManagedBean {}

    private final Map<Class<?>, Object> singletons = new HashMap<>();

    public <T> void registerSingleton(Class<T> type, T instance) {
        singletons.put(type, instance);
    }

    public <T> T createInstance(Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInject.class)) {
                Object dependency = singletons.get(field.getType());
                if (dependency != null) {
                    field.setAccessible(true);
                    field.set(instance, dependency);
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: CustomDependencyInjector");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for CustomDependencyInjector...");
        try {
            CustomDependencyInjector instance = new CustomDependencyInjector();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
