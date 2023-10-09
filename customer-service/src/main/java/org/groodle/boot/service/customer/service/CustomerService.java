package org.groodle.boot.service.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.event.CustomerDeletionEvent;
import org.groodle.boot.service.customer.model.Customer;
import org.groodle.boot.service.customer.repository.CustomerRepository;
import org.groodle.boot.service.customer.web.errors.CustomerNotFoundException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerDeletionEventProducerService customerDeletionEventProducerService;

    public CustomerService(CustomerRepository customerRepository, CustomerDeletionEventProducerService customerDeletionEventProducerService) {
        this.customerRepository = customerRepository;
        this.customerDeletionEventProducerService = customerDeletionEventProducerService;
    }

    @Cacheable(value = "Customer")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Cacheable(value = "Customer", key = "#id")
    public Customer getById(Long id) {
        log.info("method: getById(id)");
        log.debug("Requested Customer Id:{}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @CachePut(value = "Customer", key = "#customer.id")
    public Customer create(Customer customer) {
        log.info("method:create(customer)");
        log.debug("Customer:{}", customer);
        return customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        log.info("deleteById({})", id);
        customerDeletionEventProducerService.produce(CustomerDeletionEvent.builder().id(id).build());
    }
}
