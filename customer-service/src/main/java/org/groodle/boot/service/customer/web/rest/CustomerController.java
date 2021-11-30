package org.groodle.boot.service.customer.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.customer.service.CustomerService;
import org.groodle.boot.service.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Value("${test.prop}")
    private String testProp;

    @GetMapping("/")
    public List<Customer> getAll() {
        log.info("method:getAll()");
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        log.info("method: getById(id)");
        log.debug("Requested Customer Id:{}", id);
        return customerService.getById(id);
    }

    @PostMapping("/")
    public Customer create(@RequestBody Customer customer) {
        log.info("method:create(customer)");
        log.debug("Customer:{}", customer);
        return customerService.create(customer);
    }
}
