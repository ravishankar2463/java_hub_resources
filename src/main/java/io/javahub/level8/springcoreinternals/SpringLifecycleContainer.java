package io.javahub.level8.springcoreinternals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Emulates Spring Framework IoC container and BeanPostProcessor dynamic proxy wrapping.
 */
public class SpringLifecycleContainer {

    public interface OrderService {
        String processOrder(String orderId);
    }

    public static class OrderServiceImpl implements OrderService {
        @Override
        public String processOrder(String orderId) {
            return "ORDER_PROCESSED: " + orderId;
        }
    }

    // Emulates BeanPostProcessor wrapping raw bean into transactional proxy
    public static class TransactionalProxyFactory {
        @SuppressWarnings("unchecked")
        public static <T> T wrapInProxy(Class<T> interfaceType, T rawInstance) {
            return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // Simulated @Transactional BEGIN
                        Object result = method.invoke(rawInstance, args);
                        // Simulated @Transactional COMMIT
                        return result;
                    }
                }
            );
        }
    }
}
