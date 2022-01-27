package org.groodle.boot.service.order.event;

import lombok.Data;

@Data
public class OrderPlacedEvent {
    private final String orderId;
    private final String product;

    public OrderPlacedEvent(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
    }
}
