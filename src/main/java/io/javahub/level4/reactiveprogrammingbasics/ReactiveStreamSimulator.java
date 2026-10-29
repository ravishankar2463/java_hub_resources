package io.javahub.level4.reactiveprogrammingbasics;

import java.util.ArrayList;
import java.util.List;

/**
 * Flow control simulation adhering to Reactive Streams backpressure specifications.
 */
public class ReactiveStreamSimulator {

    public interface Subscriber<T> {
        void onNext(T item);
        void onComplete();
    }

    public static class SimplePublisher<T> {
        private final List<T> items;

        public SimplePublisher(List<T> items) {
            this.items = items;
        }

        public void subscribe(Subscriber<T> subscriber, int requestDemand) {
            int toEmit = Math.min(requestDemand, items.size());
            for (int i = 0; i < toEmit; i++) {
                subscriber.onNext(items.get(i));
            }
            if (toEmit == items.size()) {
                subscriber.onComplete();
            }
        }
    }
}
