package org.dasgupta.springboot.axon.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class PlaceOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;
    private final String product;

    public PlaceOrderCommand(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
    }
}
