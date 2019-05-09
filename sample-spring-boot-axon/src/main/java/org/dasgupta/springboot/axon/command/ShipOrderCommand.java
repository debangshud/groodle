package org.dasgupta.springboot.axon.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    public ShipOrderCommand(String orderId) {
        this.orderId = orderId;
    }
}
