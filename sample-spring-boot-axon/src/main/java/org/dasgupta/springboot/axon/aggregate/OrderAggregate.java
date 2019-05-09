package org.dasgupta.springboot.axon.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.dasgupta.springboot.axon.command.ConfirmOrderCommand;
import org.dasgupta.springboot.axon.command.PlaceOrderCommand;
import org.dasgupta.springboot.axon.command.ShipOrderCommand;
import org.dasgupta.springboot.axon.event.OrderConfirmedEvent;
import org.dasgupta.springboot.axon.event.OrderPlacedEvent;
import org.dasgupta.springboot.axon.event.OrderShippedEvent;

@Slf4j
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    protected OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        log.info("OrderAggregate(PlaceOrderCommand)");
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!orderConfirmed) {
            throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
        }
        apply(new OrderShippedEvent(orderId));
    }


    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        log.info("on(OrderPlacedEvent event)");
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }


    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }
}
