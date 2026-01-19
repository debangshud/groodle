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
@RequestMapping("/api/v1/customers")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Value("${test.prop:test}")
    private String testProp;

    @GetMapping("/")
    public List<Customer> getAll() {
        log.info("method:getAll()");
        log.info("Test Property: {}",testProp);

        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        log.info("method: getById(id)");
        log.debug("Requested Customer Id:{}", id);
        log.info("Test Property: {}",testProp);
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    @PostMapping("/")
    public Customer create(@RequestBody Customer customer) {
        log.info("method:create(customer)");
        log.debug("Customer:{}", customer);
        return repository.save(customer);
    }
}
