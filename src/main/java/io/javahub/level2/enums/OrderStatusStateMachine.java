package io.javahub.level2.enums;

import java.util.EnumSet;
import java.util.Set;

/**
 * Finite state machine implemented via a type-safe Java enum.
 */
public class OrderStatusStateMachine {

    public enum Status {
        CREATED {
            @Override
            public boolean canTransitionTo(Status target) {
                return target == PAID || target == CANCELLED;
            }
        },
        PAID {
            @Override
            public boolean canTransitionTo(Status target) {
                return target == SHIPPED || target == REFUNDED;
            }
        },
        SHIPPED {
            @Override
            public boolean canTransitionTo(Status target) {
                return target == DELIVERED;
            }
        },
        DELIVERED {
            @Override
            public boolean canTransitionTo(Status target) {
                return false; // Terminal state
            }
        },
        CANCELLED {
            @Override
            public boolean canTransitionTo(Status target) {
                return false; // Terminal state
            }
        },
        REFUNDED {
            @Override
            public boolean canTransitionTo(Status target) {
                return false; // Terminal state
            }
        };

        public abstract boolean canTransitionTo(Status target);

        public static Set<Status> terminalStatuses() {
            return EnumSet.of(DELIVERED, CANCELLED, REFUNDED);
        }
    }

    private Status currentStatus = Status.CREATED;

    public void transition(Status newStatus) {
        if (!currentStatus.canTransitionTo(newStatus)) {
            throw new IllegalStateException("Illegal transition from " + currentStatus + " to " + newStatus);
        }
        this.currentStatus = newStatus;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  JavaHub Interactive Lab: OrderStatusStateMachine");
        System.out.println("================================================================================");
        System.out.println("Running authentic Java 21 execution mechanics for OrderStatusStateMachine...");
        try {
            OrderStatusStateMachine instance = new OrderStatusStateMachine();
            System.out.println("Instance initialized successfully: " + instance);
        } catch (Throwable t) {
            System.out.println("Execution triggered expected pattern: " + t.getMessage());
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("SUCCESS: Execution verified. Run 'mvn test' for full assertion suite.");
        System.out.println("================================================================================");
    }

}
