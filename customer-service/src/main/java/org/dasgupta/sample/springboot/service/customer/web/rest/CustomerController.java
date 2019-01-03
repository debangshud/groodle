package org.dasgupta.sample.springboot.service.customer.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.dasgupta.sample.springboot.service.customer.repository.CustomerRepository;
import org.dasgupta.sample.springboot.service.customer.web.errors.CustomerNotFoundException;
import org.dasgupta.sample.springboot.service.customer.web.vm.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Value("${test.prop}")
    private String testProp;

    @GetMapping("/{id}")
    public Customer get(@PathVariable String id) {
        log.info("get");
        log.debug("Requested Customer Id:{}", id);
        log.info("Test Property: {}",testProp);
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findCustomerById(id));
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException();
        }

    }

    @PostMapping("/")
    public Customer post(@RequestBody Customer customer) {
        log.info("post");
        log.debug("Customer:{}", customer);
        return customerRepository.createCustomer(customer);
    }
}
