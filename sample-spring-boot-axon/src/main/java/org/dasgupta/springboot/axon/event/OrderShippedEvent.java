package org.dasgupta.springboot.axon.event;

public class OrderShippedEvent {

    private final String orderId;

    public OrderShippedEvent(String orderId) {
        this.orderId = orderId;
    }
}
