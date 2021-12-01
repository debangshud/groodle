package org.groodle.boot.service.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.event.CustomerDeletionEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static org.groodle.boot.service.customer.service.QueueDestination.CUSTOMER_DELETION;

@Service
@Slf4j
public class CustomerDeletionEventProducerService {

    private JmsTemplate jmsTemplate;

    public CustomerDeletionEventProducerService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void produce(CustomerDeletionEvent message){
        log.info("sending message='{}'", message);
        jmsTemplate.convertAndSend(CUSTOMER_DELETION, message);
    }
}
