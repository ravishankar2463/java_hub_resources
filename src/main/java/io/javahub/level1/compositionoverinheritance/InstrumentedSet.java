package io.javahub.level1.compositionoverinheritance;

import java.util.Collection;
import java.util.Set;

/**
 * Forwarding wrapper (Decorator) cleanly tracking added elements without inheriting fragile parent internals.
 */
public class InstrumentedSet<E> {

    private final Set<E> delegate;
    private int addCount = 0;

    public InstrumentedSet(Set<E> delegate) {
        this.delegate = delegate;
    }

    public boolean add(E element) {
        addCount++;
        return delegate.add(element);
    }

    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return delegate.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public int size() {
        return delegate.size();
    }
}
