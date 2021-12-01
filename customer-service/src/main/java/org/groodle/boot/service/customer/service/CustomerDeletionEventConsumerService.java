package org.groodle.boot.service.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.event.CustomerDeletionEvent;
import org.groodle.boot.service.customer.repository.CustomerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static org.groodle.boot.service.customer.service.QueueDestination.CUSTOMER_DELETION;

@Component
@Slf4j
public class CustomerDeletionEventConsumerService {

    private CustomerRepository customerRepository;

    public CustomerDeletionEventConsumerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @JmsListener(destination = CUSTOMER_DELETION)
    @CacheEvict(value = "Customer", allEntries = true)
    public void consume(final CustomerDeletionEvent event) throws InterruptedException {
        log.info("Message: {}", event);
        customerRepository.deleteById(event.getId());
        log.info("Customer {} deleted", event.getId());
    }
}
