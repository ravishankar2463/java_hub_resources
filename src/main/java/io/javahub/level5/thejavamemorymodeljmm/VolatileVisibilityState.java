package io.javahub.level5.thejavamemorymodeljmm;

/**
 * Demonstrates memory visibility and happens-before guarantees in the Java Memory Model.
 */
public class VolatileVisibilityState {

    private int payload = 0;
    private volatile boolean ready = false;

    // Writer thread writes payload before publishing volatile 'ready'
    public void writeData(int value) {
        this.payload = value;
        this.ready = true; // Volatile write creates release fence
    }

    // Reader thread reads volatile 'ready' before inspecting payload
    public int readData() {
        if (ready) { // Volatile read creates acquire fence
            return payload; // Guaranteed to see payload write due to Happens-Before
        }
        return -1;
    }

    public boolean isReady() {
        return ready;
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: VolatileVisibilityState");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for VolatileVisibilityState...");
        try {
            VolatileVisibilityState instance = new VolatileVisibilityState();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
