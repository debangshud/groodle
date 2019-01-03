package org.dasgupta.sample.springboot.rabbitmq.sender;

import org.dasgupta.sample.springboot.rabbitmq.listener.AirbnbInventoryUpdateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledAirbnbInventoryUpdateMessagePublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${airbnb.exchange}")
    private String exchange;

    @Value("${airbnb.routingkey}")
    private String routingKey;

    @Scheduled(fixedRate = 1000)
    public void publish() {
        logger.info("Building message...");
        AirbnbInventoryUpdateMessage message = new AirbnbInventoryUpdateMessage();
        message.setMessage("airbnb.com");
        logger.info("Publishing message...");
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        logger.info("Message published");
    }
}
