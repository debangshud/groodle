package org.dasgupta.springboot.axon.event;

public class OrderConfirmedEvent {

    private final String orderId;

    public OrderConfirmedEvent(String orderId) {
        this.orderId = orderId;
    }
}
