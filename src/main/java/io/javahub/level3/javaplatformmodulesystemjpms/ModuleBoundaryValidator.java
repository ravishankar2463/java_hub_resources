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
}
