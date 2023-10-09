package org.groodle.boot.service.user.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.user.event.UserDeletionEvent;
import org.groodle.boot.service.user.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
@Transactional
public class UserDeletionEventListenerService {

    private final UserRepository userRepository;

    public UserDeletionEventListenerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RabbitListener(queues = "${rabbitmq.queue:queue.user}")
    public void receive(UserDeletionEvent event) {
        log.info("Received message: {}", event);
        String userId = event.getUserId();
        Objects.requireNonNull(userId, "User ID cannot be null");
        userRepository.deleteByUsername(userId);
        log.info("User deleted: {}",userId);
    }
}
