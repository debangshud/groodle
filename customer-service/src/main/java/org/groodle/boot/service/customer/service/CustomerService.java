package org.groodle.boot.service.customer.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.repository.CustomerRepository;
import org.groodle.boot.service.customer.web.errors.CustomerNotFoundException;
import org.groodle.boot.service.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Cacheable(value="Customer")
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Cacheable(value="Customer", key="#id")
    public Customer getById(@PathVariable Long id){
        log.info("method: getById(id)");
        log.debug("Requested Customer Id:{}", id);
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @CachePut(value="Customer", key="#customer.id")
    public Customer create(@RequestBody Customer customer) {
        log.info("method:create(customer)");
        log.debug("Customer:{}", customer);
        return repository.save(customer);
    }
}
