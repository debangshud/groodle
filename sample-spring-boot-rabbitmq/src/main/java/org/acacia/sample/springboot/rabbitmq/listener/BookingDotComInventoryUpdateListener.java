package org.dasgupta.sample.springboot.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BookingDotComInventoryUpdateListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "${booking.queue}")
    public void receive(BookingInventoryUpdateMessage message) {
        logger.info("Received message: {}", message);
    }
}
