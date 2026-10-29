package io.javahub.level3.jvmarchitectureinternals;

/**
 * Inspects runtime ClassLoader hierarchy and delegation models.
 */
public class ClassLoaderInspector {

    public String getClassLoaderChain(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        ClassLoader cl = clazz.getClassLoader();
        if (cl == null) {
            return "Bootstrap ClassLoader (null)";
        }

        while (cl != null) {
            sb.append(cl.getClass().getName());
            cl = cl.getParent();
            if (cl != null) sb.append(" -> ");
            else sb.append(" -> Bootstrap ClassLoader");
        }
        return sb.toString();
    }
}
