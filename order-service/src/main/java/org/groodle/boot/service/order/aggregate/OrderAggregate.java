package org.groodle.boot.service.order.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.groodle.boot.service.order.command.ConfirmOrderCommand;
import org.groodle.boot.service.order.command.ShipOrderCommand;
import org.groodle.boot.service.order.command.PlaceOrderCommand;
import org.groodle.boot.service.order.event.OrderConfirmedEvent;
import org.groodle.boot.service.order.event.OrderPlacedEvent;
import org.groodle.boot.service.order.event.OrderShippedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

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
        log.info("PlaceOrderCommand");
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        log.info("ConfirmOrderCommand");
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        log.info("ShipOrderCommand");
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
