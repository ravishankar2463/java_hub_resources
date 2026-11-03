package io.javahub.level15.panama;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class OffHeapBufferManager implements AutoCloseable {

    private final Arena arena;
    private final MemorySegment segment;
    private final long capacity;

    public OffHeapBufferManager(long elementCount) {
        this.capacity = elementCount;
        this.arena = Arena.ofShared();
        this.segment = arena.allocate(ValueLayout.JAVA_LONG, elementCount);
    }

    public void put(long index, long value) {
        if (index < 0 || index >= capacity) throw new IndexOutOfBoundsException();
        segment.setAtIndex(ValueLayout.JAVA_LONG, index, value);
    }

    public long get(long index) {
        if (index < 0 || index >= capacity) throw new IndexOutOfBoundsException();
        return segment.getAtIndex(ValueLayout.JAVA_LONG, index);
    }

    public long getByteSize() {
        return segment.byteSize();
    }

    @Override
    public void close() {
        arena.close();
    }
}
