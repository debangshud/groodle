package org.acacia.sample.springboot.rabbitmq.sender;

import org.acacia.sample.springboot.rabbitmq.listener.BookingInventoryUpdateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledBookingInventoryUpdateMessagePublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${booking.exchange}")
    private String exchange;

    @Value("${booking.routingkey}")
    private String routingKey;

    @Scheduled(fixedRate = 1000)
    public void publish() {
        logger.info("Building message...");
        BookingInventoryUpdateMessage message = new BookingInventoryUpdateMessage();
        message.setMessage("booking.com");
        logger.info("Publishing message...");
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        logger.info("Message published");
    }

}
