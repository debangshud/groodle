package org.groodle.boot.service.order.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.groodle.boot.service.order.command.ConfirmOrderCommand;
import org.groodle.boot.service.order.command.PlaceOrderCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;

    public OrderRestEndpoint(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/ship-order")
    public void shipOrder(){
        log.info("ship-order ...");
        final String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        commandGateway.send(new ConfirmOrderCommand(orderId));
//        commandGateway.send(new ShipOrderCommand(orderId));
    }
}
