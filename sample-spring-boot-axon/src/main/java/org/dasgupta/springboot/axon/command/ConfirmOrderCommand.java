package org.dasgupta.springboot.axon.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ConfirmOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    public ConfirmOrderCommand(String orderId) {
        this.orderId = orderId;
    }
}
