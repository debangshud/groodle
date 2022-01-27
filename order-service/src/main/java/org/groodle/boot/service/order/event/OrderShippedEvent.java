package org.groodle.boot.service.order.event;

import lombok.Data;

@Data
public class OrderShippedEvent {

    private final String orderId;

    public OrderShippedEvent(String orderId) {
        this.orderId = orderId;
    }
}
