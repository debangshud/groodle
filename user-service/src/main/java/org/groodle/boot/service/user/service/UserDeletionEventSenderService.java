package org.groodle.boot.service.user.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.event.UserDeletionEvent;
import org.groodle.boot.service.user.web.vm.UserDeleteResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.groodle.boot.service.user.web.vm.Status.SUCCESS;

@Service
@Slf4j
public class UserDeletionEventSenderService {

    @Value("${rabbitmq.exchange:exchange.user}")
    private String exchange;
    @Value("${rabbitmq.routingKey:routingKey.user}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public UserDeletionEventSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserDeleteResponse send(String userId) {
        log.info("Publishing message...");
        rabbitTemplate.convertAndSend(exchange, routingKey, UserDeletionEvent.builder().userId(userId).build());
        log.info("Message published");
        return UserDeleteResponse.builder().status(SUCCESS).build();
    }
}
