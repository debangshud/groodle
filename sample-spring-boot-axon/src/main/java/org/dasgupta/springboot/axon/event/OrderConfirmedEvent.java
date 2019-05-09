package org.dasgupta.springboot.axon.event;

import lombok.Data;

@Data
public class OrderConfirmedEvent {

    private final String orderId;

    public OrderConfirmedEvent(String orderId) {
        this.orderId = orderId;
    }
}
