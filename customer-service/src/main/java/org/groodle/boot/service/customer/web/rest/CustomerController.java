package org.groodle.boot.service.customer.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.repository.CustomerRepository;
import org.groodle.boot.service.customer.web.errors.CustomerNotFoundException;
import org.groodle.boot.service.customer.web.vm.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Value("${test.prop}")
    private String testProp;

    @GetMapping("/")
    public List<Customer> getAll() {
        log.info("method:getAll()");
        log.info("Test Property: {}",testProp);

        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable String id) {
        log.info("method: getById(id)");
        log.debug("Requested Customer Id:{}", id);
        log.info("Test Property: {}",testProp);
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @PostMapping("/")
    public Customer create(@RequestBody Customer customer) {
        log.info("post");
        log.debug("Customer:{}", customer);
        return customerRepository.save(customer);
    }
}
