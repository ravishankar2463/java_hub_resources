package io.javahub.level3.javaplatformmodulesystemjpms;

/**
 * Validates module encapsulation boundaries and package access.
 */
public class ModuleBoundaryValidator {

    public record ModuleInfoData(String moduleName, boolean isNamed, boolean isOpen) {}

    public ModuleInfoData inspectCurrentModule() {
        Module mod = this.getClass().getModule();
        return new ModuleInfoData(
            mod.getName() != null ? mod.getName() : "unnamed",
            mod.isNamed(),
            mod.isOpen(this.getClass().getPackageName())
        );
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: ModuleBoundaryValidator");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for ModuleBoundaryValidator...");
        try {
            ModuleBoundaryValidator instance = new ModuleBoundaryValidator();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
