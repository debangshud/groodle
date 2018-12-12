package org.acacia.sample.springboot.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AirbnbInventoryUpdateListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "${airbnb.queue}")
    public void receive(AirbnbInventoryUpdateMessage message) {
        logger.info("Received message: {}", message);
    }
}
