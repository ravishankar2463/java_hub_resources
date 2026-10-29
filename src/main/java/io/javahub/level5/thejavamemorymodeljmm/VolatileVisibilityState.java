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
}
