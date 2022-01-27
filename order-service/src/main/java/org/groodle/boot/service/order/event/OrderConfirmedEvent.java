package org.groodle.boot.service.order.event;

import lombok.Data;

@Data
public class OrderConfirmedEvent {

    private final String orderId;

    public OrderConfirmedEvent(String orderId) {
        this.orderId = orderId;
    }
}
