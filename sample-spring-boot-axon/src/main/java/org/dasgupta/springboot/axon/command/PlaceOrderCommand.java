package org.dasgupta.springboot.axon.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class PlaceOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId = null;
    private final String product = null;


}
