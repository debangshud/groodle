package org.dasgupta.springboot.axon.aggregate;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.dasgupta.springboot.axon.command.PlaceOrderCommand;
import org.dasgupta.springboot.axon.event.OrderPlacedEvent;

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
        log.info("OrderAggregate(PlaceOrderCommand command)");
//        try {
//            Thread.sleep(50000);
//        }catch (InterruptedException e){
//            log.error("",e);
//        }
        AggregateLifecycle.apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        log.info("on(OrderPlacedEvent event)");
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }
}
