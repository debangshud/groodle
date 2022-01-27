package org.groodle.boot.service.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000)
    public void publishToDirectExchange() {
        log.info("Triggered");
    }

    @Scheduled(fixedRate = 1000)
    public void publishToTopicExchange() {
        log.info("triggered");
    }
}
